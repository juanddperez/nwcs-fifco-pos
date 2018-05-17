(function() {
  "use strict";

  angular
    .module("app")
    .controller("SummaryController", ["$state", "$stateParams", "order", function($s, $sp, o) {
      var vm = this;

      vm.order = o;
    }]);
})();
