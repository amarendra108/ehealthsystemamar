package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.audit.model.RestAuditDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestUpdateDocumentModel;



public class GenerateAuditDocumentParameter {
	public static String getAuditManageParam(List<RestAuditDocumentModel> auditDocModel) {
		String s = "";
		String document=  "";
		String auditUploadId = "";
		String auditType = "";
		String financialYear = "";
		String quater=  "";
		String auditFolder = "";
		String createdBy = "";
		
		
		for (RestAuditDocumentModel m : auditDocModel) {
			auditUploadId = m.getAuditUploadId();
			auditType = m.getAuditTypeId();
			financialYear = m.getFinancialYear();
			quater = m.getQuater();
			auditFolder =m.getAuditFolder();
			
			createdBy = m.getCreatedBy();
			
		}
		s = s + "@p_auditUploadId='" + auditUploadId + "',";
		s = s + "@p_audittype='" + auditType + "',";
		s = s + "@p_financialYear='" + financialYear + "',";
		s = s + "@p_quater='" + quater + "',";
		s = s + "@p_auditFolder='" + auditFolder + "',";
		
        s = s + "@p_createdBy='" + createdBy + "',";
		
		
		for (RestUpdateDocumentModel a : auditDocModel.get(0).getDocumentList()) {
			document = document + "(@p_auditUploadId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",\""+a.getCommentName()+"\"),";
			
		}
		if(!document.isEmpty()) {
			document = document.substring(0, document.length() - 1);
			s = s + "@p_auditDocuments='" + document + "',";
			
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
			System.out.println("GenParam@@@@@@@@"+s);
		}

		return s;
	}
}
