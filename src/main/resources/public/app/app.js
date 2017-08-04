'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'myApp.adherent',
  'myApp.version'
]).
  config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');

    $routeProvider.when('/view1', {
      templateUrl: 'view1/view1.html',
      controller: 'View1Ctrl'
    }).when('/view2', {
      templateUrl: 'view2/view2.html',
      controller: 'View2Ctrl'
    }).when('/adherentRecherche', {
      templateUrl: 'adherent/adherentRecherche.html',
      controller: 'AdherentRechercheCtrl'


    }).when('/adherentAjout', {
      templateUrl: 'adherent/adherentAjout.html',
      controller: 'AdherentAjoutCtrl'

    }).when('/adherentDetail', {
      templateUrl: 'adherent/adherentDetails.html',
      controller: 'AdherentDetailsCtrl'
    }


      ).otherwise({ redirectTo: '/view1' });
  }]);
