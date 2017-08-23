'use strict';
angular.module('myApp')


    .controller('AdherentRechercheCtrl', ['$scope', 'AdherentService', '$rootScope', '$location', function ($scope, AdherentService, $rootScope, $location) {

        // INITIALISATION POUR LA FONCTIONNALITE
        var idSort = false, nomSort = false, prenomSort = false,
            dateNaissanceSort = false, AjourCotisationSort = false, nombreMediaSort = false;

        /* 
        Recuperation des adherent dans le serveur
    */
        
        //var emprunts = AdherentService.getEmprunts();


        /* 
            Completer A jour sur les cotisations 
            Completer les nombre de medias empruntes
        */
        AdherentService.getAdherents().$promise.then(function (result) {
      /*      result.forEach(function (element) {
                if (new Date(element.datef) < new Date()) {
                    element.ajour = "NON";
                } else {
                    element.ajour = "OUI";
                }
                var tabMedia = []
                
                AdherentService.getNombreMedia(element.id).$promise.then(function (result2) {
                    result2.forEach(function (element2) {
                        if (tabMedia.includes(element2.media) === false) {
                            tabMedia.push(element2.media);
                        }
                    })
                    element.nbrMedias = tabMedia.length;
                });
            })*/
        	
        		$scope.adherentsTab =result;
        		console.log(result);
        		
        		/*angular.forEach($scope.adherentsTab,function(value,key){
        			//console.log(key + "  " + value.dateNaissaince);
        			console.log(value.dateNaissaince);
        			//	console.log(value.dateNaissaince);
        				//.log(key)
        			
        				//$scope.adherentsTab[key].dateNaissaince=new Date(value.dateNaissaince.year,value.dateNaissaince.monthValue,value.dateNaissaince.dayOfMonth);
        				
        			
        		});*/
        		//$scope.adherentsTab.daten=new Date(dateNaissaince[0],dateNaissaince[1],dateNaissaince[2]);
          //  $scope.adherentsTab.datep=new Date(dateFinAbonnement[0],dateFinAbonnement[1],dateFinAbonnement[2]);
           // console.log(result);
        });

        //@INFOS A UTILISER SI BESOIN MEME FONCTION QUE LA PRECEDENTE POUR RECUPERER LE NOMBRE DE MEDIA
        /* emprunts.$promise.then(function (emps) {
            $scope.adherentsTab.$promise.then(function (adrs) {
                console.log(emps);
                console.log(adrs);
                adrs.forEach(function (element) {
                    element.nbrMedias = getLenght(emps, element.id);
                    console.log(element.nbrMedias);
                })


            })
        }) */


        //@INFOS FONCTION POUR RECUPERER LE NOMBRE LA LARGEUR D UN TABLEAU D EMPRUNT AVEC UN ID ADHERENT
       /*  function getLenght(emprunt, id) {
            var len;
            var nouveauTab = [];
            emprunt.forEach(function (element) {
                if (element.adherent === id) {
                    if (nouveauTab.includes(element.media) === false) {
                        nouveauTab.push(element.media);
                    }
                }

            });
            len = nouveauTab.length;
            return len;

        } */






        /* 
            Afficher les details de l adherent quand on click sur une ligne du tableau
            Redirection vers la page des details de l adherent
        */

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
            /* 
                LES FONCTIONS DE TRI A UTILISER DANS LE SERVICE POUR TRIER A PARTIR DE LA BASE DE DONNEES
            
            */
            // TRI PAR ID
        $scope.sortId = function () {
            idSort = (!idSort);
            nomSort = (!nomSort);
            if (idSort === true) {
                console.log("ASC")
            } else {
                console.log("DESC")
            }

        };
            // TRI PAR NOM
        $scope.sortNom = function () {
            nomSort = (!nomSort);
            if (nomSort === true) {
                console.log("ASC")
            } else {
                console.log("DESC")
            }


        };
            // TRI PAR PRENOM
        $scope.sortPrenom = function () {
            prenomSort = (!prenomSort);
            if (prenomSort === true) {
                console.log("ASC")
            } else {
                console.log("DESC")
            }

        };
            //  TRI PAR DATE DE NAISSANCE
        $scope.sortDateNaissance = function () {
            dateNaissanceSort = (!dateNaissanceSort);
            if (dateNaissanceSort === true) {
                console.log("ASC")
            } else {
                console.log("DESC")
            }
        };
            //TRI PAR LES ADHERENT QUI SONT A JOUR DANS LEUR COTISATIONS OU PAS
        $scope.sortAjourCotisation = function () {
            AjourCotisationSort = (!AjourCotisationSort);
            if (AjourCotisationSort === true) {
                console.log("ASC")
            } else {
                console.log("DESC")
            }
        };

            // TRI PAR NOMBRE DE MEDIA
        $scope.sortNombreMedia = function () {
            nombreMediaSort = (!nombreMediaSort);
            if (nombreMediaSort === true) {
                console.log("ASC")
            } else {
                console.log("DESC")
            }
        }
    }]);