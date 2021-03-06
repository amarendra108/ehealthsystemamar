package nirmalya.aatithya.restmodule.master.model;

public class TimeSheetModel {
	private String timesheetId;
	private String year;
	private String month;
	private String empId;
	private String week;
	private String project;
	private String allocateHour;
	private String usedHour;
	private String availableHour;
	private String monday;
	private String tuesday;
	private String wedday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private String totalProjectDays;
	private String createdBy;
	
	public TimeSheetModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TimeSheetModel(Object timesheetId, Object year, Object month,Object empId, Object week, Object project, Object allocateHour,
            Object usedHour, Object availableHour, Object monday, Object tuesday, Object wedday, Object thursday,
            Object friday, Object saturday, Object sunday, Object totalProjectDays, Object createdBy) {
        super();
        this.timesheetId = (String) timesheetId;
        this.year = (String) year;      
        this.month = (String) month;
        this.empId = (String) empId;
        this.week = (String) week;
        this.project = (String) project;
        this.allocateHour = (String) allocateHour;
        this.usedHour = (String) usedHour;
        this.availableHour = (String) availableHour;
        this.monday = (String) monday;
        this.tuesday = (String) tuesday;
        this.wedday = (String) wedday;
        this.thursday = (String) thursday;
        this.friday = (String) friday;
        this.saturday = (String) saturday;
        this.sunday = (String) sunday;
        this.totalProjectDays = (String) totalProjectDays;
        this.createdBy = (String) createdBy;
    }
	public String getTimesheetId() {
		return timesheetId;
	}
	public void setTimesheetId(String timesheetId) {
		this.timesheetId = timesheetId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getAllocateHour() {
		return allocateHour;
	}
	public void setAllocateHour(String allocateHour) {
		this.allocateHour = allocateHour;
	}
	public String getUsedHour() {
		return usedHour;
	}
	public void setUsedHour(String usedHour) {
		this.usedHour = usedHour;
	}
	public String getAvailableHour() {
		return availableHour;
	}
	public void setAvailableHour(String availableHour) {
		this.availableHour = availableHour;
	}
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public String getWedday() {
		return wedday;
	}
	public void setWedday(String wedday) {
		this.wedday = wedday;
	}
	public String getThursday() {
		return thursday;
	}
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public String getTotalProjectDays() {
		return totalProjectDays;
	}
	public void setTotalProjectDays(String totalProjectDays) {
		this.totalProjectDays = totalProjectDays;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
}