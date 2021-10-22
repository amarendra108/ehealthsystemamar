package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.master.model.ProductDetailsModel;
import nirmalya.aatithya.restmodule.master.model.ProductMasterModel;

public class GenerateProductMasterParameter {

	public static String saveProduct(ProductMasterModel product) {
		
		String s = "";
		String img = "";
		
		if(product.getProductId()!=null && product.getProductId()!="") {
			s = s + "@p_productId='" + product.getProductId() + "',";
		}
		if(product.getProductName()!=null && product.getProductName()!="") {
			s = s + "@p_productName='" + product.getProductName() + "',";
		}
		if(product.getBrand()!=null && product.getBrand()!="") {
			s = s + "@p_brand='" + product.getBrand() + "',";
		}
		if(product.getMode()!=null && product.getMode()!="") {
			s = s + "@p_mode='" + product.getMode() + "',";
		}
		if(product.getHsnCode()!=null && product.getHsnCode()!="") {
			s = s + "@p_hsnCode='" + product.getHsnCode() + "',";
		}
		if(product.getSicCode()!=null && product.getSicCode()!="") {
			s = s + "@p_sicCode='" + product.getSicCode() + "',";
		}
		if(product.getProductCategory()!=null && product.getProductCategory()!="") {
			s = s + "@p_productCat='" + product.getProductCategory() + "',";
		}
		if(product.getProductCategoryText()!=null && product.getProductCategoryText()!="") {
			s = s + "@p_productCatText='" + product.getProductCategoryText() + "',";
		}
		if(product.getCreatedBy()!=null && product.getCreatedBy()!="") {
			s = s + "@p_createdBy='" + product.getCreatedBy() + "',";
		}
		if(product.getProductStatus()!=null && product.getProductStatus()!="") {
			s = s + "@p_isProdActive='" + product.getProductStatus() + "',";
		} else {
			s = s + "@p_isProdActive='" + 0 + "',";
		}
		
		if(product.getImgName().size() > 0) {
			for(String m : product.getImgName()) {
				img = img + "(@p_productId,\"" + m + "\"),";
			}
			
			img = img.substring(0, img.length() - 1);
		}
		s = s + "@p_productImg='" + img + "';";
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println(s);
		
		return s;
	}

	public static String saveProductDtls(ProductDetailsModel product) {

		String s = "";
		
		if(product.getProductId()!=null && product.getProductId()!="") {
			s = s + "@p_productDtlsId='" + product.getProductId() + "',";
		}
		if(product.getSku()!=null && product.getSku()!="") {
			s = s + "@p_skuId='" + product.getSku() + "',";
		}
		if(product.getModel()!=null && product.getModel()!="") {
			s = s + "@p_model='" + product.getModel() + "',";
		}
		if(product.getManufacture()!=null && product.getManufacture()!="") {
			s = s + "@p_manufactureItem='" + product.getManufacture() + "',";
		}
		if(product.getVariationType()!=null && product.getVariationType()!="") {
			s = s + "@p_variationType='" + product.getVariationType() + "',";
		}
		if(product.getVariationValue()!=null && product.getVariationValue()!="") {
			s = s + "@p_variationValue='" + product.getVariationValue() + "',";
		}
		if(product.getUnit()!=null && product.getUnit()!="") {
			s = s + "@p_unit='" + product.getUnit() + "',";
		}
		if(product.getCreatedBy()!=null && product.getCreatedBy()!="") {
			s = s + "@p_createdBy='" + product.getCreatedBy() + "',";
		}
		if(product.getSalePrice()!=null) {
			s = s + "@p_salePrice=" + product.getSalePrice() + ",";
		}
		if(product.getSaleTax()!=null) {
			s = s + "@p_saleTax=" + product.getSaleTax() + ",";
		}
		if(product.getSaleCess()!=null) {
			s = s + "@p_saleCess=" + product.getSaleCess() + ",";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println(s);
		
		return s;
	}
	
	public static String saveProductPurchaseDtls(ProductDetailsModel product) {
		
		String s = "";
		
		if(product.getProductId()!=null && product.getProductId()!="") {
			s = s + "@p_productPurchaseId='" + product.getProductId() + "',";
		}
		if(product.getSku()!=null && product.getSku()!="") {
			s = s + "@p_skuPrId='" + product.getSku() + "',";
		}
		if(product.getVendorId()!=null && product.getVendorId()!="") {
			s = s + "@p_vendorId='" + product.getVendorId() + "',";
		}
		if(product.getCreatedBy()!=null && product.getCreatedBy()!="") {
			s = s + "@p_createdBy='" + product.getCreatedBy() + "',";
		}
		if(product.getSalePrice()!=null) {
			s = s + "@p_purchasePrice=" + product.getSalePrice() + ",";
		}
		if(product.getSaleTax()!=null) {
			s = s + "@p_purchaseTax=" + product.getSaleTax() + ",";
		}
		if(product.getSaleCess()!=null) {
			s = s + "@p_purchaseCess=" + product.getSaleCess() + ",";
		}
		if(product.getMoq()!=null) {
			s = s + "@p_moq=" + product.getMoq() + ",";
		}
		
		if(product.getEditSKUId()!=null && product.getEditSKUId()!="") {
			s = s + "@p_editSkuId='" + product.getEditSKUId() + "',";
		}
		if(product.getEditVendorId()!=null && product.getEditVendorId()!="") {
			s = s + "@p_editVendorId='" + product.getEditVendorId() + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);
			
			s = "SET " + s + ";";
		}
		
		System.out.println(s);
		
		return s;
	}

}
