package nirmalya.aatithya.restmodule.common.utils;


import nirmalya.aatithya.restmodule.demo.model.RestStudentNewModel;

public class GenerateStudentNewParameter {

	public static String getAddempParam(RestStudentNewModel employee) {

		String s = "";

		if (employee.getStudent() != null && employee.getStudent() != "") {
			s = s + "@p_studId='" + employee.getStudent() + "',";
		}
		if (employee.getName() != null && employee.getName() != "") {
			s = s + "@p_name='" + employee.getName() + "',";
		}
		if (employee.getFatherName() != null && employee.getFatherName() != "") {
			s = s + "@p_fatherName='" + employee.getFatherName() + "',";
		}
		if (employee.getMotherName()!= null && employee.getMotherName() != "") {
			s = s + "@p_motherName='" + employee.getMotherName() + "',";
		}
		if (employee.getRegisNo()!= null && employee.getRegisNo() != "") {
			s = s + "@p_regisNo='" + employee.getRegisNo() + "',";
		}
		if (employee.getDob() != null && employee.getDob() != "") {
			s = s + "@p_dob='" + employee.getDob() + "',";
		}
		if (employee.getAddress() != null && employee.getAddress() != "") {
			s = s + "@p_address='" + employee.getAddress() + "',";
		}
		if (employee.getCountry() != null && employee.getCountry() != "") {
			s = s + "@p_country='" + employee.getCountry() + "',";
		}

		if (employee.getState() != null && employee.getState() != "") {
			s = s + "@p_state='" + employee.getState() + "',";
		}

		if (employee.getDist()!= null && employee.getDist() != "") {
			s = s + "@p_dist='" + employee.getDist() + "',";
		}

		if (employee.getEmail() != null && employee.getEmail() != "") {
			s = s + "@p_email='" + employee.getEmail() + "',";
		}

		if (employee.getMobile()!= null && employee.getMobile() != "") {
			s = s + "@p_mobile='" + employee.getMobile() + "',";
		}

		if (employee.getBloodGroup() != null && employee.getBloodGroup() != "") {
			s = s + "@p_bloodGroup='" + employee.getBloodGroup() + "',";
		}
		if (employee.getDateofadmisn()!= null && employee.getDateofadmisn() != "") {
			s = s + "@p_dsa='" + employee.getDateofadmisn() + "',";
		}
		if (employee.getDateofpasst()!= null && employee.getDateofpasst() != "") {
			s = s + "@p_dpo='" + employee.getDateofpasst() + "',";
		}
		if (employee.getEmployeeStatus() != null && employee.getEmployeeStatus() != "") {
			s = s + "@p_employeeStatus='" + employee.getEmployeeStatus() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

}
