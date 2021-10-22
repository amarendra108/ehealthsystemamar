package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.hospitality.model.RestHotelServiceModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemCategoryModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemPriceModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryReturnInModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryServiceModel;

public class GenerateLaundryMasterParamter {

	public static String getLItemCategory(RestLaundryItemCategoryModel lmModel) {
		// TODO Auto-generated method stub
		String s = "";

		if (lmModel.getItemCategoryID() != null && lmModel.getItemCategoryID() != "") {
			s = s + "@p_itemCatId='" + lmModel.getItemCategoryID() + "',";
		}

		if (lmModel.getItemCategoryName() != null && lmModel.getItemCategoryName() != "") {
			s = s + "@p_itemCaName='" + lmModel.getItemCategoryName() + "',";
		}

		if (lmModel.getDesc() != null && lmModel.getDesc() != "") {
			s = s + "@p_desc='" + lmModel.getDesc() + "',";
		}
		if (lmModel.getItemCategoryStatus() != null && lmModel.getItemCategoryStatus() != "") {
			s = s + "@p_isActive=" + lmModel.getItemCategoryStatus() + ",";
		} else {
			s = s + "@p_isActive=" + 0 + ",";
		}
		if (lmModel.getCreatedBy() != null && lmModel.getCreatedBy() != "") {
			s = s + "@p_CreatedBy='" + lmModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}

	public static String getHotelSerice(RestHotelServiceModel hotelModel) {
		// TODO Auto-generated method stub
		String s = "";

		if (hotelModel.getHotelID() != null && hotelModel.getHotelID() != "") {
			s = s + "@p_hotelId='" + hotelModel.getHotelID() + "',";
		}

		if (hotelModel.getHotelName() != null && hotelModel.getHotelName() != "") {
			s = s + "@p_serviceName='" + hotelModel.getHotelName() + "',";
		}

		if (hotelModel.getExtraCharge() != null) {
			s = s + "@p_extraCharge='" + hotelModel.getExtraCharge() + "',";
		}

		if (hotelModel.getHotelDesc() != null && hotelModel.getHotelDesc() != "") {
			s = s + "@p_desc='" + hotelModel.getHotelDesc() + "',";
		}
		if (hotelModel.getHotelStatus() != null && hotelModel.getHotelStatus() != "") {
			s = s + "@p_isActive=" + hotelModel.getHotelStatus() + ",";
		} else {
			s = s + "@p_isActive=" + 0 + ",";
		}
		if (hotelModel.getCreatedBy() != null && hotelModel.getCreatedBy() != "") {
			s = s + "@p_CreatedBy='" + hotelModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}

	public static String getReturnIn(RestLaundryReturnInModel returnModel) {
		// TODO Auto-generated method stub
		String s = "";

		if (returnModel.getReturnInId() != null && returnModel.getReturnInId() != "") {
			s = s + "@p_returnId='" + returnModel.getReturnInId() + "',";
		}

		if (returnModel.getReturnInName() != null && returnModel.getReturnInName() != "") {
			s = s + "@p_returnName='" + returnModel.getReturnInName() + "',";
		}

		if (returnModel.getReturndesc() != null && returnModel.getReturndesc() != "") {
			s = s + "@p_desc='" + returnModel.getReturndesc() + "',";
		}
		if (returnModel.getReturnInStatus() != null && returnModel.getReturnInStatus() != "") {
			s = s + "@p_isActive=" + returnModel.getReturnInStatus() + ",";
		} else {
			s = s + "@p_isActive=" + 0 + ",";
		}
		if (returnModel.getCreatedBy() != null && returnModel.getCreatedBy() != "") {
			s = s + "@p_CreatedBy='" + returnModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}

	public static String getLaundryServicess(RestLaundryServiceModel laundryModel) {
		// TODO Auto-generated method stub
		String s = "";

		if (laundryModel.getLaundryServiceID() != null && laundryModel.getLaundryServiceID() != "") {
			s = s + "@p_laundryServ='" + laundryModel.getLaundryServiceID() + "',";
		}

		if (laundryModel.getLaundryServiceName() != null && laundryModel.getLaundryServiceName() != "") {
			s = s + "@p_ladryServName='" + laundryModel.getLaundryServiceName() + "',";
		}

		if (laundryModel.getLaundryDesc() != null && laundryModel.getLaundryDesc() != "") {
			s = s + "@p_ladryServDesc='" + laundryModel.getLaundryDesc() + "',";
		}

		if (laundryModel.getLaundryImage() != null && laundryModel.getLaundryImage() != "") {
			s = s + "@p_ladryServImage='" + laundryModel.getLaundryImage() + "',";
		}

		if (laundryModel.getLaundryServiceStatus() != null && laundryModel.getLaundryServiceStatus() != "") {
			s = s + "@p_ladryServAct=" + laundryModel.getLaundryServiceStatus() + ",";
		} else {
			s = s + "@p_ladryServAct=" + 0 + ",";
		}
		if (laundryModel.getCreatedBy() != null && laundryModel.getCreatedBy() != "") {
			s = s + "@p_CreatedBy='" + laundryModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}

	public static String getLItem(RestLaundryItemModel laundryModel) {
		// TODO Auto-generated method stub
		String s = "";

		if (laundryModel.getlItemID() != null && laundryModel.getlItemID() != "") {
			s = s + "@p_laundryItems='" + laundryModel.getlItemID() + "',";
		}

		if (laundryModel.getlItemName() != null && laundryModel.getlItemName() != "") {
			s = s + "@p_lItemNames='" + laundryModel.getlItemName() + "',";
		}
		if (laundryModel.getlItemCategoryName() != null && laundryModel.getlItemCategoryName() != "") {
			s = s + "@p_lndryItmsCategory='" + laundryModel.getlItemCategoryName() + "',";
		}
		if (laundryModel.getItemGroupType() != null && laundryModel.getItemGroupType() != "") {
			s = s + "@p_accGrpTypes='" + laundryModel.getItemGroupType() + "',";
		}
		if (laundryModel.getlItemDesc() != null && laundryModel.getlItemDesc() != "") {
			s = s + "@p_lItemsDescription='" + laundryModel.getlItemDesc() + "',";
		}

		if (laundryModel.getPurchaseSubGroup() != null && laundryModel.getPurchaseSubGroup() != "") {
			s = s + "@p_purchaseSubGroups='" + laundryModel.getPurchaseSubGroup() + "',";
		}
		if (laundryModel.getSalesSubGroup() != null && laundryModel.getSalesSubGroup() != "") {
			s = s + "@p_salesSubGroups='" + laundryModel.getSalesSubGroup() + "',";
		}

		if (laundryModel.getlItemimage() != null && laundryModel.getlItemimage() != "") {
			s = s + "@p_lItemImages='" + laundryModel.getlItemimage() + "',";
		}
		if (laundryModel.getlItemStatus() != null && laundryModel.getlItemStatus() != "") {
			s = s + "@p_ItemActives=" + laundryModel.getlItemStatus() + ",";
		} else {
			s = s + "@p_ItemActives=" + 0 + ",";
		}
		if (laundryModel.getCreatedBy() != null && laundryModel.getCreatedBy() != "") {
			s = s + "@p_CreatedBy='" + laundryModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;
	}

	public static String getItemPrice(RestLaundryItemPriceModel laundry) {
		String s = "";

		if (laundry.getLaundryItemName() != null && laundry.getLaundryItemName() != "") {
			s = s + "@p_priceItems='" + laundry.getLaundryItemName() + "',";
		}

		if (laundry.getLndryServiceName() != null && laundry.getLndryServiceName() != "") {
			s = s + "@p_priceLaundry='" + laundry.getLndryServiceName() + "',";
		}
		if (laundry.getHotelServiceName() != null && laundry.getHotelServiceName() != "") {
			s = s + "@p_priceHotels='" + laundry.getHotelServiceName() + "',";
		}
		if (laundry.getUserTypeName() != null && laundry.getUserTypeName() != "") {
			s = s + "@p_priceUserType='" + laundry.getUserTypeName() + "',";
		}
		if (laundry.getEffectiveDate() != null && laundry.getEffectiveDate() != "") {
			s = s + "@p_effectiveDate='" + laundry.getEffectiveDate() + "',";
		}

		if (laundry.getlItemPrice() != null) {
			s = s + "@p_price='" + laundry.getlItemPrice() + "',";
		}
		if (laundry.getlItemPriceStatus() != null && laundry.getlItemPriceStatus() != "") {
			s = s + "@p_priceActive=" + laundry.getlItemPriceStatus() + ",";
		} else {
			s = s + "@p_priceActive=" + 0 + ",";
		}
		if (laundry.getCreatedBy() != null && laundry.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + laundry.getCreatedBy() + "',";
		}
		
		
		if (laundry.getOldItemId() != null && laundry.getOldItemId() != "") {
			s = s + "@p_oldItemId='" + laundry.getOldItemId() + "',";
		}
		if (laundry.getOldLaundryService() != null && laundry.getOldLaundryService() != "") {
			s = s + "@p_oldLaundryService='" + laundry.getOldLaundryService() + "',";
		}
		if (laundry.getOldHotelService() != null && laundry.getOldHotelService() != "") {
			s = s + "@p_oldHotelService='" + laundry.getOldHotelService() + "',";
		} 
		if (laundry.getOldUserType() != null && laundry.getOldUserType() != "") {
			s = s + "@p_oldUserType='" + laundry.getOldUserType() + "',";
		}
		if (laundry.getOldEffectiveDate() != null && laundry.getOldEffectiveDate() != "") {
			s = s + "@p_oldEffectiveDate='" + laundry.getOldEffectiveDate() + "',";
		}
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;
	}
}