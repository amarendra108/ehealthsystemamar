package nirmalya.aatithya.restmodule.customer.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestSalesInvoiceNewModel {
	
	private String saleInvoice;
	private String salesOrder;
	private String salesOrderId;
	private String quotationId;
	private String qutActive; 
	private String qutCreatedBy;
	private String qutUpdatedOn;
	private String itemId;
	private Integer slNo;
	private String itemName;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double gstRate;
	private Double lineTotal;
	private Double subTotal;
	private Double qIGST;
	private Double qCGST;
	private Double qSGST;
	private Double grandTotal;
	private Boolean taxType;
	private String sku;
	private Double itemIgst;
	private Double itemCgst;
	private Double itemSgst;
	private String poNo;
	private String storeId;
	private String salesActive;

	private String paymentId;
	private String sOrder;
	private String sInvoice;
	private String paymodeId;
	private String payDate;
	private Double totalAmnt;
	private Double paidAmnt;
	private String tdN;
	private String ckn;
	private Double outStdAmt;
	private String custId;
	private String custName;
	private String rVoucher;
	private Boolean taxTypepay;
	private Double gstRatepay;
	private Double subTotalpay;
	private Double qIGSTpay;
	private Double qCGSTpay;
	private Double qSGSTpay;
	private Double grandTotalpay;
	
	public RestSalesInvoiceNewModel() {
		super();
		
	}

	public RestSalesInvoiceNewModel(Object saleInvoice, Object salesOrder, Object salesOrderId, Object quotationId,
			Object qutActive, Object qutCreatedBy, Object qutUpdatedOn, Object itemId, Object slNo, Object itemName,
			Object quantity, Object unitPrice, Object discount, Object gstRate, Object lineTotal, Object subTotal,
			Object qIGST, Object qCGST, Object qSGST, Object grandTotal, Object taxType, Object sku, Object itemIgst,
			Object itemCgst, Object itemSgst, Object poNo, Object storeId, Object salesActive,Object paymentId, Object sOrder, Object sInvoice, Object paymodeId, Object payDate, Object totalAmnt,
			Object paidAmnt,Object tdN,Object ckn,Object outStdAmt,Object custId,Object custName,Object rVoucher,Object taxTypepay,
			Object gstRatepay,Object subTotalpay,Object qIGSTpay,Object qCGSTpay,Object qSGSTpay,Object grandTotalpay) {
		super();
		this.saleInvoice =(String) saleInvoice;
		this.salesOrder =(String) salesOrder;
		this.salesOrderId =(String) salesOrderId;
		this.quotationId =(String) quotationId;
		this.qutActive =(String) qutActive;
		this.qutCreatedBy =(String) qutCreatedBy;
		this.qutUpdatedOn =(String) qutUpdatedOn;
		this.itemId =(String) itemId;
		this.slNo =(Integer) slNo;
		this.itemName =(String) itemName;
		this.quantity =(Double) quantity;
		this.unitPrice =(Double) unitPrice;
		this.discount =(Double) discount;
		this.gstRate =(Double) gstRate;
		this.lineTotal =(Double) lineTotal;
		this.subTotal =(Double) subTotal;
		this.qIGST =(Double) qIGST;
		this.qCGST =(Double) qCGST;
		this.qSGST =(Double) qSGST;
		this.grandTotal =(Double) grandTotal;
		this.taxType =(Boolean) taxType;
		this.sku =(String) sku;
		this.itemIgst =(Double) itemIgst;
		this.itemCgst =(Double) itemCgst;
		this.itemSgst =(Double) itemSgst;
		this.poNo =(String) poNo;
		this.storeId =(String)storeId;
		this.salesActive =(String) salesActive;
		this.paymentId = (String)paymentId;
		this.sOrder = (String)sOrder;
		this.sInvoice = (String)sInvoice;
		this.paymodeId =(String) paymodeId;
		this.payDate = (String)payDate;
		this.totalAmnt =(Double) totalAmnt;
		this.paidAmnt =(Double) paidAmnt;
		this.tdN =(String) tdN;
		this.ckn =(String) ckn;
		this.outStdAmt=(Double) outStdAmt;
		this.custId=(String) custId;
		this.custName=(String) custName;
		this.rVoucher=(String) rVoucher;
		this.taxTypepay=(Boolean) taxTypepay;
		this.gstRatepay=(Double) gstRatepay;
		this.subTotalpay=(Double) subTotalpay;
		this.qIGSTpay=(Double) qIGSTpay;
		this.qCGSTpay=(Double) qCGSTpay;
		this.qSGSTpay=(Double) qSGSTpay;
		this.grandTotalpay=(Double) grandTotalpay;
	}



	public String getSaleInvoice() {
		return saleInvoice;
	}



	public void setSaleInvoice(String saleInvoice) {
		this.saleInvoice = saleInvoice;
	}



	public String getSalesOrder() {
		return salesOrder;
	}



	public void setSalesOrder(String salesOrder) {
		this.salesOrder = salesOrder;
	}



	public String getSalesOrderId() {
		return salesOrderId;
	}



	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}



	public String getQuotationId() {
		return quotationId;
	}



	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}



	public String getQutActive() {
		return qutActive;
	}



	public void setQutActive(String qutActive) {
		this.qutActive = qutActive;
	}



	public String getQutCreatedBy() {
		return qutCreatedBy;
	}



	public void setQutCreatedBy(String qutCreatedBy) {
		this.qutCreatedBy = qutCreatedBy;
	}



	public String getQutUpdatedOn() {
		return qutUpdatedOn;
	}



	public void setQutUpdatedOn(String qutUpdatedOn) {
		this.qutUpdatedOn = qutUpdatedOn;
	}



	public String getItemId() {
		return itemId;
	}



	public void setItemId(String itemId) {
		this.itemId = itemId;
	}



	public Integer getSlNo() {
		return slNo;
	}



	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public Double getQuantity() {
		return quantity;
	}



	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}



	public Double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public Double getDiscount() {
		return discount;
	}



	public void setDiscount(Double discount) {
		this.discount = discount;
	}



	public Double getGstRate() {
		return gstRate;
	}



	public void setGstRate(Double gstRate) {
		this.gstRate = gstRate;
	}



	public Double getLineTotal() {
		return lineTotal;
	}



	public void setLineTotal(Double lineTotal) {
		this.lineTotal = lineTotal;
	}



	public Double getSubTotal() {
		return subTotal;
	}



	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}



	public Double getqIGST() {
		return qIGST;
	}



	public void setqIGST(Double qIGST) {
		this.qIGST = qIGST;
	}



	public Double getqCGST() {
		return qCGST;
	}



	public void setqCGST(Double qCGST) {
		this.qCGST = qCGST;
	}



	public Double getqSGST() {
		return qSGST;
	}



	public void setqSGST(Double qSGST) {
		this.qSGST = qSGST;
	}



	public Double getGrandTotal() {
		return grandTotal;
	}



	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}



	public Boolean getTaxType() {
		return taxType;
	}



	public void setTaxType(Boolean taxType) {
		this.taxType = taxType;
	}



	public String getSku() {
		return sku;
	}



	public void setSku(String sku) {
		this.sku = sku;
	}



	public Double getItemIgst() {
		return itemIgst;
	}



	public void setItemIgst(Double itemIgst) {
		this.itemIgst = itemIgst;
	}



	public Double getItemCgst() {
		return itemCgst;
	}



	public void setItemCgst(Double itemCgst) {
		this.itemCgst = itemCgst;
	}



	public Double getItemSgst() {
		return itemSgst;
	}



	public void setItemSgst(Double itemSgst) {
		this.itemSgst = itemSgst;
	}



	public String getPoNo() {
		return poNo;
	}



	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}



	public String getStoreId() {
		return storeId;
	}



	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}



	public String getSalesActive() {
		return salesActive;
	}



	public void setSalesActive(String salesActive) {
		this.salesActive = salesActive;
	}

	
	public String getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}



	public String getsOrder() {
		return sOrder;
	}



	public void setsOrder(String sOrder) {
		this.sOrder = sOrder;
	}



	public String getsInvoice() {
		return sInvoice;
	}



	public void setsInvoice(String sInvoice) {
		this.sInvoice = sInvoice;
	}



	public String getPaymodeId() {
		return paymodeId;
	}



	public void setPaymodeId(String paymodeId) {
		this.paymodeId = paymodeId;
	}



	public String getPayDate() {
		return payDate;
	}



	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}



	public Double getTotalAmnt() {
		return totalAmnt;
	}



	public void setTotalAmnt(Double totalAmnt) {
		this.totalAmnt = totalAmnt;
	}



	public Double getPaidAmnt() {
		return paidAmnt;
	}



	public void setPaidAmnt(Double paidAmnt) {
		this.paidAmnt = paidAmnt;
	}



	public String getTdN() {
		return tdN;
	}

	public void setTdN(String tdN) {
		this.tdN = tdN;
	}

	public String getCkn() {
		return ckn;
	}

	public void setCkn(String ckn) {
		this.ckn = ckn;
	}

	public Double getOutStdAmt() {
		return outStdAmt;
	}

	public void setOutStdAmt(Double outStdAmt) {
		this.outStdAmt = outStdAmt;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getrVoucher() {
		return rVoucher;
	}

	public void setrVoucher(String rVoucher) {
		this.rVoucher = rVoucher;
	}

	public Boolean getTaxTypepay() {
		return taxTypepay;
	}

	public void setTaxTypepay(Boolean taxTypepay) {
		this.taxTypepay = taxTypepay;
	}

	public Double getGstRatepay() {
		return gstRatepay;
	}

	public void setGstRatepay(Double gstRatepay) {
		this.gstRatepay = gstRatepay;
	}

	public Double getSubTotalpay() {
		return subTotalpay;
	}

	public void setSubTotalpay(Double subTotalpay) {
		this.subTotalpay = subTotalpay;
	}

	public Double getqIGSTpay() {
		return qIGSTpay;
	}

	public void setqIGSTpay(Double qIGSTpay) {
		this.qIGSTpay = qIGSTpay;
	}

	public Double getqCGSTpay() {
		return qCGSTpay;
	}

	public void setqCGSTpay(Double qCGSTpay) {
		this.qCGSTpay = qCGSTpay;
	}

	public Double getqSGSTpay() {
		return qSGSTpay;
	}

	public void setqSGSTpay(Double qSGSTpay) {
		this.qSGSTpay = qSGSTpay;
	}

	public Double getGrandTotalpay() {
		return grandTotalpay;
	}

	public void setGrandTotalpay(Double grandTotalpay) {
		this.grandTotalpay = grandTotalpay;
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
