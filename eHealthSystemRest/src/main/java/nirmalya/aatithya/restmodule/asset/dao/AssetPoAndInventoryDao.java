package nirmalya.aatithya.restmodule.asset.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import nirmalya.aatithya.restmodule.asset.model.RestAssetPoAndInventoryModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class AssetPoAndInventoryDao {
	Logger logger = LoggerFactory.getLogger(AssetPoAndInventoryDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * //view asset PO And Inventory
	 */	
			@SuppressWarnings("unchecked")
			public JsonResponse<List<RestAssetPoAndInventoryModel>> viewAssetPo() {
				
				logger.info("Method : viewAssetPo Dao starts");

				List<RestAssetPoAndInventoryModel> assets = new ArrayList<RestAssetPoAndInventoryModel>();
				JsonResponse<List<RestAssetPoAndInventoryModel>> resp = new JsonResponse<List<RestAssetPoAndInventoryModel>>();

				try {

					List<Object[]> x = em.createNamedStoredProcedureQuery("AssetPoAndInventory")
							.setParameter("actionType", "viewAssetPoAndInventory").setParameter("actionValue", "").getResultList();

					for (Object[] m : x) {
						
						Object DATE = null;
						if (m[4] != null) {
							DATE = m[4].toString();
						}
						
						RestAssetPoAndInventoryModel asset = new RestAssetPoAndInventoryModel(null,m[0],m[1],m[2],null,m[3],
								DATE,null,null,null);
						
						assets.add(asset);
						
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.setBody(assets);
			
				logger.info("Method : viewAssetPo Dao ends");
				return resp;
			}
}
