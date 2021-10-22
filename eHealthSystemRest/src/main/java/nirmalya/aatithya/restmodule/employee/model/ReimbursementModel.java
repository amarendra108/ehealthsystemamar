package nirmalya.aatithya.restmodule.employee.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

//import nirmalya.aathithya.webmodule.employee.model.ReimbursementDocumentModel;

public class ReimbursementModel {

	private String reimbursementReqId;
	private String slNo;
	private String empName;
	private String requisitionId;
	private String placeName;
	private String fromDate;
	private String toDate;
	private String purpose;
	private Double advanceReq;
	private Double advanceAmount;
	private Double aamttPaid;
	private String approveStatus;
	
	
	private String reimTypeId;
	//private String reimTypeName;
	private String reimPolicyId;
	//private String reimPolicyName;
	private String expenseDate;
	private String expenseDesc;
	private Double expenseAmount;
	private String referenceNo;
	private String documentName;
	private String docName;
//	private List<ReimbursementDocumentModel> documentList;
	
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	private String total;
	
	private String module;
	private String component;
	private String subcomponent;
	public ReimbursementModel(Object slNo,Object reimbursementReqId,Object requisitionId,Object empName, Object placeName, Object fromDate, Object toDate,
			Object purpose, Double advanceReq, Double advanceAmount,Double aamttPaid ,Object approveStatus, Object reimTypeId,
			 Object reimPolicyId, Object expenseDate, Object expenseDesc,
			 Double expenseAmount, Object referenceNo,Object documentName,Object docName, Object createdBy,
			Object createdOn, Object updatedBy, Object updatedOn, Object total, Object module, Object component, Object subcomponent) {
		
		super();
		
		this.reimbursementReqId = (String) reimbursementReqId;
		this.requisitionId = (String) requisitionId;
		this.empName = (String) empName;
		this.placeName = (String) placeName;
		this.fromDate = (String) fromDate;
		this.toDate = (String) toDate;
		this.purpose = (String) purpose;
		this.advanceReq =  advanceReq;
		this.advanceAmount = advanceAmount;
		this.aamttPaid = aamttPaid;
		this.approveStatus = (String) approveStatus;
		this.slNo = (String) slNo;
		this.reimTypeId = (String) reimTypeId;
		//this.reimTypeName = (String) reimTypeName;
		this.reimPolicyId = (String) reimPolicyId;
	//	this.reimPolicyName = (String) reimPolicyName;
		this.expenseDate = (String) expenseDate;
		this.expenseDesc = (String) expenseDesc;
		this.expenseAmount = (Double) expenseAmount;
		this.referenceNo = (String) referenceNo;
		this.documentName = (String) documentName;
		this.docName = (String) docName;
		this.createdBy = (String) createdBy;
		this.createdOn = (String) createdOn;
		this.updatedBy = (String) updatedBy;
		this.updatedOn = (String) updatedOn;
		this.total = (String) total;
		this.module = (String) module;
		this.component = (String) component;
		this.subcomponent = (String) subcomponent;
		
	}

	


	public ReimbursementModel() {
		// TODO Auto-generated constructor stub
	}




	public String getReimbursementReqId() {
		return reimbursementReqId;
	}




	public void setReimbursementReqId(String reimbursementReqId) {
		this.reimbursementReqId = reimbursementReqId;
	}




	public String getRequisitionId() {
		return requisitionId;
	}




	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}




	public String getEmpName() {
		return empName;
	}




	public void setEmpName(String empName) {
		this.empName = empName;
	}




	public String getPlaceName() {
		return placeName;
	}




	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}




	public String getFromDate() {
		return fromDate;
	}




	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}




	public String getToDate() {
		return toDate;
	}




	public void setToDate(String toDate) {
		this.toDate = toDate;
	}




	public String getPurpose() {
		return purpose;
	}




	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}




	




	public Double getAdvanceReq() {
		return advanceReq;
	}




	public void setAdvanceReq(Double advanceReq) {
		this.advanceReq = advanceReq;
	}




	public Double getAdvanceAmount() {
		return advanceAmount;
	}




	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}




	public String getApproveStatus() {
		return approveStatus;
	}




	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}




	public String getSlNo() {
		return slNo;
	}




	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}




	public String getReimTypeId() {
		return reimTypeId;
	}




	public void setReimTypeId(String reimTypeId) {
		this.reimTypeId = reimTypeId;
	}




	/*
	 * public String getReimTypeName() { return reimTypeName; }
	 * 
	 * 
	 * 
	 * 
	 * public void setReimTypeName(String reimTypeName) { this.reimTypeName =
	 * reimTypeName; }
	 */




	public String getReimPolicyId() {
		return reimPolicyId;
	}




	public void setReimPolicyId(String reimPolicyId) {
		this.reimPolicyId = reimPolicyId;
	}




	/*
	 * public String getReimPolicyName() { return reimPolicyName; }
	 * 
	 * 
	 * 
	 * 
	 * public void setReimPolicyName(String reimPolicyName) { this.reimPolicyName =
	 * reimPolicyName; }
	 */




	public String getExpenseDate() {
		return expenseDate;
	}




	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}




	public String getExpenseDesc() {
		return expenseDesc;
	}




	public void setExpenseDesc(String expenseDesc) {
		this.expenseDesc = expenseDesc;
	}








	public Double getExpenseAmount() {
		return expenseAmount;
	}




	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}




	public String getReferenceNo() {
		return referenceNo;
	}




	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}




	/*
	 * public List<ReimbursementDocumentModel> getDocumentList() { return
	 * documentList; }
	 * 
	 * 
	 * 
	 * 
	 * public void setDocumentList(List<ReimbursementDocumentModel> documentList) {
	 * this.documentList = documentList; }
	 */



	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public String getCreatedOn() {
		return createdOn;
	}




	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}




	public String getUpdatedBy() {
		return updatedBy;
	}




	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}




	public String getUpdatedOn() {
		return updatedOn;
	}




	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}




	public String getDocName() {
		return docName;
	}




	public void setDocName(String docName) {
		this.docName = docName;
	}




	public String getDocumentName() {
		return documentName;
	}




	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}




	public String getTotal() {
		return total;
	}




	public void setTotal(String total) {
		this.total = total;
	}




	public String getModule() {
		return module;
	}




	public void setModule(String module) {
		this.module = module;
	}




	public String getComponent() {
		return component;
	}




	public void setComponent(String component) {
		this.component = component;
	}




	public String getSubcomponent() {
		return subcomponent;
	}




	public void setSubcomponent(String subcomponent) {
		this.subcomponent = subcomponent;
	}




	public Double getAamttPaid() {
		return aamttPaid;
	}




	public void setAamttPaid(Double aamttPaid) {
		this.aamttPaid = aamttPaid;
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