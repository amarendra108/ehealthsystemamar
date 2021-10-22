package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RestTestReportModel {
	private String patientId;
	private String patientName;
	private String patientAge;
	private Integer age;
	private String gender;
	private String weight;
	private String height;
	private String testDate;
	private String phc;
	private String mob;
	
	private String grpOrder;
	private String grpName;
	private String testName;
	private String testUnit;
	private String range;
	private String actualValue;
	
	private String reportStatus;
	private String email;
	private String date;
	
	public RestTestReportModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestTestReportModel(Object patientId, Object patientName, Object age, Object gender, Object weight,
			Object height, Object mob, Object phc,Object grpOrder,Object grpName,Object testName,Object testUnit,Object range,
			Object email,Object date,Object reportStatus) {
		super();
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.age = (Integer) age;
		this.gender = (String) gender;
		this.weight = (String) weight;
		this.height = (String) height;
		this.mob = (String) mob;
		this.phc = (String) phc;
		
		this.grpOrder = (String) grpOrder;
		this.grpName = (String) grpName;
		this.testName = (String) testName;
		this.testUnit = (String) testUnit;
		this.range = (String) range;
		
		this.email = (String) email;
		this.date = (String) date;
		this.reportStatus = (String) reportStatus;
	}
	public RestTestReportModel(Object patientId, Object patientName, Object age, Object gender, Object weight,
			Object height, Object mob, Object phc,Object grpOrder,Object grpName,Object testName,Object testUnit,Object range) {
		super();
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.age = (Integer) age;
		this.gender = (String) gender;
		this.weight = (String) weight;
		this.height = (String) height;
		this.mob = (String) mob;
		this.phc = (String) phc;
		
		this.grpOrder = (String) grpOrder;
		this.grpName = (String) grpName;
		this.testName = (String) testName;
		this.testUnit = (String) testUnit;
		this.range = (String) range;
		

	}
	
	public String getGrpOrder() {
		return grpOrder;
	}
	public void setGrpOrder(String grpOrder) {
		this.grpOrder = grpOrder;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestUnit() {
		return testUnit;
	}
	public void setTestUnit(String testUnit) {
		this.testUnit = testUnit;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getActualValue() {
		return actualValue;
	}
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getPhc() {
		return phc;
	}
	public void setPhc(String phc) {
		this.phc = phc;
	}
	
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
