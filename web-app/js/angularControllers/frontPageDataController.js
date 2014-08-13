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

