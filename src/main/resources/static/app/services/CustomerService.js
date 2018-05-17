(function() {
  "use strict";

  angular
    .module("app")
    .factory("CustomerService", ["$http", function(h) {
        return {
          findCustomerPlayerInfo: function(id) {
            return h.get("/customers/" + id + "/player-info").then(function(r) {
              return r.data;
            });
          }
        };
      }
    ]);
})();
