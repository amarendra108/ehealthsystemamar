package nirmalya.aatithya.restmodule.hospitality.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLaundryReturnInModel {
	private String returnInId;
	private String returnInName;
	private String returndesc;
	private String returnInStatus;
	private String createdBy;
	private String createdDate;
	
	
	
	public RestLaundryReturnInModel(Object returnInId, Object returnInName, Object returndesc, Object returnInStatus,
			Object createdBy, Object createdDate) {
		super();
		this.returnInId =(String) returnInId;
		this.returnInName =(String) returnInName;
		this.returndesc =(String) returndesc;
		this.returnInStatus =(String) returnInStatus;
		this.createdBy =(String) createdBy;
		this.createdDate =(String) createdDate;
	}



	public RestLaundryReturnInModel() {
		// TODO Auto-generated constructor stub
	}



	public String getReturnInId() {
		return returnInId;
	}



	public void setReturnInId(String returnInId) {
		this.returnInId = returnInId;
	}



	public String getReturnInName() {
		return returnInName;
	}



	public void setReturnInName(String returnInName) {
		this.returnInName = returnInName;
	}



	public String getReturndesc() {
		return returndesc;
	}



	public void setReturndesc(String returndesc) {
		this.returndesc = returndesc;
	}



	public String getReturnInStatus() {
		return returnInStatus;
	}



	public void setReturnInStatus(String returnInStatus) {
		this.returnInStatus = returnInStatus;
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
