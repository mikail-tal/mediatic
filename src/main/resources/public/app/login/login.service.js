(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('LoginService', LoginService)
        .run(function (LoginService) {
            LoginService.addAuthorization();
        });

    LoginService.$inject = ['$localStorage', '$rootScope', '$http'];
    function LoginService($localStorage, $rootScope, $http) {

        var service = {
            storeUser: storeUser,
            addAuthorization: addAuthorization
        };

        return service;

        function storeUser(user) {
            $localStorage.$reset();
            $localStorage.$default(user);
            $rootScope.login = $localStorage.$default().login;
            addAuthorization();
        }

        function addAuthorization() {
            $rootScope.login = $localStorage.$default().login;
            var password = $localStorage.$default().password;
            if ($rootScope.login && password) {
                var token = btoa($rootScope.login + ':' + password);
                $http.defaults.headers.common.Authorization = 'Basic ' + token
            }
        }
    }
})();