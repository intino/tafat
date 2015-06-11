define(["connection/Request", "TemplateHelper" ], function (request, templateHelper) {
    var ObjectsEventHandler = {};

    ObjectsEventHandler.init = function(connection){
        request.init("GET", {}, "/RootObjects");
        connection.send(request, addNewObjects);

        function addNewObjects(objects) {
            objects = JSON.parse(objects);
            if(objects.length>0)
                templateHelper.render("objects-selector", objects, function(template){
                    $("#modal-objects-container").append(addEventToAddMoreObjects($(template)))
                });
        }

        function addEventToAddMoreObjects(element) {element.
            on("change", function () {
                element.nextAll().remove();
                if(element.val()!="Choose object") {
                    var id = composeId();
                    getObjectSensors(id);
                    request.init("GET", {}, "/ObjectComponents?objectId=" + id);
                    connection.send(request, addNewObjects);
                }else{
                    $("#selectable-sensor").empty();
                }
            });
            return element;
        }

        function composeId(){
            var id = "";
            $("#modal-objects-container").children().each(function(index, element){id += "/" + $(element).find("option:selected").data("id")});
            return id.substr(1);
        }

        function getObjectSensors(objectId) {
            request.init("GET", {}, "/MeasurableAttributes?objectId="+ objectId);
            connection.send(request, showSensors);
        }

        function showSensors(sensors) {
            templateHelper.render("selection", JSON.parse(sensors), function(out){
                $("#selectable-sensor").empty();
                $("#selectable-sensor").append(out);
            });
        }
    };

    return ObjectsEventHandler;

});