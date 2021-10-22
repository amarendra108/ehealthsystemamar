package nirmalya.aatithya.restmodule.employee.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;



public class ReimbrusementRestOtherModel {
	private String otherReimbrusementId;
	private String date;
	private String travellingPurpose;
	private String typeReimbrusement;
	private String expenseDate;
	private String descExpense;
	private Double expenseAmount;
	
	private String docName;
	private String referenceNo;
	private String createdBy;
	private String updatedBy;
	private String createdOn;
	private String updatedOn;
	public ReimbrusementRestOtherModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimbrusementRestOtherModel(Object otherReimbrusementId, Object date, Object travellingPurpose,
			Object typeReimbrusement, Object expenseDate, Object descExpense, Double expenseAmount,Object docName,
			Object referenceNo) {
		super();
		this.otherReimbrusementId = (String)otherReimbrusementId;
		this.date = (String)date;
		this.travellingPurpose = (String)travellingPurpose;
		this.typeReimbrusement = (String)typeReimbrusement;
		this.expenseDate = (String)expenseDate;
		this.descExpense = (String)descExpense;
		this.expenseAmount = expenseAmount;
		this.docName = (String)docName;
		this.referenceNo = (String)referenceNo;
	}
	public String getOtherReimbrusementId() {
		return otherReimbrusementId;
	}
	public void setOtherReimbrusementId(String otherReimbrusementId) {
		this.otherReimbrusementId = otherReimbrusementId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTravellingPurpose() {
		return travellingPurpose;
	}
	public void setTravellingPurpose(String travellingPurpose) {
		this.travellingPurpose = travellingPurpose;
	}
	public String getTypeReimbrusement() {
		return typeReimbrusement;
	}
	public void setTypeReimbrusement(String typeReimbrusement) {
		this.typeReimbrusement = typeReimbrusement;
	}
	public String getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getDescExpense() {
		return descExpense;
	}
	public void setDescExpense(String descExpense) {
		this.descExpense = descExpense;
	}
	
	
	public Double getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
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
