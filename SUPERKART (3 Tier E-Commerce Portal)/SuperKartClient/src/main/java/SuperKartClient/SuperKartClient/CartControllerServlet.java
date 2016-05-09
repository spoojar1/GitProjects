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
 * Servlet implementation class CartControllerServlet
 */
@WebServlet("/CartControllerServlet")
public class CartControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 */
	public CartControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//to add products to cart
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Entered post in cart controller sevlet");
		
		response.setContentType("text/html");
		//PrintWriter writer = response.getWriter();
		
		String quantity = request.getParameter("quantity");
		String productid = request.getParameter("productid");
		
		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("USER");
		
		Client client;
		WebResource webResource;
		ClientConfig config;
		
		MultivaluedMap formData;
		
		String statusString="";
		try {	
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/addtocart");
			
			config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/addtocart");
	       
			formData = new MultivaluedMapImpl();
			formData.add("quantity", quantity);
			formData.add("productid", productid);
			formData.add("username", username);
			
			
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//fetch cart details
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered get in cart controller sevlet");
		
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("USER");
		
		String statusString="";
		
		Client client;
		WebResource webResource;
		ClientConfig config;
		
		try {	
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchcart");
			
			config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchcart");
	       
	        MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", username);
			formData.add("hashkey", "CHSUYG");
			
			//System.out.println("After error "+userName+" "+password+" Hello");
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			writer.append(statusString);
			
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
