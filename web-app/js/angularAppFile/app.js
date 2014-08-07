var app = angular.module('useAngular', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function($routeProvider,$http) {

$routeProvider.when('/', {
	templateUrl : 'view/homePage.html',
	controller : 'homePageData'
  }).when('/addNews', {
	templateUrl : 'view/addNews.html',
	controller : 'homePageData'
  }).otherwise({
	redirectTo: '/'
  });
}]);