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
					<li><a href="#categories">Categories</a>
						<ul>
							<li><a href="#science">Science</a></li>
							<li><a href="#environment">Environment</a></li>
							<li><a href="#history">History</a></li>
							<li><a href="#technology">Technology</a></li>
							<li><a href="#transport">Transport</a></li>
							<li><a href="#space">Space</a></li>
						</ul>
					</li>
					<li><a href="#popular">Popular</a></li>
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