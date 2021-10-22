
package nirmalya.aatithya.restmodule.asset.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAssetCodeModel {

	private String assetCode;
	private String sku ;
	private String assetCodeName;
	private String item;
	private String itemName;
	private String store;
	private String dateOfPurchase;
	private String guaranty;
	private String brandId;
	private String brandName;
	private String email;
	private String custPhone;
	private String description;
	private String workingStatus;
	private String assignStatus;
	private String status;
	private String serialNo;
	private String mobileNo;
	private String imieNo;
	private String model;
	private String chassisNo;
	private String engineNo;
	private String category;
	private String categoryName;
	private String barcode;
	private String grnInvoice;
	private String classificationId;
	private String classification; // private
	private String createdBy;
	
	
	public RestAssetCodeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestAssetCodeModel(Object assetCode,Object sku, Object assetCodeName, Object item,Object itemName, Object store, Object dateOfPurchase,
			Object guaranty,Object brandId, Object brandName, Object email, Object custPhone, Object description, Object workingStatus,
			Object assignStatus, Object status, Object serialNo, Object mobileNo, Object imieNo, Object model,
			Object chassisNo, Object engineNo, Object category,Object categoryName, Object barcode, Object grnInvoice,Object classificationId,
			Object classification,Object createdBy) {
		super();
		this.assetCode = (String) assetCode;
		this.sku = (String) sku;
		this.assetCodeName = (String) assetCodeName;
		this.item = (String) item;
		this.itemName = (String) itemName;
		this.store = (String) store;
		this.dateOfPurchase = (String) dateOfPurchase;
		this.guaranty = (String) guaranty;
		this.brandId = (String) brandId;
		this.brandName = (String) brandName;
		this.email = (String) email;
		this.custPhone = (String) custPhone;
		this.description = (String) description;
		this.workingStatus = (String) workingStatus;
		this.assignStatus = (String) assignStatus;
		this.status = (String) status;
		this.serialNo = (String) serialNo;
		this.mobileNo = (String) mobileNo;
		this.imieNo = (String) imieNo;
		this.model = (String) model;
		this.chassisNo = (String) chassisNo;
		this.engineNo = (String) engineNo;
		this.category = (String) category;
		this.categoryName = (String) categoryName;
		this.barcode = (String) barcode;
		this.grnInvoice = (String) grnInvoice;
		this.classificationId = (String) classificationId;
		this.classification = (String) classification;
		this.createdBy = (String) createdBy;
		
	}
	public RestAssetCodeModel(Object assetCode,Object sku, Object store, Object dateOfPurchase,
			Object guaranty,Object classification, Object itemName) {
		super();
		this.assetCode = (String) assetCode;
		this.sku = (String) sku;
		//this.assetCodeName = (String) assetCodeName;
		this.itemName = (String) itemName;
		this.store = (String) store;
		this.dateOfPurchase = (String) dateOfPurchase;
		this.guaranty = (String) guaranty;

		this.classification = (String) classification;

		
	}
	private String dashAssetCode;
	private String dashPolicy;
	private String dashMainDate;
	private String dashNextDate;
	private String dashServiceName;
	private String dashTaskPerform;
	private String dashFreQuency;
	
	public RestAssetCodeModel(Object dashAssetCode,Object dashPolicy, Object dashMainDate, Object dashNextDate,
			Object dashServiceName,Object dashTaskPerform, Object dashFreQuency,Object createdBy) {
		super();
		this.dashAssetCode = (String) dashAssetCode;
		this.dashPolicy = (String) dashPolicy;
		this.dashMainDate = (String) dashMainDate;
		this.dashNextDate = (String) dashNextDate;
		this.dashServiceName = (String) dashServiceName;
		this.dashTaskPerform = (String) dashTaskPerform;

		this.dashFreQuency = (String) dashFreQuency;
		this.createdBy = (String) createdBy;

		
	}
	public String getDashAssetCode() {
		return dashAssetCode;
	}

	public void setDashAssetCode(String dashAssetCode) {
		this.dashAssetCode = dashAssetCode;
	}

	public String getDashPolicy() {
		return dashPolicy;
	}

	public void setDashPolicy(String dashPolicy) {
		this.dashPolicy = dashPolicy;
	}

	public String getDashMainDate() {
		return dashMainDate;
	}

	public void setDashMainDate(String dashMainDate) {
		this.dashMainDate = dashMainDate;
	}

	public String getDashNextDate() {
		return dashNextDate;
	}

	public void setDashNextDate(String dashNextDate) {
		this.dashNextDate = dashNextDate;
	}

	public String getDashServiceName() {
		return dashServiceName;
	}

	public void setDashServiceName(String dashServiceName) {
		this.dashServiceName = dashServiceName;
	}

	

	public String getDashTaskPerform() {
		return dashTaskPerform;
	}

	public void setDashTaskPerform(String dashTaskPerform) {
		this.dashTaskPerform = dashTaskPerform;
	}

	public String getDashFreQuency() {
		return dashFreQuency;
	}

	public void setDashFreQuency(String dashFreQuency) {
		this.dashFreQuency = dashFreQuency;
	}

	public String getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(String classificationId) {
		this.classificationId = classificationId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public String getAssetCodeName() {
		return assetCodeName;
	}

	public String getItem() {
		return item;
	}

	public String getStore() {
		return store;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public String getGuaranty() {
		return guaranty;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getEmail() {
		return email;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public String getDescription() {
		return description;
	}

	public String getWorkingStatus() {
		return workingStatus;
	}

	public String getAssignStatus() {
		return assignStatus;
	}

	public String getStatus() {
		return status;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getImieNo() {
		return imieNo;
	}

	public String getModel() {
		return model;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public String getCategory() {
		return category;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getGrnInvoice() {
		return grnInvoice;
	}

	public String getClassification() {
		return classification;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public void setAssetCodeName(String assetCodeName) {
		this.assetCodeName = assetCodeName;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public void setGuaranty(String guaranty) {
		this.guaranty = guaranty;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}

	public void setAssignStatus(String assignStatus) {
		this.assignStatus = assignStatus;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setImieNo(String imieNo) {
		this.imieNo = imieNo;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setGrnInvoice(String grnInvoice) {
		this.grnInvoice = grnInvoice;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	
	public String getItemName() {
		return itemName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
