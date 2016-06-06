'use strict';

const gulp = require('gulp');
const $ = require('gulp-load-plugins')();
const runSequence = require('run-sequence');
const merge = require('merge-stream');
const path = require('path');
const fs = require('fs');

const AUTOPREFIXER_BROWSERS = [
    'ie >= 10',
    'ie_mob >= 10',
    'ff >= 30',
    'chrome >= 34',
    'safari >= 7',
    'opera >= 23',
    'ios >= 7',
    'android >= 4.4',
    'bb >= 10'
];

const PRODUCTION_PATH = '..\\out\\production\\tafat\\web';

const styleTask = (stylesPath, srcs) => {
    return gulp.src(srcs.map(src => path.join('app', stylesPath, src)))
.pipe($.changed(stylesPath, {extension: '.css'}))
        .pipe($.autoprefixer(AUTOPREFIXER_BROWSERS))
        .pipe(gulp.dest('.tmp/' + stylesPath))
        .pipe($.cssmin())
        .pipe(gulp.dest('dist/' + stylesPath));
};

const lint = src => {
    return gulp.src(src)
        .pipe($.eslint())
        .pipe($.eslint.format())
        .pipe($.eslint.failAfterError());
};

const imageOptimizeTask = (src, dest) => {
    return gulp.src(src)
        .pipe($.imagemin({
            progressive: true,
            interlaced: true
        }))
        .pipe(gulp.dest(dest));
};

const optimizeHtmlTask = (src, dest) => {
    let assets = $.useref.assets({searchPath: ['.tmp', 'app', 'dist']});

    return gulp.src(src)
        // Replace path for vulcanized assets
        .pipe($.if('*.html', $.replace('elements/elements.html', 'elements/elements.vulcanized.html')))
        .pipe(assets)
        // Concatenate and minify JavaScript
        .pipe($.if('*.js', $.uglify({preserveComments: false})))
        // Concatenate and minify styles
        // In case you are still using useref build blocks
        .pipe($.if('*.css', $.cssmin()))
        .pipe(assets.restore())
        .pipe($.useref())
        // Minify any HTML
        .pipe($.if('*.html', $.htmlmin({
            quotes: true,
            empty: true,
            spare: true
        })))
        // Output files
        .pipe(gulp.dest(dest));
};

gulp.task('copy-styles', () => styleTask('styles', ['**/*.css']));

gulp.task('copy-element-styles', () => styleTask('elements', ['**/*.css']));

gulp.task('transpile-js', () => {
    return gulp.src([
            'app/**/*.{js,html}',
            '!app/index.html'
        ])
        .pipe($.sourcemaps.init())
        .pipe($.if('*.html', $.crisper())) // Extract JS from .html files
        .pipe($.if('*.js', $.babel()))
        .pipe($.sourcemaps.write('.'))
        .pipe(gulp.dest('.tmp/'))
        .pipe(gulp.dest('dist/'));
});

gulp.task('check-js', () => {
    return lint([
        'app/scripts/**/*.js',
        'app/elements/**/*.js',
        'app/elements/**/*.html',
        'gulpfile.js'
    ]);
});

// Optimize images
gulp.task('copy-images', () => {
    return imageOptimizeTask('app/images/**/*', 'dist/images');
});

// Copy all files at the root level (app)
gulp.task('generate-dist-folder', () => {
    return gulp.src([
        'app/*',
        '!app/test'
    ], {
        dot: true
    }).pipe(gulp.dest('dist'));
});

gulp.task('copy-dependencies', () => {
    return gulp.src(['bower_components/**/*']).pipe(gulp.dest('dist/bower_components'));
});

gulp.task('copy-elements', () => {
    let elements = gulp.src(['app/elements/**/*.html',
            'app/elements/**/*.css',
            'app/elements/**/*.js'])
        .pipe(gulp.dest('dist/elements'));

let vulcanized = gulp.src(['app/elements/elements.html'])
    .pipe($.rename('elements.vulcanized.html'))
    .pipe(gulp.dest('dist/elements'));

return merge(elements, vulcanized);
});

gulp.task('copy-to-res', () => {
    return gulp.src([
        'dist/**/*'
    ], {
        dot: true
    }).pipe(gulp.dest('../platform/res/web'));
});

gulp.task('copy-to-server', () => {
    return gulp.src([
        'dist/**/*'
    ], {
        dot: true
    }).pipe(gulp.dest(PRODUCTION_PATH));
});

gulp.task('copy-fonts', () => {
    return gulp.src(['app/fonts/**'])
        .pipe(gulp.dest('dist/fonts'));
});

gulp.task('copy-js', () => {
    return gulp.src(['app/**/*.js', '!app/{elements,test}/**/*.js']).pipe(gulp.dest('dist'));
});

gulp.task('copy-minified-html', () => {
    return optimizeHtmlTask(['app/**/*.html', '!app/{elements,test}/**/*.html'], 'dist');
});

gulp.task('vulcanize', () => {
    const DEST_DIR = 'dist/elements';
return gulp.src('dist/elements/elements.vulcanized.html')
    .pipe($.vulcanize({
        stripComments: true,
        inlineCss: true,
        inlineScripts: true
    }))
    .pipe(gulp.dest(DEST_DIR));
});

// Clean output directory
gulp.task('clean', cb => {
    fs.unlink('.tmp', () => fs.unlink('dist', () => cb()));
});

gulp.task('dev', ['copy-dev-app-to-res'], () => {
    $.livereload.listen({
    basePath: 'app'
});

gulp.watch(['app/{styles,elements}/**/*.css'], ['refresh']);
gulp.watch(['app/{scripts,elements}/**/{*.js,*.html}'], ['refresh']);
});

gulp.task('refresh', (cb) => {
    runSequence('generate-dev-app', 'copy-to-server', 'copy-to-res', 'reload', cb);
});

gulp.task('reload', () => {
    gulp.src(PRODUCTION_PATH).pipe($.livereload());
});

gulp.task('copy-dev-app-to-res', cb => {
    runSequence('generate-dev-app', 'copy-to-res', 'copy-to-server', cb);
});

gulp.task('generate-dev-app', ['clean'], cb => {
    runSequence(
    'generate-dist-folder', ['copy-styles', 'copy-dependencies', 'copy-images', 'copy-elements', 'copy-element-styles', 'copy-fonts'],
'check-js', 'transpile-js', 'copy-js', cb);
});

gulp.task('default', ['clean'], cb => {
    runSequence(
    'generate-dist-folder', ['copy-styles', 'copy-dependencies', 'copy-images', 'copy-elements', 'copy-element-styles', 'copy-fonts'],
'check-js', 'transpile-js',
    'copy-minified-html',
    'vulcanize',
    'copy-to-res',
    cb);
});
