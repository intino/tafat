define([ "Graphic", "ClientState", "SensorsManager", "ProgressBar", "DateTimePicker", "SimulationState"], function (Graphic, ClientState, SensorsManager, ProgressBar, DateTimePicker, SimulationState) {


    function configureWebSocket(websocket) {
        websocket.onopen = function () {
            this.send(ClientState.username+":"+ClientState.simulationId);
        };

        websocket.onmessage = function (message) {message = JSON.parse(message.data);

            if(message.type == "initGraphic") {
                Graphic.initTimeLine(message.info);
                DateTimePicker.setRange(new Date(message.info.init), new Date(message.info.end));
                ClientState.initDate = new Date(message.info.init);
                ClientState.endDate = new Date(message.info.end);
            }
            if(message.type == "sensorValues") {
                Graphic.addNewData(message.info);
                SensorsManager.update(message.info);

            }


            if(message.type == "progress") {
                ProgressBar.value(message.info.percentage);
                SimulationState.refreshDate(message.info.percentage);
            }
            if(message.type == "Breakpoint") {
                SimulationState.change({state:"Breakpoint"});
                SensorsManager.enableAllInputs();
            }
            if(message.type == "SimulationState") {
                SimulationState.change(message.info);
            }

        }
    }

    return {
        init : function (URL) {
            this.webSocket = new WebSocket("ws://" + URL);
            configureWebSocket(this.webSocket);
        }
    };

});