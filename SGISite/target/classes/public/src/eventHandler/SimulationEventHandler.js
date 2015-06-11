define(function () {
    var SimulationEventHandler = {};

    SimulationEventHandler.init = function () {
        $("#simulation-state-button").on("click", function () {
            $(this).find("i").toggleClass("fa-play").toggleClass("fa-pause");
        });
    };

    return SimulationEventHandler;

});


