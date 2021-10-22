package nirmalya.aatithya.restmodule.demo.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestEmployeeNewModel {
	private String employee;
	private String fName;
	private String lName;
	private String age;
	private String gender;
	private String genderId;
	private String maritialStatus;
	private String maritialStatusId;
	private String phNo;
	private String employeeStatus;

	public RestEmployeeNewModel() {
		super();
	}

	public RestEmployeeNewModel(Object employee, Object fName, Object lName, Object age, Object gender, Object genderId,
			
			Object maritialStatus,Object maritialStatusId, Object phNo, Object employeeStatus) {
		super();
		this.employee = (String) employee;
		this.fName = (String) fName;
		this.lName = (String) lName;
		this.age = (String) age;
		this.gender= (String) gender;
		this.genderId = (String) genderId;
		this.maritialStatus = (String) maritialStatus;
		this.maritialStatusId = (String) maritialStatusId;
		this.phNo = (String) phNo;
		this.employeeStatus = (String) employeeStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	
	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getMaritialStatusId() {
		return maritialStatusId;
	}

	public void setMaritialStatusId(String maritialStatusId) {
		this.maritialStatusId = maritialStatusId;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
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
