'use strict';
angular.module('myApp')


    .controller('AdherentRechercheCtrl', ['$scope', 'AdherentService', '$rootScope', '$location', function ($scope, AdherentService, $rootScope, $location) {

    	
    	$scope.search={}
    		$scope.search.id='';
    		$scope.search.nom='';
    		$scope.search.keyword='';
    		// INITIALISATION POUR LA FONCTIONNALITE
            var idSort = false, nomSort = false, prenomSort = false,
                dateNaissanceSort = false, AjourCotisationSort = false, nombreMediaSort = false;

    		var page=0;
    		var size=10;
    		var totalPages;
    		
    		$scope.infosPage={}
    		$scope.infosPage.page=0;
    		$scope.infosPage.size='10'
    		$scope.infosPage.totalPages=0;
    		$scope.infosPage.range=[1,2,3,4,5]
    		$scope.isFirst=true;
    		$scope.isLast=false;
    		
    		
    		 var getDatas=function(page,size){
    			AdherentService.pageChange(page,size).$promise.then(function (result) {
                	$scope.infosPage.totalPages=result.totalPages;
                	$scope.infosPage.page=result.number;
                	$scope.isLast=result.last;
                	$scope.isFirst=result.first;
                	 fillArray();
                	$scope.adherentsTab =result.content;
                		
                		console.log($scope.infosPage.range.length);
                		console.log(result)
                });
    		}
    		
            getDatas($scope.infosPage.page,$scope.infosPage.size);
    		
    		
    		
    		$scope.change=function(index){
    			getDatas(index,$scope.infosPage.size)
    		}
    		
    		
    		$scope.$watch('infosPage.size',function(){
    			
    		getDatas($scope.infosPage.page,$scope.infosPage.size);
    		                
    		            
    		        },true);
    		
    		
    		
    		
    		function fillArray(){
    			var tab=[]
    			
    			for(var i=0;i<$scope.infosPage.totalPages;i++){
           		 tab[i]=i+1;
           	 }
    			$scope.infosPage.range=tab;
    			
    		}
    		
    		
    		
    		
    		
    		
    		
    		
        
     

        		




       
        $scope.afficherDetails = function (index) {
         	var id=0;
            angular.forEach($scope.adherentsTab, function (value, key) {
           
            //	console.log(value)
                if (key === index) {
                   // $rootScope.adherent = value;
                	id=value.id
                }
            });
           // console.log(id)
            $scope.redirect(id);
        };
        $scope.redirect = function (id) {
        
            $location.path('/adherentDetail/'+id);


        }
        angular.forEach($scope.adherentsTab, function (value, key) {

        })
        
        $scope.searchBy=function(){
        	AdherentService.getAdrBy($scope.search.id,$scope.search.nom).$promise.then(function (result) {
        		console.log(result);
        		$scope.adherentsTab=result.content;
        	})
        }
        $scope.searchAll=function(){
        	AdherentService.search($scope.search.keyword).$promise.then(function(result){
        		console.log("abcs")
        		$scope.adherentsTab=result.content;
        	})
        
        }
        
        
        
        
        
        
            /* 
                LES FONCTIONS DE TRI A UTILISER DANS LE SERVICE POUR TRIER A PARTIR DE LA BASE DE DONNEES
            
            */
            // TRI PAR ID
        
        
        
        
        
        
        $scope.sortId = function () {
            idSort = (!idSort);
            //nomSort = (!nomSort);
            if (idSort === true) {
            	$scope.adherentsTab= AdherentService.filter('id','asc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            } else {
            	$scope.adherentsTab= AdherentService.filter('id','desc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            }

        };
            // TRI PAR NOM
        $scope.sortNom = function () {
            nomSort = (!nomSort);
            if (nomSort === true) {
            	$scope.adherentsTab= AdherentService.filter('nom','asc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            } else {
            	$scope.adherentsTab= AdherentService.filter('nom','desc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            }


        };
            // TRI PAR PRENOM
        $scope.sortPrenom = function () {
            prenomSort = (!prenomSort);
            if (prenomSort === true) {
            	$scope.adherentsTab= AdherentService.filter('prenom','asc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            } else {
            	$scope.adherentsTab= AdherentService.filter('prenom','desc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            }

        };
            //  TRI PAR DATE DE NAISSANCE
        $scope.sortDateNaissance = function () {
            dateNaissanceSort = (!dateNaissanceSort);
            if (dateNaissanceSort === true) {
            
            	$scope.adherentsTab= AdherentService.filter('datenaissance','asc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            } else {
            	$scope.adherentsTab= AdherentService.filter('datenaissance','desc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            }
        };
            //TRI PAR LES ADHERENT QUI SONT A JOUR DANS LEUR COTISATIONS OU PAS
        $scope.sortAjourCotisation = function () {
            AjourCotisationSort = (!AjourCotisationSort);
            if (AjourCotisationSort === true) {
            	$scope.adherentsTab= AdherentService.filter('ajour','asc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            } else {
            	$scope.adherentsTab= AdherentService.filter('ajour','desc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            }
        };

            // TRI PAR NOMBRE DE MEDIA
        $scope.sortNombreMedia = function () {
            nombreMediaSort = (!nombreMediaSort);
            if (nombreMediaSort === true) {
            	$scope.adherentsTab= AdherentService.filter('nbrMedia','asc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            } else {
            	$scope.adherentsTab= AdherentService.filter('nbrMedia','desc',$scope.infosPage.size).$promise.then(function(result){
            		$scope.adherentsTab=result.content;
            	})
            }
        }
    }]);