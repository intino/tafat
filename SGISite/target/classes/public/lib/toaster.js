(function(){if( typeof Array["prototype"]["indexOf"]!=="function"){Array["prototype"]["indexOf"]=function(_0xf74ex0,_0xf74ex1){for(var _0xf74ex2=(_0xf74ex1||0),_0xf74ex3=this["length"];_0xf74ex2<_0xf74ex3;_0xf74ex2+=1){if((_0xf74ex0===undefined)||(_0xf74ex0===null)){if(this[_0xf74ex2]===_0xf74ex0){return _0xf74ex2}}else {if(this[_0xf74ex2]===_0xf74ex0){return _0xf74ex2}}};return -1;}}})();(function(_0xf74ex4,_0xf74ex5){var _0xf74ex6={gettoaster:function(){var _0xf74ex7=_0xf74ex4("#"+_0xf74ex10["toaster"]["id"]);if(_0xf74ex7["length"]<1){_0xf74ex7=_0xf74ex4(_0xf74ex10["toaster"]["template"])["attr"]("id",_0xf74ex10["toaster"]["id"])["css"](_0xf74ex10["toaster"]["css"])["addClass"](_0xf74ex10["toaster"]["class"]);if((_0xf74ex10["stylesheet"])&&(!_0xf74ex4("link[href="+_0xf74ex10["stylesheet"]+"]")["length"])){_0xf74ex4("head")["appendTo"]("\x3Clink rel=\x22stylesheet\x22 href=\x22"+_0xf74ex10["stylesheet"]+"\x22\x3E")};_0xf74ex4(_0xf74ex10["toaster"]["container"])["append"](_0xf74ex7);};return _0xf74ex7;},notify:function(_0xf74ex8,_0xf74ex9,_0xf74exa){var _0xf74exb=this["gettoaster"]();var _0xf74exc=_0xf74ex4(_0xf74ex10["toast"]["template"]["replace"]("%priority%",_0xf74exa))["hide"]()["css"](_0xf74ex10["toast"]["css"])["addClass"](_0xf74ex10["toast"]["class"]);_0xf74ex4(".title",_0xf74exc)["css"](_0xf74ex10["toast"]["csst"])["html"](_0xf74ex8);_0xf74ex4(".message",_0xf74exc)["css"](_0xf74ex10["toast"]["cssm"])["html"](_0xf74ex9);if((_0xf74ex10["debug"])&&(window["console"])){console["log"](toast)};_0xf74exb["append"](_0xf74ex10["toast"]["display"](_0xf74exc));if(_0xf74ex10["donotdismiss"]["indexOf"](_0xf74exa)=== -1){var _0xf74exd=( typeof _0xf74ex10["timeout"]==="number")?_0xf74ex10["timeout"]:(( typeof _0xf74ex10["timeout"]==="object")&&(_0xf74exa in _0xf74ex10["timeout"]))?_0xf74ex10["timeout"][_0xf74exa]:1500;setTimeout(function(){_0xf74ex10["toast"]["remove"](_0xf74exc,function(){_0xf74exc["remove"]()})},_0xf74exd);};}};var _0xf74exe={"toaster":{"id":"toaster","container":"body","template":"\x3Cdiv\x3E\x3C/div\x3E","class":"toaster","css":{"position":"fixed","top":"10px","right":"10px","width":"300px","zIndex":50000}},"toast":{"template":"\x3Cdiv class=\x22alert alert-%priority% alert-dismissible\x22 role=\x22alert\x22\x3E"+"\x3Cbutton type=\x22button\x22 class=\x22close\x22 data-dismiss=\x22alert\x22\x3E"+"\x3Cspan aria-hidden=\x22true\x22\x3E\x26times;\x3C/span\x3E"+"\x3Cspan class=\x22sr-only\x22\x3EClose\x3C/span\x3E"+"\x3C/button\x3E"+"\x3Cspan class=\x22title\x22\x3E\x3C/span\x3E: \x3Cspan class=\x22message\x22\x3E\x3C/span\x3E"+"\x3C/div\x3E","css":{},"cssm":{},"csst":{"fontWeight":"bold"},"fade":"slow","display":function(_0xf74exc){return _0xf74exc["fadeIn"](_0xf74ex10["toast"]["fade"])},"remove":function(_0xf74exc,_0xf74exf){return _0xf74exc["animate"]({opacity:"0",padding:"0px",margin:"0px",height:"0px"},{duration:_0xf74ex10["toast"]["fade"],complete:_0xf74exf})}},"debug":false,"timeout":1500,"stylesheet":null,"donotdismiss":[]};var _0xf74ex10={};_0xf74ex4["extend"](_0xf74ex10,_0xf74exe);_0xf74ex4["toaster"]=function(_0xf74ex11){if( typeof _0xf74ex11==="object"){if("settings" in _0xf74ex11){_0xf74ex10=_0xf74ex4["extend"](_0xf74ex10,_0xf74ex11["settings"])};var _0xf74ex8=("title" in _0xf74ex11)?_0xf74ex11["title"]:"Notice";var _0xf74ex9=("message" in _0xf74ex11)?_0xf74ex11["message"]:null;var _0xf74exa=("priority" in _0xf74ex11)?_0xf74ex11["priority"]:"success";if(_0xf74ex9!==null){_0xf74ex6["notify"](_0xf74ex8,_0xf74ex9,_0xf74exa)};}};_0xf74ex4["toaster"]["reset"]=function(){_0xf74ex10={};_0xf74ex4["extend"](_0xf74ex10,_0xf74exe);};})(jQuery);