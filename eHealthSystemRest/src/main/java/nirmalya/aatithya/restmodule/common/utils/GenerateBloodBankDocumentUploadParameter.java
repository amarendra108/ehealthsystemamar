package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.bloodbank.model.RestBloodBankDocumentUpload;
import nirmalya.aatithya.restmodule.bloodbank.model.RestBloodBankProfileModel;

public class GenerateBloodBankDocumentUploadParameter {
	
public static String getdrDataUpload(RestBloodBankProfileModel doctor) {
		
		String s = "";
		String document = "";
		
		
		  for (RestBloodBankDocumentUpload a : doctor.getDocumentList()) {
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
