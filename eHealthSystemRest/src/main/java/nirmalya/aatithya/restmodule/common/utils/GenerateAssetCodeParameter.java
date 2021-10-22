/**Defines SQL SET Parameters */
package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;

/**
 * @author Nirmalya Labs
 *
 */
public class GenerateAssetCodeParameter {

	/**
	 * returns parameter set for property assetcode class
	 **/

	public static String getAddassetCodeParam(RestAssetCodeModel asset) {
		String s = "";
		//System.out.println("asset in gp"+asset);
		if (asset.getAssetCode() != null && asset.getAssetCode() != "") {
			s = s + "@p_assetCodeId='" + asset.getAssetCode() + "',";
		}

		if (asset.getAssetCodeName() != null && asset.getAssetCodeName() != "") {
			s = s + "@p_assetCodeName='" + asset.getAssetCodeName() + "',";
		}

		if (asset.getSku() != null && asset.getSku() != "") {
			s = s + "@p_sku='" + asset.getSku() + "',";
		}
		
		if (asset.getItem() != null && asset.getItem() != "") {
			s = s + "@p_item='" + asset.getItem() + "',";
		}
		
		if (asset.getStore() != null && asset.getStore() != "") {
			s = s + "@p_store='" + asset.getStore() + "',";
		}
		
		if (asset.getSerialNo() != null && asset.getSerialNo() != "") {
			s = s + "@p_serialno='" + asset.getSerialNo() + "',";
		}
		if (asset.getDateOfPurchase() != null && asset.getDateOfPurchase() != "") {
			s = s + "@p_dop='" + asset.getDateOfPurchase() + "',";
		}

		if (asset.getGuaranty() != null && asset.getGuaranty() != "") {
			s = s + "@p_grrnty='" + asset.getGuaranty() + "',";
		}

		if (asset.getBrandId() != null && asset.getBrandId() != "") {
			s = s + "@p_brndNm='" + asset.getBrandId() + "',";
		}

		if (asset.getEmail() != null && asset.getEmail() != "") {
			s = s + "@p_custEmail='" + asset.getEmail() + "',";
		}

		if (asset.getCustPhone() != null && asset.getCustPhone() != "") {
			s = s + "@p_custPhone='" + asset.getCustPhone() + "',";
		}

		if (asset.getDescription() != null && asset.getDescription() != "") {
			s = s + "@p_description='" + asset.getDescription() + "',";
		}
		if (asset.getBarcode() != null && asset.getBarcode() != "") {
			s = s + "@p_barcode='" + asset.getBarcode() + "',";
		}
		if (asset.getCategory() != null && asset.getCategory() != "") {
			s = s + "@p_Category='" + asset.getCategory() + "',";
		}
		if (asset.getGrnInvoice() != null && asset.getGrnInvoice() != "") {
			s = s + "@p_Grn='" + asset.getGrnInvoice() + "',";
		}
		if (asset.getClassificationId() != null && asset.getClassificationId() != "") {
			s = s + "@p_class='" + asset.getClassificationId() + "',";
		}
		if (asset.getWorkingStatus() != null && asset.getWorkingStatus() != "") {
			s = s + "@p_WorkingStatus='" + asset.getWorkingStatus() + "',";
		}
		if (asset.getStatus() != null && asset.getStatus() != "") {
			s = s + "@p_status='" + asset.getStatus() + "',";
		}
		if (asset.getCreatedBy() != null && asset.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + asset.getCreatedBy() + "',";
		}

		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
System.out.println("Generate Parameter====="+s);
		return s;
	}
}