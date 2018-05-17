(function() {
  "use strict";

  angular
    .module("app")
    .controller("PosViewController", ["$state", "$stateParams", "ShoppingCartService", "customer", "shoppingCart", "products", function($s, $sp, s, c, sp, p) {
      var vm = this;

      vm.customer = c;
      
      vm.products = p;
      vm.products=[{code:"CB",description:"Cerveza Bavaria",imageSrc:"images/products/bavaria.png",price:1000,points:5},{code:"CP",description:"Cerveza Pilsen",imageSrc:"images/products/pilsen.png",price:900,points:5},{code:"CI",description:"Cerveza Imperial",imageSrc:"images/products/imperial.png",price:1125,points:10},{code:"CIL",description:"Cerveza Imperial Light",imageSrc:"images/products/imperial_light.png",price:1150,points:5},{code:"CIS",description:"Cerveza Imperial Silver",imageSrc:"images/products/imperial_silver.png",price:1100,points:5}];

      vm.initShoppingCart = function() {
        vm.shoppingCart = {
          elements: {},
          totalElements: 0,
          totalAmount: 0,
          customerId: vm.customer.playerId
        };
      };

      if (!sp || !sp.totalAmount) {
        vm.initShoppingCart();
      } else {
        vm.shoppingCart = sp;
      }

      vm.checkout = function() {
        if (vm.shoppingCart.totalElements > 0) {
          s.saveShoppingCart(vm.shoppingCart).then(function() {
            $s.go("checkout");
          });
        } else {
          alert("No hay elementos en el carrito para cobrar");
        }
      };

      vm.calculateTotals = function() {
        vm.shoppingCart.totalElements = vm.shoppingCart.totalAmount = 0;
        angular.forEach(vm.shoppingCart.elements, function(v, k) {
          vm.shoppingCart.totalElements += v.quantity;
          vm.shoppingCart.totalAmount += v.total;
        });
      };

      vm.removeProduct = function(product) {
        delete vm.shoppingCart.elements[product.code];
        vm.calculateTotals();
      };

      vm.addProduct = function(product) {
        if (!vm.shoppingCart.elements[product.code]) {
          vm.shoppingCart.elements[product.code] = {
              product: product,
              quantity: 0,
              total: product.price
          }
        }
        var e = vm.shoppingCart.elements[product.code];
        e.quantity += 1;
        e.total = e.product.price * e.quantity;
        e.totalPoints = e.product.points * e.quantity;
        vm.calculateTotals();
      };
    }]);
})();
