'use strict';
angular.module('myApp')


    .controller('UserDetailsCtrl', ['$scope', 'UserService'
    , '$rootScope', '$location', '$filter','$routeParams',
    function ($scope, UserService, $rootScope, $location, $filter,$routeParams) {
        $scope.user={}
        $scope.disabled=true;


        UserService.getUserById($routeParams.id).$promise.then(function (result)
        {
            $scope.user=result;
            
    
        });
        $scope.enregistrerUser=function(){
            UserService.PostUser($scope.user);
        }


            






    }]);
