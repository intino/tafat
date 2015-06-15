define(['jquery'],
function connection ($){return {

        init : function (direction) {
            this.direction = direction;
        },

        setSimulationId : function (simulationId) {
            this.simulationId= simulationId;
        },

        send : function (request, actionWhenReceive) {
            $.ajax({
                url: "http://"+ this.direction + "/api/" +this.simulationId + request.path,
                dataType: "text",
                type: request.method,
                data: request.data,
                error: function (jqXHR, textStatus) {
                    alert(textStatus)
                }
            }).done(function (msg) {
                actionWhenReceive(msg);
            });
        }

}});