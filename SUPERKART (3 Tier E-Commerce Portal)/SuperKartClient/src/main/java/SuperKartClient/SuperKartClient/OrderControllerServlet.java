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
 * Servlet implementation class OrderControllerServlet
 */
@WebServlet("/OrderControllerServlet")
public class OrderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 */
	public OrderControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//to submit order
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Entered get in order controller sevlet");
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("USER");
		
		Client client;
		WebResource webResource;
		MultivaluedMap formData;
		
		String statusString="";
		try {	
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/updatecart");
			ClientConfig config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/submitorder");
	        
	        formData = new MultivaluedMapImpl();
			formData.add("username", username);
			
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			System.out.println("inside order servlet"+statusString);
			
			//request.setAttribute("email",statusString);
			//new EmailServlet().doGet(request, response);
			writer.append("true");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//fetch previous orders
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered post in order controller sevlet");
		
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("USER");
		
		Client client;
		WebResource webResource;
		MultivaluedMap formData;
		
		String statusString="";
		try {	
			//client = Client.create();
			//webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/updatecart");
			ClientConfig config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        client = Client.create(config);
	        webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchprevorders");
	        
	        formData = new MultivaluedMapImpl();
			formData.add("username", username);
			
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			//System.out.println("inside order servlet"+statusString);
			
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
