package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppointmentModel {

	private Integer id;
	private String regNo;
	private String patientName;
	private Integer age;
	private String gender;
	private String mob;
	private String appntmntDate;
	private String appntmntTime;
	private Integer appointStatus;
	private String appntmntStatus;
	private Integer slNo;

	public AppointmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppointmentModel(Object id, Object regNo, Object patientName, Object age, Object gender, Object mob,
			Object appntmntDate, Object appntmntTime, Object appointStatus, Object appntmntStatus, Object slNo) {
		super();
		this.id = (Integer) id;
		this.regNo = (String) regNo;
		this.patientName = (String) patientName;
		this.age = (Integer) age;
		this.gender = (String) gender;
		this.mob = (String) mob;
		this.appntmntDate = (String) appntmntDate;
		this.appntmntTime = (String) appntmntTime;
		this.appointStatus = (Integer) appointStatus;
		this.appntmntStatus = (String) appntmntStatus;
		this.slNo = (Integer) slNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getAppntmntDate() {
		return appntmntDate;
	}

	public void setAppntmntDate(String appntmntDate) {
		this.appntmntDate = appntmntDate;
	}

	public String getAppntmntTime() {
		return appntmntTime;
	}

	public void setAppntmntTime(String appntmntTime) {
		this.appntmntTime = appntmntTime;
	}

	public Integer getAppointStatus() {
		return appointStatus;
	}

	public void setAppointStatus(Integer appointStatus) {
		this.appointStatus = appointStatus;
	}

	public String getAppntmntStatus() {
		return appntmntStatus;
	}

	public void setAppntmntStatus(String appntmntStatus) {
		this.appntmntStatus = appntmntStatus;
	}

	public Integer getSlNo() {
		return slNo;
	}

	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
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
