define(function () {
    ProgressBar = {};

    ProgressBar.init = function() {
        $("#progressbar").progressbar({
            value: 0
        });
    };

    ProgressBar.value = function (value) {
        $("#progressbar").progressbar("value", value);
    };

    return ProgressBar;

});