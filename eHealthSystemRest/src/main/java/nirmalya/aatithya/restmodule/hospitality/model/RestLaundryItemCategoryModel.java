package nirmalya.aatithya.restmodule.hospitality.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLaundryItemCategoryModel {

	private String itemCategoryID;
	private String itemCategoryName;
	private String desc;
	private String itemCategoryStatus;
	private String createdBy;
	private String createdDate;
	
	
	
	public RestLaundryItemCategoryModel(Object itemCategoryID, Object itemCategoryName, Object desc,
			Object itemCategoryStatus, Object createdBy, Object createdDate) {
		super();
		this.itemCategoryID =(String) itemCategoryID;
		this.itemCategoryName =(String) itemCategoryName;
		this.desc =(String) desc;
		this.itemCategoryStatus =(String) itemCategoryStatus;
		this.createdBy =(String) createdBy;
		this.createdDate =(String) createdDate;
	}



	public RestLaundryItemCategoryModel() {
		// TODO Auto-generated constructor stub
	}



	public String getItemCategoryID() {
		return itemCategoryID;
	}



	public void setItemCategoryID(String itemCategoryID) {
		this.itemCategoryID = itemCategoryID;
	}



	public String getItemCategoryName() {
		return itemCategoryName;
	}



	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public String getItemCategoryStatus() {
		return itemCategoryStatus;
	}



	public void setItemCategoryStatus(String itemCategoryStatus) {
		this.itemCategoryStatus = itemCategoryStatus;
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
