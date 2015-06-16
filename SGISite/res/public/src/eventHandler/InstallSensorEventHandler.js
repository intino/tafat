define(["connection/Request", "TemplateHelper", "Graphic", "ClientState", "SensorsManager", "connection/Request", "connection/Connection"], function (request, TemplateHelper, Graphic, ClientState, SensorsManager, Request, Connection) {

        var SensorHandler = {};
        var selectsObject = {};

        function getSelectedSensor() {
            var simulationSelection = $('#selectable-sensor').val();
            if(simulationSelection.length==1)
                return simulationSelection[0];
        }

        function isAllDataFilled() {
            var sensorSelection = $("#selectable-sensor").val();
            return ($(selectsObject[0]).val()!="Choose object")  && (sensorSelection.length==1);
        }

        function composeObjectId(){
            var id = "";
            $("#modal-objects-container").children().each(function(index, element){if($(element).val()!="Choose object")id += "/" + $(element).find("option:selected").data("id")});
            return id.substr(1);
        }

        function showSensorInstalled(sensor, color) {
            sensor.objects = [];
            selectsObject.each(function (index, element) {
                if($(element).val()!="Choose object")
                    sensor.objects.push($(element).val());
            });

            TemplateHelper.render("sensor-showing", sensor, function (rendered) {
                $("#installed-sensor-container").append($(rendered)).find("input").keypress(function (event) {
                    if(event.which == 13) {
                        var newSensorValue = {
                            objectId: sensor.objectId,
                            value: parseInt($(this).val()),
                            measurableAttributeName: sensor.measurableAttributeName
                        };
                        request.init("POST", newSensorValue, "/Change");
                        SensorHandler.connection.send(request, function () {

                        });
                    }
                });
                attachEvents();
                SensorsManager.setSensorColor(sensor, color);
            });
        }

        function installSensor() {
            selectsObject = $("#modal-objects-container").children();
            if(isAllDataFilled()) {
                var sensor = {username:ClientState.username, objectId: composeObjectId(), measurableAttributeName: getSelectedSensor()};
                request.init("POST", sensor, "/Sensor");
                SensorHandler.connection.send(request, function () {
                    var colorOnGraphic = Graphic.addNewSensor(sensor);
                    showSensorInstalled(sensor, colorOnGraphic);
                    $("#sensorModal").modal("hide");
                });
            }
        }

        function attachEvents() {
            $(".close-sensor").on("click", function () {
                var sensorDiv = $(this).parent();
                var watchIdentification = $(sensorDiv).data("id");
                Request.init("DELETE", {
                    objectId: watchIdentification.split(":")[0],
                    measurableAttributeName: watchIdentification.split(":")[1],
                    username:ClientState.username
                }, "/Sensor");
                Connection.send(Request, function () {
                    sensorDiv.remove()
                });
                Graphic.deleteSerie(watchIdentification)
            });
        }
        SensorHandler.init =  function (connection) {
            this.connection = connection;
            $("#install-sensor-button").on("click", installSensor);
        };

        return SensorHandler;
});