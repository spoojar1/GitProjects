<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="SuperKartClient.SuperKartClient.UsersBean"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>SuperKart</title>
<link rel="stylesheet" href="foundation.min.css">
<link rel="stylesheet" href="foundation_bck.css">
<link href='foundation-icons.css' rel='stylesheet' type='text/css'>
 
 <script src="jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#hiddenlogin').hide();
	$('#hiddenregister').hide();
	
	<%	if (request.getAttribute("valid")!=null){
			if(request.getAttribute("fail-attempts")!=null){%>
				if (localStorage.clickcount){
		            localStorage.clickcount = Number(localStorage.clickcount)+1;
		        } else {
		            localStorage.clickcount = 1;
		        }
				//alert(localStorage.clickcount);
			<%}%>	
			var id = $('#login').attr('href');
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();
			$('#mask').css({'width':maskWidth,'height':maskHeight});
			//$('#mask').fadeIn(1000);	
			$('#mask').fadeTo(0,0.5);	
			var winH = $(window).height();
			var winW = $(window).width();
			$(id).css('top',  winH/2-$(id).height()/2);
			$(id).css('left', winW/2-$(id).width()/2);
			$(id).fadeIn(0);
			$('#hiddenlogin').show();
	<%	}
	%>
	
	<%	if (request.getAttribute("taken")!=null){%>
			var id = $('#register').attr('href');
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();
			$('#mask').css({'width':maskWidth,'height':maskHeight});
			//$('#mask').fadeIn(1000);	
			$('#mask').fadeTo(0,0.5);	
			var winH = $(window).height();
			var winW = $(window).width();
			$(id).css('top',  winH/2-$(id).height()/2);
			$(id).css('left', winW/2-$(id).width()/2);
			$(id).fadeIn(0);
			$('#hiddenregister').show();
	<%	}
	%>
	
	//LOGIN
	$('#login').click(function(e) {
		e.preventDefault();
		var id = $(this).attr('href');
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		$('#mask').fadeIn(1000);	
		$('#mask').fadeTo("slow",0.5);	
		var winH = $(window).height();
		var winW = $(window).width();
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);
		$(id).fadeIn(2000);
	});
	
	//REGISTER
	$('#register').click(function(e) {
		e.preventDefault();
		var id = $(this).attr('href');
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		$('#mask').fadeIn(1000);	
		$('#mask').fadeTo("slow",0.5);	
		var winH = $(window).height();
		var winW = $(window).width();
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);
		$(id).fadeIn(2000);
	});
	
	//Common for LOGIN and REGISTER
	$('.window #close').click(function (e) {
		e.preventDefault();
		$('#mask').hide();
		$('.window').hide();
		$('#hiddenlogin').hide();
	});		
	$('#mask').click(function () {
		$(this).hide();
		$('.window').hide();
		$('#hiddenlogin').hide();
	});
	
	
	
	//AJAX
	$('#logout').click(function(event){
		$.get('LoginControllerServlet', function(responseText){
			if(responseText=="invalid"){
				window.location.href='index.jsp';
			}
		})
		.fail(function(){
			alert("AJAX Fail");
		});
		return false;
	});
	
	//AJAX
	/*$('#viewprofile').click(function(event){
		$.post('ProfileDetailsControllerServlet',{mode:"midviewprofile"}, function(responseText){
			alert(responseText);
			if(responseText=="midviewprofile"){
				window.location.href='profile.jsp';
			}
		})
		.fail(function(){
			alert("AJAX Fail");
		});
		return false;
	});*/
	
	$.ajax({
		type: "POST",
		url: "ProductControllerServlet",
		dataType: "xml",
		data: {"productcat": "M", "limit": "4" },
		success: function(xml){
			var xmlDoc = xml.getElementsByTagName("Products");
			var output="";
			for (var i=0; i<xmlDoc.length; i++){
			    output+='<div class="column">';
			    output+='<img class="thumbnail" src="images/mobiles/'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'.jpg">';
			    output+='<h5>'+xmlDoc[i].getElementsByTagName('ProductName')[0].firstChild.nodeValue+'</h5>';
			    output+='<p>$'+xmlDoc[i].getElementsByTagName('Price')[0].firstChild.nodeValue+'</p>';
			    var uname="<%=session.getAttribute("USER")%>";
				if (uname!=null)
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value="'+uname+'"/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
				else
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value=""/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
			}
			document.getElementById("productcatM").value='M';
			document.getElementById("firstdivrow").innerHTML=output
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
	});
	
	$.ajax({
		type: "POST",
		url: "ProductControllerServlet",
		dataType: "xml",
		data: {"productcat": "L", "limit": "4" },
		success: function(xml){
			var xmlDoc = xml.getElementsByTagName("Products");
			var output="";
			for (var i=0; i<xmlDoc.length; i++){
			    output+='<div class="column">';
			    output+='<img class="thumbnail" src="images/laptops/'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'.jpg">';
			    output+='<h5>'+xmlDoc[i].getElementsByTagName('ProductName')[0].firstChild.nodeValue+'</h5>';
			    output+='<p>$'+xmlDoc[i].getElementsByTagName('Price')[0].firstChild.nodeValue+'</p>';
			    var uname="<%=session.getAttribute("USER")%>";
				if (uname!=null)
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value="'+uname+'"/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
				else
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value=""/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
			}
			document.getElementById("productcatL").value='L';
			document.getElementById("seconddivrow").innerHTML=output
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
	});
	
	$.ajax({
		type: "POST",
		url: "ProductControllerServlet",
		dataType: "xml",
		data: {"productcat": "C", "limit": "4" },
		success: function(xml){
			var xmlDoc = xml.getElementsByTagName("Products");
			var output="";
			for (var i=0; i<xmlDoc.length; i++){
			    output+='<div class="column">';
			    output+='<img class="thumbnail" src="images/cameras/'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'.jpg">';
			    output+='<h5>'+xmlDoc[i].getElementsByTagName('ProductName')[0].firstChild.nodeValue+'</h5>';
			    output+='<p>$'+xmlDoc[i].getElementsByTagName('Price')[0].firstChild.nodeValue+'</p>';
			    var uname="<%=session.getAttribute("USER")%>";
				if (uname!=null)
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value="'+uname+'"/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
				else
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value=""/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
			}
			document.getElementById("productcatC").value='C';
			document.getElementById("thirddivrow").innerHTML=output
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
	});
	
	$.ajax({
		type: "POST",
		url: "ProductControllerServlet",
		dataType: "xml",
		data: {"productcat": "T", "limit": "4" },
		success: function(xml){
			var xmlDoc = xml.getElementsByTagName("Products");
			var output="";
			for (var i=0; i<xmlDoc.length; i++){
			    output+='<div class="column">';
			    output+='<img class="thumbnail" src="images/tablets/'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'.jpg">';
			    output+='<h5>'+xmlDoc[i].getElementsByTagName('ProductName')[0].firstChild.nodeValue+'</h5>';
			    output+='<p>$'+xmlDoc[i].getElementsByTagName('Price')[0].firstChild.nodeValue+'</p>';
			    var uname="<%=session.getAttribute("USER")%>";
				if (uname!=null)
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value="'+uname+'"/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
				else
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value=""/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
			}
			document.getElementById("productcatT").value='T';
			document.getElementById("fourthdivrow").innerHTML=output
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
	});
	
	$.ajax({
		type: "POST",
		url: "ProductControllerServlet",
		dataType: "xml",
		data: {"productcat": "MI", "limit": "6" },
		success: function(xml){
			var xmlDoc = xml.getElementsByTagName("Products");
			var output="";
			for (var i=0; i<xmlDoc.length; i++){
			    output+='<div class="column">';
			    output+='<img class="thumbnail" src="images/misc/'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'.jpg">';
			    output+='<div id="h5div"><h5>'+xmlDoc[i].getElementsByTagName('ProductName')[0].firstChild.nodeValue+'</h5></div>';
			    output+='<p>$'+xmlDoc[i].getElementsByTagName('Price')[0].firstChild.nodeValue+'</p>';
			    var uname="<%=session.getAttribute("USER")%>";
				if (uname!=null)
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value="'+uname+'"/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
				else
					output+='<form action="ProductControllerServlet" method="get"><input type="hidden" name="username" value=""/><input type="hidden" name="productid" value="'+xmlDoc[i].getElementsByTagName('ProductId')[0].firstChild.nodeValue+'"/><input type="submit" class="button expanded" value="Check This Out"/></form></div>';
			}
			document.getElementById("fifthdivrow").innerHTML=output;
		},
		error: function() {
			alert("An error occurred while processing XML file.");
		}
	});
	
});
				
</script>
<style>
#firstdivrow img,#seconddivrow img,#thirddivrow img,#fourthdivrow img{
	height: 400px;
	width: 300px;
}

#fifthdivrow img{
	height: 192px;
	width: 192px;
}

#firstdivrow,#seconddivrow,#thirddivrow,#fourthdivrow,#fifthdivrow h5{
	text-align:center;
}

#firstdivrow,#seconddivrow,#thirddivrow,#fourthdivrow,#fifthdivrow p{
	text-align:center;
}

#fifthdivrow #h5div{
	height: 60px;
}

.log-in-form {
	border: 1px solid #cacaca;
	padding: 1rem;
	border-radius: 3px; 
	background-color:#fff;
}

#mask 
{
  position:absolute;
  left:0;
  top:0;
  z-index:9000;
  background-color:#000;
  display:none;
}
  
#boxes .window 
{
  position:absolute;
  left:0;
  top:0;
  display:none;
  z-index:9999;
}

#boxes #dialog1,#dialog2
{
  width:400px; 
}

#close{
 float:right;
}

.row{
	padding-left: .9375rem;
	padding-right: .9375rem;
}
.row #category{
	display: block;
	width:85%;
	float: left;
	height:40px;
}
.row #viewitems{
	display: block;
	float: right;
	width:15%;
	height:40px;
}
#viewitems .button{
	float:right;
	height:80%;
	width:70%;
	line-height:0.2rem;
}
hr{
	padding: 0px;  
	margin-top:0.5rem;
	margin-bottom:0.5rem;
}

#hiddenlogin,#hiddenregister{
	 color: #ff0000;
	 font-size: 0.8rem;
}

#profilefetch, #fetchproduct{
  background:none;
  border:none;
  font-size:1em;
  color:blue;
  width:60px;
  text-align:center;
  color:#2199e8;
}
</style>

</head>
<body>

<div class="top-bar">
	<div class="top-bar-left">
		<ul class="menu">
			<li><a href="index.jsp" style="color:black;font-weight:bold;">SUPERKART</a></li>
			<li><a href="#firstDiv">Mobiles</a></li>
			<li><a href="#secondDiv">Laptops</a></li>
			<li><a href="#thirdDiv">Cameras</a></li>
			<li><a href="#fourthDiv">Tablets</a></li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="search" name="searchstring" style="display:inline; height:30px; width:300px; margin-right:0;" placeholder="Search">
					<input type="submit" class="button" style="width:100px; height:30px;line-height:0.3rem; margin-right:0;" value="Search"/>
				</form>
			</li>
		</ul>
	</div>
	<div class="top-bar-right">
		<ul class="menu">
			
			<% String uname = (String)session.getAttribute("USER");
			if (uname==null){ %>
					<li><a id="login" href="#dialog1">Login</a></li>
					<li><a id="register" href="#dialog2">Register</a></li>
			<%}else{%>
					<script>
						if(localStorage.clickcount!=undefined)
							alert("#Invalid Logins"+localStorage.clickcount);
						localStorage.clear();
					</script>
					<li><a href="prevorders.jsp">My Orders</a></li>
					<li><a href="cart.jsp" id="cart-image" style="padding-top:0px;padding-bottom:0px;"><img src="images/cart.png" style="width:35px;height:25px;"></img></a></li>
					<li><form action="ProfileDetailsControllerServlet" method="post"><input type="submit" id="profilefetch" value="Profile"/></form></li>
					<li><a id="logout">Logout</a></li>
			<%	}
			%>
		</ul>
	</div>
</div>

<div class="orbit" role="region" aria-label="Favorite Space Pictures" data-orbit>
	<ul class="orbit-container">
		<button class="orbit-previous" aria-label="previous"><span class="show-for-sr">Previous Slide</span>&#9664;</button>
		<button class="orbit-next" aria-label="next"><span class="show-for-sr">Next Slide</span>&#9654;</button>
		<li class="orbit-slide is-active">
			<a href="https://localhost:8443/SuperKartClient/ProductControllerServlet?username=&productid=P0014"><img style="height:500px; width:100%;" src="images/tablets/P0014_pd.jpg"></a>
		</li>
		<li class="orbit-slide">
			<a href="https://localhost:8443/SuperKartClient/ProductControllerServlet?username=&productid=P0004"><img style="height:500px; width:100%;" src="images/mobiles/P0004_pd.jpg"></a>
		</li>
		<li class="orbit-slide">
			<a href="https://localhost:8443/SuperKartClient/ProductControllerServlet?username=&productid=P0012"><img style="height:500px; width:100%;" src="images/cameras/P0012_pd.jpg"></a>
		</li>
	</ul>
</div>
<div class="row column text-center">
	<h2>Our Newest Products</h2>
	<hr>
</div>

<div class="row small-up-2 large-up-4" id="firstDiv">
	<div class="row">
		<div id="category">
			<h5 style="padding-left: .9375rem;">Mobiles</h5>
		</div>
		<div id="viewitems">
			<form action="ProductControllerServlet" method="post">
				<input type="hidden" id="hiddenusername" name="username" value="" />
				<input type="hidden" name="limit" value="" />
				<input type="hidden" id="productcatM" name="productcat" value="" />
				<input type="submit" id="fetchproduct" class="button" value="View More"/>
			</form>
		</div>
	</div>
	<div class="row" id="firstdivrow">
	</div>
</div>
<hr>
<div class="row small-up-2 large-up-4" id="secondDiv">
	<div class="row">
		<div id="category">
			<h5 style="padding-left: .9375rem;">Laptops</h5>
		</div>
		<div id="viewitems">
			<form action="ProductControllerServlet" method="post">
				<input type="hidden" id="hiddenusername" name="username" value="" />
				<input type="hidden" name="limit" value="" />
				<input type="hidden" id="productcatL" name="productcat" value="" />
				<input type="submit" id="fetchproduct" class="button" value="View More"/>
			</form>
		</div>
	</div>
	<div class="row" id="seconddivrow">
	</div>
</div>
<hr>
<div class="row small-up-2 large-up-4" id="thirdDiv">
	<div class="row">
		<div id="category">
			<h5 style="padding-left: .9375rem;">Cameras</h5>
		</div>
		<div id="viewitems">
			<form action="ProductControllerServlet" method="post">
				<input type="hidden" id="hiddenusername" name="username" value="" />
				<input type="hidden" name="limit" value="" />
				<input type="hidden" id="productcatC" name="productcat" value="" />
				<input type="submit" id="fetchproduct" class="button" value="View More"/>
			</form>
		</div>
	</div>
	<div class="row" id="thirddivrow">
	</div>
</div>
<hr>
<div class="row small-up-2 large-up-4" id="fourthDiv">
	<div class="row">
		<div id="category">
			<h5 style="padding-left: .9375rem;">Tablets</h5>
		</div>
		<div id="viewitems">
			<form action="ProductControllerServlet" method="post">
				<input type="hidden" id="hiddenusername" name="username" value="" />
				<input type="hidden" name="limit" value="" />
				<input type="hidden" id="productcatT" name="productcat" value="" />
				<input type="submit" id="fetchproduct" class="button" value="View More"/>
			</form>
		</div>
	</div>
	<div class="row" id="fourthdivrow">
	</div>
</div>
<div class="row column text-center" id="fifthDiv">
	<h3>Some Other Neat Products</h3>
	<hr style="margin-bottom:1rem;">
</div>
<div class="row small-up-2 medium-up-3 large-up-6" id="fifthdivrow">
</div>

<div id="boxes">
	<div id="dialog1" class="window">
		<div class="row">
			<div id="form">
				<form action="LoginControllerServlet" method="post">
					<div class="row log-in-form">
						<div id="close"><a>X</a></div>
						<h4 class="text-center">LOGIN</h4>
						<label>Username
						<input type="text" placeholder="ssp151830" name="username" value="UserName1" >
						</label>
						<label>Password
						<input type="password" placeholder="Password" name="password" value="Pass123" >
						</label>
						<p id="hiddenlogin">Invalid UserName/Password</p>
						<p><input type="submit" class="button expanded" value="Log In"></input></p>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="mask"></div>
</div>

<div id="boxes">
	<div id="dialog2" class="window">
		<div class="row">
			<div id="form">
				<form action="RegisterControllerServlet" method="post">
					<div class="row log-in-form">
						<div id="close"><a>X</a></div>
						<h4 class="text-center">CREATE ACCOUNT</h4>
						<label>Username
						<input type="text" placeholder="Enter a Unique username" value="UserName6" name="username">
						</label>
						<p id="hiddenregister">UserName already taken</p>
						<label>Email
						<input type="text" placeholder="john.doe@gmail.com" value="UserName6" name="emailid">
						</label>
						<label>Password
						<input type="password" placeholder="Password" value="UserName6" name="password">
						</label>
						<label>Verify Password
						<input type="password" placeholder="Password">
						</label>
						<p><input type="submit" class="button expanded" value="Create Account"></input></p>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="mask"></div>
</div>

<div class="callout secondary">
	<div class="row">
		<div class="large-4 columns">
			<h5>Your ONE STOP SHOP for all the Electronics you can think of !!</h5>
			<p>From mobiles, to laptops, to LCDs. You can find all of your household and workplace electronics here at COMPANYNAME. We provide the best customer service and truly believe that Customer is King.</p>
		</div>
		<div class="large-3 large-offset-2 columns">
			<ul class="menu vertical">
				<li>
					<a href="#">
						<p style="font-weight:bold;">COMPANY NAME</p>
					</a>
				</li>
				<li><a href="#">About Us</a></li>
				<li><a href="contactus.jsp">Contact Us</a></li>
				<li><a href="#">Careers</a></li>
			</ul>
		</div>
		<div class="large-3 columns">
			<ul class="menu vertical">
				<li>
					<a href="#">
						<p style="font-weight:bold;">HELP</p>
					</a>
				</li>
				<li><a href="#">FAQ</a></li>
				<li><a href="#">Cancellation & Returns</a></li>
				<li><a href="#">Gift Cards</a></li>
			</ul>
		</div>
	</div>
</div>

<script src="foundation.js"></script>
<script>
      $(document).foundation();
    </script>
<script type="text/javascript" src="https://intercom.zurb.com/scripts/zcom.js"></script>
</body>
</html>
