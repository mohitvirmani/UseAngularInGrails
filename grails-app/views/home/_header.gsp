<!-- Begin Header -->
<div class="header-wrapper opacity">
	<div class="header">
		<!-- Begin Logo -->
		<div class="logo">
		    <a href="index.html">
				<img src="assets/images-theme/logo.png" alt="" />
			</a>
	    </div>
		<!-- End Logo -->
		<!-- Begin Menu -->
		<div id="menu-wrapper">
			<div id="menu" class="menu">
				<ul id="tiny">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="page-with-sidebar.html">Categories</a>
						<ul>
							<li><a href="page-with-sidebar.html">Category1</a></li>
							<li><a href="page-with-sidebar.html">Category2</a></li>
							<li><a href="page-with-sidebar.html">Category3</a></li>
							<li><a href="page-with-sidebar.html">Category4</a></li>
							<li><a href="page-with-sidebar.html">Category5</a></li>
						</ul>
					</li>
					<li><a href="typography.html">Popular</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
		<!-- End Menu -->
	</div>
</div>
<!-- End Header -->

<script>
$(document).ready(function(){
	ddsmoothmenu.init({
    		mainmenuid: "menu",
    		orientation: 'h',
    		classname: 'menu',
    		contentsource: "markup"
    	});	
});
</script>