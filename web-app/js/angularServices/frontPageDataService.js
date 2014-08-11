//TODO: to be used
angular.module('UseAngularInGrail.services', []).service('frontPageListservice', function($http) {

	  var userListAPI = [];
	  
	  userListAPI.getUsers = function() {
	      return $http({
	        method: 'GET', 
	        url: '/home/index'
	      });
	    }
	    return userListAPI;
  });