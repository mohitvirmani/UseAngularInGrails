<!-- Begin Footer -->
<div class="footer-wrapper">
<div id="footer" class="four">
		<div id="first" class="widget-area">
			<div class="widget widget_archive">
				<h3 class="widget-title">Categories</h3>
				<ul>
					<li><a href="#science">Science</a></li>
					<li><a href="#environment">Environment</a></li>
					<li><a href="#history">History</a></li>
					<li><a href="#technology">Technology</a></li>
					<li><a href="#transport">Transport</a></li>
					<li><a href="#space">Space</a></li>
				</ul>
			</div>	
		</div><!-- #first .widget-area -->
	
		<div id="second" class="widget-area">
			<div id="twitter-2" class="widget widget_twitter">
					<h3 class="widget-title">Twitter</h3>
					
					<div id="twitter-wrapper">
						<div id="twitter"></div>
						<span class="username"><a href="http://twitter.com/elemisdesign">→ Follow @elemisdesign</a></span>
					</div>
			</div>
		</div><!-- #second .widget-area -->
	
		<div id="third" class="widget-area">
		<div id="example-widget-3" class="widget example">
			<h3 class="widget-title">Popular Posts</h3>
			<ul class="post-list">
			  	<li> 
			  		<div class="frame">
			  			<a href="#"><img src="assets/images-theme/art/s1.jpg" /></a>
			  		</div>
					<div class="meta">
					    <h6><a href="#">Charming Winter</a></h6>
					    <em>28th Sep 2012</em>
				    </div>
				</li>
				<li> 
			  		<div class="frame">
			  			<a href="#"><img src="assets/images-theme/art/s2.jpg" /></a>
			  		</div>
					<div class="meta">
					    <h6><a href="#">Trickling Stream</a></h6>
					    <em>5th Sep 2012</em>
				    </div>
				</li>
				<li> 
			  		<div class="frame">
			  			<a href="#"><img src="assets/images-theme/art/s3.jpg" /></a>
			  		</div>
					<div class="meta">
					    <h6><a href="#">Morning Glory</a></h6>
					    <em>26th Sep 2012</em>
				    </div>
				</li>
			</ul>
			
		</div>
		</div><!-- #third .widget-area -->
		
		<div id="fourth" class="widget-area">
		<div class="widget">
			<h3 class="widget-title">Latest posts</h3>
			<ul class="flickr-feed"></ul>
			
		</div>
		</div><!-- #fourth .widget-area -->
	</div>
</div>
<div class="site-generator-wrapper">
	<div class="site-generator">Copyright 2014. All rights reserved.</div>
</div>
<!-- End Footer --> 

<script>

$(document).ready(function($){
	$('.flickr-feed').dcFlickr({
		limit: 9, 
        q: { 
            id: '51789731@N07',
			lang: 'en-us',
			format: 'json',
			jsoncallback: '?'
        },
		onLoad: function(){
			$('.frame a').prepend('<span class="more"></span>');
			$('.frame').mouseenter(function(e) {

            $(this).children('a').children('span').fadeIn(300);
        }).mouseleave(function(e) {

            $(this).children('a').children('span').fadeOut(200);
        });
		}
	});

	jQuery(document).ready(function($) {	
		$('.quick-flickr-item').addClass("frame");
		$('.frame a').prepend('<span class="more"></span>');
	});

	jQuery(document).ready(function($) {
		$('.frame').mouseenter(function(e) {
			$(this).children('a').children('span').fadeIn(300);
		}).mouseleave(function(e) {
			$(this).children('a').children('span').fadeOut(200);
		});
	});	

});	
</script>