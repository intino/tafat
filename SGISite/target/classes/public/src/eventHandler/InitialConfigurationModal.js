define(['connection/Request', 'connection/Connection','TemplateHelper', 'ClientState'], function (Request, Connection, TemplateHelper, ClientState) {
    InitialConfiguration = {events : {}};


    InitialConfiguration.init = function (websocket) {
        settings();
        getAllSimulations();
        return this;
    };

    InitialConfiguration.show = function () {
        $('#myModal').modal('show');
        return this;
    };

    InitialConfiguration.done = function (onDone) {
        this.events.onDone = function () {
            $('#myModal').addClass('fade').modal('hide').removeClass("show");
            onDone();
        }
    };

    function configureModal() {
        $('#myModal').modal({
            backdrop: 'static',
            keyboard: false
        });
    }

    function configureButtonClick() {
        $("#SubscriptionButton").on('click', function () {
            if (isAllNecessaryData()) {
                subscribeToSimulation();
            } else {
                alert("Please fill all information")
            }
        });
    }

    function settings() {
        configureModal();
        configureButtonClick();
    }

    function getAllSimulations() {
        Request.init('GET', {}, '/Simulations');
        Connection.send(Request, function (simulations) {
            TemplateHelper.render("selection", JSON.parse(simulations), addSimulations)
        });
    }

    function subscribeToSimulation() {
        ClientState.simulationId = getSelectedSimulation();
        ClientState.username = $("#username").val();
        document.title = getSelectedSimulation() + " - Tafat";
        $("#simulation-name").append("Tafat - " + getSelectedSimulation());
        $("#username-title").append(ClientState.username);
        Connection.setSimulationId(getSelectedSimulation());
        Request.init('POST', {username: $("#username").val()}, '/Subscribe');
        Connection.send(Request, function () {
            InitialConfiguration.events.onDone();
        });
    }

    function addSimulations(template) {
        var firstValue = $('#selectable-simulation').append($(template)).find("option").val();
        $('#selectable-simulation').val(firstValue);
    }

    function getSelectedSimulation() {
        var simulationSelection = $('#selectable-simulation').val();
        if(simulationSelection.length==1)
            return simulationSelection[0];
        return null;
    }

    function isAllNecessaryData() {
        return (getSelectedSimulation() != null) && ($("#username").val() != "");
    }

    return InitialConfiguration;
});