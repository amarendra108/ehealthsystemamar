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
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateQuotationNewParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateRequisitionParam;
import nirmalya.aatithya.restmodule.common.utils.GenerateSaleOrderNewParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.model.RestQuotationNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestSaleOrderNewModel;
import nirmalya.aatithya.restmodule.master.model.ProductCategoryModel;
import nirmalya.aatithya.restmodule.procurment.model.InventorySkuProductModel;

@Repository
public class RestSaleOrderNewDao {
	
	Logger logger = LoggerFactory.getLogger(RestSaleOrderNewDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings( "unchecked" )
	public List<DropDownModel> GetStoreList() {
		logger.info("Method : demo Dao starts");

		List<DropDownModel> GetStoreList = new ArrayList<DropDownModel>();

		try {
			
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
					.setParameter("actionType", "GetStoreList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				GetStoreList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : demo Dao ends");

		return GetStoreList;
	}

	/*
	 * cust Autosearch
	 * 
	 */


	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> getCustomerAutoSearchNewList(String id) {
		logger.info("Method : getCustomerAutoSearchNewList starts");
		
		List<RestSaleOrderNewModel> itemNameList = new ArrayList<RestSaleOrderNewModel>();
		JsonResponse<List<RestSaleOrderNewModel>> resp = new JsonResponse<List<RestSaleOrderNewModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
					.setParameter("actionType", "getcustomerList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				RestSaleOrderNewModel dropDownModel = new RestSaleOrderNewModel(null, null, m[0], m[1], null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, m[2], null, null, null, null, null, null,null,null,null,null);
				itemNameList.add(dropDownModel);
			}
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getCustomerAutoSearchNewList ends");
		return response;
	}
	
	/*
	 * item autosearch
	 */
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> getItemQuotationAutoSearchNewList(String id) {
		logger.info("Method : getItemQuotationAutoSearchNewList starts");
		List<RestSaleOrderNewModel> itemNameList = new ArrayList<RestSaleOrderNewModel>();
		JsonResponse<List<RestSaleOrderNewModel>> resp = new JsonResponse<List<RestSaleOrderNewModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
					.setParameter("actionType", "getitemquotationList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				RestSaleOrderNewModel dropDownModel = new RestSaleOrderNewModel(null, null, null, null, null, null,
						null, null, null, null, m[0], m[1], null, null, null, m[2], null, null, null, null, null, null,
						null, null, null, m[3], null, null, null, null, null, null, null, null);
				itemNameList.add(dropDownModel);
			}
			//System.out.println("getAllItemList" +itemNameList);
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getItemQuotationAutoSearchNewList ends");
		return response;
	}
	
	// get Product Category List Modal
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ProductCategoryModel>>> getProductCategoryDataListModal() {
		logger.info("Method : getProductCategoryDataListModal starts");

		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();
		List<ProductCategoryModel> yearList = new ArrayList<ProductCategoryModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
					.setParameter("actionType", "getProductCategoryList").setParameter("actionValue", "")
					.getResultList();
			for (Object[] m : x) {

				ProductCategoryModel item = new ProductCategoryModel(m[0], m[1], m[2], m[3], m[4], m[5]);
				yearList.add(item);

				 //System.out.println("yearList===="+yearList);
			}
			resp.setBody(yearList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<ProductCategoryModel>>> response = new ResponseEntity<JsonResponse<List<ProductCategoryModel>>>(
				resp, HttpStatus.CREATED);
		 System.out.println(response);
		logger.info("Method : getProductCategoryDataListModal ends");
		return response;
	}
		
		// get product by cat

		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> getProductsNByCat(String catId) {
			//System.out.println("catId" + catId);
			logger.info("Method : getProductsNByCat starts");

			List<InventorySkuProductModel> productList = new ArrayList<InventorySkuProductModel>();
			JsonResponse<List<InventorySkuProductModel>> resp = new JsonResponse<List<InventorySkuProductModel>>();
			try {

				String value = GenerateRequisitionParam.getProduct(catId);
				List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
						.setParameter("actionType", "getProductByCat").setParameter("actionValue", value).getResultList();

				for (Object[] m : x) {
					InventorySkuProductModel dropDownModel = new InventorySkuProductModel(m[0], m[1], m[2], m[3], m[4],
							m[5], m[6]);
					productList.add(dropDownModel);
				}

				resp.setBody(productList); 
				//System.out.println(resp);

			} catch (Exception e) {
				e.printStackTrace();
			}

			ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> response = new ResponseEntity<JsonResponse<List<InventorySkuProductModel>>>(
					resp, HttpStatus.CREATED);
			logger.info("Method : getProductsNByCat ends");
			return response;
		}
	/*
	 * add 	
	 */
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> addsalenew(
				List<RestSaleOrderNewModel> restSaleOrderNewModel) {

			logger.info("Method : addsalenew starts");
			
				System.out.println("RestSaleOrderNewModel"+restSaleOrderNewModel);
			JsonResponse<List<RestSaleOrderNewModel>> resp = new JsonResponse<List<RestSaleOrderNewModel>>();
			List<RestSaleOrderNewModel> listData = new ArrayList<RestSaleOrderNewModel>();
			
			
			try {
				String values = GenerateSaleOrderNewParameter.getAddempParam(restSaleOrderNewModel);
				System.out.println("value#"+values);

				if (restSaleOrderNewModel.get(0).getSalesOrder() == null
						|| restSaleOrderNewModel.get(0).getSalesOrder() == "") {
					System.out.println("ADD#"+values);
					List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
							.setParameter("actionType", "addsalenew").setParameter("actionValue", values)
							.getResultList();
					try {
						for (Object[] m : x) {
							
							Object DATER = null;
							if (m[22] != null) {
								DATER = m[22].toString();
							}
							Object DATET = null;
							if (m[23] != null) {
								DATET = m[23].toString();
							}

							RestSaleOrderNewModel dropDownModel = new RestSaleOrderNewModel(m[0],null,m[1],
									m[2],null,null,m[3],m[4],m[5],null,m[6],m[7],m[8],m[9],m[10],m[11],
									m[12],m[13],m[14],m[15],m[16],m[17],null,m[18],null,
									m[19],m[20],m[21],DATER,DATET,null,m[24],m[25],m[26]);
							listData.add(dropDownModel);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else {
					System.out.println("@modify"+values);
					List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
							.setParameter("actionType", "modifysalesnew").setParameter("actionValue", values)
							.getResultList();
					
					
					try {
							for (Object[] m : x) {
							
							Object DATER = null;
							if (m[22] != null) {
								DATER = m[22].toString();
							}
							Object DATET = null;
							if (m[23] != null) {
								DATET = m[23].toString();
							}

							RestSaleOrderNewModel dropDownModel = new RestSaleOrderNewModel(m[0],null,m[1],
									m[2],null,null,m[3],m[4],m[5],null,m[6],m[7],m[8],m[9],m[10],m[11],
									m[12],m[13],m[14],m[15],m[16],m[17],null,m[18],null,
									m[19],m[20],m[21],DATER,DATET,null,m[24],m[25],m[26]);
							listData.add(dropDownModel);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("modify print"+listData);
					
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
			ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>>(
					resp, HttpStatus.CREATED);
			System.out.println("response data is" + response);
			logger.info("Method : addsalenew ends");
			return response;
		}
		
/*
 * view		
 */
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestSaleOrderNewModel>> getAllsalesOrder() {
			logger.info("Method : getAllsalesOrder Dao starts");

			List<RestSaleOrderNewModel> getAllemployee = new ArrayList<RestSaleOrderNewModel>();
			JsonResponse<List<RestSaleOrderNewModel>> resp = new JsonResponse<List<RestSaleOrderNewModel>>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
						.setParameter("actionType", "viewsalesorder").setParameter("actionValue", "").getResultList();

				for (Object[] m : x) {
					
					Object createdOn = null;
					if (m[6] != null) {
						createdOn = m[6].toString();
					}
					
					Object DATER = null;
					if (m[9] != null) {
						DATER = m[9].toString();
					}
					Object DATET = null;
					if (m[10] != null) {
						DATET = m[10].toString();
					}
				
					RestSaleOrderNewModel viewdemo = new RestSaleOrderNewModel(m[0], null, m[1], m[2], null, null, m[3],
							m[4], m[5], createdOn, null, null, null, null, null, null, null, null, null, null, null,
							null, null, null, null, null, m[7], m[8], DATER, DATET, null, null, null, null);
					getAllemployee.add(viewdemo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			resp.setBody(getAllemployee);
			logger.info("Method : getAllsalesOrder Dao ends");

			return resp;

		}	
		
/*
 * edit
 * 
 */
		@SuppressWarnings("unchecked")
		public List<RestSaleOrderNewModel> viewsalesOrdeerEdit(String id) {
			logger.info("Method : viewsalesOrdeerEdit starts");
			System.out.println("RestSaleOrderNewModel" + id);
			List<RestSaleOrderNewModel> getRequisitionTypeList = new ArrayList<RestSaleOrderNewModel>();

			try {
				String values = "SET @p_salesId='" + id + "';";
				System.out.println(values);
				List<Object[]> x = em.createNamedStoredProcedureQuery("salesOrderNew")
						.setParameter("actionType", "getSalesOrderEdit").setParameter("actionValue", values)
						.getResultList();
				try {
					for (Object[] m : x) {
						
						Object DATER = null;
						if (m[22] != null) {
							DATER = m[22].toString();
						}
						Object DATET = null;
						if (m[23] != null) {
							DATET = m[23].toString();
						}

						RestSaleOrderNewModel dropDownModel = new RestSaleOrderNewModel(m[0],null,m[1],
								m[2],null,null,m[3],m[4],m[5],null,m[6],m[7],m[8],m[9],m[10],m[11],
								m[12],m[13],m[14],m[15],m[16],m[17],null,m[18],null,
								m[19],m[20],m[21],DATER,DATET,null,m[24],m[25],m[26]);
					getRequisitionTypeList.add(dropDownModel);
					System.out.println("print edit" + getRequisitionTypeList);
					
					
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("@@@@@@@@edit"+getRequisitionTypeList);
			logger.info("Method : viewsalesOrdeerEdit ends");
			return getRequisitionTypeList;
		}

		/*
		 * delete
		 */
		
		public ResponseEntity<JsonResponse<Object>> deletesalesOrder(RestSaleOrderNewModel deletesalesOrder) {
			logger.info("Method : deletesalesOrder starts");
			System.out.println("restSaleOrderNewModel" + deletesalesOrder);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");

			try {
				String value = GenerateSaleOrderNewParameter.getDeleteParam(deletesalesOrder);
				System.out.println(value);
				em.createNamedStoredProcedureQuery("salesOrderNew")
						.setParameter("actionType", "deletesalesOrder").setParameter("actionValue", value).execute();
				System.out.println("print block" + deletesalesOrder);
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
			System.out.println("@@@@@@@@@@@@@@@@" + deletesalesOrder);
			logger.info("Method : deletesalesOrder ends");
			return response;
		}

}
