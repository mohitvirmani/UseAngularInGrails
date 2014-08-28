<!DOCTYPE html>
<html ng-app="useAngular">
<!-- This module directs the view to fetch the details from app.js, redirected to app.js -->
	<head>
		<!-- TODO : grails upgrade, use asset pipeline, and also use layout, no loading of js using srcipt -->
	    <link rel="stylesheet" href="/UseAngularInGrails/css/bootstrap/bootstrap.css">
		<link rel="stylesheet" href="/UseAngularInGrails/css/bootstrap/jasny-bootstrap.css">
		<link rel="stylesheet" href="/UseAngularInGrails/css/bootstrap/jasny-bootstrap.min.css">
		<link rel="stylesheet" href="/UseAngularInGrails/css/customStyle.css">
		<link rel="stylesheet" href="/UseAngularInGrails/css/bootstrap/heroic-features.css">
		<title>Use Angular In Grail</title>
		<script src="/UseAngularInGrails/js/angular/bootstrap.js"></script>
		<script src="/UseAngularInGrails/js/angular/jquery-1.11.0.js"></script>
		<script src="/UseAngularInGrails/js/angular/angular-resource.js"></script>
		<script src="/UseAngularInGrails/js/angular/angular.js"></script>
		<script src="/UseAngularInGrails/js/angular/angular-route.js"></script>
		<script src="/UseAngularInGrails/js/angular/bootstrap.min.js"></script>
		<script src="/UseAngularInGrails/js/bootstrap/jasny-bootstrap.js"></script>
		<script src="/UseAngularInGrails/js/bootstrap/jasny-bootstrap.min.js"></script>
		<script src="/UseAngularInGrails/js/angular/ui-bootstrap-tpls-0.9.0.js"></script>
		<script src="/UseAngularInGrails/js/angular/ngAutocomplete.js"></script>
		<script src="/UseAngularInGrails/js/angularAppFile/app.js"></script>
		<script src="/UseAngularInGrails/js/angular/angular-resource.min.js"></script>
		<script src="/UseAngularInGrails/js/angularControllers/frontPageDataController.js"></script>
		<script src="/UseAngularInGrails/js/angularServices/frontPageDataService.js"></script>
	</head>
	<body>
		<div ng-view>
		</div>
	</body>
</html>
