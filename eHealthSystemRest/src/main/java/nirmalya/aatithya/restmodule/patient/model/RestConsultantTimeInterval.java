package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class RestConsultantTimeInterval {
	private String date;
	private String time;
	private List<DropDownModel> timeinterval;
	
	
	public RestConsultantTimeInterval(Object date, Object time) {
		super();
		this.date = (String)date;
		this.time = (String)time;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public List<DropDownModel> getTimeinterval() {
		return timeinterval;
	}


	public void setTimeinterval(List<DropDownModel> timeinterval) {
		this.timeinterval = timeinterval;
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
