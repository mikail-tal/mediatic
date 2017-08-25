'use strict';
angular.module('myApp')
    .factory('AdherentService', ['config', '$resource', '$location', function (config, $resource, $location) {
        return {
                // ENREGISTRE UN ADHERENT
            postAdherent: function (adherent) {
            		var annee = adherent.dateNaissance.getFullYear() ;
                var jour = adherent.dateNaissance.getDate() + 1;
                var mois = adherent.dateNaissance.getMonth();
                adherent.dateNaissance=new Date(annee,jour,mois)
                return $resource(config.apiUrl + '/adherent').save(adherent);

            },
                // RECUPERATION DES ADHERENTS
            getAdherents: function () {
                var resource= $resource(config.apiUrl + '/adherent/');
                return resource.get();
            },
            getAdr:function(id){
            	
            	var Adherent=$resource(config.apiUrl + '/adherent/'+id);
            return 	Adherent.get();
            	
            },
            getAdrBy:function(id,nom){
            	var resource=$resource(config.apiUrl+'/adherent?id='+id+'&nom='+nom);
            	return resource.get();
            },
            pageChange:function(page,size){
            	var resource=$resource(config.apiUrl+'/adherent?page='+page+'&size='+size);
            	return resource.get();
            }
            ,
            search:function(keyword){
            	var resource=$resource(config.apiUrl+'/adherent/search?keyword='+keyword);
            	return resource.get();
            },
            filter:function(field,order,size){
            	var resource=$resource(config.apiUrl+'/adherent/filter?field='+field+'&order='+order+'&size='+size);
            	return resource.get();
            }
            ,
                // RECUPERATION DES MEDIAS
            getMedias: function () {
                return $resource(config.apiUrl + '/media').query();
            },
            getNbrMedias:function(){
            	return $ressource(config.apiUrl+'/nbrMedia').query();
            }
            ,
                // MODIFICATION D UN ADHERENT EN UTILISANT LA METHODE PUT ET REDIRECTION VERS LA PAGE DE RECHERCHE
            updateAdherent: function (adherent) {
                var Adherent = $resource(config.apiUrl + '/adherent/:id/', { id: '@id' }, {
                    update: {
                        method: 'PUT'
                    }
                });
                Adherent.get({ 'id': adherent.id }).$promise.then(function (res) {
                    var adr = res;
                    adr = adherent;
                    Adherent.update(adr).$promise.then(function (result) {
                        $location.path('/adherentRecherche');
                    });

                }, function (errResponse) {
                    alert('ADHERENT INTROUVABLE');
                });
                /* 
                @WARNING A VOIR COMMENT CA MARCHE
                
                adr=adherent;
                adr.$save(); */
                /* var adr = Adherent.get({ id: adherent.id }, function () {
                    adr=adherent;
                    adr.$save();


                }); */


            }, 
                // @WARNING     A MODIFIER
            getAssignedAJour: function () {
                var AssignedAJour = this.getAdherents();
                return AssignedAJour;
            },
                // CALCUL DE L AGE A PARTIR DE LA DATE DE NAISSANCE
            getAge: function (dateNaissance) {
                var aujourdhui = new Date();

                var age = aujourdhui.getFullYear() - dateNaissance.getFullYear();
                var m = aujourdhui.getMonth() - dateNaissance.getMonth();
                if (m < 0 || (m === 0 && aujourdhui.getDate() < dateNaissance.getDate())) {
                    age--;
                }
                return age;
            },
                // CALCUL DE LA DATE DE FIN D ABONNEMENT AVEC 1AN ET 1 JOUR EN PLUS
            getDateFin: function (date) {
            var 	datePaiement=new Date(date);
                var annee = datePaiement.getFullYear() + 1;
                var jour = datePaiement.getDate() + 1;
                var mois = datePaiement.getMonth();
                return new Date(annee, mois, jour);
            },
                // CALCUL DE LA DATE DE RETOUR PREVUE D UN MEDIA 30 POUR LES LIVRE ET 15 POUR CD ET DVD
            getDateRetourPrevue:function(dateEmprunt,type){
                var jour;
                if(type ==='Livre'){
                    jour=dateEmprunt.getDate()+30;
                }else{
                    jour=dateEmprunt.getDate()+15;
                }
                var mois=dateEmprunt.getMonth();
                var annee=dateEmprunt.getFullYear();
                
                return new Date(annee,mois,jour);
            },
                // ENREGISTRER L EMPRUNT D UN ADHERENT
            postEmprunt: function (emprunt) {
                return $resource(config.apiUrl + '/emprunt').save(emprunt);

            },
                    //RECUPERER LES EMPRUNTS D UN ADHERENT 
            getEmpruntAdherent:function(id){
                
                var Emprunt = $resource(config.apiUrl + '/adherent='+id);
                return Emprunt.query();

            },
                 // RECUPER LES MEDIAS EMPRUNTES AVEC UN FILTRE D IDS
            getMediaEmpruntee:function(ids){
                var request='';
                angular.forEach(ids,function(value,key){
                    if(key===0){
                        request+=value;
                    }else{
                        request+='&&id='+value;
                    }          
                });
                return $resource(config.apiUrl+'/media?id='+request).query();
            },
            // RECUPERER LES IDS DES MEDIAS EMPRUNTES PAR UN ADHERENT
            getNombreMedia:function(id){
                 return $resource(config.apiUrl + '/emprunt?adherent='+id).query();    
            },
            // RECUPERER TOUS LES MEDIAS    
            getEmprunts:function(){
                return $resource(config.apiUrl+'/emprunt').query();
            }
            
            
        }
    }]);