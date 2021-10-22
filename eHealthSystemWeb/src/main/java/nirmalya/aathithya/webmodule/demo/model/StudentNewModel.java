package nirmalya.aathithya.webmodule.demo.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentNewModel {
	

	private String student;
	private String name;
	private String fatherName;
	private String motherName; 
	private String regisNo; 
	private String dob;
	private String address;
	private String country; 
	private String state;
	private String dist; 
	private String email;
	private String mobile; 
	private String bloodGroup;
	private String dateofadmisn;
	private String dateofpasst;
	private String employeeStatus; 
	
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getRegisNo() {
		return regisNo;
	}
	public void setRegisNo(String regisNo) {
		this.regisNo = regisNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getEmployeeStatus() {
		return employeeStatus;
	}
	
	public String getDateofadmisn() {
		return dateofadmisn;
	}
	public void setDateofadmisn(String dateofadmisn) {
		this.dateofadmisn = dateofadmisn;
	}
	public String getDateofpasst() {
		return dateofpasst;
	}
	public void setDateofpasst(String dateofpasst) {
		this.dateofpasst = dateofpasst;
	}
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
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
