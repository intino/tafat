"use strict";

var PushService = (function () {

    var callbacks = {};
    var service = {};

    service.openConnection = function (url) {
        var ws = new WebSocket(url);

        ws.onopen = function () {
        };

        ws.onmessage = function (event) {
            var data = JSON.parse(event.data);
            var callbacks = callback(data.name).slice(0);
            callbacks.forEach(function (callback) {
                callback(data.parameters);
            });
        };

        ws.onclose = function () {
            callbacks = {};
        };
    };

    service.registerCallback = function (name, callback) {
        if (!(name in callbacks))
            callbacks[name] = [];
        callbacks[name].push(callback);
        return {
            deregister: function () {
                var index = callbacks[name].indexOf(callback);
                if (index === -1) return;
                callbacks[name].splice(index, 1);
            }
        }
    };

    function callback(name) {
        return name in callbacks ? callbacks[name] : [];
    }

    return service;
})();
