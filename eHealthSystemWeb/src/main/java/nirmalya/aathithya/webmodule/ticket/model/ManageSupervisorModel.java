package nirmalya.aathithya.webmodule.ticket.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ManageSupervisorModel {
	private String ticketid;
	private String phone;
	private String ticketStatus;
	private String callQueue; 
	private String  callReason;
	private String agent;
	private String dateTime;
	private String createdBy;       
	private String action;
	private String customerName;
	private String customerLocation;
	private String productType;
	private String serviceCenter;
	private String complainCategory;
	private String complainDescription;
	private String quantity;
	private String salesDescription;
	private String marketingDescription;
	private String comment;
	private String lastStatus;
	private String lastComments;
	private String lastDate;
	private BigInteger ageing;
	private String supports;
	private String level;
	private String escalation;
	private String complainLocation;
	private String complainAsst;
	
	public ManageSupervisorModel() {
		super();
	}

	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getCallQueue() {
		return callQueue;
	}

	public void setCallQueue(String callQueue) {
		this.callQueue = callQueue;
	}

	public String getCallReason() {
		return callReason;
	}

	public void setCallReason(String callReason) {
		this.callReason = callReason;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(String serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

	public String getComplainCategory() {
		return complainCategory;
	}

	public void setComplainCategory(String complainCategory) {
		this.complainCategory = complainCategory;
	}

	public String getComplainDescription() {
		return complainDescription;
	}

	public void setComplainDescription(String complainDescription) {
		this.complainDescription = complainDescription;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSalesDescription() {
		return salesDescription;
	}

	public void setSalesDescription(String salesDescription) {
		this.salesDescription = salesDescription;
	}

	public String getMarketingDescription() {
		return marketingDescription;
	}

	public void setMarketingDescription(String marketingDescription) {
		this.marketingDescription = marketingDescription;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getLastComments() {
		return lastComments;
	}

	public void setLastComments(String lastComments) {
		this.lastComments = lastComments;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public BigInteger getAgeing() {
		return ageing;
	}

	public void setAgeing(BigInteger ageing) {
		this.ageing = ageing;
	}

	public String getSupports() {
		return supports;
	}

	public void setSupports(String supports) {
		this.supports = supports;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEscalation() {
		return escalation;
	}

	public void setEscalation(String escalation) {
		this.escalation = escalation;
	}

	public String getComplainLocation() {
		return complainLocation;
	}

	public void setComplainLocation(String complainLocation) {
		this.complainLocation = complainLocation;
	}

	public String getComplainAsst() {
		return complainAsst;
	}

	public void setComplainAsst(String complainAsst) {
		this.complainAsst = complainAsst;
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
