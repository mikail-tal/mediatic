'use strict';
angular.module('myApp')


    .controller('AdherentAjoutEmpruntCtrl', ['$scope', '$location', 'AdherentService', '$rootScope', '$filter',"$routeParams", function ($scope, $location, AdherentService, $rootScope, $filter,$routeParams) {
        
        var idMedia = -1; // INITIALISATION AVEC UN NBR NEGATIF SI LE MEDIA N EXISTE PAS
        var type='',page=0,size=5;  
        var idAdherent=$routeParams.id;
       // $scope.media={}
        //$scope.media={}
        $scope.types={}
       
      
        

        /* 
            Recuperation des medias pour l autocompletion du champ 
            Capture des evenement clavier sur le champ pour la recherche
        */
        
        
        $scope.$watch('recherche', function () {
        	
        	AdherentService.getMedias($scope.recherche,type,page,size).$promise.then(function (result) {
        		if($scope.recherche){
        			if(result.numberOfElements){
        				$scope.medias=result.content;
        				//$scope.media=$scope.medias[O]
        				
        				
        				
        			}else{
        			
               
        			}
        			
        		}
                
               
            });
        	
        	
          
        }, true);
        
        
 $scope.$watch('media', function () {
	
	 
	 
	 
	 
	 
	 if($scope.medias){
		 console.log($scope.media)
		 //type=$scope.media.type;
		 //console.log(type+' ICI LE TYPE')
     	
    /* 	AdherentService.getMedias($scope.recherche,type,page,size).$promise.then(function (result) {
     		
     			if(result.numberOfElements){
     				
     				$scope.medias = result.content;
     				$scope.med=$scope.medias[0]
     				
     				
                   //  $scope.media.type=$scope.medias[0].type;
                     console.log($scope.med)
                      console.log($scope.medias)
     			}else{
     				console.log('NOTHINNNNG')
     				$scope.medias={}
     				//$scope.media.type={}
     			//	console.log($scope.media)
            
     			}
     			
     		
             
            
         });*/
	 }

        	
        	
          //  $scope.medias = rechercher();
          //  idMedia = rechercherTitreComplet();
        }, true);
        
        
        /* 
            Recuperation de tous les medias pour faire une recherche globale
         */
        /*AdherentService.getMedias().$promise.then(function (result) {
            $scope.toutMedias = result.content;
            
        })*/
        /* 
            La recherche des medias pour afficher 2 suggestions sur les resultats
            Affecter la nouvelle liste de choix d autocompletion
        */
      /*  function rechercher() {
            var rechercheMedia = []
            var count = 0;
            
            angular.forEach($scope.toutMedias, function (value, index) {
                if ($scope.recherche !== undefined) {
                    if (value.titre.toLowerCase().search($scope.recherche.toLowerCase()) > -1 && count < 3) {
                        rechercheMedia[index] = value;
                        count++
                    }
                }

            })
            return rechercheMedia;
        }

         
            Invalider le formulair si le media n existe pas pour l emprunt
            Enregistrer l identifiant et le type du media si trouvee 
        
        function rechercherTitreComplet() {

            var found = false;
            angular.forEach($scope.toutMedias, function (value, index) {
                if (found === false) {
                    if (value.titre === $scope.recherche) {
                        idMedia = value.id;
                        typeMedia = value.type;
                        found = true;
                    }
                }

            })
            if (found === false) {
                $scope.formEmprunt.$invalid = true;
                idMedia = -1;
            }
            return idMedia;
        }*/

        /* 
            Emprunter un Media avec calcul de la date retour prevue
        */
        $scope.emprunter = function () {
        	console.log($scope.media)

            var emprunt = {
                media: $scope.media.id,
                adherent: parseInt(idAdherent,10),
                dateEmprunt: $scope.dateE,
                dateRetourPrevue: AdherentService.getDateRetourPrevue($scope.dateE, $scope.media.type),
                dateRetourEffectif: null
            }
            AdherentService.postEmprunt(emprunt).$promise.then(function (result) {

                $scope.redirect();
            })

        }
        $scope.redirect = function () {
            $location.path('/adherentRecherche');
        }

    }]);