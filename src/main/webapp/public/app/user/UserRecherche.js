'use strict';
angular.module('myApp')


    .controller('UserRechercheCtrl', ['$scope', 'UserService'
        , '$rootScope', '$location', '$filter',
        function ($scope, UserService, $rootScope, $location, $filter) {
        

            UserService.getUsers().$promise.then(function (result) {
                console.log(result)
                $scope.userTab = result.content;
                console.log($scope.userTab)
            });




            $scope.afficherDetails = function (index) {
                var id = 0;
                angular.forEach($scope.userTab, function (value, key) {

                    if (key === index) {
                        id = value.id
                    }
                });
                $scope.redirect(id);
            };
            $scope.redirect = function (id) {

                $location.path('/userDetails/' + id);


            }
        






    }]);
