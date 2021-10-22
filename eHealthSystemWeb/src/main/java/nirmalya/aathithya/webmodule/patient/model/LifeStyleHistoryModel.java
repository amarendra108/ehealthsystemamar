package nirmalya.aathithya.webmodule.patient.model;

import java.math.BigInteger;

public class LifeStyleHistoryModel {
	private BigInteger lifeStyleId;
	private BigInteger patientId;
	private Integer smokingId; 
	private String smoking;
	private Integer alcoholId;
	private String alcohol;
	private String diet;
	private String exercise;
	private String occupation;
	private String pets;
	
	
	
	public BigInteger getLifeStyleId() {
		return lifeStyleId;
	}
	public void setLifeStyleId(BigInteger lifeStyleId) {
		this.lifeStyleId = lifeStyleId;
	}
	public BigInteger getPatientId() {
		return patientId;
	}
	public void setPatientId(BigInteger patientId) {
		this.patientId = patientId;
	}
	public Integer getSmokingId() {
		return smokingId;
	}
	public void setSmokingId(Integer smokingId) {
		this.smokingId = smokingId;
	}
	
	
	public String getSmoking() {
		return smoking;
	}
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	public String getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	public Integer getAlcoholId() {
		return alcoholId;
	}
	public void setAlcoholId(Integer alcoholId) {
		this.alcoholId = alcoholId;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPets() {
		return pets;
	}
	public void setPets(String pets) {
		this.pets = pets;
	}
	
	
	
}
