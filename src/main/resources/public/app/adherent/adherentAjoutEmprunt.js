'use strict';
angular.module('myApp')


    .controller('AdherentAjoutEmpruntCtrl', ['$scope', '$location', 'AdherentService', '$rootScope', '$filter', function ($scope, $location, AdherentService, $rootScope, $filter) {
        
        var idMedia = -1; // INITIALISATION AVEC UN NBR NEGATIF SI LE MEDIA N EXISTE PAS
        var typeMedia;  
        

        /* 
            Recuperation des medias pour l autocompletion du champ 
            Capture des evenement clavier sur le champ pour la recherche
        */
        AdherentService.getMedias().$promise.then(function (result) {
            $scope.medias = result;
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
                    if (value.title.toLowerCase().search($scope.recherche.toLowerCase()) > -1 && count < 3) {
                        $scope.rechercheMedia[index] = value;
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
                    if (value.title === $scope.recherche) {
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
                adherent: $rootScope.adherent.id,
                dateE: $scope.dateE,
                dateR: AdherentService.getDateRetourPrevue($scope.dateE, typeMedia),
                dateEf: null
            }
            AdherentService.postEmprunt(emprunt).$promise.then(function (result) {

                $scope.redirect();
            })

        }
        $scope.redirect = function () {
            $location.path('/adherentRecherche');
        }

    }]);