package nirmalya.aatithya.restmodule.chemist.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestChemistPrescriptionDetailsModel {
	private String patientName;
	private String doctorName;
	private String medicineName;
	private String dosageName;
	private String morning;
	private String afternoon;
	private String evening;
	private String quantity;
	private String address;
	private String note;
	private String medicineStatus;
	private String chemistId;
	private String reqid;
	private String medId;
	private String medType;
	private String orderid;
	public RestChemistPrescriptionDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RestChemistPrescriptionDetailsModel(Object reqid, Object patientName, Object doctorName, Object medId, 
			Object medicineName,Object medType,Object quantity,Object medicineStatus, Object note, Object orderid) {
		// TODO Auto-generated constructor stub
		this.reqid = (String)reqid;
		this.patientName = (String)patientName;
		this.doctorName = (String)doctorName;
		this.medId = (String)medId;
		this.medType = (String)medType;
		this.medicineName = (String)medicineName;
		this.quantity = (String)quantity;
		this.medicineStatus = (String)medicineStatus;
		this.note = (String)note;
		this.orderid = (String)orderid;
	}
	
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getDosageName() {
		return dosageName;
	}

	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}

	public String getMorning() {
		return morning;
	}

	public void setMorning(String morning) {
		this.morning = morning;
	}

	public String getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(String afternoon) {
		this.afternoon = afternoon;
	}

	public String getEvening() {
		return evening;
	}

	public void setEvening(String evening) {
		this.evening = evening;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMedicineStatus() {
		return medicineStatus;
	}

	public void setMedicineStatus(String medicineStatus) {
		this.medicineStatus = medicineStatus;
	}

	public String getChemistId() {
		return chemistId;
	}

	public void setChemistId(String chemistId) {
		this.chemistId = chemistId;
	}

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public String getMedId() {
		return medId;
	}

	public void setMedId(String medId) {
		this.medId = medId;
	}

	public String getMedType() {
		return medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
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