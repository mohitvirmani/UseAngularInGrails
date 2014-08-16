//creates a new module named useAngular, and app.js also provides details, of the views to be rendered and controllers
//to be used, for various URL's
var app = angular.module('useAngular', ['ngRoute','ngResource']);

app.config([ '$routeProvider', '$httpProvider',
		function($routeProvider, $http) {
			$routeProvider.when('/', {
				templateUrl : 'view/homePage.html',
				controller : 'homePageData'
			}).when('/addNews', {
				templateUrl : 'view/addNews.html',
				controller : 'homePageData'
			}).when('/NewsList', {
				templateUrl : 'view/listNews.html',
				controller : 'homePageData'
			}).when('/RecipieList', {
				templateUrl : 'view/listRecipies.html',
				controller : 'RecipiesPageData'
			}).when('/newRecipie', {
				templateUrl : 'view/addRecipie.html',
				controller : 'RecipiesPageData'
			}).otherwise({
				redirectTo : '/'
			});
		} ]);