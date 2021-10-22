package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.customer.model.RestSalesInvoiceNewModel;

public class GenerateSalesInvoiceNewParameter {
	public static String getAddempParam(List<RestSalesInvoiceNewModel> customer) {
		String s = "";
		String listdata ="";
		String saleInvoice="";
		String salesOrderId="";
		String qutActive=null;
		String qutCreatedBy="";
		Double subTotal=0.00;
		Double qIGST=0.00;
		Double qCGST=0.00;
		Double qSGST=0.00;
		Double grandTotal=0.00;
		Boolean taxType=null;
		String storeId="";
        String custName="";
		
		
		for (RestSalesInvoiceNewModel m : customer) {
			saleInvoice=m.getSaleInvoice();
			salesOrderId=m.getSalesOrderId();
			qutActive=m.getQutActive();
			qutCreatedBy=m.getQutCreatedBy();
			subTotal=m.getSubTotal();
			qIGST=m.getqIGST();
			qCGST=m.getqCGST();
			qSGST=m.getqSGST();
			grandTotal=m.getGrandTotal();
			taxType=m.getTaxType();
			storeId=m.getStoreId();
			custName=m.getCustName();
			
		}
		
		s = s + "@p_salesInvoice='" + saleInvoice + "',";
		s = s + "@p_salesOrderId='" + salesOrderId + "',";
		s = s + "@p_qutActive='" + qutActive + "',";
		s = s + "@p_qutCreatedBy='" + qutCreatedBy + "',";
		s = s + "@p_qutSubTotal=" + subTotal + ",";
		s = s + "@p_qutIGST=" + qIGST + ",";
		s = s + "@p_qutCGST=" + qCGST + ",";
		s = s + "@p_qutSGST=" + qSGST + ",";
		s = s + "@p_qutGrandTotal=" + grandTotal + ",";
		s = s + "@p_taxType=" + taxType + ",";
		s = s + "@p_storeId='" + storeId + "',";
		s = s + "@p_custName='" + custName + "',";
		
		if(!customer.get(0).getSaleInvoice().contentEquals("1")) {
		for (RestSalesInvoiceNewModel m : customer) {

			listdata = listdata + "(@p_salesInvoice,\"" + m.getItemId() + "\",\"" + m.getItemName() + "\",\""
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
	
	public static String getDeleteParamnew(RestSalesInvoiceNewModel customer) {
		String[] userIds = customer.getSaleInvoice().split(",");
		String s = "";
		String litem = "";
		for (String a : userIds) {
			litem = litem + "\"" + a + "\",";
		}
		litem = litem.substring(0, litem.length() - 1);
		litem = "(" + litem + ")";
		s = s + "@p_salesInvoice='" + litem + "',";

		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println("EE"+s);

		return s;
	}

}
