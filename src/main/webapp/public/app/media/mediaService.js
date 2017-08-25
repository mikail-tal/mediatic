'use strict';
angular.module('myApp')
    .service('MediaService', [ 'config','$resource','$location', function (config, $resource,$location) {
        
        
        this.logConfig = function () {
           // console.log(config.apiUrl);
        }
       
        this.getMediaViaResource = function () {
            var resource= $resource(config.apiUrl+'/media');
            return resource.get();
        }
        this.postMedia = function(media){
            $resource(config.apiUrl+'/media').save(media).$promise.then(function (result)
                    {
            	
                $location.path('/mediaRecherche');
            	
                    });
        }
        this.getByPage=function(page,size){
        	var resource=$resource(config.apiUrl+'/media?page='+page+'&size='+size);
        	return resource.get();
        }
        this.getMediaBy=function(titre,auteur,type){
        	var resource=$resource(config.apiUrl+'/media?titre='+titre+'&auteur='+auteur+'&type='+type);
        	return resource.get();
        }
        
        this.search=function(keyword){
        	console.log('ABCD')
        	var resource=$resource(config.apiUrl+'/media/search?keyword='+keyword);
        	return resource.get();
        }
        this.filter=function(field,order){
        	var resource=$resource(config.apiUrl+'/media/filter?field='+field+'&order='+order);
        	return resource.get();
        }
        this.removeMedia= function(id){
            return $resource(config.apiUrl+'/media').remove(id);
        }
        
        this.getMedia=function(id){
        	
        	var Media=$resource(config.apiUrl + '/media/'+id);
        return 	Media.get();
        	
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

