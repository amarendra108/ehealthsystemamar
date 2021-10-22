package nirmalya.aathithya.webmodule.employee.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.employee.model.ReimbrusementOtherModel;
import nirmalya.aathithya.webmodule.employee.model.ReimbursementModel;

@Controller
@RequestMapping(value = { "employee/" })
public class ReimbrusementOthersController {
	Logger logger = LoggerFactory.getLogger(ReimbrusementOthersController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("reimbursement-other")
	public String reimbrusementOther(Model model, HttpSession session) {

		logger.info("Method : reimbrusementOther starts");

		/*
		 * Get Policy Type List
		 * 
		 */

		try {
			DropDownModel[] reimType = restTemplate.getForObject(env.getEmployeeUrl() + "getTypeLists",
					DropDownModel[].class);
			List<DropDownModel> reimbTypeLists = Arrays.asList(reimType);
			model.addAttribute("reimbTypeLists", reimbTypeLists);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Method : reimbrusementOther ends");
		return "employee/reimbursement-other";
	}
	/*
	 * save image
	 */

	@PostMapping("reimbursement-other-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");
		System.out.println("MultipartFile" + inputFile);
		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("quotationPFile", inputFile);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : uploadFile controller function 'post-mapping' ends");
		System.out.println("img" + response);
		return response;
	}

	/*
	 * Add Travel Reimbursement Details
	 * 
	 */

	@SuppressWarnings("unchecked")

	@PostMapping("reimbursement-other-add")
	public @ResponseBody JsonResponse<Object> addReimbursementOther(HttpSession session,

			@RequestBody ReimbrusementOtherModel reimbursementModel) {

		logger.info("Method : addReimbursementTravelDetails");
		System.out.println("@@@ADDDDDDweb module" + reimbursementModel);
		String dateFormat = "";
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
			System.out.println(dateFormat);

		} catch (Exception e) {
			e.printStackTrace();
		}
		reimbursementModel.setCreatedBy(userId);
		if (reimbursementModel.getDate() != null && reimbursementModel.getDate() != "") {
			reimbursementModel.setDate(DateFormatter.inputDateFormat(reimbursementModel.getDate(), dateFormat));
		}
		if (reimbursementModel.getExpenseDate() != null && reimbursementModel.getExpenseDate() != "") {
			reimbursementModel.setExpenseDate(DateFormatter.inputDateFormat(reimbursementModel.getExpenseDate(), dateFormat));
		}
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				reimbursementModel.setDocName(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// System.out.println(attendanceModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrsop" + resp);
		try {
			resp = restTemplate.postForObject(env.getEmployeeUrl() + "add-reimbursement-others", reimbursementModel,
					JsonResponse.class);
			
			ObjectMapper mapper = new ObjectMapper();
			
			  List<ReimbursementModel> quotation = mapper.convertValue(resp.getBody(), 
					  new TypeReference<List<ReimbursementModel>>() {
			  });
			  
			  resp.setBody(quotation);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addReimbursementTravelDetails ends");
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrreturn" + resp);
		return resp;
	}

	private String saveAllImage(byte[] imageBytes, String ext) {
		logger.info("Method : saveAllImage starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName = nowTime + ".jpg";
				} else {
					imageName = nowTime + "." + ext;
				}

			}

			Path path = Paths.get(env.getFileUploadMaster() + imageName);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;
		
	}
	
	// View Travel Reimbrusiement

		@SuppressWarnings("unchecked")

		@GetMapping("/reimbursement-other-view")
		public @ResponseBody List<ReimbrusementOtherModel> viewReimbursementOther(HttpSession session) {

			logger.info("Method : viewReimbursementOther");

			JsonResponse<List<ReimbrusementOtherModel>> resp = new JsonResponse<List<ReimbrusementOtherModel>>();
			// System.out.println("studentModellllllllllllllllllllllllll" + resp);
			try {
			resp = restTemplate.getForObject(env.getEmployeeUrl() + "viewReimbursementOther", JsonResponse.class);
		}catch(

		RestClientException e)
		{
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		// System.out.println("studentmappppppppppppppppppper" + mapper);

		List<ReimbrusementOtherModel> reimbursementModel=mapper.convertValue(resp.getBody(),new TypeReference<List<ReimbrusementOtherModel>>(){});
		String dateFormat = "";
		try
		{
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		}catch(
		Exception e)
		{

		} 
			  for(ReimbrusementOtherModel a:reimbursementModel) {
			  
			  // System.out.println("for rach lllllllllooooooopppppppppp" + studentModel);
				  if (a.getDate() != null && a.getDate() != "") {
					  a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat)); }		  					  
			  if (a.getExpenseDate() != null && a.getExpenseDate() != "") {
			  a.setExpenseDate(DateFormatter.dateFormat(a.getExpenseDate(), dateFormat)); }
			  }

		if(resp.getMessage()!=""&&resp.getMessage()!=null)
		{
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		}else
		{
			resp.setMessage("Success");
		}

		logger.info("Method : viewReimbursementOther ends");
		System.out.println("respwwwwwwwwwwwwwwwweb view"+resp);

		System.out.println("studentModelwebview"+reimbursementModel); 
		return reimbursementModel;
	
		}
		/*
		 * for editing Reimbursement
		 * 
		 */

		@SuppressWarnings("unchecked")
		@GetMapping("reimbursement-other-edit")
		public @ResponseBody JsonResponse<ReimbrusementOtherModel> editReimbursementTravel(@RequestParam String id,
				HttpSession session) {
			System.out.println(id);
			logger.info("Method : editReimbursementTravel starts");

			JsonResponse<ReimbrusementOtherModel> jsonResponse = new JsonResponse<ReimbrusementOtherModel>();

			try {
				jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "edit-rest-reimbursement-other?id=" + id,
						JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}

			ObjectMapper mapper = new ObjectMapper();

			ReimbrusementOtherModel reimModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<ReimbrusementOtherModel>() {
					});
			String dateFormat = "";
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			if (reimModel.getDate() != null && reimModel.getDate() != "") {
				reimModel.setDate(DateFormatter.dateFormat(reimModel.getDate(), dateFormat));
			}
			
			if (reimModel.getExpenseDate() != null && reimModel.getExpenseDate() != "") {
				reimModel.setExpenseDate(DateFormatter.dateFormat(reimModel.getExpenseDate(), dateFormat));
			}
			

			jsonResponse.setBody(reimModel);
			if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
				jsonResponse.setCode(jsonResponse.getMessage());
				jsonResponse.setMessage("Unsuccess");

			} else {
				jsonResponse.setMessage("Success");
			}

			logger.info("Method : editReimbursementTravel ends");
			System.out.println("EDITjsonResponse"+jsonResponse);
			return jsonResponse;
		}
		
		/*
		 *
		 * Delete Reimbursement
		 *
		 */
		
		@SuppressWarnings("unchecked")
		@GetMapping("reimbursement-other-delete-details")
		public @ResponseBody JsonResponse<ReimbrusementOtherModel> deleteReimbursementOther(@RequestParam String id) {
			System.out.println(id);
			logger.info("Method : deleteReimbursementOther starts");

			JsonResponse<ReimbrusementOtherModel> jsonResponse = new JsonResponse<ReimbrusementOtherModel>();

			try {
				jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "deleteReimbursementOthers?id=" + id,
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

			logger.info("Method : deleteReimbursementOther ends");
			return jsonResponse;
		}
}
