package nirmalya.aatithya.restmodule.master.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateProductMasterParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.model.ProductDetailsModel;
import nirmalya.aatithya.restmodule.master.model.ProductMasterModel;

/**
 * @author NirmalyaLabs
 *
 */
@Repository
public class ProductMasterDao {

	Logger logger = LoggerFactory.getLogger(ProductMasterDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBrandListForProduct() {
		logger.info("Method : getBrandListForProduct starts");

		List<DropDownModel> brandList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getBrand").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				brandList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getBrandListForProduct ends");
		return brandList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getModeListForProduct() {
		logger.info("Method : getModeListForProduct starts");
		
		List<DropDownModel> modeList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getMode").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				modeList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getModeListForProduct ends");
		return modeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getHSNCodeListForProduct() {
		logger.info("Method : getHSNCodeListForProduct starts");
		
		List<DropDownModel> hsnCodeList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getHSNCode").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				hsnCodeList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getHSNCodeListForProduct ends");
		return hsnCodeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getVariationTypeListtForProduct() {
		logger.info("Method : getVariationTypeListtForProduct starts");
		
		List<DropDownModel> hsnCodeList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getVariationType").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				hsnCodeList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getVariationTypeListtForProduct ends");
		return hsnCodeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getUOMListForProduct() {
		logger.info("Method : getUOMListForProduct starts");
		
		List<DropDownModel> hsnCodeList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getUOM").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				hsnCodeList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getUOMListForProduct ends");
		return hsnCodeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getVendorListForProduct() {
		logger.info("Method : getVendorListForProduct starts");
		
		List<DropDownModel> hsnCodeList = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getVendor").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				hsnCodeList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getVendorListForProduct ends");
		return hsnCodeList;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ProductMasterModel>> saveProductMaster(ProductMasterModel product) {
		logger.info("Method : saveProductMaster starts");

		Boolean validity = true;
		JsonResponse<ProductMasterModel> resp = new JsonResponse<ProductMasterModel>();
		resp.setMessage("");
		resp.setCode("");

		List<ProductMasterModel> newProduct = new ArrayList<ProductMasterModel>();

		if (product.getProductName() == null || product.getProductName() == "") {
			resp.setMessage("Product Name Required");
			validity = false;
		} else if (product.getBrand() == null || product.getBrand() == "") {
			resp.setMessage("Brand Required");
			validity = false;
		} else if (product.getMode() == null || product.getMode() == "") {
			resp.setMessage("Product Mode Required");
			validity = false;
		}

		if (validity)
			try {
				String values = GenerateProductMasterParameter.saveProduct(product);

				if (product.getProductId() != null && product.getProductId() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
							.setParameter("actionType", "modifyProduct").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						ProductMasterModel item = new ProductMasterModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], null, null, null, null, null, null, null, null, null);
						newProduct.add(item);
					}
				} else {

					List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
							.setParameter("actionType", "addProduct").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						ProductMasterModel item = new ProductMasterModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], null, null, null, null, null, null, null, null, null);
						newProduct.add(item);
					}

				}

				resp.setBody(newProduct.get(0));
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<ProductMasterModel>> response = new ResponseEntity<JsonResponse<ProductMasterModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveProductMaster ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ProductDetailsModel>> saveProductDetails(ProductDetailsModel product) {
		logger.info("Method : saveProductDetails starts");
		
		Boolean validity = true;
		JsonResponse<ProductDetailsModel> resp = new JsonResponse<ProductDetailsModel>();
		resp.setMessage("");
		resp.setCode("");
		
		List<ProductDetailsModel> newProduct = new ArrayList<ProductDetailsModel>();
		
		if (product.getSku() == null || product.getSku() == "") {
			resp.setMessage("SKU Id Required");
			validity = false;
		} else if (product.getModel() == null || product.getModel() == "") {
			resp.setMessage("Model Required");
			validity = false;
		} else if (product.getVariationType() == null || product.getVariationType() == "") {
			resp.setMessage("Variation Type Required");
			validity = false;
		} else if (product.getVariationValue() == null || product.getVariationValue() == "") {
			resp.setMessage("Variation Value Required");
			validity = false;
		} else if (product.getUnit() == null || product.getUnit() == "") {
			resp.setMessage("UOM Required");
			validity = false;
		}
		
		if (validity)
			try {
				String values = GenerateProductMasterParameter.saveProductDtls(product);
				
				if (product.getIsEdit() != null && product.getIsEdit() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
							.setParameter("actionType", "modifyProductDtls").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						
						ProductDetailsModel item = new ProductDetailsModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], null, null, null, null);
						newProduct.add(item);
					}
				} else {
					
					List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
							.setParameter("actionType", "addProductDtls").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						
						ProductDetailsModel item = new ProductDetailsModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], null, null, null, null);
						newProduct.add(item);
					}
					
				}
				
				resp.setBody(newProduct.get(0));
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		
		ResponseEntity<JsonResponse<ProductDetailsModel>> response = new ResponseEntity<JsonResponse<ProductDetailsModel>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : saveProductDetails ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ProductDetailsModel>> saveProductPurchaseDetails(ProductDetailsModel product) {
		logger.info("Method : saveProductPurchaseDetails starts");
		
		Boolean validity = true;
		JsonResponse<ProductDetailsModel> resp = new JsonResponse<ProductDetailsModel>();
		resp.setMessage("");
		resp.setCode("");
		
		List<ProductDetailsModel> newProduct = new ArrayList<ProductDetailsModel>();
		
		if (product.getSku() == null || product.getSku() == "") {
			resp.setMessage("SKU Id Required");
			validity = false;
		} else if (product.getVendorId() == null || product.getVendorId() == "") {
			resp.setMessage("Vendor Required");
			validity = false;
		}
		
		if (validity)
			try {
				String values = GenerateProductMasterParameter.saveProductPurchaseDtls(product);
				
				if (product.getIsEdit() != null && product.getIsEdit() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
							.setParameter("actionType", "modifyProductPurchase").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						
						ProductDetailsModel item = new ProductDetailsModel(m[0], m[1], null, null, m[2], m[3], m[4], m[5], m[6], m[7], null, null, null, m[8], m[9]);
						newProduct.add(item);
					}
				} else {
					
					List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
							.setParameter("actionType", "addProductPurchase").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						
						ProductDetailsModel item = new ProductDetailsModel(m[0], m[1], null, null, m[2], m[3], m[4], m[5], m[6], m[7], null, null, null, m[8], m[9]);
						newProduct.add(item);
					}
					
				}
				if(newProduct.size() > 0) {
					resp.setBody(newProduct.get(0));
				}
				
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		
		ResponseEntity<JsonResponse<ProductDetailsModel>> response = new ResponseEntity<JsonResponse<ProductDetailsModel>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : saveProductPurchaseDetails ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ProductMasterModel>>> getProductSKUListing() {
		logger.info("Method : getProductSKUListing starts");

		List<ProductMasterModel> locationList = new ArrayList<ProductMasterModel>();
		JsonResponse<List<ProductMasterModel>> resp = new JsonResponse<List<ProductMasterModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getProductList").setParameter("actionValue", "").getResultList();
			for (Object[] m : x) {
//				Object createDate = null;
//
//				if (m[11] != null) {
//					createDate = DateFormatter.returnStringDateMonth(m[11]);
//				}

				ProductMasterModel dropDownModel = new ProductMasterModel(m[0], m[1], m[2], m[3], null, null, m[4], null ,null, m[5],
						m[6], m[7], m[8], m[9], m[10], m[11].toString(), m[12], m[13], m[14]);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<ProductMasterModel>>> response = new ResponseEntity<JsonResponse<List<ProductMasterModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getProductSKUListing ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ProductDetailsModel>>> getSKUListingById(String id) {
		logger.info("Method : getSKUListingById starts");
		
		List<ProductDetailsModel> locationList = new ArrayList<ProductDetailsModel>();
		JsonResponse<List<ProductDetailsModel>> resp = new JsonResponse<List<ProductDetailsModel>>();
		
		try {
			String value = "SET @P_ProductId = '" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getSKUListingById").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
//				Object createDate = null;
//				
//				if (m[11] != null) {
//					createDate = DateFormatter.returnStringDateMonth(m[11]);
//				}
				
				ProductDetailsModel dropDownModel = new ProductDetailsModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11].toString(), "2", null, null);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<List<ProductDetailsModel>>> response = new ResponseEntity<JsonResponse<List<ProductDetailsModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getSKUListingById ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ProductDetailsModel>>> getSKUPurchaseListingById(String id) {
		logger.info("Method : getSKUPurchaseListingById starts");
		
		List<ProductDetailsModel> locationList = new ArrayList<ProductDetailsModel>();
		JsonResponse<List<ProductDetailsModel>> resp = new JsonResponse<List<ProductDetailsModel>>();
		
		try {
			String value = "SET @P_ProductId = '" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getSKUPurchaseListingById").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
//				Object createDate = null;
//				
//				if (m[9] != null) {
//					createDate = DateFormatter.returnStringDateMonth(m[9]);
//				}
				
				ProductDetailsModel dropDownModel = new ProductDetailsModel(m[0], m[1], null, null, m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9].toString(), null, m[10], m[11]);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<List<ProductDetailsModel>>> response = new ResponseEntity<JsonResponse<List<ProductDetailsModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getSKUPurchaseListingById ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ProductMasterModel>> getProductDetailsById(String id) {
		logger.info("Method : getProductDetailsById starts");

		List<ProductMasterModel> locationList = new ArrayList<ProductMasterModel>();
		JsonResponse<ProductMasterModel> resp = new JsonResponse<ProductMasterModel>();

		String value = "SET @P_ProductId='" + id + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getProductDetailsById").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {

				ProductMasterModel dropDownModel = new ProductMasterModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], null, null, null, null, null, null, null, null, null);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList.get(0));

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<ProductMasterModel>> response = new ResponseEntity<JsonResponse<ProductMasterModel>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getProductDetailsById ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ProductDetailsModel>> getSKUDetailsById(String id, String skuid) {
		logger.info("Method : getSKUDetailsById starts");
		
		List<ProductDetailsModel> locationList = new ArrayList<ProductDetailsModel>();
		JsonResponse<ProductDetailsModel> resp = new JsonResponse<ProductDetailsModel>();
		
		String value = "SET @P_ProductId='" + id + "', @P_SKUID='" + skuid + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getSKUDetailsById").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				
				ProductDetailsModel dropDownModel = new ProductDetailsModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], null, "2", null, null);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList.get(0));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<ProductDetailsModel>> response = new ResponseEntity<JsonResponse<ProductDetailsModel>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getSKUDetailsById ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ProductDetailsModel>> getSKUPurchaseDetails(String id, String skuid, String vendor) {
		logger.info("Method : getSKUPurchaseDetails starts");
		
		List<ProductDetailsModel> locationList = new ArrayList<ProductDetailsModel>();
		JsonResponse<ProductDetailsModel> resp = new JsonResponse<ProductDetailsModel>();
		
		String value = "SET @P_ProductId='" + id + "', @P_SKUID='" + skuid + "', @P_Vendor='" + vendor + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "editSKUPurchaseDetails").setParameter("actionValue", value) 
					.getResultList();
			for (Object[] m : x) {
				
				ProductDetailsModel dropDownModel = new ProductDetailsModel(m[0], m[1], null, null, m[2], m[3], m[4], m[5], m[6], m[7], null, null, "2", m[8], m[9]);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList.get(0));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<ProductDetailsModel>> response = new ResponseEntity<JsonResponse<ProductDetailsModel>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getSKUPurchaseDetails ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<DropDownModel>> getVariantDetails(String id, String skuid) {
		logger.info("Method : getVariantDetails starts");
		
		List<DropDownModel> locationList = new ArrayList<DropDownModel>();
		JsonResponse<DropDownModel> resp = new JsonResponse<DropDownModel>();
		
		String value = "SET @P_ProductId='" + id + "', @P_SKUID='" + skuid + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getVariantDetails").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList.get(0));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<DropDownModel>> response = new ResponseEntity<JsonResponse<DropDownModel>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getVariantDetails ends");
		return response;
	}
}
