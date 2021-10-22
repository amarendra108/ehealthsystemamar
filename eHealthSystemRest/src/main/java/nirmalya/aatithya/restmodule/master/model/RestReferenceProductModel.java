package nirmalya.aatithya.restmodule.master.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestReferenceProductModel {

	private String brandId;
	private String brandName;
	private String brandOrder;
	private String brandCode;
	private String brandDesc;
	private String brandStatus;
	private String brandCreatedBy;
	private String brandModifiedBy;
	private String brandCreatedOn;
	private String brandUpdatedOn;
	
	// product Type
	
		private String productId;
		private String productOrder;
		private String productName;
		private String productDesc;
		private String productStatus;
		private String productCreatedBy;
		private String productModifiedBy;
		private String productCreatedOn;
		private String productUpdatedOn;
		
		
		// variation Type
		
			private String variationId;
			private String variationName;
			private String variationDesc;
			private String variationStatus;
			private String variationCreatedBy;
			private String variationModifiedBy;
			private String variationCreatedOn;
			private String variationUpdatedOn;
	

	public RestReferenceProductModel(Object brandId, Object brandName, Object brandOrder, Object brandCode,
			Object brandDesc, Object brandStatus, Object brandCreatedBy, Object brandModifiedBy, Object brandCreatedOn,
			Object brandUpdatedOn, Object productId, Object productName,
			Object productDesc, Object productStatus, Object productCreatedBy, Object productModifiedBy, Object productCreatedOn,
			Object productUpdatedOn, Object variationId, Object variationName,
			Object variationDesc, Object variationStatus, Object variationCreatedBy, Object variationModifiedBy, Object variationCreatedOn,
			Object variationUpdatedOn) {
		
		
		super();

		this.brandId = (String) brandId;
		this.brandName = (String) brandName;
		this.brandOrder = (String) brandOrder;
		this.brandCode = (String) brandCode;
		this.brandDesc = (String) brandDesc;
		this.brandStatus = (String) brandStatus;
		this.brandCreatedBy = (String) brandCreatedBy;
		this.brandModifiedBy = (String) brandModifiedBy;
		this.brandCreatedOn = (String) brandCreatedOn;
		this.brandUpdatedOn = (String) brandUpdatedOn;
		
		this.productId = (String) productId;
		this.productOrder = (String) productOrder;
		this.productName = (String) productName;
		this.productDesc = (String) productDesc;
		this.productStatus = (String) productStatus;
		this.productCreatedBy = (String) productCreatedBy;
		this.productModifiedBy = (String) productModifiedBy;
		this.productCreatedOn = (String) productCreatedOn;
		this.productUpdatedOn = (String) productUpdatedOn;
		
		this.variationId = (String) variationId;
		this.variationName = (String) variationName;
		this.variationDesc = (String) variationDesc;
		this.variationStatus = (String) variationStatus;
		this.variationCreatedBy = (String) variationCreatedBy;
		this.variationModifiedBy = (String) variationModifiedBy;
		this.variationCreatedOn = (String) variationCreatedOn;
		this.variationUpdatedOn = (String) variationUpdatedOn;

	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandOrder() {
		return brandOrder;
	}

	public void setBrandOrder(String brandOrder) {
		this.brandOrder = brandOrder;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}

	public String getBrandCreatedBy() {
		return brandCreatedBy;
	}

	public void setBrandCreatedBy(String brandCreatedBy) {
		this.brandCreatedBy = brandCreatedBy;
	}

	public String getBrandModifiedBy() {
		return brandModifiedBy;
	}

	public void setBrandModifiedBy(String brandModifiedBy) {
		this.brandModifiedBy = brandModifiedBy;
	}

	public String getBrandCreatedOn() {
		return brandCreatedOn;
	}

	public void setBrandCreatedOn(String brandCreatedOn) {
		this.brandCreatedOn = brandCreatedOn;
	}

	public String getBrandUpdatedOn() {
		return brandUpdatedOn;
	}

	public void setBrandUpdatedOn(String brandUpdatedOn) {
		this.brandUpdatedOn = brandUpdatedOn;
	}

	
	
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(String productOrder) {
		this.productOrder = productOrder;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductCreatedBy() {
		return productCreatedBy;
	}

	public void setProductCreatedBy(String productCreatedBy) {
		this.productCreatedBy = productCreatedBy;
	}

	public String getProductModifiedBy() {
		return productModifiedBy;
	}

	public void setProductModifiedBy(String productModifiedBy) {
		this.productModifiedBy = productModifiedBy;
	}

	public String getProductCreatedOn() {
		return productCreatedOn;
	}

	public void setProductCreatedOn(String productCreatedOn) {
		this.productCreatedOn = productCreatedOn;
	}

	public String getProductUpdatedOn() {
		return productUpdatedOn;
	}

	public void setProductUpdatedOn(String productUpdatedOn) {
		this.productUpdatedOn = productUpdatedOn;
	}

	
	
	
	public String getVariationId() {
		return variationId;
	}

	public void setVariationId(String variationId) {
		this.variationId = variationId;
	}

	public String getVariationName() {
		return variationName;
	}

	public void setVariationName(String variationName) {
		this.variationName = variationName;
	}

	public String getVariationDesc() {
		return variationDesc;
	}

	public void setVariationDesc(String variationDesc) {
		this.variationDesc = variationDesc;
	}

	public String getVariationStatus() {
		return variationStatus;
	}

	public void setVariationStatus(String variationStatus) {
		this.variationStatus = variationStatus;
	}

	public String getVariationCreatedBy() {
		return variationCreatedBy;
	}

	public void setVariationCreatedBy(String variationCreatedBy) {
		this.variationCreatedBy = variationCreatedBy;
	}

	public String getVariationModifiedBy() {
		return variationModifiedBy;
	}

	public void setVariationModifiedBy(String variationModifiedBy) {
		this.variationModifiedBy = variationModifiedBy;
	}

	public String getVariationCreatedOn() {
		return variationCreatedOn;
	}

	public void setVariationCreatedOn(String variationCreatedOn) {
		this.variationCreatedOn = variationCreatedOn;
	}

	public String getVariationUpdatedOn() {
		return variationUpdatedOn;
	}

	public void setVariationUpdatedOn(String variationUpdatedOn) {
		this.variationUpdatedOn = variationUpdatedOn;
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
