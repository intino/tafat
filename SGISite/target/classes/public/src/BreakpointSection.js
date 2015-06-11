define(['moment'], function(moment){
    BreakpointSection= {};

    BreakpointSection.createBreakpointView = function (breakpoint) {
        breakpointDate = $("#breakpointDatepicker").clone().attr("id", breakpoint.breakpointId).attr("disabled","").css('visibility', "visible");
        breakpointContainer = $('.breakpoint-slot').clone(true, true)
            .removeClass("breakpoint-slot").css('visibility', 'visible')
            .data("id", breakpoint.breakpointId);
        timeslot = breakpointContainer.find(".time-slot").append(breakpointDate);
        $('#breakpoint-body').append(breakpointContainer);
    };



    BreakpointSection.isTimeSelected = function () {
        return this.getTimeSelected() != "";
    };

    BreakpointSection.getTimeSelected = function () {
        return moment($("#breakpointDatepicker").val(), "DD/MM/YYYY HH:mm:ss").format("MM/DD/YYYY HH:mm:ss");
    };

    BreakpointSection.isValidDate = function () {
        return $("#breakpointDatepicker").val() != "";
    }

    return BreakpointSection;
});

