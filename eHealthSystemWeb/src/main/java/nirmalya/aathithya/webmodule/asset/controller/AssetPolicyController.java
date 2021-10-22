
package nirmalya.aathithya.webmodule.asset.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import nirmalya.aathithya.webmodule.asset.model.AssetPolicyModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.common.utils.NumberFormatter;
import nirmalya.aathithya.webmodule.master.model.ProductMasterModel;


@Controller

@RequestMapping(value = "asset/")
public class AssetPolicyController {

	Logger logger = LoggerFactory.getLogger(AssetPolicyController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("asset-policy")
	public String assetPolicy(Model model, HttpSession session) {
		logger.info("Method : assetPolicy starts");

		//service type list
		try {
			DropDownModel[] serviceType = restClient.getForObject(env.getAssetUrl() + "getSeviceTypelist",
					DropDownModel[].class);
			List<DropDownModel> servicelist = Arrays.asList(serviceType);
			model.addAttribute("servicelist", servicelist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		//frequency list
		try {
			DropDownModel[] frequency = restClient.getForObject(env.getAssetUrl() + "getFrequencylist",
					DropDownModel[].class);
			List<DropDownModel> frequencylist = Arrays.asList(frequency);
			model.addAttribute("frequencylist", frequencylist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		logger.info("Method : assetPolicy ends");
		return "asset/asset-policy";
	}
	
	//view product list

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "asset-policy-product-list" })
	public @ResponseBody List<ProductMasterModel> getProductSKUListing(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("Method : getProductSKUListing starts");
		
		JsonResponse<List<ProductMasterModel>> res = new JsonResponse<List<ProductMasterModel>>();
		
		try {
			res = restClient.getForObject(env.getMasterUrl() + "getProductSKUListing",
					JsonResponse.class);
			
			ObjectMapper mapper = new ObjectMapper();

			List<ProductMasterModel> product = mapper.convertValue(res.getBody(),
					new TypeReference<List<ProductMasterModel>>() {
					});
			
			String dateFormat = "";
			
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for(ProductMasterModel m : product) {
				
				if(m.getProductStatus().contentEquals("1")) {
					m.setProductStatus("Active");
				} else {
					m.setProductStatus("Inactive");
				}
				
				m.setCreatedDate(DateFormatter.dateFormat(m.getCreatedDate(), dateFormat));
				
			    m.setpPrice(NumberFormatter.doubleToStringWithComma(m.getPurchasePrice()));
			    m.setsPrice(NumberFormatter.doubleToStringWithComma(m.getSalePrice()));
			}
			
			res.setBody(product);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		
		logger.info("Method : getProductSKUListing ends");
		return res.getBody();
		
	}
	
	//add asset policy
	@SuppressWarnings("unchecked")
	@PostMapping("asset-policy-add")
	public @ResponseBody JsonResponse<Object> addAssetPolicy(@RequestBody AssetPolicyModel policy, HttpSession session) {

		logger.info("Method : addAssetPolicy starts");
		System.out.println(policy);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			System.out.println("CReatedBYYYYYY"+userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		policy.setCreatedBy(userId);
		
		try {
			resp = restClient.postForObject(env.getAssetUrl() + "addassetpolicy", policy, JsonResponse.class);
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
		logger.info("Method : addAssetPolicy ends");

		return resp;
	} 
	
	// view asset policy

		@SuppressWarnings("unchecked")
		@GetMapping("asset-policy-view")
		public @ResponseBody List<AssetPolicyModel> viewAssetPolicy() {

			logger.info("Method : viewAssetPolicy starts");

			JsonResponse<List<AssetPolicyModel>> resp = new JsonResponse<List<AssetPolicyModel>>();

			try {
				resp = restClient.getForObject(env.getAssetUrl() + "viewassetpolicy", JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			System.out.println(resp);
			logger.info("Method : viewAssetPolicy ends");

			return resp.getBody();
		}
		
		//edit asset policy

		@SuppressWarnings("unchecked")
		@GetMapping("asset-policy-edit")
		public @ResponseBody JsonResponse<AssetPolicyModel> editAssetPolicy(@RequestParam String id, HttpSession session) {

			logger.info("Method : editAssetPolicy starts");

			JsonResponse<AssetPolicyModel> response = new JsonResponse<AssetPolicyModel>();
			try {
				response = restClient.getForObject(env.getAssetUrl() + "editassetpolicy?id=" + id, JsonResponse.class);

			} catch (

			RestClientException e) {
				e.printStackTrace();
			}
			
			if (response.getMessage() != null && response.getMessage() != "") {
				response.setCode(response.getMessage());
				response.setMessage("Unsuccess");

			} else {
				response.setMessage("Success");
			}

			System.out.println("##@@@@"+response);
			logger.info("Method : editAssetPolicy ends");
			return response;
		}
		
	//delete asset policy
		
		@SuppressWarnings("unchecked")
		@GetMapping("asset-policy-delete")
		public @ResponseBody JsonResponse<AssetPolicyModel> deleteAssetPolicy(@RequestParam String deleteId) {

			logger.info("Method : deleteAssetPolicy starts");
			// System.out.println("@@@@@" + deleteId);
			JsonResponse<AssetPolicyModel> response = new JsonResponse<AssetPolicyModel>();

			try {
				response = restClient.getForObject(env.getAssetUrl() + "deleteAssetPolicy?id=" + deleteId,
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
			logger.info("Method : deleteAssetPolicy ends");
			return response;
		}

}
