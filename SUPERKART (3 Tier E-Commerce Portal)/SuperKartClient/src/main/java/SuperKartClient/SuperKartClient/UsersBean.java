package SuperKartClient.SuperKartClient;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.regex.Pattern;

public class UsersBean {

	private String firstName;
	private String lastName;
	private String userName;
	private String userPassword;
	private String emailId;
	private long contactNo;
	private Date dateOfBirth;
	private String billingAddress;
	private String shippingAddress;
	private Timestamp lastLogin;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public UsersBean stringToUsersBean(String str){
		String[] s=str.split(Pattern.quote("^"));
		System.out.println("In UsersBean stringToUsersBean "+str+" "+s.length);
		UsersBean bean=new UsersBean();
		if(!s[0].equals("null"))
			bean.setFirstName(s[0]);
		if(!s[1].equals("null"))
			bean.setLastName(s[1]);
		bean.setUserName(s[2]);
		if(!s[4].equals("null"))
			bean.setEmailId(s[4]);
		if(!s[5].equals("null"))
			bean.setContactNo(Long.parseLong(s[5]));
		if(!s[6].equals("null"))
			bean.setDateOfBirth(Date.valueOf(s[6]));
		if(!s[7].equals("null"))
			bean.setBillingAddress(s[7]);
		if(!s[8].equals("null"))
			bean.setShippingAddress(s[8]);
		if(!s[9].equals("null"))
			bean.setLastLogin(Timestamp.valueOf(s[9]));
		
		return bean;
	}
	
}
