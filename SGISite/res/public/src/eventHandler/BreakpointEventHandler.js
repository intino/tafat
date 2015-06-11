define(["connection/Request", "DateTimePicker"], function (request, Datepicker) {
    var BreakpointEventHandler = {};

    BreakpointEventHandler.init = function(breakpointSection, connection) {
        Datepicker.init();

        Datepicker.setDoneAction(function () {
            if(breakpointSection.isValidDate()) {
                request.init("POST", {time: breakpointSection.getTimeSelected()}, "/Breakpoint");
                connection.send(request, function (message) {
                    addBreakpoint(JSON.parse(message))
                });
            }
        });

        function addBreakpoint(breakpoint) {
            (breakpointSection.isTimeSelected()) ? breakpointSection.createBreakpointView(breakpoint) : null;
        }



        $(".close-breakpoint").on("click", function () {
            var breakpointSection = $(this).parent();
            request.init("DELETE", {breakpointId: $(breakpointSection).data("id")}, "/Breakpoint");//TODO
            connection.send(request, function () {
                breakpointSection.remove()
            });
        });
    };


    return BreakpointEventHandler;

});