(function() {
  "use strict";

  angular
    .module("app")
    .factory("ShoppingCartService", ["$http", function(h) {
        return {
          checkout: function() {
            return h.post("/shopping-cart/checkout").then(function(r) {
              return r;
            });
          },
          saveShoppingCart: function(c) {
            return h.post("/shopping-cart", c).then(function(r) {
              return r.data;
            });
          },
          getShoppingCart: function(c) {
            return h.get("/shopping-cart").then(function(r) {
              return r.data;
            });
          }
        };
      }
    ]);
})();
