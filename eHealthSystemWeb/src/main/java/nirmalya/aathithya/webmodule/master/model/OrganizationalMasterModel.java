package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrganizationalMasterModel {

	private String organizationalId;
	private String organizationalName;
	private String organizationalDesc;
	private String organizationalStatus;
	private String parentId;
	private String createdBy;
	private String catLevel;
	private String parentName;
	private BigInteger nodeCount;

	public String getOrganizationalId() {
		return organizationalId;
	}

	public void setOrganizationalId(String organizationalId) {
		this.organizationalId = organizationalId;
	}

	public String getOrganizationalName() {
		return organizationalName;
	}

	public void setOrganizationalName(String organizationalName) {
		this.organizationalName = organizationalName;
	}

	public String getOrganizationalDesc() {
		return organizationalDesc;
	}

	public void setOrganizationalDesc(String organizationalDesc) {
		this.organizationalDesc = organizationalDesc;
	}

	public String getOrganizationalStatus() {
		return organizationalStatus;
	}

	public void setOrganizationalStatus(String organizationalStatus) {
		this.organizationalStatus = organizationalStatus;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCatLevel() {
		return catLevel;
	}

	public void setCatLevel(String catLevel) {
		this.catLevel = catLevel;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public BigInteger getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(BigInteger nodeCount) {
		this.nodeCount = nodeCount;
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
