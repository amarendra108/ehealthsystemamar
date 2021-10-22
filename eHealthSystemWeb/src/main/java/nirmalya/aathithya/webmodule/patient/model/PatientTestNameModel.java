package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;

public class PatientTestNameModel {
	private String testGroupName;
	private String patientName;
	private List<DropDownModel> testlist;
	
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
