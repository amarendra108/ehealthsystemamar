package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;
import nirmalya.aatithya.restmodule.notice.model.RestInitiateNoticeDocumentModel;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;

public class GenerateNoticeInitiateParameter {

	public static String getNoticeinitateParam(List<RestIntiateNoticeModel> initiateNoticeModel) {
		String s = "";
		String document=  "";
		String noticeId = "";
		String noticeIdMstr = "";
		String noticeNo = "";
		String startDate = "";
		String endDate = "";
		String noticeFrom = "";
		String comment = "";
		String createdBy = "";
		for (RestIntiateNoticeModel m : initiateNoticeModel) {
			noticeId = m.getNoticeId();
			//noticeIdMstr = m.getNoticeIdMstr();
			noticeIdMstr = m.getNoticeType();
			noticeNo = m.getNoticeNo();
			startDate =m.getStartDate();
			endDate = m.getEndDate();
			noticeFrom = m.getNoticeFrom();
			comment = m.getComment();
			createdBy = m.getCreatedBy();
			
		}
		s = s + "@p_lastnoticeId='" + noticeId + "',";
		s = s + "@p_noticetypeId='" + noticeIdMstr + "',";
		s = s + "@p_noticeno='" + noticeNo + "',";
		s = s + "@p_startDate='" + startDate + "',";
		s = s + "@p_dueDate='" + endDate + "',";
		s = s + "@p_noticefrom='" + noticeFrom + "',";
		s = s + "@p_comment='" + comment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		
		
		for (RestInitiateNoticeDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
			document = document + "(@p_lastnoticeId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
		}
		if(!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_noticeDocuments='" + document + "',";
		}
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}
	
	//Save Draft Details
	
	public static String getSaveDraftParam(List<RestIntiateNoticeModel>  initiateNoticeModel) {
		String s = "";
		String document=  "";
		String noticeId = "";
		String draftNo = "";
		String draftPersonTo = "";
		String draftPersonCc = "";
		String draftSubject = "";
		String draftComment = "";
		String createdBy = "";
		String participantDept="";
		
		for (RestIntiateNoticeModel m : initiateNoticeModel) {
			noticeId = m.getNoticeId();
			draftNo = m.getDraftNo();
			draftPersonTo = m.getDraftpersonTo();
			draftPersonCc =m.getDraftpersonCc();
			draftSubject = m.getDraftSubject();
			draftComment = m.getDraftComment();
			createdBy = m.getCreatedBy();
			participantDept=m.getParticipantDept();
			
		}
		s = s + "@p_draftno='" + draftNo + "',";
		s = s + "@p_noticeId='" + noticeId + "',";
		s = s + "@p_personTo='" + draftPersonTo + "',";
		s = s + "@p_personCc='" + draftPersonCc + "',";
		s = s + "@p_draftSub='" + draftSubject + "',";
		s = s + "@p_draftComment='" + draftComment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		s = s + "@p_participantDept='" + participantDept + "',";
		
		for (RestInitiateNoticeDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
			document = document + "(@p_draftno,@p_noticeId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
		}
		if(!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_noticeDocuments='" + document + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		System.out.println("GenerateParam"+s);
		return s;
	}
	
	//Save Sent Notice Details
	

	public static String getSaveMailParam(List<RestIntiateNoticeModel> initiateNoticeModel) {
		String s = "";
		String document=  "";
		String noticeId = "";
		String sendNo = "";
		String sendPersonTo = "";
		String sendPersonCc = "";
		String sendSubject = "";
		String sendComment = "";
		String createdBy = "";
		String participantDept="";
		
		for (RestIntiateNoticeModel m : initiateNoticeModel) {
			noticeId = m.getNoticeId();
			sendNo = m.getSendNo();
			sendPersonTo = m.getSendPersonTo();
			sendPersonCc =m.getSendPersonCc();
			sendSubject = m.getSendSubject();
			sendComment = m.getSendComment();
			createdBy = m.getCreatedBy();
			participantDept=m.getParticipantDept();
			
		}
		s = s + "@p_sendId='" + sendNo + "',";
		s = s + "@p_noticeId='" + noticeId + "',";
		s = s + "@p_sendPersonTo='" + sendPersonTo + "',";
		s = s + "@p_sendPersonCc='" + sendPersonCc + "',";
		s = s + "@p_sendSubject='" + sendSubject + "',";
		s = s + "@p_sendComment='" + sendComment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		s = s + "@p_participantDept='" + participantDept + "',";
		
		for (RestInitiateNoticeDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
			document = document + "(@p_sendId,@p_noticeId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
		}
		if(!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_noticeDocuments='" + document + "',";
		}
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}
	//Add meeting details
	public static String getMeetingScheduleParam(List<RestIntiateNoticeModel> initiateNoticeModel) {
		String s = "";
		String document=  "";
		String noticeId = "";
		String meetingNo = "";
		String meetingDate = "";
		String meetingComment = "";
		String startTime = "";
		String endTime = "";
		String meetingSubject = "";
		String participantDept = "";
		String participants = "";
		String createdBy = "";
		
		for (RestIntiateNoticeModel m : initiateNoticeModel) {
			noticeId = m.getNoticeId();
			meetingNo = m.getMeetingNo();
			meetingDate =m.getMeetingDate();
			startTime = m.getStartTime();
			endTime = m.getEndTime();
			meetingSubject = m.getMeetingComment();
			participantDept = m.getParticipantDept();
			participants = m.getParticipants();
			meetingComment= m.getMeetingComment();
			createdBy=m.getCreatedBy();
			
		}
		s = s + "@p_noticeId='" + noticeId + "',";
		s = s + "@p_meetingNo='" + meetingNo + "',";
		s = s + "@p_meetingDate='" + meetingDate + "',";
		s = s + "@p_startTime='" + startTime + "',";
		s = s + "@p_endTime='" + endTime + "',";
		s = s + "@p_meetingSubject='" + meetingSubject + "',";
		s = s + "@p_participantDept='" + participantDept + "',";
		s = s + "@p_participants='" + participants + "',";
		s = s + "@p_meetingComment='" + meetingComment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		
		for (RestInitiateNoticeDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
			document = document + "(@p_meetingNo,@p_noticeId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
		}
		if(!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_meetingDocuments='" + document + "',";
		}
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}
	
	
	
	public static String getMeetingDetailsParam(List<RestIntiateNoticeModel> initiateNoticeModel) {
		String s = "";
		String document=  "";
		String meetingNo = "";
		String meetingDate = "";
		String meetingComment = "";
		String startTime = "";
		String endTime = "";
		String meetingSubject = "";
		String participantDept = "";
		String participants = "";
		String createdBy = "";
		
		for (RestIntiateNoticeModel m : initiateNoticeModel) {
			meetingNo = m.getMeetingNo();
			meetingDate =m.getMeetingDate();
			startTime = m.getStartTime();
			endTime = m.getEndTime();
			meetingSubject = m.getMeetingSubject();
			participantDept = m.getParticipantDept();
			participants = m.getParticipants();
			meetingComment= m.getMeetingComment();
			createdBy=m.getCreatedBy();
			
			
		}
		s = s + "@p_meetingId='" + meetingNo + "',";
		s = s + "@p_meetingDate='" + meetingDate + "',";
		s = s + "@p_startTime='" + startTime + "',";
		s = s + "@p_endTime='" + endTime + "',";
		s = s + "@p_meetingSubject='" + meetingSubject + "',";
		s = s + "@p_participantDept='" + participantDept + "',";
		s = s + "@p_participants='" + participants + "',";
		s = s + "@p_meetingComment='" + meetingComment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		
		for (RestInitiateNoticeDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
			document = document + "(@p_meetingId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
		}
		if(!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_meetingDocuments='" + document + "',";
		}
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
System.out.println("Generate Meeting Params"+s);
		return s;
	}
	
}
