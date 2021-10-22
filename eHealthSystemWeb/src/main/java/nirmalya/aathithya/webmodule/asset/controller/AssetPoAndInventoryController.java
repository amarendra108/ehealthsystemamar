package nirmalya.aathithya.webmodule.asset.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nirmalya.aathithya.webmodule.asset.model.AssetPoAndInventoryModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Controller
@RequestMapping(value = "asset/")
public class AssetPoAndInventoryController {
	Logger logger = LoggerFactory.getLogger(AssetPoAndInventoryController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("asset-po")
	public String assetPO(Model model, HttpSession session) {
		logger.info("Method : assetPO starts");

		logger.info("Method : assetPO ends");
		return "asset/asset-po-and-inventory";
	}

	
	/*
	 * // view asset PO And Inventory
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("asset-po-view")
	public @ResponseBody List<AssetPoAndInventoryModel> AssetPoAndInventoryModel(HttpSession session) {

		logger.info("Method : viewAssetCode starts");

		JsonResponse<List<AssetPoAndInventoryModel>> resp = new JsonResponse<List<AssetPoAndInventoryModel>>();

		try {
			resp = restClient.getForObject(env.getAssetUrl() + "viewassetpo", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<AssetPoAndInventoryModel> asset = mapper.convertValue(resp.getBody(),
				new TypeReference<List<AssetPoAndInventoryModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (AssetPoAndInventoryModel a : asset) {
			if (a.getPoDate() != null && a.getPoDate() != "") {
				a.setPoDate(DateFormatter.dateFormat(a.getPoDate(), dateFormat));
			}
		}
		logger.info("Method : viewAssetCode ends");

		return asset;
	}
}


