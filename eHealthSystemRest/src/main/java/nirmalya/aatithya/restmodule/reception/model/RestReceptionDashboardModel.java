package nirmalya.aatithya.restmodule.reception.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestReceptionDashboardModel {
	public BigInteger todayAppointment;
	public BigInteger noAppointmentCancel;
	public String noHospital;
	public String patientAdmsn;
	public String avlblDctr;
	public String avlblSlot;
	public String testReport;

	public RestReceptionDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestReceptionDashboardModel(Object todayAppointment, Object noAppointmentCancel, Object noHospital,
			Object patientAdmsn, Object avlblDctr, Object avlblSlot, Object testReport) {
		super();
		this.todayAppointment = (BigInteger) todayAppointment;
		this.noAppointmentCancel = (BigInteger) noAppointmentCancel;
		this.noHospital = (String) noHospital;
		this.patientAdmsn = (String) patientAdmsn;
		this.avlblDctr = (String) avlblDctr;
		this.avlblSlot = (String) avlblSlot;
		this.testReport = (String) testReport;
	}

	public BigInteger getTodayAppointment() {
		return todayAppointment;
	}

	public void setTodayAppointment(BigInteger todayAppointment) {
		this.todayAppointment = todayAppointment;
	}

	public BigInteger getNoAppointmentCancel() {
		return noAppointmentCancel;
	}

	public void setNoAppointmentCancel(BigInteger noAppointmentCancel) {
		this.noAppointmentCancel = noAppointmentCancel;
	}

	public String getNoHospital() {
		return noHospital;
	}

	public void setNoHospital(String noHospital) {
		this.noHospital = noHospital;
	}

	public String getPatientAdmsn() {
		return patientAdmsn;
	}

	public void setPatientAdmsn(String patientAdmsn) {
		this.patientAdmsn = patientAdmsn;
	}

	public String getAvlblDctr() {
		return avlblDctr;
	}

	public void setAvlblDctr(String avlblDctr) {
		this.avlblDctr = avlblDctr;
	}

	public String getAvlblSlot() {
		return avlblSlot;
	}

	public void setAvlblSlot(String avlblSlot) {
		this.avlblSlot = avlblSlot;
	}

	public String getTestReport() {
		return testReport;
	}

	public void setTestReport(String testReport) {
		this.testReport = testReport;
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
