'use strict';
angular.module('myApp')
    .service('MediaService', [ 'config','$resource', function (config, $resource) {
        
        
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

    }])