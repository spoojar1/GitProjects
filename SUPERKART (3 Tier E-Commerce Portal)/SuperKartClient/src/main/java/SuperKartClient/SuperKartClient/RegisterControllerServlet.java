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
 * Servlet implementation class RegisterControllerServlet
 */
@WebServlet("/RegisterControllerServlet")
public class RegisterControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RegisterControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String emailid = request.getParameter("emailid");
		String password = request.getParameter("password");
		
		//System.out.println(username+" "+emailid+" "+password);
		UsersBean bean = new UsersBean();
		bean.setUserName(username);
		bean.setUserPassword(password);
		bean.setEmailId(emailid);
				
		String str="";
		Boolean status=false;
		try {
			
			//Client client = Client.create();
			//WebResource webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/registeruser");
			
			ClientConfig config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        Client client = Client.create(config);
	        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/registeruser");
	        
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", username);
			formData.add("emailid", emailid);
			formData.add("password", password);
			
			//System.out.println("After error "+userName+" "+password+" Hello");
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			str = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//false = username exists 
		//true = new user created
		if (status) {
			request.setAttribute("bean", bean);
			HttpSession session = request.getSession();
			session.setAttribute("USER", username);
			
			//Call fetchprofiledetails servlet
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfileDetailsControllerServlet");
			dispatcher.forward(request,response);
			
		} else {
			System.out.println(status);
			request.setAttribute("taken", true);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
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
