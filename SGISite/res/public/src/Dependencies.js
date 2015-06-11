requirejs.config({
    "baseUrl": "src",
    shim : {
        "bootstrap" : {"deps" :['jquery']},
        "timepicker" : {"deps" :['jquery', 'jquery-ui']},
        "highcharts" : {"deps" :['jquery']},
        "highcharts-module" : {"deps" :['jquery', "highcharts"]},
        "toaster" : {"deps" : ['jquery', 'bootstrap']}
    },
    paths : {
        "highcharts" : "http://code.highcharts.com/stock/highstock",
        "highcharts-module" : "http://code.highcharts.com/stock/modules/exporting",
        "jquery-ui":"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min",
        "jquery": "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min",
        "toaster" : "https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min",
        "moment" : "https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min",
        "bootstrap" : "//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min",
        "dust.core" : "../lib/dustCore.min",
        "timepicker" : "../lib/timepicker",
        "lib": "../lib",
        "template": "../views/template"
    }
});
define.amd.dust = true;
requirejs(["main"]);
