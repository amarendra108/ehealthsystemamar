package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.demo.model.RestEmployeeNewModel;

public class GenerateEmployeeNewParameter {

	public static String getAddempParam(RestEmployeeNewModel employee) {

		String s = "";

		if (employee.getEmployee() != null && employee.getEmployee() != "") {
			s = s + "@p_empId='" + employee.getEmployee() + "',";
		}
		if (employee.getfName() != null && employee.getfName() != "") {
			s = s + "@p_fName='" + employee.getfName() + "',";
		}
		if (employee.getlName() != null && employee.getlName() != "") {
			s = s + "@p_lName='" + employee.getlName() + "',";
		}
		if (employee.getAge() != null && employee.getAge() != "") {
			s = s + "@p_age='" + employee.getAge() + "',";
		}
		if (employee.getGender() != null && employee.getGender() != "") {
			s = s + "@p_gender='" + employee.getGender() + "',";
		}
		if (employee.getMaritialStatus() != null && employee.getMaritialStatus() != "") {
			s = s + "@p_maritialStatus='" + employee.getMaritialStatus() + "',";
		}
		if (employee.getPhNo() != null && employee.getPhNo() != "") {
			s = s + "@p_phNo='" + employee.getPhNo() + "',";
		}

		if (employee.getEmployeeStatus() != null) {
			s = s + "@p_empStatus='" + employee.getEmployeeStatus() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

}
