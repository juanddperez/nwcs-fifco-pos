(function() {
  "use strict";

  angular
    .module("app")
    .controller("CheckoutController", ["$state", "ShoppingCartService", "shoppingCart", function($s, scs, sc) {
      var vm = this;

      vm.shoppingCart = sc;

      vm.complete = function() {
        scs.checkout().then(function(e) {
          var l = e.headers("location"),
              o = l.substring(l.lastIndexOf("/") + 1);
          
          $s.go("summary", { orderNr: o });
        });
      };
    }]);
})();
