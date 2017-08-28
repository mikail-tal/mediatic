'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'ngResource',
  'myApp.version',
  'ngStorage'
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

    }).when('/adherentDetail/:id', {
      templateUrl: 'adherent/adherentDetails.html',
      controller: 'AdherentDetailsCtrl'

    }).when('/mediaAjout', {
      templateUrl: 'media/mediaAjout.html',
      controller: 'MediaAjoutCtrl'


    }).when('/mediaDetail/:id', {
      templateUrl: 'media/mediaDetails.html',
      controller: 'MediaDetailsCtrl'

    }).when('/mediaRecherche', {
      templateUrl: 'media/mediaRecherche.html',
      controller: 'MediaRechercheCtrl'

    }).when('/adherentAjoutEmprunt/:id', {
      templateUrl: 'adherent/adherentAjoutEmprunt.html',
      controller: 'AdherentAjoutEmpruntCtrl'

    }).when('/login', {
      templateUrl: 'login/login.html',
      controller: 'LoginCtrl'

    }).when('/mediaAjoutEmprunt/:id/:typeMedia', {
      templateUrl: 'media/mediaAjoutEmprunt.html',
      controller: 'EmpruntAjoutCtrl'

    }).when('/userAjout', {
      templateUrl: 'user/AjoutUser.html',
      controller: 'UserCtrl'

    }).when('/userRecherche', {
      templateUrl: 'user/UserRecherche.html',
      controller: 'UserRechercheCtrl'

    }).when('/userDetails/:id', {
      templateUrl: 'user/UserDetails.html',
      controller: 'UserDetailsCtrl'

    }).otherwise({ redirectTo: '/login' });

  }]).constant('config', {
    apiUrl: '/mediatic'
  }).run(['$rootScope', '$location', function ($rootScope, $location) {
    $rootScope.$on("$locationChangeStart", function (event, next, current) {

      console.log($rootScope.login)
      if (!$rootScope.login) {

        if ($location.path() !== '/login') {
          $location.path('/login')
          //console.log(event , next , current)
        }

      } else {

      }
    });
    $rootScope.adherent;
    $rootScope.media;
    $rootScope.login;
    if ($rootScope.login) {
      $location.path('/login');
    }


    //  $rootScope.adherentsTab=[];
  }]);



