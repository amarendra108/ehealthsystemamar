package nirmalya.aathithya.webmodule.doctor.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ViewMyPatientModel {
	private String patientId;
	private String patientName;
	private String address;
	private String mobileNo;
	private String opdDate;
	

public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getOpdDate() {
		return opdDate;
	}


	public void setOpdDate(String opdDate) {
		this.opdDate = opdDate;
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
