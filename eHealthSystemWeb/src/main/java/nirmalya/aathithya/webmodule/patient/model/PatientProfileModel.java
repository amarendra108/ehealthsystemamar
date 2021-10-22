package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientProfileModel {
	
	private String fileDetails;
	private Integer userId;
	private String firstnameid;
	private String lastnameid;
	private Integer uhidNo;
	private String bloodgrp;
	private String dobid;
	private Integer age;
	private String gender;
	private String maritialStatus;
	private String occupation;
	
	
	public String getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(String fileDetails) {
		this.fileDetails = fileDetails;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstnameid() {
		return firstnameid;
	}
	public void setFirstnameid(String firstnameid) {
		this.firstnameid = firstnameid;
	}
	public String getLastnameid() {
		return lastnameid;
	}
	public void setLastnameid(String lastnameid) {
		this.lastnameid = lastnameid;
	}
	public Integer getUhidNo() {
		return uhidNo;
	}
	public void setUhidNo(Integer uhidNo) {
		this.uhidNo = uhidNo;
	}
	public String getBloodgrp() {
		return bloodgrp;
	}
	public void setBloodgrp(String bloodgrp) {
		this.bloodgrp = bloodgrp;
	}
	public String getDobid() {
		return dobid;
	}
	public void setDobid(String dobid) {
		this.dobid = dobid;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritialStatus() {
		return maritialStatus;
	}
	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
