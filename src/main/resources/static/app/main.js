(function() {
  "use strict";

  angular
    .module("app", ["ui.router", "ngLocale"])
    .config(["$urlRouterProvider", "$stateProvider", "$locationProvider", function($r, $s, $l) {
      $r.otherwise("/");
      $l.html5Mode(true);

      $s.state("pos", {
        url: "/",
        templateUrl: "app/templates/pos-view.html?customer",
        controllerAs : "vm",
        params: {
          customer: "10156111015540865"
        },
        resolve: {
          customer: ["$stateParams", "CustomerService", function($sp, s) {
            return s.findCustomerPlayerInfo($sp.customer);
          }],
          shoppingCart: ["ShoppingCartService", function(s) {
            return s.getShoppingCart();
          }],
          products: ["ProductService", function(s) {
            return s.findAllProducts();
          }]
        },
        controller : "PosViewController"
      });
      
      $s.state("checkout", {
        url: "/checkout",
        templateUrl: "app/templates/checkout.html",
        controllerAs : "vm",
        resolve: {
          shoppingCart: ["ShoppingCartService", function(s) {
            return s.getShoppingCart();
          }]
        },
        controller : "CheckoutController"
      });

      $s.state("summary", {
        url: "/summary/:orderNr",
        templateUrl: "app/templates/summary.html",
        params: {
          orderNr: null
        },
        resolve: {
          order: ["$stateParams", "OrderService", function($sp, s) {
            return s.findOrderByOrderNr($sp.orderNr);
          }]
        },
        controllerAs : "vm",
        controller : "SummaryController"
      });
    }]);
})();
