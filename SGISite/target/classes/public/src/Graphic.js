
define(["SensorsManager", "ClientState"], function(SensorManager, ClientState){
    var Graphic = {};
    var seriesOptions = [];

    Graphic.addNewSensor = function (sensor) {
        var chart = $('#container-graph').highcharts();
        return chart.addSeries({
            id: sensor.objectId +":"+ sensor.measurableAttributeName,
            name: sensor.measurableAttributeName,
            marker:{
                enabled: true
            }
        }, false).color;

    };

    Graphic.initTimeLine = function initTimeLine(info) {
        var chart = $('#container-graph').highcharts();
        chart.addSeries({
            id: 'time',
            type: 'line',
            name: 'time',
            color : "transparent",
            enableMouseTracking : false,
            marker : {},
            data : (function () {
                var data = [], i,
                    endTime = new Date(info.end).getTime(),
                    numberOfPoints = (endTime - new Date(info.init).getTime())/info.precision;

                for (i = -numberOfPoints; i <= 0; i += 1)
                    data.push([ endTime + i * info.precision,0 ]);

                return data;
            }())
        });
        chart.hideLoading('Loading time...');
    };

    Graphic.addNewData = function addNewData(sensor) {
        var serie = $('#container-graph').highcharts().get(sensor.objectId +":"+ sensor.measurableAttributeName);
        serie.addPoint([(new Date(sensor.time)).getTime(), sensor.value]);
    };



    Graphic.refreshGraphic = function (){
        Highcharts.setOptions({
            global : {
                useUTC : false

            }
        });

        $('#container-graph').highcharts('StockChart', {

            colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9',
                '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1'],
            tooltip :{
                positioner : function () {
                    var x = $('#container-graph').highcharts().chartWidth-180;
                    return {x:x, y:0};
                }
            },
            rangeSelector: {
                buttons: [{
                    count: 1,
                    type: 'minute',
                    text: '1M'
                }, {
                    count: 5,
                    type: 'minute',
                    text: '5M'
                }, {
                    type: 'all',
                    text: 'All'
                }],
                inputEnabled: false,
                selected: 0
            },

            series: seriesOptions,
            exporting: {
                enabled:false
            },
            xAxis: {
                ordinal:false
            }
        });
        $('#container-graph').highcharts().showLoading('Loading time...');
    };

    return Graphic;

});

