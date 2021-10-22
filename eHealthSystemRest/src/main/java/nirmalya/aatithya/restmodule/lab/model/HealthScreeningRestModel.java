package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HealthScreeningRestModel {

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

	public HealthScreeningRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HealthScreeningRestModel(Object uHIdNo, Object healthId, Object uHIdName, Object nameProfile,
			Object fistName, Object middleName, Object lastName, Object age, Object patientId, Object stateId,
			Object genderName, Object genderId, Object countryId, Object countryName, Object stateName,
			Object patientName, Object mobileNoId, Object startDate, Object startTime, Object uhidCardNo,
			Object statusProfile) {
		super();

		this.uHIdNo = (String) uHIdNo;
		this.healthId = (String) healthId;

		this.uHIdName = (String) uHIdName;

		this.nameProfile = (String) nameProfile;
		this.fistName = (String) fistName;
		this.middleName = (String) middleName;
		this.lastName = (String) lastName;
		this.age = (Integer) age;
		this.genderId = (String) genderId;
		this.genderName = (String) genderName;
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.countryId = (String) countryId;
		this.countryName = (String) countryName;
		this.stateId = (String) stateId;
		this.stateName = (String) stateName;
		this.mobileNoId = (String) mobileNoId;
		this.startDate = (String) startDate;
		this.startTime = (String) startTime;
		this.uhidCardNo = (Integer) uhidCardNo;
		this.statusProfile = (Integer) statusProfile;

	}

	public String getuHIdNo() {
		return uHIdNo;
	}

	public void setuHIdNo(String uHIdNo) {
		this.uHIdNo = uHIdNo;
	}

	public String getHealthId() {
		return healthId;
	}

	public void setHealthId(String healthId) {
		this.healthId = healthId;
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

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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
