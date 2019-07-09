var app = angular.module('app', []);

app.controller('personController', ['$scope', '$http', 'window', function($scope, $http, $window) {


    $scope.originalPerson= {
        name:'',
        username:'',
        password:'',

    };
    $scope.person = angular.copy($scope.originalPerson);

    $scope.submitPersonForm = function () {

        $http({
            method: 'POST', url: '/api/persons/create', data: $scope.person,
            headers: {'Content-Type': 'application/json'}
        }).then(successCallback, errorCallback);
        };

        function successCallback(response) {
            $scope.person = angular.copy($scope.originalPerson);
            alert('Person registered successfully.');
            $window.location('/');
        }
        function errorCallback(error) {
            alert('Error occurred');
        }

    $scope.resetForm = function () {
        $scope.person = angular.copy($scope.originalPerson);
    }
}]);