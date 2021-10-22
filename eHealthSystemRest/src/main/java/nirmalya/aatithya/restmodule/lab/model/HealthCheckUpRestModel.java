package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HealthCheckUpRestModel {
	private String uHIdNo;
	private BigInteger uHIdName;
	private String patientId; 
	private String state;
	private String eGender;
	private String mobileNo;
	private String appointmentDate;
	private String appointmentTime;
	private String name;
	private Integer age;
	private Integer status;
	public HealthCheckUpRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HealthCheckUpRestModel(Object uHIdNo, Object uHIdName, Object patientId, Object state,
			Object eGender, Object mobileNo, Object appointmentDate, Object appointmentTime,Object name,Object age,Object status) {
		super();
		this.uHIdNo = (String)uHIdNo;
		this.uHIdName = (BigInteger)uHIdName;
		this.patientId = (String)patientId;
		this.state = (String)state;
		this.eGender = (String)eGender;
		this.mobileNo = (String)mobileNo;
		this.appointmentDate = (String)appointmentDate;
		this.appointmentTime = (String)appointmentTime;
		this.name = (String)name;
		this.age = (Integer)age;
		this.status = (Integer)status;
	}
	
	public String getuHIdNo() {
		return uHIdNo;
	}
	public void setuHIdNo(String uHIdNo) {
		this.uHIdNo = uHIdNo;
	}
	public BigInteger getuHIdName() {
		return uHIdName;
	}
	public void setuHIdName(BigInteger uHIdName) {
		this.uHIdName = uHIdName;
	}
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String geteGender() {
		return eGender;
	}
	public void seteGender(String eGender) {
		this.eGender = eGender;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
