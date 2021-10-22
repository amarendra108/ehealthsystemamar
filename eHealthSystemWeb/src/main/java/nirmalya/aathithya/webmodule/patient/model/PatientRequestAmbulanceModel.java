package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientRequestAmbulanceModel {
	private String orderId;
	private String patientId;
	private String ambulanceId;
	private String ambulaneOwnerName;
	private String frmDestination;
	private String toDestination;
	private String patientNote;
	private String dateId;
	private String status;
	
	public PatientRequestAmbulanceModel() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getAmbulaneOwnerName() {
		return ambulaneOwnerName;
	}

	public void setAmbulaneOwnerName(String ambulaneOwnerName) {
		this.ambulaneOwnerName = ambulaneOwnerName;
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
