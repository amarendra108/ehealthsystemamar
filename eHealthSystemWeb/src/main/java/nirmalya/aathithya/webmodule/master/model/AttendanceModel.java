package nirmalya.aathithya.webmodule.master.model;

public class AttendanceModel {
	private String isOut;
	private String punchinDate;
	
	private String punchinTime;
	private Integer punchinLocation;
	private String punchinNote;
	private String punchoutTime;
	private String punchOutLocation;
	private String punchOutNote;
	
	public AttendanceModel() {
		super();
		// TODO Auto-generated constructor stub
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
