'use strict';

angular.module('myApp')
.controller('LoginCtrl',['$location', '$localStorage', 'LoginService', '$scope','$rootScope', function ($location, $localStorage, LoginService, $scope,$rootScope) {
    
    $scope.user = {};
    
    $scope.login = function () {
        LoginService.login($scope.user);

        var user=$rootScope.user
        
    	
      //LoginService.storeUser(user);
      console.log(user)
     
      
    }
  }]);