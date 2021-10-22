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
import nirmalya.aatithya.restmodule.common.utils.GenerateInvPaymentNewParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateSalesInvoiceNewParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.model.RestSaleOrderNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestSalesInvoiceNewModel;

@Repository
public class SalesInvoiceNewDao {

	Logger logger = LoggerFactory.getLogger(SalesInvoiceNewDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings( "unchecked" )
	public List<DropDownModel> GetStoreNameList() {
		logger.info("Method : demo Dao starts");

		List<DropDownModel> GetStoreNameList = new ArrayList<DropDownModel>();

		try {
			
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceNew")
					.setParameter("actionType", "GetStoreNameList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				GetStoreNameList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		logger.info("Method : demo Dao ends");

		return GetStoreNameList;
	}


	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>> getSalesOrderAutoSearchNewList(String id) {
		logger.info("Method : getSalesOrderAutoSearchNewList starts");
		System.out.println("RestSalesInvoiceNewModel" + id);
		List<RestSalesInvoiceNewModel> itemNameList = new ArrayList<RestSalesInvoiceNewModel>();
		JsonResponse<List<RestSalesInvoiceNewModel>> resp = new JsonResponse<List<RestSalesInvoiceNewModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceNew")
					.setParameter("actionType", "getsalesInvoiceList").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				RestSalesInvoiceNewModel dropDownModel = new RestSalesInvoiceNewModel(null, null, m[0], null, null,
						null, null, m[1], m[2], m[3], null, null, null, m[4], null, null, null, null, null, null, m[5],
						m[6], null, null, null, null, m[7], null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
				itemNameList.add(dropDownModel);
			}
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("autosearch" + response);
		logger.info("Method : getCustomerAutoSearchNewList ends");
		return response;
	}

	/*
	 * add
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>> addsaleInvoicenew(
			List<RestSalesInvoiceNewModel> restSalesInvoiceNewModel) {

		logger.info("Method : addsaleInvoicenew starts");

		System.out.println("RestSalesInvoiceNewModel" + restSalesInvoiceNewModel);
		JsonResponse<List<RestSalesInvoiceNewModel>> resp = new JsonResponse<List<RestSalesInvoiceNewModel>>();
		List<RestSalesInvoiceNewModel> listData = new ArrayList<RestSalesInvoiceNewModel>();

		try {
			String values = GenerateSalesInvoiceNewParameter.getAddempParam(restSalesInvoiceNewModel);
			System.out.println("value#" + values);

			if (restSalesInvoiceNewModel.get(0).getSaleInvoice() == null
					|| restSalesInvoiceNewModel.get(0).getSaleInvoice() == "") {
				System.out.println("ADD#" + values);
				List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceNew")
						.setParameter("actionType", "addsaleInvoicenew").setParameter("actionValue", values)
						.getResultList();
				try {
					for (Object[] m : x) {
						RestSalesInvoiceNewModel dropDownModel = new RestSalesInvoiceNewModel(m[0], null, m[1], null,
								m[2], m[3], null, m[4], null, m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13],
								m[14], m[15], m[16], m[17], m[18], m[19], m[20], null, m[21], null, null, null, null,
								null, null, null, null, null, null, null, null, m[22],null,null,null,null,null,null,null,null);
						listData.add(dropDownModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("@modify"+values);
				List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceNew")
						.setParameter("actionType", "modifysaleInvoicenew").setParameter("actionValue", values)
						.getResultList();

				try {
					for (Object[] m : x) {
						RestSalesInvoiceNewModel dropDownModel = new RestSalesInvoiceNewModel(m[0], null, m[1], null,
								m[2], m[3], null, m[4],null, m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13],
								m[14], m[15], m[16], m[17], m[18], m[19], m[20], null, m[21], null,null,null,null,null,null,null,null,null,null,null,null,m[22],null,null,null,null,null,null,null,null);
						listData.add(dropDownModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println("modify print"+listData);

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
		ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>> response = new ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("response data is" + response);
		logger.info("Method : addsaleInvoicenew ends");
		return response;
	}

	/*
	 * view
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestSalesInvoiceNewModel>> getAllsalesInvoice() {
		logger.info("Method : getAllsalesInvoice Dao starts");

		List<RestSalesInvoiceNewModel> getAllemployee = new ArrayList<RestSalesInvoiceNewModel>();
		JsonResponse<List<RestSalesInvoiceNewModel>> resp = new JsonResponse<List<RestSalesInvoiceNewModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceNew")
					.setParameter("actionType", "viewsalesInvoice").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				Object createdOn = null;
				if (m[4] != null) {
					createdOn = m[4].toString();
				}

				RestSalesInvoiceNewModel viewdemo = new RestSalesInvoiceNewModel(m[0], null, m[1], null, m[2], m[3],
						createdOn, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, m[5], null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
				getAllemployee.add(viewdemo);
			}

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
	public List<RestSalesInvoiceNewModel> viewsalesOrdeerEdit(String id) {
		logger.info("Method : viewsalesOrdeerEdit starts");
		System.out.println("RestSalesInvoiceNewModel" + id);
		List<RestSalesInvoiceNewModel> getRequisitionTypeList = new ArrayList<RestSalesInvoiceNewModel>();

		try {
			String values = "SET @p_salesInvoice='" + id + "';";
			System.out.println(values);
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceNew")
					.setParameter("actionType", "getSalesInvoiceEdit").setParameter("actionValue", values)
					.getResultList();
			try {
				for (Object[] m : x) {
					RestSalesInvoiceNewModel dropDownModel = new RestSalesInvoiceNewModel(m[0], null, m[1], null,
							m[2], m[3], null, m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14],
							m[15], m[16], m[17], m[18], m[19], m[20], m[21], null, m[22], null,null,null,null,null,null,null,null,null,null,null,null,m[23],null,null,null,null,null,null,null,null);
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
	public ResponseEntity<JsonResponse<Object>> deletesalesInvoice(RestSalesInvoiceNewModel deletesalesInvoice) {
		logger.info("Method : deletesalesInvoice starts");
		System.out.println("restSalesInvoiceNewModel" + deletesalesInvoice);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = GenerateSalesInvoiceNewParameter.getDeleteParamnew(deletesalesInvoice);
			System.out.println(value);
			em.createNamedStoredProcedureQuery("salesInvoiceNew")
					.setParameter("actionType", "deletesalesInvoice").setParameter("actionValue", value).execute();
			System.out.println("print block" + deletesalesInvoice);
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
		System.out.println("@@@@@@@@@@@@@@@@" + deletesalesInvoice);
		logger.info("Method : deletesalesInvoice ends");
		return response;
	}
	
	
/*
 * payment mode	
 */
	
	@SuppressWarnings( "unchecked" )
	public List<DropDownModel> GetpaymentModeList() {
		logger.info("Method :paymentModeList dao starts");

		List<DropDownModel> GetpaymentModeList = new ArrayList<DropDownModel>();

		try {
			
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("salesInvoiceNew")
					.setParameter("actionType", "PaymentMode").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				GetpaymentModeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		logger.info("Method : paymentModeList dao ends");

		return GetpaymentModeList;
	}
	
	
	/*
	 * payment add
	 */

	public ResponseEntity<JsonResponse<Object>> addinvPaymentnew(
			RestSalesInvoiceNewModel salesInvoiceNewModel) {

		logger.info("Method : addsaleInvPaymentnewdao starts");

		System.out.println("RestSalesInvoiceNewModel" + salesInvoiceNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			String values = GenerateInvPaymentNewParameter.getAddpayParam(salesInvoiceNewModel);
			System.out.println("value#" + values);

			if (salesInvoiceNewModel.getSaleInvoice() == null
					|| salesInvoiceNewModel.getSaleInvoice() == "") {
				System.out.println("ADD#" + values);
				em.createNamedStoredProcedureQuery("salesInvoiceNew")
						.setParameter("actionType", "addsaleInvpaymentnew").setParameter("actionValue", values)
						.execute();
				

			} else {
				System.out.println("@modify"+values);
				 em.createNamedStoredProcedureQuery("salesInvoiceNew")
						.setParameter("actionType", "modifypaymentnew").setParameter("actionValue", values)
						.execute();

			
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
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				resp, HttpStatus.CREATED);
		System.out.println("response data is" + response);
		logger.info("Method : addinvPaymentnewdao ends");
		return response;
	}
}
