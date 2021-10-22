package nirmalya.aatithya.restmodule.asset.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import nirmalya.aatithya.restmodule.asset.model.RestAssetMaintenanceModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.GenerateAssetMaintenanceParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class AssetMaintenanceDao {
	
	Logger logger = LoggerFactory.getLogger(AssetMaintenanceDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * //view asset policy
	 */	
			@SuppressWarnings("unchecked")
			public JsonResponse<List<RestAssetMaintenanceModel>> viewAssetPolicy() {
				
				logger.info("Method : viewAssetPolicy Dao starts");

				List<RestAssetMaintenanceModel> policy = new ArrayList<RestAssetMaintenanceModel>();
				JsonResponse<List<RestAssetMaintenanceModel>> resp = new JsonResponse<List<RestAssetMaintenanceModel>>();

				try {

					List<Object[]> x = em.createNamedStoredProcedureQuery("AssetMaintenanceRoutines")
							.setParameter("actionType", "viewAssetMaintainance").setParameter("actionValue", "").getResultList();

					for (Object[] m : x) {
						
						RestAssetMaintenanceModel viewEmp = new RestAssetMaintenanceModel(null,m[0],null,m[1],m[2],m[3],m[4],
								null,null,null,null,null,null,null,m[5],m[6],m[7],m[8]);
						
						policy.add(viewEmp);
						
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.setBody(policy);
			
				logger.info("Method : viewAssetPolicy Dao ends");
				return resp;
			}
				
	/*
	 * //Auto search for asset list
	*/	
			
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestAssetMaintenanceModel>>> AssetAutoSearchList(String id) {
		
		logger.info("Method : AssetAutoSearchList starts");
	
		List<RestAssetMaintenanceModel> assetList = new ArrayList<RestAssetMaintenanceModel>();
		JsonResponse<List<RestAssetMaintenanceModel>> resp = new JsonResponse<List<RestAssetMaintenanceModel>>();
		
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetMaintenanceRoutines").setParameter("actionType", "getAssetList")
					.setParameter("actionValue", value).getResultList();
			
			for (Object[] m : x) {
				
				RestAssetMaintenanceModel dropDownModel = new RestAssetMaintenanceModel(null,null,null,null,null,null,null,
						null,null,m[0],m[1],null,null,null,null,null,null,null);
				assetList.add(dropDownModel);
			}
			
			//System.out.println(assetList);
			resp.setBody(assetList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestAssetMaintenanceModel>>> response = new ResponseEntity<JsonResponse<List<RestAssetMaintenanceModel>>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : AssetAutoSearchList ends");
		return response;
	}
	
	
	/*
	 * //add asset Maintenance Details
	 */
	public JsonResponse<Object> addAssetMaintenance(RestAssetMaintenanceModel asset) {
		
		logger.info("Method : addAssetMaintenance starts");
	
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
			try {
				String values = GenerateAssetMaintenanceParameter.getAddassetMaintenanceParam(asset);

				if (asset.getMaintenanceId() == null) {
					
					em.createNamedStoredProcedureQuery("AssetMaintenanceRoutines").setParameter("actionType", "addAssetMaintenance")
							.setParameter("actionValue", values).execute();
					
				} else {
					
					em.createNamedStoredProcedureQuery("AssetMaintenanceRoutines").setParameter("actionType", "modifyAssetMaintenance")
							.setParameter("actionValue", values).execute();
				}
			} catch

			(Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					System.out.println(err);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		
		logger.info("Method : addAssetMaintenance ends");
		return resp;
	}
	
}
