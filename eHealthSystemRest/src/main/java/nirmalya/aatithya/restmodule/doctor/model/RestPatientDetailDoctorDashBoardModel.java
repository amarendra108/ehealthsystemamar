package nirmalya.aatithya.restmodule.doctor.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientDetailDoctorDashBoardModel {
	private String id;
	private String name;
	private String datetime;
	private String location;
	private String notes;
	private String doctornote;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RestPatientDetailDoctorDashBoardModel(Object id, Object name, Object datetime, Object location, Object notes) {
		// TODO Auto-generated constructor stub
		this.id = (String)id;
		this.name = (String)name;
		this.datetime = (String)datetime;
		this.location = (String)location;
		this.notes = (String)notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDoctornote() {
		return doctornote;
	}

	public void setDoctornote(String doctornote) {
		this.doctornote = doctornote;
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
