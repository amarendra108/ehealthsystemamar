package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientHistoryModel {
	//MAJOR ILLNES
	private String id;
	private String name; 
	private String date;
	private String doctor;
	private String notes;
	
	//MAJOR SURGERY
	private String surgeryId;
	private String surgeryName; 
	private String surgeryDate;
	private String surgeryDoctor;
	private String surgeryNotes;
	private String surgeryNote;
	
	//MEDICAL CONDITION
		private Integer medConditionId;
		private String medConditionName; 
		
	public PatientHistoryModel(Object id, Object name, Object date, Object doctor, Object notes) {
		super();
		this.id = (String)id;
		this.name =(String) name;
		this.date = (String)date;
		this.doctor = (String)doctor;
		this.notes = (String)notes;
	}
	
	
	
	public PatientHistoryModel(Object surgeryId, Object surgeryName, Object surgeryDate, Object surgeryDoctor,
			Object surgeryNotes,Object surgeryNote) {
		super();
		this.surgeryId = (String)surgeryId;
		this.surgeryName = (String)surgeryName;
		this.surgeryDate = (String)surgeryDate;
		this.surgeryDoctor =(String) surgeryDoctor;
		this.surgeryNotes = (String)surgeryNotes;
	}


	public PatientHistoryModel(Object medConditionId, Object medConditionName) {
		super();
		this.medConditionId = (Integer)medConditionId;
		this.medConditionName = (String)medConditionName;
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	
	public String getSurgeryId() {
		return surgeryId;
	}



	public void setSurgeryId(String surgeryId) {
		this.surgeryId = surgeryId;
	}



	public String getSurgeryName() {
		return surgeryName;
	}



	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}



	public String getSurgeryDate() {
		return surgeryDate;
	}



	public void setSurgeryDate(String surgeryDate) {
		this.surgeryDate = surgeryDate;
	}



	public String getSurgeryDoctor() {
		return surgeryDoctor;
	}



	public void setSurgeryDoctor(String surgeryDoctor) {
		this.surgeryDoctor = surgeryDoctor;
	}



	public String getSurgeryNotes() {
		return surgeryNotes;
	}



	public void setSurgeryNotes(String surgeryNotes) {
		this.surgeryNotes = surgeryNotes;
	}



	public String getSurgeryNote() {
		return surgeryNote;
	}



	public void setSurgeryNote(String surgeryNote) {
		this.surgeryNote = surgeryNote;
	}



	



	public Integer getMedConditionId() {
		return medConditionId;
	}



	public void setMedConditionId(Integer medConditionId) {
		this.medConditionId = medConditionId;
	}



	public String getMedConditionName() {
		return medConditionName;
	}



	public void setMedConditionName(String medConditionName) {
		this.medConditionName = medConditionName;
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
