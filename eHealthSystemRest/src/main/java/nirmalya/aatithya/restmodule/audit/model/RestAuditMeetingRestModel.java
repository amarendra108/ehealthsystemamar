package nirmalya.aatithya.restmodule.audit.model;

import java.util.List;

import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorDocumentModel;

public class RestAuditMeetingRestModel {
	
	private String meetingId;
	private String date;
	private String startTime;
	private String endTime;
	private String subject;
	private String departmentid;
	private String departmentName;
	private String participantId;
	private String participantName;
	private String commentck;
	List<RestAuditMeetingDocumentModel> documentList;
	private String createdBy;
	private String updatedBy;
	private String createdOn;
	private String updatedOn;
	private String status;
	private String action;
	private String draftNo;
	private String draftDate;
	private String draftfromtime;
	private String draftendtime;
	private String draftsubject;
	private String draftDepartmentid;
	private String draftDepartment;
	private String draftparticipant;
	private String draftComment;
	
	
	private String sendNo;
	
	private String emailId;
	private String mailDate;
	private String mailfromtime;
	private String mailendtime;
	private String mailsubject;
	
	private String mailDepartment;
	private String mailparticipant;
	private String mailComment;
	
	
public RestAuditMeetingRestModel() {
		
		super();
	}
	public RestAuditMeetingRestModel(Object meetingId, Object date, Object startTime, Object endTime,
			Object subject,  Object departmentid,Object departmentName,Object participantid, Object participantName, Object commentck,Object createdBy,Object updatedBy
			
				) {
		super();
		this.meetingId = (String) meetingId;
		this.date = (String) date;
		this.startTime = (String) startTime;
		this.endTime = (String) endTime;
		this.subject = (String) subject;
		this.departmentid = (String) departmentid;
		this.departmentName = (String) departmentName;		
		this.participantId = (String) participantId;		
		this.participantName = (String) participantName;
		this.commentck = (String) commentck;
		this.createdBy = (String) createdBy;
		this.updatedBy = (String) updatedBy;
		
		/*
		 * this.type = (String) type; this.address = (String) address; this.city =
		 * (String) motherName; this.state = (String) state; this.country = (String)
		 * country; this.zipCode = (String) zipCode; this.status = (Boolean) status;
		 */
	}
	
	
	
	
	
	
	public RestAuditMeetingRestModel(Object draftNo, Object meetingId, Object draftDate, Object draftfromtime,
			Object draftendtime, Object draftsubject, Object draftDepartmentid,Object draftDepartment,Object draftparticipant, Object draftComment) {
		super();

		this.draftNo = (String) draftNo;
		this.meetingId = (String) meetingId;
		this.draftDate = (String) draftDate;
		this.draftfromtime = (String) draftfromtime;
		this.draftendtime = (String) draftendtime;
		this.draftsubject = (String) draftsubject;

		this.draftDepartmentid = (String) draftDepartmentid;
		this.draftDepartment = (String) draftDepartment;
		this.draftparticipant = (String) draftparticipant;
		this.draftComment = (String) draftComment;
		
	}

	
	
	/*
	 * public auditMeetingRestModel(Object sendNo, Object meetingId, Object
	 * mailDate, Object mailfromtime, Object mailendtime, Object mailsubject, Object
	 * mailDepartment,Object mailparticipant, Object Commentmail) { super();
	 * 
	 * this.sendNo = (String) sendNo; this.meetingId = (String) meetingId;
	 * this.mailDate = (String) mailDate; this.mailfromtime = (String) mailfromtime;
	 * this.mailendtime = (String) mailendtime; this.mailsubject = (String)
	 * mailsubject; this.mailDepartment = (String) mailDepartment;
	 * this.mailparticipant = (String) mailparticipant; this.Commentmail = (String)
	 * Commentmail;
	 * 
	 * }
	 */
	
	
	public String getDraftNo() {
		return draftNo;
	}
	public String getDraftDepartmentid() {
		return draftDepartmentid;
	}
	public void setDraftDepartmentid(String draftDepartmentid) {
		this.draftDepartmentid = draftDepartmentid;
	}
	public void setDraftNo(String draftNo) {
		this.draftNo = draftNo;
	}
	public String getDraftDate() {
		return draftDate;
	}
	public void setDraftDate(String draftDate) {
		this.draftDate = draftDate;
	}
	public String getDraftfromtime() {
		return draftfromtime;
	}
	public void setDraftfromtime(String draftfromtime) {
		this.draftfromtime = draftfromtime;
	}
	public String getDraftendtime() {
		return draftendtime;
	}
	public void setDraftendtime(String draftendtime) {
		this.draftendtime = draftendtime;
	}
	public String getDraftsubject() {
		return draftsubject;
	}
	public void setDraftsubject(String draftsubject) {
		this.draftsubject = draftsubject;
	}
	public String getDraftDepartment() {
		return draftDepartment;
	}
	public void setDraftDepartment(String draftDepartment) {
		this.draftDepartment = draftDepartment;
	}
	public String getDraftparticipant() {
		return draftparticipant;
	}
	public void setDraftparticipant(String draftparticipant) {
		this.draftparticipant = draftparticipant;
	}
	public String getDraftComment() {
		return draftComment;
	}
	public void setDraftComment(String draftComment) {
		this.draftComment = draftComment;
	}
	public List<RestAuditMeetingDocumentModel> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<RestAuditMeetingDocumentModel> documentList) {
		this.documentList = documentList;
	}
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public String getCommentck() {
		return commentck;
	}
	public void setCommentck(String commentck) {
		this.commentck = commentck;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSendNo() {
		return sendNo;
	}
	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}
	public String getMailDate() {
		return mailDate;
	}
	public void setMailDate(String mailDate) {
		this.mailDate = mailDate;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMailfromtime() {
		return mailfromtime;
	}
	public void setMailfromtime(String mailfromtime) {
		this.mailfromtime = mailfromtime;
	}
	public String getMailendtime() {
		return mailendtime;
	}
	public void setMailendtime(String mailendtime) {
		this.mailendtime = mailendtime;
	}
	public String getMailsubject() {
		return mailsubject;
	}
	public void setMailsubject(String mailsubject) {
		this.mailsubject = mailsubject;
	}
	public String getMailDepartment() {
		return mailDepartment;
	}
	public void setMailDepartment(String mailDepartment) {
		this.mailDepartment = mailDepartment;
	}
	public String getMailparticipant() {
		return mailparticipant;
	}
	public void setMailparticipant(String mailparticipant) {
		this.mailparticipant = mailparticipant;
	}
	
	public String getMailComment() {
		return mailComment;
	}
	public void setMailComment(String mailComment) {
		this.mailComment = mailComment;
	}

}
