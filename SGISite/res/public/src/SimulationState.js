define(["connection/Request", "connection/Connection", "Graphic", "ClientState", "moment", "SensorsManager", "NotificationMessage"], function (Request, Connection, Graphic, ClientState, moment, SensorsManager, NotificationMessage) { var SimulationState = {};

    var state;

    SimulationState.init  = function () {
        $("#play-pause-button").on('click', leadedPlayPause);
        $(".big-play").on("click", leadedPlayPause);
        $("#stop-button").on('click', stopAction);
    };

    SimulationState.change = function (info) {
        if(state == "Breakpoint" || info.state == "play"){
            SensorsManager.disableAllInputs();
            togglePlayButton();
            return;
        }
        if (info.state == "stop") {
            togglePlayButton();
        } else {
            togglePlayButton();
        }
        state = info.state;
    };

    SimulationState.refreshDate = function (percentage) {
        actualDate = (percentage/100)*(ClientState.endDate.getTime() - ClientState.initDate.getTime()) + ClientState.initDate.getTime();
        actualDate = moment(actualDate).format("DD/MM/YYYY HH:mm:ss");
        $(".Simulation-state").find(".simulation-time").val(actualDate);
    };

    function leadedPlayPause () {
        if (isSensorsInstalled()) {
            playPauseAction();
            $(".upper-play-div").css("visibility", "hidden");
        }else
            $('#sensorModal').modal({show: true});

    }

    function togglePlayButton() {
        $("#play-pause-button").toggleClass("fa-pause");
    }

    function playPauseAction() {
        Request.init("POST", {}, ($("#play-pause-button").hasClass("fa-pause"))? "/Pause" : "/Play");
        Connection.send(Request, function () {
        });
    }

    function stopAction() {
        Request.init("POST", {}, "/Stop");
        Connection.send(Request, function () {
            $("#play-pause-button").removeClass("fa-pause");
        });
    }

    function isSensorsInstalled() {
        return $("#installed-sensor-container").find(".sensor-div").size()>0;
    }

    return SimulationState;
});