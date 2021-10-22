package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIBioMedicalModel {

	private String bioMName;
	private String bioMReason;
	private String bioMDate;
	
	private String userid;

	public APIBioMedicalModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIBioMedicalModel(Object bioMName, Object bioMReason, Object bioMDate) {
		super();
		this.bioMName = (String) bioMName;
		this.bioMReason = (String) bioMReason;
		this.bioMDate = (String) bioMDate;
	}

	public String getBioMName() {
		return bioMName;
	}

	public void setBioMName(String bioMName) {
		this.bioMName = bioMName;
	}

	public String getBioMReason() {
		return bioMReason;
	}

	public void setBioMReason(String bioMReason) {
		this.bioMReason = bioMReason;
	}

	public String getBioMDate() {
		return bioMDate;
	}

	public void setBioMDate(String bioMDate) {
		this.bioMDate = bioMDate;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
