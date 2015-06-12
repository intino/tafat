define(["jquery","eventHandler/EventHandler", "Menu", "connection/Connection", "DateTimePicker", "BreakpointSection", "Graphic","ProgressBar","eventHandler/InitialConfigurationModal", "WebSocketClient", "SimulationState","jquery-ui", "timepicker", "bootstrap", "highcharts","highcharts-module" ],

function main($,eventHandler, menu, connection, datetime, breakpointSection, Graphic, progressbar, modalInitialConfiguration, WebSocketClient, SimulationState) {
    $(window).load(function () {
        $('#myModal').modal("show");
    });
    const REMOTE_DIRECTION = "localhost:8080";

    $("#dropdown-button a").on("click", function () {
        if($(this).data("format")==undefined)
            $('#container-graph').highcharts().print();
        else
        $('#container-graph').highcharts().exportChart({
            type: $(this).data("format"),
            filename: 'my-graphic'
        });
    });

    connection.init(REMOTE_DIRECTION);
    modalInitialConfiguration.init().show().done(function () {
        WebSocketClient.init("localhost:8081");
        Graphic.refreshGraphic();
        eventHandler.init(menu, breakpointSection, datetime, connection);
        SimulationState.init();

        //Graphic

        progressbar.init();

        // Sensor modal
        progressbar.init();
        $("#selectable-sensor").selectable();

    });


});