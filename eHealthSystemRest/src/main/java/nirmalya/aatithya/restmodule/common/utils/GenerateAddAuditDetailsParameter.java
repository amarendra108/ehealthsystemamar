package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.audit.model.RestAuditLinkCategoryModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingRestModel;
import nirmalya.aatithya.restmodule.audit.model.RestUpdateDocumentModel;
import nirmalya.aatithya.restmodule.notice.model.RestInitiateNoticeDocumentModel;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;

public class GenerateAddAuditDetailsParameter {
	public static String getAuditManageParam(RestAuditLinkCategoryModel m,int k) {
		String s = "";
		String document=  "";
		String qItem3 = "";
		String qItem1 = "";
		String qItem4 = "";
	
		
		if (k != 0) {
			s = s + "@p_count=" + k + ",";
		}
			if (m.getAuditInitiate() != null && m.getAuditInitiate() != "") {
				s = s + "@p_auditInitiate='" + m.getAuditInitiate() + "',";
			} else {
				s = s + "@p_auditInitiate='',";
			}
			if (m.getAuditType() != null && m.getAuditType() != "") {
				s = s + "@p_auditType='" + m.getAuditType() + "',";
			} else {
				s = s + "@p_auditType='',";
			}
			if (m.getAuditorName() != null && m.getAuditorName() != "") {
				s = s + "@p_auditor='" + m.getAuditorName() + "',";
			} else {
				s = s + "@p_auditor='',";
			}
			if (m.getFinancialYear() != null && m.getFinancialYear() != "") {
				s = s + "@p_financialYear='" + m.getFinancialYear() + "',";
			} else {
				s = s + "@p_financialYear='',";
			}
			if (m.getInitiatedBy() != null && m.getInitiatedBy() != "") {
				s = s + "@p_initiatedBy='" + m.getInitiatedBy() + "',";
			} else {
				s = s + "@p_initiatedBy='',";
			}
			if (m.getInitiatedDate() != null && m.getInitiatedDate() != "") {
				s = s + "@p_initiatedDate='" + m.getInitiatedDate() + "',";
				} else {
				s = s + "@p_initiatedDate=null,";
			}
			if (m.getLetterNo() != null && m.getLetterNo() != "") {
				s = s + "@p_letterNo='" + m.getLetterNo() + "',";
			} else {
				s = s + "@p_letterNo='',";
			}
			if (m.getRegionalManager() != null && m.getRegionalManager() != "") {
				s = s + "@p_RegionalManager='" + m.getRegionalManager() + "',";
			} else {
				s = s + "@p_RegionalManager='',";
			}
			if (m.getConcernedFinance() != null && m.getConcernedFinance() != "") {
				s = s + "@p_ConcernedFinance='" + m.getConcernedFinance() + "',";
			} else {
				s = s + "@p_ConcernedFinance='',";
			}

			if (m.getQuarter() != null && m.getQuarter() != "") {
				s = s + "@p_Quarter='" + m.getQuarter() + "',";
			} else {
				s = s + "@p_Quarter='',";
			}
			if (m.getRegion() != null && m.getRegion() != "") {
				s = s + "@p_Region='" + m.getRegion() + "',";
			} else {
				s = s + "@p_Region='',";
			}
			
			if (m.getSummary() != null && m.getSummary() != "") {
				s = s + "@p_summary='" + m.getSummary() + "',";
			} else {
				s = s + "@p_summary='',";
			}

			if (m.getSubject() != null && m.getSubject() != "") {
				s = s + "@p_subject='" + m.getSubject() + "',";
			} else {
				s = s + "@p_subject='',";
			}
			if (m.getCreatedBy() != null && m.getCreatedBy() != "") {
				s = s + "@p_createdBy='" + m.getCreatedBy() + "',";
			} else {
				s = s + "@p_createdBy='',";
			}
			if (m.getDept() != null && m.getDept() != "") {
				s = s + "@p_dept='" + m.getDept() + "',";
			}  else {
				String dept = "DPT002";
				s = s + "@p_dept='" + dept + "',";
			}
			if (m.getSection() != null && m.getSection() != "") {
				s = s + "@p_section='" + m.getSection() + "',";
			} else {
				s = s + "@p_section='',";
			}
			
			if (m.getDeptHead() != null && m.getDeptHead() != "") {
				s = s + "@p_deptHead='" + m.getDeptHead() + "',";
			} else {
				s = s + "@p_deptHead='',";
			}

			if (m.getStartDate() != null && m.getStartDate() != "") {
				s = s + "@p_startDate='" + m.getStartDate() + "',";
			} else {
				s = s + "@p_startDate=null,";
			}
			if (m.getEndDate() != null && m.getEndDate() != "") {
				s = s + "@p_endDate='" + m.getEndDate() + "',";
			} else {
				s = s + "@p_endDate=null,";
			}
		
			// Concerned Auditee add
			
			if (m.getConcernedAuditee().size() > 0) {
				for (int i = 0; i < m.getConcernedAuditee().size(); i++) {
					qItem4 = qItem4 + "(@p_auditType,@p_auditInitiate,\"" + m.getConcernedAuditee().get(i) + "\",\""
							+ m.getCreatedBy() + "\"),";
				}
			}
	
		if (qItem4.length() > 0) {
			qItem4 = qItem4.substring(0, qItem4.length() - 1);
			s = s + "@p_ConAuditee='" + qItem4 + "',";
		}
		s = s + "@p_ConAuditee='',";


		if (m.getDocumentList().size() > 0) {

			for (int i = 0; i < m.getDocumentList().size(); i++) {

				document += "(@p_auditInitiate,\"" + m.getDocumentList().get(i).getDocumnentName() + "\",\""
						+ m.getDocumentList().get(i).getFileName() + "\",\"" + m.getDocumentList().get(i).getCommentName()
						+ "\"),";

			}
			if (document.length() > 0) {
				document = document.substring(0, document.length() - 1);
				s = s + "@p_auditDocuments='" + document + "',";
			}
		}
		String deptHead = "";
		String aspemail = "";
		String listProduct = "";
		
		for (int i = 0; i < m.getConcernedAuditee().size(); i++) {
			aspemail = m.getConcernedAuditee().get(i).replace("(", "").replace(")", "");
			listProduct = listProduct + "'" + m.getConcernedAuditee().get(i) + "',";
		}

		if (listProduct != "") {
			aspemail = listProduct.substring(0, listProduct.length() - 1);
		}
		String asp1 = "";
		if (aspemail != "") {
			asp1 = "@p_peopleEmail=\"(" + aspemail + ")\"";
		}
		if (s != "") {
			s = s.substring(0, s.length() - 1);
			if (asp1 != "") {
				s = "SET " + s + "," + asp1 + ";";
			} else {
				s = "SET " + s + ";";
			}
		}
		System.out.println("GenParam@@@@@@@@"+s);
		return s;
	}
	

	
	public static String getSaveMailParams(List<RestAuditLinkCategoryModel> initiateNoticeModel) {
		String s = "";
		String document=  "";
		String auditInitiate = "";
		String manageAuditNo = "";
		String mauditDate = "";
		String toEmail = "";
		String toCc = "";
		String mailsubject = "";
		String comment="";
		String createdBy = "";
		String participantDept="";
		
		for (RestAuditLinkCategoryModel m : initiateNoticeModel) {
			auditInitiate = m.getAuditInitiate();
			manageAuditNo = m.getManageAuditNo();
			mauditDate = m.getMauditDate();
			toEmail =m.getToEmail();
			toCc = m.getToCc();
			mailsubject = m.getMailsubject();
			comment = m.getComment();
			createdBy = m.getCreatedBy();
			//participantDept=m.getParticipantDept();
			
		}
		s = s + "@p_manageAuditNo='" + manageAuditNo + "',";
		s = s + "@p_auditInitiate='" + auditInitiate + "',";
		s = s + "@p_mauditDate='" + mauditDate + "',";
		s = s + "@p_toEmail='" + toEmail + "',";
		s = s + "@p_toCc='" + toCc + "',";
		s = s + "@p_mailsubject='" + mailsubject + "',";
		s = s + "@p_comment='" + comment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		//s = s + "@p_participantDept='" + participantDept + "',";
		
		
		  for (RestUpdateDocumentModel a :
		  initiateNoticeModel.get(0).getDocumentList()) { document = document +
		  "(@p_manageAuditNo,@p_auditInitiate,\"" + a.getDocumnentName() + "\",\"" +
		  a.getFileName() + "\",@p_createdBy),"; } if(!document.isEmpty()) { document =
		  document.substring(0, document.length() - 1); s = s + "@p_documents='" +
		  document + "',"; }
		 
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}
	
	public static String getSaveDraftParamCommense(List<RestAuditLinkCategoryModel>  initiateNoticeModel) {
		String s = "";
		String document=  "";
		String auditInitiate = "";
		String draftNo = "";
		String draftPersonTo = "";
		String draftPersonCc = "";
		String draftSubject = "";
		String draftComment = "";
		String createdBy = "";
		//String participantDept="";
		
		for (RestAuditLinkCategoryModel m : initiateNoticeModel) {
			draftNo = m.getDraftNo();
			auditInitiate = m.getAuditInitiate();
			
			draftPersonTo = m.getDraftpersonTo();
			draftPersonCc =m.getDraftpersonCc();
			draftSubject = m.getDraftSubject();
			draftComment = m.getDraftComment();
			createdBy = m.getCreatedBy();
			//participantDept=m.getParticipantDept();
			
		}
		s = s + "@p_draftno='" + draftNo + "',";
		s = s + "@p_auditInitiate='" + auditInitiate + "',";
		s = s + "@p_personTo='" + draftPersonTo + "',";
		s = s + "@p_personCc='" + draftPersonCc + "',";
		s = s + "@p_draftSub='" + draftSubject + "',";
		s = s + "@p_draftComment='" + draftComment + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		//s = s + "@p_participantDept='" + participantDept + "',";
		
		for (RestUpdateDocumentModel a : initiateNoticeModel.get(0).getDocumentList()) {
			document = document + "(@p_draftno,@p_auditInitiate,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
		}
		if(!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_auditDocuments='" + document + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		System.out.println("GenerateParam"+s);
		return s;
	}
}
