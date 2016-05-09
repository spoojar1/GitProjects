package SuperKartClient.SuperKartClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;

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

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	
	final static Logger logger = Logger.getLogger(LoginControllerServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("USER");
		
		session = request.getSession();
		session.invalidate();
		
		Enumeration e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			String attName = (String) e.nextElement();
			request.removeAttribute(attName);
		}
		  
		ClientConfig config = new DefaultClientConfig(); // SSL configuration
        // SSL configuration
        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
        Client client = Client.create(config);
        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/clearcache");
        
        ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(ClientResponse.class,username);
        
		response.getWriter().write(String.valueOf("invalid"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//login and user verify
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logger.info("Inside doPost");
		
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		//LoginBean bean = new LoginBean();
		UsersBean bean = new UsersBean();
		bean.setUserName(userName);
		bean.setUserPassword(password);

		//String status = "";
		int status=0;
		try {
			//Client client = Client.create();
			//WebResource webResource = client.resource("http://localhost:9081/SuperKartWebServices/loginservices/checkuservalidity");
			
			ClientConfig config = new DefaultClientConfig(); // SSL configuration
	        // SSL configuration
	        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
	        Client client = Client.create(config);
	        WebResource webResource = client.resource("https://localhost:8444/SuperKartWebServices").path("loginservices/checkuservalidity");
	        
	        
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", userName);
			formData.add("password", password);
			formData.add("hashkey", "CHSUYG");
			
			//System.out.println("form data "+userName+" "+password+" Hello");
			ClientResponse restResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			logger.info("User Validation Request sent to https://localhost:8444/SuperKartWebServices/loginservices/checkuservalidity");
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}

			String statusString = restResponse.getEntity(String.class);
			if(statusString.equals("404")){
				RequestDispatcher rd = request.getRequestDispatcher("errorpage.html");
				rd.forward(request, response);
			}
			status = Integer.parseInt(statusString);
			//0: username wrong
			//1: password wrong
			//2: user valid
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("inside login servlet "+status);
		if (status==2) {
			
			/*String[] s=status.split(Pattern.quote("^"));
			String timeStamp = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(s[1]);
			bean.setLastLogin(Timestamp.valueOf(timeStamp));
			*/
			request.setAttribute("bean", bean);
			HttpSession session = request.getSession();
			session.setAttribute("USER", userName);
            
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else {
			System.out.println(status);
			if(status==1){	
				request.setAttribute("fail-attempts",1);
			}
			
			request.setAttribute("valid", false);
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
