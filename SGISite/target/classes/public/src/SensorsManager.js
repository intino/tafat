
define([], function () {var SensorsManager = {};

    SensorsManager.update = function (data) {
        var sensorDiv = findSensorInput(data.objectId + ":" + data.measurableAttributeName);
        $(sensorDiv).val(data.value);
    };

    SensorsManager.enableAllInputs = function () {
        accessToInputs(false);
    };

    SensorsManager.disableAllInputs = function () {
        accessToInputs(true);
    };

    function accessToInputs(boolean) {
        $("#installed-sensor-container").find("input").prop('disabled', boolean);
    }

    SensorsManager.setSensorColor = function (sensor, selectedColor) {
        $(findSensorDiv(sensor.objectId+":"+sensor.measurableAttributeName)).find(".legend-square-color").css("background", selectedColor);
    };

    function findSensorInput(identifier) {
        return $(findSensorDiv(identifier)).find("input");
    }

    function findSensorDiv (identifier) {
        return $("#installed-sensor-container").find("[data-id='"+identifier+"']");
    }

    return SensorsManager;
});
