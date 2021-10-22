package nirmalya.aatithya.restmodule.ticket.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.ticket.model.TicketDashboardRestModel;

@Repository
public class TicketDashboardDao {
	Logger logger = LoggerFactory.getLogger(TicketDashboardDao.class);
	@Autowired
	ServerDao serverDao;
	@Autowired
	EntityManager em;
	@SuppressWarnings("unchecked")
	/*
	 * Ticket list
	 * 
	 */
	public List<DropDownModel> totalTicket() {
		logger.info("Method : totalTicket starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "totalTicket").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : totalTicket ends");

		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * general ticket list
	 * 
	 */
	public List<DropDownModel> generalTicket() {
		logger.info("Method : generalTicket starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "generalTicket").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : generalTicket ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Service Ticket list
	 * 
	 */
	public List<DropDownModel> serviceTicket() {
		logger.info("Method : serviceTicket starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "serviceTicket").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : serviceTicket ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Complain Ticket list
	 * 
	 */
	public List<DropDownModel> complainTicket() {
		logger.info("Method : complainTicket starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "complainTicket").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : complainTicket ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Sales Ticket list
	 * 
	 */
	public List<DropDownModel> salesTicket() {
		logger.info("Method : salesTicket starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "salesTicket").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : salesTicket ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Market Ticket list
	 * 
	 */
	public List<DropDownModel> marketTicket() {
		logger.info("Method : marketTicket starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "marketTicket").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : marketTicket ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * general ticket Table
	 * 
	 */
	public List<DropDownModel> generalTicketTable() {
		logger.info("Method : generalTicketTable starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "generalTicketTable").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1].toString());
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//System.out.println("Dept ==="+dept);
		logger.info("Method : generalTicketTable ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Service Ticket Table
	 * 
	 */
	public List<DropDownModel> serviceTicketTable() {
		logger.info("Method : serviceTicketTable starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "serviceTicketTable").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1].toString());
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	System.out.println("Dept ==="+dept);
		logger.info("Method : serviceTicketTable ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Complain Ticket Table
	 * 
	 */
	public List<DropDownModel> complainTicketTable() {
		logger.info("Method : complainTicketTable starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "complainTicketTable").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1].toString());
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : complainTicketTable ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Sales Ticket Table
	 * 
	 */
	public List<DropDownModel> salesTicketTable() {
		logger.info("Method : salesTicketTable starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "salesTicketTable").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1].toString());
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : salesTicketTable ends");
		
		return dept;
	}
	@SuppressWarnings("unchecked")
	/*
	 * Market Ticket Table
	 * 
	 */
	public List<DropDownModel> marketTicketTable() {
		logger.info("Method : marketTicketTable starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "marketTicketTable").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1].toString());
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Dept ==="+dept);
		logger.info("Method : marketTicketTable ends");
		
		return dept;
	}
	/*
	 * General ticket For Graph
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDashboardRestModel> getGeneralTicketPie() {
		logger.info("Method : getGeneralTicketPie  starts");

		List<TicketDashboardRestModel> requisitionData = new ArrayList<TicketDashboardRestModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "getGeneralTicketPie").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				TicketDashboardRestModel dropDownModel = new TicketDashboardRestModel(m[0], m[1], m[2],
						m[3], m[4]);
				requisitionData.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getGeneralTicketPie ends");
		//System.out.println("getGeneralTicketPie"+requisitionData);
		return requisitionData;
	}
	/*
	 * Service ticket For Graph
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDashboardRestModel> getServicePieChart() {
		logger.info("Method : getServicePieChart  starts");
		
		List<TicketDashboardRestModel> requisitionData = new ArrayList<TicketDashboardRestModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "getServicePie").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				TicketDashboardRestModel dropDownModel = new TicketDashboardRestModel(m[0], m[1], m[2],
						m[3], m[4]);
				requisitionData.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getServicePieChart ends");
		//System.out.println("getServicePieChart"+requisitionData);
		return requisitionData;
	}
	/*
	 * Complaints ticket For Graph
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDashboardRestModel> getComplaintsPieChart() {
		logger.info("Method : getComplaintsPieChart  starts");
		
		List<TicketDashboardRestModel> requisitionData = new ArrayList<TicketDashboardRestModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "getComplaintsPie").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				TicketDashboardRestModel dropDownModel = new TicketDashboardRestModel(m[0], m[1], m[2],
						m[3], m[4]);
				requisitionData.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getComplaintsPieChart ends");
		//System.out.println("getComplaintsPieChart"+requisitionData);
		return requisitionData;
	}
	/*
	 * Sales ticket For Graph
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDashboardRestModel> getSalesPieChart() {
		logger.info("Method : getSalesPieChart  starts");
		
		List<TicketDashboardRestModel> requisitionData = new ArrayList<TicketDashboardRestModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "getSalesPie").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				TicketDashboardRestModel dropDownModel = new TicketDashboardRestModel(m[0], m[1], m[2],
						m[3], m[4]);
				requisitionData.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getSalesPieChart ends");
		//System.out.println("getSalesPieChart"+requisitionData);
		return requisitionData;
	}
	/*
	 * Marketing ticket For Graph
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDashboardRestModel> getmarketPieChart() {
		logger.info("Method : getmarketPieChart  starts");
		
		List<TicketDashboardRestModel> requisitionData = new ArrayList<TicketDashboardRestModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "getmarketPie").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				TicketDashboardRestModel dropDownModel = new TicketDashboardRestModel(m[0], m[1], m[2],
						m[3], m[4]);
				requisitionData.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getmarketPieChart ends");
		//System.out.println("getmarketPieChart"+requisitionData);
		return requisitionData;
	}
	/*
	 * Location wise customer ticket
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDashboardRestModel> getLocationCustomerCount() {
		logger.info("Method : getLocationCustomerCount  starts");
		Integer total1 = 0, total2 = 0, total3 = 0;
		List<TicketDashboardRestModel> requisitionData = new ArrayList<TicketDashboardRestModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("tickettDashBoard")
					.setParameter("actionType", "getLocationGraph").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				BigInteger t1 = (BigInteger) m[1];
				total1 = Integer.parseInt((t1.toString()));
				
				BigInteger t2 = (BigInteger) m[2];
				total2 = Integer.parseInt((t2.toString()));
				
				BigInteger t3 = (BigInteger) m[3];
				total3 = Integer.parseInt((t3.toString()));
				
				TicketDashboardRestModel dropDownModel = new TicketDashboardRestModel(m[0], total1,total2, total3);
				requisitionData.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getLocationCustomerCount ends");
		//System.out.println("getLocationCustomerCount"+requisitionData);
		return requisitionData;
	}
}
