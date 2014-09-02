<!DOCTYPE html>
<html>
<!-- This module directs the view to fetch the details from app.js, redirected to app.js -->
	<head>
		<meta name='layout' content='mainLayout' />
	</head>
	<body>
		<g:render template="home/header"/>
		<div ng-view></div>
		<g:render template="home/footer"/>
		<script>
			$(document).ready(function(){
				$.backstretch("assets/images-theme/bg/1.jpg");
			});
		</script>
		

	</body>
</html>

