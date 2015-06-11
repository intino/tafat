define(["dust.core"], function (dust) { var TemplateHelper = {};

    TemplateHelper.render = function (templateName, context, callback) {
        require(["template/"+templateName], function (template) {
            dust.render(template, context, function (err, out) {
                if (err) console.log(err + " with name: " + templateName);
                callback(out);
            });
        });
    };

    return TemplateHelper;

});