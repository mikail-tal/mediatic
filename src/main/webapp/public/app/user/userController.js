'use strict';

angular.module('myApp')
.controller('UserCtrl',['$location', '$localStorage', 'LoginService', '$scope', function ($location, $localStorage, LoginService, $scope) {
    
    $scope.user = {};
    
    $scope.enregistrer = function () {
      LoginService.storeUser($scope.user);
      $location.path('/login');
    }
  }]);