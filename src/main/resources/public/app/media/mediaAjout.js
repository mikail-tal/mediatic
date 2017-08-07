angular.module('myApp')

    .controller('MediaAjoutCtrl', ['$scope', function ($scope) {

        $scope.media = "bienvenue dans media ";
        $scope.titre = '';
        $scope.auteur = '';
        $scope.selected = '';
        $scope.list = [];

        $scope.ajoutMedia = function () {
            console.log('dans la fonction ajout media');
            if ($scope.titre && $scope.auteur && $scope.selected) {
                console.log('on as passe le if');
                
            }
    }
        $scope.supprimerMedia = function (index) {
            console.log('je suis dans la function supprimer');
            $scope.list.splice(index, 1);
        };

    }]);