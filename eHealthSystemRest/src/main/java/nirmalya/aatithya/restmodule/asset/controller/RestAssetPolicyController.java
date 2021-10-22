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
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.controller.ProductMasterRestController;
import nirmalya.aatithya.restmodule.asset.dao.AssetPolicyDao;
import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.asset.model.RestAssetPolicyModel;
import nirmalya.aatithya.restmodule.master.model.ProductMasterModel;
@RestController
@RequestMapping(value = "asset/")
public class RestAssetPolicyController {
	
	Logger logger = LoggerFactory.getLogger(ProductMasterRestController.class);

	@Autowired
	AssetPolicyDao assetPolicyDao;
	
	@RequestMapping(value = "getProductSKUListing", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ProductMasterModel>>> getProductSKUListing() {
		logger.info("Method : getProductSKUListing starts");
		
		logger.info("Method : getProductSKUListing ends");
		return assetPolicyDao.getProductSKUListing();
	}
	
	//service type list
	
	@GetMapping(value = "getSeviceTypelist")
	public List<DropDownModel> serviceTypeList() {
		logger.info("Method : serviceTypeList starts");

		logger.info("Method : serviceTypeList ends");
		return assetPolicyDao.serviceTypeList();
	}
	
	//frequency list
	
	@GetMapping(value = "getFrequencylist")
	public List<DropDownModel> frequencyList() {
		logger.info("Method : frequencyList starts");

		logger.info("Method : frequencyList ends");
		return assetPolicyDao.frequencyList();
	}
	
	//add asset policy
	
	@PostMapping(value="addassetpolicy")
	public JsonResponse<Object> addAssetPolicy(@RequestBody RestAssetPolicyModel policy){
		logger.info("Method : addAssetPolicy starts");
		
		logger.info("Method : addAssetPolicy ends");
		return assetPolicyDao.addAssetPolicy(policy);
	}
	
	//view asset policy
	
	@GetMapping(value="viewassetpolicy")
	public JsonResponse<List<RestAssetPolicyModel>> viewAssetPolicy(){
		logger.info("Method : viewAssetPolicy starts");
		
		logger.info("Method : viewAssetPolicy ends");
		return assetPolicyDao.viewAssetPolicy();
	}
	
	//edit asset policy
		
	@GetMapping(value = "editassetpolicy")
	public JsonResponse<RestAssetPolicyModel> editAssetPolicy(@RequestParam String id) {
		logger.info("Method : editAssetPolicy starts");

		logger.info("Method : editAssetPolicy ends");
		return assetPolicyDao.editAssetPolicy(id);
	}
	
	//delete asset policy
	
		@GetMapping(value="deleteAssetPolicy")
		public JsonResponse<RestAssetPolicyModel> deleteAssetPolicy(@RequestParam String id){
			logger.info("Method : deleteAssetPolicy starts");
			
			logger.info("Method : deleteAssetPolicy ends");
			return assetPolicyDao.deleteAssetPolicy(id);
		}
}


