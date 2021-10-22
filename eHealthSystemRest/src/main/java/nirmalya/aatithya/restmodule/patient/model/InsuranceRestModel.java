package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InsuranceRestModel {
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
	public InsuranceRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InsuranceRestModel(Object insuranceId, Object patientId, Object insuranceCompany, Object insuranceType
			, Object healthInsuranceType, Object thirdPartyAdmin, Object policyNo, Object premiumAmount, Object policyStartDate
			, Object policyEndDate, Object totalInsuranceAmount, Object sumAssuredAmount, Object premiumDueDate) {
		super();
		this.insuranceId = (BigInteger)insuranceId;
		this.patientId = (BigInteger)patientId;
		this.insuranceCompany = (String)insuranceCompany;
		this.insuranceType = (String)insuranceType;
		this.healthInsuranceType = (String)healthInsuranceType;
		this.thirdPartyAdmin = (String)thirdPartyAdmin;
		this.policyNo = (BigInteger)policyNo;
		this.premiumAmount = (Integer)premiumAmount;
		this.policyStartDate = (String)policyStartDate;
		this.policyEndDate = (String)policyEndDate;
		this.totalInsuranceAmount = (Integer)totalInsuranceAmount;
		this.sumAssuredAmount = (Integer)sumAssuredAmount;
		this.premiumDueDate = (String)premiumDueDate;
		
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
