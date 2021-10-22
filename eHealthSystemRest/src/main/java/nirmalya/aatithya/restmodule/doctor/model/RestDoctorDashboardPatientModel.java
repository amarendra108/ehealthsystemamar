package nirmalya.aatithya.restmodule.doctor.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDoctorDashboardPatientModel {
	private String id;
	private String time;
	private String name;
	private String address;
	private String type;
	private String status;
	private String uhId;

	public RestDoctorDashboardPatientModel(Object id, Object time, Object name, Object address, Object status,
			Object uhId) {
		// TODO Auto-generated constructor stub
		this.id = (String) id;
		this.time = (String) time;
		this.name = (String) name;
		this.address = (String) address;
		this.status = (String) status;
		this.uhId = (String) uhId;
	}

	public String getUhId() {
		return uhId;
	}

	public void setUhId(String uhId) {
		this.uhId = uhId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
