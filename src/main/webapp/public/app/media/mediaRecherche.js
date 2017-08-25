angular.module('myApp')

    .controller('MediaRechercheCtrl', ['$scope', 'AdherentService', '$resource', 'MediaService', '$rootScope', '$location', 'orderByFilter',
        function ($scope, AdherentService, $resource, MediaService, $rootScope, $location, orderBy) {
    	
     	$scope.media={};
    	$scope.adherents={};
    	$scope.disabled=true;
    	
    	$scope.selected='10';
		var page=0;
		var size=10;
		var totalPages;
		$scope.range=[1,2,3,4,5];
		
		
		
        MediaService.getByPage(page,size).$promise.then(function (result) {
        	totalPages=result.totalPages;
        	page=result.number;
        	 fillArray();
        	 $scope.dataMedia =result.content;
        		
        		console.log(result);
        });
		
		
		
		$scope.change=function(index){
			MediaService.getByPage(index,size).$promise.then(function (result) {
				totalPages=result.totalPages;	
            	page=result.number;
            	 fillArray();
            	 $scope.dataMedia =result.content;
			})
		}
		
		
		$scope.$watch('selected',function(){
			size=$scope.selected;
		MediaService.getByPage(page,size).$promise.then(function (result) {		
			totalPages=result.totalPages;
        	 fillArray();
        	 $scope.dataMedia =result.content;
				
			})
		                
		            
		        },true);
		
		
		
		
		function fillArray(){
			var tab=[]
			
			for(var i=0;i<totalPages;i++){
       		 tab[i]=i+1;
       	 }
			$scope.range=tab;
			
		}
    	
    	
    	
    	
    	var idSort = false, auteurSort = false, titreSort = false,
        typeSort = false, empParSort = false, dateRetourPrevueSort = false;
    	
    	
    	
    	$scope.search={
    			titre:'',
    			auteur:'',
    			type:'',
    			
    	}
    	
	 $scope.$watch('search.titre',function(){
		 if(!$scope.search.titre){
			 $scope.search.titre=''
		 }
		 
	       // 	console.log($scope.adherent.cotisation.datePaiement)
	            console.log($scope.search.keyword)
	        },true);
    	$scope.$watch('search.auteur',function(){
    		if(!$scope.search.auteur){
   			 $scope.search.auteur=''
   		 }
   		 
 	       // 	console.log($scope.adherent.cotisation.datePaiement)
 	            console.log($scope.search.keyword)
 	        },true);
    	$scope.$watch('search.type',function(){
    		if(!$scope.search.type){
   			 $scope.search.type=''
   		 }
   		 
 	       // 	console.log($scope.adherent.cotisation.datePaiement)
 	            console.log($scope.search.keyword)
 	        },true);
            // recupurer les données  // 
           
           // console.log($scope.dataMedia);
    /*        	MediaService.getMediaViaResource().$promise.then(function (result) {
            		 $scope.dataMedia = result.content;
                console.log($scope.dataMedia);
            	angular.forEach($scope.dataMedia,function(value,key){
        			//console.log(key + "  " + value.dateNaissaince);
        			
        			//	console.log(value.dateNaissaince);
        				//.log(key)
            		
            		console.log(value);
            		console.log(key)
            		if(value.empruntEnCours){
            		
        				$scope.dataMedia[key].dateRetourPrevue=new Date(value.empruntEnCours.adherent.dateRetourPrevue);
        				console.log(value.dateRetourPrevue);

            		}
        				
        			
        		});
            	
            	
                $scope.propertyName = '';
                $scope.reverse = false;
                $scope.dataMedia = orderBy($scope.dataMedia, $scope.propertyName, $scope.reverse);
            });*/
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
            
            $scope.searchBy=function(){
            	
            	MediaService.getMediaBy($scope.search.titre,$scope.search.auteur,$scope.search.type).$promise.then(function (result) {
            		//console.log(result);
            		$scope.dataMedia=result.content;
            	})
            }
            $scope.searchAll=function(){
            	MediaService.search($scope.search.keyword).$promise.then(function(result){
            		console.log("abcs")
            		$scope.dataMedia=result.content;
            	})
            
            }
            
            
            //////// tri par id


           /* $scope.sortBy = function (propertyName) {
              //  console.log(propertyName)
                $scope.reverse = (propertyName !== null && $scope.propertyName === propertyName) ? !$scope.reverse : false;
                $scope.propertyName = propertyName;
                $scope.dataMedia = orderBy($scope.dataMedia, $scope.propertyName, $scope.reverse);
            };*/
            
            $scope.sortId = function () {
                idSort = (!idSort);
                //nomSort = (!nomSort);
                if (idSort === true) {
                	MediaService.filter('id','asc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                } else {
                	MediaService.filter('id','desc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                }

            };
                // TRI PAR NOM
            $scope.sortTitre = function () {
                auteurSort = (!auteurSort);
                if (auteurSort === true) {
                	MediaService.filter('titre','asc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                } else {
                	MediaService.filter('titre','desc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                }


            };
                // TRI PAR PRENOM
            $scope.sortAuteur = function () {
                titreSort = (!titreSort);
                if (titreSort === true) {
                MediaService.filter('auteur','asc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                } else {
               MediaService.filter('auteur','desc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                }

            };
                //  TRI PAR DATE DE NAISSANCE
            $scope.sortType = function () {
            	typeSort = (!typeSort);
                if (typeSort === true) {
                
                	MediaService.filter('type','asc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                } else {
                	MediaService.filter('type','desc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                }
            };
                //TRI PAR LES ADHERENT QUI SONT A JOUR DANS LEUR COTISATIONS OU PAS
            $scope.sortEmpruntePar = function () {
            	empParSort = (!empParSort);
                if (empParSort === true) {
                	MediaService.filter('emprunteepar','asc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                } else {
                	MediaService.filter('emprunteepar','desc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                }
            };

                // TRI PAR NOMBRE DE MEDIA
            $scope.sortDateRetourPrevue = function () {
            	dateRetourPrevueSort = (!dateRetourPrevueSort);
                if (dateRetourPrevueSort === true) {
                MediaService.filter('dateretourprevue','asc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                } else {
                	MediaService.filter('dateretourprevue','desc').$promise.then(function(result){
                		$scope.dataMedia=result.content;
                	})
                }
            }


        }
    ]);