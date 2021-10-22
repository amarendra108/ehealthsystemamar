	package nirmalya.aatithya.restmodule.ticket.model;

	import java.io.IOException;
	import java.math.BigInteger;

	import com.fasterxml.jackson.databind.ObjectMapper;

	public class CallAgentTicketRestModel {
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
		private String ticketId;
		private String ticketStatus;
		private String dateTime;
		private BigInteger ageing;
		private String lastDate;
		private String callQueueId;
		private String tat;
		private String level;

		public CallAgentTicketRestModel() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CallAgentTicketRestModel(Object customerId, Object phoneNo, Object customerName, Object customerLocation,
				Object industrialType, Object customerType, Object alternatePhoneNo, Object email, Object ticketId,
				Object callQueue, Object callReason, Object agentId, Object ticketStatus, Object dateTime,
				Object callDisconnected, Object productType, Object commentBox, Object serviceCenter,
				Object complaintCategory, Object complaintDesc, Object complainLocation, Object complainAssist,
				Object marketingDesc, Object marketingQuantity, Object saleDesc, Object saleQuantity, Object lastDate,
				Object ageing, Object callQueueId, Object tat, Object level) {

			super();
			// TODO Auto-generated constructor stub
			try {
				this.customerId = (String) customerId;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				this.phoneNo = (String) phoneNo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				this.customerName = (String) customerName;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.customerLocation = (String) customerLocation;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				this.industrialType = (String) industrialType;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.customerType = (String) customerType;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.alternatePhoneNo = (String) alternatePhoneNo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.email = (String) email;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.ticketId = (String) ticketId;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.callQueue = (String) callQueue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.callReason = (String) callReason;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.agentId = (String) agentId;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.ticketStatus = (String) ticketStatus;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.dateTime = (String) dateTime;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.callDisconnected = (Boolean) callDisconnected;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.productType = (String) productType;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.commentBox = (String) commentBox;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.serviceCenter = (String) serviceCenter;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.complaintCategory = (String) complaintCategory;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.complaintDesc = (String) complaintDesc;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.complainLocation = (String) complainLocation;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.complainAssist = (String) complainAssist;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.marketingDesc = (String) marketingDesc;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.marketingQuantity = (String) marketingQuantity;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.saleDesc = (String) saleDesc;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.saleQuantity = (String) saleQuantity;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.lastDate = (String) lastDate;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.ageing = (BigInteger) ageing;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.callQueueId = (String) callQueueId;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.tat = (String) tat;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.level = (String) level;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
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

		public String getIndustrialType() {
			return industrialType;
		}

		public void setIndustrialType(String industrialType) {
			this.industrialType = industrialType;
		}

		public String getCustomerType() {
			return customerType;
		}

		public void setCustomerType(String customerType) {
			this.customerType = customerType;
		}

		public String getAlternatePhoneNo() {
			return alternatePhoneNo;
		}

		public void setAlternatePhoneNo(String alternatePhoneNo) {
			this.alternatePhoneNo = alternatePhoneNo;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Boolean getCallDisconnected() {
			return callDisconnected;
		}

		public void setCallDisconnected(Boolean callDisconnected) {
			this.callDisconnected = callDisconnected;
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

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

		public String getCommentBox() {
			return commentBox;
		}

		public void setCommentBox(String commentBox) {
			this.commentBox = commentBox;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public String getServiceCenter() {
			return serviceCenter;
		}

		public void setServiceCenter(String serviceCenter) {
			this.serviceCenter = serviceCenter;
		}

		public Boolean getAssignType() {
			return assignType;
		}

		public void setAssignType(Boolean assignType) {
			this.assignType = assignType;
		}

		public String getComplaintCategory() {
			return complaintCategory;
		}

		public void setComplaintCategory(String complaintCategory) {
			this.complaintCategory = complaintCategory;
		}

		public String getComplaintDesc() {
			return complaintDesc;
		}

		public void setComplaintDesc(String complaintDesc) {
			this.complaintDesc = complaintDesc;
		}

		public String getComplainLocation() {
			return complainLocation;
		}

		public void setComplainLocation(String complainLocation) {
			this.complainLocation = complainLocation;
		}

		public String getComplainAssist() {
			return complainAssist;
		}

		public void setComplainAssist(String complainAssist) {
			this.complainAssist = complainAssist;
		}

		public String getSaleDesc() {
			return saleDesc;
		}

		public void setSaleDesc(String saleDesc) {
			this.saleDesc = saleDesc;
		}

		public String getSaleQuantity() {
			return saleQuantity;
		}

		public void setSaleQuantity(String saleQuantity) {
			this.saleQuantity = saleQuantity;
		}

		public String getMarketingDesc() {
			return marketingDesc;
		}

		public void setMarketingDesc(String marketingDesc) {
			this.marketingDesc = marketingDesc;
		}

		public String getMarketingQuantity() {
			return marketingQuantity;
		}

		public void setMarketingQuantity(String marketingQuantity) {
			this.marketingQuantity = marketingQuantity;
		}

		public String getAgentId() {
			return agentId;
		}

		public void setAgentId(String agentId) {
			this.agentId = agentId;
		}

		public String getTicketId() {
			return ticketId;
		}

		public void setTicketId(String ticketId) {
			this.ticketId = ticketId;
		}

		public String getTicketStatus() {
			return ticketStatus;
		}

		public void setTicketStatus(String ticketStatus) {
			this.ticketStatus = ticketStatus;
		}

		public String getDateTime() {
			return dateTime;
		}

		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}

		public BigInteger getAgeing() {
			return ageing;
		}

		public void setAgeing(BigInteger ageing) {
			this.ageing = ageing;
		}

		public String getLastDate() {
			return lastDate;
		}

		public void setLastDate(String lastDate) {
			this.lastDate = lastDate;
		}

		public String getCallQueueId() {
			return callQueueId;
		}

		public void setCallQueueId(String callQueueId) {
			this.callQueueId = callQueueId;
		}

		public String getTat() {
			return tat;
		}

		public void setTat(String tat) {
			this.tat = tat;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
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
