package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class RestPatientTestNameModel {
	private String testGroupName;
	private String patientName;
	private List<DropDownModel> testlist;

	public RestPatientTestNameModel(Object testGroupName, Object patientName) {
		super();
		this.testGroupName = (String) testGroupName;
		this.patientName = (String) patientName;
	}

	
	public String getTestGroupName() {
		return testGroupName;
	}


	public void setTestGroupName(String testGroupName) {
		this.testGroupName = testGroupName;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public List<DropDownModel> getTestlist() {
		return testlist;
	}


	public void setTestlist(List<DropDownModel> testlist) {
		this.testlist = testlist;
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
