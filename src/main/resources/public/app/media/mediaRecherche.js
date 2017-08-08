angular.module('myApp')

    .controller('MediaRechercheCtrl', ['$scope','AdherentService','$resource','MediaService','$rootScope','$location',
     function ($scope,AdherentService,$resource,MediaService,$rootScope,$location) {

          // recupurer les données  // 
        $scope.dataMedia = MediaService.getMediaViaResource();
            console.log($scope.dataMedia);
        $scope.dataMedia.$promise.then(function(){
            console.log($scope.dataMedia);
        });
            /// supprimer un media ///
        $scope.supprimerMedia = function (index) {

            console.log('je suis dans la function supprimer');
           MediaService.removeMedia(index);
        };
       
          $scope.afficherMediaDetail = function (index) {
              console.log('dans la function afficher')
            angular.forEach($scope.dataMedia, function (value, key) {
                if (key === index) {
                    $rootScope.media = value ;
                }
            });
            $scope.redirect();
        };  
        // redirection vers le detail du media 
        $scope.redirect = function () {
            $location.path('/mediaDetail');
        }
    }]);