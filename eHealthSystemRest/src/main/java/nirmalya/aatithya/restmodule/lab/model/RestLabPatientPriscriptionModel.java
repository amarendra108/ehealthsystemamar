package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLabPatientPriscriptionModel {
	
	private String testGroupName;
	private String testName;
	private String patientsName;
	private String address;
	private String orderId;
	
	
	public RestLabPatientPriscriptionModel(Object testGroupName, Object testName, Object patientsName, Object address,Object orderId) {
		super();
		this.testGroupName = (String) testGroupName;
		this.testName = (String) testName;
		this.patientsName = (String) patientsName;
		this.address = (String) address;
		this.orderId = (String) orderId;
		
	}


	public String getTestGroupName() {
		return testGroupName;
	}


	public void setTestGroupName(String testGroupName) {
		this.testGroupName = testGroupName;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	


	public String getPatientsName() {
		return patientsName;
	}


	public void setPatientsName(String patientsName) {
		this.patientsName = patientsName;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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
