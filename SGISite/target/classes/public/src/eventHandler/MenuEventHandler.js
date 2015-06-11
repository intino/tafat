define(function(){
    var MenuHandler={};

    MenuHandler.init = function (menu) {
        $('#visibility-menu-button').on("click", function () {
            menu.slideMenu($);
        });

        $('#menu-panel').on('click', function (event) {
            menu.toggleMenuSection($, event.target);
        });

        $('#addSensor').on('click', function (event) {
            event.preventDefault();
            $('#sensorModal').modal({show: true});
        });

        $('.calendar-button').on("click", function () {
            datetime.showDatePicker();
        });
    }
    return MenuHandler;
});
