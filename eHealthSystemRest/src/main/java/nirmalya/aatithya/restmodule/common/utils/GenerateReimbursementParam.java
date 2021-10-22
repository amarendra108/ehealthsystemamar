package nirmalya.aatithya.restmodule.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//import nirmalya.aathithya.webmodule.employee.model.ReimbursementDocumentModel;
import nirmalya.aatithya.restmodule.employee.model.ReimbursementModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRequisitionModel;

public class GenerateReimbursementParam {

	
	public static String getReimbursementParam(ReimbursementModel reimbursementModel) {

		String s = "";

		if (reimbursementModel.getReimbursementReqId() != null && reimbursementModel.getReimbursementReqId() != "") {
			s = s + "@p_reimbursementReqId='" + reimbursementModel.getReimbursementReqId() + "',";
		}
		if (reimbursementModel.getRequisitionId() != null && reimbursementModel.getRequisitionId() != "") {
			s = s + "@p_requisitionId='" + reimbursementModel.getRequisitionId() + "',";
		}
		if (reimbursementModel.getEmpName() != null || reimbursementModel.getEmpName() != "") {
			s = s + "@p_empName='" + reimbursementModel.getEmpName() + "',";
		}
		if (reimbursementModel.getPlaceName() != null && reimbursementModel.getPlaceName() != "") {
			s = s + "@p_placeName='" + reimbursementModel.getPlaceName() + "',";
		}

		if (reimbursementModel.getFromDate() != null && reimbursementModel.getFromDate() != "") {
			s = s + "@p_fromDate='" + reimbursementModel.getFromDate() + "',";
		}

		if (reimbursementModel.getToDate() != null || reimbursementModel.getToDate() != "") {
			s = s + "@p_toDate='" + reimbursementModel.getToDate() + "',";
		}

		if (reimbursementModel.getPurpose() != null && reimbursementModel.getPurpose() != "") {
			s = s + "@p_purpose='" + reimbursementModel.getPurpose() + "',";
		}
		if (reimbursementModel.getAdvanceReq() != null) {
			s = s + "@p_advance='" + reimbursementModel.getAdvanceReq() + "',";
		}
		
		if (reimbursementModel.getAdvanceAmount() != null) {
			s = s + "@p_advanceAmount='" + reimbursementModel.getAdvanceAmount() + "',";
		}
		if (reimbursementModel.getAamttPaid() != null) {
			s = s + "@p_advreq='" + reimbursementModel.getAamttPaid() + "',";
		}

		
		

		if (reimbursementModel.getCreatedBy() != null || reimbursementModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + reimbursementModel.getCreatedBy() + "',";
		}
		if (reimbursementModel.getModule() != null && reimbursementModel.getModule() != "") {
			s = s + "@p_module='" + reimbursementModel.getModule() + "',";
		}
		if (reimbursementModel.getComponent() != null && reimbursementModel.getComponent() != "") {
			s = s + "@p_component='" + reimbursementModel.getComponent() + "',";
		}
		if (reimbursementModel.getSubcomponent()!= null && reimbursementModel.getSubcomponent() != "") {
			s = s + "@p_subComponent='" + reimbursementModel.getSubcomponent() + "',";
		}
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}
	
	
	
	public static String getReimbursementParamTravel(ReimbursementModel reimbursementModel) {

		String s = "";
		if (reimbursementModel.getSlNo() != null && reimbursementModel.getSlNo() != "") {
			s = s + "@p_reimbursementReqIdss='" + reimbursementModel.getSlNo() + "',";
		}
		
		  if (reimbursementModel.getReimbursementReqId() != null &&
		  reimbursementModel.getReimbursementReqId() != "") { s = s +
		  "@p_reimbursementReqIds='" + reimbursementModel.getReimbursementReqId() +
		  "',"; }
		 

		if (reimbursementModel.getReimTypeId() != null && reimbursementModel.getReimTypeId() != "") {
			s = s + "@p_reimTypeId='" + reimbursementModel.getReimTypeId() + "',";
		}

		
		if (reimbursementModel.getReimPolicyId() != null || reimbursementModel.getReimPolicyId() != "") {
			s = s + "@p_reimPolicyId='" + reimbursementModel.getReimPolicyId() + "',";
		}

		

		
		if (reimbursementModel.getExpenseDate() != null || reimbursementModel.getExpenseDate() != "") {
			s = s + "@p_expenseDate='" + reimbursementModel.getExpenseDate() + "',";
		}

		if (reimbursementModel.getExpenseDesc() != null && reimbursementModel.getExpenseDesc() != "") {
			s = s + "@p_expenseDesc='" + reimbursementModel.getExpenseDesc() + "',";
		}
		if (reimbursementModel.getExpenseAmount() != null) {
			s = s + "@p_expenseAmount='" + reimbursementModel.getExpenseAmount() + "',";
		}

		if (reimbursementModel.getReferenceNo() != null && reimbursementModel.getReferenceNo() != "") {
			s = s + "@p_referenceNo='" + reimbursementModel.getReferenceNo() + "',";
		}
		if (reimbursementModel.getDocumentName() != null || reimbursementModel.getDocumentName() != "") {
			s = s + "@p_documentName='" + reimbursementModel.getDocumentName() + "',";
		}
		if (reimbursementModel.getDocName() != null || reimbursementModel.getDocName() != "") {
			s = s + "@p_docName='" + reimbursementModel.getDocName() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}
	
	public static String getApproveParam(InventoryRequisitionModel restItemRequisitonModel) {
		String[] userIds = restItemRequisitonModel.getReqId().split(",");
		String s = "";
		String litem = "";
		String act = "";
		for (String a : userIds) {
			litem = litem + "\"" + a + "\",";
		}
		litem = litem.substring(0, litem.length() - 1);
		litem = "(" + litem + ")";
		s = s + "@p_reqIds='" + litem + "',";

		for (String a : userIds) {

			act = act + "(\"" + restItemRequisitonModel.getModuleId() + "\",\""
					+ restItemRequisitonModel.getComponentId() + "\",\"" + restItemRequisitonModel.getSubComponentId()
					+ "\",\"" + a + "\",\"" + "Approve Requisition" + "\",\"" + restItemRequisitonModel.getCreatedBy()
					+ "\"),";

		}
		act = act.substring(0, act.length() - 1);

		s = s + "@p_actSubQuery='" + act + "',";
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

}
