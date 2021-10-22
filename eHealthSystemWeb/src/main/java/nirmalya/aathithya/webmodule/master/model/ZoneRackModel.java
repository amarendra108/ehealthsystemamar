package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ZoneRackModel {
	private String warehouseId;
	private String zoneId;
	private String rackId;
	private String rackCode;
	private String rackName;
	private Integer ZoneSlNo;
	private Integer rackSlNo;
	private String createdBy;
	private String zoneCode;
	private BigInteger sectionCount;
	
	public ZoneRackModel() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public Integer getZoneSlNo() {
		return ZoneSlNo;
	}
	public void setZoneSlNo(Integer zoneSlNo) {
		ZoneSlNo = zoneSlNo;
	}
	
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public BigInteger getSectionCount() {
		return sectionCount;
	}
	public void setSectionCount(BigInteger sectionCount) {
		this.sectionCount = sectionCount;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getRackCode() {
		return rackCode;
	}
	public void setRackCode(String rackCode) {
		this.rackCode = rackCode;
	}
	public String getRackName() {
		return rackName;
	}
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}
	public Integer getRackSlNo() {
		return rackSlNo;
	}
	public void setRackSlNo(Integer rackSlNo) {
		this.rackSlNo = rackSlNo;
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
