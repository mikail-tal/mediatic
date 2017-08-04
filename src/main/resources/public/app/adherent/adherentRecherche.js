angular.module('myApp')


    .controller('AdherentRechercheCtrl', ['$scope', 'AdherentService', '$rootScope', '$location', function ($scope, AdherentService, $rootScope, $location) {

        // $scope.hello="HELLO WORLD ADHRENT Recherche";
        $scope.adherentsTab = AdherentService.getAdherents();

        console.log($scope.adherentsTab);
        $scope.adherentsTab.$promise.then(function(result){
            
            result.forEach(function(element){
                if(new Date(element.datef)<new Date()){
                    element.ajour="NON";
                }else{
                    element.ajour="OUI";
                }
                
            })
        });

    

        
        

        $scope.$watch('adherentsTab',function(){
           // console.log('DATE')
           
                
            
        })



        $scope.afficherDetails = function (index) {
            angular.forEach($scope.adherentsTab, function (value, key) {
                if (key === index) {
                    $rootScope.adherent = value;
                }

            });

            $scope.redirect();


        };
        $scope.redirect = function () {
            $location.path('/adherentDetail');


        }

    }]);