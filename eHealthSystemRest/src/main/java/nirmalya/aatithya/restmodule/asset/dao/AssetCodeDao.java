package nirmalya.aatithya.restmodule.asset.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.ObjectMapper;



import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateAssetCodeParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateRequisitionParam;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.model.ProductCategoryModel;
import nirmalya.aatithya.restmodule.master.model.RestAssetModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRequisitionModel;
import nirmalya.aatithya.restmodule.procurment.model.InventorySkuProductModel;

@Repository
public class AssetCodeDao {
Logger logger = LoggerFactory.getLogger(AssetCodeDao.class);
	
	@Autowired
	ServerDao serverDao;
	
	@Autowired
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> storeList() {
		logger.info("Method : storeList Dao starts");
		
		List<DropDownModel> storeList = new ArrayList<DropDownModel>();
		
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "storeList").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				storeList.add(dropDownModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(storeList);
		logger.info("Method : storeList Dao ends");
		
		return storeList;
	}
	

	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAssetCodeModel>> viewAssetCode() {

		logger.info("Method : viewAssetCode Dao starts");

		List<RestAssetCodeModel> viewAssetCode = new ArrayList<RestAssetCodeModel>();
		JsonResponse<List<RestAssetCodeModel>> resp = new JsonResponse<List<RestAssetCodeModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "viewAssetCode").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				
				Object DATE = null;
				if (m[5] != null) {
					DATE = m[5].toString();
				}
				
				
				RestAssetCodeModel viewasset = new RestAssetCodeModel(m[0],null, m[1], m[2],m[3], m[4], DATE,m[6],null,m[7],
						m[8], m[9],null, m[10],null, m[11], m[12], null,null,null,null,null,null,null,null,null,null,null,null);
				viewAssetCode.add(viewasset);
			

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(viewAssetCode);

		//System.out.println("AAAAAA" + resp);
		logger.info("Method : viewAssetCode Dao ends");

		return resp;
	}
	/**
	 * DAO Function for auto complete list
	 * 
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestAssetCodeModel>>> ItemAutoSearchList(String id) {
		logger.info("Method : ItemAutoSearchList starts");
		List<RestAssetCodeModel> itemNameList = new ArrayList<RestAssetCodeModel>();
		JsonResponse<List<RestAssetCodeModel>> resp = new JsonResponse<List<RestAssetCodeModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "getitemList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				RestAssetCodeModel dropDownModel = new RestAssetCodeModel(null,m[0], null,m[1],m[2], null,null,null,m[3],m[4],
						null,null,null,null,null,null,null, null,null,null,null,null,m[5],m[6],null,null,null,null,null);
				itemNameList.add(dropDownModel);
			}
				//System.out.println(itemNameList);
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestAssetCodeModel>>> response = new ResponseEntity<JsonResponse<List<RestAssetCodeModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : ItemAutoSearchList ends");
		return response;
	}
	
	//add				
	
	public JsonResponse<Object> addAssetCode(RestAssetCodeModel asset) {
		
		
		logger.info("Method : addAssetCode starts");
		//System.out.println("######"+asset);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
			try {
				String values = GenerateAssetCodeParameter.getAddassetCodeParam(asset);
				//System.out.println(values);

				if (asset.getAssetCode() == null || asset.getAssetCode() == "") {
					
					em.createNamedStoredProcedureQuery("AssetCode").setParameter("actionType", "addassetCode")
							.setParameter("actionValue", values).execute();
					
				} else {
					
					em.createNamedStoredProcedureQuery("AssetCode").setParameter("actionType", "modifyAssetCode")
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
			 
			//System.out.println("@@@@@"+resp);

		
		logger.info("Method : addAssetCode ends");
		return resp;
	}
	
	//get Product Category List Modal
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ProductCategoryModel>>> getProductCategoryListModal() {
		logger.info("Method : getProductCategoryListModal starts");
		
		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();
		List<ProductCategoryModel> yearList = new ArrayList<ProductCategoryModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "getProductCategoryList").setParameter("actionValue", "").getResultList();
			for (Object[] m : x) {
				
				ProductCategoryModel item = new ProductCategoryModel(m[0], m[1], m[2], m[3], m[4],m[5]);
				yearList.add(item);
				
				//System.out.println("yearList===="+yearList);
			}
			resp.setBody(yearList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<List<ProductCategoryModel>>> response = new ResponseEntity<JsonResponse<List<ProductCategoryModel>>>(
				resp, HttpStatus.CREATED);
		
		
		
		logger.info("Method : getProductCategoryListModal ends");
		return response;
	}
	
	//get product by cat
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> getProductsByCat(String catId) {
		//System.out.println("catId"+catId);
		logger.info("Method : getProductByCat starts");

		List<InventorySkuProductModel> productList = new ArrayList<InventorySkuProductModel>();
		JsonResponse<List<InventorySkuProductModel>> resp = new JsonResponse<List<InventorySkuProductModel>>();
		try {

			String value = GenerateRequisitionParam.getProduct(catId);
			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "getProductByCat").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				InventorySkuProductModel dropDownModel = new InventorySkuProductModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6]);
				productList.add(dropDownModel);
			}
			
			resp.setBody(productList);
			//System.out.println(resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> response = new ResponseEntity<JsonResponse<List<InventorySkuProductModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getProductByCat ends");
		return response;
	}
	
	
	
	//delete
	
	//@SuppressWarnings("unchecked")
	public JsonResponse<RestAssetCodeModel> deleteAssetCode(String id) {
		logger.info("Method : deleteAssetCode starts");

		RestAssetCodeModel req = new RestAssetCodeModel();
		JsonResponse<RestAssetCodeModel> resp = new JsonResponse<RestAssetCodeModel>();

		try {

			String value = "SET @p_assetCodeId='(" + id + ")';";
			System.out.println("@@@@@"+value);

			 em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "deleteAssetCode").setParameter("actionValue", value).execute();

			

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		logger.info("Method : deleteAssetCode ends");
		
		return resp;
	}
	
	//edit
	@SuppressWarnings("unchecked")
	public JsonResponse<RestAssetCodeModel> editAssetCode(String id) {
		logger.info("Method : editAssetCode starts");

		RestAssetCodeModel req = new RestAssetCodeModel();
		JsonResponse<RestAssetCodeModel> resp = new JsonResponse<RestAssetCodeModel>();

		try {

			String value = "SET @p_assetCodeId='" + id + "';";
			//System.out.println("@@@@@"+value);

			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "editAssetCode").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				
				Object DATE = null;
				if (m[6] != null) {
					DATE =m[6].toString(); 
				}
				

				RestAssetCodeModel reqEdit = new RestAssetCodeModel(m[0],m[1],m[2],m[3],m[4],m[5],DATE,m[7],
						 m[8],m[9], m[10], m[11],m[12],m[13],m[14],m[15],m[16],
						m[17],m[18],m[19],m[20],m[21],m[22],m[23],m[24],m[25],m[26],m[27],null);
				req=reqEdit;

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//System.out.println("DaOOOOO"+resp);
		logger.info("Method : editAssetCode ends");
		
		return resp;
	}
	
	//get asset classification list
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestAssetModel>>> getAllAssetsList() {
		logger.info("Method : getAllAssetsList starts");

		JsonResponse<List<RestAssetModel>> resp = new JsonResponse<List<RestAssetModel>>();
		List<RestAssetModel> newLoc = new ArrayList<RestAssetModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("AssetCode")
					.setParameter("actionType", "getAssetClassification").setParameter("actionValue", "").getResultList();
			for (Object[] m : x) {

				RestAssetModel item = new RestAssetModel(m[0], m[1], null, null, m[2], null, m[3], m[4], m[5]);
				newLoc.add(item);
			}
			//System.out.println("AssetClassification==========="+newLoc);
			resp.setBody(newLoc);
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

		ResponseEntity<JsonResponse<List<RestAssetModel>>> response = new ResponseEntity<JsonResponse<List<RestAssetModel>>>(
				resp, HttpStatus.CREATED);
		//System.out.println("AssetClassification==========="+response);
		logger.info("Method : getAllAssetsList ends");
		return response;
	}

}
