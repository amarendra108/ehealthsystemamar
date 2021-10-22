package nirmalya.aathithya.webmodule.asset.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import nirmalya.aathithya.webmodule.asset.model.AssetMaintenanceModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Controller
@RequestMapping(value = "asset/")
public class AssetMaintenanceController {
	
	Logger logger = LoggerFactory.getLogger(AssetMaintenanceController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("asset-maintenance")
	public String assetMaintenance(Model model, HttpSession session) {
		logger.info("Method : assetMaintenance starts");
		
		logger.info("Method : assetMaintenance ends");
		return "asset/asset-maintenance";
	}

	/*
	 * // view asset policy
	 */
			@SuppressWarnings("unchecked")
			@GetMapping("asset-maintenance-policyview")
			public @ResponseBody List<AssetMaintenanceModel> viewAssetPolicy() {

				logger.info("Method : viewAssetPolicy starts");

				JsonResponse<List<AssetMaintenanceModel>> resp = new JsonResponse<List<AssetMaintenanceModel>>();

				try {
					resp = restClient.getForObject(env.getAssetUrl() + "viewassetnewpolicy", JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}

				logger.info("Method : viewAssetPolicy ends");
				return resp.getBody();
			}
			
			
			/*
			 * //Web Controller - Get Asset List By AutoSearch
			 */

			@SuppressWarnings("unchecked")
			@PostMapping(value = { "asset-maintenance-list" })
			public @ResponseBody JsonResponse<AssetMaintenanceModel> AssetAutoSearchList(Model model,
					@RequestBody String searchValue, BindingResult result) {

				logger.info("Method : AssetAutoSearchList starts");
				

				JsonResponse<AssetMaintenanceModel> res = new JsonResponse<AssetMaintenanceModel>();

				try {
					res = restClient.getForObject(env.getAssetUrl() + "getassetListByAutoSearch?id=" + searchValue,
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
				
				logger.info("Method : AssetAutoSearchList ends");
				return res;

			}

			
			/*
			 * //add asset maintenance details
			 */	
			
			@SuppressWarnings("unchecked")
			@PostMapping("asset-maintenance-add")
			public @ResponseBody JsonResponse<Object> addAssetMaintenance(@RequestBody AssetMaintenanceModel asset,
					HttpSession session) {

				logger.info("Method : addAssetMaintenance starts");
				
				JsonResponse<Object> resp = new JsonResponse<Object>();

				String userId = "";
				String dateFormat = "";
				try {
					userId = (String) session.getAttribute("USER_ID");
				
					dateFormat = (String) session.getAttribute("DATEFORMAT");
				} catch (Exception e) {
					e.printStackTrace();

				}
				asset.setCreatedBy(userId);

				if (asset.getPerformedDate() != null && asset.getPerformedDate() != "") {
					asset.setPerformedDate(DateFormatter.inputDateFormat(asset.getPerformedDate(), dateFormat));
				}

				try {
					resp = restClient.postForObject(env.getAssetUrl() + "addassetmaintenance", asset,
							JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}

				if (resp.getMessage() != "" && resp.getMessage() != null) {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}

				logger.info("Method : addAssetMaintenance ends");

				return resp;
			}
			
}
