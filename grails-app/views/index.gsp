<!DOCTYPE html>
<html ng-app="useAngular">
<!-- This module directs the view to fetch the details from app.js, redirected to app.js -->
	<head>
		<!-- TODO : grails upgrade, use asset pipeline, and also use layout, no loading of js using srcipt -->
		
		<asset:stylesheet href="css/bootstrap/bootstrap.css"/>
		<asset:stylesheet href="css/bootstrap/jasny-bootstrap.css"/>
		<asset:stylesheet href="bootstrap/jasny-bootstrap.min.css"/>
		<asset:stylesheet href="css/customStyle.css"/>
		<asset:stylesheet href="css/bootstrap/heroic-features.css"/>

		<asset:javascript src="js/angular/bootstrap.js"/>
		<asset:javascript src="js/angular/jquery-1.11.0.js"/>
		<asset:javascript src="js/angular/angular-resource.js"/>
		<asset:javascript src="js/angular/angular.js"/>
		<asset:javascript src="js/angular/angular-route.js"/>
		<asset:javascript src="js/angular/bootstrap.min.js"/>
		<asset:javascript src="js/bootstrap/jasny-bootstrap.js"/>
		<asset:javascript src="js/bootstrap/jasny-bootstrap.min.js"/>
		<asset:javascript src="js/angular/ui-bootstrap-tpls-0.9.0.js"/>
		<asset:javascript src="js/angular/ngAutocomplete.js"/>
		<asset:javascript src="js/angularAppFile/app.js"/>
		<asset:javascript src="js/angular/angular-resource.min.js"/>
		<asset:javascript src="js/angularControllers/frontPageDataController.js"/>
		<asset:javascript src="js/angularServices/frontPageDataService.js"/>
		
		<title>Use Angular In Grails</title>
	</head>
	<body>
		<div ng-view>
		</div>
	</body>
</html>
