angular.module('myApp')


    .controller('AdherentAjoutCtrl', ['$scope', 'AdherentService', '$rootScope', '$location', '$filter', function ($scope, AdherentService, $rootScope, $location, $filter) {

        $scope.hello = "HELLO WORLD ADHERENT AJOUT";



        $scope.ajouterAdherent = function () {
            var adherent = {
                nom: $scope.nom,
                prenom: $scope.prenom,
                daten: $filter('date')($scope.daten, 'yyyy-MM-dd'),
                age: $scope.age,
                email: $scope.email,
                adress: $scope.adress,
                codeP: $scope.codeP,
                ville: $scope.ville,
                datep: $filter('date')($scope.datep, 'yyyy-MM-dd'),
                datef: $scope.datef,
                montant: $scope.montant

            }
            console.log(adherent.daten);

            AdherentService.postAdherent(adherent).$promise.then(function(result){
                $scope.redirect();
            })
            




        }
        $scope.redirect = function () {
            $location.path('/adherentRecherche');


        }

        $scope.$watch('daten', function () {
            if ($scope.daten) {
                $scope.age = AdherentService.getAge($scope.daten);

            }
        }, true);
        $scope.$watch('datep',function(){
           // console.log('DATE')
            if($scope.datep){
                $scope.datef=AdherentService.getDateFin($scope.datep);
            }
        })





    }]);
