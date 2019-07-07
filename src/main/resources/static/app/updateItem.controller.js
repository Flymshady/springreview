var app = angular.module('app', []);

app.controller('itemUpdateController', ['$scope', '$http', '$window', function($scope, $http, $window) {

    var id= document.getElementById("itemId").textContent;
    getById(id);
    function getById(id) {
        var url="/items/detail/"+id;
        var itemDataPromise = $http.get(url);
        itemDataPromise.then(function (response) {
            $scope.originalItem=response;
            $scope.item = angular.copy($scope.originalItem);

            $scope.submitItemForm = function () {

                $http({
                    method:'PUT', url:'/items/update/'+id, data:$scope.item,
                    headers:{'Content-Type':'application/json'}
                }).then(successCallback, errorCallback);


                function successCallback(response){
                    $scope.item = angular.copy($scope.originalItem);
                    alert('Item updated successfully.');
                    $window.location.href='/';
                }
                function errorCallback(error){
                    alert('Error occurred');
                }
            };
            $scope.resetForm = function () {
                $scope.item = angular.copy($scope.originalItem);
            }


        });
    }




}]);
