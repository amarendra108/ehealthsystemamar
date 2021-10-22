package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppointmentListModel {

	private String userid;
	private String doctorName;
	private String speciality;
	private String appdate;
	private String apptime;
	private String notes;
	private String status;
	
	private String appmonth;
	private String appyear;
	private String patname;
	
	private String appno;

	public AppointmentListModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppointmentListModel(Object userid, Object patname, Object appdate, Object appmonth, Object appyear,
			Object notes, Object status, Object doctorName) {
		super();
		this.userid = (String) userid;
		this.patname = (String) patname;
		this.appdate = (String) appdate;
		this.appmonth = (String) appmonth;
		this.appyear = (String) appyear;
		this.notes = (String) notes;
		this.status = (String) status;
		this.doctorName = (String) doctorName;
	}

	public AppointmentListModel(Object userid, Object doctorName, Object speciality, Object appdate, Object apptime,
			Object notes, Object status, Object appno, Object patname) {
		super();
		this.userid = (String) userid;
		this.doctorName = (String) doctorName;
		this.speciality = (String) speciality;
		this.appdate = (String) appdate;
		this.apptime = (String) apptime;
		this.notes = (String) notes;
		this.status = (String) status;
		this.appno = (String) appno;
		this.patname = (String) patname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public String getApptime() {
		return apptime;
	}

	public void setApptime(String apptime) {
		this.apptime = apptime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppmonth() {
		return appmonth;
	}

	public void setAppmonth(String appmonth) {
		this.appmonth = appmonth;
	}

	public String getAppyear() {
		return appyear;
	}

	public void setAppyear(String appyear) {
		this.appyear = appyear;
	}

	public String getPatname() {
		return patname;
	}

	public void setPatname(String patname) {
		this.patname = patname;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
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
