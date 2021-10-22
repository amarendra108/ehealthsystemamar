package nirmalya.aathithya.webmodule.master.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.LocationMasterModel;
import nirmalya.aathithya.webmodule.master.model.LocationRoomModel;
import nirmalya.aathithya.webmodule.master.model.LocationSectionModel;
import nirmalya.aathithya.webmodule.master.model.WirehouseRoomModel;
import nirmalya.aathithya.webmodule.master.model.ZoneMasterModel;
import nirmalya.aathithya.webmodule.master.model.ZoneRackModel;

/**
 * @author NirmalyaLabs
 *
 */
@Controller
@RequestMapping(value = { "master/" })
public class WarehouseController {
	Logger logger = LoggerFactory.getLogger(WarehouseController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;
	@SuppressWarnings("unused")
	@GetMapping(value = { "manage-warehouse" })
	public String manageWareHouse(Model model, HttpSession session) {
		logger.info("Method : manageWareHouse starts");
		
		try {
			DropDownModel[] location = restClient.getForObject(env.getMasterUrl() + "getWarehouseLocationList", DropDownModel[].class);
			List<DropDownModel> locationList = Arrays.asList(location);
			
			model.addAttribute("locationList", locationList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		
		
	/*	try {
			LocationMasterModel[] location = restClient.getForObject(env.getMasterUrl() + "getLocationList", LocationMasterModel[].class);
			List<LocationMasterModel> locationList = Arrays.asList(location);
			
			int count = 0;
			
			for(LocationMasterModel m : locationList) {
				System.out.println(m.getLocationType());
				
				if(m.getLocationType().equals("Warehouse")) {
					//System.out.println("warehouse");
					DropDownModel dropDownModel = new DropDownModel(m.getLocationId(), m.getLocationName());
					List<DropDownModel> warehouseList = Arrays.asList(dropDownModel);
					model.addAttribute("warehouseList",warehouseList);
					model.addAttribute("locationId",m.getLocationId());
					model.addAttribute("locationCode",m.getLocationCode());
					System.out.println(warehouseList);
				}
		
			}
		
			model.addAttribute("count", count);
			System.out.println(locationList);
			model.addAttribute("locationList", locationList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}*/

		logger.info("Method : manageWareHouse ends");
		return "master/managewarehouse";
	}
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "manage-warehouse-get-location" })
	public @ResponseBody JsonResponse<Object> getlocationDetailAgainstId(Model model, @RequestBody String warehouseId,
			BindingResult result) {
		logger.info("Method : getlocationDetailAgainstId starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restClient.getForObject(env.getMasterUrl() + "rest-get-locationDeatils?id=" + warehouseId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getlocationDetailAgainstId ends");
		return res;

	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-zone-save")
	public @ResponseBody JsonResponse<Object> saveZoneMaster(@RequestBody ZoneMasterModel zoneMasterModel, HttpSession session) {
		logger.info("Method : saveZoneMaster starts");
		System.out.println("add"+zoneMasterModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		zoneMasterModel.setCreatedBy(userId);
		
		
		
		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveZoneMaster", zoneMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			
			resp.setMessage("Success");
		}
		System.out.println("adddddd"+resp);
		logger.info("Method : saveLocationMaster starts");
		return resp;
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-save-rack")
	public @ResponseBody JsonResponse<Object> saveRackMaster(@RequestBody ZoneRackModel zoneRackModel, HttpSession session) {
		logger.info("Method : saveRackMaster starts");
		System.out.println("saveRackMaster"+zoneRackModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		zoneRackModel.setCreatedBy(userId);
		
		
		
		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveRackMaster", zoneRackModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}
		System.out.println("addrack"+resp);
		logger.info("Method : saveRackMaster starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "manage-warehouse-get-warehouse-zone-details" })
	public @ResponseBody JsonResponse<List<ZoneMasterModel>> getWarehouseZoneDetailAgainstId(Model model, @RequestBody String id,
			BindingResult result) {
		System.out.println("getWarehouseZoneDetailAgainstId"+id); 
		logger.info("Method : getWarehouseZoneDetailAgainstId starts");
		
		
		JsonResponse<List<ZoneMasterModel>> res = new JsonResponse<List<ZoneMasterModel>>();
		
		try {
			res = restClient.getForObject(env.getMasterUrl() + "getZoneDetails?id=" + id,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			
			List<ZoneMasterModel> zoneDetails = mapper.convertValue(res.getBody(),
					new TypeReference<List<ZoneMasterModel>>() {
			});
			
			for(ZoneMasterModel m : zoneDetails) {
				try {
					ZoneRackModel[] section = restClient.getForObject(env.getMasterUrl() + "viewRackListByZone?id="+m.getZoneId(), ZoneRackModel[].class);
					List<ZoneRackModel> sectionList = Arrays.asList(section);
					
					m.setSectionList(sectionList);
				} catch (RestClientException e) {
					e.printStackTrace();
				}
				
			}
			
			res.setBody(zoneDetails);
			System.out.println("Rack"+zoneDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		System.out.println("view"+res);
		logger.info("Method : getWarehouseZoneDetailAgainstId ends");
		return res;

	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-zone-edit")
	public @ResponseBody JsonResponse<Object> editZoneMaster(@RequestBody String zone, HttpSession session) {
		logger.info("Method : editZoneMaster starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "editZoneMaster?id="+zone,
					JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("success");
		}
		
		logger.info("Method : editZoneMaster starts");
		return resp;
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-rack-edit")
	public @ResponseBody JsonResponse<Object> editRackMaster(@RequestBody String zone, HttpSession session) {
		logger.info("Method : editRackMaster starts");
		System.out.println("edit"+zone);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "editRackMaster?id="+zone,
					JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("success");
		}
		System.out.println("editweb"+resp);
		logger.info("Method : editRackMaster starts");
		return resp;
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-zone-delete")
	public @ResponseBody JsonResponse<Object> deleteZoneMaster(@RequestBody String zone, HttpSession session) {
		logger.info("Method : deleteZoneMaster starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "deleteZoneMaster?id="+zone+"&createdBy="+userId,
					JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("success");
		}
		
		logger.info("Method : deleteZoneMaster starts");
		return resp;
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-rack-delete")
	public @ResponseBody JsonResponse<Object> deleteRackMaster(@RequestBody String zone, HttpSession session) {
		logger.info("Method : deleteZoneMaster starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "deleteRackMaster?id="+zone+"&createdBy="+userId,
					JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("success");
		}
		
		logger.info("Method : deleteRackMaster starts");
		return resp;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@PostMapping(value = { "manage-warehouse-get-Wh-room-details" })
	public @ResponseBody JsonResponse<List<WirehouseRoomModel>> getwarehouseRoomDetails(Model model, @RequestBody List<String> tCountry,
			BindingResult result) {
		logger.info("Method : getwarehouseRoomDetails starts");
		
		JsonResponse<List<WirehouseRoomModel>> res = new JsonResponse<List<WirehouseRoomModel>>();
		
		try {
			res = restClient.postForObject(env.getMasterUrl() + "getwarehouseRoomDetails", tCountry,
					JsonResponse.class);
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<WirehouseRoomModel> locDetails = mapper.convertValue(res.getBody(),
					new TypeReference<List<WirehouseRoomModel>>() {
			});
			
			List<DropDownModel> roomCountList = new ArrayList<DropDownModel>();
			
			try {
				DropDownModel[] roomCount = restClient.postForObject(env.getMasterUrl() + "countZoneWiseRoom",tCountry, DropDownModel[].class);
				roomCountList = Arrays.asList(roomCount);
						} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(tCountry.size() > 0) {
				for(int i = 0; i < locDetails.size(); i++) {
					try {
						WirehouseRoomModel[] room = restClient.getForObject(env.getMasterUrl() + "viewRoomListByRack?id="+locDetails.get(i).getRackId(), WirehouseRoomModel[].class);
						List<WirehouseRoomModel> roomList = Arrays.asList(room);
						
						locDetails.get(i).setRoomList(roomList);
						int c  = 0;
						for(WirehouseRoomModel a : roomList) {
							c = c + 1;
						}
						locDetails.get(i).setRoomCount(c);
					} catch (RestClientException e) {
						e.printStackTrace();
					}
					
				}
				
				for(DropDownModel m : roomCountList) {
					for(WirehouseRoomModel a : locDetails) {
						if(m.getKey().contains(a.getZoneId())) {
							a.setFloorCount(Integer.parseInt(m.getName()));
						}
					}
				}
			}
			
			res.setBody(locDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		System.out.println("room"+res);
		logger.info("Method : getwarehouseRoomDetails ends");
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-save-room")
	public @ResponseBody JsonResponse<Object> saveBinMaster(@RequestBody WirehouseRoomModel location, HttpSession session) {
		logger.info("Method : saveBinMaster starts");
		System.out.println("addddB"+location);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		location.setCreatedBy(userId);
		
		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveBinMaster", location,
					JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("Success");
		}
		System.out.println("roomadd"+resp);
		logger.info("Method : saveBinMaster starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/manage-warehouse-room-delete")
	public @ResponseBody JsonResponse<Object> deleteBinMaster(@RequestBody String floor, HttpSession session) {
		logger.info("Method : deleteBinMaster starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "deleteBinMaster?id="+floor+"&createdBy="+userId,
					JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("success");
		}
		
		logger.info("Method : deleteBinMaster starts");
		return resp;
	}
	
}
