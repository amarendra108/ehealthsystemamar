package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.lab.model.RestLabDocumentModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabProfileModel;

public class GenerateLabProfileParameter {
	
public static String getdrDataUpload(RestLabProfileModel lab) {
		
		String s = "";
		String document = "";
		
		
		  for (RestLabDocumentModel a : lab.getDocumentList()) {
			  //System.out.println("doctor.getRoleId()"+doctor.getRoleId());
			 
			  document = document +  "(\'"+lab.getRoleId()+ "\',\'" + lab.getDoctorId()+ "\',\'" + a.getDocumnentName() + "\',\'" +
			  a.getFileName() + "\'),"; 
			  } 
		  document = document.substring(0, document.length() - 1);

			s = s + "" + document + ",";

			if (s != "") {
				s = s.substring(0, s.length() - 1);

			}

			System.out.println("Generate Parameter"+s);

			return s;
		}


}
