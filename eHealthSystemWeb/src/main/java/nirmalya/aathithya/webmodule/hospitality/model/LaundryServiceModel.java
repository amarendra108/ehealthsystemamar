package nirmalya.aathithya.webmodule.hospitality.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LaundryServiceModel {

	private String laundryServiceID;
	private String laundryServiceName;
	private String laundryImage;
	private String laundryDesc;
	private String laundryServiceStatus;
	private String createdBy;
	private String createdDate;
	
	
	public String getLaundryServiceID() {
		return laundryServiceID;
	}


	public void setLaundryServiceID(String laundryServiceID) {
		this.laundryServiceID = laundryServiceID;
	}


	public String getLaundryServiceName() {
		return laundryServiceName;
	}


	public void setLaundryServiceName(String laundryServiceName) {
		this.laundryServiceName = laundryServiceName;
	}

	public String getLaundryImage() {
		return laundryImage;
	}


	public void setLaundryImage(String laundryImage) {
		this.laundryImage = laundryImage;
	}


	public String getLaundryDesc() {
		return laundryDesc;
	}


	public void setLaundryDesc(String laundryDesc) {
		this.laundryDesc = laundryDesc;
	}


	public String getLaundryServiceStatus() {
		return laundryServiceStatus;
	}


	public void setLaundryServiceStatus(String laundryServiceStatus) {
		this.laundryServiceStatus = laundryServiceStatus;
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
