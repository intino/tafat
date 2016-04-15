'use strict';

Polymer({
    is: 'tafat-body',

    properties: {
        selected: {
            type: Number
        },
        _components: Array,
        _interval: Object
    },

    attached: function attached() {
        this._loadConfiguration();
    },

    _loadConfiguration: function _loadConfiguration() {
        $.get('/interfaceConfiguration', (function (data) {
            var configuration = JSON.parse(data);
            document.title = configuration.title;
            this.querySelector('cotton-header').title = configuration.title;
            this.querySelector('cotton-header').logo = 'data:image/png;base64,' + configuration.logo;
            this.querySelector('simulation-controller').updateTime(configuration.time);
            $('#favicon').attr('href', 'data:image/png;base64,' + configuration.logo);
            this._process(configuration.components);
        }).bind(this));
    },

    _play: function _play() {
        var _this = this;

        $.get('/play', function (data) {
            if (data === 'OK') {
                _this._interval = setInterval(_this._reload.bind(_this), 100);
            }
        });
    },

    _pause: function _pause() {
        var _this2 = this;

        $.get('/pause', function (data) {
            if (data === 'OK') {
                clearInterval(_this2._interval);
            }
        });
    },

    _replay: function _replay() {
        var _this3 = this;

        $.get('/reset', function (data) {
            if (data === 'OK') {
                clearInterval(_this3._interval);
            }
            _this3._loadConfiguration();
        });
    },

    _process: function _process(components) {
        this._components = components;
        this.querySelector('cotton-footer').tabs = components.map(function (component) {
            return component.title;
        });
    },

    _reload: function _reload() {
        $.get('/values', (function (input) {
            var data = JSON.parse(input);
            this.querySelector('simulation-controller').updateTime(data.time);
            data.values.forEach((function (component) {
                component.time = this.querySelector('simulation-controller').getTime();
                this.querySelector('[name="' + component.title + '"]').refresh(component);
            }).bind(this));
        }).bind(this));
    },
    _isHeatmap: function _isHeatmap(type) {
        return type === 'heatmap';
    },
    _isChart: function _isChart(type) {
        return type === 'chart';
    }
});