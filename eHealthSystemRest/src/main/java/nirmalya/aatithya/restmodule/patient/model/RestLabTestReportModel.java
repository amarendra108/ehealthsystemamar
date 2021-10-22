package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLabTestReportModel {
	private String patientId;
	private String patientName;
	private String patientAge;
	//private Integer age;
	private String age;
	private String gender;
	private String weight;
	private String height;
	private String testDate;
	private String phc;
	private String mob;
	private String address;
	private String bloodGrp;
	
	private String grpOrder;
	private String grpName;
	private String testName;
	private String testUnit;
	private String range;
	private String actualValue;
	
	private List<RestPatientTestNameModel> testgroup;
	public RestLabTestReportModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestLabTestReportModel(Object patientId, Object patientName, Object age, Object gender, Object weight,
			Object height, Object testDate, Object phc) {
		super();
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.age = (String) age;
		this.gender = (String) gender;
		this.weight = (String) weight;
		this.height = (String) height;
		this.testDate = (String) testDate;
		this.phc = (String) phc;
	}
	public RestLabTestReportModel(Object testName, Object actualValue) {
		super();
		this.testName = (String) testName;
		this.actualValue = (String) actualValue;
	}
	
	public RestLabTestReportModel(Object patientId, Object patientName, Object mob, Object address, Object bloodGrp,Object weight,
			Object height) {
		super();
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.mob = (String) mob;
		this.address = (String) address;
		this.bloodGrp = (String) bloodGrp;
		this.weight = (String) weight;
		this.weight = (String) weight;
		this.height = (String) height;
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
	public List<RestPatientTestNameModel> getTestgroup() {
		return testgroup;
	}
	public void setTestgroup(List<RestPatientTestNameModel> testgroup) {
		this.testgroup = testgroup;
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
	/*public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}*/
	
	public String getPatientAge() {
		return patientAge;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBloodGrp() {
		return bloodGrp;
	}
	public void setBloodGrp(String bloodGrp) {
		this.bloodGrp = bloodGrp;
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
