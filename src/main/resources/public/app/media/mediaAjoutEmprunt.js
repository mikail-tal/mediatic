
'use strict';

angular.module('myApp')

.controller('EmpruntAjoutCtrl', ['$scope', 'AdherentService','$rootScope','$location', function($scope,AdherentService,$rootScope,$location) {

    


            if($rootScope.media)
              {
                
        var idAdherent = -1; 
        var typeMedia=$rootScope.media.typeMedia;

                AdherentService.getAdherents().$promise.then(function (result)
              {
        $scope.adherents = result;
        console.log(result)
        $scope.$watch('recherche', function () {
          console.log('ABC')
                $scope.adherents = recherche();
                idAdherent = rechercherTitreComplet();
            }, true);

      });
      AdherentService.getAdherents().$promise.then(function (result) {
            $scope.toutAdherents = result;
        })
               
            }else{
                 $location.path('/mediaRecherche');
            }
        
      

      function recherche(){
        $scope.rechercheAdherent = [];
        var count= 0;
        
        angular.forEach($scope.toutAdherents, function (value, index){
          console.log(value);
          if($scope.recherche !== undefined){
            console.log(value.nom)
            if (value.nom.toLowerCase().search($scope.recherche.toLowerCase()) > -1 && count < 3){
              console.log('====')
              console.log('ABC')
              $scope.rechercheAdherent[index]=value;
              count++;
            }
          }
        })
                    return $scope.rechercheAdherent;
      }



         function rechercherTitreComplet() {

            var found = false;
            angular.forEach($scope.adherents, function (value, index) {
                if (found === false) {
                    if (value.nom === $scope.recherche) {
                        idAdherent = value.id;
                      
                        found = true;
                    }
                }

            })
            if (found === false) {
                $scope.formEmprunt.$invalid = true;
                idAdherent = -1;
            }
            return idAdherent;
        }
      

          $scope.emprunter = function () {

            var emprunt = {
                media: $rootScope.media.id,
                adherent: idAdherent,
                dateE: $scope.dateE,
                dateR: AdherentService.getDateRetourPrevue($scope.dateE, typeMedia),
                dateEf: null
            }
            AdherentService.postEmprunt(emprunt).$promise.then(function (result) {

                $scope.redirect();
            })

        }
        $scope.redirect = function () {
            $location.path('/mediaRecherche');
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
        
        
