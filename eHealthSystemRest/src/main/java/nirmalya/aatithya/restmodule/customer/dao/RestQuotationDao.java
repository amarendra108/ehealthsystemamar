package nirmalya.aatithya.restmodule.customer.dao;

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
import nirmalya.aatithya.restmodule.common.utils.GenerateCustomerNewParameter;
import nirmalya.aatithya.restmodule.common.utils.GeneratePoNoNewParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateQuotationNewParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateRequisitionParam;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.model.RestCustoomerNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestQuotationNewModel;
import nirmalya.aatithya.restmodule.master.model.ProductCategoryModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRequisitionModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRfqModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRfqVendorModel;
import nirmalya.aatithya.restmodule.procurment.model.InventorySkuProductModel;

@Repository
public class RestQuotationDao {

	Logger logger = LoggerFactory.getLogger(RestQuotationDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestQuotationNewModel>> getAllquotation() {
		logger.info("Method : All quotation Dao starts");

		List<RestQuotationNewModel> getAllemployee = new ArrayList<RestQuotationNewModel>();
		JsonResponse<List<RestQuotationNewModel>> resp = new JsonResponse<List<RestQuotationNewModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "viewquotation").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DATE = null;
				if (m[4] != null) {
					DATE = m[4].toString();
				}
				Object createdOn = null;
				if (m[8] != null) {
					createdOn = m[8].toString();
				}
				Object podate = null;
				if (m[10] != null) {
					podate = m[10].toString();
				}
				Object sldate = null;
				if (m[13] != null) {
					sldate = m[13].toString();
				}
				System.out.println("getAllemployee" + getAllemployee);
				RestQuotationNewModel viewdemo = new RestQuotationNewModel(m[0], m[1], m[2], m[3], null, DATE, m[5],
						m[6], m[7], createdOn, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, m[9], podate, m[11], m[12],sldate);
				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : Allquotation Dao ends");

		return resp;

	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestQuotationNewModel>> getAllquotationItem() {
		logger.info("Method : getAllquotationItem starts");

		List<RestQuotationNewModel> getAllemployee = new ArrayList<RestQuotationNewModel>();
		JsonResponse<List<RestQuotationNewModel>> resp = new JsonResponse<List<RestQuotationNewModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "viewquotationItem").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				// System.out.println("getAllemployee@@" +getAllemployee);
				RestQuotationNewModel viewdemo = new RestQuotationNewModel(null, null, null, null, null, null, null,
						null, null, null, null, m[0], m[1], m[2], m[3], m[4], m[5], null, m[6], m[7], m[8], null, null,
						null, null, m[9], null, null, null, null, null, null,null,null);
				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : getAllquotationItem ends");
		// System.out.println("response data is" + resp);
		return resp;

	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> getCustomerListByAutoSearch(String id) {
		logger.info("Method : getCustomerListByAutoSearch starts");

		List<RestQuotationNewModel> itemNameList = new ArrayList<RestQuotationNewModel>();
		JsonResponse<List<RestQuotationNewModel>> resp = new JsonResponse<List<RestQuotationNewModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "getcustomerList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				RestQuotationNewModel dropDownModel = new RestQuotationNewModel(null, null, m[0], m[1], null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, m[2], null, null, null, null, null, null, null, null,null,null);
				itemNameList.add(dropDownModel);
			}
			// System.out.println("getAllcustomer" +itemNameList);
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> response = new ResponseEntity<JsonResponse<List<RestQuotationNewModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getCustomerListByAutoSearch ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> getItemQuotationAutoSearchList(String id) {
		logger.info("Method : getItemQuotationAutoSearchList starts");
		List<RestQuotationNewModel> itemNameList = new ArrayList<RestQuotationNewModel>();
		JsonResponse<List<RestQuotationNewModel>> resp = new JsonResponse<List<RestQuotationNewModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "getitemquotationList").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				RestQuotationNewModel dropDownModel = new RestQuotationNewModel(null, null, null, null, null, null,
						null, null, null, null, m[0], m[1], null, null, null, m[2], null, null, null, null, null, null,
						null, null, null, m[3], null, null, null, null, null, null,null, null);
				itemNameList.add(dropDownModel);
			}
			System.out.println("getAllItemList" + itemNameList);
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> response = new ResponseEntity<JsonResponse<List<RestQuotationNewModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getItemQuotationAutoSearchList ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> addquotationnew(
			List<RestQuotationNewModel> restQuotationNewModel) {

		logger.info("Method : addquotationnew starts");

		JsonResponse<List<RestQuotationNewModel>> resp = new JsonResponse<List<RestQuotationNewModel>>();
		List<RestQuotationNewModel> listData = new ArrayList<RestQuotationNewModel>();

		try {
			String values = GenerateQuotationNewParameter.getAddempParam(restQuotationNewModel);

			if (restQuotationNewModel.get(0).getQuotationId() == null
					|| restQuotationNewModel.get(0).getQuotationId() == "") {

				List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
						.setParameter("actionType", "addquotationnew").setParameter("actionValue", values)
						.getResultList();
				try {
					for (Object[] m : x) {
						Object DATE = null;
						if (m[4] != null) {
							DATE = m[4].toString();
						}

						RestQuotationNewModel dropDownModel = new RestQuotationNewModel(m[0], m[1], m[2], m[3], null,
								DATE, m[5], m[6], m[7], null, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15],
								m[16], m[17], m[18], m[19], null, m[20], m[21], m[22], m[23], m[24], m[25], null, null,
								null,null, null);
						listData.add(dropDownModel);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("@addd" + listData);
			} else {
				System.out.println("@modify" + values);
				List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
						.setParameter("actionType", "modifyquotationnew").setParameter("actionValue", values)
						.getResultList();

				try {
					for (Object[] m : x) {
						Object DATE = null;
						if (m[4] != null) {
							DATE = m[4].toString();
						}

						RestQuotationNewModel dropDownModel = new RestQuotationNewModel(m[0], m[1], m[2], m[3], null,
								DATE, m[5], m[6], m[7], null, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15],
								m[16], m[17], m[18], m[19], null, m[20], m[21], m[22], m[23], m[24], m[25], null, null,
								null,null, null);
						listData.add(dropDownModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("print in modify block" + listData);
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> response = new ResponseEntity<JsonResponse<List<RestQuotationNewModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("response data is" + response);
		logger.info("Method : addquotationnew ends");
		return response;
	}

	// get Product Category List Modal
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ProductCategoryModel>>> getProductCategoryDataListModal() {
		logger.info("Method : getProductCategoryDataListModal starts");

		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();
		List<ProductCategoryModel> yearList = new ArrayList<ProductCategoryModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "getProductCategoryList").setParameter("actionValue", "")
					.getResultList();
			for (Object[] m : x) {

				ProductCategoryModel item = new ProductCategoryModel(m[0], m[1], m[2], m[3], m[4], m[5]);
				yearList.add(item);

				// System.out.println("getAllItemList" +yearList);
			}
			resp.setBody(yearList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<ProductCategoryModel>>> response = new ResponseEntity<JsonResponse<List<ProductCategoryModel>>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : getProductCategoryDataListModal ends");
		// System.out.println(response);
		return response;
	}

	// get product by cat

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> getProductsByCat(String catId) {
		// System.out.println("catId" + catId);
		logger.info("Method : getProductsByCat starts");

		List<InventorySkuProductModel> productList = new ArrayList<InventorySkuProductModel>();
		JsonResponse<List<InventorySkuProductModel>> resp = new JsonResponse<List<InventorySkuProductModel>>();
		try {

			String value = GenerateRequisitionParam.getProduct(catId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "getProductByCat").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				InventorySkuProductModel dropDownModel = new InventorySkuProductModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6]);
				productList.add(dropDownModel);
			}

			resp.setBody(productList);
			// System.out.println(resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> response = new ResponseEntity<JsonResponse<List<InventorySkuProductModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getProductsByCat ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<RestQuotationNewModel> viewQuotationEdit(String id) {
		logger.info("Method : viewQuotationEdit starts");
		System.out.println("restQuotationNewModel" + id);
		// JsonResponse<List<RestQuotationNewModel>> resp = new
		// JsonResponse<List<RestQuotationNewModel>>();
		List<RestQuotationNewModel> getRequisitionTypeList = new ArrayList<RestQuotationNewModel>();

		try {
			String values = "SET @p_quotationId='" + id + "';";
			System.out.println(values);
			List<Object[]> x = em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "getQuotationEdit").setParameter("actionValue", values).getResultList();
			try {
				for (Object[] m : x) {

					Object DATE = null;
					if (m[4] != null) {
						DATE = m[4].toString();
					}
					RestQuotationNewModel dropDownModel = new RestQuotationNewModel(m[0], m[1], m[2], m[3], null, DATE,
							m[5], m[6], m[7], null, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16], m[17],
							m[18], m[19], null, m[20], m[21], m[22], m[23], m[24], m[25], null, null, null,null, null);
					getRequisitionTypeList.add(dropDownModel);
					System.out.println("print edit" + getRequisitionTypeList);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// resp.setBody(getRequisitionTypeList);
		System.out.println("@@@@@@@@edit" + getRequisitionTypeList);
		logger.info("Method : viewQuotationEdit ends");
		return getRequisitionTypeList;
	}

	/*
	 * delete
	 */

	public ResponseEntity<JsonResponse<Object>> deleteItemQuotation(RestQuotationNewModel deleteItemQuotation) {
		logger.info("Method : restDeleteRequisition starts");
		System.out.println("restQuotationNewModel" + deleteItemQuotation);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = GenerateQuotationNewParameter.getDeleteParam(deleteItemQuotation);
			System.out.println(value);
			em.createNamedStoredProcedureQuery("quotationmasterRotines")
					.setParameter("actionType", "deleteQuotationItem").setParameter("actionValue", value).execute();
			System.out.println("print block" + deleteItemQuotation);
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("@@@@@@@@@@@@@@@@" + deleteItemQuotation);
		logger.info("Method : deleteItemQuotation ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> addpoNo(RestQuotationNewModel quotationNewModel) {

		logger.info("Method : addpoNo starts");
		System.out.println("quotationNewModel" + quotationNewModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GeneratePoNoNewParameter.getAddempParam(quotationNewModel);
			System.out.println(values);

			if (quotationNewModel.getQuotationId() == null || quotationNewModel.getQuotationId() == "") {
				System.out.println("Hii New" + quotationNewModel);
				em.createNamedStoredProcedureQuery("quotationmasterRotines").setParameter("actionType", "addpoNo")
						.setParameter("actionValue", values).execute();
				System.out.println("print in addEmp block");

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("quotationmasterRotines").setParameter("actionType", "modifypoNo")
						.setParameter("actionValue", values).execute();

			}

		} catch

		(Exception e) {
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
		System.out.println(response);
		logger.info("Method : addpoNo ends");
		return response;
	}
}
