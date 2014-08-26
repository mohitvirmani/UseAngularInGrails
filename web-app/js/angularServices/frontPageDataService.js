//Creates reference of useAngular module
var homePage = angular.module('useAngular');

homePage.service('newsServices', function($http) {

	var newsListAPI = [];
	newsListAPI.getAllNews = function() {
		return $http({
			method : 'GET',
			url : 'home/getAllNews'
		});
	}
	return newsListAPI;
});

homePage.service('recipiesServices', function($http) {

	var recipieAPI = [];
	recipieAPI.getAllRecipies = function() {
		return $http({
			method : 'GET',
			url : 'recipies/recipieslist'
		});
	}

	recipieAPI.deleteRecipie = function(id) {
		console.log("Id of Recipie " + id)
		return $http({
			method : 'POST',
			url : 'recipies/deleteRecipie?id=' + id
		});
	}
	return recipieAPI;
});
