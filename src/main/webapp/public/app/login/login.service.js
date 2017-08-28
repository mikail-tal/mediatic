(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('LoginService', LoginService)
        .run(function (LoginService, $rootScope) {
           // console.log('RUUUUUUUN')
            LoginService.addAuthorization();
            $rootScope.logout = LoginService.logout;

        });

    LoginService.$inject = ['$localStorage', '$rootScope', '$http'
    , '$location','$resource','config','$route'];

    
    
    
    function LoginService($localStorage, $rootScope, $http, $location,$resource,config,$route) {

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

                var User=$resource(config.apiUrl + '/api/users?login='+user.login);
                User.get().$promise.then(function (result) {
                    var custumUser={
                        "login":result.login,
                         "name":result.name,
                         "hasRole":result.credentials.includes('ADMIN')//JSON.stringify(result.credentials)
                    }
                    $rootScope.user=custumUser;
                    console.log($rootScope.user)
                  //  $rootScope.user.hasRole=$rootScope.user.credentials.includes('ADMIN');
                 //   console.log($rootScope.hasRole)
                    storeUser(custumUser);

                 
                    $location.path('/adherentRecherche');



                });


                

                    

            //console.log('success')
           // console.log(user)
            // $rootScope.login=user.login
             
       
        	},function errorCallBack(response){
                console.log('error');
                 
        	}
        	)
        	
        	
        	
        	
        }

        function storeUser(user) {
            $localStorage.$reset();
            $localStorage.$default(user);
            $rootScope.login = $localStorage.$default().login;
            $rootScope.name = $localStorage.$default().name;
            $rootScope.hasRole=$localStorage.$default().hasRole;
           // $rootScope.role=$
            addAuthorization();
        }

        function addAuthorization() {
            $rootScope.login = $localStorage.$default().login;
            $rootScope.name = $localStorage.$default().name;
            $rootScope.hasRole=$localStorage.$default().hasRole;
            //var password = $localStorage.$default().password;
            if ($rootScope.login) {
                var token = btoa($rootScope.login + ':' );
                $http.defaults.headers.common.Authorization = 'Basic ' + token
            }
        }

        function logout()
        {
            $localStorage.$reset();
            $rootScope.login = null;
            // $scope.reload = function() { // To Reload anypage
            //     $templateCache.removeAll();     
            //     $state.transitionTo($state.current, $stateParams, { reload: true, inherit: true, notify: true });
            // };
          //  $state.go("/login",null,{reload: true})
            //$rootScope.name=null;
           // $rootScope.hasRole=null;
            $location.path('/login');
           // $route.reload();

           

        }
    }
})();