angular.module('myApp')
    .factory('AdherentService', ['config', '$resource', function (config, $resource) {
        return {



            postAdherent: function (adherent) {
               return $resource(config.apiUrl + '/adherent').save(adherent);

            },
            getAdherents: function () {

                return $resource(config.apiUrl + '/adherent').query();
            },
            getAssignedAJour: function () {
                var AssignedAJour = this.getAdherents();
                //console.log(AssignedAJour[0].nom);

                return AssignedAJour;
            }, getAge: function (dateNaissance) {
                var aujourdhui = new Date();
                
                var age = aujourdhui.getFullYear() - dateNaissance.getFullYear();
                var m = aujourdhui.getMonth() - dateNaissance.getMonth();
                if (m < 0 || (m === 0 && aujourdhui.getDate() < dateNaissance.getDate())) {
                    age--;
                }
                return age;
            }, getDateFin:function (datePaiement){
                var annee=datePaiement.getFullYear()+1;
                var jour=datePaiement.getDate()+1;
                var mois=datePaiement.getMonth();
                

                
                return new Date(annee,mois,jour);
            }


        }
    }]);