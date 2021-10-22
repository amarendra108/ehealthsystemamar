package nirmalya.aatithya.restmodule.hospitality.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateLaundryMasterParamter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.hospitality.model.RestHotelServiceModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemCategoryModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemPriceModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryReturnInModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryServiceModel;

@Repository
public class RestLaundryMasterDao {
	Logger logger = LoggerFactory.getLogger(RestLaundryMasterDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;

	// Add

	public ResponseEntity<JsonResponse<Object>> addLItemCategory(RestLaundryItemCategoryModel lmModel) {

		logger.info("Method : addLItemCategory starts");
		System.out.println("taskModel==" + lmModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateLaundryMasterParamter.getLItemCategory(lmModel);
			System.out.println("45677654" + values);

			if (lmModel.getItemCategoryID() == null || lmModel.getItemCategoryID() == "") {

				// System.out.println("print in addEmp block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "addItemCategory")
						.setParameter("actionValue", values).execute();

			} else {
				// System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "modifyItemCategory")
						.setParameter("actionValue", values).execute();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("YYYY" + response);
		logger.info("Method : addLItemCategory ends");
		return response;
	}

	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLaundryItemCategoryModel>> viewLItemCategory() {

		logger.info("Method : viewLItemCategory Dao starts");

		List<RestLaundryItemCategoryModel> lmModel = new ArrayList<RestLaundryItemCategoryModel>();
		JsonResponse<List<RestLaundryItemCategoryModel>> resp = new JsonResponse<List<RestLaundryItemCategoryModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "viewItemCategory").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				Object val = null;
				if (m[3] != null) {
					val = m[3].toString();
				}

				RestLaundryItemCategoryModel viewtask = new RestLaundryItemCategoryModel(m[0], m[1], m[2], val, null,
						null);
				lmModel.add(viewtask);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(lmModel);

		System.out.println("AAAAAA" + resp);
		logger.info("Method : viewLItemCategory Dao ends");

		return resp;
	}

	// delete

	public JsonResponse<RestLaundryItemCategoryModel> deleteLItemCategory(String id) {
		logger.info("Method : deleteLItemCategory starts");
		System.out.println("delete id==" + id);
		RestLaundryItemCategoryModel req = new RestLaundryItemCategoryModel();
		JsonResponse<RestLaundryItemCategoryModel> resp = new JsonResponse<RestLaundryItemCategoryModel>();

		try {

			String value = "SET @p_itemCatId='" + id + "';";
			System.out.println("@@@@@" + value);

			em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "deleteItemCategory")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteLItemCategory ends");

		return resp;
	}

	// Add

	public ResponseEntity<JsonResponse<Object>> addHotelService(RestHotelServiceModel hotelModel) {

		logger.info("Method : addHotelService starts");
		System.out.println("taskModel==" + hotelModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateLaundryMasterParamter.getHotelSerice(hotelModel);
			System.out.println("45677654" + values);

			if (hotelModel.getHotelID() == null || hotelModel.getHotelID() == "") {

				// System.out.println("print in addEmp block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "addHotelService")
						.setParameter("actionValue", values).execute();

			} else {
				// System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "modifyHotelService")
						.setParameter("actionValue", values).execute();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("YYYY" + response);
		logger.info("Method : addHotelService ends");
		return response;
	}

	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestHotelServiceModel>> viewHotelService() {

		logger.info("Method : viewHotelService Dao starts");

		List<RestHotelServiceModel> hotelModel = new ArrayList<RestHotelServiceModel>();
		JsonResponse<List<RestHotelServiceModel>> resp = new JsonResponse<List<RestHotelServiceModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "viewHotelService").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				/*
				 * Object val = null; if (m[3] != null) { val = m[3].toString(); }
				 */

				RestHotelServiceModel viewtask = new RestHotelServiceModel(m[0], m[1], m[2], m[3], m[4], null, null);
				hotelModel.add(viewtask);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(hotelModel);

		System.out.println("AAAAAA" + resp);
		logger.info("Method : viewHotelService Dao ends");

		return resp;
	}

	// delete

	public JsonResponse<RestHotelServiceModel> deleteHotelService(String id) {
		logger.info("Method : deleteHotelService starts");
		System.out.println("delete id==" + id);
		RestHotelServiceModel req = new RestHotelServiceModel();
		JsonResponse<RestHotelServiceModel> resp = new JsonResponse<RestHotelServiceModel>();

		try {

			String value = "SET @p_itemCatId='" + id + "';";
			System.out.println("@@@@@" + value);

			em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "deleteHotelService")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteHotelService ends");

		return resp;
	}

	// Add

	public ResponseEntity<JsonResponse<Object>> addLaundryservice(RestLaundryServiceModel returnModel) {

		logger.info("Method : addLaundryservice starts");
		System.out.println("taskModel==" + returnModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateLaundryMasterParamter.getLaundryServicess(returnModel);
			System.out.println("45677654" + values);

			if (returnModel.getLaundryServiceID() == null || returnModel.getLaundryServiceID() == "") {

				// System.out.println("print in addEmp block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "addLaundryServices")
						.setParameter("actionValue", values).execute();

			} else {
				// System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "modifyLaundryService")
						.setParameter("actionValue", values).execute();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("YYYY" + response);
		logger.info("Method : addLaundryservice ends");
		return response;
	}

	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLaundryServiceModel>> viewLaundryservice() {

		logger.info("Method : viewLaundryservice Dao starts");

		List<RestLaundryServiceModel> returnModel = new ArrayList<RestLaundryServiceModel>();
		JsonResponse<List<RestLaundryServiceModel>> resp = new JsonResponse<List<RestLaundryServiceModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "viewLaundryService").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				Object val = null;
				if (m[4] != null) {
					val = m[4].toString();
				}

				RestLaundryServiceModel viewlaundry = new RestLaundryServiceModel(m[0], m[1], m[2], m[3], val, null,
						null);
				returnModel.add(viewlaundry);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(returnModel);

		System.out.println("AAAAAA" + resp);
		logger.info("Method : viewLaundryservice Dao ends");

		return resp;
	}

	// delete

	public JsonResponse<RestLaundryServiceModel> deleteLaundryservice(String id) {
		logger.info("Method : deleteLaundryservice starts");
		System.out.println("delete id==" + id);
		RestLaundryServiceModel req = new RestLaundryServiceModel();
		JsonResponse<RestLaundryServiceModel> resp = new JsonResponse<RestLaundryServiceModel>();

		try {

			String value = "SET @p_laundryServ='" + id + "';";
			System.out.println("@@@@@" + value);

			em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "deleteLaundryService")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteLaundryservice ends");

		return resp;
	}

	// Add

	public ResponseEntity<JsonResponse<Object>> addLitem(RestLaundryItemModel itemModel) {

		logger.info("Method : addLitem starts");
		System.out.println("taskModel==" + itemModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateLaundryMasterParamter.getLItem(itemModel);
			System.out.println("45677654" + values);

			if (itemModel.getlItemID() == null || itemModel.getlItemID() == "") {

				// System.out.println("print in addEmp block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "addLaundryItem")
						.setParameter("actionValue", values).execute();

			} else {
				// System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "modifyLaundryItem")
						.setParameter("actionValue", values).execute();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("YYYY" + response);
		logger.info("Method : addLitem ends");
		return response;
	}

	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLaundryItemModel>> viewLitem() {

		logger.info("Method : viewLitem Dao starts");

		List<RestLaundryItemModel> returnModel = new ArrayList<RestLaundryItemModel>();
		JsonResponse<List<RestLaundryItemModel>> resp = new JsonResponse<List<RestLaundryItemModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "viewAllLaundryItem").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				Object val = null;
				if (m[8] != null) {
					val = m[8].toString();
				}

				RestLaundryItemModel viewlaundry = new RestLaundryItemModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], val, null, null, m[9]);
				returnModel.add(viewlaundry);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(returnModel);

		System.out.println("AAAAAA" + resp);
		logger.info("Method : viewLitem Dao ends");

		return resp;
	}

	// delete

	public JsonResponse<RestLaundryItemModel> deleteLitem(String id) {
		logger.info("Method : deleteLitem starts");
		System.out.println("delete id==" + id);
		RestLaundryItemModel req = new RestLaundryItemModel();
		JsonResponse<RestLaundryItemModel> resp = new JsonResponse<RestLaundryItemModel>();

		try {

			String value = "SET @p_laundryItems='" + id + "';";
			System.out.println("@@@@@" + value);

			em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "deleteLaundryItem")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteLitem ends");

		return resp;
	}

	// DROPDOWN

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getLaundryItemCategory() {
		logger.info("Method : getLaundryItemCategory starts");
		List<DropDownModel> itemcategory = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "getLaundryItemCategory").setParameter("actionValue", "")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				itemcategory.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getLaundryItemCategory ends");
		return itemcategory;
	}

	// Add

	public ResponseEntity<JsonResponse<Object>> addReturnIn(RestLaundryReturnInModel returnModel) {

		logger.info("Method : addReturnIn starts");
		System.out.println("taskModel==" + returnModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateLaundryMasterParamter.getReturnIn(returnModel);
			System.out.println("45677654" + values);

			if (returnModel.getReturnInId() == null || returnModel.getReturnInId() == "") {

				// System.out.println("print in addEmp block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "addReturnIn")
						.setParameter("actionValue", values).execute();

			} else {
				// System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "modifyReturnIn")
						.setParameter("actionValue", values).execute();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("YYYY" + response);
		logger.info("Method : addReturnIn ends");
		return response;
	}

	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLaundryReturnInModel>> viewReturnIn() {

		logger.info("Method : viewReturnIn Dao starts");

		List<RestLaundryReturnInModel> returnModel = new ArrayList<RestLaundryReturnInModel>();
		JsonResponse<List<RestLaundryReturnInModel>> resp = new JsonResponse<List<RestLaundryReturnInModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "viewReturnIn").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				/*
				 * Object val = null; if (m[3] != null) { val = m[3].toString(); }
				 */

				RestLaundryReturnInModel viewtask = new RestLaundryReturnInModel(m[0], m[1], m[2], m[3], null, null);
				returnModel.add(viewtask);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(returnModel);

		System.out.println("AAAAAA" + resp);
		logger.info("Method : viewReturnIn Dao ends");

		return resp;
	}

	// delete

	public JsonResponse<RestLaundryReturnInModel> deleteReturnIn(String id) {
		logger.info("Method : deleteReturnIn starts");
		System.out.println("delete id==" + id);
		RestLaundryReturnInModel req = new RestLaundryReturnInModel();
		JsonResponse<RestLaundryReturnInModel> resp = new JsonResponse<RestLaundryReturnInModel>();

		try {

			String value = "SET @p_returnId='" + id + "';";
			System.out.println("@@@@@" + value);

			em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "deleteReturnIn")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteReturnIn ends");

		return resp;
	}

	// DROPDOWN

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getLaundItemName() {
		logger.info("Method : getLaundItemName starts");
		List<DropDownModel> itemName = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "getitemNameList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				itemName.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getLaundItemName ends");
		return itemName;
	}

	// DROPDOWN

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getLaundryServicename() {
		logger.info("Method : getLaundryServicename starts");
		List<DropDownModel> laundry = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "getlaundryService").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				laundry.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getLaundryServicename ends");
		return laundry;
	}

// DROPDOWN

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getHotelServiceNamep() {
		logger.info("Method : getHotelServiceNamep starts");
		List<DropDownModel> hotel = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "gethotelService").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				hotel.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getHotelServiceNamep ends");
		return hotel;
	}

// DROPDOWN

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getLaundryUserType() {
		logger.info("Method : getLaundryUserType starts");
		List<DropDownModel> usertype = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "getUserType").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				usertype.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getLaundryUserType ends");
		return usertype;
	}

	// Add

	public ResponseEntity<JsonResponse<Object>> addLaundryItemPrice(RestLaundryItemPriceModel priceModel) {

		logger.info("Method : addLaundryItemPrice starts");
		System.out.println("taskModel==" + priceModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateLaundryMasterParamter.getItemPrice(priceModel);
			System.out.println("45677654" + values);

			if (priceModel.getEditID() == null) {

				System.out.println("print in addEmp block");
				em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "addLaundryPrice")
						.setParameter("actionValue", values).execute();

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("laundryItemService")
						.setParameter("actionType", "modifyLaundryItemPrice").setParameter("actionValue", values)
						.execute();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("YYYY" + response);
		logger.info("Method : addLaundryItemPrice ends");
		return response;
	}

	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLaundryItemPriceModel>> viewLaundryItemPrice() {

		logger.info("Method : viewLaundryItemPrice Dao starts");

		List<RestLaundryItemPriceModel> priceModel = new ArrayList<RestLaundryItemPriceModel>();
		JsonResponse<List<RestLaundryItemPriceModel>> resp = new JsonResponse<List<RestLaundryItemPriceModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("laundryItemService")
					.setParameter("actionType", "viewAllLaundryItemPrice").setParameter("actionValue", "")
					.getResultList();

			for (Object[] m : x) {

				Object val = null;
				if (m[10] != null) {
					val = m[10].toString();
				}

				RestLaundryItemPriceModel viewtask = new RestLaundryItemPriceModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], m[8], m[9], val, null, null);
				priceModel.add(viewtask);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(priceModel);

		System.out.println("AAAAAA" + resp);
		logger.info("Method : viewLaundryItemPrice Dao ends");

		return resp;
	}

	// delete

	public JsonResponse<RestLaundryItemPriceModel> deleteLaundryItemPrice(String id1, String id2, String id3,
			String id4, String id5) {
		logger.info("Method : deleteLaundryItemPrice starts");
		//System.out.println("delete id==" + id1 + " " + id2 + " " + id3 + " " + id4 + " " + id5);
		RestLaundryItemPriceModel req = new RestLaundryItemPriceModel();
		JsonResponse<RestLaundryItemPriceModel> resp = new JsonResponse<RestLaundryItemPriceModel>();

		try {

			String value = "SET @p_priceItems='" + id1 + "',@p_priceLaundry='" + id2 + "',@p_priceHotels='" + id3
					+ "',@p_priceUserType='" + id4 + "',@p_effectiveDate='" + id5 + "';";
			//System.out.println("@@@@@" + value);

			em.createNamedStoredProcedureQuery("laundryItemService").setParameter("actionType", "deleteLaundryItemPrice")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("@@@@@===" + resp);
		logger.info("Method : deleteLaundryItemPrice ends");

		return resp;
	}
}
