'use strict';
angular.module('myApp')


    .controller('AdherentAjoutCtrl', ['$scope', 'AdherentService', '$rootScope', '$location', '$filter', function ($scope, AdherentService, $rootScope, $location, $filter) {
        $scope.adherent={}
        $scope.adherent.montant=0;
        /* 
            Verifier que les champs addresse,codePostal,ville et montant ne sont pas undefined 
            Attribuer une valeur par defaut 
        */
        function verifUndefined(){
            if($scope.adress===undefined){
                $scope.adress="";
            }
            if($scope.codeP===undefined){
                $scope.codeP="";
            }
            if($scope.ville===undefined){
                $scope.ville="";
            }
            if($scope.montant===undefined){
                $scope.montant=0;
            }
        }

        /* 
            Ajout d un adherent avec un filtre pour les dates pour le fichier JSON
            Redirection vers la page de recherche apres la resolution de la promesse
        */
        $scope.ajouterAdherent = function () {
           // verifUndefined();

            /*  $scope.adherent = 
            
            {
                nom: $scope.nom,
                prenom: $scope.prenom,
                daten: $filter('date')($scope.daten, 'yyyy-MM-dd'),
                age: $scope.age,
                email: $scope.email,
                adress: $scope.adress,
                codeP: $scope.codeP,
                ville: $scope.ville,
                datep: $filter('date')($scope.datep, 'yyyy-MM-dd'),
                datef: $filter('date')($scope.datef, 'yyyy-MM-dd'),
                montant: $scope.montant

            } */
            AdherentService.postAdherent($scope.adherent).$promise.then(function(result){
                $scope.redirect();
            })
        }


        $scope.redirect = function () {
            $location.path('/adherentRecherche');


        }
        /* 
            Suivre les evenements de la date de naissance et la date de paiement
            Modification de l age et de la date de fin d abonnement
        */

        $scope.$watch('adherent.daten', function () {
            if ($scope.adherent.daten) {
                $scope.adherent.age = AdherentService.getAge($scope.adherent.daten);

            }
        }, true);
        $scope.$watch('adherent.datep',function(){
            if($scope.adherent.datep){
                $scope.adherent.datef=AdherentService.adherent.getDateFin($scope.adherent.datep);
            }
        },true);





    }]);
