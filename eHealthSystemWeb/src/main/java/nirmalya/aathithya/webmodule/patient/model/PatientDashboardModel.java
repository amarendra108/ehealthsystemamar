package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientDashboardModel {
	private BigInteger patientId;
	private String patientName;
	private BigInteger registerId;
	private String phone;
	private String address;
	private String bloodGrp;
	private String weight;
	private String height;

	private String insuranceName;
	private String insuranceCompany;
	
	private String foodName;
	private String foodType;
	private String foodTypes;
	
	
	private String emergencyName;
	private String emergencyMob;
	private String emergencyRelation;
	
	private String testReportDate;
	private String testReportName;
	
	private String drug;
	private String since;
	private String dose;
	private String emergencyi;
	private String emergencyis;
	
	private String appointDate;
	private String appointTime;
	private String appointDoctor;
	private String appointSpeciality;
	private String appointSpecialitys;
	private String appointSpecialityss;
	
	private String medCondtnName;
	private String medCondtnNames;
	
	
	private String familyDrName;
	private String familyDrSpeciality;
	private String familyDrContctNo;
	private String familyDrNames;
	private String familyDrSpecialitys;
	private String familyDrContctNos;
	private String familyDrSpecialityss;
	private String familyDrContctNoss;
	private String familyDrContctNosss;
	private String familyDrContctNossss;
	
	private String appointmentCounts;
	private String reportCount;
	private String majorsurgeryCount;
	private String healthScreeningCount;
	private String healthCheckupCount;
	private String healths;
	private String healthCs;
	private Integer healthChs;
	private Integer familys;
	private Integer familyDrs;
	private Integer familyDrCs;
	private Integer familyDrCss;
	private Integer familyDrCons;
	
	private String patImage;
	private String patImageFile;
	
	
	
	public PatientDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	





	public String getPatImage() {
		return patImage;
	}






	public void setPatImage(String patImage) {
		this.patImage = patImage;
	}






	public BigInteger getPatientId() {
		return patientId;
	}






	public void setPatientId(BigInteger patientId) {
		this.patientId = patientId;
	}






	public String getPatImageFile() {
		return patImageFile;
	}






	public void setPatImageFile(String patImageFile) {
		this.patImageFile = patImageFile;
	}






	public String getPatientName() {
		return patientName;
	}






	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}






	public BigInteger getRegisterId() {
		return registerId;
	}






	public void setRegisterId(BigInteger registerId) {
		this.registerId = registerId;
	}






	public String getPhone() {
		return phone;
	}






	public void setPhone(String phone) {
		this.phone = phone;
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






	public String getInsuranceName() {
		return insuranceName;
	}






	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}






	public String getInsuranceCompany() {
		return insuranceCompany;
	}






	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}






	public String getFoodName() {
		return foodName;
	}






	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}






	public String getFoodType() {
		return foodType;
	}






	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}






	public String getFoodTypes() {
		return foodTypes;
	}






	public void setFoodTypes(String foodTypes) {
		this.foodTypes = foodTypes;
	}






	public String getEmergencyName() {
		return emergencyName;
	}






	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}






	public String getEmergencyMob() {
		return emergencyMob;
	}






	public void setEmergencyMob(String emergencyMob) {
		this.emergencyMob = emergencyMob;
	}






	public String getEmergencyRelation() {
		return emergencyRelation;
	}






	public void setEmergencyRelation(String emergencyRelation) {
		this.emergencyRelation = emergencyRelation;
	}






	public String getTestReportDate() {
		return testReportDate;
	}






	public void setTestReportDate(String testReportDate) {
		this.testReportDate = testReportDate;
	}






	public String getTestReportName() {
		return testReportName;
	}






	public void setTestReportName(String testReportName) {
		this.testReportName = testReportName;
	}






	public String getDrug() {
		return drug;
	}






	public void setDrug(String drug) {
		this.drug = drug;
	}






	public String getSince() {
		return since;
	}






	public void setSince(String since) {
		this.since = since;
	}






	public String getDose() {
		return dose;
	}






	public void setDose(String dose) {
		this.dose = dose;
	}






	public String getEmergencyi() {
		return emergencyi;
	}






	public void setEmergencyi(String emergencyi) {
		this.emergencyi = emergencyi;
	}






	public String getEmergencyis() {
		return emergencyis;
	}






	public void setEmergencyis(String emergencyis) {
		this.emergencyis = emergencyis;
	}






	public String getAppointDate() {
		return appointDate;
	}






	public void setAppointDate(String appointDate) {
		this.appointDate = appointDate;
	}






	public String getAppointTime() {
		return appointTime;
	}






	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}






	public String getAppointDoctor() {
		return appointDoctor;
	}






	public void setAppointDoctor(String appointDoctor) {
		this.appointDoctor = appointDoctor;
	}






	public String getAppointSpeciality() {
		return appointSpeciality;
	}






	public void setAppointSpeciality(String appointSpeciality) {
		this.appointSpeciality = appointSpeciality;
	}






	public String getAppointSpecialitys() {
		return appointSpecialitys;
	}






	public void setAppointSpecialitys(String appointSpecialitys) {
		this.appointSpecialitys = appointSpecialitys;
	}






	public String getAppointSpecialityss() {
		return appointSpecialityss;
	}






	public void setAppointSpecialityss(String appointSpecialityss) {
		this.appointSpecialityss = appointSpecialityss;
	}






	public String getMedCondtnName() {
		return medCondtnName;
	}






	public void setMedCondtnName(String medCondtnName) {
		this.medCondtnName = medCondtnName;
	}






	public String getMedCondtnNames() {
		return medCondtnNames;
	}






	public void setMedCondtnNames(String medCondtnNames) {
		this.medCondtnNames = medCondtnNames;
	}






	public String getFamilyDrName() {
		return familyDrName;
	}






	public void setFamilyDrName(String familyDrName) {
		this.familyDrName = familyDrName;
	}






	public String getFamilyDrSpeciality() {
		return familyDrSpeciality;
	}






	public void setFamilyDrSpeciality(String familyDrSpeciality) {
		this.familyDrSpeciality = familyDrSpeciality;
	}






	public String getFamilyDrContctNo() {
		return familyDrContctNo;
	}






	public void setFamilyDrContctNo(String familyDrContctNo) {
		this.familyDrContctNo = familyDrContctNo;
	}






	public String getFamilyDrNames() {
		return familyDrNames;
	}






	public void setFamilyDrNames(String familyDrNames) {
		this.familyDrNames = familyDrNames;
	}






	public String getFamilyDrSpecialitys() {
		return familyDrSpecialitys;
	}






	public void setFamilyDrSpecialitys(String familyDrSpecialitys) {
		this.familyDrSpecialitys = familyDrSpecialitys;
	}






	public String getFamilyDrContctNos() {
		return familyDrContctNos;
	}






	public void setFamilyDrContctNos(String familyDrContctNos) {
		this.familyDrContctNos = familyDrContctNos;
	}






	public String getFamilyDrSpecialityss() {
		return familyDrSpecialityss;
	}






	public void setFamilyDrSpecialityss(String familyDrSpecialityss) {
		this.familyDrSpecialityss = familyDrSpecialityss;
	}






	public String getFamilyDrContctNoss() {
		return familyDrContctNoss;
	}






	public void setFamilyDrContctNoss(String familyDrContctNoss) {
		this.familyDrContctNoss = familyDrContctNoss;
	}






	public String getFamilyDrContctNosss() {
		return familyDrContctNosss;
	}






	public void setFamilyDrContctNosss(String familyDrContctNosss) {
		this.familyDrContctNosss = familyDrContctNosss;
	}






	public String getFamilyDrContctNossss() {
		return familyDrContctNossss;
	}






	public void setFamilyDrContctNossss(String familyDrContctNossss) {
		this.familyDrContctNossss = familyDrContctNossss;
	}






	public String getAppointmentCounts() {
		return appointmentCounts;
	}






	public void setAppointmentCounts(String appointmentCounts) {
		this.appointmentCounts = appointmentCounts;
	}






	public String getReportCount() {
		return reportCount;
	}






	public void setReportCount(String reportCount) {
		this.reportCount = reportCount;
	}






	public String getMajorsurgeryCount() {
		return majorsurgeryCount;
	}






	public void setMajorsurgeryCount(String majorsurgeryCount) {
		this.majorsurgeryCount = majorsurgeryCount;
	}






	public String getHealthScreeningCount() {
		return healthScreeningCount;
	}






	public void setHealthScreeningCount(String healthScreeningCount) {
		this.healthScreeningCount = healthScreeningCount;
	}






	public String getHealthCheckupCount() {
		return healthCheckupCount;
	}






	public void setHealthCheckupCount(String healthCheckupCount) {
		this.healthCheckupCount = healthCheckupCount;
	}






	public String getHealths() {
		return healths;
	}






	public void setHealths(String healths) {
		this.healths = healths;
	}






	public String getHealthCs() {
		return healthCs;
	}






	public void setHealthCs(String healthCs) {
		this.healthCs = healthCs;
	}






	public Integer getHealthChs() {
		return healthChs;
	}






	public void setHealthChs(Integer healthChs) {
		this.healthChs = healthChs;
	}






	public Integer getFamilys() {
		return familys;
	}






	public void setFamilys(Integer familys) {
		this.familys = familys;
	}






	public Integer getFamilyDrs() {
		return familyDrs;
	}






	public void setFamilyDrs(Integer familyDrs) {
		this.familyDrs = familyDrs;
	}






	public Integer getFamilyDrCs() {
		return familyDrCs;
	}






	public void setFamilyDrCs(Integer familyDrCs) {
		this.familyDrCs = familyDrCs;
	}






	public Integer getFamilyDrCss() {
		return familyDrCss;
	}






	public void setFamilyDrCss(Integer familyDrCss) {
		this.familyDrCss = familyDrCss;
	}






	public Integer getFamilyDrCons() {
		return familyDrCons;
	}






	public void setFamilyDrCons(Integer familyDrCons) {
		this.familyDrCons = familyDrCons;
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
