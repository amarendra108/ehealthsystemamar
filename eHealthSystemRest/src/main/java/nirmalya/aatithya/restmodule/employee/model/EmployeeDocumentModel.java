package nirmalya.aatithya.restmodule.employee.model;

import java.util.List;

import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorDocumentModel;

public class EmployeeDocumentModel {

	private List<InventoryVendorDocumentModel> documentList;
	private String employeeId;
	private String createdBy;
	
	public List<InventoryVendorDocumentModel> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<InventoryVendorDocumentModel> documentList) {
		this.documentList = documentList;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public EmployeeDocumentModel(String employeeId, String createdBy) {
		super();
		this.employeeId = employeeId;
		this.createdBy = createdBy;
	}
	
}
