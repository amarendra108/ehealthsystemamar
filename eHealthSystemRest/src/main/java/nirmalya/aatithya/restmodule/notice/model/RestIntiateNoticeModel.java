package nirmalya.aatithya.restmodule.notice.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestIntiateNoticeModel {
	private String noticeId;
	private String createdOn;
	private String noticeType;
	private String noticeNo;
	private String startDate;
	private String endDate;
	private String noticeFrom;
	private String comment;
	private String noticeIdMstr;
	private String createdBy;
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

	private List<RestInitiateNoticeDocumentModel> documentList;

	public RestIntiateNoticeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	// AutoSeach
	public RestIntiateNoticeModel(Object noticeIdMstr, Object noticeType, Object noticeNo) {
		super();
		this.noticeType = (String) noticeType;
		this.noticeNo = (String) noticeNo;
		this.noticeIdMstr = (String) noticeIdMstr;
	}

	// Add
	public RestIntiateNoticeModel(Object noticeId, Object noticeType, Object noticeIdMstr, Object noticeNo,
			Object startDate, Object endDate, Object comment, Object createdOn, Object createdBy, Object noticeFrom) {
		super();
		this.noticeId = (String) noticeId;
		this.noticeIdMstr = (String) noticeIdMstr;
		this.noticeType = (String) noticeType;
		this.noticeNo = (String) noticeNo;
		this.startDate = (String) startDate;
		this.endDate = (String) endDate;
		this.createdOn = (String) createdOn;
		this.noticeFrom = (String) noticeFrom;
		this.comment = (String) comment;
		this.createdBy = (String) createdBy;
	}

	public RestIntiateNoticeModel(Object draftNo, Object noticeId, Object draftSubject, Object draftComment,
			Object draftDate, Object draftpersonTo, Object draftpersonCc) {
		super();

		this.draftNo = (String) draftNo;
		this.noticeId = (String) noticeId;
		this.draftSubject = (String) draftSubject;
		this.draftComment = (String) draftComment;
		this.draftDate = (String) draftDate;
		this.draftpersonTo = (String) draftpersonTo;
		this.draftpersonCc = (String) draftpersonCc;
	}

//Schedule Meeting 
	public RestIntiateNoticeModel(Object meetingNo, Object noticeId, Object meetingDate, Object startTime,
			Object endTime, Object meetingSubject, Object participantDept, Object participants, Object meetingComment) {
		super();

		this.meetingNo = (String) meetingNo;
		this.noticeId = (String) noticeId;
		this.meetingDate = (String) meetingDate;
		this.startTime = (String) startTime;
		this.endTime = (String) endTime;
		this.meetingSubject = (String) meetingSubject;
		this.participantDept = (String) participantDept;
		this.participants = (String) participants;
		this.meetingComment = (String) meetingComment;
	}

	// New Meeting Details
	public RestIntiateNoticeModel(Object meetingNo, Object meetingDate, Object startTime, Object endTime,
			Object participantDept, Object createdBy, Object participants, Object meetingFileName) {
		super();

		this.meetingNo = (String) meetingNo;
		this.meetingDate = (String) meetingDate;
		this.startTime = (String) startTime;
		this.endTime = (String) endTime;
		this.participantDept = (String) participantDept;
		this.participants = (String) participants;
		this.createdBy = (String) createdBy;
		this.meetingFileName = (String) meetingFileName;
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

	public List<RestInitiateNoticeDocumentModel> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<RestInitiateNoticeDocumentModel> documentList) {
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

	public String getMeetingFileName() {
		return meetingFileName;
	}

	public void setMeetingFileName(String meetingFileName) {
		this.meetingFileName = meetingFileName;
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
