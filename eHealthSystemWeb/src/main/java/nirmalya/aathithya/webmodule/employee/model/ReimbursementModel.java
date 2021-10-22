package nirmalya.aathithya.webmodule.employee.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementModel {

	private String reimbursementReqId;
	private String requisitionId;
	private String empName;
	private String placeName;
	private String fromDate;
	private String toDate;
	private String purpose;
	private String advanceReq;
	private Double advanceAmount;
	private Double aamttPaid;
	private String approveStatus;
	private String slNo;
	private String reimTypeId;
	private String reimTypeName;
	private String reimPolicyId;
	private String reimPolicyName;
	private String expenseDate;
	private String expenseDesc;
	private String expenseAmount;
	private String referenceNo;
	private String documentName;
	private String docName;
	//private List<ReimbursementDocumentModel> documentList;
	
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	private String total;
	private String module;
	private String component;
	private String subcomponent;
	public ReimbursementModel() {
		super();
	}
	

	public String getReimbursementReqId() {
		return reimbursementReqId;
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


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}


	public void setReimbursementReqId(String reimbursementReqId) {
		this.reimbursementReqId = reimbursementReqId;
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


	public String getAdvanceReq() {
		return advanceReq;
	}


	public void setAdvanceReq(String advanceReq) {
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
	
	


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


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


	
	public String getReimTypeId() {
		return reimTypeId;
	}


	public void setReimTypeId(String reimTypeId) {
		this.reimTypeId = reimTypeId;
	}


	public String getReimTypeName() {
		return reimTypeName;
	}


	public void setReimTypeName(String reimTypeName) {
		this.reimTypeName = reimTypeName;
	}


	public String getReimPolicyId() {
		return reimPolicyId;
	}


	public void setReimPolicyId(String reimPolicyId) {
		this.reimPolicyId = reimPolicyId;
	}


	public String getReimPolicyName() {
		return reimPolicyName;
	}


	public void setReimPolicyName(String reimPolicyName) {
		this.reimPolicyName = reimPolicyName;
	}


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


	public String getExpenseAmount() {
		return expenseAmount;
	}


	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}


	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	public String getReferenceNo() {
		return referenceNo;
	}


	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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
