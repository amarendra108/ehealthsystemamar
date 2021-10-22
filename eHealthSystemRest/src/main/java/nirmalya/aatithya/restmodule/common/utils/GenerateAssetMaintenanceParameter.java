package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.asset.model.RestAssetMaintenanceModel;

public class GenerateAssetMaintenanceParameter {
	
	public static String getAddassetMaintenanceParam(RestAssetMaintenanceModel asset) {
		
		String s = "";
		
		if (asset.getMaintenanceId() != null) {
			s = s + "@p_maintenanceId='" + asset.getMaintenanceId() + "',";
		}

		if (asset.getAssetCodeId() != null && asset.getAssetCodeId() != "") {
			s = s + "@p_assetCodeId='" + asset.getAssetCodeId() + "',";
		}

		if (asset.getProductId() != null && asset.getProductId() != "") {
			s = s + "@p_productId='" + asset.getProductId() + "',";
		}
		
		if (asset.getPolicyId()!= null) {
			s = s + "@p_policyId='" + asset.getPolicyId() + "',";
		}
		
		if (asset.getDesc() != null && asset.getDesc() != "") {
			s = s + "@p_desc='" + asset.getDesc() + "',";
		}
		
		if (asset.getPerformedDate() != null && asset.getPerformedDate() != "") {
			s = s + "@p_performedDate='" + asset.getPerformedDate() + "',";
		}
		if (asset.getPrice() != null) {
			s = s + "@p_price='" + asset.getPrice() + "',";
		}
		if (asset.getFrequencyId() != null && asset.getFrequencyId() != "") {
			s = s + "@p_frequency='" + asset.getFrequencyId() + "',";
		}

		if (asset.getServiceTypeId() != null && asset.getServiceTypeId() != "") {
			s = s + "@p_serviceTypeId='" + asset.getServiceTypeId() + "',";
		}

		if (asset.getCreatedBy()!= null && asset.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + asset.getCreatedBy() + "',";
		}

		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		//System.out.println("Generate Parameter====="+s);
		return s;
	}

}
