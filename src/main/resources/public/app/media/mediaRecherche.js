angular.module('myApp')

    .controller('MediaRechercheCtrl', ['$scope','AdherentService','$resource','MediaService','$rootScope', function ($scope,AdherentService,$resource,MediaService,$rootScope) {

          
        $scope.dataMedia = MediaService.getMediaViaResource();
            console.log($scope.dataMedia);
        $scope.dataMedia.$promise.then(function(){
            console.log($scope.dataMedia);
        });
            // supprimer un media 
        $scope.supprimerMedia = function (index) {

            console.log('je suis dans la function supprimer');
           MediaService.removeMedia(index);
        };
       
          $scope.afficherMediaDetail = function (index) {
            angular.forEach($scope.media, function (value, key) {
                if (key === index) {
                    $rootScope.media = value;
                }
            });
            $scope.redirect();
        };
        $scope.redirect = function () {
            $location.path('/mediaDetail');


        }


        

    }]);