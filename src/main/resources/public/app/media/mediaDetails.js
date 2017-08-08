angular.module('myApp')

    .controller('MediaDetailsCtrl', ['$scope', '$rootScope', '$location', 'MediaService',
        function ($scope, $rootScope, $location, MediaService) {
            
            
            if($rootScope.media){
               $scope.media = $rootScope.media;
               $scope.check=true;
               
            }else{
                 $location.path('/mediaRecherche');
            }

              
            $scope.modifierMedia = function () {
                 $scope.check=false;
            }
            $scope.enregistrerMedia = function () {
                // console.log('enregistre');
                MediaService.updateMedia($scope.media);
               
            }
        
            $scope.emprunter = function () {
                $location.path('/mediaAjoutEmprunt');
            }



        }]);

