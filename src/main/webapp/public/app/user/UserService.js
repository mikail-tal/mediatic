'use strict';
angular.module('myApp')
    .service('UserService', [ 'config','$resource','$location', function (config, $resource,$location) {

        return{
            getUsers : function(){
                var resource= $resource(config.apiUrl+'/api/users/all');
                return resource.get();
            }
            ,
            getUserById: function(id){
                var resource=$resource(config.apiUrl+'/api/users/'+id);
                return resource.get();
            }
            ,
            PostUser:function(user){
                $resource(config.apiUrl+'/api/users').save(user).$promise.then(function (result)
                {
            
            $location.path('/userRecherche');
            
                });
            }
            



        }
        
        

                

    }])

