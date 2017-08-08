'use strict';
angular.module('myApp')
    .service('MediaService', [ 'config','$resource','$location', function (config, $resource,$location) {
        
        
        this.logConfig = function () {
            console.log(config.apiUrl);
        }
       
        this.getMediaViaResource = function () {
            return $resource(config.apiUrl+'/media').query();
        }
        this.postMedia = function(media){
           return $resource(config.apiUrl+'/media').save(media);
        }
        this.removeMedia= function(id){
            return $resource(config.apiUrl+'/media').remove(id)
        }
         this.updateMedia = function (media) {
                var Media = $resource(config.apiUrl + '/media/:id/', { id: '@id' }, {
                    update: {
                        method: 'PUT'
                    }
                });
                Media.get({ 'id': media.id }).$promise.then(function (res) {
                    var med = res;
                    med = media;
                    Media.update(med).$promise.then(function (result) {
                        $location.path('/mediaRecherche');
                    });

                }, function (errResponse) {
                    alert('Media INTROUVABLE');
                });}

                this.getDateRetourPrevue = function(dateEmprunt,type){
                var jour;
                if(type ==='Livre'){
                    jour=dateEmprunt.getDate()+30;
                }else{
                    jour=dateEmprunt.getDate()+15;
                }
                var mois=dateEmprunt.getMonth();
                var annee=dateEmprunt.getFullYear();
                
                return new Date(annee,mois,jour);
            };

                

    }])

