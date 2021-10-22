package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.employee.model.ExtendExitManagementRestModel;

public class GenerateExtendExitManagementParameter {
	public static String getAddExitManagementParam(ExtendExitManagementRestModel exitManagement) {
		String s = "";

		if (exitManagement.getEmployeeExit() != null && exitManagement.getEmployeeExit() != "") {
			s = s + "@p_exitMangementId='" + exitManagement.getEmployeeExit() + "',";
		}
		if (exitManagement.getEmpID() != null && exitManagement.getEmpID() != "") {
			s = s + "@p_empName='" + exitManagement.getEmpID() + "',";
		}

		if (exitManagement.getDesignation() != null && exitManagement.getDesignation() != "") {
			s = s + "@p_empdesignation='" + exitManagement.getDesignation() + "',";
		}

		if (exitManagement.getResignDate() != null && exitManagement.getResignDate() != "") {
			s = s + "@p_resignationDate='" +exitManagement.getResignDate() + "',";
		}
		if (exitManagement.getSalary() != null) {
			s = s + "@p_salary=" + exitManagement.getSalary() + ",";
		}
		if (exitManagement.getBonus() != null) {
			s = s + "@p_bonus=" + exitManagement.getBonus() + ",";
		}
		if (exitManagement.getRecovery() != null) {
			s = s + "@p_recovery=" + exitManagement.getRecovery() + ",";
		}

		if (exitManagement.getNoticePeriod() != null && exitManagement.getNoticePeriod() != "") {
			s = s + "@p_noticeperiod='" + exitManagement.getNoticePeriod() + "',";
		}

		if (exitManagement.getReleaseDate() != null && exitManagement.getReleaseDate() != "") {
			s = s + "@p_releaseDate='" +exitManagement.getReleaseDate() + "',";
		}
		if (exitManagement.getReason() != null && exitManagement.getReason() != "") {
			s = s + "@p_reason='" + exitManagement.getReason() + "',";
		}
		if (exitManagement.getEmpStatus() != null && exitManagement.getEmpStatus() != " ") {
			s = s + "@p_status='" + exitManagement.getEmpStatus() + "',";
		}
		if (exitManagement.getCreatedBy() != null && exitManagement.getCreatedBy() != " ") {
			s = s + "@p_createdBy='" + exitManagement.getCreatedBy() + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;
		 
	}

	public static String getAddClearanceParam(List<ExtendExitManagementRestModel> exitManagement) {
		String s = "";
		String listdata ="";
		String employeeInit ="";
		String employeeExit="";
		String empID ="";
		String deptId ="";
		String managerId ="";
		String createdBy = "";
		
		
		for (ExtendExitManagementRestModel m : exitManagement) {
			
			employeeInit = m.getInitId();
			employeeExit =m.getEmployeeExit();
			empID = m.getEmpID();
			deptId = m.getDeptId();
			managerId = m.getManagerId();
			createdBy = m.getCreatedBy();
			
		}
		s = s + "@p_initiateId='" + employeeInit + "',";
		s = s + "@p_exitMangementId='" + employeeExit + "',";
		s = s + "@p_empId='" + empID + "',";
		s = s + "@p_manager='" + managerId + "',";
		s = s + "@p_empDepartmentName='" + deptId + "',";
		s = s + "@p_createdBy='" + createdBy + "',";
		
	if(!exitManagement.get(0).getEmployeeExit().contentEquals("1")) {
		for (ExtendExitManagementRestModel m : exitManagement) {
			
			listdata = listdata + "(@p_initiateId,\"" + m.getEmpID() + "\",\"" + m.getClearanceDeptId() + "\",\""
					+ m.getClearanceBy() + "\",\"" + m.getClearanceStatus() + "\",\"" + m.getClearanceCmnt() + "\",\"" + m.getCreatedBy()
					+ "\"),";
		}
		listdata = listdata.substring(0, listdata.length() - 1);

		s = s + "@p_litemSubQuery='" + listdata + "',";
		
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
	}
		return s;
	}
	
	
	
	public static String getAddFinanceDetailsParam(ExtendExitManagementRestModel exit) {
			String s = "";
				
				if(exit.getFinanceId()!=null && exit.getFinanceId()!="")
				{
					s = s + "@p_financeId='" + exit.getFinanceId() + "',";
				}
				if(exit.getEmployeeExit()!=null && exit.getEmployeeExit()!="")
				{
					s = s + "@p_employeeExit='" + exit.getEmployeeExit() + "',";
				}
				if(exit.getEmployeeIdF()!=null && exit.getEmployeeIdF()!="")
				{
					s = s + "@p_employeeId='" + exit.getEmployeeIdF() + "',";
				}
				 
				if(exit.getEmpNameF()!=null && exit.getEmpNameF()!="")
				{
					s = s + "@p_empName='" + exit.getEmpNameF() + "',";
				}
				if(exit.getEmpDepartmentF()!=null && exit.getEmpDepartmentF()!="")
				{
					s = s + "@p_empDep='" + exit.getEmpDepartmentF() + "',";
				}
				if(exit.getManagerF()!=null && exit.getManagerF()!="")
				{
					s = s + "@p_empManager='" + exit.getManagerF() + "',";
				}
				
				if(exit.getSalaryF()!=null)
				{
					s = s + "@p_salary=" + exit.getSalaryF() + ",";
				}
				if(exit.getBonusF()!=null)
				{
					s = s + "@p_bonusf=" + exit.getBonusF() + ",";
				}
				if(exit.getRecoveryF()!=null) {
					s = s + "@p_recovery=" + exit.getRecoveryF() + ",";
				}
				if(exit.getOther()!=null) {
					s = s + "@p_other=" + exit.getOther() + ",";
				}
				if(exit.getNoticePeriodF()!=null && exit.getNoticePeriodF()!="")
				{
					s = s + "@p_noticeperiod='" + exit.getNoticePeriodF() + "',";
				}
				
				if(exit.getComment()!=null && exit.getComment()!="")
				{
					s = s + "@p_comment='" + exit.getComment() + "',";
				}
				
				if(exit.getCreatedBy()!=null && exit.getCreatedBy()!=" ")
				{
					s = s + "@p_createdBy='" + exit.getCreatedBy() + "',";
				}
				if(s != "") {
					s = s.substring(0, s.length()-1);
					
					s = "SET " + s + ";" ;
				}
				System.out.println("Finance"+s);
				return s;
			}
}
