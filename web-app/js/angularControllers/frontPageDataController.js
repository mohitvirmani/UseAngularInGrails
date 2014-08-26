//Creates reference of useAngular module
var homePage = angular.module('useAngular');

// homePageData controller defined below
homePage.controller('homePageData', [ '$scope', '$http', '$location',
		'newsServices', function($scope, $http, $location, newsServices) {

			// new function 'getAllNews()' created in the homePageData
			// controller
			$scope.userList = '';
			$scope.news = '';
			$scope.getAllNews = function() {
				newsServices.getAllNews().success(function(data) {
					$scope.news = data.news
				})
			}
			$scope.getAllNews();

			// new function created, to add new news to the database
			$scope.submitNews = function() {
				var res = uploadNewsRecord();
				$location.path("/");
			}
		} ]);

// gets form data, and saves to database
function uploadNewsRecord() {
	var formdata = new FormData(document.forms.namedItem("uploadNewsForm"));

	$.ajax({
		url : 'uploadNews/record.json',
		type : 'POST',
		data : formdata,
		processData : false,
		contentType : false,
		async : false,
		success : function(response) {
			console.log("Success")
			return response
		},
		error : function(response) {
			console.log("Error")
			return response
		}
	});
}

// Recipeis controller
homePage.controller('RecipiesPageData', [
		'$scope',
		'$http',
		'$location',
		'recipiesServices',
		function($scope, $http, $location, recipiesServices) {
			$scope.message = '';
			$scope.imageId = '';
			$scope.name = '';
			$scope.recipielist = '';
			$scope.ingredientslist = '';
			$scope.recipies = '';

			// To get list of recipies
			$scope.getRecipielist = function() {
				recipiesServices.getAllRecipies().success(function(data) {
					$scope.recipielist = data.recipielist;
					$scope.ingredientslist = data.ingredient;
					if (data.recipielist.length != 0) {
						console.log(data.recipielist[0].id)
						$scope.imageId = data.recipielist[0].id
						$scope.name = data.recipielist[0].name;
					}
				});
			}

			// To save recipies
			$scope.saveData = function(uploadRecipiesForm) {
				$scope.message = uploadRecipiesRecord()
				console.log($scope.message)
				$location.path("/RecipieList");
			}

			// delete recipies from list
			$scope.deleteRecipie = function(id) {
				recipiesServices.deleteRecipie(id).success(function(data) {
					$scope.removeRecipieFromTable(id)
					console.log(data.message)
				});
				// $http.post('deleteRecipie/'+id+'.json').success(function(data)
				// {
				// $scope.message = data.message;
				// $scope.removeRecipieFromTable(id)
				// console.log(data.message)
				// });

			}

			// remove tr that are deleted form DB in but not on client side
			$scope.removeRecipieFromTable = function(id) {
				for (i = 0; i < $scope.recipielist.length; i++) {
					if ($scope.recipielist[i].id == id) {
						console.log("iterate " + i)
						$scope.recipielist.splice($scope.recipielist
								.indexOf($scope.recipielist[i]), 1);
						break;
					}
				}
			};

			// To change image according to recipies when you click on the
			// picture link in list page
			$scope.changeImage = function(id, name) {
				$scope.imageId = id;
				$scope.name = name;
			}

			// Get ingredients of recipies when you click on viewingredients
			// Link
			$scope.getIngredients = function(id, name) {
				$http.get('getIngredientslist/' + id + '.json').success(
						function(data) {
							console.log("Ingredients list")
							$scope.ingredientslist = data.ingredients
						});
			}

			$scope.getRecipielist();

			$scope.editRecipie = function(id) {
				console.log("In edit")
				var editModal = $modal.open({
					templateUrl : 'editTemplates.html',
					controller : 'editRecipieCtrl',
					scope : $scope,
					resolve : {
						id : function() {
							return id;
						}
					}
				});
			};

			$scope.getRecipieFromRecipies = function(id) {
				for (i = 0; i < $scope.recipielist.length; i++) {
					if ($scope.recipielist[i].id == prospectId) {
						return $scope.recipielist[i];
					}
				}
			};
		} ]);

function uploadRecipiesRecord() {
	var formdata = new FormData(document.forms.namedItem("uploadRecipiesForm"));
	var status
	$.ajax({
		url : 'recipies/create.json',
		type : 'POST',// dont use get request
		data : formdata,
		processData : false,
		contentType : false,
		async : false,
		success : function(response) {
			console.log("success in " + response.message)
			status = response.message
		},
		error : function(response) {
			console.log("errors in " + response.message)
			status = response.message
		}
	});

	return status
}

var editRecipieCtrl = function($scope, $modalInstance, $location, $http,
		$timeout, id) {
	var recipie = $scope.getRecipieFromRecipies(id);
	$scope.saveEdit = function() {
		$http({
			url : '',
			method : "POST"
		}).success(function(data) {
			$modalInstance.dismiss('cancel');
			if (data.result == "success") {
				$scope.removeProspectFromTable(id);
			}
		});
	};
	$scope.deleteCancel = function() {
		$modalInstance.dismiss('cancel');
	};
};