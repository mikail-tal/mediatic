'use strict';
angular.module('myApp')


    .controller('UserCtrl', ['$scope', 'UserService'
    , '$rootScope', '$location', '$filter', 
    function ($scope, UserService, $rootScope, $location, $filter) {
        $scope.invalide;
        $scope.user={}
        $scope.ajouterUser=function(){
            UserService.PostUser($scope.user);
        }
       
        $scope.$watch('user.confirmPassword', function () {
            if ($scope.user.confirmPassword != $scope.user.password) {
                $scope.invalide=true
            }else{
                $scope.invalide=false;
            }
        }, true);


            






    }]);
