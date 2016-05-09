package SuperKartClient.SuperKartClient;

import java.io.IOException;
import java.io.PrintWriter;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class ProductControllerServlet
 */
@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 */
	public ProductControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Entered post in product controller sevlet");
		
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		
		String productcat = request.getParameter("productcat");
		String limit = request.getParameter("limit");
		String sortorder = request.getParameter("sortorder");
		String searchstring = request.getParameter("searchstring");
		
		writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		System.out.println("sortorder"+sortorder);
		String statusString="";
		//limit="" for full product listing
		
		System.out.println("searching "+searchstring+" limit "+limit);
		
		if(limit!=null && !limit.equals("")){
			try {
				//System.out.println("Hello from servlet");
				//Client client = Client.create();
				//WebResource webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchproductsoncat");
				
				ClientConfig config = new DefaultClientConfig(); // SSL configuration
		        // SSL configuration
		        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
		        Client client = Client.create(config);
		        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchproductsoncat");
		        
		        MultivaluedMap formData = new MultivaluedMapImpl();
				formData.add("productcat", productcat);
				formData.add("limit", limit);
				formData.add("sortorder", sortorder);
				formData.add("searchstring", searchstring);
				
				System.out.println("searching 1"+searchstring);
				//System.out.println("form data "+userName+" "+password+" Hello");
				ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
						.post(ClientResponse.class, formData);
				
				if (restResponse.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
				}
	
				statusString = restResponse.getEntity(String.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println("wihtin servlet "+statusString);
			writer.append(statusString);
		}else{
			try {
				//System.out.println("Hello from servlet");
				//Client client = Client.create();
				//WebResource webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchproductsoncat");
				
				ClientConfig config = new DefaultClientConfig(); // SSL configuration
		        // SSL configuration
		        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
		        Client client = Client.create(config);
		        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchproductsoncat");
		        
		        MultivaluedMap formData = new MultivaluedMapImpl();
				formData.add("productcat", productcat);
				formData.add("limit", limit);
				formData.add("sortorder", sortorder);
				formData.add("searchstring", searchstring);
				
				//System.out.println("searching 2"+searchstring);
				
				//System.out.println("form data "+userName+" "+password+" Hello");
				ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
						.post(ClientResponse.class, formData);
	
				if (restResponse.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
				}
				
				statusString = restResponse.getEntity(String.class);
				//statusString="<XMLRoot><Products><ProductId>P0012</ProductId><ProductName>Nextbook Flexx 9 </ProductName><Price>82.99</Price></Products><Products><ProductId>P0014</ProductId><ProductName>Fire, 7" Display</ProductName><Price>69.99</Price></Products><Products><ProductId>P0015</ProductId><ProductName>Samsung Galaxy Tab</ProductName><Price>130</Price></Products><Products><ProductId>P0016</ProductId><ProductName>Dragon Touch M8 </ProductName><Price>79.99</Price></Products></XMLRoot>";
				request.setAttribute("xmlDoc",statusString);
				request.setAttribute("productcat",productcat);
				request.setAttribute("searchstring",searchstring);
				
				RequestDispatcher rd = request.getRequestDispatcher("product_list.jsp");
				rd.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//to fetch individual product details
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Entered sevlet");
		response.setContentType("text/html");
		
		//PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("USER");
		String productid="";
		
		//call coming from webpage(getparameter) or after review addition(getattribute)
		if(request.getAttribute("productid") != null)
			productid=(String) request.getAttribute("productid");
		else
			productid=request.getParameter("productid");
		
		ProductsBean productbean = new ProductsBean();
		
		String statusString="";
		
		Client client;
		WebResource webResource;
		ClientConfig config;
		
		MultivaluedMap formData;
		ClientResponse restResponse;
		
		try {
			System.out.println("Hello from servlet");
			
			//FETCH PRODUCT BEAN
			
			System.out.println("FETCH PRODUCT BEAN");
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchproductbean");
			
			config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchproductbean");
	        
			formData = new MultivaluedMapImpl();
			formData.add("productid", productid);

			restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);

			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			productbean = productbean.stringToProductsBean(statusString);
			request.setAttribute("productbean",productbean);
			
			//FETCH PRODUCT PURCHASE STATUS FOR A USER
			
			System.out.println("FETCH PRODUCT PURCHASE STATUS FOR A USER");
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchproductpurchasestatus");
			
			config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchproductpurchasestatus");
	        
	        formData = new MultivaluedMapImpl();
			formData.add("productid", productid);
			formData.add("username", username);
			
			restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);

			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			request.setAttribute("purchasestatus",statusString);
			
			//FETCH NUMBER OF REVIEWS FOR A PRODUCT
			
			System.out.println("FETCH NUMBER OF REVIEWS FOR A PRODUCT");
			
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchnumberofreviews");
			
			config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchnumberofreviews");
	        
	        formData = new MultivaluedMapImpl();
			formData.add("productid", productid);
			
			restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);

			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			//request.setAttribute("numberofreviews",statusString);
			
			//System.out.println(statusString);
			
			//FETCH AVERAGE RATING FOR A PRODUCT
			System.out.println("FETCH AVERAGE RATING FOR A PRODUCT");
			
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchaveragerating");
			
			config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchaveragerating");
	        
	        formData = new MultivaluedMapImpl();
			formData.add("productid", productid);
			
			restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);

			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			request.setAttribute("avgrating",statusString);
			
			System.out.println(statusString);
			
			//FETCH REVIEWS FOR A PRODUCT
			System.out.println("FETCH REVIEWS FOR A PRODUCT");
			
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchproductreviews");
			
			config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchproductreviews");
	        
	        formData = new MultivaluedMapImpl();
			formData.add("productid", productid);
			
			restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);

			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			request.setAttribute("productreviews",statusString);
			
			//System.out.println(statusString);
			
			RequestDispatcher rd = request.getRequestDispatcher("product_template.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        };
    }

    private SSLContext getSSLContext() {
        javax.net.ssl.TrustManager x509 = new javax.net.ssl.X509TrustManager() {

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new javax.net.ssl.TrustManager[]{x509}, null);
        } catch (java.security.GeneralSecurityException ex) {
        }
        return ctx;
    }
    
}
