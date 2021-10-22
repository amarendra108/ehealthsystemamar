package nirmalya.aathithya.webmodule.audit.model;

import java.util.List;

public class AuditManageModel {
	
	
private String auditInitiate;
	
	private String auditType;
	
	private String auditorName;
	
	private String financialYear;
	
	private String initiatedBy;
	
	private String initiatedDate;
	
	private String letterNo;
	
	private String region;
	private String regionalManager;
	private String concernedFinance;
	private String quarter;
	
	private String summary;
	private String subject;
	
	private String document;
	
	private String initiatedStatus;
	
	private String createdBy;

	
	private String dept;
	private String section;
	private String deptHead;
	private String startDate;
	private String endDate;
	private String auditStatus;

	
	private List<AuditMangeDocumentModel> documentList;


	


	public String getAuditType() {
		return auditType;
	}


	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}


	public String getAuditorName() {
		return auditorName;
	}


	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}


	public String getFinancialYear() {
		return financialYear;
	}


	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}


	public String getInitiatedBy() {
		return initiatedBy;
	}


	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}


	public String getInitiatedDate() {
		return initiatedDate;
	}


	public void setInitiatedDate(String initiatedDate) {
		this.initiatedDate = initiatedDate;
	}


	public String getLetterNo() {
		return letterNo;
	}


	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getRegionalManager() {
		return regionalManager;
	}


	public void setRegionalManager(String regionalManager) {
		this.regionalManager = regionalManager;
	}


	public String getConcernedFinance() {
		return concernedFinance;
	}


	public void setConcernedFinance(String concernedFinance) {
		this.concernedFinance = concernedFinance;
	}


	public String getQuarter() {
		return quarter;
	}


	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getDocument() {
		return document;
	}


	public void setDocument(String document) {
		this.document = document;
	}


	public String getInitiatedStatus() {
		return initiatedStatus;
	}


	public void setInitiatedStatus(String initiatedStatus) {
		this.initiatedStatus = initiatedStatus;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getDeptHead() {
		return deptHead;
	}


	public void setDeptHead(String deptHead) {
		this.deptHead = deptHead;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getAuditStatus() {
		return auditStatus;
	}


	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}


	public List<AuditMangeDocumentModel> getDocumentList() {
		return documentList;
	}


	public void setDocumentList(List<AuditMangeDocumentModel> documentList) {
		this.documentList = documentList;
	}


	public String getAuditInitiate() {
		return auditInitiate;
	}


	public void setAuditInitiateId(String auditInitiate) {
		this.auditInitiate = auditInitiate;
	}
	

}
