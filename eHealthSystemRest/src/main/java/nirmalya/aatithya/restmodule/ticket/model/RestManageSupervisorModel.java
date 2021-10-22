package nirmalya.aatithya.restmodule.ticket.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestManageSupervisorModel {
	private String ticketid;
	private String phone;
	private String callQueue;
	private String callReason;
	private String agent;
	private String dateTime;
	private String ticketStatus;
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
	private String lastDate;
	private BigInteger ageing;
	private String supports;
	private String level;
	private Boolean escalation;
	private String complainLocation;
	private String complainAsst;

	public RestManageSupervisorModel(Object ticketid, Object phone, Object callQueue, Object callReason, Object agent,
			Object ticketStatus, Object dateTime, Object customerName, Object customerLocation, Object productType,
			Object serviceCenter, Object complainCategory, Object complainDescription, Object quantity,
			Object salesDescription, Object marketingDescription, Object comment, Object lastDate, Object ageing,
			Object supports, Object level, Object escalation, Object complainLocation, Object complainAsst) {
		super();

		try {
			this.ticketid = (String) ticketid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.phone = (String) phone;
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
			this.agent = (String) agent;
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
			this.productType = (String) productType;
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
			this.complainCategory = (String) complainCategory;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.complainDescription = (String) complainDescription;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.quantity = (String) quantity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.salesDescription = (String) salesDescription;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.marketingDescription = (String) marketingDescription;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.comment = (String) comment;
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
			this.supports = (String) supports;
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
		try {
			this.escalation = (Boolean) escalation;
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
			this.complainAsst = (String) complainAsst;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
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

	public Boolean getEscalation() {
		return escalation;
	}

	public void setEscalation(Boolean escalation) {
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
