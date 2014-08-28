//creates a new module named useAngular, and app.js also provides details, of the views to be rendered and controllers
//to be used, for various URL's, ['helloApp.controllers','helloApp.services']
var app = angular.module('useAngular', ['ngRoute','ui.bootstrap']);

app.config([ '$routeProvider', '$httpProvider',
		function($routeProvider, $http) {
		$routeProvider.when('/', {
				templateUrl : 'view/homePage.html',
				controller : 'homePageData'
			}).when('/moreinfo/:id', {
				templateUrl : 'view/viewinfo.html',
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
			}).when('/editRecipie', {
				templateUrl : 'view/editRecipie.html',
				controller : 'RecipiesPageData'
			}).otherwise({
				redirectTo : '/'
			});
		} ]);

app.service('testService', function(){
    this.sayHello= function(text){
    	console.log("Sonu")
        return "Service says \"Hello " + text + "\"";
    };       
});
