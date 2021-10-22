package nirmalya.aatithya.restmodule.asset.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.asset.model.RestAssetGraphModel;
import nirmalya.aatithya.restmodule.account.model.DataSetAccountTree;
import nirmalya.aatithya.restmodule.asset.model.AssetJobCardModel;
import nirmalya.aatithya.restmodule.asset.model.AssetMaintenanceHistoryModel;
import nirmalya.aatithya.restmodule.asset.model.AssetWithPOInventoryModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DataTableRequest;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.procurment.model.ProcurementDashboardModel;
import nirmalya.aatithya.restmodule.procurment.model.RestProcurementStockDetailsModel;

/**
 * @author Nirmalya Labs
 *
 */
@Repository
public class AssetDashboardDao {

	Logger logger = LoggerFactory.getLogger(AssetDashboardDao.class);
	
	@Autowired
	ServerDao serverDao;
	
	@Autowired
	EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	/*
	 * asset list
	 * 
	 */
	public List<DropDownModel> totalAsset() {
		logger.info("Method : getDeptList starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("assetDashBoard")
					.setParameter("actionType", "totalAsset").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Dept ==="+dept);
		logger.info("Method : getDeptList ends");

		return dept;
	}
	
	/*
	 * asset list
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> totalAssetMentaince() {
		logger.info("Method : totalAssetMentaince starts");
		
		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("assetDashBoard")
					.setParameter("actionType", "totalMaintenance").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),null);
				dept.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Dept ==="+dept);
		logger.info("Method : totalAssetMentaince ends");
		
		return dept;
	}
	/*
	 * view Asset Category
	 */
	

		@SuppressWarnings("unchecked")
		public List<RestAssetCodeModel> viewAssetCategory() {

			logger.info("Method : viewAssetCategory Dao starts");

			List<RestAssetCodeModel> viewAssetCategory = new ArrayList<RestAssetCodeModel>();
		
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("assetDashBoard")
						.setParameter("actionType", "viewAssetCategory").setParameter("actionValue", "").getResultList();

				for (Object[] m : x) {

					
					Object DATE = null;
					if (m[3] != null) {
						DATE = m[3].toString();
					}
					
					
					RestAssetCodeModel viewasset = new RestAssetCodeModel(m[0],m[1],m[2],DATE,m[4],m[5],m[6]);
					viewAssetCategory.add(viewasset);
				

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("AAAAAA" + viewAssetCategory);
			logger.info("Method : viewAssetCategory Dao ends");

			return viewAssetCategory;
		}
		/*
		 * Asset Report
		 */
		
		@SuppressWarnings("unchecked")
		public List<RestAssetCodeModel> restAssetServiceType() {
			logger.info("Method : stock starts");

			List<RestAssetCodeModel> response = new ArrayList<RestAssetCodeModel>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("assetDashBoard")
						.setParameter("actionType", "assetService").setParameter("actionValue", "").getResultList();

				for (Object[] m : x) {
					Object mainDate = null;
					if (m[2] != null) {
						mainDate = m[2].toString();
					}
					Object nextDate = null;
					if (m[3] != null) {
						nextDate = m[3].toString();
					}

					RestAssetCodeModel purchase = new RestAssetCodeModel(m[0],m[1].toString(),mainDate,nextDate,m[4],m[5],m[6],null);

					response.add(purchase);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : stock ends");
			return response;
		}
		
		/*
		 * Asset Report For Graph
		 */
		@SuppressWarnings("unchecked")
		public List<RestAssetGraphModel> getreportForGraph() {
			logger.info("Method : getrequisitionData  starts");

			List<RestAssetGraphModel> requisitionData = new ArrayList<RestAssetGraphModel>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("assetDashBoard")
						.setParameter("actionType", "getreportForGraph").setParameter("actionValue", "").getResultList();

				for (Object[] m : x) {
					RestAssetGraphModel dropDownModel = new RestAssetGraphModel(m[0], m[1], m[2],
							m[3]);
					requisitionData.add(dropDownModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : getrequisitionData ends");
			System.out.println(requisitionData);
			return requisitionData;
		}

}
