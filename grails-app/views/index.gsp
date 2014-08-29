<!DOCTYPE html>
<html ng-app="useAngular">
<!-- This module directs the view to fetch the details from app.js, redirected to app.js -->
	<head>
<%--		<meta name='layout' content='mainLayout' />--%>
	<title>Use Angular In Grails</title>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,400italic,300italic,300,700,700italic|Open+Sans+Condensed:300,700' rel="stylesheet" type='text/css'>
	<asset:stylesheet href="application.css"/>
	<asset:javascript src="application.js"/>
	
<%--	<script type="text/javascript" src="assets/js-theme/jquery-1.7.2.min.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/ddsmoothmenu.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/retina.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/selectnav.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/jquery.masonry.min.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/jquery.fitvids.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/jquery.backstretch.min.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/mediaelement.min.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/mediaelementplayer.min.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/jquery.dcflickr.1.0.js"></script>--%>
<%--	<script type="text/javascript" src="assets/js-theme/twitter.min.js"></script>--%>

	<asset:script type="text/javascript">
		$.backstretch("assets/images/images-theme/bg/1.jpg");
	</asset:script>
	
	</head>
	<body>
		<div ng-view></div>
	</body>
</html>
