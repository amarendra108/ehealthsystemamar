package nirmalya.aathithya.webmodule.doctor.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorDashboardCountModel {
	private String  appointment;
	private String  pending;
	private String  booked;
	private String  rejected;
	
	public String getAppointment() {
		return appointment;
	}
	
	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}
	
	public String getPending() {
		return pending;
	}
	
	public void setPending(String pending) {
		this.pending = pending;
	}
	
	public String getBooked() {
		return booked;
	}
	
	public void setBooked(String booked) {
		this.booked = booked;
	}
	
	public String getRejected() {
		return rejected;
	}
	
	public void setRejected(String rejected) {
		this.rejected = rejected;
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
