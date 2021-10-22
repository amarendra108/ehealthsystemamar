package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;


public class ConsultantTimeInterval {
	private String date;
	private String time;
	private List<DropDownModel> timeinterval;
	
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
