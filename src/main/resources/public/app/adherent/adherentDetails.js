'use strict';
angular.module('myApp')


    .controller('AdherentDetailsCtrl', ['$scope', '$rootScope', '$filter', 'AdherentService', '$location', function ($scope, $rootScope, $filter, AdherentService, $location) {
        //Desactivation de tous les champs de saisie et du bouton d enregistrement
        $scope.disabled = true;
        /* 
            Initialisation des champs de saisie avec les infos de l adherent choisi
            Redirection vers la page de recherche si l adherent n existe pas
            @WARNING A voir pour completer ou modifier
        */

        if ($rootScope.adherent) {
            $scope.adherent = $rootScope.adherent
            $scope.adherent.daten = new Date($rootScope.adherent.daten);
            $scope.adherent.datep = new Date($rootScope.adherent.datep);

        } else {
            $location.path('/adherentRecherche');
        }
        /* 
            Supprimer les doublons pour avoir un seul media si l adherent a emprunte le meme media plusieurs fois
        */

        function supprimerDoublons(tableau) {
            var nouveau = [], obj = {};
            angular.forEach(tableau, function (value, key) {
                obj[value.media] = 0;
            });
            angular.forEach(obj, function (value, key) {
                nouveau.push(parseInt(key));
            });
            return nouveau;
        }


        /* 
                Recuperer la liste des emprunts pour un adherent
                Supprimmer les doublons et garder seulement les medias
                Recuperer les medias empruntes avec leurs details
                S assurer qu on a un adherent
        
        */


        if ($rootScope.adherent) {
            AdherentService.getEmpruntAdherent($rootScope.adherent.id).$promise.then(function (result) {
                $scope.emprunts = result;
                var tabIds = supprimerDoublons($scope.emprunts);
                AdherentService.getMediaEmpruntee(tabIds).$promise.then(function (result) {
                    $scope.medias = result;
                })
            });
        }
        //@WARNING A SUPPRIMER OU A UTILISER POUR UNE AUTRE FONCTIONNALITE
        /* $scope.emprunts.$promise.then(function (result) {
           
           console.log('MEDIA');
           console.log($scope.emprunts)





           console.log('EMPRUNT')
           console.log($scope.emprunts);
           var tabIds=supprimerDoublons($scope.emprunts);
           console.log('LES IDS')
           console.log(tabIds);
           
           var request={id:tabIds};
           
           console.log('LA REQUETE')
           console.log(request)

               
               $scope.MED={} 



          
       }); */
        // var tabIds=supprimerDoublons($scope.emprunts);
        /* var MED=AdherentService.getMediaEmpruntee();
        MED.$promise.then(function(result){
             
             console.log(MED);
             angular.forEach(MED,function(value,key){
                 console.log(value);
                     console.log(key);
                 if(value.id===$scope.emprunts.media){
                     console.log('EGAL EGAL')
                     
                     
                     //$scope.media.push(value);
                 }
             })


         }) ;
     */













        /* 
            Creation d un nouvel adherent avec un filtre pour la date de naissance et la date de paiement
        */

        function nouveau() {
            var ad = {
                nom: $scope.nom,
                prenom: $scope.prenom,
                daten: $filter('date')($scope.daten, 'yyyy-MM-dd'),
                email: $scope.email,
                adress: $scope.adress,
                codeP: $scope.codeP,
                ville: $scope.ville,
                datep: $filter('date')($scope.datep, 'yyyy-MM-dd'),
                montant: $scope.montant
            }
            return ad;
        };
        /* 
            Iteration dans le nouveau et l ancien adherent pour enregistrer les changements
            Enregistrer les changement sur l ancien adherent (+ d attribut que le nouvel adherent)
            Verification que l adresse le codePostal la ville et le montant ne sont pas indefini
            Modification de l adherent sur le serveurJson
            
        */

        $scope.enregistrer = function () {




            /* var newAdherent = nouveau();
            console.log(newAdherent); */
            /*    angular.forEach(oldAdherent, function (value, index) {
                   angular.forEach(newAdherent, function (value2, index2) {
                       console.log(index + ' L INDEX DE L ADHERENT');
                       console.log(index2+' L index du nouveau');
                       if (index === index2) {
                           
                           if (value != value2) {
                               if (index === 'adress' || index === 'codeP' || index === 'ville') {
                                   
                                   if (value2 === undefined) {
                                       
                                       oldAdherent[index] = "";
                                   } else {
                                       console.log(value2);
                                       oldAdherent[index] = value2;
                                   }
                               } else if (index === 'montant') {
                                   if (value2 === undefined) {
                                       oldAdherent[index] = 0;
                                   } else {
                                       oldAdherent[index] = value2;
                                   }
                               } else {
                                   oldAdherent[index] = value2;
                               }
   
                           }
                       }
   
                   });
   
               }); */
            AdherentService.updateAdherent($scope.adherent);
        }

        /* 
            Redirection vers la page d ajout  d emprunt
        */
        $scope.ajouterEmprunt = function () {
            $location.path('/adherentAjoutEmprunt')
        }
        /* 
            Redirection vers la page de recherche d adherents @ WARNING A SUPPRIMMER SI PAS BESOIN
        */
        $scope.redirect = function () {

        }
        /* 
            Modification de l age de l adherent quand la date de naissance est modifiee
        */

        $scope.$watch('daten', function () {
            if ($scope.daten) {
                $rootScope.adherent.age = AdherentService.getAge($scope.daten);
            }
        }, true);
        /* 
            Modification de la date de fin d abonnement quand la date de paiement est modifiee
        */

        $scope.$watch('datep', function () {
            if ($scope.datep) {
                $rootScope.adherent.datef = AdherentService.getDateFin($scope.datep);
            }
        })


    }]);