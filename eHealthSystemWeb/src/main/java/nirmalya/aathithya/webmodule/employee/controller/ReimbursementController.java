package nirmalya.aathithya.webmodule.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.ActivitylogModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
//import nirmalya.aathithya.webmodule.employee.model.ReimbursementDocumentModel;
import nirmalya.aathithya.webmodule.employee.model.ReimbursementModel;
import nirmalya.aathithya.webmodule.employee.model.ReimbursementPaymentModel;

@Controller
@RequestMapping(value = { "employee/" })
public class ReimbursementController {

	Logger logger = LoggerFactory.getLogger(ReimbursementController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	FileUpload fileUpload;
	

	@GetMapping("/reimbursement")
	public String employee(Model model, HttpSession session) {
		logger.info("Method : reimbursement starts");
		
		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-reimbursement-type",
					DropDownModel[].class);
			List<DropDownModel> reimType = Arrays.asList(dropDownModel);
			model.addAttribute("reimType", reimType);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-policy-type",
					DropDownModel[].class);
			List<DropDownModel> policyType = Arrays.asList(dropDownModel);
			model.addAttribute("policyType", policyType);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] gender = restTemplate.getForObject(env.getEmployeeUrl() + "getRequisitionList",
					DropDownModel[].class);
			List<DropDownModel> getRequisitionList = Arrays.asList(gender);
			model.addAttribute("getRequisitionList", getRequisitionList);

		} catch (RestClientException e) {
			e.printStackTrace();

		}
		//Payment mode list
		try {
			DropDownModel[] paymentMode = restTemplate.getForObject(env.getEmployeeUrl() + "getPaymentMode",
					DropDownModel[].class);
			List<DropDownModel> PayModeLists = Arrays.asList(paymentMode);

			model.addAttribute("PayModeLists", PayModeLists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		// Get bank Name Details
		try {
			DropDownModel[] bankNames = restTemplate.getForObject(env.getEmployeeUrl() + "getBankNamesPay",
					DropDownModel[].class);
			List<DropDownModel> bankNameLists = Arrays.asList(bankNames);
			model.addAttribute("bankNameLists", bankNameLists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		 
		logger.info("Method : reimbursement ends");
		return "employee/reimbursement";
	}

	/*
	 * View Reimbursement
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@GetMapping("reimbursement-view-through-ajax")
	public @ResponseBody List<ReimbursementModel> viewReimbursement(Model model, HttpSession session) {
		logger.info("Method : viewReimbursement starts");

		JsonResponse<List<ReimbursementModel>> jsonResponse = new JsonResponse<List<ReimbursementModel>>();

		try {

			jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "reimbursement-view", JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ReimbursementModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ReimbursementModel>>() {
					});

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");

			for (ReimbursementModel m : addreq) {

				if (m.getFromDate() != null && m.getFromDate() != "") {
					m.setFromDate(DateFormatter.dateFormat(m.getFromDate(), dateFormat));
				}

				if (m.getToDate() != null && m.getToDate() != "") {
					m.setToDate(DateFormatter.dateFormat(m.getToDate(), dateFormat));
				}

				if (m.getCreatedOn() != null && m.getCreatedOn() != "") {
					m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
				}
			}

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewReimbursement ends");
		System.out.println(jsonResponse);
		return jsonResponse.getBody();
	}

	/*
	 * Add Reimbursement
	 * 
	 */
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping("reimbursement-add-details") public @ResponseBody
	 * JsonResponse<Object> addReimbursement(HttpSession session,
	 * 
	 * @RequestBody ReimbursementModel studentModel) {
	 * 
	 * logger.info("Method : addReimbursement"); JsonResponse<Object> resp = new
	 * JsonResponse<Object>();
	 * System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrsop" + resp); String
	 * userId = ""; String dateFormat = ""; try { dateFormat = (String)
	 * session.getAttribute("DATEFORMAT"); System.out.println(dateFormat);
	 * 
	 * } catch (Exception e) {
	 * 
	 * } studentModel.setCreatedBy(userId);
	 * 
	 * // reimbursementModel.setCreatedBy(userId); studentModel.setEmpName(userId);
	 * if (studentModel.getFromDate() != null && studentModel.getFromDate() != "") {
	 * studentModel.setFromDate(DateFormatter.dateFormat(studentModel.getFromDate(),
	 * dateFormat)); }
	 * 
	 * if (studentModel.getToDate() != null && studentModel.getToDate() != "") {
	 * studentModel.setToDate(DateFormatter.dateFormat(studentModel.getToDate(),
	 * dateFormat)); } System.out.println(studentModel);
	 * 
	 * 
	 * try { resp = restTemplate.postForObject(env.getEmployeeUrl() +
	 * "add-reimbursement", studentModel, JsonResponse.class); } catch
	 * (RestClientException e) { e.printStackTrace(); }
	 * 
	 * if (resp.getMessage() != "" && resp.getMessage() != null) {
	 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
	 * resp.setMessage("Success"); }
	 * 
	 * logger.info("Method : addReimbursement ends");
	 * System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrreturn" + resp); return resp;
	 * }
	 */


	
	@SuppressWarnings("unchecked")

	@PostMapping("/reimbursement-add-details")
	public @ResponseBody JsonResponse<Object> addReimbursement(@RequestBody ReimbursementModel vendorMasterModel,
			HttpSession session) {
		logger.info("Method : addReimbursement starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		vendorMasterModel.setCreatedBy(userId);

		// reimbursementModel.setCreatedBy(userId);
		vendorMasterModel.setEmpName(userId);

		if (vendorMasterModel.getFromDate() != null && vendorMasterModel.getFromDate() != "") {
			vendorMasterModel.setFromDate(DateFormatter.dateFormat(vendorMasterModel.getFromDate(), dateFormat));
		}

		if (vendorMasterModel.getToDate() != null && vendorMasterModel.getToDate() != "") {
			vendorMasterModel.setToDate(DateFormatter.dateFormat(vendorMasterModel.getToDate(), dateFormat));
		}

		try {

			resp = restTemplate.postForObject(env.getEmployeeUrl() + "add-reimbursement", vendorMasterModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

	//	String message = resp.getMessage();

		  
		  if (resp.getMessage() != "" && resp.getMessage() != null) {
		  resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
		  resp.setMessage("Success"); }
		  
		  logger.info("Method : addReimbursement ends");
		  System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrreturn" + resp); return resp;
	}
	 
	/*
	 * for editing Reimbursement
	 * 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("reimbursement-edit-through-ajax")
	public @ResponseBody JsonResponse<ReimbursementModel> editReimbursement(@RequestParam String id,
			HttpSession session) {

		logger.info("Method : editReimbursement starts");

		JsonResponse<ReimbursementModel> jsonResponse = new JsonResponse<ReimbursementModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "edit-rest-reimbursement?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		ReimbursementModel reimModel = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<ReimbursementModel>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (reimModel.getFromDate() != null && reimModel.getFromDate() != "") {
			reimModel.setFromDate(DateFormatter.dateFormat(reimModel.getFromDate(), dateFormat));
		}
		if (reimModel.getToDate() != null && reimModel.getToDate() != "") {
			reimModel.setToDate(DateFormatter.dateFormat(reimModel.getToDate(), dateFormat));
		}
		
		jsonResponse.setBody(reimModel);
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : editReimbursement ends");
		System.out.println("EDITjsonResponse"+jsonResponse);
		return jsonResponse;
	}

	/*
	 *
	 * Delete Reimbursement
	 *
	 */
	
	@SuppressWarnings("unchecked")
	@GetMapping("reimbursement-delete-details")
	public @ResponseBody JsonResponse<ReimbursementModel> deleteReimbursement(@RequestParam String id) {
		System.out.println(id);
		logger.info("Method : deleteReimbursement starts");

		JsonResponse<ReimbursementModel> jsonResponse = new JsonResponse<ReimbursementModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "deleteReimbursement?id=" + id,
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

		logger.info("Method : deleteReimbursement ends");
		return jsonResponse;
	}
	
	
	/*
	 * post Mapping for add reimbursement requisition details
	 * 
	 */
	
	/*@SuppressWarnings("unchecked")
	@PostMapping(value = "reimbursement-req-save-th-ajax")
	public @ResponseBody JsonResponse<Object> saveReimbursementReq(@RequestBody List<ReimbursementModel> reimbursementModel, HttpSession session,
			Model model) {
		logger.info("Method : saveReimbursementReq function starts");
		System.out.println(reimbursementModel);
		JsonResponse<Object> res = new JsonResponse<Object>();
		
		List<ReimbursementModel> reimReqList = new ArrayList<ReimbursementModel>();
		
		//List<ReimbursementDocumentModel> docList = new ArrayList<ReimbursementDocumentModel>();
		
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (ReimbursementModel m : reimbursementModel) {
			m.setCreatedBy(userId);
			m.setExpenseDate(DateFormatter.inputDateFormat(m.getExpenseDate(), dateFormat));

		}*/
		/*
		 * for (ReimbursementDocumentModel a :
		 * reimbursementModel.get(0).getDocumentList()) { if (a.getImageNameEdit() !=
		 * null && a.getImageNameEdit() != "") { a.setFileName(a.getImageNameEdit()); }
		 * else { if (a.getFileName() != null && a.getFileName() != "") { String
		 * delimiters = "\\."; String[] x = a.getFileName().split(delimiters);
		 * 
		 * if (x[1].contentEquals("png") || x[1].contentEquals("jpg") ||
		 * x[1].contentEquals("jpeg")) {
		 * 
		 * for (String s1 : a.getDocumentFile()) { if (s1 != null) try { byte[] bytes =
		 * Base64.getDecoder().decode(s1); String imageName =
		 * fileUpload.saveAllImage(bytes); a.setFileName(imageName); } catch (Exception
		 * e) { e.printStackTrace(); } } } else if (x[1].contentEquals("pdf")) { for
		 * (String s1 : a.getDocumentFile()) { try { byte[] bytes =
		 * Base64.getDecoder().decode(s1); String pdfName =
		 * fileUpload.saveAllPdf(bytes); a.setFileName(pdfName); } catch (Exception e) {
		 * e.printStackTrace(); } } } else if (x[1].contentEquals("docx")) { for (String
		 * s1 : a.getDocumentFile()) { try { byte[] bytes =
		 * Base64.getDecoder().decode(s1); String pdfName =
		 * fileUpload.saveAllDocx(bytes); a.setFileName(pdfName); } catch (Exception e)
		 * { e.printStackTrace(); } } } else if (x[1].contentEquals("doc")) { for
		 * (String s1 : a.getDocumentFile()) { try { byte[] bytes =
		 * Base64.getDecoder().decode(s1); String pdfName =
		 * fileUpload.saveAllDoc(bytes); a.setFileName(pdfName); } catch (Exception e) {
		 * e.printStackTrace(); } } } else if (x[1].contentEquals("xls")) { for (String
		 * s1 : a.getDocumentFile()) { try { byte[] bytes =
		 * Base64.getDecoder().decode(s1); String pdfName =
		 * fileUpload.saveAllXls(bytes); a.setFileName(pdfName); } catch (Exception e) {
		 * e.printStackTrace(); } } } else if (x[1].contentEquals("xlsx")) { for (String
		 * s1 : a.getDocumentFile()) { try { byte[] bytes =
		 * Base64.getDecoder().decode(s1); String pdfName =
		 * fileUpload.saveAllXlsx(bytes); a.setFileName(pdfName); } catch (Exception e)
		 * { e.printStackTrace(); } } } } } }
		 */
	/*	try {
			res = restTemplate.postForObject(env.getEmployeeUrl() + "reimbursement-req-rest-add",
					reimbursementModel, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		reimReqList = mapper.convertValue(res.getBody(), new TypeReference<List<ReimbursementModel>>() {
		});
		if (reimReqList != null) {

			for (ReimbursementModel a : reimReqList) {
				if (a.getExpenseDate() != null && a.getExpenseDate() != "") {
					a.setExpenseDate(DateFormatter.dateFormat(a.getExpenseDate(), dateFormat));
				}
			}*/
			//docList = reimReqList.get(0).getDocumentList();
			/*
			 * for (ReimbursementDocumentModel m : docList) { if (m.getFileName() != null &&
			 * m.getFileName() != "") { String[] extension = m.getFileName().split("\\.");
			 * if (extension.length == 2) { if (extension[1].equals("xls") ||
			 * extension[1].equals("xlsx")) {
			 * 
			 * String docPath = "<i class=\"fa fa-file-excel-o excel\" title= " +
			 * m.getFileName() + "></i> ";
			 * 
			 * m.setAction(docPath); } if (extension[1].equals("pdf")) { String docPath =
			 * " <i class=\"fa fa-file-pdf-o excel pdf\"   title=" + m.getFileName() +
			 * " ;></i> ";
			 * 
			 * m.setAction(docPath); } if (extension[1].equals("doc") ||
			 * extension[1].equals("dox") || extension[1].equals("docx")) { String docPath =
			 * " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title=" +
			 * m.getFileName() + "></i> "; m.setAction(docPath); } if
			 * (extension[1].equals("png") || extension[1].equals("jpg") ||
			 * extension[1].equals("jpeg")) { String docPath =
			 * " <i class=\"fa fa-picture-o \"\" aria-hidden=\"true\" title=" +
			 * m.getFileName() + "></i>  "; m.setAction(docPath); } } else {
			 * m.setAction("N/A"); } } else { m.setAction("N/A"); }
			 * m.setAction("<a href=\"/document/procurment/" + m.getFileName() +
			 * "\" target=\"_blank\" >" + m.getAction() + "</a>");
			 * 
			 * }
			 */
		//}
		//String message = res.getMessage();
	//	reimReqList.get(0).setDocumentList(docList);
		/*if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(reimReqList);
		
		logger.info("Method : saveReimbursementReq function Ends");
		return res;
	}*/
	/*
	 * save image
	 */

	@PostMapping("reimbursement-upload-file")
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

	@PostMapping("reimbursement-add-details-travels")
	public @ResponseBody JsonResponse<Object> addReimbursementTravelDetails(HttpSession session,

			@RequestBody ReimbursementModel reimbursementModel) {

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
			resp = restTemplate.postForObject(env.getEmployeeUrl() + "add-reimbursement-travel-details", reimbursementModel,
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

	@GetMapping("/reimbursement-travel-view")
	public @ResponseBody List<ReimbursementModel> viewReimbursementTravels(HttpSession session) {

		logger.info("Method : viewReimbursementTravels");

		JsonResponse<List<ReimbursementModel>> resp = new JsonResponse<List<ReimbursementModel>>();
		// System.out.println("studentModellllllllllllllllllllllllll" + resp);
		try {
		resp = restTemplate.getForObject(env.getEmployeeUrl() + "viewReimbursementTravels", JsonResponse.class);
	}catch(

	RestClientException e)
	{
		e.printStackTrace();
	}

	ObjectMapper mapper = new ObjectMapper();

	// System.out.println("studentmappppppppppppppppppper" + mapper);

	List<ReimbursementModel> reimbursementModel=mapper.convertValue(resp.getBody(),new TypeReference<List<ReimbursementModel>>(){});
	String dateFormat = "";
	try
	{
		dateFormat = (String) session.getAttribute("DATEFORMAT");
	}catch(
	Exception e)
	{

	} 
		  for(ReimbursementModel a:reimbursementModel) {
		  
		  // System.out.println("for rach lllllllllooooooopppppppppp" + studentModel);
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

	logger.info("Method : viewReimbursementTravels ends");System.out.println("respwwwwwwwwwwwwwwwweb view"+resp);

	System.out.println("studentModelwebview"+reimbursementModel); 
	return reimbursementModel;
	}
	/*
	 * //Leave Type Rule Delete
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("/reimbursement-travel-delete") public @ResponseBody
	 * JsonResponse<ReimbursementModel> deleteReimbursementTravels(@RequestParam
	 * String deleteId) { logger.info("Method : deleteReimbursementTravels starts");
	 * JsonResponse<ReimbursementModel> resp = new
	 * JsonResponse<ReimbursementModel>();
	 * 
	 * try { resp = restTemplate.getForObject(env.getEmployeeUrl() +
	 * "deleteReimbursementTravels?deleteId=" + deleteId, JsonResponse.class); }
	 * catch (RestClientException e) { e.printStackTrace(); }
	 * 
	 * if (resp.getMessage() != null && resp.getMessage() != "") {
	 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
	 * 
	 * resp.setMessage("Success");
	 * 
	 * } System.out.println("deletewwwwwwwwwwwwweeeeeeeeeeeebbbbbbbbbbbbbb"+resp);
	 * logger.info("Method :  deleteReimbursementTravels ends"); return resp; }
	 */
	
	/*
	 *
	 * Delete Reimbursement
	 *
	 */
	
	@SuppressWarnings("unchecked")
	@GetMapping("reimbursement-delete-travels")
	public @ResponseBody JsonResponse<ReimbursementModel> deleteReimbursementTravels(@RequestParam String id) {
		System.out.println(id);
		logger.info("Method : deleteReimbursementTravels starts");

		JsonResponse<ReimbursementModel> jsonResponse = new JsonResponse<ReimbursementModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "deleteReimbursementTravels?id=" + id,
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

		logger.info("Method : deleteReimbursementTravels ends");
		return jsonResponse;
	}
	
	/*
	 * for editing Reimbursement
	 * 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("reimbursement-edit-travel")
	public @ResponseBody JsonResponse<ReimbursementModel> editReimbursementTravel(@RequestParam String id,
			HttpSession session) {
		System.out.println(id);
		logger.info("Method : editReimbursementTravel starts");

		JsonResponse<ReimbursementModel> jsonResponse = new JsonResponse<ReimbursementModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "edit-rest-reimbursement-travels?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		ReimbursementModel reimModel = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<ReimbursementModel>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
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
	 * For Modal view of reimbursement
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "reimbursement-new-modal" })
	public @ResponseBody JsonResponse<List<ReimbursementModel>> modalAssignmentRem(Model model,
			@RequestBody String index, BindingResult result) {

		logger.info("Method :modalAssignmentEdu starts");

		byte[] decodeId = Base64.getDecoder().decode(index.getBytes());

		String id = (new String(decodeId));
		JsonResponse<List<ReimbursementModel>> response = new JsonResponse<List<ReimbursementModel>>();
		try {
			response = restTemplate.getForObject(env.getEmployeeUrl() + "getReimbusementByIdsModal?reimId=" + id,
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null) {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");
		} else {
			response.setMessage("success");
		}
		logger.info("Method : modalAssignmentEdu  ends ");
		return response;
	}
	/*
	
	
	/*
	 * for drop down branch names by bank id
	 */

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping(value = { "reimbursement-payment-voucher-branchList" })
	 * public @ResponseBody JsonResponse<DropDownModel> getbranchLists(Model
	 * model, @RequestBody String index, BindingResult result) {
	 * logger.info("Method : getbranchList starts");
	 * 
	 * JsonResponse<DropDownModel> res = new JsonResponse<DropDownModel>(); try {
	 * res = restTemplate.getForObject(env.getEmployeeUrl() + "getbranchLists?id=" +
	 * index, JsonResponse.class);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * if (res.getMessage() != null) {
	 * 
	 * res.setCode(res.getMessage()); res.setMessage("Unsuccess"); } else {
	 * res.setMessage("success"); }
	 * 
	 * logger.info("Method : getbranchLists ends"); return res; }
	 */
	/*
	 * Add Reimbursement Payment Voucher
	 * 
	 */

	@SuppressWarnings("unchecked")

	@PostMapping("reimbursement-add-details-payment-voucher")
	public @ResponseBody JsonResponse<Object> addReimbursementPayment(@RequestBody ReimbursementPaymentModel reimbursementModel,
			HttpSession session) {
		logger.info("Method : addReimbursementPayment starts");
		System.out.println("@@@ADDDDDD" + reimbursementModel);
		String dateFormat = "";
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		reimbursementModel.setCreatedBy(userId);
		//reimbursementModel.setEmpName(userId);

		if (reimbursementModel.getTransactionDate() != null && reimbursementModel.getTransactionDate() != "") {
			reimbursementModel.setTransactionDate(DateFormatter.dateFormat(reimbursementModel.getTransactionDate(), dateFormat));
		}

		

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.postForObject(env.getEmployeeUrl() + "add-reimbursement-payment", reimbursementModel,
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

		logger.info("Method : addReimbursementPayment ends");
		System.out.println(resp);
		return resp;
	}

	/*
	 * view throughAjax
	 * 
	 * 
	 */
	/*
	 * @GetMapping(value = { "reimbursement-view-activity-log" })
	 * public @ResponseBody List<ActivitylogModel>
	 * getActivityLogReimbrusement(@RequestParam String id, HttpSession session) {
	 * logger.info("Method : getActivityLogReimbrusement starts");
	 * List<ActivitylogModel> activityLogList = new ArrayList<ActivitylogModel>();
	 * try {
	 * 
	 * ActivitylogModel[] activityLog = restTemplate
	 * .getForObject(env.getEmployeeUrl() + "get-activity-log-riemb?id=" + id,
	 * ActivitylogModel[].class); activityLogList = Arrays.asList(activityLog);
	 * 
	 * for(ActivitylogModel m : activityLog) { String dateFormat = (String)
	 * session.getAttribute("DATEFORMAT"); if(m.getOperationOn()!=null &&
	 * m.getOperationOn()!="") {
	 * m.setOperationOn(DateFormatter.dateFormat(m.getOperationOn(), dateFormat)); }
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * logger.info("Method : getActivityLogReimbrusement ends"); return
	 * activityLogList; }
	 */
	
	
	
	@GetMapping(value = { "reimbursement-view-activity-log" })
	public @ResponseBody List<ActivitylogModel> getActivityLogReimbrusement(@RequestParam String id,
			HttpSession session) {
		System.out.println(id);
		logger.info("Method : getActivityLogReimbrusement starts");
		List<ActivitylogModel> activityLogList = new ArrayList<ActivitylogModel>();
		try {

			ActivitylogModel[] activityLog = restTemplate
					.getForObject(env.getEmployeeUrl() + "get-activity-log-riemb?id=" + id, ActivitylogModel[].class);
			activityLogList = Arrays.asList(activityLog);

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			for (ActivitylogModel m : activityLogList) {
				String date = m.getOperationOn();
				if (date != null && date != "") {
					date = DateFormatter.dateFormat(date, dateFormat);
					System.out.println(date);
					m.setOperationOn(date);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("activityLogList"+activityLogList);
		logger.info("Method : getActivityLogReimbrusement ends");
		return activityLogList;
	}
	 
	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("reimbursement-view-activity-log") public @ResponseBody
	 * List<ActivitylogModel> getActivityLogReimbrusement(Model model, HttpSession
	 * session) { logger.info("Method : getActivityLogReimbrusement starts");
	 * 
	 * JsonResponse<List<ActivitylogModel>> jsonResponse = new
	 * JsonResponse<List<ActivitylogModel>>();
	 * 
	 * try {
	 * 
	 * jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() +
	 * "get-activity-log-riemb", JsonResponse.class);
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * 
	 * List<ActivitylogModel> addreq = mapper.convertValue(jsonResponse.getBody(),
	 * new TypeReference<List<ActivitylogModel>>() { });
	 * 
	 * //String dateFormat = (String) (session).getAttribute("DATEFORMAT");
	 * 
	 * 
	 * String dateFormat = (String) (session).getAttribute("DATEFORMAT"); for
	 * (ActivitylogModel m : addreq) { String date = m.getOperationOn(); if (date !=
	 * null && date != "") { date = DateFormatter.dateFormat(date, dateFormat);
	 * System.out.println(date); m.setOperationOn(date); }
	 * 
	 * }
	 * 
	 * jsonResponse.setBody(addreq);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * logger.info("Method ; getActivityLogReimbrusement ends");
	 * System.out.println(jsonResponse); return jsonResponse.getBody(); }
	 */

	
	/*
	 * post Mapping for approve ItemRequisition
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "reimbursement-approve-th-ajax")
	public @ResponseBody JsonResponse<Object> approveItemReimbrusement(
			@RequestBody ReimbursementModel inventoryItemRequisitionModel, HttpSession session) {
		logger.info("Method : approveItemRequisition function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			inventoryItemRequisitionModel.setCreatedBy(userId);
		} catch (Exception e) {

		}
		try {

			res = restTemplate.postForObject(env.getEmployeeUrl() + "rest-approve-reimbrusement-rem",
					inventoryItemRequisitionModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		
		
		logger.info("Method : approveItemRequisition function Ends");
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("reimbursement-master-data-through-ajax")
	public @ResponseBody List<ReimbursementModel> reimbruseemntThroughAjax(Model model, HttpServletRequest request,HttpSession session) {
		logger.info("Method : reimbruseemntThroughAjax starts");

		JsonResponse<List<ReimbursementModel>> jsonResponse = new JsonResponse<List<ReimbursementModel>>();

		try {

			jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "get-vendor-list-reimbrusement", JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ReimbursementModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ReimbursementModel>>() {
					});
			String dateFormat=(String)(session).getAttribute("DATEFORMAT");
			for(ReimbursementModel m : addreq) {
				String date=m.getCreatedOn();
				if(date!=null && date!="") {
					date=DateFormatter.dateFormat(date, dateFormat);
					System.out.println(date);
					m.setCreatedOn(date);
				}
				
			}
			
			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}
System.out.println("jsonResponse.getBody()"+jsonResponse.getBody());
		logger.info("Method ; reimbruseemntThroughAjax ends");

		return jsonResponse.getBody();
	}
}