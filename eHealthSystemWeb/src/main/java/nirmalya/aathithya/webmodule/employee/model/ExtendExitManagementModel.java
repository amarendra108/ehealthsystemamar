package nirmalya.aathithya.webmodule.employee.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtendExitManagementModel {

	private String employeeExit;
	private String empID;
	private String empName;
	private String designationId;
	private String designation;
	private String noticePeriod;
	private Double salary;
	private String resignDate;
	private String releaseDate;
	private Double bonus;
	private Double recovery;
	private String reason;
	private String empStatus;
	private String deptId;
	private String deptName;
	private String managerId;
	private String managerName;
	private Integer clearanceId;
	private String clearanceDeptId;
	private String clearanceDeptName;
	private String clearanceBy;
	private String clearancePName;
	private String clearanceCmnt;
	private String clearanceStatus;
	private String financeStatus;
	private String initId;
	private String createdBy;
	private Integer slNo;
	private String empClrncStatus;
	private String financeId;
	private String employeeIdF;
	private String empNameF;
	private String empDepartmentF;
	private String empDepartmentNameF;
	private String managerF;
	private String managerNameF;
	private Double salaryF;
	private String noticePeriodF;
	private Double bonusF;
	private Double other;
	private String comment;
	private Double recoveryF;
	
	public ExtendExitManagementModel() {
		super();
// TODO Auto-generated constructor stub
	}

	public String getEmployeeExit() {
		return employeeExit;
	}

	public void setEmployeeExit(String employeeExit) {
		this.employeeExit = employeeExit;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getNoticePeriod() {
		return noticePeriod;
	}

	public Integer getSlNo() {
		return slNo;
	}

	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}

	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getRecovery() {
		return recovery;
	}

	public void setRecovery(Double recovery) {
		this.recovery = recovery;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Integer getClearanceId() {
		return clearanceId;
	}

	public void setClearanceId(Integer clearanceId) {
		this.clearanceId = clearanceId;
	}

	public String getClearanceDeptId() {
		return clearanceDeptId;
	}

	public void setClearanceDeptId(String clearanceDeptId) {
		this.clearanceDeptId = clearanceDeptId;
	}

	public String getClearanceDeptName() {
		return clearanceDeptName;
	}

	public void setClearanceDeptName(String clearanceDeptName) {
		this.clearanceDeptName = clearanceDeptName;
	}

	public String getClearanceBy() {
		return clearanceBy;
	}

	public void setClearanceBy(String clearanceBy) {
		this.clearanceBy = clearanceBy;
	}

	public String getClearancePName() {
		return clearancePName;
	}

	public void setClearancePName(String clearancePName) {
		this.clearancePName = clearancePName;
	}

	public String getClearanceCmnt() {
		return clearanceCmnt;
	}

	public void setClearanceCmnt(String clearanceCmnt) {
		this.clearanceCmnt = clearanceCmnt;
	}

	public String getClearanceStatus() {
		return clearanceStatus;
	}

	public void setClearanceStatus(String clearanceStatus) {
		this.clearanceStatus = clearanceStatus;
	}

	public String getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(String financeStatus) {
		this.financeStatus = financeStatus;
	}

	public String getInitId() {
		return initId;
	}

	public void setInitId(String initId) {
		this.initId = initId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getEmpClrncStatus() {
		return empClrncStatus;
	}

	public void setEmpClrncStatus(String empClrncStatus) {
		this.empClrncStatus = empClrncStatus;
	}
	
	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getEmployeeIdF() {
		return employeeIdF;
	}

	public void setEmployeeIdF(String employeeIdF) {
		this.employeeIdF = employeeIdF;
	}

	public String getEmpNameF() {
		return empNameF;
	}

	public void setEmpNameF(String empNameF) {
		this.empNameF = empNameF;
	}

	public String getEmpDepartmentF() {
		return empDepartmentF;
	}

	public void setEmpDepartmentF(String empDepartmentF) {
		this.empDepartmentF = empDepartmentF;
	}

	public String getEmpDepartmentNameF() {
		return empDepartmentNameF;
	}

	public void setEmpDepartmentNameF(String empDepartmentNameF) {
		this.empDepartmentNameF = empDepartmentNameF;
	}

	public String getManagerF() {
		return managerF;
	}

	public void setManagerF(String managerF) {
		this.managerF = managerF;
	}

	public String getManagerNameF() {
		return managerNameF;
	}

	public void setManagerNameF(String managerNameF) {
		this.managerNameF = managerNameF;
	}

	public Double getSalaryF() {
		return salaryF;
	}

	public void setSalaryF(Double salaryF) {
		this.salaryF = salaryF;
	}

	public String getNoticePeriodF() {
		return noticePeriodF;
	}

	public void setNoticePeriodF(String noticePeriodF) {
		this.noticePeriodF = noticePeriodF;
	}

	public Double getBonusF() {
		return bonusF;
	}

	public void setBonusF(Double bonusF) {
		this.bonusF = bonusF;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getRecoveryF() {
		return recoveryF;
	}

	public void setRecoveryF(Double recoveryF) {
		this.recoveryF = recoveryF;
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