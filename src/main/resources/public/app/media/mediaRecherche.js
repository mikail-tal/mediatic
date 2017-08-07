angular.module('myApp')

    .controller('MediaRechercheCtrl', ['$scope','$resource','MediaService', function ($scope,$resource,MediaService) {

        $scope.media= "bienvenue dans Recherche ";
    
        $scope.dataMedia = MediaService.getMediaViaResource();
            console.log($scope.dataMedia);
        $scope.dataMedia.$promise.then(function(){
            console.log($scope.dataMedia);
        });

    }]);