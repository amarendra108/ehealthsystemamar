package nirmalya.aatithya.restmodule.ticket.model;

public class TicketDashboardRestModel {
	private Double open;
	private Double closed;
	private Double escalated;
	private Double pending;
	private Double incomplete;
	public TicketDashboardRestModel(Object open, Object closed, Object escalated, Object pending,
			Object incomplete) {
		super();
		this.open = (Double)open;
		this.closed = (Double)closed;
		this.escalated = (Double)escalated;
		this.pending = (Double)pending;
		this.incomplete = (Double)incomplete;
	}
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
	public TicketDashboardRestModel(Object location, Object newCustomer, Object existingCustomer, Object companyEmployee) {
		super();
		this.location = (String)location;
		this.newCustomer = (Integer)newCustomer;
		this.existingCustomer = (Integer)existingCustomer;
		this.companyEmployee = (Integer)companyEmployee;
	}
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
