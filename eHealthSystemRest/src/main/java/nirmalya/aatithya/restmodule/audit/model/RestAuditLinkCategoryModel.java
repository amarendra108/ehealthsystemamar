package nirmalya.aatithya.restmodule.audit.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAuditLinkCategoryModel {
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

	private String dept;
	private String section;
	private String deptHead;
	private String startDate;
	private String endDate;
	
	private String auditStatus;
	private String createdBy;
	
	private String manageAuditNo;
	private String mauditDate;
	private String toEmail;
	private String toCc;
	private String mailsubject;
	private String comment;
	
	//private String emailId;
	
	
	
	//private String auditInitiateIdMstr;
	private String draftNo;
//	private String date;
	private String draftpersonTo;
	private String draftpersonCc;
	private String draftSubject;
	private String draftComment;
	private String draftDate;
	
	private List<RestUpdateDocumentModel> documentList;
private List<String> concernedAuditee = new ArrayList<String>();
	
	public RestAuditLinkCategoryModel() {
		super();
	}

	public RestAuditLinkCategoryModel(Object auditInitiate, Object auditType, Object financialYear, Object initiatedBy,Object initiatedDate,Object auditorName,Object letterNo,
		    Object region, Object regionalManager,Object  concernedFinance,Object quarter,
			Object subject,Object summary, Object document,
			Object dept,Object section,Object deptHead,Object startDate,Object endDate,Object createdBy,Object auditStatus) {
		super();
		this.auditInitiate = (String) auditInitiate;
		this.auditType = (String) auditType;
		this.financialYear = (String) financialYear;
		this.initiatedBy = (String) initiatedBy;
		this.initiatedDate = (String) initiatedDate;
		this.auditorName=(String) auditorName;
		this.letterNo=(String) letterNo;
	
		this.region=(String)region;
		this.regionalManager=(String)regionalManager;
		this.concernedFinance=(String)concernedFinance;
		this.quarter=(String)quarter;
		
		this.summary = (String) summary;
		this.subject = (String) subject;
		
		this.document = (String) document;
		
		this.dept = (String) dept;
		this.section = (String) section;
		this.deptHead = (String) deptHead;
		this.startDate=(String) startDate;
		this.endDate=(String) endDate;
		
		this.createdBy = (String) createdBy;
		this.auditStatus = (String) auditStatus;
	
	}

	
	public RestAuditLinkCategoryModel(Object subject, Object manageAuditNo, Object mauditDate, Object toEmail,
			Object toCc, Object mailsubject) {
		super();
		this.subject = (String)subject;
		this.manageAuditNo = (String)manageAuditNo;
		this.mauditDate = (String)mauditDate;
		this.toEmail = (String)toEmail;
		this.toCc = (String)toCc;
		this.mailsubject = (String)mailsubject;
		this.comment = (String)comment;
	//	this.emailId = (String)emailId;
		
		//this.auditInitiate =(String)auditInitiate;
		
	}

	
	
	

	

	public RestAuditLinkCategoryModel(Object draftNo, Object auditInitiate,Object draftDate ,Object draftpersonTo, Object draftpersonCc, Object draftSubject,
			Object draftComment) {
		super();
		this.draftNo = (String)draftNo;
		this.auditInitiate =(String)auditInitiate;
		this.draftpersonTo = (String)draftpersonTo;
		this.draftpersonCc = (String)draftpersonCc;
		this.draftSubject = (String)draftSubject;
		this.draftComment = (String)draftComment;
		this.draftDate = (String)draftDate;
	}

	public String getAuditInitiate() {
		return auditInitiate;
	}

	public void setAuditInitiate(String auditInitiate) {
		this.auditInitiate = auditInitiate;
	}

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

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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

	public List<RestUpdateDocumentModel> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<RestUpdateDocumentModel> documentList) {
		this.documentList = documentList;
	}

	
	  public List<String> getConcernedAuditee() { return concernedAuditee; }
	  
	  public void setConcernedAuditee(List<String> concernedAuditee) {
	  this.concernedAuditee = concernedAuditee; }
	 
	public String getManageAuditNo() {
		return manageAuditNo;
	}

	public void setManageAuditNo(String manageAuditNo) {
		this.manageAuditNo = manageAuditNo;
	}

	
	
	  public String getMauditDate() { return mauditDate; }
	  
	  public void setMauditDate(String mauditDate) { this.mauditDate = mauditDate;
	  }
	 
	 
	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getToCc() {
		return toCc;
	}

	public void setToCc(String toCc) {
		this.toCc = toCc;
	}

	public String getMailsubject() {
		return mailsubject;
	}

	public void setMailsubject(String mailsubject) {
		this.mailsubject = mailsubject;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



	/*
	 * public String getAuditInitiateIdMstr() { return auditInitiateIdMstr; }
	 * 
	 * public void setAuditInitiateIdMstr(String auditInitiateIdMstr) {
	 * this.auditInitiateIdMstr = auditInitiateIdMstr; }
	 */

	public String getDraftNo() {
		return draftNo;
	}

	public void setDraftNo(String draftNo) {
		this.draftNo = draftNo;
	}

	public String getDraftpersonTo() {
		return draftpersonTo;
	}

	public void setDraftpersonTo(String draftpersonTo) {
		this.draftpersonTo = draftpersonTo;
	}

	public String getDraftpersonCc() {
		return draftpersonCc;
	}

	public void setDraftpersonCc(String draftpersonCc) {
		this.draftpersonCc = draftpersonCc;
	}

	public String getDraftSubject() {
		return draftSubject;
	}

	public void setDraftSubject(String draftSubject) {
		this.draftSubject = draftSubject;
	}

	public String getDraftComment() {
		return draftComment;
	}

	public void setDraftComment(String draftComment) {
		this.draftComment = draftComment;
	}

	public String getDraftDate() {
		return draftDate;
	}

	public void setDraftDate(String draftDate) {
		this.draftDate = draftDate;
	}

	/*
	 * public String getDraftDate() { return draftDate; }
	 * 
	 * public void setDraftDate(String draftDate) { this.draftDate = draftDate; }
	 */
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
