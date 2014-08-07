var homePage= angular.module('useAngular');


homePage.controller('homePageData',['$scope','$http','$location',function($scope,$http,$location) {

$scope.getAllNews = function(){
 $http.get('getNewslist.json').success(function(data) {
        		$scope.news = data.news;
                console.log(data.news);
        	});
}
$scope.getAllNews();

$scope.submitNews = function(){

var res=uploadNewsRecord();
$location.path("/");
}

}]);

;
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


homePage.controller('showImage',['$scope','$http','$location',function($scope,$http,$location) {
$scope.getAllNews = function(){
 $http.get('getNewslist.json').success(function(data) {
        		$scope.news = data.news;
                console.log(data.news);
        	});
}
$scope.getAllNews();
}]);
