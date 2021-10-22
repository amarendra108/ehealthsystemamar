package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.master.model.RestAttendanceModel;

public class GenerateParameterAttendance {
	public static String getAddempParam(RestAttendanceModel employee) {

		String s = "";

		if (employee.getIsOut() != null && employee.getIsOut() != "") {
			s = s + "@p_attendanceId='" + employee.getIsOut() + "',";
		}

		if (employee.getPunchinDate() != null && employee.getPunchinDate() != "") {
			s = s + "@p_punchinDate='" + employee.getPunchinDate() + "',";
		}
		if (employee.getPunchinTime() != null && employee.getPunchinTime() != "") {
			s = s + "@p_punchinTime='" + employee.getPunchinTime() + "',";
		}

		if (employee.getPunchinLocation() != null) {
			s = s + "@p_punchinLocation='" + employee.getPunchinLocation() + "',";
		}

		if (employee.getPunchinNote() != null && employee.getPunchinNote() != "") {
			s = s + "@p_punchinNote='" + employee.getPunchinNote() + "',";
		}

		if (employee.getPunchoutTime() != null && employee.getPunchoutTime() != "") {
			s = s + "@p_atnPunchOut='" + employee.getPunchoutTime() + "',";
		}

		if (employee.getPunchOutLocation() != null) {
			s = s + "@p_atnPunchOutLoc='" + employee.getPunchOutLocation() + "',";
		}

		if (employee.getPunchOutNote() != null && employee.getPunchOutNote() != "") {
			s = s + "@p_atnPunchOutNote='" + employee.getPunchOutNote() + "',";
		}

		// address

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;
	}
}
