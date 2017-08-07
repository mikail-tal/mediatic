angular.module('myApp')

    .controller('MediaDetailsCtrl', ['$scope','MediaAjoutCtrl','$rootScope', function ($scope,MediaAjoutCtrl,$rootScope) {
       $scope.modifierMedia =function () {
          if ($rootScope.media) {

            $scope.titre = $rootScope.media.auteur;
            $scope.auteur = $rootScope.media.titre;
            $scope.type = $rootScope.media.selected;
          
        } else {
            $location.path('/mediaRecherche');
        }
       }
       $scope.enregistrerMedia = function(){

       }

        
           

    }]);