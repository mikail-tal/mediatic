angular.module('myApp')


    .controller('AdherentAjoutCtrl', ['$scope', 'AdherentService','$rootScope','$location', function ($scope, AdherentService,$rootScope,$location) {

        $scope.hello = "HELLO WORLD ADHERENT AJOUT";
        


        $scope.ajouterAdherent = function () {
            var adherent = {
                nom: $scope.nom,
                prenom: $scope.prenom,
                date: $scope.date,
                age: $scope.age,
                email: $scope.email,
                adress: $scope.adress,
                codeP: $scope.codeP,
                ville: $scope.ville,
                datep: $scope.datep,
                datef: $scope.datef,
                montant: $scope.montant

            }

            AdherentService.postAdherent(adherent);
            $scope.redirect();
            
            


        }
        $scope.redirect=function(){
            $location.path('/adherentRecherche');
            

        }
    




    }]);