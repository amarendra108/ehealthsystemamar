package nirmalya.aathithya.webmodule.asset.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.asset.model.AssetCodeModel;
import nirmalya.aathithya.webmodule.asset.model.AssetGraphModel;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

/**
 * @author Nirmalya Labs
 *
 */
@Controller
@RequestMapping(value = "asset")
public class AssetDashboardController {

	Logger logger = LoggerFactory.getLogger(AssetDashboardController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	/**
	 * View Default 'Asset Maintenance Policy History' page
	 *
	 */
	@GetMapping("/asset-management-dashboard")
	public String assetDashboard(Model model, HttpSession session) {
		logger.info("Method : assetDashboard starts");

		DropDownModel[] assets = restClient.getForObject(env.getAssetUrl() + "assetssCount", DropDownModel[].class);
		List<DropDownModel> assetsCount = Arrays.asList(assets);
		model.addAttribute("assetsCount", assetsCount);

		DropDownModel[] assetmentainance = restClient.getForObject(env.getAssetUrl() + "assetssMentenance",
				DropDownModel[].class);
		List<DropDownModel> assetmentainance1 = Arrays.asList(assetmentainance);
		model.addAttribute("assetmentainance1", assetmentainance1);
		/*
		 * total maintenance table for dashboard
		 */
		List<AssetCodeModel> resp = new ArrayList<AssetCodeModel>();

		AssetCodeModel[] assetService = restClient.getForObject(env.getAssetUrl() + "asset-service",
				AssetCodeModel[].class);
		resp = Arrays.asList(assetService);
		model.addAttribute("assetService", resp);
		/*
		 * Asset Category Report
		 */
		List<AssetCodeModel> respReport = new ArrayList<AssetCodeModel>();

		AssetCodeModel[] assetReport = restClient.getForObject(env.getAssetUrl() + "assetCategory",
				AssetCodeModel[].class);
		respReport = Arrays.asList(assetReport);
		model.addAttribute("assetReport", respReport);

		logger.info("Method : assetCode ends");

		logger.info("Method : assetDashboard ends");
		return "asset/asset-management-dashboard";
	}

	/*
	 * Asset Report For Graph
	 */

	@GetMapping("/asset-management-dashboard-asset-report")
	public @ResponseBody JsonResponse<Object> getreportForGraph(HttpSession session) {

		logger.info("Method : getreportForGraph starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			AssetGraphModel[] auditTypes = restClient.getForObject(env.getAssetUrl() + "getAssetreportForGraph",
					AssetGraphModel[].class);
			List<AssetGraphModel> dataList = Arrays.asList(auditTypes);
			List<Integer> totalAsset = new ArrayList<Integer>();
			List<Integer> mentainAsset = new ArrayList<Integer>();
			List<Integer> remainAsset = new ArrayList<Integer>();

			List<String> monthList = new ArrayList<String>();

			for (AssetGraphModel m : auditTypes) {

				totalAsset.add(m.getTotalAsset());
				mentainAsset.add(m.getMentainAsset());
				remainAsset.add(m.getRemainAsset());
				monthList.add(m.getMonth());

			}

			dataList.get(0).setMonthList(monthList);

			dataList.get(0).setTotalAssetList(totalAsset);
			dataList.get(0).setMentainAssetList(mentainAsset);
			dataList.get(0).setRemainAssetList(remainAsset);

			resp.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(resp);
		logger.info("Method : getreportForGraph ends");

		return resp;
	}
	/*
	 * Asset Category for pie chart
	 * 
	 * 
	 * @GetMapping("/asset-management-dashboard-asset-report") public @ResponseBody
	 * JsonResponse<Object> getPiechart(HttpSession session) {
	 * 
	 * logger.info("Method : getPiechart starts"); JsonResponse<Object> resp = new
	 * JsonResponse<Object>(); try { AssetGraphModel[] auditTypes = restClient
	 * .getForObject(env.getAssetUrl() + "getPiechart", AssetGraphModel[].class);
	 * List<AssetGraphModel> dataList = Arrays.asList(auditTypes); List<Integer>
	 * totalAsset = new ArrayList<Integer>(); List<Integer> mentainAsset = new
	 * ArrayList<Integer>(); List<Integer> remainAsset = new ArrayList<Integer>();
	 * 
	 * List<String> monthList = new ArrayList<String>();
	 * 
	 * for (AssetGraphModel m : auditTypes) {
	 * 
	 * totalAsset.add(m.getTotalAsset()); mentainAsset.add(m.getMentainAsset());
	 * remainAsset.add(m.getRemainAsset()); monthList.add(m.getMonth());
	 * 
	 * }
	 * 
	 * dataList.get(0).setMonthList(monthList);
	 * 
	 * dataList.get(0).setTotalAssetList(totalAsset);
	 * dataList.get(0).setMentainAssetList(mentainAsset);
	 * dataList.get(0).setRemainAssetList(remainAsset);
	 * 
	 * resp.setBody(dataList); } catch (Exception e) { e.printStackTrace(); }
	 * System.out.println(resp); logger.info("Method : getPiechart ends");
	 * 
	 * return resp; }
	 */
}
