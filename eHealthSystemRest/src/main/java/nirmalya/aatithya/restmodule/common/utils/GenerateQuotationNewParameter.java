package nirmalya.aatithya.restmodule.common.utils;


import java.util.List;

import nirmalya.aatithya.restmodule.customer.model.RestQuotationNewModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRequisitionModel;


public class GenerateQuotationNewParameter {
	
public static String getAddempParam(List<RestQuotationNewModel> customer) {
		
		String s = "";
		String listdata ="";
		String quotationId="";
		String qutName="";
		String custId="";
		String qutValidDate="";
		String qutDescription="";
		String qutActive=null;
		String qutCreatedBy="";
		Double subTotal=0.00;
		Double qIGST=0.00;
		Double qCGST=0.00;
		Double qSGST=0.00;
		Double grandTotal=0.00;
		Boolean taxType=null;
		
		
		for (RestQuotationNewModel m : customer) {
			quotationId=m.getQuotationId();
			qutName=m.getQutName();
			custId=m.getCustId();
			qutValidDate=m.getQutValidDate();
			qutDescription=m.getQutDescription();
			qutActive=m.getQutActive();
			qutCreatedBy=m.getQutCreatedBy();
			subTotal=m.getSubTotal();
			qIGST=m.getqIGST();
			qCGST=m.getqCGST();
			qSGST=m.getqSGST();
			grandTotal=m.getGrandTotal();
			taxType=m.getTaxType();
			
		}
		
		s = s + "@p_quotationId='" + quotationId + "',";
		s = s + "@p_qutName='" + qutName + "',";
		s = s + "@p_custId='" + custId + "',";
		s = s + "@p_qutValidDate='" + qutValidDate + "',";
		s = s + "@p_qutDescription='" + qutDescription + "',";
		s = s + "@p_qutActive='" + qutActive + "',";
		s = s + "@p_qutCreatedBy='" + qutCreatedBy + "',";
		s = s + "@p_qutSubTotal=" + subTotal + ",";
		s = s + "@p_qutIGST=" + qIGST + ",";
		s = s + "@p_qutCGST=" + qCGST + ",";
		s = s + "@p_qutSGST=" + qSGST + ",";
		s = s + "@p_qutGrandTotal=" + grandTotal + ",";
		s = s + "@p_taxType=" + taxType + ",";
		
		if(!customer.get(0).getQuotationId().contentEquals("1")) {
		for (RestQuotationNewModel m : customer) {
			Double disc = 0.0;
			if(m.getDiscount() == null) {
				disc = 0.0;
			} else {
				disc = m.getDiscount();
			}

			listdata = listdata + "(@p_quotationId,\"" + m.getItemId() + "\",\"" + m.getItemName() + "\",\""
					+ m.getQuantity() + "\",\"" + m.getUnitPrice() + "\",\"" + disc + "\",\""
					+ m.getGstRate() + "\",\"" + m.getLineTotal() + "\",\"" + m.getqItmCode() + "\",\"" + m.getSku()
					+ "\"," + m.getItemIgst() + "," + m.getItemCgst() + "," + m.getItemSgst() + "),";
		}
		listdata = listdata.substring(0, listdata.length() - 1);

		s = s + "@p_litemSubQuery='" + listdata + "',";


		if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
		}
		}
		System.out.println("Item Details"+s);
		return s;
		
		
	}
public static String getDeleteParam(RestQuotationNewModel customer) {
	String[] userIds = customer.getQuotationId().split(",");
	String s = "";
	String litem = "";
	for (String a : userIds) {
		litem = litem + "\"" + a + "\",";
	}
	litem = litem.substring(0, litem.length() - 1);
	litem = "(" + litem + ")";
	s = s + "@p_quotationId='" + litem + "',";

	
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

	System.out.println(s);

	return s;
}
}




