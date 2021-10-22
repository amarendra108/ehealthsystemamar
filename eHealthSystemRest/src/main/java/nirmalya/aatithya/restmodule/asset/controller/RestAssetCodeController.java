package nirmalya.aatithya.restmodule.asset.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.asset.dao.AssetCodeDao;
import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.model.ProductCategoryModel;
import nirmalya.aatithya.restmodule.master.model.RestAssetModel;

import nirmalya.aatithya.restmodule.procurment.model.InventoryRequisitionModel;
import nirmalya.aatithya.restmodule.procurment.model.InventorySkuProductModel;


@RestController
@RequestMapping(value = "asset/")
public class RestAssetCodeController {
Logger logger = LoggerFactory.getLogger(RestAssetCodeController.class);
	
	@Autowired
	AssetCodeDao assetCodeDao;
	
	@GetMapping(value = "getstorelist")
	public List<DropDownModel> storeList() {
		logger.info("Method : storeList starts");

		logger.info("Method : storeList ends");
		return assetCodeDao.storeList();
	}
	
	@GetMapping(value="viewassetcode")
	public JsonResponse<List<RestAssetCodeModel>> viewAssetCode(){
		logger.info("Method : viewAssetCode starts");
		
		logger.info("Method : viewAssetCode ends");
		return assetCodeDao.viewAssetCode();
	}
	/*
	 * Get mapping for get Item Name
	 */

	@GetMapping(value = "getitemListByAutoSearch")
	public ResponseEntity<JsonResponse<List<RestAssetCodeModel>>> ItemAutoSearchList(
			@RequestParam String id) {
		logger.info("Method : ItemAutoSearchList starts");

		logger.info("Method : ItemAutoSearchList endss");
		return assetCodeDao.ItemAutoSearchList(id);
	}
	
	//add

	@PostMapping(value = "addAssetCode")
	public JsonResponse<Object> addAssetCode(@RequestBody RestAssetCodeModel asset) {
		logger.info("Method : addAssetCode starts");

		logger.info("Method : addAssetCode ends");
		return assetCodeDao.addAssetCode(asset);
	}
	
	//get product category list
	@RequestMapping(value = "getProductCategoryListModal", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ProductCategoryModel>>> getProductCategoryListModal() {
		logger.info("Method : getProductCategoryListModal starts");
		
		logger.info("Method : getProductCategoryListModal ends");
		return assetCodeDao.getProductCategoryListModal();
	}
	
	//get Products By Cat
	
	@GetMapping(value = "getProductsByCat")
	public ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> getProductsByCat(@RequestParam String id) {
		logger.info("Method in rest: getProductsByCat starts");
			
		logger.info("Method in rest: getProductsByCat ends");
		return assetCodeDao.getProductsByCat(id);
	}
	
	//delete
	
	@GetMapping(value="deleteAssetCode")
	public JsonResponse<RestAssetCodeModel> deleteAssetCode(@RequestParam String id){
		logger.info("Method : deleteAssetCode starts");
		
		logger.info("Method : deleteAssetCode ends");
		return assetCodeDao.deleteAssetCode(id);
	}
	
	//edit
	
	@GetMapping(value="editassetcode")
	public JsonResponse<RestAssetCodeModel> AssetCode(@RequestParam String id){
		logger.info("Method : AssetCode starts");
		
		logger.info("Method : editStudent ends");
		return assetCodeDao.editAssetCode(id);
	}
	
	@RequestMapping(value = "getAllAssetsList", method = { RequestMethod.GET})
	public ResponseEntity<JsonResponse<List<RestAssetModel>>> getAllAssetsList() {
		logger.info("Method : getAllAssetsList starts");

		logger.info("Method : getAllAssetsList ends");
		return assetCodeDao.getAllAssetsList();
	}
}

