var app = require('connect')();
var serveStatic = require('serve-static');
app.use(serveStatic('../../')).listen(8888);

require('open')('http://localhost:8888/cotton-header/demo/');
