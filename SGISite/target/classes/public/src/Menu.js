define(['DateTimePicker', 'MenuSection'],
    
function (dateSelector, menuSection) {

    var menu = {};
    var $menuPanel;

    menu.init = function () {
        $menuPanel = $('#menu-panel');
        dateSelector.init($);
    };

    menu.toggleMenuSection = function ($, target) {
        ($(target).prop('id')=="menu-panel")? null : menuSection.toggle($,target);
    };

    menu.slideMenu = function ($) {
        (isSlided()) ? slideIn() : slideOut();
    };

    function isSlided() {
        return $menuPanel.hasClass('animated slideOutLeft');
    }

    function slideIn() {
        $menuPanel.removeClass('animated slideOutLeft');
        $menuPanel.css('display', 'block');
        $('#graph').css('width', '');
        $menuPanel.addClass('animated slideInLeft');
    }

    function slideOut() {
        $menuPanel.removeClass('animated slideInLeft');
        $menuPanel.addClass('animated slideOutLeft');
        $menuPanel.css('display', 'none');
        $('#graph').css('width', '100%');
    }

    return menu;
});

