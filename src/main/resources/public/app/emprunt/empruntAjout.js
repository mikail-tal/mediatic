'use strict';

angular.module('myApp')

.controller('EmpruntAjoutCtrl', ['$scope', function($scope) {

  $scope.nom = '';
  $scope.date = '';
  $scope.list = [];

  
  $scope.npm = function($scope){
      if($scope.nom && $scope.date)
        {
          $scope.list.push(
            {
              nom: $scope.nom,
              date: $scope.date

            }
          )
        }
  }
      
}]);