package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.master.model.LocationRoomModel;
import nirmalya.aatithya.restmodule.master.model.WirehouseRomeModel;
import nirmalya.aatithya.restmodule.master.model.ZoneMasterModel;
import nirmalya.aatithya.restmodule.master.model.ZoneRackModel;

public class GenerateWareHouseMasterParameter {
	public static String saveZoneMaster(ZoneMasterModel zoneMasterModel) {

		String s = "";

		if (zoneMasterModel.getZoneId() != null && zoneMasterModel.getZoneId() != "") {
			s = s + "@p_zoneId='" + zoneMasterModel.getZoneId() + "',";
		}
		if (zoneMasterModel.getWarehouseId() != null && zoneMasterModel.getWarehouseId() != "") {
			s = s + "@p_warehouseId='" + zoneMasterModel.getWarehouseId() + "',";
		}
		if (zoneMasterModel.getZoneName() != null && zoneMasterModel.getZoneName() != "") {
			s = s + "@p_zoneName='" + zoneMasterModel.getZoneName() + "',";
		}
		if (zoneMasterModel.getZoneCode() != null && zoneMasterModel.getZoneCode() != "") {
			s = s + "@p_zoneCode='" + zoneMasterModel.getZoneCode() + "',";
		}
		
		if (zoneMasterModel.getCreatedBy() != null && zoneMasterModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + zoneMasterModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println("zoneG"+s);

		return s;
	}

	public static String saveRackMaster(ZoneRackModel zoneRackModel) {

		String s = "";

		
		if (zoneRackModel.getWarehouseId() != null && zoneRackModel.getWarehouseId() != "") {
			s = s + "@p_warehouseId='" + zoneRackModel.getWarehouseId() + "',";
		}
		if (zoneRackModel.getZoneId() != null && zoneRackModel.getZoneId() != "") {
			s = s + "@p_zoneId='" + zoneRackModel.getZoneId() + "',";
		}
		if (zoneRackModel.getRackId() != null && zoneRackModel.getRackId() != "") {
			s = s + "@p_rackId='" + zoneRackModel.getRackId() + "',";
		}
		if (zoneRackModel.getRackCode()!= null && zoneRackModel.getRackCode() != "") {
			s = s + "@p_rackCode='" + zoneRackModel.getRackCode() + "',";
		}
		if (zoneRackModel.getRackName() != null && zoneRackModel.getRackName() != "") {
			s = s + "@p_rackName='" + zoneRackModel.getRackName() + "',";
		}
		if (zoneRackModel.getCreatedBy() != null && zoneRackModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + zoneRackModel.getCreatedBy() + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println("rackG"+s);

		return s;
	}

	public static String getRackIdList(List<String> id) {

		String s = "";
		String section = "";
		
		if(id.size() > 0) {
			for(String m : id) {
				section = section + "\"" + m + "\",";
			}
			
			section = section.substring(0, section.length() - 1);
		}
		
		s = "(" + section + ")";
		
		s = "SET @p_sectionListSubQuery='" + s + "';";
		
		System.out.println(s);
		
		return s;
	}
	
public static String saveBin(WirehouseRomeModel location) {
		
		String s = "";
		
		if(location.getRackId()!=null && location.getRackId()!="") {
			s = s + "@p_rackId='" + location.getRackId() + "',";
		}
		if(location.getRoomId()!=null && location.getRoomId()!="") {
			s = s + "@p_roomId='" + location.getRoomId() + "',";
		}
		if(location.getRoomCode()!=null && location.getRoomCode()!="") {
			s = s + "@p_roomCode='" + location.getRoomCode() + "',";
		}
		if(location.getRoomName()!=null && location.getRoomName()!="") {
			s = s + "@p_roomName='" + location.getRoomName() + "',";
		}
		if(location.getRoomType()!=null && location.getRoomType()!="") {
			s = s + "@p_roomType='" + location.getRoomType() + "',";
		}
		if(location.getCreatedBy()!=null && location.getCreatedBy()!="") {
			s = s + "@p_createdBy='" + location.getCreatedBy() + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println("BIN"+s);
		
		return s;
		
	}

}
