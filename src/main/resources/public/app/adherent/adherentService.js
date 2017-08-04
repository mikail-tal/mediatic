angular.module('myApp')
    .factory('AdherentService', ['config','$resource', function (config,$resource) {
        return {

           
            
            postAdherent: function (adherent) {
               $resource(config.apiUrl+'/adherent').save(adherent);
              
            },
            getAdherents:function(){
                return $resource(config.apiUrl+'/adherent').query();
            } 

        }
    }]);