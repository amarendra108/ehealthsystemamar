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

import nirmalya.aatithya.restmodule.master.dao.WarehouseDao;
import nirmalya.aatithya.restmodule.master.model.LocationMasterModel;
import nirmalya.aatithya.restmodule.master.model.LocationRoomModel;
import nirmalya.aatithya.restmodule.master.model.LocationSectionModel;
import nirmalya.aatithya.restmodule.master.model.WareHouseModel;
import nirmalya.aatithya.restmodule.master.model.WirehouseRomeModel;
import nirmalya.aatithya.restmodule.master.model.ZoneMasterModel;
import nirmalya.aatithya.restmodule.master.model.ZoneRackModel;

/**
 * @author NirmalyaLabs
 *
 */
@RestController
@RequestMapping(value = "master")
public class WarehouseRestController {
	Logger logger = LoggerFactory.getLogger(LocationMasterRestController.class);

	@Autowired
	WarehouseDao warehouseDao;
	
	@RequestMapping(value = "rest-get-locationDeatils", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<WareHouseModel>>> getLocationDetails(@RequestParam String id) {
		logger.info("Method : getLocationDetails starts");
		logger.info("Method : getLocationDetails ends");
		return warehouseDao.getLocationDetails(id);
	}
	
	@RequestMapping(value = "getWarehouseLocationList", method = { RequestMethod.GET })
	public List<DropDownModel> getWarehouseLocationList() {
		logger.info("Method : getWarehouseLocationList starts");
		
		logger.info("Method : getWarehouseLocationList ends");
		return warehouseDao.getWarehouseLocationList();
	}
	@RequestMapping(value = "saveZoneMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ZoneMasterModel>> saveZoneMaster(@RequestBody ZoneMasterModel zoneMasterModel) {
		logger.info("Method : saveZoneMaster starts");
		System.out.println("zoneMasterModel"+zoneMasterModel);
		logger.info("Method : saveZoneMaster ends");
		return warehouseDao.saveZoneMaster(zoneMasterModel);
	}
	
	@RequestMapping(value = "getZoneDetails", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ZoneMasterModel>>> getZoneDetails(@RequestParam String id) {
		logger.info("Method : getZoneDetails starts");
		logger.info("Method : getZoneDetails ends");
		return warehouseDao.getZoneDetails(id);
	}
	
	
	@RequestMapping(value = "editZoneMaster", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ZoneMasterModel>> editZoneMaster(@RequestParam String id) {
		logger.info("Method : editZoneMaster starts");
		
		logger.info("Method : editZoneMaster ends");
		return warehouseDao.editZoneMaster(id);
	}
	
	@RequestMapping(value = "deleteZoneMaster", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteZoneMaster(@RequestParam String id, @RequestParam String createdBy) {
		logger.info("Method : deleteZoneMaster starts");
		
		logger.info("Method : deleteZoneMaster ends");
		return warehouseDao.deleteZoneMaster(id,createdBy);
	}
	
	@RequestMapping(value = "viewRackListByZone", method = { RequestMethod.GET })
	public List<ZoneRackModel> viewRackListByZone(@RequestParam String id) {
		logger.info("Method : viewRackListByZone starts");
		
		logger.info("Method : viewRackListByZone ends");
		return warehouseDao.viewRackListByZone(id);
	}
	
	@RequestMapping(value = "saveRackMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ZoneRackModel>> saveRackMaster(@RequestBody ZoneRackModel zoneRackModel) {
		logger.info("Method : saveRackMaster starts");
		
		logger.info("Method : saveRackMaster ends");
		return warehouseDao.saveRackMaster(zoneRackModel);
	}
	@RequestMapping(value = "editRackMaster", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ZoneRackModel>> editRackMaster(@RequestParam String id) {
		logger.info("Method : editRackMaster starts");
		
		logger.info("Method : editRackMaster ends");
		return warehouseDao.editRackMaster(id);
	}
	
	@RequestMapping(value = "deleteRackMaster", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteRackMaster(@RequestParam String id, @RequestParam String createdBy) {
		logger.info("Method : deleteRackMaster starts");
		
		logger.info("Method : deleteRackMaster ends");
		return warehouseDao.deleteRackMaster(id,createdBy);
	}
	
	@RequestMapping(value = "getwarehouseRoomDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<List<WirehouseRomeModel>>> getwarehouseRoomDetails(@RequestBody List<String> id) {
		logger.info("Method : getwarehouseRoomDetails starts");
		
		logger.info("Method : getwarehouseRoomDetails ends");
		return warehouseDao.getwarehouseRoomDetails(id);
	}
	
	@RequestMapping(value = "countZoneWiseRoom", method = { RequestMethod.POST })
	public List<DropDownModel> countZoneWiseRoom(@RequestBody List<String> id) {
		logger.info("Method : countZoneWiseRoom starts");
		
		logger.info("Method : countZoneWiseRoom ends");
		return warehouseDao.countZoneWiseRoom(id);
	}
	
	@RequestMapping(value = "viewRoomListByRack", method = { RequestMethod.GET })
	public List<WirehouseRomeModel> viewRoomListByRack(@RequestParam String id) {
		logger.info("Method : viewRoomListByRack starts");

		logger.info("Method : viewRoomListByRack ends");
		return warehouseDao.viewRoomListByRack(id);
	}
	
	@RequestMapping(value = "saveBinMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<WirehouseRomeModel>> saveBinMaster(@RequestBody WirehouseRomeModel location) {
		logger.info("Method : saveBinMaster starts");
		
		logger.info("Method : saveBinMaster ends");
		return warehouseDao.saveBinMaster(location);
	} 
	
	@RequestMapping(value = "deleteBinMaster", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteBinMaster(@RequestParam String id, @RequestParam String createdBy) {
		logger.info("Method : deleteBinMaster starts");
		
		logger.info("Method : deleteBinMaster ends");
		return warehouseDao.deleteBinMaster(id,createdBy);
	}
}
