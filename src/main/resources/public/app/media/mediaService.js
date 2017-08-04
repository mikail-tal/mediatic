'use strict';
angular.module('myApp.media')

    .service('MediaService', ['$resource', 'config', function ($resource, config) {

        this.compute = function () {

        },


            this.config = function () {
                console.log(apiUrl);
            }


        this.getMediaViaResouce = function () {
            return $resource(config.apiUrl).query();
        }





    }])