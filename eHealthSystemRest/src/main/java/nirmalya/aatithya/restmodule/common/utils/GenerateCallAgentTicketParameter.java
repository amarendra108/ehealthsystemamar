package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.ticket.model.CallAgentTicketRestModel;

public class GenerateCallAgentTicketParameter {
	/**
	 * returns parameter set for GenerateCallAgentTicketParameter
	 **/
	public static String getAddCallAgentTicket(CallAgentTicketRestModel form) {
		String s = "";

		if (form.getCustomerId() != null && form.getCustomerId() != "") {
			s = s + "@p_hidCustomer='" + form.getCustomerId() + "',";
		}

		if (form.getPhoneNo() != null && form.getPhoneNo() != "") {
			s = s + "@p_telephone='" + form.getPhoneNo() + "',";
		}

		if (form.getCustomerName() != null && form.getCustomerName() != "") {
			s = s + "@p_customerName='" + form.getCustomerName() + "',";
		}

		if (form.getCustomerLocation() != null && form.getCustomerLocation() != "") {
			s = s + "@p_Customerlocation='" + form.getCustomerLocation() + "',";
		}
		if (form.getIndustrialType() != null && form.getIndustrialType() != "") {
			s = s + "@p_industrialType='" + form.getIndustrialType() + "',";
		}
		if (form.getCustomerType() != null && form.getCustomerType() != "") {
			s = s + "@p_customerType='" + form.getCustomerType() + "',";
		}
		if (form.getAlternatePhoneNo() != null && form.getAlternatePhoneNo() != "") {
			s = s + "@p_alternatePhone='" + form.getAlternatePhoneNo() + "',";
		}
		if (form.getEmail() != null && form.getEmail() != "") {
			s = s + "@p_email='" + form.getEmail() + "',";
		}
		if (form.getTicketId() != null && form.getTicketId() != "") {
			s = s + "@p_ticketId='" + form.getTicketId() + "',";
		}
		if (form.getCallQueue() != null && form.getCallQueue() != "") {
			s = s + "@p_callqueue='" + form.getCallQueue() + "',";
		}
		if (form.getCallReason() != null && form.getCallReason() != "") {
			s = s + "@p_callreason='" + form.getCallReason() + "',";
		}
		if (form.getProductType() != null && form.getProductType() != "") {
			s = s + "@p_productType='" + form.getProductType() + "',";
		}
		if (form.getCommentBox() != null && form.getCommentBox() != "") {
			s = s + "@p_comment='" + form.getCommentBox() + "',";
		}
		if (form.getCreatedBy() != null && form.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + form.getCreatedBy() + "',";
		}
		if (form.getAgentId() != null && form.getAgentId() != "") {
			s = s + "@p_agentId='" + form.getAgentId() + "',";
		}
		if (form.getServiceCenter() != null && form.getServiceCenter() != "") {
			s = s + "@p_serviceCenter='" + form.getServiceCenter() + "',";
		}
		if (form.getComplaintCategory() != null && form.getComplaintCategory() != "") {
			s = s + "@p_complaintCategory='" + form.getComplaintCategory() + "',";
		}
		if (form.getComplaintDesc() != null && form.getComplaintDesc() != "") {
			s = s + "@p_complaintDescription='" + form.getComplaintDesc() + "',";
		}
		if (form.getComplainLocation() != null && form.getComplainLocation() != "") {
			s = s + "@p_complaintLocation='" + form.getComplainLocation() + "',";
		}
		if (form.getComplainAssist() != null && form.getComplainAssist() != "") {
			s = s + "@p_assistedPerson='" + form.getComplainAssist() + "',";
		}
		if (form.getComplainAssist() != null && form.getComplainAssist() != "") {
			s = s + "@p_assistedPerson='" + form.getComplainAssist() + "',";
		}
		if (form.getSaleDesc() != null && form.getSaleDesc() != "") {
			s = s + "@p_saleDescription='" + form.getSaleDesc() + "',";
		}
		if (form.getMarketingDesc() != null && form.getMarketingDesc() != "") {
			s = s + "@p_marketingDescription='" + form.getMarketingDesc() + "',";
		}
		if (form.getMarketingQuantity() != null && form.getMarketingQuantity() != "") {
			s = s + "@p_marketingQuantity='" + form.getMarketingQuantity() + "',";
		}
		if (form.getSaleQuantity() != null && form.getSaleQuantity() != "") {
			s = s + "@p_saleQuantity='" + form.getSaleQuantity() + "',";
		}

		if (form.getCallDisconnected() != null) {
			s = s + "@p_calldisconnected=" + form.getCallDisconnected() + ",";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}

}
