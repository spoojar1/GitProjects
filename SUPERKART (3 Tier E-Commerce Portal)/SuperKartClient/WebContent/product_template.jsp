<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="SuperKartClient.SuperKartClient.UsersBean"%>
<%@page import="SuperKartClient.SuperKartClient.ProductsBean"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>SuperKart</title>
 <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="https://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.min.css">
<link href='https://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css' rel='stylesheet' type='text/css'>
<script type="text/javascript">

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
				alert(localStorage.clickcount);
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
	
	
	<% 	ProductsBean productbean=(ProductsBean)request.getAttribute("productbean"); %>
	var alertmsg=document.getElementById("productimagedisplay");
	
	<% if (session != null && productbean != null){ %>
			var output="";
			
			var productid="<%=productbean.getProductId()%>";
			var productcat="<%=productbean.getProductCategory()%>";
			
			//document.getElementById("productidbuy").value=productid;
			document.getElementById("productidcart").value=productid;
			
			//Set Product Display Images
			switch(productcat){
				case "M":	productcat="mobiles";
							break;
				case "L":	productcat="laptops";
							break;
				case "T":	productcat="tablets";
							break;
				case "C":	productcat="cameras";
							break;
				default:	productcat="misc";
							break;
			}
			
			output='<img class="thumbnail" id="displaypic" src="images/'+productcat+'/'+productid+'_pd.jpg">';
			output+='<div class="row small-up-4">';
			
			for(var i=1;i<=4;i++){
				output+='<div class="column">';
				output+='<img class="thumbnail" id="thumbpics" src="images/'+productcat+'/'+productid+'_pd'+i+'.jpg">';
				output+='</div>';
			}
			
			output+='</div>';	
			document.getElementById("productimagedisplay").innerHTML=output;
			document.getElementById("productheading").innerHTML="<%=productbean.getProductName()%>";
			
			//Set Star Ratings
			var stars="<%=request.getAttribute("avgrating")%>";
			
			output="";
			for(var i=1;i<=stars;i++){
				output+='<img src="images/starfilled.png" />';
			}
			for(var i=5;i>stars;i--){
				output+='<img src="images/starempty.png" />';
			}
			
			if(stars == "null")
				document.getElementById("star").innerHTML='<p>Unrated</p>';
			else
				document.getElementById("star").innerHTML=output;
				
			var numberofreviews="<%=request.getAttribute("numberofreviews")%>";
			document.getElementById("numberofreviews").innerHTML=numberofreviews + ' <a href="#panel1">Reviews</a>';
			
			var price="<%=productbean.getPrice()%>";
			document.getElementById("productprice").innerHTML='$'+price;
			
			var str="<%=(String)request.getAttribute("productreviews")%>";
			var xml = $.parseXML(str);
			var xmlDoc = xml.getElementsByTagName("Reviews");
			var output='<h4>Reviews</h4>';
			
			for (var i=0; i<xmlDoc.length; i++){
			    output+='<div class="media-object stack-for-small">';
			    output+='<div class="media-object-section"></div>';
			    output+='<div class="media-object-section" >';
			    output+='<h5>'+xmlDoc[i].getElementsByTagName('UserName')[0].firstChild.nodeValue+'</h5>';
			    output+='<p>'+xmlDoc[i].getElementsByTagName('ReviewText')[0].firstChild.nodeValue+'</p>';
			    output+='</div></div>';
			}
			var str="<%=request.getAttribute("purchasestatus")%>";
			
			if(str>0){
				output+='<form action="ReviewControllerServlet" method="post">';
				output+='My Review';
				output+='<textarea name="reviewtext" placeholder="None"></textarea>';
				//output+='<button class="button">Submit Review</button>';
				var productid="<%=productbean.getProductId()%>";
				output+='<input type="hidden" name="productid" value="'+productid+'"/>';
				output+='Star Rating';
				output+='<input type="number" name="starrating" value="1" min="1" max="5">';
				output+='<input type="submit" name="submitreview" class="button" value="Submit Review"/></form>';
			}else{
				$('#writereviewlink').hide();
			}
			
			document.getElementById("panel1").innerHTML=output;
			
			var specs="<%=productbean.getSpecifications()%>";
			//alert(specs);
			var xml = $.parseXML(specs);
			var xmlDoc = xml.documentElement.childNodes;
			var output='<ol>';
			
			for (var i=0; i<xmlDoc.length; i++){
				output+='<li>'+xmlDoc[i].childNodes[0].nodeValue+'</li>';
			}
			output+='</ol>';
			document.getElementById("description").innerHTML=output;
			
	<% } %>	
	
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
	
	document.getElementById("quantitycart").value=1;
	//document.getElementById("quantitybuy").value=1;
	
	$("#quantity").bind('keyup mouseup', function () {
		var current = $("#quantity").val();
		//alert("Hello"+current);
		document.getElementById("quantitycart").value=current;
		//document.getElementById("quantitybuy").value=current;
	});
});
</script>

<style>
#star {
    line-height:10px;
}

#star img {
    width: 20px; 
	height: 20px;
}
#displaypic{
	width: 600px; 
	height: 300px;
}
#thumbpics{
	width: 200px; 
	height: 150px;
}
#description{
	margin-left:1rem;
}
#description li{
	list-style-type:disc;
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
	height:100%;
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
	line-height:0.2rem;
}
hr{
	padding: 0px;  
	margin-top:0.5rem;
	margin-bottom:0.5rem;
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
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="M" />
					<input style="width:70px;" type="submit" id="fetchproduct" value="Mobiles"/>
				</form>
			</li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="L" />
					<input style="width:70px;" type="submit" id="fetchproduct" value="Laptops"/>
				</form>
			</li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="C" />
					<input style="width:70px;" type="submit" id="fetchproduct" value="Cameras"/>
				</form>
			</li>
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="hidden" id="hiddenusername" name="username" value="" />
					<input type="hidden" name="limit" value="" />
					<input type="hidden" name="productcat" value="T" />
					<input type="submit" id="fetchproduct" value="Tablets"/>
				</form>
			</li>
		</ul>
	</div>
	<div class="top-bar-right">
		<ul class="menu">
			<li>
				<form action="ProductControllerServlet" method="post">
					<input type="search" name="searchstring" style="display:inline; height:30px; width:300px; margin-right:0;" placeholder="Search">
					<input type="submit" class="button" style="width:100px; height:30px;line-height:0.3rem; margin-right:0;" value="Search"/>
				</form>
			</li>
			<% String uname = (String)session.getAttribute("USER");
				if (uname==null){ %>
					<li><a id="login" href="#dialog1">Login</a></li>
					<li><a id="register" href="#dialog2">Register</a></li>
				<%}else{%>
					<li><a href="prevorders.jsp">My Orders</a></li>
					<li><a href="cart.jsp" id="cart-image" style="padding-top:0px;padding-bottom:0px;"><img src="images/cart.png" style="width:35px;height:25px;"></img></a></li>
					<li><form action="ProfileDetailsControllerServlet" method="post"><input type="submit" id="profilefetch" value="Profile"/></form></li>
					<li><a id="logout">Logout</a></li>
			<%	}
			%>
		</ul>
	</div>
</div>
<br>
<div class="row">
	<div class="medium-6 columns" id="productimagedisplay">
	</div>
	
	<div class="medium-6 large-5 columns">
		<h3 id="productheading"></h3>
		<div class="row">
			<div id="star" class="small-5 columns">
			</div>
			<div class="small-3 columns" id="numberofreviews">
			</div>
			<div class="small-4 columns" style="text-align:right;" id="writereviewlink">
				Write a <a href="#myreview">Review</a>
			</div>
		</div>
		</br>
		<div id="description">
		</div>
		<div class="row">
			<div class="small-3 columns">Quantity
				<input type="number" id="quantity" name="quantity" value="1" min="1" max="5">
			</div>
			<div class="small-3 columns" style="float:right;">Price
				<label style="font-size:1.5rem;" id="productprice"></label>
			</div>
		</div>
		
		<form action="CartControllerServlet" method="post">
			<input type="hidden" id="quantitycart" name="quantity" value="" />
			<input type="hidden" id="productidcart" name="productid" value="" />
		<%  uname = (String)session.getAttribute("USER");
			if (uname!=null){ %>
			<input type="submit" class="button expanded" value="Add To Cart"/>	
		<% } %>
		</form>
	</div>
</div>

<div class="column row">
	<hr>
	<ul class="tabs" data-tabs id="example-tabs">
		<li class="tabs-title is-active"><a href="#panel1" aria-selected="true">Reviews</a></li>
	</ul>
	
	<div class="tabs-content" data-tabs-content="example-tabs">
		<div class="tabs-panel is-active" id="panel1">
		</div>
	</div>
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
				<li><a href="#"><p style="font-weight:bold;">COMPANY NAME</p></a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="contactus.jsp">Contact Us</a></li>
				<li><a href="#">Careers</a></li>
			</ul>
		</div>
		<div class="large-3 columns">
			<ul class="menu vertical">
				<li><a href="#"><p style="font-weight:bold;">HELP</p></a></li>
				<li><a href="#">FAQ</a></li>
				<li><a href="#">Cancellation & Returns</a></li>
				<li><a href="#">Gift Cards</a></li>

			</ul>
		</div>
	</div>
</div>

<script src="https://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.js"></script>
<script>
      $(document).foundation();
</script>
<script type="text/javascript" src="https://intercom.zurb.com/scripts/zcom.js"></script>
</body>
</html>
