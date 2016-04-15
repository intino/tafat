var navigationHandler = (function() {

  var self = {};

  var focus;

  function calculateDelta(e) {
    if (e.wheelDelta) {
      return e.wheelDelta / 120;
    } else if (e.detail) {
      return -e.detail / 3;
    }
  }

  function calculateScale(delta) {
    if (delta < 0) {
      return 1 - (delta / 5);
    }
    return 1 / (1 + (delta / 5));
  }

  self.focus = function(point) {
    focus = point;
  };

  self.calculateRange = function(e, currentRange) {
    var delta = calculateDelta(e);
    if (delta) {
      var scale = calculateScale(delta);
      var min = focus + (currentRange.min - focus) * scale;
      var max = focus + (currentRange.max - focus) * scale;
      return {from: min, to: max};
    }
  };

  return self;
});
