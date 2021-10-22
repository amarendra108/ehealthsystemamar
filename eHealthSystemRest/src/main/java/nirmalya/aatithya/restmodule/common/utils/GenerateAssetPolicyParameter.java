package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.asset.model.RestAssetPolicyModel;

public class GenerateAssetPolicyParameter {
	
	public static String getAssetPolicy(RestAssetPolicyModel policy) {

		String s = "";
		
		if (policy.getPolicyId() != null ) {
			s = s + "@p_policyId='" + policy.getPolicyId() + "',";
		}
		if (policy.getItem() != null && policy.getItem() != "") {
			s = s + "@p_item='" + policy.getItem() + "',";
		}
		if (policy.getServiceName() != null && policy.getServiceName() != "") {
			s = s + "@p_SName='" + policy.getServiceName() + "',";
		}
		if (policy.getFrequency() != null && policy.getFrequency() != "") {
			s = s + "@p_frequency='" + policy.getFrequency() + "',";
		}
		if (policy.getServiceType() != null && policy.getServiceType() != "") {
			s = s + "@p_SType='" + policy.getServiceType() + "',";
		}
		if (policy.getTaskPerformed() != null && policy.getTaskPerformed() != "") {
			s = s + "@p_taskPerformed='" + policy.getTaskPerformed() + "',";
		}
		if (policy.getCreatedBy() != null && policy.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + policy.getCreatedBy() + "',";
		}


			if (s != "") {
				s = s.substring(0, s.length() - 1);

				s = "SET " + s + ";";
			}

		return s;
	}


}
