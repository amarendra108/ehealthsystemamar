package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorProfileModel {
	private String doctorid;
	private String drname;
	private String drGender;
	private String drOrganisation;
	private String drExprience;
	private String drSpciality;
	private String drRating;
	private String fileDetails;

	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public String getDrname() {
		return drname;
	}

	public void setDrname(String drname) {
		this.drname = drname;
	}

	public String getDrGender() {
		return drGender;
	}

	public void setDrGender(String drGender) {
		this.drGender = drGender;
	}

	public String getDrOrganisation() {
		return drOrganisation;
	}

	public void setDrOrganisation(String drOrganisation) {
		this.drOrganisation = drOrganisation;
	}

	public String getDrExprience() {
		return drExprience;
	}

	public void setDrExprience(String drExprience) {
		this.drExprience = drExprience;
	}

	public String getDrSpciality() {
		return drSpciality;
	}

	public void setDrSpciality(String drSpciality) {
		this.drSpciality = drSpciality;
	}

	public String getDrRating() {
		return drRating;
	}

	public void setDrRating(String drRating) {
		this.drRating = drRating;
	}

	public String getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(String fileDetails) {
		this.fileDetails = fileDetails;
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
