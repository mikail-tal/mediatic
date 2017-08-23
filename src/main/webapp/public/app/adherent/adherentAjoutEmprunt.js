'use strict';
angular.module('myApp')


    .controller('AdherentAjoutEmpruntCtrl', ['$scope', '$location', 'AdherentService', '$rootScope', '$filter',"$routeParams", function ($scope, $location, AdherentService, $rootScope, $filter,$routeParams) {
        
        var idMedia = -1; // INITIALISATION AVEC UN NBR NEGATIF SI LE MEDIA N EXISTE PAS
        var typeMedia;  
        var idAdherent=$routeParams.id;
        

        /* 
            Recuperation des medias pour l autocompletion du champ 
            Capture des evenement clavier sur le champ pour la recherche
        */
        AdherentService.getMedias().$promise.then(function (result) {
            $scope.medias = result;
            console.log($scope.medias)
            $scope.$watch('recherche', function () {
                $scope.medias = rechercher();
                idMedia = rechercherTitreComplet();
            }, true);
        });
        /* 
            Recuperation de tous les medias pour faire une recherche globale
         */
        AdherentService.getMedias().$promise.then(function (result) {
            $scope.toutMedias = result;
            
        })
        /* 
            La recherche des medias pour afficher 2 suggestions sur les resultats
            Affecter la nouvelle liste de choix d autocompletion
        */
        function rechercher() {
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

        /* 
            Invalider le formulair si le media n existe pas pour l emprunt
            Enregistrer l identifiant et le type du media si trouvee 
        */
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
        }

        /* 
            Emprunter un Media avec calcul de la date retour prevue
        */
        $scope.emprunter = function () {

            var emprunt = {
                media: idMedia,
                adherent: parseInt(idAdherent,10),
                dateEmprunt: $scope.dateE,
                dateRetourPrevue: AdherentService.getDateRetourPrevue($scope.dateE, typeMedia),
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