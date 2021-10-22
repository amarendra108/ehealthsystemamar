package nirmalya.aatithya.restmodule.customer.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestSaleOrderNewModel {
	private String quotationId;
	private String qutName; 
	private String custId;
	private String custName;
	private String custGSTNo;
	private String qutValidDate; 
	private String qutDescription;
	private String qutActive; 
	private String qutCreatedBy;
	private String qutUpdatedOn;
	private String itemId;
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
	private String active;
	private Boolean taxType;
	private String qItmCode;
	private String sku;
	private String salesOrder;
	private String storeId;
	private String orderReceiveDate;
	private String orderReceiveTime;
	private String salesActive;
	private Double itemIgst;
	private Double itemCgst;
	private Double itemSgst;
	
	public RestSaleOrderNewModel() {
		super();
		
	}

	public RestSaleOrderNewModel(Object quotationId, Object qutName, Object custId, Object custName, Object custGSTNo,
			Object qutValidDate, Object qutDescription, Object qutActive, Object qutCreatedBy, Object qutUpdatedOn,
			Object itemId, Object itemName, Object quantity, Object unitPrice, Object discount,
			Object gstRate, Object lineTotal, Object subTotal, Object qIGST, Object qCGST, Object qSGST,
			Object grandTotal, Object active, Object taxType, Object qItmCode, Object sku, Object salesOrder,
			Object storeId, Object orderReceiveDate, Object orderReceiveTime, Object salesActive, Object itemIgst, Object itemCgst, Object itemSgst) {
		super();
		this.quotationId = (String)quotationId;
		this.qutName = (String)qutName;
		this.custId =(String) custId;
		this.custName =(String) custName;
		this.custGSTNo = (String)custGSTNo;
		this.qutValidDate = (String)qutValidDate;
		this.qutDescription = (String)qutDescription;
		this.qutActive = (String)qutActive;
		this.qutCreatedBy =(String) qutCreatedBy;
		this.qutUpdatedOn =(String) qutUpdatedOn;
		this.itemId =(String) itemId;
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
		this.active =(String) active;
		this.taxType = (Boolean)taxType;
		this.qItmCode =(String) qItmCode;
		this.sku =(String) sku;
		this.salesOrder =(String) salesOrder;
		this.storeId =(String) storeId;
		this.orderReceiveDate =(String) orderReceiveDate;
		this.orderReceiveTime =(String) orderReceiveTime;
		this.salesActive =(String) salesActive;
		this.itemIgst =(Double) itemIgst;
		this.itemCgst =(Double) itemCgst;
		this.itemSgst =(Double) itemSgst;
	}
	
	
	public String getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}
	public String getQutName() {
		return qutName;
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

	public void setQutName(String qutName) {
		this.qutName = qutName;
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
	public String getCustGSTNo() {
		return custGSTNo;
	}
	public void setCustGSTNo(String custGSTNo) {
		this.custGSTNo = custGSTNo;
	}
	public String getQutValidDate() {
		return qutValidDate;
	}
	public void setQutValidDate(String qutValidDate) {
		this.qutValidDate = qutValidDate;
	}
	public String getQutDescription() {
		return qutDescription;
	}
	public void setQutDescription(String qutDescription) {
		this.qutDescription = qutDescription;
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Boolean getTaxType() {
		return taxType;
	}
	public void setTaxType(Boolean taxType) {
		this.taxType = taxType;
	}
	public String getqItmCode() {
		return qItmCode;
	}
	public void setqItmCode(String qItmCode) {
		this.qItmCode = qItmCode;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(String salesOrder) {
		this.salesOrder = salesOrder;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrderReceiveDate() {
		return orderReceiveDate;
	}
	public void setOrderReceiveDate(String orderReceiveDate) {
		this.orderReceiveDate = orderReceiveDate;
	}
	public String getOrderReceiveTime() {
		return orderReceiveTime;
	}
	public void setOrderReceiveTime(String orderReceiveTime) {
		this.orderReceiveTime = orderReceiveTime;
	}
	public String getSalesActive() {
		return salesActive;
	}
	public void setSalesActive(String salesActive) {
		this.salesActive = salesActive;
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
