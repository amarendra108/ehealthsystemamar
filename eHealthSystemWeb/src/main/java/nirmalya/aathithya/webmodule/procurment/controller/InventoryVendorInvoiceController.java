package nirmalya.aathithya.webmodule.procurment.controller;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
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

import nirmalya.aathithya.webmodule.common.utils.ActivitylogModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.procurment.model.InventoryActionInvoiceModel;
import nirmalya.aathithya.webmodule.procurment.model.InventoryProductModel;
import nirmalya.aathithya.webmodule.procurment.model.InventoryRequisitionModel;
import nirmalya.aathithya.webmodule.procurment.model.InventoryVendorDocumentModel;

/*
 * @author NirmalyaLabs
 *
 */
@Controller
@RequestMapping(value = "inventory/")
public class InventoryVendorInvoiceController {

	Logger logger = LoggerFactory.getLogger(InventoryVendorInvoiceController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	@GetMapping("vendor-invoice")
	public String getInvoiceViewPage(Model model, HttpSession session) {

		logger.info("Method : getInvoiceViewPage starts");

		/**
		 * get DropDown value for Requisition Type
		 *
		 */

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-action-vendor-list",
					DropDownModel[].class);
			List<DropDownModel> vendorList = Arrays.asList(dropDownModel);
			model.addAttribute("vendorList", vendorList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-payment-status",
					DropDownModel[].class);
			List<DropDownModel> paymentStatusList = Arrays.asList(dropDownModel);
			model.addAttribute("paymentStatusList", paymentStatusList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		/**
		 * get DropDown value for Requisition Type
		 *
		 */

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-location",
					DropDownModel[].class);
			List<DropDownModel> locationList = Arrays.asList(dropDownModel);
			model.addAttribute("locationList", locationList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		/**
		 * get DropDown value for Requisition Type
		 *
		 */

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-uom",
					DropDownModel[].class);
			List<DropDownModel> uomList = Arrays.asList(dropDownModel);
			model.addAttribute("uomList", uomList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		/**
		 * payment term master
		 */
		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-Payment-term",
					DropDownModel[].class);
			List<DropDownModel> paytermList = Arrays.asList(dropDownModel);
			model.addAttribute("paytermList", paytermList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : getInvoiceViewPage ends");
		return "procurement/vendor-invoice";

	}

	/*
	 * drop down for get vendor location
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/vendor-invoice-vendor-loc-change" })
	public @ResponseBody JsonResponse<DropDownModel> getVendorLocChange(Model model, @RequestBody String index,
			BindingResult result) {
		logger.info("Method : getVendorLocChange List starts");

		JsonResponse<DropDownModel> res = new JsonResponse<DropDownModel>();

		try {
			res = restTemplate.getForObject(env.getInventoryUrl() + "getVendorLocChange?vendorId=" + index,
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

		logger.info("Method :getVendorLocChange List starts  ends");
		return res;
	}

	/*
	 * drop down for get vendor contact
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/vendor-invoice-vendor-contact-change" })
	public @ResponseBody JsonResponse<DropDownModel> getVendorContactChange(Model model, @RequestBody String index,
			BindingResult result) {
		logger.info("Method : getVendorContactChange List starts");

		JsonResponse<DropDownModel> res = new JsonResponse<DropDownModel>();

		try {
			res = restTemplate.getForObject(env.getInventoryUrl() + "getVendorContactChange?vendorId=" + index,
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

		logger.info("Method :getVendorContactChange List starts  ends");
		return res;
	}

	/*
	 * view throughAjax
	 * 
	 * 
	 */
	@GetMapping(value = { "vendor-invoice-activity-log" })
	public @ResponseBody List<ActivitylogModel> getActivityLog(@RequestParam String id, HttpSession session) {
		logger.info("Method : viewStockThroughAjax starts");
		List<ActivitylogModel> activityLogList = new ArrayList<ActivitylogModel>();
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		System.out.println("id " + id);
		try {

			ActivitylogModel[] activityLog = restTemplate
					.getForObject(env.getInventoryUrl() + "get-activity-log?id=" + id, ActivitylogModel[].class);
			activityLogList = Arrays.asList(activityLog);

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (ActivitylogModel m : activityLogList) {

			if (m.getOperationOn() != null && m.getOperationOn() != "") {
				m.setOperationOn(DateFormatter.dateFormat(m.getOperationOn(), dateFormat));
			}
		}
		logger.info("Method : viewStockThroughAjax ends");
		return activityLogList;
	}

	/*
	 * post Mapping for add po
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "vendor-invoice-save-th-ajax")
	public @ResponseBody JsonResponse<Object> saveInvoice(
			@RequestBody InventoryActionInvoiceModel inventoryActionInvoiceModel, HttpSession session) {
		logger.info("Method : saveInvoice function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();

		List<InventoryVendorDocumentModel> documentList = new ArrayList<InventoryVendorDocumentModel>();
		String userId = "";
		String dateFormat = "";
		String vendorId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
			vendorId = (String) session.getAttribute("VENDOR_ID");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		inventoryActionInvoiceModel.setCreatedBy(userId);
		inventoryActionInvoiceModel.setIsSaveByVendor(vendorId);
		inventoryActionInvoiceModel
				.setDueDate(DateFormatter.inputDateFormat(inventoryActionInvoiceModel.getDueDate(), dateFormat));
		inventoryActionInvoiceModel
				.setInvDate(DateFormatter.inputDateFormat(inventoryActionInvoiceModel.getInvDate(), dateFormat));
		for (InventoryVendorDocumentModel a : inventoryActionInvoiceModel.getDocumentList()) {
			if (a.getImageNameEdit() != null && a.getImageNameEdit() != "") {
				a.setFileName(a.getImageNameEdit());
			} else {
				if (a.getFileName() != null && a.getFileName() != "") {
					String delimiters = "\\.";
					String[] x = a.getFileName().split(delimiters);

					if (x[1].contentEquals("png") || x[1].contentEquals("jpg") || x[1].contentEquals("jpeg")) {

						for (String s1 : a.getDocumentFile()) {
							if (s1 != null)
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String imageName = fileUpload.saveAllImage(bytes);
									a.setFileName(imageName);
								} catch (Exception e) {
									e.printStackTrace();
								}
						}
					} else if (x[1].contentEquals("pdf")) {
						for (String s1 : a.getDocumentFile()) {
							try {
								byte[] bytes = Base64.getDecoder().decode(s1);
								String pdfName = fileUpload.saveAllPdf(bytes);
								a.setFileName(pdfName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else if (x[1].contentEquals("docx")) {
						for (String s1 : a.getDocumentFile()) {
							try {
								byte[] bytes = Base64.getDecoder().decode(s1);
								String pdfName = fileUpload.saveAllDocx(bytes);
								a.setFileName(pdfName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else if (x[1].contentEquals("doc")) {
						for (String s1 : a.getDocumentFile()) {
							try {
								byte[] bytes = Base64.getDecoder().decode(s1);
								String pdfName = fileUpload.saveAllDoc(bytes);
								a.setFileName(pdfName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else if (x[1].contentEquals("xls")) {
						for (String s1 : a.getDocumentFile()) {
							try {
								byte[] bytes = Base64.getDecoder().decode(s1);
								String pdfName = fileUpload.saveAllXls(bytes);
								a.setFileName(pdfName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else if (x[1].contentEquals("xlsx")) {
						for (String s1 : a.getDocumentFile()) {
							try {
								byte[] bytes = Base64.getDecoder().decode(s1);
								String pdfName = fileUpload.saveAllXlsx(bytes);
								a.setFileName(pdfName);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		try {
			res = restTemplate.postForObject(env.getInventoryUrl() + "rest-add-action-invoice",
					inventoryActionInvoiceModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		InventoryActionInvoiceModel form = mapper.convertValue(res.getBody(),
				new TypeReference<InventoryActionInvoiceModel>() {
				});

		if (form.getActiveDate() != null) {
			form.setActiveDate(DateFormatter.dateFormat(form.getActiveDate(), dateFormat));
		}
		if (form.getCompleteDate() != null) {
			form.setCompleteDate(DateFormatter.dateFormat(form.getCompleteDate(), dateFormat));
		}
		if (form.getCreatedOn() != null) {
			form.setCreatedOn(DateFormatter.dateFormat(form.getCreatedOn(), dateFormat));
		}
		if (form.getDueDate() != null) {
			form.setDueDate(DateFormatter.dateFormat(form.getDueDate(), dateFormat));
		}
		if (form.getInvDate() != null) {
			form.setInvDate(DateFormatter.dateFormat(form.getInvDate(), dateFormat));
		}
		if (form.getOnholdDate() != null) {
			form.setOnholdDate(DateFormatter.dateFormat(form.getOnholdDate(), dateFormat));
		}
		if (form.getPayemtDueDate() != null) {
			form.setPayemtDueDate(DateFormatter.dateFormat(form.getPayemtDueDate(), dateFormat));
		}

		if (form != null) {
			for (InventoryProductModel a : form.getProductList()) {
				if (a.getCreatedOn() != null) {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}
			}
			documentList = form.getDocumentList();
			if (documentList != null) {
				for (InventoryVendorDocumentModel m : documentList) {
					if (m.getFileName() != null && m.getFileName() != "") {

						String[] extension = m.getFileName().split("\\.");
						if (extension.length == 2) {
							if (extension[1].equals("xls") || extension[1].equals("xlsx")) {

								String docPath = "<i class=\"fa fa-file-excel-o excel\" title= " + m.getFileName()
										+ "></i> ";

								m.setAction(docPath);
							}
							if (extension[1].equals("pdf")) {
								String docPath = " <i class=\"fa fa-file-pdf-o excel pdf\"   title=" + m.getFileName()
										+ " ;></i> ";

								m.setAction(docPath);
							}
							if (extension[1].equals("doc") || extension[1].equals("dox")
									|| extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg")
									|| extension[1].equals("jpeg")) {
								String docPath = " <i class=\"fa fa-picture-o \"\" aria-hidden=\"true\" title="
										+ m.getFileName() + "></i>  ";
								m.setAction(docPath);
							}
						} else {
							m.setAction("N/A");
						}
					} else {
						m.setAction("N/A");
					}
					m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}
		}
		res.setBody(form);
		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : saveInvoice function Ends");
		return res;
	}

	/*
	 * drop down for get vendor contact
	 */
	@GetMapping(value = { "/vendor-invoice-vendor-add-days-to-inddate" })
	public @ResponseBody JsonResponse<DropDownModel> calculateInvDate(@RequestParam String invDate,
			@RequestParam String days, HttpSession session) {
		logger.info("Method : calculateInvDate List starts");
		String dateFormat = (String) session.getAttribute("DATEFORMAT");
		JsonResponse<DropDownModel> res = new JsonResponse<DropDownModel>();
		String format = DateFormatter.inputDateFormat(invDate, dateFormat);
		String[] splitDate = format.split("-");
		DropDownModel dropDownModel = new DropDownModel();

		LocalDate date4 = LocalDate
				.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]))
				.plusDays(Integer.parseInt(days));

		String indDueDate = DateFormatter.dateFormat(date4.toString(), dateFormat);

		String pattern = "yyyy-MM-dd";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());

		LocalDate d2 = LocalDate.parse(dateInString, DateTimeFormatter.ISO_LOCAL_DATE);
		Duration diff = Duration.between(d2.atStartOfDay(), date4.atStartOfDay());
		Long diffDays = diff.toDays();
		dropDownModel.setKey(indDueDate);
		dropDownModel.setName(diffDays + " Days");
		res.setBody(dropDownModel);
		logger.info("Method :calculateInvDate List starts  ends");
		return res;
	}

	/*
	 * view po through ajax
	 * 
	 * 
	 */
	@GetMapping(value = { "vendor-invoice-get-invoice-list" })
	public @ResponseBody List<InventoryActionInvoiceModel> getPoListThrowAjax(HttpSession session) {
		logger.info("Method : getPoListThrowAjax starts");
		List<InventoryActionInvoiceModel> inventoryPoModelList = new ArrayList<InventoryActionInvoiceModel>();
		String dateFormat = (String) session.getAttribute("DATEFORMAT");
		try {
			String vendorId = (String) session.getAttribute("VENDOR_ID");
			InventoryActionInvoiceModel[] inventoryPoModel = restTemplate.getForObject(
					env.getInventoryUrl() + "get-vendor-invoice-view-list?vendorId=" + vendorId,
					InventoryActionInvoiceModel[].class);
			inventoryPoModelList = Arrays.asList(inventoryPoModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (InventoryActionInvoiceModel a : inventoryPoModelList) {
			if (a.getActiveDate() != null && a.getActiveDate() != "") {
				a.setActiveDate(DateFormatter.dateFormat(a.getActiveDate(), dateFormat));
			}
			if (a.getInvDate() != null && a.getInvDate() != "") {
				a.setInvDate(DateFormatter.dateFormat(a.getInvDate(), dateFormat));
			}
			if (a.getCompleteDate() != null && a.getCompleteDate() != "") {
				a.setCompleteDate(DateFormatter.dateFormat(a.getCompleteDate(), dateFormat));
			}
			if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
				a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
			}
			if (a.getDueDate() != null && a.getDueDate() != "") {
				a.setDueDate(DateFormatter.dateFormat(a.getDueDate(), dateFormat));
			}
			if (a.getOnholdDate() != null && a.getOnholdDate() != "") {
				a.setOnholdDate(DateFormatter.dateFormat(a.getOnholdDate(), dateFormat));
			}
			if (a.getPayemtDueDate() != null && a.getPayemtDueDate() != "") {
				a.setPayemtDueDate(DateFormatter.dateFormat(a.getPayemtDueDate(), dateFormat));
			}

		}
		logger.info("Method : getPoListThrowAjax ends");
		return inventoryPoModelList;
	}

	/*
	 * for copy
	 * 
	 * 
	 */
	@GetMapping(value = { "vendor-invoice-edit-trough-ajax" })
	public @ResponseBody InventoryActionInvoiceModel viewPoEdit(@RequestParam String id, HttpSession session) {
		logger.info("Method : viewPoEdit starts");
		InventoryActionInvoiceModel productList = new InventoryActionInvoiceModel();
		List<InventoryVendorDocumentModel> documentList = new ArrayList<InventoryVendorDocumentModel>();
		String dateFormat = (String) session.getAttribute("DATEFORMAT");
		if (id != null && id != "") {
			try {
				productList = restTemplate.getForObject(env.getInventoryUrl() + "get-invoice-edit?id=" + id,
						InventoryActionInvoiceModel.class);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (productList.getActiveDate() != null && productList.getActiveDate() != "") {
			productList.setActiveDate(DateFormatter.dateFormat(productList.getActiveDate(), dateFormat));
		}
		if (productList.getInvDate() != null && productList.getInvDate() != "") {
			productList.setInvDate(DateFormatter.dateFormat(productList.getInvDate(), dateFormat));
		}
		if (productList.getCompleteDate() != null && productList.getCompleteDate() != "") {
			productList.setCompleteDate(DateFormatter.dateFormat(productList.getCompleteDate(), dateFormat));
		}
		if (productList.getCreatedOn() != null && productList.getCreatedOn() != "") {
			productList.setCreatedOn(DateFormatter.dateFormat(productList.getCreatedOn(), dateFormat));
		}
		if (productList.getDueDate() != null && productList.getDueDate() != "") {
			productList.setDueDate(DateFormatter.dateFormat(productList.getDueDate(), dateFormat));
		}
		if (productList.getOnholdDate() != null && productList.getOnholdDate() != "") {
			productList.setOnholdDate(DateFormatter.dateFormat(productList.getOnholdDate(), dateFormat));
		}
		if (productList.getPayemtDueDate() != null && productList.getPayemtDueDate() != "") {
			productList.setPayemtDueDate(DateFormatter.dateFormat(productList.getPayemtDueDate(), dateFormat));
		}

		if (productList != null) {
			for (InventoryProductModel a : productList.getProductList()) {
				if (a.getCreatedOn() != null) {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}
			}
			documentList = productList.getDocumentList();
			
			for(InventoryProductModel a :productList.getProductList() ) {
				if(a.getCreatedOn() != null && a.getCreatedOn() !="") {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}
			}
			 
			if (documentList != null) {
				for (InventoryVendorDocumentModel m : documentList) {
					if (m.getFileName() != null && m.getFileName() != "") {

						String[] extension = m.getFileName().split("\\.");
						if (extension.length == 2) {
							if (extension[1].equals("xls") || extension[1].equals("xlsx")) {

								String docPath = "<i class=\"fa fa-file-excel-o excel\" title= " + m.getFileName()
										+ "></i> ";

								m.setAction(docPath);
							}
							if (extension[1].equals("pdf")) {
								String docPath = " <i class=\"fa fa-file-pdf-o excel pdf\"   title=" + m.getFileName()
										+ " ;></i> ";

								m.setAction(docPath);
							}
							if (extension[1].equals("doc") || extension[1].equals("dox")
									|| extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg")
									|| extension[1].equals("jpeg")) {
								String docPath = " <i class=\"fa fa-picture-o \"\" aria-hidden=\"true\" title="
										+ m.getFileName() + "></i>  ";
								m.setAction(docPath);
							}
						} else {
							m.setAction("N/A");
						}
					} else {
						m.setAction("N/A");
					}
					m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}
			productList.setDocumentList(documentList);
		}
		logger.info("Method : viewPoEdit ends");
		return productList;
	}

	/**
	 * Web Controller - Get Item List By AutoSearch
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/vendor-invoice-get-product-list" })
	public @ResponseBody JsonResponse<InventoryRequisitionModel> getItemAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getItemAutoSearchList starts");

		JsonResponse<InventoryRequisitionModel> res = new JsonResponse<InventoryRequisitionModel>();

		try {
			res = restTemplate.getForObject(env.getInventoryUrl() + "getProductListByAutoSearch?id=" + searchValue,
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

		logger.info("Method : getItemAutoSearchList ends");
		return res;
	}

	/*
	 * post Mapping for add po
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "vendor-invoice-save-dn-th-ajax")
	public @ResponseBody JsonResponse<Object> saveDN(
			@RequestBody InventoryActionInvoiceModel inventoryActionInvoiceModel, HttpSession session) {
		logger.info("Method : saveDN function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
		String vendorId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
			vendorId = (String) session.getAttribute("VENDOR_ID");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		inventoryActionInvoiceModel.setCreatedBy(userId);
		inventoryActionInvoiceModel.setIsSaveByVendor(vendorId);
		inventoryActionInvoiceModel
				.setDueDate(DateFormatter.inputDateFormat(inventoryActionInvoiceModel.getDueDate(), dateFormat));
		inventoryActionInvoiceModel
				.setInvDate(DateFormatter.inputDateFormat(inventoryActionInvoiceModel.getInvDate(), dateFormat));

		try {
			res = restTemplate.postForObject(env.getInventoryUrl() + "rest-add-dn", inventoryActionInvoiceModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : saveDN function Ends");
		return res;
	}

}
