package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginDetailsModel {
	private BigInteger loginId;
	private String date;
	private String ip; 
	private String browser;
	private String time;
	private String loginAttempt;
	
	public LoginDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginDetailsModel(Object loginId, Object date, Object ip, Object browser, Object time,
			Object loginAttempt) {
		super();
		this.loginId = (BigInteger)loginId;
		this.date = (String)date;
		this.ip = (String)ip;
		this.browser = (String)browser;
		this.time = (String)time;
		this.loginAttempt = (String)loginAttempt;
	}



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
	
	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}
}
