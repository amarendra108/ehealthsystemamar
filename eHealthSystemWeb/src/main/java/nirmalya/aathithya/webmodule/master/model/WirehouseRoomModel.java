package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WirehouseRoomModel {
	
	private String warehouseId;
	private String zoneId;
	private String rackId;
	private String roomId;
	private String zoneCode;
	private String floorCode;
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
	private List<WirehouseRoomModel> roomList = new ArrayList<WirehouseRoomModel>();
	private Integer roomCount;
	private Integer floorCount;
	
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

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
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

	public List<WirehouseRoomModel> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<WirehouseRoomModel> roomList) {
		this.roomList = roomList;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Integer getFloorCount() {
		return floorCount;
	}

	public void setFloorCount(Integer floorCount) {
		this.floorCount = floorCount;
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
