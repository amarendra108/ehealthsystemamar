package nirmalya.aathithya.webmodule.lab.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.TestLabListModel;
import nirmalya.aathithya.webmodule.lab.model.TestReportModel;

@Controller
@RequestMapping(value = { "lab/" })
public class TestReportUploadController {

	Logger logger = LoggerFactory.getLogger(TestReportUploadController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	@GetMapping("test-report")
	public String viewLabTestReport(Model model, HttpSession session) {

		logger.info("Method : viewPatientTestReport starts");
		/*
		 * for test group
		 */
		try {
			DropDownModel[] group = restTemplate.getForObject(env.getLabUrl() + "testGroupNameTypeLists",
					DropDownModel[].class);
			List<DropDownModel> groupNameList = Arrays.asList(group);
			model.addAttribute("groupNameList", groupNameList);
			System.out.println(groupNameList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("patId", userId);

		logger.info("Method : viewLabTestReport ends");
		return "lab/test-report-upload";
	}

	// get test report details

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/test-report-get-reportType" })
	public @ResponseBody JsonResponse<List<TestLabListModel>> getReportType(@RequestParam String id) {
		logger.info("Method : getReportType starts");

		List<String> myList = new ArrayList<String>(Arrays.asList(id.split(",")));

		List<TestLabListModel> testModelList = new ArrayList<TestLabListModel>();

		// List<LabTestReportModel> grpList = new ArrayList<LabTestReportModel>();
		JsonResponse<List<TestReportModel>> res = new JsonResponse<List<TestReportModel>>();
		JsonResponse<List<TestLabListModel>> res1 = new JsonResponse<List<TestLabListModel>>();
		try {

			for (String a : myList) {
				TestLabListModel testModel = new TestLabListModel();
				res = restTemplate.getForObject(env.getLabUrl() + "rest-getReportType?id=" + a, JsonResponse.class);

				ObjectMapper mapper = new ObjectMapper();

				List<TestReportModel> customer = mapper.convertValue(res.getBody(),
						new TypeReference<List<TestReportModel>>() {
						});
				testModel.setObj(customer);

				// grpList.add(customer) ;
				testModelList.add(testModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		res1.setBody(testModelList);
		if (res1.getMessage() != null) {
			res1.setCode(res1.getMessage());
			res1.setMessage("Unsuccess");
		} else {
			res1.setMessage("success");
		}
		logger.info("Method : getReportType ends");
		return res1;
	}
	// save Test Report

	@SuppressWarnings({ "unchecked" })
	@PostMapping(value = "test-report-addLabTestReport")
	public @ResponseBody JsonResponse<Object> addLabTestReport(@RequestBody List<TestReportModel> testReport,
			HttpSession session) {
		logger.info("Method : addLabTestReport function starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			resp = restTemplate.postForObject(env.getLabUrl() + "rest-addLabTestReport", testReport,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String message = resp.getMessage();
		if (message != null && message != "") {
		} else {
			resp.setMessage("Success");
		}
		logger.info("Method : addLabTestReport function Ends");
		return resp;
	}


	// View Test Report
	@SuppressWarnings("unchecked")
	@GetMapping("test-report-viewLabTestReport")
	public @ResponseBody List<TestReportModel> viewLabTestReport(HttpSession session) {

		logger.info("Method : viewLabTestReport starts");

		JsonResponse<List<TestReportModel>> response = new JsonResponse<List<TestReportModel>>();
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");
			response = restTemplate.getForObject(env.getLabUrl() + "rest-viewLabTestReport?id=" + userId, JsonResponse.class);

		} catch (

		RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewLabTestReport ends");
		return response.getBody();
	}
}
