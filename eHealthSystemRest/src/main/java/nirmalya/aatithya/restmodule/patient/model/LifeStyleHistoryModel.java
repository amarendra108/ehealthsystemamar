package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	public LifeStyleHistoryModel(Object lifeStyleId, Object patientId,Object smokingId, Object smoking, Object alcoholId,
			Object alcohol, Object diet, Object exercise, Object occupation, Object pets) {
		super();
		this.lifeStyleId = (BigInteger)lifeStyleId;
		this.patientId = (BigInteger)patientId;
		this.smokingId = (Integer)smokingId;
		this.smoking = (String)smoking;
		this.alcoholId =(Integer) alcoholId;
		this.alcohol = (String)alcohol;
		this.diet = (String)diet;
		this.exercise =(String) exercise;
		this.occupation =(String) occupation;
		this.pets = (String)pets;
	}
	
	public LifeStyleHistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}


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
	
	public Integer getAlcoholId() {
		return alcoholId;
	}
	public void setAlcoholId(Integer alcoholId) {
		this.alcoholId = alcoholId;
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
