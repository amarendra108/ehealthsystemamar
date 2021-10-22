package nirmalya.aatithya.restmodule.pm.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestHieDashboardModel {
	private String totalRegs;
	private String total_hospital;
	private String total_clinic;
	private String total_doctors;
	private String diagnosticLab;
	private String pathologyLab;
	private String total_pharmacy;
	private String total_ambulance;
	
	
	//dash board graphical model
		private String count;
		private String location;
		private String orgtype;
		
		//dash board graphical model
				private String year;
				private String privates;
				private String government;
				private String ngo;
	public RestHieDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestHieDashboardModel(Object totalRegs, Object total_hospital, Object total_clinic, Object total_doctors,
			Object diagnosticLab, Object pathologyLab, Object total_pharmacy, Object total_ambulance) {
		super();
		this.totalRegs = (String)totalRegs;
		this.total_hospital = (String)total_hospital;
		this.total_clinic = (String)total_clinic;
		this.total_doctors = (String)total_doctors;
		this.diagnosticLab = (String)diagnosticLab;
		this.pathologyLab = (String)pathologyLab;
		this.total_pharmacy = (String)total_pharmacy;
		this.total_ambulance = (String)total_ambulance;
	}
	
	public RestHieDashboardModel(Object count, Object location, Object orgtype) {
		this.count = (String)count;
		this.location = (String)location;
		this.orgtype = (String)orgtype;
	}
	
	public RestHieDashboardModel(Object year, Object privates, Object government, Object ngo) {
		this.year = (String)year;
		this.privates = (String)privates;
		this.government = (String)government;
		this.ngo = (String)ngo;
	}
	public String getTotalRegs() {
		return totalRegs;
	}
	public void setTotalRegs(String totalRegs) {
		this.totalRegs = totalRegs;
	}
	public String getTotal_hospital() {
		return total_hospital;
	}
	public void setTotal_hospital(String total_hospital) {
		this.total_hospital = total_hospital;
	}
	public String getTotal_clinic() {
		return total_clinic;
	}
	public void setTotal_clinic(String total_clinic) {
		this.total_clinic = total_clinic;
	}
	public String getTotal_doctors() {
		return total_doctors;
	}
	public void setTotal_doctors(String total_doctors) {
		this.total_doctors = total_doctors;
	}
	public String getDiagnosticLab() {
		return diagnosticLab;
	}
	public void setDiagnosticLab(String diagnosticLab) {
		this.diagnosticLab = diagnosticLab;
	}
	public String getPathologyLab() {
		return pathologyLab;
	}
	public void setPathologyLab(String pathologyLab) {
		this.pathologyLab = pathologyLab;
	}
	public String getTotal_pharmacy() {
		return total_pharmacy;
	}
	public void setTotal_pharmacy(String total_pharmacy) {
		this.total_pharmacy = total_pharmacy;
	}
	public String getTotal_ambulance() {
		return total_ambulance;
	}
	public void setTotal_ambulance(String total_ambulance) {
		this.total_ambulance = total_ambulance;
	}
	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPrivates() {
		return privates;
	}
	public void setPrivates(String privates) {
		this.privates = privates;
	}
	public String getGovernment() {
		return government;
	}
	public void setGovernment(String government) {
		this.government = government;
	}
	public String getNgo() {
		return ngo;
	}
	public void setNgo(String ngo) {
		this.ngo = ngo;
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
