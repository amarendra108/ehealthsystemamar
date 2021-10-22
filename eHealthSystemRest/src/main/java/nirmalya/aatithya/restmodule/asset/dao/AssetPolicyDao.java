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

import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.asset.model.RestAssetPolicyModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateAssetPolicyParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.dao.ProductMasterDao;
import nirmalya.aatithya.restmodule.master.model.ProductMasterModel;


@Repository
public class AssetPolicyDao {
	
	Logger logger = LoggerFactory.getLogger(AssetPolicyDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ProductMasterModel>>> getProductSKUListing() {
		logger.info("Method : getProductSKUListing starts");

		List<ProductMasterModel> locationList = new ArrayList<ProductMasterModel>();
		JsonResponse<List<ProductMasterModel>> resp = new JsonResponse<List<ProductMasterModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("productMasterRoutines")
					.setParameter("actionType", "getProductList").setParameter("actionValue", "").getResultList();
			for (Object[] m : x) {
			
				ProductMasterModel dropDownModel = new ProductMasterModel(m[0], m[1], m[2], m[3], null, null, m[4], null ,null, m[5],
						m[6], m[7], m[8], m[9], m[10], m[11].toString(), m[12], m[13], m[14]);
				locationList.add(dropDownModel);
			}
			resp.setBody(locationList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<ProductMasterModel>>> response = new ResponseEntity<JsonResponse<List<ProductMasterModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getProductSKUListing ends");
		return response;
	}
	
	//Service Type List
	@SuppressWarnings("unchecked")
	public List<DropDownModel> serviceTypeList() {
		logger.info("Method : serviceTypeList Dao starts");
		
		List<DropDownModel> serviceList = new ArrayList<DropDownModel>();
		
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("assetPolicyRoutines")
					.setParameter("actionType", "serviceTypeList").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				serviceList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("serviceList==="+serviceList);
		logger.info("Method : serviceTypeList Dao ends");
		
		return serviceList;
	}
	
	//frequency list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> frequencyList() {
		logger.info("Method : frequencyList Dao starts");
		
		List<DropDownModel> frequencyList = new ArrayList<DropDownModel>();
		
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("assetPolicyRoutines")
					.setParameter("actionType", "frequencyList").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				frequencyList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("frequencyList==="+frequencyList);
		logger.info("Method : frequencyList Dao ends");
		
		return frequencyList;
	}
	
	//add asset policy
	
	public JsonResponse<Object> addAssetPolicy(RestAssetPolicyModel policy) {

		System.out.println("######" + policy);
		logger.info("Method : addAssetPolicy starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateAssetPolicyParameter.getAssetPolicy(policy);
			System.out.println(values);

			if (policy.getPolicyId() == null) {

				em.createNamedStoredProcedureQuery("assetPolicyRoutines").setParameter("actionType", "addAssetPolicy")
						.setParameter("actionValue", values).execute();

			} else {

				em.createNamedStoredProcedureQuery("assetPolicyRoutines").setParameter("actionType", "modifyAssetPolicy")
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
		System.out.println("@@@@@" + resp);

		logger.info("Method : addAssetPolicy ends");
		return resp;
	}
	
	//view asset policy
	
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestAssetPolicyModel>> viewAssetPolicy() {
			
			logger.info("Method : viewAssetPolicy Dao starts");

			List<RestAssetPolicyModel> policy = new ArrayList<RestAssetPolicyModel>();
			JsonResponse<List<RestAssetPolicyModel>> resp = new JsonResponse<List<RestAssetPolicyModel>>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("assetPolicyRoutines")
						.setParameter("actionType", "viewAssetPolicy").setParameter("actionValue", "").getResultList();

				for (Object[] m : x) {
					//System.out.println(m[3]);
					RestAssetPolicyModel viewEmp = new RestAssetPolicyModel(m[0],null,m[1],m[2],m[3],m[4],
							null,null);
					policy.add(viewEmp);
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(policy);
			
			//System.out.println("AAAAAA"+resp);
			logger.info("Method : viewAssetPolicy Dao ends");
			
			return resp;
		}
		
		//edit asset Asset
		
		@SuppressWarnings("unchecked")
		public JsonResponse<RestAssetPolicyModel> editAssetPolicy(String id) {
			logger.info("Method : editAssetPolicy starts");

			RestAssetPolicyModel req = new RestAssetPolicyModel();
			JsonResponse<RestAssetPolicyModel> resp = new JsonResponse<RestAssetPolicyModel>();

			try {

				String value = "SET @p_policyId='" + id + "';";
				System.out.println("@@@@@"+value);

				List<Object[]> x = em.createNamedStoredProcedureQuery("assetPolicyRoutines")
						.setParameter("actionType", "editAssetPolicy").setParameter("actionValue", value).getResultList();

				for (Object[] m : x) {

					RestAssetPolicyModel reqEdit = new RestAssetPolicyModel(m[0],m[1],m[2],m[3],m[4],m[5],
							null,null);
					req=reqEdit;
					
					System.out.println("req====="+req);
				}

				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("resp====="+resp);
			logger.info("Method : editAssetPolicy ends");
			
			return resp;
		}
		
		// delete asset code

			public JsonResponse<RestAssetPolicyModel> deleteAssetPolicy(String id) {
			logger.info("Method : deleteAssetPolicy starts");

			RestAssetPolicyModel req = new RestAssetPolicyModel();
			JsonResponse<RestAssetPolicyModel> resp = new JsonResponse<RestAssetPolicyModel>();

			try {

				String value = "SET @p_policyId='(" + id + ")';";
				System.out.println("@@@@@" + value);

				em.createNamedStoredProcedureQuery("assetPolicyRoutines").setParameter("actionType", "deleteAssetPolicy")
						.setParameter("actionValue", value).execute();

				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("resp====" + resp);
			logger.info("Method : deleteAssetPolicy ends");

			return resp;
		}
		

}
