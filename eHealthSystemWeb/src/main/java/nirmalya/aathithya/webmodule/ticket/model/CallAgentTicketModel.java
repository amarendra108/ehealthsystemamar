package nirmalya.aathithya.webmodule.ticket.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CallAgentTicketModel {
	private String customerId;
	private String phoneNo;
	private String customerName;
	private String customerLocation;
	private String industrialType;
	private String customerType;
	private String alternatePhoneNo;
	private String email;
	private Boolean callDisconnected;
	private String callQueue;
	private String callReason;
	private String productType;
	private String commentBox;
	private String createdBy;
	private String serviceCenter;
	private Boolean assignType;
	private String complaintCategory;
	private String complaintDesc;
	private String complainLocation;
	private String complainAssist;
	private String saleDesc;
	private String saleQuantity;
	private String marketingDesc;
	private String marketingQuantity;
	private String agentId;
	private String action;
	private String ticketId;
	private String ticketStatus;
	private String dateTime;
	private BigInteger ageing;
	private String lastDate;
	private String callQueueId;
	private String tat;
	private String level;

	public CallAgentTicketModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public String getIndustrialType() {
		return industrialType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public String getAlternatePhoneNo() {
		return alternatePhoneNo;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getCallDisconnected() {
		return callDisconnected;
	}

	public String getCallQueue() {
		return callQueue;
	}

	public String getCallReason() {
		return callReason;
	}

	public String getProductType() {
		return productType;
	}

	public String getCommentBox() {
		return commentBox;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getServiceCenter() {
		return serviceCenter;
	}

	public Boolean getAssignType() {
		return assignType;
	}

	public String getComplaintCategory() {
		return complaintCategory;
	}

	public String getComplaintDesc() {
		return complaintDesc;
	}

	public String getComplainLocation() {
		return complainLocation;
	}

	public String getComplainAssist() {
		return complainAssist;
	}

	public String getSaleDesc() {
		return saleDesc;
	}

	public String getSaleQuantity() {
		return saleQuantity;
	}

	public String getMarketingDesc() {
		return marketingDesc;
	}

	public String getMarketingQuantity() {
		return marketingQuantity;
	}

	public String getAgentId() {
		return agentId;
	}

	public String getAction() {
		return action;
	}

	public String getTicketId() {
		return ticketId;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public String getDateTime() {
		return dateTime;
	}

	public BigInteger getAgeing() {
		return ageing;
	}

	public String getLastDate() {
		return lastDate;
	}

	public String getCallQueueId() {
		return callQueueId;
	}

	public String getTat() {
		return tat;
	}

	public String getLevel() {
		return level;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public void setIndustrialType(String industrialType) {
		this.industrialType = industrialType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public void setAlternatePhoneNo(String alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCallDisconnected(Boolean callDisconnected) {
		this.callDisconnected = callDisconnected;
	}

	public void setCallQueue(String callQueue) {
		this.callQueue = callQueue;
	}

	public void setCallReason(String callReason) {
		this.callReason = callReason;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setCommentBox(String commentBox) {
		this.commentBox = commentBox;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setServiceCenter(String serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

	public void setAssignType(Boolean assignType) {
		this.assignType = assignType;
	}

	public void setComplaintCategory(String complaintCategory) {
		this.complaintCategory = complaintCategory;
	}

	public void setComplaintDesc(String complaintDesc) {
		this.complaintDesc = complaintDesc;
	}

	public void setComplainLocation(String complainLocation) {
		this.complainLocation = complainLocation;
	}

	public void setComplainAssist(String complainAssist) {
		this.complainAssist = complainAssist;
	}

	public void setSaleDesc(String saleDesc) {
		this.saleDesc = saleDesc;
	}

	public void setSaleQuantity(String saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public void setMarketingDesc(String marketingDesc) {
		this.marketingDesc = marketingDesc;
	}

	public void setMarketingQuantity(String marketingQuantity) {
		this.marketingQuantity = marketingQuantity;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setAgeing(BigInteger ageing) {
		this.ageing = ageing;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public void setCallQueueId(String callQueueId) {
		this.callQueueId = callQueueId;
	}

	public void setTat(String tat) {
		this.tat = tat;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		ObjectMapper  mapperObj=new ObjectMapper();
		String jsonStr;
		try{
			jsonStr=mapperObj.writeValueAsString(this);
		}catch(IOException ex){
			
			jsonStr=ex.toString();
		}
		return jsonStr;
	}
}
