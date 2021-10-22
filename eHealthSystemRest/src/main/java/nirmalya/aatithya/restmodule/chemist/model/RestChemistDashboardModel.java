package nirmalya.aatithya.restmodule.chemist.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestChemistDashboardModel {
	
	private String todayAppointment;
	private String pendingRequest;
	private String bookedRequest;
	private String rejectedRequest;
	
	
	public RestChemistDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public RestChemistDashboardModel(Object todayAppointment, Object pendingRequest, Object bookedRequest,Object rejectedRequest) {
		super();
		this.todayAppointment = (String) todayAppointment;
		this.pendingRequest = (String) pendingRequest;
		this.bookedRequest = (String) bookedRequest;
		this.rejectedRequest = (String) rejectedRequest;
	}





	public String getTodayAppointment() {
		return todayAppointment;
	}


	public void setTodayAppointment(String todayAppointment) {
		this.todayAppointment = todayAppointment;
	}


	public String getPendingRequest() {
		return pendingRequest;
	}


	public void setPendingRequest(String pendingRequest) {
		this.pendingRequest = pendingRequest;
	}


	public String getBookedRequest() {
		return bookedRequest;
	}


	public void setBookedRequest(String bookedRequest) {
		this.bookedRequest = bookedRequest;
	}


	public String getRejectedRequest() {
		return rejectedRequest;
	}


	public void setRejectedRequest(String rejectedRequest) {
		this.rejectedRequest = rejectedRequest;
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
