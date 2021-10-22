package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ZoneMasterModel {
private String zoneId;
private String warehouseId;
private String zoneCode;
private String zoneName;
private String createdBy;
private Integer zoneSlNo;
private List<ZoneRackModel> sectionList = new ArrayList<ZoneRackModel>();
private BigInteger locCount;
private BigInteger floorCount;

public BigInteger getLocCount() {
	return locCount;
}
public void setLocCount(BigInteger locCount) {
	this.locCount = locCount;
}
public BigInteger getFloorCount() {
	return floorCount;
}
public void setFloorCount(BigInteger floorCount) {
	this.floorCount = floorCount;
}
public ZoneMasterModel() {
	super();
	// TODO Auto-generated constructor stub
}
public String getZoneId() {
	return zoneId;
}

public String getZoneCode() {
	return zoneCode;
}

public String getWarehouseId() {
	return warehouseId;
}
public void setWarehouseId(String warehouseId) {
	this.warehouseId = warehouseId;
}
public void setZoneCode(String zoneCode) {
	this.zoneCode = zoneCode;
}
public void setZoneId(String zoneId) {
	this.zoneId = zoneId;
}
public String getZoneName() {
	return zoneName;
}
public void setZoneName(String zoneName) {
	this.zoneName = zoneName;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public Integer getZoneSlNo() {
	return zoneSlNo;
}
public void setZoneSlNo(Integer zoneSlNo) {
	this.zoneSlNo = zoneSlNo;
}
public List<ZoneRackModel> getSectionList() {
	return sectionList;
}
public void setSectionList(List<ZoneRackModel> sectionList) {
	this.sectionList = sectionList;
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
