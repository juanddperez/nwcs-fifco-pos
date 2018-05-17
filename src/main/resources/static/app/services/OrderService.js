(function() {
  "use strict";

  angular
    .module("app")
    .factory("OrderService", ["$http", function(h) {
        return {
          findOrderByOrderNr: function(n) {
            return h.get("/orders/" + n).then(function(r) {
              return r.data;
            });
          }
        };
      }
    ]);
})();
