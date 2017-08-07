'use strict';

angular.module('myApp')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/login', {
      templateUrl: 'login/login.html',
      controller: 'LoginCtrl',
      controllerAs: 'ctrl'
    });
  }])
  .controller('LoginCtrl', function ($location, $localStorage, LoginService) {
    var ctrl = this;
    ctrl.user = {};
    
    ctrl.login = function () {
      LoginService.storeUser(ctrl.user);
      $location.path('media-search');
    }
  });