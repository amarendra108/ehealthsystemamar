package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HealthScreeningModel {
	private String uHIdNo;
	
	private String healthId;


	private String uHIdName;

	private String nameProfile;

	private String fistName;

	private String middleName;

	private String lastName;

	private Integer age;

	private String genderId;

	private String genderName;

	private String patientId;

	private String patientName;

	private String countryId;

	private String countryName;

	private String stateId;

	private String stateName;

	private String mobileNoId;

	private String startDate;

	private String startTime;

	private Integer uhidCardNo;

	private Integer statusProfile;

	public HealthScreeningModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getuHIdNo() {
		return uHIdNo;
	}

	public void setuHIdNo(String uHIdNo) {
		this.uHIdNo = uHIdNo;
	}

	public String getuHIdName() {
		return uHIdName;
	}

	public void setuHIdName(String uHIdName) {
		this.uHIdName = uHIdName;
	}

	public String getNameProfile() {
		return nameProfile;
	}

	public void setNameProfile(String nameProfile) {
		this.nameProfile = nameProfile;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	

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

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	

	public String getHealthId() {
		return healthId;
	}

	public void setHealthId(String healthId) {
		this.healthId = healthId;
	}

	public String getMobileNoId() {
		return mobileNoId;
	}

	public void setMobileNoId(String mobileNoId) {
		this.mobileNoId = mobileNoId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getUhidCardNo() {
		return uhidCardNo;
	}

	public void setUhidCardNo(Integer uhidCardNo) {
		this.uhidCardNo = uhidCardNo;
	}

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getStatusProfile() {
		return statusProfile;
	}

	public void setStatusProfile(Integer statusProfile) {
		this.statusProfile = statusProfile;
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
