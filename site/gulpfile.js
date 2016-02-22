'use strict';

// Include Gulp & tools we'll use
var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var runSequence = require('run-sequence');
var livereload = require('gulp-livereload');
var merge = require('merge-stream');
var path = require('path');
var fs = require('fs');

var AUTOPREFIXER_BROWSERS = [
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

var PRODUCTION_PATH = '../out/production/tafat/web';

var styleTask = function (stylesPath, srcs) {
    return gulp.src(srcs.map(function(src) {
            return path.join('app', stylesPath, src);
        }))
        .pipe($.changed(stylesPath, {extension: '.css'}))
        .pipe($.autoprefixer(AUTOPREFIXER_BROWSERS))
        .pipe(gulp.dest('.tmp/' + stylesPath))
        .pipe($.cssmin())
        .pipe(gulp.dest('dist/' + stylesPath));
};

var jshintTask = function (src) {
    return gulp.src(src)
        .pipe($.jshint.extract()) // Extract JS from .html files
        .pipe($.jshint())
        .pipe($.jshint.reporter('jshint-stylish'))
        .pipe($.jshint.reporter('fail'));
};

var imageOptimizeTask = function(src, dest) {
  return gulp.src(src)
    .pipe($.imagemin({
      progressive: true,
      interlaced: true
    }))
    .pipe(gulp.dest(dest));
};

var optimizeHtmlTask = function (src, dest) {
    var assets = $.useref.assets({searchPath: ['.tmp', 'app', 'dist']});

    return gulp.src(src)
        // Replace path for vulcanized assets
        .pipe($.if('*.html', $.replace('elements/elements.html', 'elements/elements.vulcanized.html')))
        .pipe(assets)
        // Concatenate and minify JavaScript
        .pipe($.if('*.js', $.uglify({preserveComments: 'some'})))
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

gulp.task('copy-styles', function () {
    return styleTask('styles', ['**/*.css']);
});

gulp.task('copy-element-styles', function () {
    return styleTask('elements', ['**/*.css']);
});

gulp.task('transpile-js', function () {
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

gulp.task('check-js', function () {
    return jshintTask([
        'app/scripts/**/*.js',
        'app/elements/**/*.js',
        'app/elements/**/*.html',
        'gulpfile.js'
    ]);
});

// Optimize images
gulp.task('copy-images', function() {
  return imageOptimizeTask('app/images/**/*', 'dist/images');
});

// Copy all files at the root level (app)
gulp.task('generate-dist-folder', function () {
    return gulp.src([
        'app/*',
        '!app/index.html'
    ], {
        dot: true
    }).pipe(gulp.dest('dist'));
});

gulp.task('copy-dependencies', function () {
    return gulp.src(['bower_components/**/*']).pipe(gulp.dest('dist/bower_components'));
});

gulp.task('copy-elements', function () {
    var elements = gulp.src(['app/elements/**/*.html',
            'app/elements/**/*.css',
            'app/elements/**/*.js'])
        .pipe(gulp.dest('dist/elements'));

    var vulcanized = gulp.src(['app/elements/elements.html'])
        .pipe($.rename('elements.vulcanized.html'))
        .pipe(gulp.dest('dist/elements'));

    return merge(elements, vulcanized);
});

// Copy all files to res directory from server module
gulp.task('copy-to-res', function () {
    return gulp.src([
        'dist/**/*'
    ], {
        dot: true
    }).pipe(gulp.dest('../Base/res/web'));
});

gulp.task('copy-to-server', function () {
    return gulp.src([
        'dist/**/*'
    ], {
        dot: true
    }).pipe(gulp.dest(PRODUCTION_PATH));
});

gulp.task('copy-fonts', function () {
    return gulp.src(['app/fonts/**'])
        .pipe(gulp.dest('dist/fonts'));
});

gulp.task('copy-js', function () {
    return gulp.src(['app/**/*.js', '!app/{elements}/**/*.js']).pipe(gulp.dest('dist'));
});

gulp.task('copy-minified-html', function () {
    return optimizeHtmlTask(['app/**/*.html', '!app/{elements}/**/*.html'], 'dist');
});

gulp.task('vulcanize', function () {
    var DEST_DIR = 'dist/elements';
    return gulp.src('dist/elements/elements.vulcanized.html')
        .pipe($.vulcanize({
            stripComments: true,
            inlineCss: true,
            inlineScripts: true
        }))
        .pipe(gulp.dest(DEST_DIR));
});

// Clean output directory
gulp.task('clean', function (cb) {
  fs.unlink('.tmp', function () {
    fs.unlink('dist', function () {
      cb();
    });
  });
});

gulp.task('dev', ['copy-dev-app-to-res'], function() {
    livereload.listen({
        basePath: 'app'
    });

    gulp.watch(['app/{styles,elements}/**/*.css'], ['refresh']);
    gulp.watch(['app/{scripts,elements}/**/{*.js,*.html}'], ['refresh']);
});

gulp.task('refresh', function(cb) {
    runSequence('generate-dev-app', 'copy-to-server', 'reload', cb);
});

gulp.task('reload', function() {
    gulp.src(PRODUCTION_PATH).pipe(livereload());
});

gulp.task('copy-dev-app-to-res', function(cb) {
    runSequence('generate-dev-app', 'copy-to-res', cb);
});

gulp.task('generate-dev-app', ['clean'], function(cb) {
    runSequence(
        'generate-dist-folder', ['copy-styles', 'copy-dependencies', 'copy-images', 'copy-elements', 'copy-element-styles', 'copy-fonts'],
        'transpile-js', 'copy-js', cb);
});

gulp.task('default', ['clean'], function (cb) {
    runSequence(
        'generate-dist-folder', ['copy-styles', 'copy-dependencies', 'copy-images', 'copy-elements', 'copy-element-styles', 'copy-fonts'],
        'transpile-js',
        //'copy-minified-html',
        'copy-to-res',
        cb);
});
