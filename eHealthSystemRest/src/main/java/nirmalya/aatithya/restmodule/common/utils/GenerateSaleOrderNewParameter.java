package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.customer.model.RestSaleOrderNewModel;

public class GenerateSaleOrderNewParameter {
	public static String getAddempParam(List<RestSaleOrderNewModel> customer) {
		String s = "";
		String listdata ="";
		String quotationId="";
		String custId="";
		String qutDescription="";
		String qutActive=null;
		String qutCreatedBy="";
		Double subTotal=0.00;
		Double qIGST=0.00;
		Double qCGST=0.00;
		Double qSGST=0.00;
		Double grandTotal=0.00;
		Boolean taxType=null;
		String salesOrder="";
		String storeId="";
		String orderReceiveDate="";
		String orderReceiveTime="";
		
		
		
		for (RestSaleOrderNewModel m : customer) {
			quotationId=m.getQuotationId();
			custId=m.getCustId();
			qutDescription=m.getQutDescription();
			qutActive=m.getQutActive();
			qutCreatedBy=m.getQutCreatedBy();
			subTotal=m.getSubTotal();
			qIGST=m.getqIGST();
			qCGST=m.getqCGST();
			qSGST=m.getqSGST();
			grandTotal=m.getGrandTotal();
			taxType=m.getTaxType();
			salesOrder=m.getSalesOrder();
			storeId=m.getStoreId();
			orderReceiveDate=m.getOrderReceiveDate();
			orderReceiveTime=m.getOrderReceiveTime();
			
		}
		
		s = s + "@p_quotationId='" + quotationId + "',";
		s = s + "@p_custId='" + custId + "',";
		s = s + "@p_qutDescription='" + qutDescription + "',";
		s = s + "@p_qutActive='" + qutActive + "',";
		s = s + "@p_qutCreatedBy='" + qutCreatedBy + "',";
		s = s + "@p_qutSubTotal=" + subTotal + ",";
		s = s + "@p_qutIGST=" + qIGST + ",";
		s = s + "@p_qutCGST=" + qCGST + ",";
		s = s + "@p_qutSGST=" + qSGST + ",";
		s = s + "@p_qutGrandTotal=" + grandTotal + ",";
		s = s + "@p_taxType=" + taxType + ",";
		s = s + "@p_salesId='" + salesOrder + "',";
		s = s + "@p_storeId='" + storeId + "',";
		s = s + "@p_recvDate='" + orderReceiveDate + "',";
		s = s + "@p_recvTime='" + orderReceiveTime + "',";
		
		
		if(!customer.get(0).getSalesOrder().contentEquals("1")) {
		for (RestSaleOrderNewModel m : customer) {

			listdata = listdata + "(@p_quotationId,\"" + m.getItemId() + "\",\"" + m.getItemName() + "\",\""
					+ m.getQuantity() + "\",\"" + m.getUnitPrice() + "\",\"" + m.getDiscount() + "\",\""
					+ m.getGstRate() + "\",\"" + m.getLineTotal() + "\",\"" + m.getSku()
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
	public static String getDeleteParam(RestSaleOrderNewModel customer) {
		String[] userIds = customer.getSalesOrder().split(",");
		String s = "";
		String litem = "";
		for (String a : userIds) {
			litem = litem + "\"" + a + "\",";
		}
		litem = litem.substring(0, litem.length() - 1);
		litem = "(" + litem + ")";
		s = s + "@p_salesId='" + litem + "',";

		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println(s);

		return s;
	}
}
