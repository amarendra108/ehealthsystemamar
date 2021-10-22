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
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateLocationMasterParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateWareHouseMasterParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.model.LocationMasterModel;
import nirmalya.aatithya.restmodule.master.model.LocationRoomModel;
import nirmalya.aatithya.restmodule.master.model.LocationSectionModel;
import nirmalya.aatithya.restmodule.master.model.WareHouseModel;
import nirmalya.aatithya.restmodule.master.model.WirehouseRomeModel;
import nirmalya.aatithya.restmodule.master.model.ZoneMasterModel;
import nirmalya.aatithya.restmodule.master.model.ZoneRackModel;
@Repository
public class WarehouseDao {

	Logger logger = LoggerFactory.getLogger(WarehouseDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<WareHouseModel>>> getLocationDetails(String id) {

		logger.info("Method : getLocationDetails starts");
		List<WareHouseModel> locationList = new ArrayList<WareHouseModel>();
		JsonResponse<List<WareHouseModel>> resp = new JsonResponse<List<WareHouseModel>>();

		String value = "SET @p_locationId='" + id + "';";
		System.out.println(value);
		try {
			
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
					.setParameter("actionType", "getWarehouseList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				WareHouseModel wareHouseModel = new WareHouseModel(m[0], m[1],m[2],m[3]);
				locationList.add(wareHouseModel);
			}

			resp.setBody(locationList);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<WareHouseModel>>> response = new ResponseEntity<JsonResponse<List<WareHouseModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getLocationDetails ends");
		System.out.println("response"+response);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getWarehouseLocationList() {
		logger.info("Method : getWarehouseLocationList starts");

		List<DropDownModel> locationList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
					.setParameter("actionType", "getWhLocationList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				locationList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getWarehouseLocationList ends");
		return locationList;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ZoneMasterModel>> saveZoneMaster(ZoneMasterModel zoneMasterModel) {
		logger.info("Method : saveZoneMaster starts");
			System.out.println(zoneMasterModel);
		Boolean validity = true;
		JsonResponse<ZoneMasterModel> resp = new JsonResponse<ZoneMasterModel>();
		resp.setMessage("");
		resp.setCode("");
		
		List<ZoneMasterModel> zoneData = new ArrayList<ZoneMasterModel>();

		if (zoneMasterModel.getZoneCode() == null || zoneMasterModel.getZoneCode() == "") {
			resp.setMessage("Zone Code Required");
			validity = false;
		} else if (zoneMasterModel.getZoneName() == null || zoneMasterModel.getZoneName() == "") {
			resp.setMessage("Zone Name Required");
			validity = false;
		
		}
		if (validity)
			try {
				String values = GenerateWareHouseMasterParameter.saveZoneMaster(zoneMasterModel);
				if (zoneMasterModel.getZoneId() != null && zoneMasterModel.getZoneId() != "") {
			
					List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
							.setParameter("actionType", "modifyZone").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						ZoneMasterModel zoneMasterModelList = new ZoneMasterModel(m[0], m[1], m[2],m[3],m[4],null,null,m[5]);
						zoneData.add(zoneMasterModelList);
					}
				} else {
				
					List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
							.setParameter("actionType", "addZone").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						ZoneMasterModel zoneMasterModelList = new ZoneMasterModel(m[0], m[1], m[2],m[3],m[4],null,null,m[5]);
						zoneData.add(zoneMasterModelList);
					}
					System.out.println("values"+values);

				}

				resp.setBody(zoneData.get(0));
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

		ResponseEntity<JsonResponse<ZoneMasterModel>> response = new ResponseEntity<JsonResponse<ZoneMasterModel>>(
				resp, HttpStatus.CREATED);
		System.out.println("rest"+response);
		logger.info("Method : saveZoneMaster ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ZoneMasterModel>>> getZoneDetails(String id) {

		logger.info("Method : getZoneDetails starts");
		List<ZoneMasterModel> locationList = new ArrayList<ZoneMasterModel>();
		JsonResponse<List<ZoneMasterModel>> resp = new JsonResponse<List<ZoneMasterModel>>();

		String value = "SET @p_zoneId='" + id + "';";
		System.out.println(value);
		try {
			System.out.println("x"+value);
			List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
					.setParameter("actionType", "getZoneList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				ZoneMasterModel zoneMasterModel = new ZoneMasterModel(m[0], m[1],m[2],m[3],m[4],null,m[5],m[6]);
				locationList.add(zoneMasterModel);
			}
			System.out.println("zoneMasterModel"+value);
			resp.setBody(locationList);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<ZoneMasterModel>>> response = new ResponseEntity<JsonResponse<List<ZoneMasterModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getZoneDetails ends");
		System.out.println("response"+response);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ZoneMasterModel>> editZoneMaster(String id) {
		logger.info("Method : editZoneMaster starts");

		JsonResponse<ZoneMasterModel> resp = new JsonResponse<ZoneMasterModel>();
		List<ZoneMasterModel> newZone = new ArrayList<ZoneMasterModel>();

		try {

			String value = "SET @P_Zone='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
					.setParameter("actionType", "editZone").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {

				ZoneMasterModel zoneMasterModel = new ZoneMasterModel(m[0], m[1], m[2], m[3],m[4],null,null,m[5]);
				newZone.add(zoneMasterModel);
			}
			
			resp.setBody(newZone.get(0));
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

		ResponseEntity<JsonResponse<ZoneMasterModel>> response = new ResponseEntity<JsonResponse<ZoneMasterModel>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : editZoneMaster ends");
		return response;
	}
	public ResponseEntity<JsonResponse<Object>> deleteZoneMaster(String id, String createdBy) {
		logger.info("Method : deleteZoneMaster starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		if (validity)
			try {

				String value = "SET @P_ModifiedBy='" + createdBy + "', @p_zone='" + id + "';";
				System.out.println("delete data"+value);
				em.createNamedStoredProcedureQuery("warehouseRoutine").setParameter("actionType", "deleteZone")
						.setParameter("actionValue", value).execute();

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

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : deleteFloorMaster ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZoneRackModel> viewRackListByZone(String id) {
		logger.info("Method : viewRackListByZone starts");
		
		List<ZoneRackModel> sectionList = new ArrayList<ZoneRackModel>();
		
		String value = "SET @p_Zone='" + id + "';";
		
		try {
			System.out.println("xxxx@"+value);
			List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
					.setParameter("actionType", "getRackList").setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				ZoneRackModel dropDownModel = new ZoneRackModel(m[0], m[1], m[2], m[3], m[4], m[5],m[6],m[7],m[8],m[9]);
				sectionList.add(dropDownModel);
			}
			System.out.println("viewwwww@"+value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("viewrack"+sectionList);
		logger.info("Method : viewRackListByZone ends");
		return sectionList;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ZoneRackModel>> saveRackMaster(ZoneRackModel zoneRackModel) {
		logger.info("Method : saveRackMaster starts");

		Boolean validity = true;
		JsonResponse<ZoneRackModel> resp = new JsonResponse<ZoneRackModel>();
		resp.setMessage("");
		resp.setCode("");
		
		List<ZoneRackModel> rackData = new ArrayList<ZoneRackModel>();

		if (zoneRackModel.getRackName() == null || zoneRackModel.getRackName() == "") {
			resp.setMessage("Rack Name Required");
			validity = false;
		} else if (zoneRackModel.getRackCode()== null || zoneRackModel.getRackCode() == "") {
			resp.setMessage("Rack Code Required");
			validity = false;
		
		}
		if (validity)
			try {
				String values = GenerateWareHouseMasterParameter.saveRackMaster(zoneRackModel);
				System.out.println(zoneRackModel);
				if (zoneRackModel.getRackId() != null && zoneRackModel.getRackId() != "") {
			
					List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
							.setParameter("actionType", "modifyRack").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						ZoneRackModel zoneRackModelList = new ZoneRackModel(m[0],m[1], m[2],m[3],m[4],m[5],m[6],m[7],m[8],null);
						rackData.add(zoneRackModelList);
					}
				} else {
				System.out.println("xy"+values);
					List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
							.setParameter("actionType", "addRack").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
							
						ZoneRackModel zoneRackModelList = new ZoneRackModel(m[0], m[1], m[2],m[3],m[4],m[5],m[6],m[7],m[8],null);
						rackData.add(zoneRackModelList);
					}
						System.out.println("rackData"+values);
				}

				resp.setBody(rackData.get(0));
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

		ResponseEntity<JsonResponse<ZoneRackModel>> response = new ResponseEntity<JsonResponse<ZoneRackModel>>(
				resp, HttpStatus.CREATED);
			System.out.println(response);
		logger.info("Method : saveRackMaster ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ZoneRackModel>> editRackMaster(String id) {
		logger.info("Method : editRackMaster starts");

		JsonResponse<ZoneRackModel> resp = new JsonResponse<ZoneRackModel>();
		List<ZoneRackModel> newZone = new ArrayList<ZoneRackModel>();

		try {

			String value = "SET @P_Rack='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
					.setParameter("actionType", "editRack").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {

				ZoneRackModel ZoneRackModel = new ZoneRackModel(m[0], m[1], m[2], m[3],m[4],m[5],m[6],m[7],m[8],null);
				newZone.add(ZoneRackModel);
			}
			
			resp.setBody(newZone.get(0));
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

		ResponseEntity<JsonResponse<ZoneRackModel>> response = new ResponseEntity<JsonResponse<ZoneRackModel>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : editRackMaster ends");
		return response;
	}
	public ResponseEntity<JsonResponse<Object>> deleteRackMaster(String id, String createdBy) {
		logger.info("Method : deleteRackMaster starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		if (validity)
			try {

				String value = "SET @P_ModifiedBy='" + createdBy + "', @p_rack='" + id + "';";
				System.out.println("delete data"+value);
				em.createNamedStoredProcedureQuery("warehouseRoutine").setParameter("actionType", "deleteRack")
						.setParameter("actionValue", value).execute();

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

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : deleteRackMaster ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<WirehouseRomeModel>>> getwarehouseRoomDetails(List<String> id) {
		logger.info("Method : getwarehouseRoomDetails starts");
		
		List<WirehouseRomeModel> locationList = new ArrayList<WirehouseRomeModel>();
		JsonResponse<List<WirehouseRomeModel>> resp = new JsonResponse<List<WirehouseRomeModel>>();
		
		String value = GenerateWareHouseMasterParameter.getRackIdList(id);
		
		if(id.size() > 0) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
						.setParameter("actionType", "getwarehouseRoomDetails").setParameter("actionValue", value)
						.getResultList();
				for (Object[] m : x) {
					
					WirehouseRomeModel dropDownModel = new WirehouseRomeModel(m[0], m[1], m[2], null, m[3], m[4], null, m[5], m[6], null,
							m[7], m[8], null, null,null);
					locationList.add(dropDownModel);
				}
				resp.setBody(locationList);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ResponseEntity<JsonResponse<List<WirehouseRomeModel>>> response = new ResponseEntity<JsonResponse<List<WirehouseRomeModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("getwarehouseRoomDetails"+response);
		logger.info("Method : getwarehouseRoomDetails ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> countZoneWiseRoom(List<String> id) {
		logger.info("Method : countZoneWiseRoom starts");
		
		List<DropDownModel> locationList = new ArrayList<DropDownModel>();
		
		String value = GenerateWareHouseMasterParameter.getRackIdList(id);
		
		if(id.size() > 0) {
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
						.setParameter("actionType", "countZoneWiseRoom").setParameter("actionValue", value)
						.getResultList();
				for (Object[] m : x) {
					
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1].toString());
					locationList.add(dropDownModel);


				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		logger.info("Method : countZoneWiseRoom ends");
		return locationList;
	}
	
	@SuppressWarnings("unchecked")
	public List<WirehouseRomeModel> viewRoomListByRack(String id) {
		logger.info("Method : viewRoomListByRack starts");

		List<WirehouseRomeModel> roomList = new ArrayList<WirehouseRomeModel>();

		String value = "SET @P_Rack='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
					.setParameter("actionType", "getBinList").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				WirehouseRomeModel dropDownModel = new WirehouseRomeModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14]);
				roomList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : viewRoomListByRack ends");
		return roomList;
	}
	 
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<WirehouseRomeModel>> saveBinMaster(WirehouseRomeModel location) {
		logger.info("Method : saveBinMaster starts");
		System.out.println("B"+location);
		Boolean validity = true;
		JsonResponse<WirehouseRomeModel> resp = new JsonResponse<WirehouseRomeModel>();
		resp.setMessage("");
		resp.setCode("");
		
		List<WirehouseRomeModel> newLoc = new ArrayList<WirehouseRomeModel>();
		
		if (location.getRoomCode() == null || location.getRoomCode() == "") {
			resp.setMessage("Room Code Required");
			validity = false;
		} else if (location.getRoomName() == null || location.getRoomName() == "") {
			resp.setMessage("Room Name Required");
			validity = false;
		} else if (location.getRoomType() == null || location.getRoomType() == "") {
			resp.setMessage("Room Type Required");
			validity = false;
		} else if (location.getRackId() == null || location.getRackId() == "") {
			resp.setMessage("Section Id Required");
			validity = false;
		}
		
		if (validity)
			try {
				System.out.println("aB"+location);
				String values = GenerateWareHouseMasterParameter.saveBin(location);
				System.out.println("Bin"+values);
				if (location.getRoomId() != null && location.getRoomId() != "") {
					
					List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
							.setParameter("actionType", "modifyBin").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						

						WirehouseRomeModel item = new WirehouseRomeModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9],
								m[10], m[11], m[12], m[13], m[14]);
						newLoc.add(item);
					}
				} else {
					System.out.println("Bin$"+values);
					List<Object[]> x = em.createNamedStoredProcedureQuery("warehouseRoutine")
							.setParameter("actionType", "addBin").setParameter("actionValue", values).getResultList();
					for (Object[] m : x) {
						

						
						WirehouseRomeModel item = new WirehouseRomeModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9],
								m[10], m[11], m[12], m[13], m[14]);
						newLoc.add(item);
					}
					
				}
				System.out.println("Bin@"+values);
				resp.setBody(newLoc.get(0));
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
		
		ResponseEntity<JsonResponse<WirehouseRomeModel>> response = new ResponseEntity<JsonResponse<WirehouseRomeModel>>(
				resp, HttpStatus.CREATED);
		System.out.println("BinR"+response);
		logger.info("Method : saveBinMaster ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> deleteBinMaster(String id, String createdBy) {
		logger.info("Method : deleteBinMaster starts");
		
		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		
		if (validity)
			try {
				
				String value = "SET @P_ModifiedBy='" + createdBy + "', @P_Room='" + id + "';";
				em.createNamedStoredProcedureQuery("warehouseRoutine").setParameter("actionType", "deleteBin")
				.setParameter("actionValue", value).execute();
				
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
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		
		logger.info("Method : deleteBinMaster ends");
		return response;
	}
	
}
