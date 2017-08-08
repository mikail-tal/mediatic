(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('LoginService', LoginService)
        .run(function (LoginService, $rootScope) {
            LoginService.addAuthorization();
            $rootScope.logout = LoginService.logout;

        });

    LoginService.$inject = ['$localStorage', '$rootScope', '$http', '$location'];
    function LoginService($localStorage, $rootScope, $http, $location) {

        var service = {
            storeUser: storeUser,
            addAuthorization: addAuthorization,
            logout: logout
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

        function logout()
        {
            $localStorage.$reset();
            $location.path('/login');

         //   $rootScope.login = null;
        }
    }
})();