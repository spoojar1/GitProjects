package SuperKartWebServices.SuperKartWebServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

@Path("/loginservices")
public class LoginServices {
	
	final static Logger logger = Logger.getLogger(LoginServices.class);
	MemCached mcc=new MemCached();
	
	LoginDAO loginDAO;
	ProductDAO productDAO;
	ReviewDAO reviewDAO;
	CartDAO cartDAO;
	OrderDAO orderDAO;
	
	@Path("/clearcache")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public void clearCache(String username) {
		mcc.removeFromCache(username+"profile");
		mcc.removeFromCache(username+"orders");
	}
	
	@Path("/checkuservalidity")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response isValidUser(MultivaluedMap<String, String> formParam) {
		loginDAO = new LoginDAO();
		
		logger.info("Received request for user validation from Jersey Client Server");
		
		if(!formParam.getFirst("hashkey").equals("CHSUYG"))
			return Response.ok().entity(String.valueOf(404)).build();
		//System.out.println("Hello "+formParam.getFirst("username")+" "+formParam.getFirst("password"));
		int response = loginDAO.verifyPassword(formParam.getFirst("username"), formParam.getFirst("password"));
		
		
			
		//0: username wrong
		//1: password wrong
		//2: user valid
		//int response=loginDAO.updateLastLoginTime(formParam.getFirst("username"));

		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchprofiledetails")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchProfileDetails(MultivaluedMap<String, String> formParam) {
		loginDAO = new LoginDAO();
		String username=formParam.getFirst("username");
		String str=mcc.getFromCache(username+"profile");
		if(str==null){
			str = loginDAO.fetchProfileDetails(username);
			mcc.putInCache(username+"profile",str);
		}
		return Response.ok().entity(String.valueOf(str)).build();
	}
	
	@Path("/updateprofiledetails")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateProfileDetails(MultivaluedMap<String, String> formParam) {
		loginDAO = new LoginDAO();
		
		String username=formParam.getFirst("username");
		if(mcc.getFromCache(username+"profile")!=null){
			mcc.removeFromCache(username+"profile");
			System.out.println("Cache for "+username+"profile cleared");
		}
		Boolean str = loginDAO.updateProfileDetails(formParam);
		return Response.ok().entity(String.valueOf(str)).build();
	}
	
	@Path("/registeruser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response registerUser(MultivaluedMap<String, String> formParam) {
		loginDAO = new LoginDAO();
		
		//false = username exists 
		//true = new user created
		boolean str = loginDAO.registerUser(formParam);
		return Response.ok().entity(String.valueOf(str)).build();
	}
	
	@Path("/fetchproductsoncat")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchProductsOnCat(MultivaluedMap<String, String> formParam) {
		
		productDAO = new ProductDAO();
		String response="";
		System.out.println("checking search"+(formParam.getFirst("searchstring").equals("")));
		if(formParam.getFirst("searchstring")==null || formParam.getFirst("searchstring").equals(""))
			response = productDAO.fetchProductsOnCat(formParam.getFirst("productcat"),formParam.getFirst("limit"),formParam.getFirst("sortorder"));
		else
			response = productDAO.fetchSearchResults(formParam.getFirst("searchstring"),formParam.getFirst("sortorder"));
		
		System.out.println("Within fetchproductsoncat loginservices"+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchproductdetails")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchProductDetails(MultivaluedMap<String, String> formParam) {
		
		productDAO = new ProductDAO();
		String response = productDAO.fetchProductsOnCat(formParam.getFirst("productcat"),formParam.getFirst("limit"),formParam.getFirst("sortorder"));
		System.out.println("Within fetchproductdetails "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchproductbean")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchProductBean(MultivaluedMap<String, String> formParam) {
		
		productDAO = new ProductDAO();
		String response = productDAO.fetchProductBean(formParam.getFirst("productid"));
		System.out.println("Within fetchproductbean "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchproductpurchasestatus")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchProductPurchaseStatus(MultivaluedMap<String, String> formParam) {
		
		productDAO = new ProductDAO();
		String response = productDAO.fetchProductPurchaseStatus(formParam.getFirst("productid"),formParam.getFirst("username"));
		System.out.println("Within fetchproductpurchasestatus "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchnumberofreviews")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchNumberOfReviews(MultivaluedMap<String, String> formParam) {

		productDAO = new ProductDAO();
		String response = productDAO.fetchNumberOfReviews(formParam.getFirst("productid"));
		System.out.println("Within fetchnumberofreviews "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchaveragerating")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchAverageRating(MultivaluedMap<String, String> formParam) {
		
		productDAO = new ProductDAO();
		String response = productDAO.fetchAverageRating(formParam.getFirst("productid"));
		System.out.println("Within fetchaveragerating "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchproductreviews")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchProductReviews(MultivaluedMap<String, String> formParam) {
		
		productDAO = new ProductDAO();
		String response = productDAO.fetchProductReviews(formParam.getFirst("productid"));
		System.out.println("Within fetchproductreviews "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/insertreview")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertReview(MultivaluedMap<String, String> formParam) {
		
		reviewDAO = new ReviewDAO();
		Boolean response = reviewDAO.insertReview(formParam.getFirst("username"),formParam.getFirst("productid"),formParam.getFirst("starrating"),formParam.getFirst("reviewtext"));
		System.out.println("Within insertreview "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/addtocart")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addToCart(MultivaluedMap<String, String> formParam) {
		
		cartDAO = new CartDAO();
		Boolean response = cartDAO.addToCart(formParam.getFirst("quantity"),formParam.getFirst("productid"),formParam.getFirst("username"));
		System.out.println("Within addtocart "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/fetchcart")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchCart(MultivaluedMap<String, String> formParam) {
		
		if(!formParam.getFirst("hashkey").equals("CHSUYG"))
			return Response.ok().entity(String.valueOf(404)).build();
		
		cartDAO = new CartDAO();
		String response = cartDAO.fetchCart(formParam.getFirst("username"));
		System.out.println("Within fetchCart "+response);
		return Response.ok().entity(response).build();
	}
	
	@Path("/updatecart")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateCart(MultivaluedMap<String, String> formParam) {
		
		cartDAO = new CartDAO();
		Boolean response = cartDAO.updateCart(formParam.getFirst("username"),formParam.getFirst("productid"),formParam.getFirst("quantity"));
		System.out.println("Within updatecart "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/removeitemcart")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeItemCart(MultivaluedMap<String, String> formParam) {
		
		cartDAO = new CartDAO();
		Boolean response = cartDAO.removeItemCart(formParam.getFirst("username"),formParam.getFirst("productid"));
		System.out.println("Within removeItemCart "+response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/submitorder")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response submitOrder(MultivaluedMap<String, String> formParam) {
		
		orderDAO = new OrderDAO();
		
		String username=formParam.getFirst("username");
		if(mcc.getFromCache(username+"orders")!=null){
			mcc.removeFromCache(username+"orders");
			System.out.println("Cache for "+username+"orders cleared");
		}
		
		String response = orderDAO.submitOrder(formParam.getFirst("username"));
		System.out.println("Within submitorder "+response);
		return Response.ok().entity(response).build();
	}
	
	@Path("/fetchprevorders")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response fetchPrevOrders(MultivaluedMap<String, String> formParam) {
		
		orderDAO = new OrderDAO();
		
		String username=formParam.getFirst("username");
		String str=mcc.getFromCache(username+"orders");
		if(str==null){
			str=orderDAO.fetchPrevOrders(formParam.getFirst("username"));
			mcc.putInCache(username+"orders",str);
		}
		System.out.println("Within fetchPrevOrders "+str);
		return Response.ok().entity(str).build();
	}
}
