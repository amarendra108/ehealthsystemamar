package nirmalya.aatithya.restmodule.recruitment.model;

import java.util.List;

public class RestRequisitonVendorAllocationModel {
	
	private List<String> requisitionId;
	private List<String> vendorId;
	private String createdOn;
	private String createdBy;
	private String updatedOn;
	private String updatedBy;
	
	
	public RestRequisitonVendorAllocationModel(Object createdOn,
			Object createdBy, Object updatedOn, Object updatedBy) {
		super();
		this.createdOn = (String) createdOn;
		this.createdBy = (String) createdBy;
		this.updatedOn = (String) updatedOn;
		this.updatedBy = (String) updatedBy;
	}


	public List<String> getRequisitionId() {
		return requisitionId;
	}


	public void setRequisitionId(List<String> requisitionId) {
		this.requisitionId = requisitionId;
	}


	public List<String> getVendorId() {
		return vendorId;
	}


	public void setVendorId(List<String> vendorId) {
		this.vendorId = vendorId;
	}


	public String getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
