package nirmalya.aatithya.restmodule.master.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WirehouseRomeModel {
	private String warehouseId;
	private String zoneId;
	private String rackId;
	private String roomId;
	private String zoneCode;
	private String rackCode;
	private String roomCode;
	private String zoneName;
	private String rackName;
	private String roomName;
	private Integer zoneSlNo;
	private Integer rackSlNo;
	private Integer roomSlNO;
	private String roomType;
	private String createdBy;
	
	
	public WirehouseRomeModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public WirehouseRomeModel(Object warehouseId, Object zoneId, Object rackId, Object roomId, Object zoneCode,
			 Object rackCode, Object roomCode, Object zoneName, Object rackName, Object roomName,
			Object zoneSlNo, Object rackSlNo, Object roomSlNO, Object roomType, Object createdBy) {
		super();
		this.warehouseId =(String) warehouseId;
		this.zoneId =(String) zoneId;
		this.rackId =(String) rackId;
		this.roomId =(String) roomId;
		this.zoneCode =(String) zoneCode;
	
		this.rackCode =(String) rackCode;
		this.roomCode =(String) roomCode;
		this.zoneName = (String)zoneName;
		this.rackName =(String) rackName;
		this.roomName = (String)roomName;
		this.zoneSlNo =(Integer) zoneSlNo;
		this.rackSlNo =(Integer) rackSlNo;
		this.roomSlNO =(Integer) roomSlNO;
		this.roomType =(String) roomType;
		this.createdBy = (String)createdBy;
	}


	public String getWarehouseId() {
		return warehouseId;
	}


	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}


	public String getZoneId() {
		return zoneId;
	}


	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}


	public String getRackId() {
		return rackId;
	}


	public void setRackId(String rackId) {
		this.rackId = rackId;
	}


	public String getRoomId() {
		return roomId;
	}


	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}


	public String getZoneCode() {
		return zoneCode;
	}


	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}



	public String getRackCode() {
		return rackCode;
	}


	public void setRackCode(String rackCode) {
		this.rackCode = rackCode;
	}


	public String getRoomCode() {
		return roomCode;
	}


	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}


	public String getZoneName() {
		return zoneName;
	}


	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}


	public String getRackName() {
		return rackName;
	}


	public void setRackName(String rackName) {
		this.rackName = rackName;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public Integer getZoneSlNo() {
		return zoneSlNo;
	}


	public void setZoneSlNo(Integer zoneSlNo) {
		this.zoneSlNo = zoneSlNo;
	}


	public Integer getRackSlNo() {
		return rackSlNo;
	}


	public void setRackSlNo(Integer rackSlNo) {
		this.rackSlNo = rackSlNo;
	}


	public Integer getRoomSlNO() {
		return roomSlNO;
	}


	public void setRoomSlNO(Integer roomSlNO) {
		this.roomSlNO = roomSlNO;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}
}
