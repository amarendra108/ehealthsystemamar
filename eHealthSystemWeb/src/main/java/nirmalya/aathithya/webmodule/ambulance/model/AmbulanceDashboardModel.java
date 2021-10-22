package nirmalya.aathithya.webmodule.ambulance.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AmbulanceDashboardModel {
	
	//for countbox start
	private String numberofAmbulance;
	private String numberofDriver;
	private String numberofStaff;
	private String numberofPatientRegistration;
	private String totalBooked;
	private String totalCancel;
	private String totalPending;
	private String feedback;
	
	private String walkinreg;
	private String requested;
	private String approved;
	private String pending;
	private String completed;
	private String drivers;
	private String totalambulance;
	private String ambulancelocation;
	
	//for countbox end
	
	//for ag-grid start
	private String orderid;
	private String patientid;
	private String name;
	private String gender;
	private String date;
	private String age;
	private String state;
	private String status;
	private String notes;
	//for ag-grid end
	
	public AmbulanceDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNumberofAmbulance() {
		return numberofAmbulance;
	}
	public void setNumberofAmbulance(String numberofAmbulance) {
		this.numberofAmbulance = numberofAmbulance;
	}
	public String getNumberofDriver() {
		return numberofDriver;
	}
	public void setNumberofDriver(String numberofDriver) {
		this.numberofDriver = numberofDriver;
	}
	public String getNumberofStaff() {
		return numberofStaff;
	}
	public void setNumberofStaff(String numberofStaff) {
		this.numberofStaff = numberofStaff;
	}
	public String getNumberofPatientRegistration() {
		return numberofPatientRegistration;
	}
	public void setNumberofPatientRegistration(String numberofPatientRegistration) {
		this.numberofPatientRegistration = numberofPatientRegistration;
	}
	public String getTotalBooked() {
		return totalBooked;
	}
	public void setTotalBooked(String totalBooked) {
		this.totalBooked = totalBooked;
	}
	public String getTotalCancel() {
		return totalCancel;
	}
	public void setTotalCancel(String totalCancel) {
		this.totalCancel = totalCancel;
	}
	public String getTotalPending() {
		return totalPending;
	}
	public void setTotalPending(String totalPending) {
		this.totalPending = totalPending;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getWalkinreg() {
		return walkinreg;
	}
	public void setWalkinreg(String walkinreg) {
		this.walkinreg = walkinreg;
	}
	public String getRequested() {
		return requested;
	}
	public void setRequested(String requested) {
		this.requested = requested;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getDrivers() {
		return drivers;
	}
	public void setDrivers(String drivers) {
		this.drivers = drivers;
	}
	public String getTotalambulance() {
		return totalambulance;
	}
	public void setTotalambulance(String totalambulance) {
		this.totalambulance = totalambulance;
	}
	public String getAmbulancelocation() {
		return ambulancelocation;
	}
	public void setAmbulancelocation(String ambulancelocation) {
		this.ambulancelocation = ambulancelocation;
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
