package nirmalya.aathithya.webmodule.audit.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AuditUploadDocumentModel {
	private String auditUploadId;
	private String auditTypeId;
	private String quater;
	private String financialYear;
	private String auditFolder;
	private List<UploadDocumentModel> documentList;
	private String createdBy;
	private String updatedBy;
	private String createdOn;
	private String updatedOn;
	
	public AuditUploadDocumentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public String getAuditUploadId() {
		return auditUploadId;
	}



	public void setAuditUploadId(String auditUploadId) {
		this.auditUploadId = auditUploadId;
	}



	public String getAuditTypeId() {
		return auditTypeId;
	}



	public void setAuditTypeId(String auditTypeId) {
		this.auditTypeId = auditTypeId;
	}



	public String getQuater() {
		return quater;
	}



	public void setQuater(String quater) {
		this.quater = quater;
	}



	public String getFinancialYear() {
		return financialYear;
	}



	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}



	public String getAuditFolder() {
		return auditFolder;
	}



	public void setAuditFolder(String auditFolder) {
		this.auditFolder = auditFolder;
	}



	public List<UploadDocumentModel> getDocumentList() {
		return documentList;
	}



	public void setDocumentList(List<UploadDocumentModel> documentList) {
		this.documentList = documentList;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public String getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}



	public String getUpdatedOn() {
		return updatedOn;
	}



	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
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
