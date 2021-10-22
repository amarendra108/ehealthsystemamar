package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.reception.model.RestReceptionProfileDocumentModel;
import nirmalya.aatithya.restmodule.reception.model.RestReceptionProfileModel;

public class GenerateReceptionDocumentUploadParameter {
	
public static String getdrDataUpload(RestReceptionProfileModel doctor) {
		
		String s = "";
		String document = "";
		
		
		  for (RestReceptionProfileDocumentModel a : doctor.getDocumentList()) {
			  //System.out.println("doctor.getRoleId()"+doctor.getRoleId());
			 
			  document = document +  "(\'"+doctor.getRoleId()+ "\',\'" + doctor.getDoctorId()+ "\',\'" + a.getDocumnentName() + "\',\'" +
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
