package nirmalya.aathithya.webmodule.customer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.customer.model.CustomerNewModel;



@Controller
@RequestMapping(value = { "customer/" })
public class CustomerNewController {

	Logger logger = LoggerFactory.getLogger(CustomerNewController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	CustomerNewController customerNewController;

	@GetMapping(value = { "/view-customer" })
	public String customerDetails(Model model, HttpSession session) {
		logger.info("Method : customerDetails starts");
		
		
		try {

			DropDownModel[] country = restTemplate.getForObject(env.getCustomerUrl() + "get-custcountry-list",
					DropDownModel[].class);
			List<DropDownModel> countrylist = Arrays.asList(country);

			model.addAttribute("countrylist", countrylist);
		} catch (Exception e) {
			e.printStackTrace();

		}
		 
		logger.info("Method : customerDetails ends");
		return "customer/view-customer";
	}
	
	
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "view-customer-stateName-ajax" })
	public @ResponseBody JsonResponse<Object> getstateList(@RequestParam String id) {
		logger.info("Method : getstateListAJAX starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getCustomerUrl() + "rest-get-custstateList-New?id=" + id,
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
		//System.out.println("state" + res);
		logger.info("Method : getstateListAJAX ends");
		return res;
	}
	 

	
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "view-customer-district-ajax" })
	public @ResponseBody JsonResponse<Object> getdistList(@RequestParam String id) {
		logger.info("Method : getdistListAJAX starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getCustomerUrl() + "rest-get-custdistList-New?id=" + id,
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
		//System.out.println("district" + res);
		logger.info("Method : getdistListAJAX ends");
		return res;
	}
	
	
	@SuppressWarnings("unchecked")

	@PostMapping("view-customer-add")
	public @ResponseBody JsonResponse<Object> addcustomer(Model model, HttpSession session,

			@RequestBody CustomerNewModel customerNewModel) {
		logger.info("Method : addcustomer starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		System.out.println(customerNewModel);
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		customerNewModel.setCustCreatedBy(userId);
		
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				customerNewModel.setFileCustomer(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			resp = restTemplate.postForObject(env.getCustomerUrl() + "addcustomer", customerNewModel,
					JsonResponse.class);
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

		logger.info("Method : addcustomer ends");
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


	@SuppressWarnings("unchecked")

	@GetMapping("view-customer-through-ajax")
	public @ResponseBody List<CustomerNewModel> viewcustomer(HttpSession session) {

		logger.info("Method :viewcustomer starts");

		JsonResponse<List<CustomerNewModel>> resp = new JsonResponse<List<CustomerNewModel>>();
		
		try {

			resp = restTemplate.getForObject(env.getCustomerUrl() + "getAllcustomer", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<CustomerNewModel> customerNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<CustomerNewModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (CustomerNewModel a : customerNewModel) {
			if (a.getCustUpdatedOn() != null && a.getCustUpdatedOn() != "") {
				a.setCustUpdatedOn(DateFormatter.dateFormat(a.getCustUpdatedOn(), dateFormat));
			}
			
		}

		resp.setBody(customerNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewcustomer ends");
		System.out.println("RESPONSE" + resp);
		return resp.getBody();
	}

	
	
	  
	
	
	@SuppressWarnings("unchecked")

	@GetMapping("view-customer-edit")
	public @ResponseBody JsonResponse<CustomerNewModel> editcustomer(Model model, @RequestParam String id,
			HttpSession session) {

		logger.info("Method : editcustomer starts");

		JsonResponse<CustomerNewModel> jsonResponse = new JsonResponse<CustomerNewModel>();
		System.out.println(id);
		try {
			jsonResponse = restTemplate.getForObject(env.getCustomerUrl() + "editcustomer?id=" + id, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			CustomerNewModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<CustomerNewModel>() {
					});
		System.out.println("customerNewModel.getFileCustomer()" +customerNewModel.getFileCustomer());
		if (customerNewModel.getFileCustomer() != null && customerNewModel.getFileCustomer() != ""
				&& !customerNewModel.getFileCustomer().equals("null")) {
			String FileCustomer = env.getBaseURL() + "document/module/" + customerNewModel.getFileCustomer();
				
			customerNewModel.setFileCustomer(FileCustomer);
		
		}
		
		jsonResponse.setBody(customerNewModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		System.out.println("REsp" + jsonResponse);
		logger.info("Method : editcustomer ends");
		return jsonResponse;
	}
	
	@PostMapping("view-customer-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");

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
		return response;
	}
	
	
	@SuppressWarnings("unchecked")

	@GetMapping("view-customer-delete")
	public @ResponseBody JsonResponse<CustomerNewModel> deleteCustomer(Model model, @RequestParam String deleteId,
			
			HttpSession session) {
		logger.info("Method : deleteCustomer starts");

		JsonResponse<CustomerNewModel> resp = new JsonResponse<CustomerNewModel>();
		System.out.println(deleteId);
		
		
		String userId = "";

		try {
		userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
		e.printStackTrace();
		}

		try {
		resp = restTemplate.getForObject(env.getCustomerUrl() + "deleteCustomer?id="+deleteId+"&createdBy="+userId,
		JsonResponse.class);

		} catch (RestClientException e) {
		e.printStackTrace();
		}
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}

		logger.info("Method :  deleteCustomer ends");

		System.out.println("resp" +resp);
		return resp;
	}
	
	@PostMapping("/manage-location-delete-file")
	public @ResponseBody JsonResponse<Object> deleteFile(HttpSession session) {
		logger.info("Method : deleteFile controller function 'post-mapping' starts");
		
		JsonResponse<Object> response = new JsonResponse<Object>();
		
		try {
			session.removeAttribute("quotationPFile");
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : deleteFile controller function 'post-mapping' ends");
		return response;
	}
	
}
