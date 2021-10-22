package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLabDashboardPatientModel {
	
	private String time;
	private String patientName;
	private String patientId;
	private String testGroup;
	private String testName;
	private String status;
	private String orderid;
	
	public RestLabDashboardPatientModel(Object time, Object patientName, Object patientId, Object testGroup,
			Object testName, Object status, Object orderid) {
		super();
		this.time = (String) time;
		this.patientName = (String) patientName;
		this.patientId = (String) patientId;
		this.testGroup = (String) testGroup;
		this.testName = (String) testName;
		this.status = (String) status;
		this.orderid = (String) orderid;
	}

	
	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getTestGroup() {
		return testGroup;
	}

	public void setTestGroup(String testGroup) {
		this.testGroup = testGroup;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
