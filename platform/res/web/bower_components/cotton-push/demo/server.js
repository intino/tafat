var config = require("../package.json");
var ws = require("nodejs-websocket");

var webSocketServer = ws.createServer(function (conn) {
    console.log("New connection");
    conn.on("close", function (code, reason) {
        console.log("Connection closed");
    });
}).listen(8889);

var app = require('connect')();
var serveStatic = require('serve-static');
app.use(serveStatic("../../")).listen(8888);

app.use('/push', function (req, res, next) {
  webSocketServer.connections.forEach(function (conn) {
      conn.sendText(JSON.stringify({title: "Greet", content: "Hello"}));
  });
  res.end("OK");
});

var open = require('open');
open('http://localhost:8888/' + config.name + '/demo/');
