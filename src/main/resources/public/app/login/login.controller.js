'use strict';

angular.module('myApp')
.controller('LoginCtrl',['$location', '$localStorage', 'LoginService', '$scope', function ($location, $localStorage, LoginService, $scope) {
    
    $scope.user = {};
    
    $scope.login = function () {
      LoginService.storeUser($scope.user);
      $location.path('/mediaRecherche');
    }
  }]);