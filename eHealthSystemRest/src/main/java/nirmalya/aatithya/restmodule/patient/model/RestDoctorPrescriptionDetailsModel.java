package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDoctorPrescriptionDetailsModel {

	private String medicineName;
	private String medType;
	private String dosage;
	private String morning;
	private String afterNoon;
	private String evening;
	private String doctor;
	private String prescrRemark;

	public RestDoctorPrescriptionDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestDoctorPrescriptionDetailsModel(Object medicineName, Object medType, Object dosage, Object morning,
			Object afterNoon, Object evening, Object doctor, Object prescrRemark) {
		super();
		this.medicineName = (String) medicineName;
		this.medType = (String) medType;
		this.dosage = (String) dosage;
		this.morning = (String) morning;
		this.afterNoon = (String) afterNoon;
		this.evening = (String) evening;
		this.doctor = (String) doctor;

	}

	public String getPrescrRemark() {
		return prescrRemark;
	}

	public void setPrescrRemark(String prescrRemark) {
		this.prescrRemark = prescrRemark;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedType() {
		return medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getMorning() {
		return morning;
	}

	public void setMorning(String morning) {
		this.morning = morning;
	}

	public String getAfterNoon() {
		return afterNoon;
	}

	public void setAfterNoon(String afterNoon) {
		this.afterNoon = afterNoon;
	}

	public String getEvening() {
		return evening;
	}

	public void setEvening(String evening) {
		this.evening = evening;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
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
