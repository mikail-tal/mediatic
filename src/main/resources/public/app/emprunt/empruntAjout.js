'use strict';

angular.module('myApp.emprunt', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/empruntAjout', {
    templateUrl: 'emprunt/empruntAjout.html',
    controller: 'EmpruntAjoutCtrl'
  });
}])

.controller('EmpruntAjoutCtrl', [function() {

}]);