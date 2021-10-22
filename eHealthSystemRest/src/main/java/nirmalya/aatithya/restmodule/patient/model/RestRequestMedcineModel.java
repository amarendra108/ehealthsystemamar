package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestRequestMedcineModel {
	private String srlno;
	private String srno;
	private String chemistid;
	private String patientId;
	private String medicineids;
	private String patientNote;
	
	
	public String getSrlno() {
		return srlno;
	}
	public void setSrlno(String srlno) {
		this.srlno = srlno;
	}
	public String getSrno() {
		return srno;
	}
	public void setSrno(String srno) {
		this.srno = srno;
	}
	public String getChemistid() {
		return chemistid;
	}
	public void setChemistid(String chemistid) {
		this.chemistid = chemistid;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMedicineids() {
		return medicineids;
	}
	public void setMedicineids(String medicineids) {
		this.medicineids = medicineids;
	}
	public String getPatientNote() {
		return patientNote;
	}
	public void setPatientNote(String patientNote) {
		this.patientNote = patientNote;
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
