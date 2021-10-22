package nirmalya.aathithya.webmodule.employee.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementPaymentModel {
	private String paymentId;
	private String paymentMode;
	private String bankName;
	private String branchName;
	private String accNo;
	//private String accHolderName;
	//private String employeeId;
	private Double total;
	private Double amtPaid;
	private Double advRequired;
	//private Boolean payStatus;
	private String transactionDate;
	private String chequeNo;
	private String transactionNo;
	private String createdBy;
	public ReimbursementPaymentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public String getAccNo() {
		return accNo;
	}


	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}


	

	public String getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getChequeNo() {
		return chequeNo;
	}


	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Double getAmtPaid() {
		return amtPaid;
	}


	public void setAmtPaid(Double amtPaid) {
		this.amtPaid = amtPaid;
	}


	public Double getAdvRequired() {
		return advRequired;
	}


	public void setAdvRequired(Double advRequired) {
		this.advRequired = advRequired;
	}


	public String getTransactionNo() {
		return transactionNo;
	}


	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}


	public String getCreatedBy() {
		return createdBy;
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
