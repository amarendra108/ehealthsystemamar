package nirmalya.aatithya.restmodule.customer.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestCustoomerNewModel {
	private String custId; 
	private String custName; 
	private String custEmail; 
	private String custPhone;
	private String custAddress; 
	private String custCity; 
	private String district; 
	private String state;
	private String country; 
	private String custZipCode; 
	private String custGSTNo; 
	private String fileCustomer;
	private String contactPerson; 
	private String custActive;
	private String custCreatedBy;
	private String custUpdatedOn;
	
	
	public RestCustoomerNewModel() {
		super();
		
	}
	public RestCustoomerNewModel(Object custId, Object custName, Object custEmail, Object custPhone, Object custAddress,
			Object custCity, Object district, Object state, Object country, Object custZipCode, Object custGSTNo,
			Object fileCustomer,Object contactPerson,Object custActive, Object custCreatedBy,Object custUpdatedOn) {
		super();
		this.custId = (String) custId;
		this.custName = (String) custName;
		this.custEmail =(String) custEmail;
		this.custPhone =(String) custPhone;
		this.custAddress = (String) custAddress;
		this.custCity = (String) custCity;
		this.district = (String) district;
		this.state = (String) state;
		this.country = (String) country;
		this.custZipCode = (String) custZipCode;
		this.custGSTNo =(String) custGSTNo;
		this.fileCustomer = (String) fileCustomer;
		this.contactPerson =(String) contactPerson;
		this.custActive = (String) custActive;
		this.custCreatedBy = (String) custCreatedBy;
		this.custUpdatedOn = (String) custUpdatedOn;
		
	}
	public String getFileCustomer() {
		return fileCustomer;
	}
	public void setFileCustomer(String fileCustomer) {
		this.fileCustomer = fileCustomer;
	}
	public String getCustUpdatedOn() {
		return custUpdatedOn;
	}
	public void setCustUpdatedOn(String custUpdatedOn) {
		this.custUpdatedOn = custUpdatedOn;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCustZipCode() {
		return custZipCode;
	}
	public void setCustZipCode(String custZipCode) {
		this.custZipCode = custZipCode;
	}
	public String getCustGSTNo() {
		return custGSTNo;
	}
	public void setCustGSTNo(String custGSTNo) {
		this.custGSTNo = custGSTNo;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getCustActive() {
		return custActive;
	}
	public void setCustActive(String custActive) {
		this.custActive = custActive;
	}
	public String getCustCreatedBy() {
		return custCreatedBy;
	}
	public void setCustCreatedBy(String custCreatedBy) {
		this.custCreatedBy = custCreatedBy;
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
