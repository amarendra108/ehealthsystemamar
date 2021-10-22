package nirmalya.aatithya.restmodule.asset.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.account.model.DataSetAccountTree;
import nirmalya.aatithya.restmodule.asset.dao.AssetDashboardDao;
import nirmalya.aatithya.restmodule.asset.model.AssetJobCardModel;
import nirmalya.aatithya.restmodule.asset.model.AssetMaintenanceHistoryModel;
import nirmalya.aatithya.restmodule.asset.model.AssetWithPOInventoryModel;
import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.asset.model.RestAssetGraphModel;
import nirmalya.aatithya.restmodule.common.utils.DataTableRequest;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.procurment.model.ProcurementDashboardModel;
import nirmalya.aatithya.restmodule.procurment.model.RestProcurementStockDetailsModel;

/**
 * @author Nirmalya Labs
 *
 */
@RestController
@RequestMapping(value = "asset/")
public class AssetDashboardRestController {

	Logger logger = LoggerFactory.getLogger(AssetDashboardRestController.class);
	
	@Autowired
	AssetDashboardDao assetDashboardDao;

	/*
	 * totla assets
	 */
	@GetMapping(value = "assetssCount")
	public List<DropDownModel> totalAsset() {
		logger.info("Method : totalAsset starts");

		logger.info("Method : totalAsset ends");
		return assetDashboardDao.totalAsset();
	}

	/*
	 * totla assets maintenance
	 */
	@GetMapping(value = "assetssMentenance")
	public List<DropDownModel> totalAssetMentaince() {
		logger.info("Method : totalAssetMentaince starts");

		logger.info("Method : totalAssetMentaince ends");
		return assetDashboardDao.totalAssetMentaince();
	}
	
	
	/*
	 * view asset Category
	 */
	@GetMapping(value="assetCategory")
	public List<RestAssetCodeModel> viewAssetCode(){
		logger.info("Method : viewAssetCategory starts");
		
		logger.info("Method : viewAssetCategory ends");
		return assetDashboardDao.viewAssetCategory();
	}
	
	@GetMapping(value = "asset-service")
	public List<RestAssetCodeModel> restAssetServiceType() {
		logger.info("Method : restAddPurchaseAnalysis starts");
		logger.info("Method : restAddPurchaseAnalysis ends");
		return assetDashboardDao.restAssetServiceType();
	}

	/*
	 * Asset Report For Graph
	 */
	@RequestMapping(value = "getAssetreportForGraph", method = { RequestMethod.GET })
	public List<RestAssetGraphModel> getreportForGraph() {

		logger.info("Method : getreportForGraph starts");
		logger.info("Method : getreportForGraph ends");

		return assetDashboardDao.getreportForGraph();
	}
	
}
