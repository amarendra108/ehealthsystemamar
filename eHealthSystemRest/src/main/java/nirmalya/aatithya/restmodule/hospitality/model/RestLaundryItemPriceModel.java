package nirmalya.aatithya.restmodule.hospitality.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLaundryItemPriceModel {

	private String laundryItem;
	private String laundryItemName;
	private String lndryServiceID;
	private String lndryServiceName;
	private String hotelServiceID;
	private String hotelServiceName;
	private String userTypeID;
	private String userTypeName;
	private Float lItemPrice;
	private String lItemPriceStatus;
	private String effectiveDate;
	private String createdBy;
	private String createdDate;
	private Integer editID;

	private String oldItemId;
	private String oldLaundryService;
	private String oldHotelService;
	private String oldUserType;
	private String oldEffectiveDate;

	public Integer getEditID() {
		return editID;
	}

	public void setEditID(Integer editID) {
		this.editID = editID;
	}

	public RestLaundryItemPriceModel(Object laundryItem, Object laundryItemName, Object lndryServiceID,
			Object lndryServiceName, Object hotelServiceID, Object hotelServiceName, Object userTypeID,
			Object userTypeName, Object lItemPrice, Object lItemPriceStatus, Object effectiveDate, Object createdBy,
			Object createdDate) {
		super();
		this.laundryItem = (String) laundryItem;
		this.laundryItemName = (String) laundryItemName;
		this.lndryServiceID = (String) lndryServiceID;
		this.lndryServiceName = (String) lndryServiceName;
		this.hotelServiceID = (String) hotelServiceID;
		this.hotelServiceName = (String) hotelServiceName;
		this.userTypeID = (String) userTypeID;
		this.userTypeName = (String) userTypeName;
		this.lItemPrice = (Float) lItemPrice;
		this.lItemPriceStatus = (String) lItemPriceStatus;
		this.effectiveDate = (String) effectiveDate;
		this.createdBy = (String) createdBy;
		this.createdDate = (String) createdDate;
	}

	public RestLaundryItemPriceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLaundryItem() {
		return laundryItem;
	}

	public void setLaundryItem(String laundryItem) {
		this.laundryItem = laundryItem;
	}

	public String getLaundryItemName() {
		return laundryItemName;
	}

	public void setLaundryItemName(String laundryItemName) {
		this.laundryItemName = laundryItemName;
	}

	public String getLndryServiceID() {
		return lndryServiceID;
	}

	public void setLndryServiceID(String lndryServiceID) {
		this.lndryServiceID = lndryServiceID;
	}

	public String getLndryServiceName() {
		return lndryServiceName;
	}

	public void setLndryServiceName(String lndryServiceName) {
		this.lndryServiceName = lndryServiceName;
	}

	public String getHotelServiceID() {
		return hotelServiceID;
	}

	public void setHotelServiceID(String hotelServiceID) {
		this.hotelServiceID = hotelServiceID;
	}

	public String getHotelServiceName() {
		return hotelServiceName;
	}

	public void setHotelServiceName(String hotelServiceName) {
		this.hotelServiceName = hotelServiceName;
	}

	public String getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(String userTypeID) {
		this.userTypeID = userTypeID;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public Float getlItemPrice() {
		return lItemPrice;
	}

	public void setlItemPrice(Float lItemPrice) {
		this.lItemPrice = lItemPrice;
	}

	public String getlItemPriceStatus() {
		return lItemPriceStatus;
	}

	public void setlItemPriceStatus(String lItemPriceStatus) {
		this.lItemPriceStatus = lItemPriceStatus;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	public String getOldItemId() {
		return oldItemId;
	}

	public void setOldItemId(String oldItemId) {
		this.oldItemId = oldItemId;
	}

	public String getOldLaundryService() {
		return oldLaundryService;
	}

	public void setOldLaundryService(String oldLaundryService) {
		this.oldLaundryService = oldLaundryService;
	}

	public String getOldHotelService() {
		return oldHotelService;
	}

	public void setOldHotelService(String oldHotelService) {
		this.oldHotelService = oldHotelService;
	}

	public String getOldUserType() {
		return oldUserType;
	}

	public void setOldUserType(String oldUserType) {
		this.oldUserType = oldUserType;
	}

	public String getOldEffectiveDate() {
		return oldEffectiveDate;
	}

	public void setOldEffectiveDate(String oldEffectiveDate) {
		this.oldEffectiveDate = oldEffectiveDate;
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
