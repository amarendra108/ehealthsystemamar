package nirmalya.aathithya.webmodule.ticket.model;

import java.util.ArrayList;
import java.util.List;

public class TicketDashboardModel {
	private Double open;
	private Double closed;
	private Double escalated;
	private Double pending;
	private Double incomplete;
	
	
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getClosed() {
		return closed;
	}
	public void setClosed(Double closed) {
		this.closed = closed;
	}
	public Double getEscalated() {
		return escalated;
	}
	public void setEscalated(Double escalated) {
		this.escalated = escalated;
	}
	public Double getPending() {
		return pending;
	}
	public void setPending(Double pending) {
		this.pending = pending;
	}
	public Double getIncomplete() {
		return incomplete;
	}
	public void setIncomplete(Double incomplete) {
		this.incomplete = incomplete;
	}

	private String location;
	private Integer newCustomer;
	private Integer existingCustomer;
	private Integer companyEmployee;


	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(Integer newCustomer) {
		this.newCustomer = newCustomer;
	}
	public Integer getExistingCustomer() {
		return existingCustomer;
	}
	public void setExistingCustomer(Integer existingCustomer) {
		this.existingCustomer = existingCustomer;
	}
	public Integer getCompanyEmployee() {
		return companyEmployee;
	}
	public void setCompanyEmployee(Integer companyEmployee) {
		this.companyEmployee = companyEmployee;
	}
	
}
