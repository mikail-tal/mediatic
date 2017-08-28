
'use strict';

angular.module('myApp')

.controller('EmpruntAjoutCtrl', ['$scope', 'AdherentService','$rootScope','$location',"$routeParams", function($scope,AdherentService,$rootScope,$location,$routeParams) {

    


            
        var idAdherent = -1; 
        var idMedia=$routeParams.id;
        //var typeMedia=$rootScope.media.typeMedia;
        var page=0,size=5,id='';
        $scope.recherche='';
        $scope.adherent={}
        $scope.notfound=true;
        

      
      
      
      $scope.$watch('recherche', function () {
    	  
    	  AdherentService.getAdrByName($scope.recherche,page,size).$promise.then(function (result)
                  {
                      console.log(result)
            if($scope.recherche){
            	if(result.numberOfElements){
                    
            		$scope.adherents = result.content;
                    $scope.adherent=$scope.adherents[0];
            	}else{
            		$scope.adherent={}
            		$scope.notfound=true;
            	}
            
            }
           

          });
    	  
    	  
    	  
    	  
    	  
          
              //  $scope.adherents = recherche();
             //   idAdherent = rechercherTitreComplet();
            }, true);
      
      /*AdherentService.getAdherents().$promise.then(function (result) {
            $scope.toutAdherents = result;
        })*/
               
            
        
      

      /*function recherche(){
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
        }*/
      

          $scope.emprunter = function () {

            var emprunt = {
                media: parseInt(idMedia,10),
                adherent: $scope.adherent.id,
                dateEmprunt: $scope.dateE,
                dateRetourPrevue: AdherentService.getDateRetourPrevue($scope.dateE, $routeParams.typeMedia),
                dateRetourEffectif: null
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
        
        
