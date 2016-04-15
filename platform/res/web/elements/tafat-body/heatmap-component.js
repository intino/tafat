'use strict';

Polymer({
    is: 'heatmap-component',
    behaviors: [Polymer.IronResizableBehavior],

    properties: {
        heatmap: {
            type: Object,
            observer: '_process'
        },
        _regions: Array,
        _regionsLoaded: {
            type: Boolean,
            value: false
        }
    },

    listeners: {
        'iron-resize': '_onIronResize'
    },

    refresh: function refresh(values) {
        if (!this._regionsLoaded) return;
        this._processValues(values.values);
    },

    _process: function _process() {
        this.id = this.heatmap.title;
        this._image = 'data:image/png;base64,' + this.heatmap.background;
    },

    _onIronResize: function _onIronResize() {
        if (this.offsetHeight !== 0) {
            this._setRegions();
        }
    },

    _setRegions: function _setRegions() {
        if (this._regionsLoaded) return;
        var ratio = this.$.background.height / this.$.background.naturalHeight;
        this.heatmap.regions.forEach((function (region) {
            region.top = (this.$.background.offsetTop + region.top * ratio) * 100 / this.$.background.offsetHeight;
            region.left = (this.$.background.offsetLeft + region.left * ratio) * 100 / this.$.background.offsetWidth;
            region.height = region.height * ratio * 100 / this.$.background.offsetHeight;
            region.width = region.width * ratio * 100 / this.$.background.offsetWidth;
        }).bind(this));
        this._regionsLoaded = true;
        if (this.offsetHeight >= this.parentElement.offsetHeight) {
            this.$.background.style.height = this.parentElement.offsetHeight + 'px';
        }
        this._regions = this.heatmap.regions;
    },

    _imageLoaded: function _imageLoaded() {
        if (this.offsetHeight !== 0) {
            this._setRegions();
        }
    },

    _processValues: function _processValues(regions) {
        var values = regions.map(function (region) {
            return region.value;
        });
        var min = Math.min.apply(null, values);
        var max = Math.max.apply(null, values);
        if (max - min > 10) {
            var filteredValues = this._filterOutliers(values);
            min = Math.min.apply(null, filteredValues);
            max = Math.max.apply(null, filteredValues);
        }
        for (var i = 0; i < regions.length; i++) this.querySelector('#' + regions[i].id).style.opacity = this._shadeOf(regions[i].value, max, min);
    },

    _filterOutliers: function _filterOutliers(array) {
        var values = array.concat();
        values.sort(function (a, b) {
            return a - b;
        });
        var q1 = values[Math.floor(values.length / 4)];
        var q3 = values[Math.ceil(values.length * (3 / 4))];
        var iqr = q3 - q1;
        var maxValue = q3 + iqr * 3;
        var minValue = q1 - iqr * 3;
        return values.filter(function (x) {
            return x < maxValue && x > minValue;
        });
    },

    _shadeOf: function _shadeOf(value, max, min) {
        if (max === min) return 0.2;
        var result = 0.6 * (value - min) / (max - min) + 0.2;
        return result > 0.8 ? 0.8 : result;
    },

    _setRadius: function _setRadius(regionType) {
        return regionType === 'square' ? '0%' : '50%';
    }
});