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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.asset.dao.AssetMaintenanceDao;
import nirmalya.aatithya.restmodule.asset.model.RestAssetMaintenanceModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.controller.ProductMasterRestController;

@RestController
@RequestMapping(value = "asset/")
public class RestAssetMaintenanceController {
	
	Logger logger = LoggerFactory.getLogger(RestAssetMaintenanceController.class);

	@Autowired
	AssetMaintenanceDao assetMaintenanceDao;
	
	
	//view asset policy
	
		@GetMapping(value="viewassetnewpolicy")
		public JsonResponse<List<RestAssetMaintenanceModel>> viewAssetPolicy(){
			logger.info("Method : viewAssetPolicy starts");
			
			logger.info("Method : viewAssetPolicy ends");
			return assetMaintenanceDao.viewAssetPolicy();
		}
	
	//Auto Search for asset list
		
		@GetMapping(value = "getassetListByAutoSearch")
		public ResponseEntity<JsonResponse<List<RestAssetMaintenanceModel>>> AssetAutoSearchList(
				@RequestParam String id) {
			logger.info("Method : AssetAutoSearchList starts");

			logger.info("Method : AssetAutoSearchList endss");
			return assetMaintenanceDao.AssetAutoSearchList(id);
		}	

	//Add Asset Maintenance Details
		
		@PostMapping(value = "addassetmaintenance")
		public JsonResponse<Object> addAssetMaintenance(@RequestBody RestAssetMaintenanceModel asset) {
			logger.info("Method : addAssetMaintenance starts");

			logger.info("Method : addAssetMaintenance ends");
			return assetMaintenanceDao.addAssetMaintenance(asset);
		}
}
