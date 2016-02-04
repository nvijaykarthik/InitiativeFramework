var initiativeApp = angular.module('initiativeApp', ['ngResource']);


initiativeApp.controller('menuController', function ($scope,$http) {
	
	$http.get("/menu/menu.groovy"). success(function(data, status, headers, config) {
		 $scope.menuList = data;
    }).
    error(function(data, status, headers, config) {
      console.log("Error Retrieving Menu");
    });
});