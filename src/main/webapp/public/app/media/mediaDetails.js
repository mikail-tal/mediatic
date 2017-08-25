angular.module('myApp')

    .controller('MediaDetailsCtrl', ['$scope', '$rootScope', '$location', 'MediaService','$routeParams',
        function ($scope, $rootScope, $location, MediaService,$routeParams) {
    	
   
	
	
	
    //	console.log($routeParams.id);
            
            MediaService.getMedia($routeParams.id).$promise.then(function (result) {
            	$scope.media=result;
            	console.log(result)
            /*	angular.forEach($scope.media.emprunt,function(value,key){
            		console.log(value)
            		console.log('VALUE')
            		$scope.adherents=value.adherent;
            		//console.log($scope.adherents[0].adherent.nom)
        			console.log(value);
        			//console.log(key);
        		});*/
            	$scope.adherents=$scope.media.emprunt;
            	
            	//console.log(result);
            //	console.log($scope.media.type);
            	
            	
            	
            	
            	  $scope.emprunter = function () {
                      $location.path('/mediaAjoutEmprunt/'+$routeParams.id+"/"+$scope.media.type);
                  }
            	
            })
           /* if($rootScope.media){
               $scope.media = $rootScope.media;
               $scope.check=true;
               
            }else{
                 $location.path('/mediaRecherche');
            }

              
            $scope.modifierMedia = function () {
                 $scope.check=false;
            }*/
            $scope.enregistrerMedia = function () {
                 console.log('enregistre');
                MediaService.updateMedia($scope.media);
               
            }
            
            
            
            
        
          



        }]);

