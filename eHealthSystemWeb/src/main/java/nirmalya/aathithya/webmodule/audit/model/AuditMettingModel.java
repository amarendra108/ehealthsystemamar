package nirmalya.aathithya.webmodule.audit.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuditMettingModel {
	
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

	private String meetingIdMstr;
	private String draftNo;
	private String draftDate;
	private String draftfromtime;
	private String draftendtime;
	private String draftsubject;
	private String draftDepartmentid;
	private String draftDepartment;
	private String draftparticipant;
	private String draftCommentck;
	
	private String sendNo;
	private String mailDate;
	private String mailfromtime;
	private String mailendtime;
	private String mailsubject;
	
	private String mailDepartment;
	private String mailparticipant;
	private String mailComment;
	
	private String emailId;
	
	
	
	
	List<AuditMeetingDocumentModel> documentList;
	
	private String createdBy;
	private String updatedBy;
	private String createdOn;
	private String updatedOn;
	private String status;
	private String action;










	






public String getEmailId() {
		return emailId;
	}






	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}






public String getMeetingIdMstr() {
		return meetingIdMstr;
	}






	public void setMeetingIdMstr(String meetingIdMstr) {
		this.meetingIdMstr = meetingIdMstr;
	}






public String getDraftDepartmentid() {
		return draftDepartmentid;
	}






	public void setDraftDepartmentid(String draftDepartmentid) {
		this.draftDepartmentid = draftDepartmentid;
	}






	public String getDraftCommentck() {
		return draftCommentck;
	}






	public void setDraftCommentck(String draftCommentck) {
		this.draftCommentck = draftCommentck;
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






public String getDraftNo() {
		return draftNo;
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
		return draftCommentck;
	}






	public void setDraftComment(String draftComment) {
		this.draftCommentck = draftComment;
	}






public List<AuditMeetingDocumentModel> getDocumentList() {
		return documentList;
	}






	public void setDocumentList(List<AuditMeetingDocumentModel> documentList) {
		this.documentList = documentList;
	}






public AuditMettingModel() {
	super();
	// TODO Auto-generated constructor stub
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



public String getMeetingId() {
	return meetingId;
}



public void setMeetingId(String meetingId) {
	this.meetingId = meetingId;
}









}