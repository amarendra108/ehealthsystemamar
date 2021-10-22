package nirmalya.aatithya.restmodule.asset.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAssetMaintenanceModel {
	
	private Integer maintenanceId;
	private Integer policyId;
	private String item;
	private String serviceName;
	private String frequency;
	private String serviceType;
	private String taskPerformed;
	private Double hour;
	private String createdBy;
	private String assetCodeId;
	private String assetCodeName;
	private String performedDate;
	private Double price;
	private String desc;
	private String productId;
	private String productName;
	private String frequencyId;
	private String serviceTypeId;
	
	public RestAssetMaintenanceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestAssetMaintenanceModel(Object maintenanceId,Object policyId, Object item, Object serviceName, Object frequency,
			Object serviceType, Object taskPerformed, Object hour, Object createdBy, Object assetCodeId,
			Object assetCodeName, Object performedDate, Object price, Object desc, Object productId,
			Object productName,Object frequencyId,Object serviceTypeId) {
		super();
		this.maintenanceId = (Integer) maintenanceId;
		this.policyId = (Integer) policyId;
		this.item = (String) item;
		this.serviceName = (String) serviceName;
		this.frequency = (String) frequency;
		this.serviceType = (String) serviceType;
		this.taskPerformed = (String) taskPerformed;
		this.hour = (Double) hour;
		this.createdBy = (String) createdBy;
		this.assetCodeId = (String) assetCodeId;
		this.assetCodeName = (String) assetCodeName;
		this.performedDate = (String) performedDate;
		this.price = (Double) price;
		this.desc = (String) desc;
		this.productId = (String) productId;
		this.productName = (String) productName;
		this.frequencyId = (String) frequencyId;
		this.serviceTypeId = (String) serviceTypeId;
	}
	

	public String getFrequencyId() {
		return frequencyId;
	}

	public String getServiceTypeId() {
		return serviceTypeId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public Integer getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public String getItem() {
		return item;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getFrequency() {
		return frequency;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getTaskPerformed() {
		return taskPerformed;
	}

	public Double getHour() {
		return hour;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getAssetCodeId() {
		return assetCodeId;
	}

	public String getAssetCodeName() {
		return assetCodeName;
	}

	public String getPerformedDate() {
		return performedDate;
	}

	public Double getPrice() {
		return price;
	}

	public String getDesc() {
		return desc;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setTaskPerformed(String taskPerformed) {
		this.taskPerformed = taskPerformed;
	}

	public void setHour(Double hour) {
		this.hour = hour;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setAssetCodeId(String assetCodeId) {
		this.assetCodeId = assetCodeId;
	}

	public void setAssetCodeName(String assetCodeName) {
		this.assetCodeName = assetCodeName;
	}

	public void setPerformedDate(String performedDate) {
		this.performedDate = performedDate;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
