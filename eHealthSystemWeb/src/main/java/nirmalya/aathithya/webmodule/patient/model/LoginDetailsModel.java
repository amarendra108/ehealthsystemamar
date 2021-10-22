package nirmalya.aathithya.webmodule.patient.model;

import java.math.BigInteger;

public class LoginDetailsModel {
	private BigInteger loginId;
	private String date;
	private String ip; 
	private String browser;
	private String time;
	private String loginAttempt;

	
	
	public BigInteger getLoginId() {
		return loginId;
	}
	public void setLoginId(BigInteger loginId) {
		this.loginId = loginId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLoginAttempt() {
		return loginAttempt;
	}
	public void setLoginAttempt(String loginAttempt) {
		this.loginAttempt = loginAttempt;
	}
	
	
	
	
}
