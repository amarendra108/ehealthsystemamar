package nirmalya.aatithya.restmodule.procurment.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.procurment.model.InventoryPoModel;

/**
 * @author NirmalyaLabs
 *
 */
@Repository
public class InventoryVendorPoDao {
	Logger logger = LoggerFactory.getLogger(InventoryVendorPoDao.class);
	@Autowired
	ServerDao serverDao;
	@Autowired
	private EntityManager entityManager;

	/**
	 * for view po list
	 */
	@SuppressWarnings("unchecked")
	public List<InventoryPoModel> getVendorPoViewList(String userId) {
		logger.info("Method : getVendorPoViewList starts");
		List<InventoryPoModel> inventoryPoModelList = new ArrayList<InventoryPoModel>();
		String value = "SET @p_VendorId='"+userId +"';";
		try {
			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("vendorPoRoutines")
					.setParameter("actionType", "getVendorPoViewList").setParameter("actionValue", value).getResultList();
 
			for (Object[] m : x) {
				Object oa = null;
				if (m[13] != null) {
					oa =  m[13].toString();
				}
				Object startDate = null;
				if (m[14] != null) {
					startDate =m[14].toString();
				}
				Object expireDate = null;
				if (m[15] != null) {
					expireDate = m[15].toString();
				}
				Object createdon = null;
				if (m[20] != null) {
					createdon = m[20].toString();
				}
				Object approveDate = null;
				if (m[21] != null) {
					approveDate = m[21].toString();
				}
				Object onHoldDate = null;
				if (m[22] != null) {
					onHoldDate = m[22].toString();
				}
				Object completeDate = null;
				if (m[23] != null) {
					completeDate = m[23].toString();
				}
				InventoryPoModel inventoryPoModel = new InventoryPoModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], oa, startDate, expireDate, m[16], m[17], m[18], m[19],
						createdon, approveDate, completeDate, onHoldDate, m[24], m[25], m[26]);

				if (inventoryPoModel.getApproveStatus().contentEquals("1")) {
					inventoryPoModel.setApproveStatus("Approve");
				} else if (inventoryPoModel.getApproveStatus().contentEquals("0")) {
					inventoryPoModel.setApproveStatus("Active");
				} else if (inventoryPoModel.getApproveStatus().contentEquals("2")) {
					inventoryPoModel.setApproveStatus("Pending");
				} else if (inventoryPoModel.getApproveStatus().contentEquals("3")) {
					inventoryPoModel.setApproveStatus("Rejected ");
				}
				inventoryPoModelList.add(inventoryPoModel);	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getVendorPoViewList ends");
		return inventoryPoModelList;
	}
	
	
}
