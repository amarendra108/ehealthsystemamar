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
import nirmalya.aatithya.restmodule.common.utils.GenerateSalesInvReturnNewParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateSalesInvoiceNewParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.model.RestSalesInvoiceNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestSalesInvoiceReturnNewModel;

@Repository
public class RestSalesInvoiceReturnNewDao {
	Logger logger = LoggerFactory.getLogger(RestSalesInvoiceReturnNewDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * sales order auto search
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> salesOrderAutoSearchNewList(String id) {
		logger.info("Method : salesOrderAutoSearchNewListDao starts");
		System.out.println("RestSalesInvoiceReturnNewModel" + id);
		List<RestSalesInvoiceReturnNewModel> itemNameList = new ArrayList<RestSalesInvoiceReturnNewModel>();
		JsonResponse<List<RestSalesInvoiceReturnNewModel>> resp = new JsonResponse<List<RestSalesInvoiceReturnNewModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceReturnNew")
					.setParameter("actionType", "getsalesOrderList").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				RestSalesInvoiceReturnNewModel dropDownModel = new RestSalesInvoiceReturnNewModel(null, null, m[0], m[1], null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null,null,null);
				itemNameList.add(dropDownModel);
			}
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("autosearch" + response);
		logger.info("Method : salesOrderAutoSearchNewListDao ends");
		return response;
	}
	/*
	 * sales Invoice auto search
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> getSalesInvoiceAutoSearchNewList(String id) {
		logger.info("Method : getSalesInvoiceAutoSearchNewListDao starts");
		System.out.println("getSalesInvoiceAutoSearchNewList" + id);
		List<RestSalesInvoiceReturnNewModel> itemNameList = new ArrayList<RestSalesInvoiceReturnNewModel>();
		JsonResponse<List<RestSalesInvoiceReturnNewModel>> resp = new JsonResponse<List<RestSalesInvoiceReturnNewModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceReturnNew")
					.setParameter("actionType", "getsalesInvoiceList").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				RestSalesInvoiceReturnNewModel dropDownModel = new RestSalesInvoiceReturnNewModel(null,
				 m[0],null,null,null,null,null,null,null,null,m[1],m[2],m[3],null,null,null,null,m[4],null,
						null,null,null,null,null,m[5],m[6],null,null,null,null);
				itemNameList.add(dropDownModel);
			}
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("autosearch" + response);
		logger.info("Method : getSalesInvoiceAutoSearchNewListDao ends");
		return response;
	}
	
	/*
	 * add
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> addsaleInvoicRtnenew(
			List<RestSalesInvoiceReturnNewModel> restSalesInvoiceReturnNewModel) {

		logger.info("Method : addsaleInvoicenew starts");

		System.out.println("RestSalesInvoiceReturnNewModel" + restSalesInvoiceReturnNewModel);
		JsonResponse<List<RestSalesInvoiceReturnNewModel>> resp = new JsonResponse<List<RestSalesInvoiceReturnNewModel>>();
		List<RestSalesInvoiceReturnNewModel> listData = new ArrayList<RestSalesInvoiceReturnNewModel>();

		try {
			String values = GenerateSalesInvReturnNewParameter.getAddempParam(restSalesInvoiceReturnNewModel);
			//System.out.println("value#" + values);

			if (restSalesInvoiceReturnNewModel.get(0).getSalesInreturn() == null
					|| restSalesInvoiceReturnNewModel.get(0).getSalesInreturn() == "") {
				//System.out.println("ADD#" + values);
				List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceReturnNew")
						.setParameter("actionType", "addsaleInvrtnnew").setParameter("actionValue", values)
						.getResultList();
				try {
					for (Object[] m : x) {
						RestSalesInvoiceReturnNewModel dropDownModel = new RestSalesInvoiceReturnNewModel(m[0], m[1], null,
								m[2], null, null, m[3],m[4], m[5], null, m[6],null, m[7], m[8], null, m[9], m[10],
								m[11], m[12], m[13], m[14], m[15], m[16], m[17],m[18], m[19], m[20], m[21], m[22], null);
						listData.add(dropDownModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("@modify"+values);
				List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceReturnNew")
						.setParameter("actionType", "modifyInrtnnew").setParameter("actionValue", values)
						.getResultList();

				try {
					for (Object[] m : x) {
						RestSalesInvoiceReturnNewModel dropDownModel = new RestSalesInvoiceReturnNewModel(m[0], m[1], null,
								m[2], null, null, m[3],m[4], m[5], null, m[6],null, m[7], m[8], null, m[9], m[10],
								m[11], m[12], m[13], m[14], m[15], m[16], m[17],m[18], m[19], m[20], m[21], m[22], null);
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
		ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("response data is" + response);
		logger.info("Method : addsaleInvoicenew ends");
		return response;
	}

	/*
	 * view
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestSalesInvoiceReturnNewModel>> getAllsalesInvoicertn() {
		logger.info("Method : getAllsalesInvoice Dao starts");

		List<RestSalesInvoiceReturnNewModel> getAllemployee = new ArrayList<RestSalesInvoiceReturnNewModel>();
		JsonResponse<List<RestSalesInvoiceReturnNewModel>> resp = new JsonResponse<List<RestSalesInvoiceReturnNewModel>>();

		try {
			System.out.println("@view"+"viewdemo");
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceReturnNew")
					.setParameter("actionType", "viewsalesInvoicertn").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				Object createdOn = null;
				if (m[6] != null) {
					createdOn = m[6].toString();
				}

				RestSalesInvoiceReturnNewModel viewdemo = new RestSalesInvoiceReturnNewModel(m[0], m[1], null,
						m[2], null, null, m[3], m[4], m[5],createdOn, null, null,null, null, null, null,null,
						null,null,null,null, null, null,null,null, null, null, null,null, null);
				getAllemployee.add(viewdemo);
			}
			
			System.out.println("@view5"+"getAllemployee");

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : getAllsalesInvoice Dao ends");

		return resp;

	}
	
/*edit
 * 	
 */
	@SuppressWarnings("unchecked")
	public List<RestSalesInvoiceReturnNewModel> viewsalesIvoicertnEdit(String id) {
		logger.info("Method : viewsalesOrdeerEdit starts");
		System.out.println("RestSalesInvoiceReturnNewModel" + id);
		List<RestSalesInvoiceReturnNewModel> getRequisitionTypeList = new ArrayList<RestSalesInvoiceReturnNewModel>();

		try {
			String values = "SET @p_salesInreturnId='" + id + "';";
			System.out.println(values);
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceReturnNew")
					.setParameter("actionType", "getSalesInvrtnEdit").setParameter("actionValue", values)
					.getResultList();
			try {
				for (Object[] m : x) {
					RestSalesInvoiceReturnNewModel dropDownModel = new RestSalesInvoiceReturnNewModel(m[0], m[1], null,
							m[2], null, null, m[3],m[4], m[5], null, m[6],null, m[7], m[8], null, m[9], m[10],
							m[11], m[12], m[13], m[14], m[15], m[16], m[17],m[18], m[19], m[20], m[21], m[22], null);
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
	
	/*delete
	 * 	
	 */
	public ResponseEntity<JsonResponse<Object>> deletesalesInvretn(RestSalesInvoiceReturnNewModel deletesalesInvretn) {
		logger.info("Method : deletesalesInvrtn Dao starts");
		System.out.println("restSalesInvoiceReturnNewModel" + deletesalesInvretn);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = GenerateSalesInvReturnNewParameter.getDeleteParamnew(deletesalesInvretn);
			System.out.println(value);
			em.createNamedStoredProcedureQuery("salesInvoiceReturnNew").setParameter("actionType", "deletesalesInvrtn")
					.setParameter("actionValue", value).execute();
			System.out.println("print block" + deletesalesInvretn);
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
		System.out.println("@@@@@@@@@@@@@@@@" + deletesalesInvretn);
		logger.info("Method : deletesalesInvrtn Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public List<RestSalesInvoiceReturnNewModel> viewsalesIvoicertnEditrtn(String id) {
		logger.info("Method : viewsalesIvoicertnEditrtn starts");
		System.out.println("RestSalesInvoiceReturnNewModel" + id);
		List<RestSalesInvoiceReturnNewModel> getRequisitionTypeList = new ArrayList<RestSalesInvoiceReturnNewModel>();

		try {
			String values = "SET @p_salesInvoiceId='" + id + "';";
			System.out.println(values);
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceReturnNew")
					.setParameter("actionType", "getSalesedit").setParameter("actionValue", values)
					.getResultList();
			try {
				for (Object[] m : x) {
					RestSalesInvoiceReturnNewModel dropDownModel = new RestSalesInvoiceReturnNewModel(m[0], m[1], null,
							m[2], null, null, m[3],m[4], m[5], null, m[6],null, m[7], m[8], null, m[9], m[10],
							m[11], m[12], m[13], m[14], m[15], m[16], m[17],m[18], m[19], m[20], m[21], m[22], null);
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
		logger.info("Method : viewsalesIvoicertnEditrtn ends");
		return getRequisitionTypeList;
	}
	
}
