package nirmalya.aatithya.restmodule.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nirmalya.aatithya.restmodule.lab.model.RestTestReportModel;


public class GenerateTestReportParameter {
	public static String getAddTestReportParam(List<RestTestReportModel> testReport) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		String sDate=dateFormat.format(date);
		String s = "";
		String sitem = "";
		
		for (RestTestReportModel m : testReport) {

			sitem = sitem + "(\'" + m.getPatientId() +
					"\',\'" + m.getPatientName() +"\',\'" + m.getPatientAge() +"\',\'"  + m.getGender() +"\',\'" 
					+ m.getWeight() +"\',\'" + m.getHeight() +"\',\'" + m.getMob() +"\',\'" + m.getPhc() +"\',\'" 
					+ sDate+"\',\'" + m.getGrpName() +"\',\'"+ m.getTestName() +"\',\'"+
			m.getTestUnit()+"\',\'"+ m.getRange() +"\',\'"+ m.getActualValue()+"\'),";
		}
		sitem = sitem.substring(0, sitem.length() - 1);

		s = s + "" + sitem + ",";

		if (s != "") {
			s = s.substring(0, s.length() - 1);

		}

		System.out.println("Generate Parameter"+s);

		return s;
	}
}
