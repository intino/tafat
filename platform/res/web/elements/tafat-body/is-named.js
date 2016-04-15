"use strict";

var TafatBehaviors = TafatBehaviors || {};
TafatBehaviors.IsNamedBehavior = {
    properties: {
        id: {
            type: String
        }
    },

    isNamedAs: function isNamedAs(name) {
        return name === this.id;
    }
};