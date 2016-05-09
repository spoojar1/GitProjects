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
 * Servlet implementation class ProfileDetailsControllerServlet
 */
@WebServlet("/ProfileDetailsControllerServlet")
public class ProfileDetailsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 */
	public ProfileDetailsControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//called when new user is created
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		System.out.println("In get of ProfileDetails servlet "+userName);
		
		try {
			//Client client = Client.create();
			//WebResource webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/updateprofiledetails");
			
			ClientConfig config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        Client client = Client.create(config);
	        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/updateprofiledetails");
	        
	        MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", userName);
			formData.add("fname", request.getParameter("fname"));
			formData.add("emailid", request.getParameter("emailid"));
			formData.add("shipaddr", request.getParameter("shipaddr"));
			formData.add("dob", request.getParameter("dob"));
			formData.add("lname", request.getParameter("lname"));
			formData.add("contact", request.getParameter("contact"));
			formData.add("billaddr", request.getParameter("billaddr"));
			
			//System.out.println("After error "+userName+" "+password+" Hello");
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			String str = restResponse.getEntity(String.class);
			//System.out.println("inside do get before calling do post"+str);
			if(Boolean.parseBoolean(str)){
				System.out.println("calling do post of profile details");
				doPost(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Inside post of fetch profile details");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//String userName = request.getParameter("username");
		HttpSession session = request.getSession(false);
		String userName=(String) session.getAttribute("USER");
		
		System.out.println("profile servlet"+userName);
		String str="";
		try {
			
			//Client client = Client.create();
			//WebResource webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/fetchprofiledetails");
			
			ClientConfig config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        Client client = Client.create(config);
	        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/fetchprofiledetails");
	        
	        MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", userName);
			//System.out.println("After error "+userName+" "+password+" Hello");
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			str = restResponse.getEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("In profileDetails servlet"+str);
		//set bean after processing the string
		UsersBean bean = new UsersBean().stringToUsersBean(String.valueOf(str));
		request.setAttribute("bean", bean);
		
		//HttpSession session = request.getSession(false);
		//session.setAttribute("USER", userName);
		
		//System.out.println("Checking sessoin"+session.getAttribute("USER"));
		//System.out.println(request.getParameter("mode"));
		/*if(request.getParameter("mode")==null){
			response.getWriter().write("midviewprofile");
		}else{*/
			System.out.println("I am here");
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);
		//}	
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
