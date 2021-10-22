package nirmalya.aatithya.restmodule.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.dao.ProductMasterDao;
import nirmalya.aatithya.restmodule.master.model.LocationMasterModel;
import nirmalya.aatithya.restmodule.master.model.ProductDetailsModel;
import nirmalya.aatithya.restmodule.master.model.ProductMasterModel;

/**
 * @author NirmalyaLabs
 *
 */
@RestController
@RequestMapping(value = "master/")
public class ProductMasterRestController {

	Logger logger = LoggerFactory.getLogger(ProductMasterRestController.class);

	@Autowired
	ProductMasterDao productMasterDao;
	
	@RequestMapping(value = "getBrandListForProduct", method = { RequestMethod.GET })
	public List<DropDownModel> getBrandListForProduct() {
		logger.info("Method : getBrandListForProduct starts");
		
		logger.info("Method : getBrandListForProduct ends");
		return productMasterDao.getBrandListForProduct();
	}
	
	@RequestMapping(value = "getModeListForProduct", method = { RequestMethod.GET })
	public List<DropDownModel> getModeListForProduct() {
		logger.info("Method : getModeListForProduct starts");
		
		logger.info("Method : getModeListForProduct ends");
		return productMasterDao.getModeListForProduct();
	}
	
	@RequestMapping(value = "getHSNCodeListForProduct", method = { RequestMethod.GET })
	public List<DropDownModel> getHSNCodeListForProduct() {
		logger.info("Method : getHSNCodeListForProduct starts");
		
		logger.info("Method : getHSNCodeListForProduct ends");
		return productMasterDao.getHSNCodeListForProduct();
	}
	
	@RequestMapping(value = "getVariationTypeListtForProduct", method = { RequestMethod.GET })
	public List<DropDownModel> getVariationTypeListtForProduct() {
		logger.info("Method : getVariationTypeListtForProduct starts");
		
		logger.info("Method : getVariationTypeListtForProduct ends");
		return productMasterDao.getVariationTypeListtForProduct();
	}
	
	@RequestMapping(value = "getUOMListForProduct", method = { RequestMethod.GET })
	public List<DropDownModel> getUOMListForProduct() {
		logger.info("Method : getUOMListForProduct starts");
		
		logger.info("Method : getUOMListForProduct ends");
		return productMasterDao.getUOMListForProduct();
	}
	
	@RequestMapping(value = "getVendorListForProduct", method = { RequestMethod.GET })
	public List<DropDownModel> getVendorListForProduct() {
		logger.info("Method : getVendorListForProduct starts");
		
		logger.info("Method : getVendorListForProduct ends");
		return productMasterDao.getVendorListForProduct();
	}
	
	@RequestMapping(value = "saveProductMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ProductMasterModel>> saveProductMaster(@RequestBody ProductMasterModel product) {
		logger.info("Method : saveProductMaster starts");
		
		logger.info("Method : saveProductMaster ends");
		return productMasterDao.saveProductMaster(product);
	}
	
	@RequestMapping(value = "saveProductDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ProductDetailsModel>> saveProductDetails(@RequestBody ProductDetailsModel product) {
		logger.info("Method : saveProductDetails starts");
		
		logger.info("Method : saveProductDetails ends");
		return productMasterDao.saveProductDetails(product);
	}
	
	@RequestMapping(value = "saveProductPurchaseDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ProductDetailsModel>> saveProductPurchaseDetails(@RequestBody ProductDetailsModel product) {
		logger.info("Method : saveProductPurchaseDetails starts");
		
		logger.info("Method : saveProductPurchaseDetails ends");
		return productMasterDao.saveProductPurchaseDetails(product);
	}
	
	@RequestMapping(value = "getProductSKUListing", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ProductMasterModel>>> getProductSKUListing() {
		logger.info("Method : getProductSKUListing starts");
		
		logger.info("Method : getProductSKUListing ends");
		return productMasterDao.getProductSKUListing();
	}
	
	@RequestMapping(value = "getSKUListingById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ProductDetailsModel>>> getSKUListingById(@RequestParam String id) {
		logger.info("Method : getSKUListingById starts");
		
		logger.info("Method : getSKUListingById ends");
		return productMasterDao.getSKUListingById(id);
	}
	
	@RequestMapping(value = "getSKUPurchaseListingById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ProductDetailsModel>>> getSKUPurchaseListingById(@RequestParam String id) {
		logger.info("Method : getSKUPurchaseListingById starts");
		
		logger.info("Method : getSKUPurchaseListingById ends");
		return productMasterDao.getSKUPurchaseListingById(id);
	}
	
	@RequestMapping(value = "getProductDetailsById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ProductMasterModel>> getProductDetailsById(@RequestParam String id) {
		logger.info("Method : getProductDetailsById starts");
		
		logger.info("Method : getProductDetailsById ends");
		return productMasterDao.getProductDetailsById(id);
	}
	
	@RequestMapping(value = "getSKUDetailsById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ProductDetailsModel>> getSKUDetailsById(@RequestParam String id, @RequestParam String skuid) {
		logger.info("Method : getSKUDetailsById starts");
		
		logger.info("Method : getSKUDetailsById ends");
		return productMasterDao.getSKUDetailsById(id,skuid);
	}
	
	@RequestMapping(value = "getSKUPurchaseDetails", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ProductDetailsModel>> getSKUPurchaseDetails(@RequestParam String id, @RequestParam String skuid, @RequestParam String vendorId) {
		logger.info("Method : getSKUPurchaseDetails starts");
		
		logger.info("Method : getSKUPurchaseDetails ends");
		return productMasterDao.getSKUPurchaseDetails(id,skuid,vendorId);
	}
	
	@RequestMapping(value = "getVariantDetails", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<DropDownModel>> getVariantDetails(@RequestParam String id, @RequestParam String skuid) {
		logger.info("Method : getVariantDetails starts");
		
		logger.info("Method : getVariantDetails ends");
		return productMasterDao.getVariantDetails(id,skuid);
	}
}
