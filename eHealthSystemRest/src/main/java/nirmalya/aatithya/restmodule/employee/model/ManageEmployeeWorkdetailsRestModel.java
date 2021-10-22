package nirmalya.aatithya.restmodule.employee.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ManageEmployeeWorkdetailsRestModel {

	private String employeeworkId;
	private String employeeId;
	private String startDate;
	private String endDate;
	private String jobTitle;
	private String jobType;
	private String jobTypeid;
	
	private String department;
	private String departmentid;
	private String timesheet;
	private String timesheetid;
	
	private String employmentStatus;
	private String employmentStatusid;
	private String degination;
	private String band;
	private String bandid;
	private String manager;
	private String managerName;
	private String annualCTC;
	
	
	private String createdBy;
public ManageEmployeeWorkdetailsRestModel() {
		
		super();
	}
	public ManageEmployeeWorkdetailsRestModel(Object employeeworkId, Object employeeId, Object startDate, Object endDate,
			Object jobTitle,Object jobType ,Object jobTypeid, Object department,Object departmentid,Object timesheet,Object timesheetid,  Object employmentStatus   ,Object employmentStatusid   , Object degination,  Object band,  Object bandid, Object manager
			,Object managerName, Object annualCTC,Object createdBy
			
				) {
		super();
		this.employeeworkId = (String) employeeworkId;
		this.employeeId = (String) employeeId;
		this.startDate = (String) startDate;
		this.endDate = (String) endDate;
		this.jobType = (String) jobType;
		this.jobTypeid = (String) jobTypeid;
		this.jobTitle = (String) jobTitle;
		this.department = (String) department;
		this.departmentid = (String) departmentid;
		this.timesheet = (String) timesheet;
		this.timesheetid = (String) timesheetid;
		this.employmentStatus = (String) employmentStatus;
		this.employmentStatusid = (String) employmentStatusid;
		this.degination = (String) degination;
		this.band = (String) band;
		this.bandid = (String) bandid;
		this.manager = (String) manager;
		this.annualCTC = (String) annualCTC;	
		this.createdBy = (String) createdBy;
		this.managerName = (String) managerName;
		

}
	public String getEmployeeworkId() {
		return employeeworkId;
	}
	public void setEmployeeworkId(String employeeworkId) {
		this.employeeworkId = employeeworkId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public String getDegination() {
		return degination;
	}
	public void setDegination(String degination) {
		this.degination = degination;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getAnnualCTC() {
		return annualCTC;
	}
	public void setAnnualCTC(String annualCTC) {
		this.annualCTC = annualCTC;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	
	
	
	public String getJobTypeid() {
		return jobTypeid;
	}
	public void setJobTypeid(String jobTypeid) {
		this.jobTypeid = jobTypeid;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getTimesheetid() {
		return timesheetid;
	}
	public void setTimesheetid(String timesheetid) {
		this.timesheetid = timesheetid;
	}
	public String getEmploymentStatusid() {
		return employmentStatusid;
	}
	public void setEmploymentStatusid(String employmentStatusid) {
		this.employmentStatusid = employmentStatusid;
	}
	public String getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(String timesheet) {
		this.timesheet = timesheet;
	}
	
	public String getBandid() {
		return bandid;
	}
	public void setBandid(String bandid) {
		this.bandid = bandid;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
