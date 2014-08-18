//Creates reference of useAngular module
var homePage= angular.module('useAngular');

//homePageData controller defined below
homePage.controller('homePageData',['$scope','$http','$location',function($scope,$http,$location) {

//new function 'getAllNews()' created in the homePageData controller
$scope.getAllNews = function(){
	$http.get('getNewslist.json').success(function(data) {
        		$scope.news = data.news;
 });

}
$scope.getAllNews();

//new function created, to add new news to the database
$scope.submitNews = function(){
	var res=uploadNewsRecord();
	$location.path("/");
}



}]);

//gets form data, and saves to database
function uploadNewsRecord(){
	var formdata = new FormData(document.forms.namedItem("uploadNewsForm"));
	
	$.ajax({
		url : 'uploadNews/record.json',
		type:'POST',
		data : formdata,
		processData: false,  
		contentType: false ,
		async:false,
		success : function(response){
		console.log("Success")
			return response
		},
		error : function(response){
		console.log("Error")
			return response
		}
	});
}

homePage.controller('RecipiesPageData',['$scope','$http','$location',function($scope,$http,$location) {
	$scope.message = '';
	$scope.imageId = '';
	$scope.name = '';
	$scope.recipielist = '';
	$scope.ingredientslist = '';
	$scope.getRecipielist = function(){
		$http.get('getRecipielist.json').success(function(data) {
			        console.log("list")
	        		$scope.recipielist = data.recipielist;
			        $scope.ingredientslist = data.ingredient;
			        console.log(data.recipielist[0].id)
			        $scope.imageId = data.recipielist[0].id
			        $scope.name = data.recipielist[0].name;
	 });
	}
	
	$scope.getRecipielist();
	
	$scope.changeImage = function(id,name){
		$scope.imageId = id;
		$scope.name = name;
	}
	
	$scope.getIngredients = function (id,name){
		$http.get('getIngredientslist/'+id+'.json').success(function(data) {
		console.log("Ingredients list")
		$scope.ingredientslist = data.ingredients
		});
	}
	
	$scope.saveData = function(uploadRecipiesForm){
		$scope.message = uploadRecipiesRecord()
		console.log($scope.message)
		$location.path("/RecipieList");		
	    }
	$scope.recipies='';
	$scope.editRecipie = function(id){
		console.log("In edit")
		$http.post('editRecipie/'+id+'.json').success(function(data) {
			$scope.recipies = data.recipies
			$scope.ingredientslist = data.ingredients
			});
		$location.path("/editRecipie");
	}
	}]);

function uploadRecipiesRecord(){
	var formdata = new FormData(document.forms.namedItem("uploadRecipiesForm"));
	var status
	$.ajax({
		url : 'recipies/create.json',
		type:'POST',//dont use get request 
		data : formdata,
		processData: false,  
		contentType: false ,
		async:false,
		success : function(response){
		console.log("success in "+response.message)
		status= response.message
		},
		error : function(response){
		console.log("errors in "+response.message)
		status= response.message
		}
	});
	
	return status
}