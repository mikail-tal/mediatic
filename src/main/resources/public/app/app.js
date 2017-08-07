'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'ngResource',
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

    }).when('/mediaAjout', {
        templateUrl: 'media/mediaAjout.html',
        controller: 'MediaAjoutCtrl'


    }).when('/mediaDetail', {
        templateUrl: 'media/mediaDetails.html',
        controller: 'MediaDetailsCtrl'

    }).when('/mediaRecherche', {
        templateUrl: 'media/mediaRecherche.html',
        controller: 'MediaRechercheCtrl'

    }).when('/adherentAjoutEmprunt', {
        templateUrl: 'adherent/adherentAjoutEmprunt.html',
        controller: 'AdherentAjoutEmpruntCtrl'

    }).otherwise({ redirectTo: '/view1' });

      }]).constant('config', {
        apiUrl: 'http://localhost:3000'
      }).run(['$rootScope', function ($rootScope) {
        $rootScope.adherent;
        //  $rootScope.adherentsTab=[];
      }]);


