package nirmalya.aatithya.restmodule.audit.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
@Repository
public class RestAuditSupplementaryDao {

	Logger logger = LoggerFactory.getLogger(RestAuditSupplementaryDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;
	
	/**
	 * DAO - Get Audit Type For Drop Down
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getSupplementaryTypeDashboard() {
		logger.info("Method : getSupplementaryTypeDashboard starts");
		
		List<DropDownModel> auditType = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditSupplementaryRoutines")
					.setParameter("actionType", "supplType").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				auditType.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : getSupplementaryTypeDashboard ends");
		return auditType;
	}
}
