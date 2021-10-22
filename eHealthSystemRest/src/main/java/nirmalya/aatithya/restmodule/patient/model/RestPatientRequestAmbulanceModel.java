package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientRequestAmbulanceModel {
	private String orderId;
	private String patientId;
	private String ambulanceId;
	private String ambulaneOwnerName;
	private String frmDestination;
	private String toDestination;
	private String patientNote;
	private String dateId;
	private String status;
	
	
	
	public RestPatientRequestAmbulanceModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RestPatientRequestAmbulanceModel(
			Object ambulaneOwnerName,Object ambulanceId) {
		super();
		
		this.ambulaneOwnerName =(String) ambulaneOwnerName;
		this.ambulanceId =(String) ambulanceId;
		
	}



	public RestPatientRequestAmbulanceModel(Object orderId, Object patientId, Object ambulanceId,
			Object ambulaneOwnerName, Object frmDestination, Object toDestination, Object patientNote, Object dateId,Object status) {
		super();
		this.orderId =(String) orderId;
		this.patientId =(String) patientId;
		this.ambulanceId =(String) ambulanceId;
		this.ambulaneOwnerName =(String) ambulaneOwnerName;
		this.frmDestination =(String) frmDestination;
		this.toDestination =(String) toDestination;
		this.patientNote = (String)patientNote;
		this.dateId =(String) dateId;
		this.status =(String) status;
	}



	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public String getPatientId() {
		return patientId;
	}



	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}



	public String getAmbulanceId() {
		return ambulanceId;
	}



	public void setAmbulanceId(String ambulanceId) {
		this.ambulanceId = ambulanceId;
	}



	public String getAmbulaneOwnerName() {
		return ambulaneOwnerName;
	}



	public void setAmbulaneOwnerName(String ambulaneOwnerName) {
		this.ambulaneOwnerName = ambulaneOwnerName;
	}



	public String getFrmDestination() {
		return frmDestination;
	}



	public void setFrmDestination(String frmDestination) {
		this.frmDestination = frmDestination;
	}



	public String getToDestination() {
		return toDestination;
	}



	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}



	public String getPatientNote() {
		return patientNote;
	}



	public void setPatientNote(String patientNote) {
		this.patientNote = patientNote;
	}



	public String getDateId() {
		return dateId;
	}



	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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
