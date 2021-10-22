package nirmalya.aatithya.restmodule.hospitality.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestHotelServiceModel {

	private String hotelID;
	private String hotelName;
	private Float extraCharge;
	private String hotelDesc;
	private String hotelStatus;
	private String createdBy;
	private String createdDate;
	
	
	
	public RestHotelServiceModel(Object hotelID, Object hotelName, Object extraCharge, Object hotelDesc, Object hotelStatus,
			Object createdBy, Object createdDate) {
		super();
		this.hotelID =(String) hotelID;
		this.hotelName =(String) hotelName;
		this.extraCharge =(Float) extraCharge;
		this.hotelDesc =(String) hotelDesc;
		this.hotelStatus =(String) hotelStatus;
		this.createdBy =(String) createdBy;
		this.createdDate =(String) createdDate;
	}



	public RestHotelServiceModel() {
		// TODO Auto-generated constructor stub
	}



	public String getHotelID() {
		return hotelID;
	}



	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}



	public String getHotelName() {
		return hotelName;
	}



	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}




	public Float getExtraCharge() {
		return extraCharge;
	}



	public void setExtraCharge(Float extraCharge) {
		this.extraCharge = extraCharge;
	}

	public String getHotelDesc() {
		return hotelDesc;
	}



	public void setHotelDesc(String hotelDesc) {
		this.hotelDesc = hotelDesc;
	}



	public String getHotelStatus() {
		return hotelStatus;
	}



	public void setHotelStatus(String hotelStatus) {
		this.hotelStatus = hotelStatus;
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
