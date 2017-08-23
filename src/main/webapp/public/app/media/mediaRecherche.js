angular.module('myApp')

    .controller('MediaRechercheCtrl', ['$scope', 'AdherentService', '$resource', 'MediaService', '$rootScope', '$location', 'orderByFilter',
        function ($scope, AdherentService, $resource, MediaService, $rootScope, $location, orderBy) {

            // recupurer les données  // 
           
           // console.log($scope.dataMedia);
            	MediaService.getMediaViaResource().$promise.then(function (result) {
            		 $scope.dataMedia = result;
                console.log($scope.dataMedia);
            	angular.forEach($scope.dataMedia,function(value,key){
        			//console.log(key + "  " + value.dateNaissaince);
        			
        			//	console.log(value.dateNaissaince);
        				//.log(key)
            		
            		/*console.log(value);
            		console.log(key)
            		if(value.empruntEnCours){
            		
        				$scope.dataMedia[key].dateRetourPrevue=new Date(value.empruntEnCours.adherent.dateRetourPrevue);
        				console.log(value.dateRetourPrevue);

            		}
        				*/
        			
        		});
            	
            	
                $scope.propertyName = '';
                $scope.reverse = false;
                $scope.dataMedia = orderBy($scope.dataMedia, $scope.propertyName, $scope.reverse);
            });
            /// supprimer un media ///
            /*
            $scope.supprimerMedia = function (index) {

               // console.log('je suis dans la function supprimer');
                MediaService.removeMedia(index);
            };
            */
            $scope.afficherMediaDetail = function (index) {
            	var id;
              //  console.log('dans la function afficher')
                angular.forEach($scope.dataMedia, function (value, key) {
                    if (key === index) {
                    	id=value.id
                       // $rootScope.media = value;
                    	
                    }
                });
                $scope.redirect(id);
            };
            // redirection vers le detail du media 
            $scope.redirect = function (id) {
                $location.path('/mediaDetail/'+id);
            }
            //////// tri par id


            $scope.sortBy = function (propertyName) {
              //  console.log(propertyName)
                $scope.reverse = (propertyName !== null && $scope.propertyName === propertyName) ? !$scope.reverse : false;
                $scope.propertyName = propertyName;
                $scope.dataMedia = orderBy($scope.dataMedia, $scope.propertyName, $scope.reverse);
            };


        }
    ]);