'use strict';

angular.module('myApp')

.controller('EmpruntAjoutCtrl', ['$scope', 'MediaService', function($scope, MediaService) {

    
        var idAdherent = -1; 
        MediaService.getMediaViaResource().$promise.then(function (result)
      {
        $scope.adherents = result;

      });


      function recherche(){
        $scope.rechercheAdherent = [];
        var count= 0;

        angular.forEach($scope.adherents, function (value, index){
          if($scope.recherche !== undefined){
            if (value.nom.toLowerCase().search($scope.recherche.toLowerCase()) > -1 && count < 3){
              $scope.rechercheAdherent[index]=value;
              count++;
            }
          }
        })
                    return $scope.rechercheAdherent;
      }

      

       $scope.emprunter = function () {

            var emprunt = {
                media: $rootScope.media.id,
                adherent: idAdherent,
                dateE: $scope.dateE,
                dateR:MediaService.getDateRetourPrevue(),
                dateEf: null
            }
            AdherentService.postEmprunt(emprunt).$promise.then(function (result) {

                $scope.redirect();
            })

        }
        $scope.redirect = function () {
            $location.path('/adherentRecherche');
        }

    }]);

        
        
        
        
        
        
        
        
        //ajouter un media  

         /*   
        $scope.ajoutEmprunt = function () {
            console.log('dans la fonction ajout media');
          var emprunt= {
            media:$scope.media,
            m:$scope.media,
            date: $scope.date,

            }
          EmpruntService. postEmprunt(emprunt);
        }*/
        
        
      
