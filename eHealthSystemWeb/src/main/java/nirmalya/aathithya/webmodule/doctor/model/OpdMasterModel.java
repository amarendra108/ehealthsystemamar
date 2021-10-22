package nirmalya.aathithya.webmodule.doctor.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Nirmalya Labs
 *
 */

public class OpdMasterModel {
	private String opdId;
	private String opdDate;
	private String opdDay;
	private String fromTime;
	private String toTime;
	private String remarks;
	private String drId;

	public OpdMasterModel() {
		super();

	}
	
	/**
	 * Generate Getter & Setter
	 *
	 */

	public String getDrId() {
		return drId;
	}

	public void setDrId(String drId) {
		this.drId = drId;
	}

	public String getOpdId() {
		return opdId;
	}

	public void setOpdId(String opdId) {
		this.opdId = opdId;
	}

	public String getOpdDate() {
		return opdDate;
	}

	public void setOpdDate(String opdDate) {
		this.opdDate = opdDate;
	}

	public String getOpdDay() {
		return opdDay;
	}

	public void setOpdDay(String opdDay) {
		this.opdDay = opdDay;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
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
