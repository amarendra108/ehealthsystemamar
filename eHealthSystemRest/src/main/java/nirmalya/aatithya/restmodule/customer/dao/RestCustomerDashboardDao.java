package nirmalya.aatithya.restmodule.customer.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.asset.model.RestAssetGraphModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.customer.model.RestCustomerGraphModel;
import nirmalya.aatithya.restmodule.customer.model.RestCustomerStoreGraphModel;
@Repository
public class RestCustomerDashboardDao {
	Logger logger = LoggerFactory.getLogger(RestCustomerDashboardDao.class);
	
	@Autowired
	ServerDao serverDao;
	
	@Autowired
	EntityManager em;
	@SuppressWarnings("unchecked")
	/*
	 * Quotions Count
	 * 
	 */
	public List<DropDownModel> totalQuotions() {
		logger.info("Method : totalQuotions starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
					.setParameter("actionType", "totalQuotions").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : totalQuotions ends");

		return dept;
	}
	/*
	 * Total Sales Order Count
	 * 
	 */
	public List<DropDownModel> totalSalesorder() {
		logger.info("Method : totalSalesorder starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
					.setParameter("actionType", "totalOrder").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : totalSalesorder ends");
		
		return dept;
	}
	/*
	 * Total Customer Count
	 * 
	 */
	public List<DropDownModel> totalCustomer() {
			logger.info("Method : totalCustomer dao starts");
			
			List<DropDownModel> dept = new ArrayList<DropDownModel>();
			
			try {
				@SuppressWarnings("unchecked")
				List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
				.setParameter("actionType", "totalCustomer").setParameter("actionValue", "").getResultList();
				
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
					dept.add(dropDownModel);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		//	System.out.println("Dept ==="+dept);
			logger.info("Method : totalCustomer dao ends");
			
			return dept;
		}
	
	/*
	 * Total Sales Invoice Count
	 * 
	 */
	public List<DropDownModel> totalSalesInvoice() {
		logger.info("Method : totalSalesInvoice dao starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
			.setParameter("actionType", "totalSalesInvoice").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	System.out.println("Dept ==="+dept);
		logger.info("Method : totalSalesInvoice  dao ends");
		
		return dept;
	}
	/*
	 * Asset Report For Graph
	 */
	@SuppressWarnings("unchecked")
	public List<RestCustomerGraphModel> customerGraph() {
		logger.info("Method : customerGraph dao  starts");

		List<RestCustomerGraphModel> requisitionData = new ArrayList<RestCustomerGraphModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
					.setParameter("actionType", "getCustomerGraph").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				RestCustomerGraphModel dropDownModel = new RestCustomerGraphModel(m[0], m[1], m[2]);
				requisitionData.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : customerGraph dao  ends");
		//System.out.println(requisitionData);
		return requisitionData;
	}
	/*
	 * Asset Report For Graph
	 */
	@SuppressWarnings("unchecked")
	public List<RestCustomerGraphModel> customerStoreGraph() {
		logger.info("Method : customerStoreGraph dao  starts");
		
		List<RestCustomerGraphModel> requisitionData = new ArrayList<RestCustomerGraphModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
					.setParameter("actionType", "customerStoreGraph").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				RestCustomerGraphModel dropDownModel = new RestCustomerGraphModel(m[0], m[1], m[2], null);
				requisitionData.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : customerStoreGraph dao  ends");
	//	System.out.println(requisitionData);
		return requisitionData;
	}
	/*
	 * Store report
	 */
	
	@SuppressWarnings("unchecked")
	public List<RestCustomerStoreGraphModel> restcustomerStore() {
		logger.info("Method : restcustomerStore dao starts");

		List<RestCustomerStoreGraphModel> response = new ArrayList<RestCustomerStoreGraphModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
					.setParameter("actionType", "customerStore").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				

				RestCustomerStoreGraphModel purchase = new RestCustomerStoreGraphModel(m[0], m[1], m[2]);

				response.add(purchase);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : restcustomerStore dao ends");
		return response;
	}
	/*
	 * Customer report
	 */
	
	@SuppressWarnings("unchecked")
	public List<RestCustomerGraphModel> restcustomer() {
		logger.info("Method : restcustomer dao starts");
		Integer total1 = 0;
	
		List<RestCustomerGraphModel> response = new ArrayList<RestCustomerGraphModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("customerDashBoard")
					.setParameter("actionType", "customer").setParameter("actionValue", "").getResultList();
			//System.out.println("x123"+x);
			for (Object[] m : x) {
				
				BigInteger t1 = (BigInteger) m[2];
				total1 = Integer.parseInt((t1.toString()));
				
				
				RestCustomerGraphModel purchase = new RestCustomerGraphModel(m[0], m[1], total1, null, null);
				
				response.add(purchase);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	System.out.println("x123"+response);
		logger.info("Method : restcustomer dao ends");
		return response;
	}
}
