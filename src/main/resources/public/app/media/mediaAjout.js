angular.module('myApp')

    .controller('MediaAjoutCtrl', ['$scope', 'MediaService', '$rootScope', function ($scope, MediaService, $rootScope) {

        //ajouter un media  
        $scope.ajoutMedia = function () {

            var media = {
                author: $scope.auteur,
                title: $scope.titre,
                type: $scope.selected
            }
            MediaService.postMedia(media);

        }



    }]);