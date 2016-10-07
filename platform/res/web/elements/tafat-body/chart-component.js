'use strict';

Polymer({
    is: 'chart-component',

    properties: {
        chart: {
            type: Object
        },
        chartElement: Object
    },

    attached: function attached() {
        var _this = this;

        this._process();
        setInterval(function () {
            return _this.chartElement.redraw();
        }, 1000);
    },

    refresh: function refresh(data) {
        data.values.forEach((function (point) {
            var newPoint = { instant: this._toMillis(data.time), value: point.value };
            this.chartElement.addPointWithoutRedraw(point.id, newPoint);
        }).bind(this));
    },

    _process: function _process() {
        this.id = this.chart.title;
        this.chartElement = this.querySelector('cotton-time-series');
        this.chart.lines.forEach((function (line) {
            this.chartElement.addSeries(line.label);
        }).bind(this));
    },

    _toMillis: function _toMillis(time) {
        return new Date(time).getTime();
    }

});