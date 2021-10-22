package nirmalya.aathithya.webmodule.customer.controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.customer.model.CustomerNewModel;
import nirmalya.aathithya.webmodule.customer.model.QuotationNewModel;
import nirmalya.aathithya.webmodule.customer.model.SalesOrderNewModel;
import nirmalya.aathithya.webmodule.master.model.ProductCategoryModel;

import nirmalya.aathithya.webmodule.procurment.model.InventorySkuProductModel;


@Controller
@RequestMapping(value = { "customer/" })
public class QuotationNewController {
	
	Logger logger = LoggerFactory.getLogger(QuotationNewController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	QuotationNewController quotationNewController;

	@Autowired
	FileUpload fileUpload;

	@GetMapping(value = { "/view-quotation" })
	public String customerDetails(Model model, HttpSession session) {
		logger.info("Method : customerDetails starts");
		
		
		
		logger.info("Method : customerDetails ends");
		return "customer/view-quotation";
	}

	/*
	 * view
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-quotation-through-ajax")
	public @ResponseBody List<QuotationNewModel> viewquotation(HttpSession session) {

		logger.info("Method :viewcustomer starts");

		JsonResponse<List<QuotationNewModel>> resp = new JsonResponse<List<QuotationNewModel>>();
			
		try {

			resp = restTemplate.getForObject(env.getCustomerUrl() + "getAllquotation", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<QuotationNewModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<QuotationNewModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (QuotationNewModel a : quotationNewModel) {
			if (a.getQutValidDate() != null && a.getQutValidDate() != "") {
				a.setQutValidDate(DateFormatter.dateFormat(a.getQutValidDate(), dateFormat));
			}

			
			  if (a.getQutUpdatedOn() != null && a.getQutUpdatedOn() != "") {
			  a.setQutUpdatedOn(DateFormatter.dateFormat(a.getQutUpdatedOn(), dateFormat));
			  }
			  
			  if (a.getPoDate() != null && a.getPoDate() != "") {
				  a.setPoDate(DateFormatter.dateFormat(a.getPoDate(), dateFormat));
				  }
			  if (a.getsOrderDate() != null && a.getsOrderDate() != "") {
				  a.setsOrderDate(DateFormatter.dateFormat(a.getsOrderDate(), dateFormat));
				  }
			  
			  
			//System.out.println(quotationNewModel); 

		}

		resp.setBody(quotationNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewcustomer ends");
		//System.out.println("RESPONSE" + resp);
		return resp.getBody();
	}

	@SuppressWarnings("unchecked")

	@GetMapping("view-quotation-item-new-trough-ajax")
	public @ResponseBody List<QuotationNewModel> viewquotationItem(HttpSession session) {

		logger.info("Method :viewquotationItem starts");

		JsonResponse<List<QuotationNewModel>> resp = new JsonResponse<List<QuotationNewModel>>();
			
		try {

			resp = restTemplate.getForObject(env.getCustomerUrl() + "getAllquotationItem", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<QuotationNewModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<QuotationNewModel>>() {
				});
		int count = 0;
		for(QuotationNewModel m : quotationNewModel) {
		count ++;
		m.setSlNo(count);
		//System.out.println("QuotationNewModel@" +count);
		}
		
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewquotationItem ends");
		//System.out.println("RESPONSE" + resp);
		return resp.getBody();
	}

	
	/*
	 * customer autoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-quotation-get-customer-list" })
	public @ResponseBody JsonResponse<QuotationNewModel> getCustomerAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getCustomerAutoSearchList starts");
			//System.out.println("QuotationNewModel"+searchValue);
		JsonResponse<QuotationNewModel> res = new JsonResponse<QuotationNewModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl()+ "getCustomerListByAutoSearch?id=" + searchValue,
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
		//System.out.println("RESPONSE@@" + res);
		logger.info("Method : getCustomerAutoSearchList ends");
		return res;
	}

	/*
	 * Item autosearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-quotation-item-get-customer-list" })
	public @ResponseBody JsonResponse<QuotationNewModel> getItemQuotationAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getItemQuotationAutoSearchList starts");
			System.out.println("QuotationNewModel"+searchValue);
		JsonResponse<QuotationNewModel> res = new JsonResponse<QuotationNewModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl()+ "getItemQuotationAutoSearchList?id=" + searchValue,
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
		System.out.println("RESPONSE@@" + res);
		logger.info("Method : getItemQuotationAutoSearchList ends");
		return res;
	}

	/*
	 * Add
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("view-quotation-add")
	public @ResponseBody JsonResponse<Object> addquotationnew(HttpSession session,
			@RequestBody List<QuotationNewModel> quotationNewModel) {
		logger.info("Method : addquotation starts");
		System.out.println(quotationNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			//System.out.println("CReatedBYYYYYY"+userId);
			dateFormat = (String) session.getAttribute("DATEFORMAT");

		} catch (Exception e) {

		}
		for (QuotationNewModel m : quotationNewModel) {
			m.setQutCreatedBy(userId);
			
			m.setQutValidDate(DateFormatter.inputDateFormat(m.getQutValidDate(), dateFormat));
		}

		try {
			resp = restTemplate.postForObject(env.getCustomerUrl() + "addquotationnew", quotationNewModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			
			  List<QuotationNewModel> quotation = mapper.convertValue(resp.getBody(), 
					  new TypeReference<List<QuotationNewModel>>() {
			  });
			 
			for (QuotationNewModel m : quotationNewModel) {
				if (m.getQutValidDate() != null && m.getQutValidDate() != "") {
					m.setQutValidDate(DateFormatter.dateFormat(m.getQutValidDate(), dateFormat));
				}
			}

			resp.setBody(quotation);
			System.out.println(quotationNewModel);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("Sradha" + resp);

		logger.info("Method : addquotation ends");

		return resp;
	}
	
	//get Product Category Data List Modal

	@SuppressWarnings("unchecked")
	@PostMapping("view-quotation-item-get-product-list")
	public @ResponseBody JsonResponse<List<ProductCategoryModel>> getProductCategoryListModal(
			@RequestBody String yearDtls, HttpSession session) {
		logger.info("Method : getProductCategoryListModal starts");

		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();
		//System.out.println(yearDtls);
		try {
			resp = restTemplate.getForObject(env.getInventoryUrl() + "getProductCategoryDataListModal",
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}
			//System.out.println("resp@@@@@@@"+ resp);
		logger.info("Method : getProductCategoryListModal starts");
		return resp;
	}

	
	// grt product by cat

	@SuppressWarnings("unchecked")

	@PostMapping(value = { "view-quotation-item-product-by-cat" })
	public @ResponseBody JsonResponse<InventorySkuProductModel> getProductsByCat(Model model, @RequestBody String index,
			BindingResult result) {
		logger.info("Method : getProductsByCat starts");
		//System.out.println(index);
		String indexValue = index.substring(0, index.length() - 1);

		JsonResponse<InventorySkuProductModel> res = new JsonResponse<InventorySkuProductModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl() + "getProductsByCat?id=" + indexValue, JsonResponse.class);
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
		logger.info("Method : getProductsByCat ends");
		return res;
	}
	
	
	
	/*
	* for editing using employee id
	*
	*
	*/
	@GetMapping(value = { "view-quotation-edit-new" })
	public @ResponseBody List<QuotationNewModel> viewQuotationEdit(@RequestParam String id,
			HttpSession session) {
		logger.info("Method : viewQuotationEdit starts");
		 System.out.println(id);
		List<QuotationNewModel> productList = new ArrayList<QuotationNewModel>();

		if (id != null && id != "") {
			try {
				QuotationNewModel[] quotationNewModel = restTemplate.getForObject(
						env.getCustomerUrl() + "viewQuotationEdit?id=" + id, QuotationNewModel[].class);

				productList = Arrays.asList(quotationNewModel);

				productList.forEach(s -> s.setSlNo(s.getSlNo()));

				int count = 0;

				for (QuotationNewModel m : quotationNewModel) {

					count++;
					m.setSlNo(count);

					String dateFormat = (String) session.getAttribute("DATEFORMAT");

					if (m.getQutValidDate() != null && m.getQutValidDate() != "") {
						m.setQutValidDate(DateFormatter.dateFormat(m.getQutValidDate(), dateFormat));

					}
				}

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Method : viewQuotationEdit ends");
		System.out.println("edit@@@@@@@@" + productList);
		return productList;
	}
	/*
	 * Delete
	 */
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "view-quotation-delete")
	public @ResponseBody JsonResponse<Object> deleteItemQuotation(
			@RequestBody QuotationNewModel quotationNewModel, HttpSession session) {
		logger.info("Method : deleteItemQuotation starts");
		System.out.println("quotationNewModel"+quotationNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			quotationNewModel.setQutCreatedBy(userId);
		} catch (Exception e) {

		}
		try {

			resp = restTemplate.postForObject(env.getCustomerUrl() + "deleteItemQuotation",
					quotationNewModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}
		System.out.println("delete@" + resp);
		logger.info("Method : deleteItemQuotation Ends");
		return resp;
	}
	
	/*
	 * save image
	 */
	
	@PostMapping("view-quotation-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");
		System.out.println("MultipartFile"+inputFile);
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
		System.out.println("img"+response);
		return response;
	}

	
	@SuppressWarnings("unchecked")

	@PostMapping("view-quotation-po-add")
	public @ResponseBody JsonResponse<Object> addpoNo(Model model, HttpSession session,

			@RequestBody QuotationNewModel quotationNewModel) {
		logger.info("Method : addpoNo starts");
		System.out.println("QuotationNewModel"+quotationNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		quotationNewModel.setQutCreatedBy(userId);
		
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				quotationNewModel.setFilePoSale(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			resp = restTemplate.postForObject(env.getCustomerUrl() + "addpoNo", quotationNewModel,
					JsonResponse.class);
			
			ObjectMapper mapper = new ObjectMapper();
			
			  List<QuotationNewModel> quotation = mapper.convertValue(resp.getBody(), 
					  new TypeReference<List<QuotationNewModel>>() {
			  });
			 
			
				if (quotationNewModel.getPoDate() != null && quotationNewModel.getPoDate() != "") {
					quotationNewModel.setPoDate(DateFormatter.inputDateFormat(quotationNewModel.getPoDate(), dateFormat));
				}
			
			resp.setBody(quotation);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			session.removeAttribute("quotationPFile");
			resp.setMessage("Success");
		}

		logger.info("Method : addpoNo ends");
		System.out.println("resp" + resp);
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

	}

