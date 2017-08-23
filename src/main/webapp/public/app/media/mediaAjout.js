angular.module('myApp')

    .controller('MediaAjoutCtrl', ['$scope', 'MediaService', '$rootScope', function ($scope, MediaService, $rootScope) {

        //ajouter un media  
    	$scope.media={}
        $scope.ajoutMedia = function () {

            /*var media = {
                author: $scope.auteur,
                title: $scope.titre,
                type: $scope.selected
            }*/
            console.log($scope.media)
            MediaService.postMedia($scope.media)

        }



    }]);