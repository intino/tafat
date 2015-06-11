define(["eventHandler/BreakpointEventHandler", "eventHandler/MenuEventHandler", "eventHandler/SimulationEventHandler", "eventHandler/ObjectsEventHandler", "eventHandler/InstallSensorEventHandler", "eventHandler/InitialConfigurationModal"],
    function(breakpointEventHandler, menuEventHandler, simulationEventHandler, ObjectsEventHandler, InstallSensorHandler, SimulationsModalHandler) {
        var EventHandler = {};



        EventHandler.init = function(menu, breakpointSection, datetime, connection) {
            menu.init();
            breakpointEventHandler.init(breakpointSection, connection);
            ObjectsEventHandler.init(connection);
            menuEventHandler.init(menu);
            simulationEventHandler.init();
            InstallSensorHandler.init(connection);
        };


        return EventHandler;
    }

);

