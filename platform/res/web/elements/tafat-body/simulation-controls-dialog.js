'use strict';

Polymer({
    is: 'simulation-controls-dialog',

    _handleSlider: function _handleSlider() {
        if (!this.querySelector('#speedSlider')) return;
        var newSpeed = eval(this._getSpeedValue());
        $.post('/speed/' + newSpeed, (function (data) {
            if (data !== 'OK') return;
            this.fire('speed-change', { speed: this._getSpeedString() });
        }).bind(this));
    },

    _getSpeedString: function _getSpeedString() {
        var speedString = this._getSpeed();
        return speedString === '1024x' ? 'max' : speedString === '1/1024x' ? 'min' : speedString;
    },

    _getSpeedValue: function _getSpeedValue() {
        var speedString = this._getSpeed();
        return speedString.substring(0, speedString.length - 1);
    },

    _getSpeed: function _getSpeed() {
        var value = this.querySelector('#speedSlider').immediateValue;
        if (value === 10) return '1x';
        if (value < 10) return '1/' + Math.pow(2, 10 - value) + 'x';
        return Math.pow(2, value - 10) + 'x';
    },

    _fastRewind: function _fastRewind() {
        this.querySelector('#speedSlider').decrement();
    },

    _fastForward: function _fastForward() {
        this.querySelector('#speedSlider').increment();
    },

    _setSliderTo1x: function _setSliderTo1x() {
        this.querySelector('#speedSlider').value = 10;
    },

    _immediateChange: function _immediateChange() {
        this.fire('speed-change', { speed: this._getSpeedString() });
    },

    show: function show() {
        this.querySelector('paper-dialog').open();
    },

    _move: function _move() {
        this.async(function () {
            var speed = $('#speed');
            this.querySelector('paper-dialog').style.top = speed.position().top + 22 + 'px';
            this.querySelector('paper-dialog').style.left = speed.position().left - 118 + 'px';
        });
    }
});