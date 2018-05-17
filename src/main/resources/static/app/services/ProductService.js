(function() {
  "use strict";

  angular
    .module("app")
    .factory("ProductService", ["$http", function(h) {
        return {
          findAllProducts: function() {
            return h.get("/products").then(function(r) {
              return r.data;
            });
          }
        };
      }
    ]);
})();
