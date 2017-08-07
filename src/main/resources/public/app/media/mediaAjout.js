angular.module('myApp')

    .controller('MediaAjoutCtrl', ['$scope','MediaService', function ($scope,MediaService) {

            //ajouter un media  
        $scope.ajoutMedia = function () {
            console.log('dans la fonction ajout media');
          var media= {
            author: $scope.auteur ,
            title: $scope.titre ,
            type: $scope.selected
            }
          MediaService. postMedia(media)  ;
        }
        
       

    }]);