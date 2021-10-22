package nirmalya.aathithya.webmodule.patient.model;

import java.math.BigInteger;

public class InsuranceModel {
	private BigInteger insuranceId;
	private BigInteger patientId;
	private String insuranceCompany; 
	private String insuranceType;
	private String healthInsuranceType;
	private String thirdPartyAdmin;
	private BigInteger policyNo;
	private Integer premiumAmount;
	private String policyStartDate;
	private String policyEndDate;
	private Integer totalInsuranceAmount;
	private Integer sumAssuredAmount;
	private String premiumDueDate;
	public InsuranceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BigInteger getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(BigInteger insuranceId) {
		this.insuranceId = insuranceId;
	}
	public BigInteger getPatientId() {
		return patientId;
	}
	public void setPatientId(BigInteger patientId) {
		this.patientId = patientId;
	}
	
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getHealthInsuranceType() {
		return healthInsuranceType;
	}
	public void setHealthInsuranceType(String healthInsuranceType) {
		this.healthInsuranceType = healthInsuranceType;
	}
	public String getThirdPartyAdmin() {
		return thirdPartyAdmin;
	}
	public void setThirdPartyAdmin(String thirdPartyAdmin) {
		this.thirdPartyAdmin = thirdPartyAdmin;
	}
	public BigInteger getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(BigInteger policyNo) {
		this.policyNo = policyNo;
	}
	public Integer getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(Integer premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public String getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public Integer getTotalInsuranceAmount() {
		return totalInsuranceAmount;
	}
	public void setTotalInsuranceAmount(Integer totalInsuranceAmount) {
		this.totalInsuranceAmount = totalInsuranceAmount;
	}
	public Integer getSumAssuredAmount() {
		return sumAssuredAmount;
	}
	public void setSumAssuredAmount(Integer sumAssuredAmount) {
		this.sumAssuredAmount = sumAssuredAmount;
	}
	public String getPremiumDueDate() {
		return premiumDueDate;
	}
	public void setPremiumDueDate(String premiumDueDate) {
		this.premiumDueDate = premiumDueDate;
	}
	
	
}
