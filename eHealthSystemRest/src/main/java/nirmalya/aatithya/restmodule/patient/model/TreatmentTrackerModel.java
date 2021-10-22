package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TreatmentTrackerModel {
	//private Integer treatmentId;
	private String medicine;
	//private String dose; 
	private String days;
	private String fromDate;
	private String toDate;
	private String morning;
	private String noon;
	private String evening;
	private String doctor;
	private String remark;
	public TreatmentTrackerModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TreatmentTrackerModel(Object medicine, Object days, Object fromDate,
			Object toDate, Object morning, Object noon, Object evening, Object doctor, Object remark) {
		super();
		//this.treatmentId = (Integer)treatmentId;
		this.medicine = (String)medicine;
		//this.dose = (String)dose;
		this.days = (String)days;
		this.fromDate = (String)fromDate;
		this.toDate = (String)toDate;
		this.morning = (String)morning;
		this.noon = (String)noon;
		this.evening = (String)evening;
		this.doctor = (String)doctor;
		this.remark = (String)remark;
		
		
	}
	
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
//	public String getDose() {
//		return dose;
//	}
//	public void setDose(String dose) {
//		this.dose = dose;
//	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getMorning() {
		return morning;
	}
	public void setMorning(String morning) {
		this.morning = morning;
	}
	public String getNoon() {
		return noon;
	}
	public void setNoon(String noon) {
		this.noon = noon;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
