package nirmalya.aathithya.webmodule.employee.controller;

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

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.employee.model.ExtendExitManagementModel;

@Controller
@RequestMapping(value = "employee/")
public class ExtendExitManagementController {

	Logger logger = LoggerFactory.getLogger(ExtendExitManagementController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("exit")
	public String addExitManagement(Model model, HttpSession session) {

		logger.info("Method : addExitManagement starts");
		try {
			DropDownModel[] name = restClient.getForObject(env.getEmployeeUrl() + "getNamelist", DropDownModel[].class);
			List<DropDownModel> namelist = Arrays.asList(name);
			model.addAttribute("namelist", namelist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dept = restClient.getForObject(env.getEmployeeUrl() + "getDeptlist", DropDownModel[].class);
			List<DropDownModel> deptlist = Arrays.asList(dept);
			model.addAttribute("deptlist", deptlist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] clrncPerson = restClient.getForObject(env.getEmployeeUrl() + "getClrncPersonList",
					DropDownModel[].class);
			List<DropDownModel> clrncPList = Arrays.asList(clrncPerson);
			model.addAttribute("clrncPList", clrncPList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : addExitManagement ends");

		return "employee/extend-exit-management";
	}

	/*
	 * dropdown for job through ajax
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "exit-emp-job-ajax" })
	public @ResponseBody JsonResponse<Object> getDesignationList(@RequestParam String name) {
		logger.info("Method : getDesignationList starts");

		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restClient.getForObject(env.getEmployeeUrl() + "rest-get-designationList?id=" + name,
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

		logger.info("Method : getDesignationList ends");
		return res;

	}

	/*
	 * Add Exit Management
	 * 
	 */

	@SuppressWarnings("unchecked")

	@PostMapping("exit-add-details")
	public @ResponseBody JsonResponse<Object> addExitManagement(@RequestBody ExtendExitManagementModel exitModel,
			HttpSession session) {
		logger.info("Method : addExitManagement starts");

		String dateFormat = "";
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		exitModel.setCreatedBy(userId);
		if (exitModel.getReleaseDate() != null && exitModel.getReleaseDate() != "") {
			exitModel.setReleaseDate(DateFormatter.inputDateFormat(exitModel.getReleaseDate(), dateFormat));
		}
		if (exitModel.getResignDate() != null && exitModel.getResignDate() != "") {
			exitModel.setResignDate(DateFormatter.inputDateFormat(exitModel.getResignDate(), dateFormat));
		}

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "addExitdetails", exitModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addExitManagement ends");
		return resp;
	}

	/*
	 * Add Initiate & Clearance Details
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("exit-save-initiate")
	public @ResponseBody JsonResponse<Object> addClearanceDetails(
			@RequestBody List<ExtendExitManagementModel> exitModel, HttpSession session) {

		logger.info("Method : addClearanceDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		for (ExtendExitManagementModel m : exitModel) {
			m.setCreatedBy(userId);
		}

		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "addinitiatedata", exitModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addClearanceDetails ends");

		return resp;
	}

	/*
	 * View Exit Management
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("exit-view-through-ajax")
	public @ResponseBody List<ExtendExitManagementModel> viewExitManagement() {

		logger.info("Method : viewExitManagement starts");

		JsonResponse<List<ExtendExitManagementModel>> resp = new JsonResponse<List<ExtendExitManagementModel>>();

		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "viewExitdetails", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewExitManagement ends");
		return resp.getBody();
	}

	/*
	 * Edit Exit Management details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("exit-details-edit")
	public @ResponseBody JsonResponse<ExtendExitManagementModel> editExitManagement(@RequestParam String id,
			HttpSession session) {

		logger.info("Method : editExitManagement starts");

		JsonResponse<ExtendExitManagementModel> jsonResponse = new JsonResponse<ExtendExitManagementModel>();

		try {
			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "editExitManagement?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		ExtendExitManagementModel exitModel = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<ExtendExitManagementModel>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		if (exitModel.getReleaseDate() != null && exitModel.getReleaseDate() != "") {
			exitModel.setReleaseDate(DateFormatter.dateFormat(exitModel.getReleaseDate(), dateFormat));
		}
		if (exitModel.getResignDate() != null && exitModel.getResignDate() != "") {
			exitModel.setResignDate(DateFormatter.dateFormat(exitModel.getResignDate(), dateFormat));
		}

		jsonResponse.setBody(exitModel);
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : editExitManagement ends");
		return jsonResponse;

	}

	/*
	 * Check clearance details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("exit-get-clrnc-details")
	public @ResponseBody JsonResponse<List<ExtendExitManagementModel>> checkClearanceDetails(@RequestParam String id,
			HttpSession session) {

		logger.info("Method : checkClearanceDetails starts");
		JsonResponse<List<ExtendExitManagementModel>> response = new JsonResponse<List<ExtendExitManagementModel>>();

		try {
			response = restClient.getForObject(env.getEmployeeUrl() + "getclearancedetails?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<ExtendExitManagementModel> exitModel = mapper.convertValue(response.getBody(),
				new TypeReference<List<ExtendExitManagementModel>>() {
				});
		int count = 0;
		for (ExtendExitManagementModel m : exitModel) {
			count++;
			m.setSlNo(count);
		}

		response.setBody(exitModel);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : checkClearanceDetails ends");
		return response;

	}

	/*
	 * Delete exit details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("exit-details-delete")
	public @ResponseBody JsonResponse<ExtendExitManagementModel> deleteExitDetails(@RequestParam String deleteId) {

		logger.info("Method : deleteExitDetails starts");

		JsonResponse<ExtendExitManagementModel> jsonResponse = new JsonResponse<ExtendExitManagementModel>();

		try {
			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "exitManagementdelete?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : deleteExitDetails ends");
		return jsonResponse;
	}

	/*
	 * Add Finance details
	 */
	@SuppressWarnings("unchecked")

	@PostMapping("exit-add-finance-details")
	public @ResponseBody JsonResponse<Object> addFinanceDetails(@RequestBody ExtendExitManagementModel exitModel,
			HttpSession session) {
		logger.info("Method : addFinanceDetails starts");
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		exitModel.setCreatedBy(userId);
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "addFinancedetails", exitModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addFinanceDetails ends");
		return resp;
	}
}