package nirmalya.aatithya.restmodule.asset.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.asset.dao.AssetPoAndInventoryDao;
import nirmalya.aatithya.restmodule.asset.model.RestAssetPoAndInventoryModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.controller.ProductMasterRestController;

@RestController
@RequestMapping(value = "asset/")
public class RestAssetPoAndInventoryController {
	Logger logger = LoggerFactory.getLogger(ProductMasterRestController.class);

	@Autowired
	AssetPoAndInventoryDao assetPoAndInventoryDao;
	
	
	//view asset policy
	
		@GetMapping(value="viewassetpo")
		public JsonResponse<List<RestAssetPoAndInventoryModel>> viewAssetPo(){
			logger.info("Method : viewAssetPo starts");
			
			logger.info("Method : viewAssetPo ends");
			return assetPoAndInventoryDao.viewAssetPo();
		}
}
