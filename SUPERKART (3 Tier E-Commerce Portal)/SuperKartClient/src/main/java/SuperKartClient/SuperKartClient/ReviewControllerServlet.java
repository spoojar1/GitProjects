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
 * Servlet implementation class ReviewControllerServlet
 */
@WebServlet("/ReviewControllerServlet")
public class ReviewControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 */
	public ReviewControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Entered sevlet");
		
		response.setContentType("text/html");
		//PrintWriter writer = response.getWriter();
		
		String productid = request.getParameter("productid");
		String starrating = request.getParameter("starrating");
		String reviewtext = request.getParameter("reviewtext");
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("USER");
		
		String statusString="";
		try {
			
			//Client client = Client.create();
			//WebResource webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/insertreview");
			
			ClientConfig config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        Client client = Client.create(config);
	        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/insertreview");
	        
	        MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", username);
			formData.add("productid", productid);
			formData.add("starrating", starrating);
			formData.add("reviewtext", reviewtext);
			
			//System.out.println("After error "+userName+" "+password+" Hello");
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			statusString = restResponse.getEntity(String.class);
			request.setAttribute("productid",productid);
			
			//call doget to refresh new counts in product_template
			new ProductControllerServlet().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//to fetch individual product details
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
