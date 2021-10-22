
package nirmalya.aatithya.restmodule.master.model;

public class RestAttendanceModel {
	private String isOut;
	private String punchinDate;

	private String punchinTime;
	private Integer punchinLocation;
	private String punchinNote;
	private String punchoutTime;
	private String punchOutLocation;
	private String punchOutNote;



	public RestAttendanceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestAttendanceModel(Object isOut, Object punchinDate, Object punchinTime, Object punchinLocation,
			Object punchinNote, Object punchoutTime, Object punchOutLocation, Object punchOutNote) {
		super();
		this.isOut = (String) isOut;
		this.punchinDate = (String) punchinDate;
		this.punchinTime = (String) punchinTime;
		this.punchinLocation = (Integer) punchinLocation;
		this.punchinNote = (String) punchinNote;

		this.punchoutTime = (String) punchoutTime;
		this.punchOutLocation = (String) punchOutLocation;
		this.punchOutNote = (String) punchOutNote;

	}

	public String getIsOut() {
		return isOut;
	}

	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}

	public String getPunchinDate() {
		return punchinDate;
	}

	public void setPunchinDate(String punchinDate) {
		this.punchinDate = punchinDate;
	}

	public String getPunchinTime() {
		return punchinTime;
	}

	public void setPunchinTime(String punchinTime) {
		this.punchinTime = punchinTime;
	}

	public Integer getPunchinLocation() {
		return punchinLocation;
	}

	public void setPunchinLocation(Integer punchinLocation) {
		this.punchinLocation = punchinLocation;
	}

	public String getPunchinNote() {
		return punchinNote;
	}

	public void setPunchinNote(String punchinNote) {
		this.punchinNote = punchinNote;
	}

	public String getPunchoutTime() {
		return punchoutTime;
	}

	public void setPunchoutTime(String punchoutTime) {
		this.punchoutTime = punchoutTime;
	}

	public String getPunchOutLocation() {
		return punchOutLocation;
	}

	public void setPunchOutLocation(String punchOutLocation) {
		this.punchOutLocation = punchOutLocation;
	}

	public String getPunchOutNote() {
		return punchOutNote;
	}

	public void setPunchOutNote(String punchOutNote) {
		this.punchOutNote = punchOutNote;
	}

}
