package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientFamilyDetailsModel {
	
	private String name;
	private String relation;
	private String age;
	private String birthDate;
	private String since;
	private String medicalCondition;
	
	
	public RestPatientFamilyDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestPatientFamilyDetailsModel(Object name,Object relation,Object age, Object birthDate, Object since) {
		super();
		
		this.name = (String)name;
		this.relation = (String)relation;
		this.age = (String)age;
		this.birthDate = (String)birthDate;
		this.since = (String)since;
		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getMedicalCondition() {
		return medicalCondition;
	}

	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
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
