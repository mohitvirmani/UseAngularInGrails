//Creates reference of useAngular module
var homePage = angular.module('useAngular');
// homePageData controller defined below
	homePage.controller('homePageData', [ '$scope', '$http', '$location',
	                              		'newsServices','$modal','$routeParams','$route',//this must be in serail order
	                              		function($scope, $http, $location,newsServices,$modal,$routeParams,$route) {

			// new function 'getAllNews()' created in the homePageData controller
			$scope.userList = '';
			$scope.news = '';
			$scope.viewnewsdata='';
			//New function created in homePageData controller, named getAllNews
			$scope.getAllNews = function() {
				//Calling the function getAllNews from the newsServices, which returns 
					//data on success, which is saved in scope
				newsServices.getAllNews().success(function(data) {
					$scope.news = data.news
				})
			}
			
			//Called on enter to show the news on home page
			$scope.getAllNews();

			// new function created, to add new news to the database
			$scope.submitNews = function() {
				var res = uploadNewsRecord();
				$location.path("/");
			}
			
			$scope.editNews = function(id) {
				var editNewsModal = $modal.open({
					templateUrl : 'editNewsTemplate.html',
					controller : 'editNewsCtrl',
					scope : $scope,
					resolve : {
						id : function() {
							return id;
						}
					}
				});
			};
			
			$scope.deleteNews = function(id){
				newsServices.deleteNews(id).success(function(data) {
					$scope.removeNewsFromTable(id)
					console.log(data.message)
				});
			};
			
			// removeRecipeFromTable() : Hide the deleted row from table and also remove the entry from DB
			$scope.removeNewsFromTable = function(id) {
				for (i = 0; i < $scope.news.length; i++) {
					if ($scope.news[i].id == id) {
						console.log("iterate " + i)
						$scope.news.splice($scope.news.indexOf($scope.news[i]), 1);
						break;
					}
				}
			};
			
			$scope.getNewsFromNewsList = function(id) {
				for (i = 0; i < $scope.news.length; i++) {
					if ($scope.news[i].id == id) {
						return $scope.news[i];
					}
				}
			};
			
			$scope.moreInfo = function(id){
				newsServices.moreInfo(id).success(function(data) {
					$scope.n=data.currentlySelectedNews
				});
			}
			$scope.moreInfo($routeParams.id)
			
			$scope.updateDIVWithCurrentNews = function(currentlySelectedNews){
				//Check how to update the current DIV with the news
			}
			
			$scope.updateDivToShowContact = function(){
				$.ajax({
					url : 'home/contactDetails',
					type: 'GET',
					success : function(response){
						$('.wrapper').html(response);
					}
				})
			}
			
	}]);

// gets form data, and saves to database
function uploadNewsRecord() {
	//Get form data
	var formdata = new FormData(document.forms.namedItem("uploadNewsForm"));

	//AJAX call to save the form data in DB
	//data posted to the below URL which has been mapped to an action in home controller
	//The action receives the JSON in params and parses those to save in DB.
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



// New Controller created : Recipes controller
homePage.controller('RecipiesPageData', [
		'$scope',
		'$http',
		'$location',
		'recipiesServices',
		'$modal',
		function($scope, $http, $location, recipiesServices, $modal) {
			$scope.message = '';
			$scope.imageId = '';
			$scope.name = '';
			$scope.recipielist = '';
			$scope.ingredientslist = '';
			$scope.recipies = '';

			// getRecipeList() : To get list of recipes

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

			// saveData() : To save recipies
			$scope.saveData = function(uploadRecipiesForm) {
				$scope.message = uploadRecipiesRecord()
				console.log($scope.message)
				$location.path("/RecipieList");
			}

		// deleteRecipe() : delete recipies from list
			$scope.deleteRecipie = function(id) {
				recipiesServices.deleteRecipie(id).success(function(data) {
					$scope.removeRecipieFromTable(id)
					console.log(data.message)
				});
				}

			// removeRecipeFromTable() : remove tr that are deleted form DB in but not on client side
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

			// getIngredients() : of recipies when you click on viewIngredients Link
			$scope.getIngredients = function(id, name) {
				$http.get('getIngredientslist/' + id + '.json').success(
						function(data) {
							console.log("Ingredients list")
							$scope.ingredientslist = data.ingredients
							$scope.changeImage(id, name)
						});
			}

			$scope.getRecipielist();

			$scope.editRecipie = function(id) {
				console.log("In edit" + id)
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
					if ($scope.recipielist[i].id == id) {
						return $scope.recipielist[i];
					}
				}
			};
		} ]);

//jQuery used instead of Angular
//TODO : look into form submission issues in Angular



function uploadRecipiesRecord() {
	console.log('uploadrecipedRecord created');
	var formdata = new FormData(document.forms.namedItem("uploadRecipiesForm"));
	var status
	$.ajax({
		url : 'recipies/create.json',
		type : 'POST',// don't use get request
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

function editRecipiesRecord(id) {
	var formdata = new FormData(document.forms.namedItem("editRecipiesForm"));
	var status
	$.ajax({
		url : 'editRecipie/'+id+'.json',
		type : 'POST',// dont use get request
		data : formdata,
		processData : false,
		contentType : false,
		async : false,
		success : function(response) {
			console.log("success in " + response.message)
			status = response.status
		},
		error : function(response) {
			console.log("errors in " + response.message)
			status = response.status
		}
	});

	return status
}


var editRecipieCtrl = function($scope, $modalInstance, $location, $http,
		$timeout, id) {
	console.log('editRecipeCtrl started');
	$scope.recipie = $scope.getRecipieFromRecipies(id);
	$scope.getIngredients(id, $scope.recipie.name)
	$scope.saveEdit = function(editRecipiesForm) {
		var status=editRecipiesRecord(id);
		if(status == 'success'){
			$modalInstance.dismiss('cancel');
		}
	};
	$scope.deleteCancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

var editNewsCtrl = function($scope, $modalInstance, $location, $http,
		$timeout, id,$route) {
	console.log('editNewsCtrl started');
	$scope.currentlySelectedNews = $scope.getNewsFromNewsList(id);

	$scope.saveEditNewsForm = function(){
		console.log('saveEditNewsFormStarted');
		var status = saveEditFormNewsRecord();
		if(status == 'Success'){
			$route.reload();//refresh the page 
			$modalInstance.dismiss('cancel');
			
		}
	};
	
	$scope.deleteCancel = function() {
		$modalInstance.dismiss('Cancel');
	};
	
};

function saveEditFormNewsRecord() {
	console.log('saveEditFormNewsRecord started');
	var formData = new FormData(document.forms.namedItem("editNewsForm"));
	console.log("saveEditNewsForm " + formData);
	var status
	$.ajax({
		url : 'editNews/edit.json',
		type : 'POST',// don't use get request
		data : formData,
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
	console.log('status ' + status);
	return status
}