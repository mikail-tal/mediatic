'use strict';
angular.module('myApp')


    .controller('AdherentRechercheCtrl', ['$scope', 'AdherentService', '$rootScope', '$location', function ($scope, AdherentService, $rootScope, $location) {

        /* 
            Recuperation des adherent dans le serveur
        */
        $scope.adherentsTab = AdherentService.getAdherents();

        /* 
            Completer A jour sur les cotisations 
        */
        $scope.adherentsTab.$promise.then(function(result){
            result.forEach(function(element){
                if(new Date(element.datef)<new Date()){
                    element.ajour="NON";
                }else{
                    element.ajour="OUI";
                }  
            })
        });
        /* 
            Afficher les details de l adherent quand on click sur une ligne du tableau
            Redirection vers la page des details de l adherent
        */

        $scope.afficherDetails = function (index) {
            angular.forEach($scope.adherentsTab, function (value, key) {
                if (key === index) {
                    $rootScope.adherent = value;
                }
            });
            $scope.redirect();
        };
        $scope.redirect = function () {
            $location.path('/adherentDetail');


        }

    }]);