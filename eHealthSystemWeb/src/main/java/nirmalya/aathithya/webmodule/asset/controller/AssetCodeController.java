package nirmalya.aathithya.webmodule.asset.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nirmalya.aathithya.webmodule.asset.model.AssetCodeModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.AssetClassificationModel;
import nirmalya.aathithya.webmodule.master.model.ProductCategoryModel;

import nirmalya.aathithya.webmodule.procurment.model.InventoryRequisitionModel;
import nirmalya.aathithya.webmodule.procurment.model.InventorySkuProductModel;


@Controller
@RequestMapping(value = "asset/")
public class AssetCodeController {

	Logger logger = LoggerFactory.getLogger(AssetCodeController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("asset-code")
	public String assetCode(Model model, HttpSession session) {
		logger.info("Method : assetCode starts");

		try {
			DropDownModel[] store = restClient.getForObject(env.getAssetUrl() + "getstorelist",
					DropDownModel[].class);
			List<DropDownModel> storelist = Arrays.asList(store);
			model.addAttribute("storelist", storelist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		logger.info("Method : assetCode ends");
		return "asset/asset-code";
	}

	// view

	@SuppressWarnings("unchecked")
	@GetMapping("asset-code-view")
	public @ResponseBody List<AssetCodeModel> viewAssetCode(HttpSession session) {

		logger.info("Method : viewAssetCode starts");

		JsonResponse<List<AssetCodeModel>> resp = new JsonResponse<List<AssetCodeModel>>();

		try {
			resp = restClient.getForObject(env.getAssetUrl() + "viewassetcode", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<AssetCodeModel> asset = mapper.convertValue(resp.getBody(), new TypeReference<List<AssetCodeModel>>() {
		});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (AssetCodeModel a : asset) {
			if (a.getDateOfPurchase() != null && a.getDateOfPurchase() != "") {
				a.setDateOfPurchase(DateFormatter.dateFormat(a.getDateOfPurchase(), dateFormat));
			}
		}

		//System.out.println(asset);
		logger.info("Method : viewAssetCode ends");

		return asset;
	}
	
	/**
	 * Web Controller - Get Item List By AutoSearch
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "asset-code-get-product-list" })
	public @ResponseBody JsonResponse<AssetCodeModel> ItemAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : ItemAutoSearchList starts");
		//System.out.println("searchValue=="+searchValue);
		JsonResponse<AssetCodeModel> res = new JsonResponse<AssetCodeModel>();

		try {
			res = restClient.getForObject(env.getAssetUrl() + "getitemListByAutoSearch?id=" + searchValue,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
			//System.out.println(res);
		logger.info("Method : ItemAutoSearchList ends");
		return res;
		
	}
	
	// add

		@SuppressWarnings("unchecked")
		@PostMapping("asset-code-add")
		public @ResponseBody JsonResponse<Object> addAssetCode(@RequestBody AssetCodeModel asset, HttpSession session) {

			logger.info("Method : addAssetCode starts");
			//System.out.println("Addddd asset=="+asset);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			
			String userId = "";
			String dateFormat = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				//System.out.println("CReatedBYYYYYY"+userId);
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			
			asset.setCreatedBy(userId);
			
			 
			if (asset.getDateOfPurchase() != null && asset.getDateOfPurchase() != "") {
				asset.setDateOfPurchase(DateFormatter.inputDateFormat(asset.getDateOfPurchase(), dateFormat));
			}
			

			//System.out.println(asset);
			//JsonResponse<Object> resp = new JsonResponse<Object>();

			try {
				resp = restClient.postForObject(env.getAssetUrl() + "addAssetCode", asset, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			if (resp.getMessage() != "" && resp.getMessage() != null) {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}

			//System.out.println(resp);
			logger.info("Method : addAssetCode ends");

			return resp;
		}
		
		//get Product Category Data List Modal
		
		@SuppressWarnings("unchecked")
		@PostMapping("asset-code-get-product-cat-list")
		public @ResponseBody JsonResponse<List<ProductCategoryModel>> getProductCategoryListModal(@RequestBody String yearDtls, HttpSession session) {
			logger.info("Method : getProductCategoryListModal starts");
			
			JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();
			
			try {
				resp = restClient.getForObject(env.getInventoryUrl() + "getProductCategoryDataListModal",
						JsonResponse.class);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if (resp.getMessage() != null && resp.getMessage() != "") {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("success");
			}
			
			logger.info("Method : getProductCategoryListModal starts");
			return resp;
		}
		
		//grt product by cat
		
		@SuppressWarnings("unchecked")
		@PostMapping(value = { "asset-code-get-product-by-cat" })
		public @ResponseBody JsonResponse<InventorySkuProductModel> getProduct(Model model, @RequestBody String index,
				BindingResult result) {
			logger.info("Method : getProductData starts");
			String indexValue=index.substring(0, index.length() - 1);
			
			JsonResponse<InventorySkuProductModel> res = new JsonResponse<InventorySkuProductModel>();

			try {
				res = restClient.getForObject(env.getAssetUrl() + "getProductsByCat?id=" + indexValue,
						JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (res.getMessage() != null) {

				res.setCode(res.getMessage());
				res.setMessage("Unsuccess");
			} else {
				res.setMessage("success");
			}
			//System.out.println(res);
			logger.info("Method : getProductData  ends");
			return res;
		}
	
		//delete
		
		@SuppressWarnings("unchecked")
		@GetMapping("asset-code-delete")
		public @ResponseBody JsonResponse<AssetCodeModel> deleteAssetCode(@RequestParam String deleteId) {

			logger.info("Method : deleteAssetCode starts");
			//System.out.println("@@@@@" + deleteId);
			JsonResponse<AssetCodeModel> response = new JsonResponse<AssetCodeModel>();

			try {
				response = restClient.getForObject(env.getAssetUrl() + "deleteAssetCode?id=" + deleteId,
						JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}

			if (response.getMessage() != null && response.getMessage() != "") {
				response.setCode(response.getMessage());
				response.setMessage("Unsuccess");

			} else {
				response.setMessage("Success");
			}

			// System.out.println("##@@@@"+response);
			logger.info("Method : deleteAssetCode ends");
			return response;
		}
		
	//edit

	@SuppressWarnings("unchecked")
	@GetMapping("asset-code-edit")
	public @ResponseBody JsonResponse<AssetCodeModel> editAssetCode(@RequestParam String id, HttpSession session) {

		logger.info("Method : editAssetCode starts");

		JsonResponse<AssetCodeModel> response = new JsonResponse<AssetCodeModel>();
		try {
			response = restClient.getForObject(env.getAssetUrl() + "editassetcode?id=" + id, JsonResponse.class);

		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		AssetCodeModel studentModel = mapper.convertValue(response.getBody(), new TypeReference<AssetCodeModel>() {
		});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		if (studentModel.getDateOfPurchase() != null && studentModel.getDateOfPurchase() != "") {
			studentModel.setDateOfPurchase(DateFormatter.dateFormat(studentModel.getDateOfPurchase(), dateFormat));
		}
		response.setBody(studentModel);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		// System.out.println("##@@@@"+response);
		logger.info("Method : editAssetCode ends");
		return response;
	}
	
	
	  //classification
	  
		@SuppressWarnings("unchecked")
		@PostMapping("asset-code-classification-view")
		public @ResponseBody JsonResponse<List<AssetClassificationModel>> getAllAssetList(
				HttpSession session) {
			logger.info("Method : getAllAssetList ends");

			JsonResponse<List<AssetClassificationModel>> resp = new JsonResponse<List<AssetClassificationModel>>();

			try {

				resp = restClient.getForObject(env.getAssetUrl() + "getAllAssetsList", JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			if (resp.getMessage() != null && resp.getMessage() != "") {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("success");
			}
			//System.out.println("Webbbb==="+resp);
			logger.info("Method : getAllAssetList starts");
			return resp;
		}

}
