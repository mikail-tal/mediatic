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
            logout: logout,
            login:login
        };

        return service;
        
        function login(user){
        	
        	
        	$http({
        		method: 'POST',
        		url:'/authenticate',
        		headers:{'Content-Type':'application/x-www-form-urlencoded'},
        		transformRequest: function(obj){
        			var str=[]
        			for(var p in obj)
        				str.push(encodeURIComponent(p)+'='+encodeURIComponent(obj[p]));
        			return str.join("&");
        		},
        		data:{username : user.login,password: user.password}
        		
        		
        		
        		
        	}).then(function successCallback(response){
        	console.log('success')	
        	},function errorCallBack(response){
        		console.log('error');
        	}
        	)
        	
        	
        	
        	
        }

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

           $rootScope.login = null;
        }
    }
})();