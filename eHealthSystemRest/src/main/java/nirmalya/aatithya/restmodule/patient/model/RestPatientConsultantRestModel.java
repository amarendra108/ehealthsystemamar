package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientConsultantRestModel {
	private String formDate;
	private String formTime;
	private String doctor;
	private String notes;
	


	
	public String getFormDate() {
		return formDate;
	}




	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}




	public String getFormTime() {
		return formTime;
	}




	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}




	public String getDoctor() {
		return doctor;
	}




	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}




	public String getNotes() {
		return notes;
	}




	public void setNotes(String notes) {
		this.notes = notes;
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
