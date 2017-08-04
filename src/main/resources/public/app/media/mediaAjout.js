'use strict';
angular.module('myApp.media', ['ngRoute'])
    .controller('MediaAjoutCtrl', ['$scope', '$resouce', '$config', '$q', function ($scope, $resouce, config, $q) {

        $scope.data1 = MediaService.getMediaViaResouce();
        // console.log($scope.data1)
        $scope.data1.$promise.then(function () {
            // console.log($scope.data1)
        });
        var defer = $q.defer();
        console.log(defer)
        defer.promise.then(function (val) {
            console.log(val)
        }, function (error) {
            console.log(error)
        })




    }])