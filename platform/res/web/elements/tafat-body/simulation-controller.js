'use strict';

Polymer({
    is: 'simulation-controller',

    updateTime: function updateTime(time) {
        this.$.time.textContent = time;
    },
    getTime: function getTime() {
        return this.$.time.textContent;
    },
    _play: function _play() {
        if (this.$.play.icon === 'av:play-arrow') {
            this.$.play.icon = 'av:pause';
            this.$.time.style.color = 'blue';
            this.fire('play-simulation');
        } else {
            this.$.play.icon = 'av:play-arrow';
            this.$.time.style.color = 'grey';
            this.fire('pause-simulation');
        }
    },

    _replay: function _replay() {
        this.$.play.icon = 'av:play-arrow';
        this.$.time.style.color = 'grey';
        this.fire('replay');
    },

    _showDialog: function _showDialog() {
        this.querySelector('simulation-controls-dialog').show();
    },

    _updateSpeed: function _updateSpeed(event) {
        this.querySelector('paper-button').textContent = event.detail.speed;
    }
});