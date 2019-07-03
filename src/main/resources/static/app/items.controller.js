(function () {
    'use strict';
    angular
        .module('app', [])
        .controller('ItemsController', ItemsController);
    ItemsController.$inject=['$http'];

    function ItemsController($http) {
        var vm=this;

        vm.items=[];
        vm.getAll=getAll;
        vm.getGenreRock=getGenreRock;
        vm.getGenrePop=getGenrePop;
        vm.getGenreMetal=getGenreMetal;
        vm.getGenreClassical=getGenreClassical;
        vm.getGenreHipHop=getGenreHipHop;
        vm.deleteItem = deleteItem;
        vm.getById=getById;

        init();
        function init() {
           getAll();

        }

        function getAll() {
            var url = "/items/all";
            var itemsPromise = $http.get(url);
            itemsPromise.then(function (response) {
                vm.items=response.data;
            });
        }

        function getGenreRock() {
            var url = "/items/genre/"+"rock";
            var itemsPromise = $http.get(url);
            itemsPromise.then(function (response) {
                vm.items=response.data;
            });
        }
        function getGenreMetal() {
            var url = "/items/genre/"+"metal";
            var itemsPromise = $http.get(url);
            itemsPromise.then(function (response) {
                vm.items=response.data;
            });
        }
        function getGenrePop() {
            var url = "/items/genre/"+"pop";
            var itemsPromise = $http.get(url);
            itemsPromise.then(function (response) {
                vm.items=response.data;
            });
        }
        function getGenreClassical() {
            var url = "/items/genre/"+"classical";
            var itemsPromise = $http.get(url);
            itemsPromise.then(function (response) {
                vm.items=response.data;
            });
        }
        function getGenreHipHop() {
            var url = "/items/genre/"+"hiphop";
            var itemsPromise = $http.get(url);
            itemsPromise.then(function (response) {
                vm.items=response.data;
            });
        }

        function deleteItem(id) {
            var url="/items/remove/"+id;
            $http.post(url).then(function (response) {
               vm.items=response.data;
            });

        }

        function getById(id) {
            var url="/items/detail/"+id;
            var itemsPromise = $http.get(url);
            itemsPromise.then(function (response) {
                vm.items=response.data;
            });
        }

    }
})();