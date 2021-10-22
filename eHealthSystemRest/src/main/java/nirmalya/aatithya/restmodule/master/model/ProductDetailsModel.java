package nirmalya.aatithya.restmodule.master.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductDetailsModel {

	private String productId;
	private String sku;
	private String model;
	private String manufacture;
	private String variationType;
	private String variationValue;
	private String unit;
	private Double salePrice;
	private Double saleTax;
	private Double saleCess;
	private String createdBy;
	private String createdDate;
	private String isEdit;
	private String vendorId;
	private Double moq;
	private String editSKUId;
	private String editVendorId;
	
	public ProductDetailsModel() {
		super();
	}

	public ProductDetailsModel(Object productId, Object sku, Object model, Object manufacture, Object variationType,
			Object variationValue, Object unit, Object salePrice, Object saleTax, Object saleCess, Object createdBy,
			Object createdDate, Object isEdit, Object vendorId, Object moq) {
		super();
		this.productId = (String) productId;
		this.sku = (String) sku;
		this.model = (String) model;
		this.manufacture = (String) manufacture;
		this.variationType = (String) variationType;
		this.variationValue = (String) variationValue;
		this.unit = (String) unit;
		this.salePrice = (Double) salePrice;
		this.saleTax = (Double) saleTax;
		this.saleCess = (Double) saleCess;
		this.createdBy = (String) createdBy;
		this.createdDate = (String) createdDate;
		this.isEdit = (String) isEdit;
		this.vendorId = (String) vendorId;
		this.moq = (Double) moq;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getVariationType() {
		return variationType;
	}

	public void setVariationType(String variationType) {
		this.variationType = variationType;
	}

	public String getVariationValue() {
		return variationValue;
	}

	public void setVariationValue(String variationValue) {
		this.variationValue = variationValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(Double saleTax) {
		this.saleTax = saleTax;
	}

	public Double getSaleCess() {
		return saleCess;
	}

	public void setSaleCess(Double saleCess) {
		this.saleCess = saleCess;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Double getMoq() {
		return moq;
	}

	public void setMoq(Double moq) {
		this.moq = moq;
	}

	public String getEditSKUId() {
		return editSKUId;
	}

	public void setEditSKUId(String editSKUId) {
		this.editSKUId = editSKUId;
	}

	public String getEditVendorId() {
		return editVendorId;
	}

	public void setEditVendorId(String editVendorId) {
		this.editVendorId = editVendorId;
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
