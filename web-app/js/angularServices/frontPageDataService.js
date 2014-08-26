//Creates reference of useAngular module
var homePage = angular.module('useAngular');

homePage.service('newsServices', function($http) {

	var newsListAPI = [];
	//New function created in newsServices, getAllNews()
	console.log('newsListAPI first  ' + newsListAPI);
	
	newsListAPI.getAllNews = function() {
		//call made to action:getAllNews, controller : home
		//HashMap returned by action, set to newsListAPI
		return $http({
			method : 'GET',
			url : 'home/getAllNews'
		});
	}
	console.log('newsListAPI second' + newsListAPI);
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
