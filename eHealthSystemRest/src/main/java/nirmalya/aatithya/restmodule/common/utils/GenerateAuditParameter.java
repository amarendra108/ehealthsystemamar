package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingRestModel;

public class GenerateAuditParameter {

	public static String getAuditParam(RestAuditMeetingRestModel audit) {

		String s = "";
		String document = "";
		if (audit.getMeetingId() != null && audit.getMeetingId() != "") {
			s = s + "@p_mettingId='" + audit.getMeetingId() + "',";
		}
		if (audit.getDate() != null && audit.getDate() != "") {
			s = s + "@p_Date='" + audit.getDate() + "',";
		}
		if (audit.getStartTime() != null && audit.getStartTime() != "") {
			s = s + "@p_ftartTime='" + audit.getStartTime() + "',";
		}
		if (audit.getEndTime() != null && audit.getEndTime() != "") {
			s = s + "@p_EndTime='" + audit.getEndTime() + "',";
		}
		if (audit.getSubject() != null && audit.getSubject() != "") {
			s = s + "@p_Subject='" + audit.getSubject() + "',";
		}
		if (audit.getDepartmentName() != null && audit.getDepartmentName() != "") {
			s = s + "@p_DepartmentName='" + audit.getDepartmentName() + "',";
		}
		if (audit.getParticipantName() != null && audit.getParticipantName() != "") {
			s = s + "@p_ParticipantName='" + audit.getParticipantName() + "',";
		}
		if (audit.getCommentck() != null && audit.getCommentck() != "") {
			s = s + "@p_Commentck='" + audit.getCommentck() + "',";
		}

		if (audit.getCreatedBy() != null && audit.getCreatedBy() != "") {
			s = s + "@p_createdby='" + audit.getCreatedBy() + "',";
		}

		// address
		for (RestAuditMeetingDocumentModel a : audit.getDocumentList()) {
			document = document + "(@p_mettingId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName()
					+ "\",@p_createdBy),";
		}
		if (!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_documents='" + document + "',";
		}
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

	public static String getSaveDraftParam(List<RestAuditMeetingRestModel> initiateNoticeModel) {
		String s = "";
		String document = "";
		
		String draftNo = "";
		String meetingId = "";
		String draftDate = "";
		String draftfromtime = "";
		String draftotime = "";
		String draftSubject = "";
		String draftdeparment = "";
		String draftparticipant = "";
		String draftComment = "";
		String createdBy = "";
		for (RestAuditMeetingRestModel m : initiateNoticeModel) {
			
			draftNo = m.getDraftNo();
			meetingId = m.getMeetingId();
			draftDate = m.getDraftDate();
			draftfromtime = m.getDraftfromtime();
			draftotime = m.getDraftendtime();

			draftSubject = m.getDraftsubject();
			draftdeparment = m.getDraftDepartment();

			draftparticipant = m.getDraftparticipant();
			draftComment = m.getDraftComment();
			createdBy = m.getCreatedBy();

		}
		s = s + "@p_draftno='" + draftNo + "',";
		s = s + "@p_mettingId='" + meetingId + "',";
		s = s + "@p_draftDate='" + draftDate + "',";
		s = s + "@p_draftfromtime='" + draftfromtime + "',";
		s = s + "@p_draftotime='" + draftotime + "',";

		s = s + "@p_draftSubject='" + draftSubject + "',";
		s = s + "@p_draftdeparment='" + draftdeparment + "',";
		s = s + "@p_raftparticipan='" + draftparticipant + "',";
		s = s + "@p_draftComment='" + draftComment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";

		for (RestAuditMeetingDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
			document = document + "(@p_draftno,@p_mettingId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName()
					+ "\",@p_createdBy),";
		}
		if (!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_documents='" + document + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		System.out.println("GenerateParam" + s);
		return s;
	}
	//Save Sent Notice Details
	

		public static String getSaveMailParam(List<RestAuditMeetingRestModel> initiateNoticeModel) {
			String s = "";
			String document=  "";
			String meetingId = "";
			String sendNo = "";
			
			String emailId = "";
			String senddate = "";
			String sendfromtime = "";
			String sendtotime = "";
			String sendsubject = "";
			String senddept = "";
			String sendpart = "";
			String sendcommmt = "";
		
			String createdBy = "";
			for (RestAuditMeetingRestModel m : initiateNoticeModel) {
				meetingId = m.getMeetingId();
				sendNo = m.getSendNo();
				emailId = m.getEmailId();
				senddate = m.getMailDate();
				sendfromtime =m.getMailfromtime();
				sendtotime = m.getMailendtime();
				sendsubject = m.getMailsubject();
				senddept =m.getMailDepartment();
				sendpart = m.getMailparticipant();
				sendcommmt = m.getMailComment();
				createdBy = m.getCreatedBy();
				
			}
			s = s + "@p_sendId='" + sendNo + "',";
			s = s + "@p_mettingId='" + meetingId + "',";
			s = s + "@p_emailId='" + emailId + "',";
			s = s + "@p_sendDate='" + senddate + "',";
			s = s + "@p_sendfromtime='" + sendfromtime + "',";
			s = s + "@p_sendtotime='" + sendtotime + "',";

			s = s + "@p_sendSubject='" + sendsubject + "',";
			s = s + "@p_senddeparment='" + senddept + "',";
			s = s + "@p_sendticipan='" + sendpart + "',";
			s = s + "@p_sendComment='" + sendcommmt + "',";
			s = s + "@p_createdBy='" + createdBy + "',";
			
			for (RestAuditMeetingDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
				document = document + "(@p_sendId,@p_mettingId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
			}
			if(!document.isEmpty()) {
				document = document.substring(0, document.length() - 1);
				s = s + "@p_documents='" + document + "',";
			}
			

			if (s != "") {
				s = s.substring(0, s.length() - 1);

				s = "SET " + s + ";";
			}

			return s;
		}
}