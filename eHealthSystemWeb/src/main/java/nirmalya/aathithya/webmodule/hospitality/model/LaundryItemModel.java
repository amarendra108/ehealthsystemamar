package nirmalya.aathithya.webmodule.hospitality.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LaundryItemModel {

	private String lItemID;
	private String lItemName;
	private String lItemCategoryName;
	private String itemGroupType;
	private String purchaseSubGroup;
	private String salesSubGroup;
	private String lItemDesc;
	private String lItemimage;
	private String lItemStatus;
	private String createdBy;
	private String createdDate;
	private String lItemCategoryID;
	
	
	
	public String getlItemCategoryID() {
		return lItemCategoryID;
	}



	public void setlItemCategoryID(String lItemCategoryID) {
		this.lItemCategoryID = lItemCategoryID;
	}



	public String getlItemID() {
		return lItemID;
	}



	public void setlItemID(String lItemID) {
		this.lItemID = lItemID;
	}



	public String getlItemName() {
		return lItemName;
	}



	public void setlItemName(String lItemName) {
		this.lItemName = lItemName;
	}



	public String getlItemCategoryName() {
		return lItemCategoryName;
	}



	public void setlItemCategoryName(String lItemCategoryName) {
		this.lItemCategoryName = lItemCategoryName;
	}



	public String getItemGroupType() {
		return itemGroupType;
	}



	public void setItemGroupType(String itemGroupType) {
		this.itemGroupType = itemGroupType;
	}



	public String getPurchaseSubGroup() {
		return purchaseSubGroup;
	}



	public void setPurchaseSubGroup(String purchaseSubGroup) {
		this.purchaseSubGroup = purchaseSubGroup;
	}



	public String getSalesSubGroup() {
		return salesSubGroup;
	}



	public void setSalesSubGroup(String salesSubGroup) {
		this.salesSubGroup = salesSubGroup;
	}

	public String getlItemDesc() {
		return lItemDesc;
	}



	public void setlItemDesc(String lItemDesc) {
		this.lItemDesc = lItemDesc;
	}



	public String getlItemimage() {
		return lItemimage;
	}



	public void setlItemimage(String lItemimage) {
		this.lItemimage = lItemimage;
	}



	public String getlItemStatus() {
		return lItemStatus;
	}



	public void setlItemStatus(String lItemStatus) {
		this.lItemStatus = lItemStatus;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public String getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
