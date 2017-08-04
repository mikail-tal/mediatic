angular.module('myApp')


    .controller('AdherentDetailsCtrl', ['$scope', '$rootScope' ,function ($scope, $rootScope) {

        $scope.hello = "HELLO WORLD ADHRENT DETAILS";


        if ($rootScope.adherent) {

            $scope.nom=$rootScope.adherent.nom;
            $scope.prenom=$rootScope.adherent.prenom;
            $scope.daten=new Date($rootScope.adherent.daten);
            $scope.email=$rootScope.adherent.email;
            $scope.adress=$rootScope.adherent.adress;
            $scope.codeP=$rootScope.adherent.prenom;
            $scope.ville=$rootScope.adherent.prenom;
            $scope.datep=new Date($rootScope.adherent.datep);
            $scope.montant=$rootScope.adherent.montant;


        }else{
            console.log('n existe pas');
        }

    }]);