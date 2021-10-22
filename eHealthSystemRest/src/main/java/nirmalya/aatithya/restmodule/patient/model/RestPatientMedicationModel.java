package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientMedicationModel {

	private BigInteger userId;
	private Integer medId;
	private String medicineName;
	private String medType;
	private String dosage;
	private String morning;
	private String afterNoon;
	private String evening;
	private String doctor;
	private String fromDate;
	private String toDate;
	private String priscriptionDate;
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
	private List<RestDoctorPrescriptionDetailsModel> prescriptionList;

	public RestPatientMedicationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
//for dwopwon of mdedicine type
	
	public RestPatientMedicationModel(Object medId, Object medicineName) {
		super();
		this.medId = (Integer) medId;
		this.medicineName = (String) medicineName;
	}
	/*
	 * //Current Medicine public RestPatientMedicationModel(Object medicineName,
	 * Object medType, Object dosage, Object morning, Object afterNoon, Object
	 * evening, Object doctor, Object fromDate, Object toDate) { super();
	 * this.medicineName = (String) medicineName; this.medType = (String) medType;
	 * this.dosage = (String) dosage; this.morning = (String) morning;
	 * this.afterNoon = (String) afterNoon; this.evening = (String) evening;
	 * this.doctor = (String) doctor; this.fromDate = (String) fromDate; this.toDate
	 * = (String) toDate;
	 * 
	 * }
	 */
	
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
//view all prescription(medcine) details to show patient and doctor
	public RestPatientMedicationModel(Object medicineName, Object medType, Object dosage, Object morning,
			Object afterNoon, Object evening, Object doctor, Object fromDate, Object toDate, Object medId, Object srlno, Object srno) {
		super();
		this.medicineName = (String) medicineName;
		this.medType = (String) medType;
		this.dosage = (String) dosage;
		this.morning = (String) morning;
		this.afterNoon = (String) afterNoon;
		this.evening = (String) evening;
		this.doctor = (String) doctor;
		this.fromDate = (String) fromDate;
		this.toDate = (String) toDate;
		this.medId = (Integer)medId;
		this.srlno = (Integer)srlno;
		this.srno = (Integer)srno;

	}

//Previous Doctor Details
	public RestPatientMedicationModel(Object priscriptionDate, Object doctor, Object doctorId) {
		super();
		this.priscriptionDate = (String) priscriptionDate;
		this.doctor = (String) doctor;
		this.doctorId = (String) doctorId;

	}
	
	public RestPatientMedicationModel(Object testName, Object testGroup, Object testRemarks,Object testDate,Object doctorId) {
		super();
		this.testName = (String) testName;
		this.testGroup = (String) testGroup;
		this.testRemarks = (String) testRemarks;
		this.testDate = (String) testDate;
		this.doctorId = (String) doctorId;

	}
	//test list to for the view of patient
		public RestPatientMedicationModel(Object testName, Object testGroup, Object testRemarks,Object testDate,Object doctor, Object doctorId, Object srlno,
				Object srno) {
			super();
			this.testName = (String) testName;
			this.testGroup = (String) testGroup;
			this.testRemarks = (String) testRemarks;
			this.testDate = (String) testDate;
			this.doctor = (String) doctor;
			this.doctorId = (String) doctorId;
			this.srlno = (Integer)srlno;
			this.srno = (Integer)srno;

		}
	/*
	 * AutoSearch Test Name with group(Medication)
	 * 
	 */
	public RestPatientMedicationModel(Object testName, Object testGroup, Object concatTest,Object medId) {
		super();
		this.testName = (String) testName;
		this.testGroup = (String) testGroup;
		this.concatTest = (String) concatTest;
		this.medId = (Integer) medId;

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

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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

	public String getPriscriptionDate() {
		return priscriptionDate;
	}

	public void setPriscriptionDate(String priscriptionDate) {
		this.priscriptionDate = priscriptionDate;
	}

	public List<RestDoctorPrescriptionDetailsModel> getPrescriptionList() {
		return prescriptionList;
	}

	public void setPrescriptionList(List<RestDoctorPrescriptionDetailsModel> prescriptionList) {
		this.prescriptionList = prescriptionList;
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
