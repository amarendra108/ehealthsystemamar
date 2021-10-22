package nirmalya.aathithya.webmodule.patient.model;

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
	
	
	
}
