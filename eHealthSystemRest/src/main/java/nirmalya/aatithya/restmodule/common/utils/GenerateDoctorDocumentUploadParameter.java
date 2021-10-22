package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDocumentUpload;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorProfileModel;

public class GenerateDoctorDocumentUploadParameter {
	
public static String getdrDataUpload(RestDoctorProfileModel doctor) {
		
		String s = "";
		String document = "";
		
		
		  for (RestDoctorDocumentUpload a : doctor.getDocumentList()) {
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
