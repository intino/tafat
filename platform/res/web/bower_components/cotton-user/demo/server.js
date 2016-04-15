var app = require('connect')();
var serveStatic = require('serve-static');
app.use(serveStatic('../../')).listen(8889);

var open = require('open');

open('http://localhost:8889/cotton-user/demo/');
