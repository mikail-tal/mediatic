angular.module('myApp')


    .controller('AdherentRechercheCtrl', ['$scope','AdherentService', function ($scope,AdherentService) {

        $scope.hello="HELLO WORLD ADHRENT Recherche";
        $scope.adherentsTab=AdherentService.getAdherents();

    }]);