package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientMedicationModel {

	private BigInteger userId;
	private Integer medId;
	private String medicineName;
	private String medType;
	private String dosage;
	private String morning;
	private String afterNoon;
	private String Evening;
	private String doctor;
	private String fromDate;
	private String toDate;
	private String remainingDosage;
	private String doctorId;
	private String remarks;
	private String casepaperNo;
	private Integer srlno;//bydeepak
	private Integer srno;//bydeepak
	private String apptNo;
	private String testName;
	private String testGroup;
	private String concatTest;
	private String testRemarks;
	private String testDate;

	private List<DoctorPrescriptionDetailsModel> prescriptionList;
	
	
	public String getApptNo() {
		return apptNo;
	}

	public void setApptNo(String apptNo) {
		this.apptNo = apptNo;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestGroup() {
		return testGroup;
	}

	public void setTestGroup(String testGroup) {
		this.testGroup = testGroup;
	}

	public String getConcatTest() {
		return concatTest;
	}

	public void setConcatTest(String concatTest) {
		this.concatTest = concatTest;
	}

	public String getTestRemarks() {
		return testRemarks;
	}

	public void setTestRemarks(String testRemarks) {
		this.testRemarks = testRemarks;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public Integer getSrlno() {
		return srlno;
	}

	public void setSrlno(Integer srlno) {
		this.srlno = srlno;
	}

	public Integer getSrno() {
		return srno;
	}

	public void setSrno(Integer srno) {
		this.srno = srno;
	}

	public String getCasepaperNo() {
		return casepaperNo;
	}

	public void setCasepaperNo(String casepaperNo) {
		this.casepaperNo = casepaperNo;
	}

	public Integer getMedId() {
		return medId;
	}

	public void setMedId(Integer medId) {
		this.medId = medId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
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
		return Evening;
	}

	public void setEvening(String evening) {
		Evening = evening;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
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

	public String getRemainingDosage() {
		return remainingDosage;
	}

	public void setRemainingDosage(String remainingDosage) {
		this.remainingDosage = remainingDosage;
	}

	public List<DoctorPrescriptionDetailsModel> getPrescriptionList() {
		return prescriptionList;
	}

	public void setPrescriptionList(List<DoctorPrescriptionDetailsModel> prescriptionList) {
		this.prescriptionList = prescriptionList;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
