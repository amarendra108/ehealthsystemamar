package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.master.model.RestReferenceProductModel;

public class GenerateProductReferenceParameter {

	public static String addBrandTypeParam(RestReferenceProductModel restProcurementMasterModel) {

		String s = "";

		if (restProcurementMasterModel.getBrandId() != null || restProcurementMasterModel.getBrandId() != "") {
			s = s + "@p_brandId='" + restProcurementMasterModel.getBrandId() + "',";
		}
		if (restProcurementMasterModel.getBrandName() != null || restProcurementMasterModel.getBrandName() != "") {
			s = s + "@p_brandName='" + restProcurementMasterModel.getBrandName() + "',";
		}
		if (restProcurementMasterModel.getBrandOrder() != null || restProcurementMasterModel.getBrandOrder() != "") {
			s = s + "@p_brandOrder='" + restProcurementMasterModel.getBrandOrder() + "',";
		}
		if (restProcurementMasterModel.getBrandCode() != null || restProcurementMasterModel.getBrandCode() != "") {
			s = s + "@p_brandCode='" + restProcurementMasterModel.getBrandCode() + "',";
		}
		if (restProcurementMasterModel.getBrandDesc() != null || restProcurementMasterModel.getBrandDesc() != "") {
			s = s + "@p_brandDesc='" + restProcurementMasterModel.getBrandDesc() + "',";
		}
		if (restProcurementMasterModel.getBrandStatus() != null || restProcurementMasterModel.getBrandStatus() != "") {
			s = s + "@p_brandStatus='" + restProcurementMasterModel.getBrandStatus() + "',";
		}
		if (restProcurementMasterModel.getBrandCreatedBy() != null
				|| restProcurementMasterModel.getBrandCreatedBy() != "") {
			s = s + "@p_brandCreatedBy='" + restProcurementMasterModel.getBrandCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		System.out.println(s);
		return s;

	}

	public static String addProductTypeParam(RestReferenceProductModel restProcurementMasterModel) {

		String s = "";

		if (restProcurementMasterModel.getProductId() != null || restProcurementMasterModel.getProductId() != "") {
			s = s + "@p_productId='" + restProcurementMasterModel.getProductId() + "',";
		}
		if (restProcurementMasterModel.getProductOrder() != null
				|| restProcurementMasterModel.getProductOrder() != "") {
			s = s + "@p_productOrder='" + restProcurementMasterModel.getProductOrder() + "',";
		}
		if (restProcurementMasterModel.getProductName() != null || restProcurementMasterModel.getProductName() != "") {
			s = s + "@p_productName='" + restProcurementMasterModel.getProductName() + "',";
		}
		if (restProcurementMasterModel.getProductDesc() != null || restProcurementMasterModel.getProductDesc() != "") {
			s = s + "@p_productDesc='" + restProcurementMasterModel.getProductDesc() + "',";
		}
		if (restProcurementMasterModel.getProductStatus() != null
				|| restProcurementMasterModel.getProductStatus() != "") {
			s = s + "@p_productStatus='" + restProcurementMasterModel.getProductStatus() + "',";
		}
		if (restProcurementMasterModel.getProductCreatedBy() != null
				|| restProcurementMasterModel.getProductCreatedBy() != "") {
			s = s + "@p_productCreatedBy='" + restProcurementMasterModel.getProductCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		System.out.println(s);
		return s;

	}

	public static String addVariationTypeParam(RestReferenceProductModel restProcurementMasterModel) {

		String s = "";

		if (restProcurementMasterModel.getVariationId() != null || restProcurementMasterModel.getVariationId() != "") {
			s = s + "@p_variationId='" + restProcurementMasterModel.getVariationId() + "',";
		}
		if (restProcurementMasterModel.getVariationName() != null
				|| restProcurementMasterModel.getVariationName() != "") {
			s = s + "@p_variationName='" + restProcurementMasterModel.getVariationName() + "',";
		}
		if (restProcurementMasterModel.getVariationDesc() != null
				|| restProcurementMasterModel.getVariationDesc() != "") {
			s = s + "@p_variationDesc='" + restProcurementMasterModel.getVariationDesc() + "',";
		}
		if (restProcurementMasterModel.getVariationStatus() != null
				|| restProcurementMasterModel.getVariationStatus() != "") {
			s = s + "@p_variationStatus='" + restProcurementMasterModel.getVariationStatus() + "',";
		}
		if (restProcurementMasterModel.getVariationCreatedBy() != null
				|| restProcurementMasterModel.getVariationCreatedBy() != "") {
			s = s + "@p_variationCreatedBy='" + restProcurementMasterModel.getVariationCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		System.out.println(s);
		return s;

	}
}