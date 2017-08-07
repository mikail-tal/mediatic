'use strict';
angular.module('myApp')
    .service('MediaService', [ 'config','$resource', function (config, $resource) {
        
        
        this.logConfig = function () {
            console.log(config.apiUrl);
        }
       
        this.getMediaViaResource = function () {
            return $resource(config.apiUrl).query();
        }

    }])