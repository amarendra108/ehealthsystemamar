package nirmalya.aathithya.webmodule.asset.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssetPolicyModel {

	private Integer policyId;
	private String item;
	private String serviceName;
	private String frequency;
	private String serviceType;
	private String taskPerformed;
	private Double hour;
	private String createdBy;
	
	public AssetPolicyModel() {
		super(); // TODO Auto-generated constructor stub
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
