package nirmalya.aatithya.restmodule.master.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestBankMasterModel {

	private String bankId;
	private String bankName;
	private String bankCode;
	private String bankDesptn;
	private String status;
	//private Boolean active;
	private String createdBy;
	
	
	public RestBankMasterModel() {
		super();
	}
	public RestBankMasterModel(Object bankId, Object bankName, Object bankCode, Object bankDesptn, Object status,Object createdBy) {
		super();
		this.bankId = (String) bankId;
		this.bankName = (String) bankName;
		this.bankCode = (String) bankCode;
		this.bankDesptn = (String) bankDesptn;
		this.status = (String) status;
		//this.active=(Boolean) active;
		this.createdBy=(String) createdBy;
		
	}
	
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankDesptn() {
		return bankDesptn;
	}
	public void setBankDesptn(String bankDesptn) {
		this.bankDesptn = bankDesptn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
