angular.module('useAngular').factory('addRecipieService', [function ($http) {
	
	  var recipieSaveMeassge = [];
	recipieSaveMeassge.saveRecipie = function() {
		console.log("comes in service")
	      return $http({
	        method: 'GET', 
	        url: '/recipies/create.json'
	      });
	    }
	    return recipieSaveMeassge;
}])