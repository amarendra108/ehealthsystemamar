package nirmalya.aathithya.webmodule.notice.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IntiateNoticeModel {

	private String noticeId;
	private String createdOn;
	private String noticeType;
	private String noticeNo;
	private String startDate;
	private String endDate;
	private String noticeFrom;
	private String comment;
	private String createdBy;
	private String noticeIdMstr;
	private String draftNo;
	private String draftpersonTo;
	private String draftpersonCc;
	private String draftSubject;
	private String draftComment;
	private String draftDate;
	private String sendNo;
	private String sendPersonTo;
	private String sendPersonCc;
	private String sendParentPersonTo;
	private String sendPaerentPersonCc;
	private String sendSubject;
	private String sendComment;
	private String meetingNo;
	private String meetingDate;
	private String startTime;
	private String endTime;
	private String meetingSubject;
	private String participantDept;
	private String participants;
	private String meetingComment;
	private String meetingFileName;

	private List<InitiateNoticeDocumentModel> documentList;

	
	public String getMeetingFileName() {
		return meetingFileName;
	}

	public void setMeetingFileName(String meetingFileName) {
		this.meetingFileName = meetingFileName;
	}

	public String getNoticeIdMstr() {
		return noticeIdMstr;
	}

	public void setNoticeIdMstr(String noticeIdMstr) {
		this.noticeIdMstr = noticeIdMstr;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
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

	public String getNoticeFrom() {
		return noticeFrom;
	}

	public void setNoticeFrom(String noticeFrom) {
		this.noticeFrom = noticeFrom;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<InitiateNoticeDocumentModel> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<InitiateNoticeDocumentModel> documentList) {
		this.documentList = documentList;
	}

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

	public String getSendNo() {
		return sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	public String getSendPersonTo() {
		return sendPersonTo;
	}

	public void setSendPersonTo(String sendPersonTo) {
		this.sendPersonTo = sendPersonTo;
	}

	public String getSendPersonCc() {
		return sendPersonCc;
	}

	public void setSendPersonCc(String sendPersonCc) {
		this.sendPersonCc = sendPersonCc;
	}

	public String getSendParentPersonTo() {
		return sendParentPersonTo;
	}

	public void setSendParentPersonTo(String sendParentPersonTo) {
		this.sendParentPersonTo = sendParentPersonTo;
	}

	public String getSendPaerentPersonCc() {
		return sendPaerentPersonCc;
	}

	public void setSendPaerentPersonCc(String sendPaerentPersonCc) {
		this.sendPaerentPersonCc = sendPaerentPersonCc;
	}

	public String getSendSubject() {
		return sendSubject;
	}

	public void setSendSubject(String sendSubject) {
		this.sendSubject = sendSubject;
	}

	public String getSendComment() {
		return sendComment;
	}

	public void setSendComment(String sendComment) {
		this.sendComment = sendComment;
	}

	public String getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
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

	public String getMeetingSubject() {
		return meetingSubject;
	}

	public void setMeetingSubject(String meetingSubject) {
		this.meetingSubject = meetingSubject;
	}

	public String getParticipantDept() {
		return participantDept;
	}

	public void setParticipantDept(String participantDept) {
		this.participantDept = participantDept;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getMeetingComment() {
		return meetingComment;
	}

	public void setMeetingComment(String meetingComment) {
		this.meetingComment = meetingComment;
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
