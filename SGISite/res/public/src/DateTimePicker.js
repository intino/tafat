define(function(){
    var DateTimePicker = {datepicker:null};
    var doneAction = undefined;
    //Para milisegundos es con L minuscula
    DateTimePicker.init = function (){
        DateTimePicker.datepicker = $('#breakpointDatepicker');
        $("#launchDatepicker").on("click", function () {
            DateTimePicker.showDatePicker();
        });

        DateTimePicker.datepicker.datetimepicker({
            dateFormat : "dd/mm/yy",
            showSecond: true,
            timeFormat: 'HH:mm:ss',
            onClose: function (dateText, inst) {
                function isDonePressed() {
                    return ($('#ui-datepicker-div').html().indexOf('ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all ui-state-hover') > -1);
                }
                if (isDonePressed()){
                    doneAction();
                }
            }
        })
            .timepicker("option", "showAnim", "fadeIn");

    };

    DateTimePicker.setDoneAction = function (onDoneAction) {
        doneAction = onDoneAction;
    }

    DateTimePicker.showDatePicker = function(){
        DateTimePicker.datepicker.datepicker("show")
    };

    DateTimePicker.setRange = function (initDate, endDate) {
        DateTimePicker.setBeginning(initDate);
        DateTimePicker.setFinal(endDate);
    };

    DateTimePicker.setBeginning = function (date) {
        DateTimePicker.datepicker.datepicker( "option", "minDate", date );
    };

    DateTimePicker.setFinal = function (date) {
        DateTimePicker.datepicker.datepicker( "option", "maxDate", date );
    };

    return DateTimePicker;
});