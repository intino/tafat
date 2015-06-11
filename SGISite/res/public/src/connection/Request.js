define(
function request (){return {

        init: function (method, data, path) {
            this.method = method;
            this.path = path;
            this.data = ($.isEmptyObject(data))? "" : JSON.stringify(data);
        }

    }
});
