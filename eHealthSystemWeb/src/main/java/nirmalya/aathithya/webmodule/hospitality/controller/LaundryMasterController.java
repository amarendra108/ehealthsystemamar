package nirmalya.aathithya.webmodule.hospitality.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
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
import nirmalya.aathithya.webmodule.hospitality.model.HotelServiceModel;
import nirmalya.aathithya.webmodule.hospitality.model.LaundryItemCategoryModel;
import nirmalya.aathithya.webmodule.hospitality.model.LaundryItemModel;
import nirmalya.aathithya.webmodule.hospitality.model.LaundryItemPriceModel;
import nirmalya.aathithya.webmodule.hospitality.model.LaundryReturnInModel;
import nirmalya.aathithya.webmodule.hospitality.model.LaundryServiceModel;

/*
 * @author NirmalyaLabs
 *
 */
@Controller
@RequestMapping(value = "hospitality/")
public class LaundryMasterController {
	Logger logger = LoggerFactory.getLogger(LaundryMasterController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("master-laundry")
	public String viewLaundryMaster(Model model, HttpSession session) {

		logger.info("Method : viewLaundryMaster starts");
		// model.addAttribute("pageName", "Geography Coming Soon");
		// for Item Category

		try {
			DropDownModel[] dd = restTemplate.getForObject(env.getHospitalityUrl() + "getLaundryItemCatgry",
					DropDownModel[].class);
			List<DropDownModel> itemCtgryList = Arrays.asList(dd);
			// System.out.println("ITEMMM "+itemCtgry);
			model.addAttribute("itemCtgryList", itemCtgryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		// for Item Name
		try {
			DropDownModel[] dd1 = restTemplate.getForObject(env.getHospitalityUrl() + "getLaundItemName",
					DropDownModel[].class);
			List<DropDownModel> item = Arrays.asList(dd1);
			// System.out.println("ITEMMM "+itemCtgry);
			model.addAttribute("item", item);
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}

// Laundry Service
		try {
			DropDownModel[] dd2 = restTemplate.getForObject(env.getHospitalityUrl() + "getLaundryServiceName",
					DropDownModel[].class);
			List<DropDownModel> laundry = Arrays.asList(dd2); // System.out.println("ITEMMM "+itemCtgry);
			model.addAttribute("laundry", laundry);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
// Sales Subgroup List 

		try {
			DropDownModel[] dd3 = restTemplate.getForObject(env.getHospitalityUrl() + "getHotelServiceName",
					DropDownModel[].class);
			List<DropDownModel> hotel = Arrays.asList(dd3);
			// System.out.println("hotel " + hotel);
			model.addAttribute("hotel", hotel);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		// Sales Subgroup List

		try {
			DropDownModel[] dd4 = restTemplate.getForObject(env.getHospitalityUrl() + "getUserType",
					DropDownModel[].class);
			List<DropDownModel> userType = Arrays.asList(dd4);
			// System.out.println("hotel " + hotel);
			model.addAttribute("userType", userType);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : viewLaundryMaster ends");
		return "laundry/LaundryMaster";

	}

	/*
	 * Add
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")

	@PostMapping("master-laundry-add-laundry-servicess")
	public @ResponseBody JsonResponse<Object> addLaundryService(Model model, HttpSession session,
			@RequestBody LaundryServiceModel laundryModel) {
		logger.info("Method : addLaundryService starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;
		try {
			if(inputFile != null) {
				
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes,fileType[1]);
				System.out.println("IF=="+imageName);
			} else {
				imageName = (String) session.getAttribute("editFile");
				System.out.println("ELSE=="+imageName);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		laundryModel.setCreatedBy(userId);
		laundryModel.setLaundryImage(imageName);
		try {
			resp = restTemplate.postForObject(env.getHospitalityUrl() + "add-laundry-service", laundryModel,
					JsonResponse.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
			session.setAttribute("quotationPFile", null);
			session.removeAttribute("quotationPFile");
		}

		logger.info("Method : addLaundryService ends");
		return resp;
	}

	// view

	@SuppressWarnings("unchecked")

	@GetMapping("master-laundry-view-laundry-service-through-ajax")
	public @ResponseBody List<LaundryServiceModel> viewLaundryService(HttpSession session) {

		logger.info("Method : viewLaundryService starts");

		JsonResponse<List<LaundryServiceModel>> resp = new JsonResponse<List<LaundryServiceModel>>();

		try {
			resp = restTemplate.getForObject(env.getHospitalityUrl() + "view-laundry-service", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LaundryServiceModel> laundryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LaundryServiceModel>>() {
				});

		System.out.println("AAAAAAAA" + laundryModel);
		logger.info("Method : viewLaundryService ends");

		return laundryModel;
	}

	// delete

	@SuppressWarnings("unchecked")

	@GetMapping("master-laundry-delete-laundry-service")
	public @ResponseBody JsonResponse<LaundryServiceModel> deleteLaundryService(@RequestParam String deleteId) {

		logger.info("Method : deleteLaundryService starts");
		System.out.println("@@@@@" + deleteId);
		JsonResponse<LaundryServiceModel> response = new JsonResponse<LaundryServiceModel>();

		try {
			response = restTemplate.getForObject(env.getHospitalityUrl() + "delete-laundry-service?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		System.out.println("##@@@@" + response);
		logger.info("Method : deleteLaundryService ends");
		return response;
	}

	/*
	 * Add
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("master-laundry-add-item-category")
	public @ResponseBody JsonResponse<Object> addItemCategory(Model model, HttpSession session,
			@RequestBody LaundryItemCategoryModel lmModel) {
		logger.info("Method : addItemCategory starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		// System.out.println("bahuuu" + lmModel);
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		lmModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getHospitalityUrl() + "add-item-categories", lmModel,
					JsonResponse.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addItemCategory ends");
		// System.out.println("resp" + resp);
		return resp;
	}

	// view

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-view-item-category-through-ajax")
	public @ResponseBody List<LaundryItemCategoryModel> viewItemCategory(HttpSession session) {

		logger.info("Method : viewItemCategory starts");

		JsonResponse<List<LaundryItemCategoryModel>> resp = new JsonResponse<List<LaundryItemCategoryModel>>();

		try {
			resp = restTemplate.getForObject(env.getHospitalityUrl() + "view-item-categories", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LaundryItemCategoryModel> lmModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LaundryItemCategoryModel>>() {
				});

		// System.out.println("AAAAAAAA" + lmModel);
		logger.info("Method : viewItemCategory ends");

		return lmModel;
	}

	// delete

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-delete-item-category")
	public @ResponseBody JsonResponse<LaundryItemCategoryModel> deleteItemCategory(@RequestParam String deleteId) {

		logger.info("Method : deleteItemCategory starts");
		// System.out.println("@@@@@" + deleteId);
		JsonResponse<LaundryItemCategoryModel> response = new JsonResponse<LaundryItemCategoryModel>();

		try {
			response = restTemplate.getForObject(env.getHospitalityUrl() + "delete-item-categories?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		// System.out.println("##@@@@" + response);
		logger.info("Method : deleteItemCategory ends");
		return response;
	}

	/*
	 * Add
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("master-laundry-add-hotel-service")
	public @ResponseBody JsonResponse<Object> addHotelService(Model model, HttpSession session,
			@RequestBody HotelServiceModel lmModel) {
		logger.info("Method : addHotelService starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		System.out.println("bahuuu" + lmModel);
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		lmModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getHospitalityUrl() + "add-hotel-service", lmModel, JsonResponse.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addHotelService ends");
		System.out.println("resp" + resp);
		return resp;
	}

	// view

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-view-hotel-service-through-ajax")
	public @ResponseBody List<HotelServiceModel> viewHotelService(HttpSession session) {

		logger.info("Method : viewHotelService starts");

		JsonResponse<List<HotelServiceModel>> resp = new JsonResponse<List<HotelServiceModel>>();

		try {
			resp = restTemplate.getForObject(env.getHospitalityUrl() + "view-hotel-service", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HotelServiceModel> lmModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<HotelServiceModel>>() {
				});

		System.out.println("AAAAAAAA" + lmModel);
		logger.info("Method : viewHotelService ends");

		return lmModel;
	}

	// delete

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-delete-hotel-service")
	public @ResponseBody JsonResponse<HotelServiceModel> deleteHotelService(@RequestParam String deleteId) {

		logger.info("Method : deleteHotelService starts");
		System.out.println("@@@@@" + deleteId);
		JsonResponse<HotelServiceModel> response = new JsonResponse<HotelServiceModel>();

		try {
			response = restTemplate.getForObject(env.getHospitalityUrl() + "delete-hotel-service?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		System.out.println("##@@@@" + response);
		logger.info("Method : deleteHotelService ends");
		return response;
	}

	/*
	 * Add
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("master-laundry-add-laundry-item")
	public @ResponseBody JsonResponse<Object> addLitem(Model model, HttpSession session,
			@RequestBody LaundryItemModel itemModel) {
		logger.info("Method : addLitem starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFileItem");
		byte[] bytes;
		String imageName = null;
		try {
			if(inputFile != null) {
				
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes,fileType[1]);
				System.out.println("IF=="+imageName);
			} else {
				imageName = (String) session.getAttribute("editFileItem");
				System.out.println("ELSE=="+imageName);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		itemModel.setCreatedBy(userId);
		itemModel.setlItemimage(imageName);
		try {
			resp = restTemplate.postForObject(env.getHospitalityUrl() + "add-laundry-item", itemModel,
					JsonResponse.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
			session.setAttribute("quotationPFileItem", null);
			session.removeAttribute("quotationPFileItem");
		}

		logger.info("Method : addLitem ends");
		System.out.println("resp" + resp);
		return resp;
	}

	// view

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-view-laundry-item-through-ajax")
	public @ResponseBody List<LaundryItemModel> viewLitem(HttpSession session) {

		logger.info("Method : viewLitem starts");

		JsonResponse<List<LaundryItemModel>> resp = new JsonResponse<List<LaundryItemModel>>();

		try {
			resp = restTemplate.getForObject(env.getHospitalityUrl() + "view-laundry-item", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LaundryItemModel> itemModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LaundryItemModel>>() {
				});

		System.out.println("AAAAAAAA" + itemModel);
		logger.info("Method : viewLitem ends");

		return itemModel;
	}

	// delete

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-delete-laundry-item")
	public @ResponseBody JsonResponse<LaundryItemModel> deleteLitem(@RequestParam String deleteId) {

		logger.info("Method : deleteLitem starts");
		System.out.println("@@@@@" + deleteId);
		JsonResponse<LaundryItemModel> response = new JsonResponse<LaundryItemModel>();

		try {
			response = restTemplate.getForObject(env.getHospitalityUrl() + "delete-laundry-item?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		System.out.println("##@@@@" + response);
		logger.info("Method : deleteLitem ends");
		return response;
	}

	/*
	 * Add
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("master-laundry-add-return-in")
	public @ResponseBody JsonResponse<Object> addReturnIn(Model model, HttpSession session,
			@RequestBody LaundryReturnInModel returnModel) {
		logger.info("Method : addReturnIn starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		System.out.println("bahuuu" + returnModel);
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		returnModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getHospitalityUrl() + "add-return-in", returnModel, JsonResponse.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addReturnIn ends");
		System.out.println("resp" + resp);
		return resp;
	}

	// view

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-view-return-in-through-ajax")
	public @ResponseBody List<LaundryReturnInModel> viewReturnIn(HttpSession session) {

		logger.info("Method : viewReturnIn starts");

		JsonResponse<List<LaundryReturnInModel>> resp = new JsonResponse<List<LaundryReturnInModel>>();

		try {
			resp = restTemplate.getForObject(env.getHospitalityUrl() + "view-return-in", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LaundryReturnInModel> returnModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LaundryReturnInModel>>() {
				});

		System.out.println("AAAAAAAA" + returnModel);
		logger.info("Method : viewReturnIn ends");

		return returnModel;
	}

	// delete

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-delete-return-in")
	public @ResponseBody JsonResponse<LaundryReturnInModel> deleteReturnIn(@RequestParam String deleteId) {

		logger.info("Method : deleteReturnIn starts");
		System.out.println("@@@@@" + deleteId);
		JsonResponse<LaundryReturnInModel> response = new JsonResponse<LaundryReturnInModel>();

		try {
			response = restTemplate.getForObject(env.getHospitalityUrl() + "delete-return-in?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		System.out.println("##@@@@" + response);
		logger.info("Method : deleteReturnIn ends");
		return response;
	}

	/*
	 * Add
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("master-laundry-add-item-price")
	public @ResponseBody JsonResponse<Object> addItemPrice(Model model, HttpSession session,
			@RequestBody LaundryItemPriceModel returnModel) {
		logger.info("Method : addItemPrice starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		System.out.println("bahuuu" + returnModel);
		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

		} catch (Exception e) {
			e.printStackTrace();
		}
		String date = "";
		String oldDate = "";
		
		if(returnModel.getEffectiveDate() != null && returnModel.getEffectiveDate() != "") {
			date = DateFormatter.inputDateFormat(returnModel.getEffectiveDate(), dateFormat);
		}
		if(returnModel.getOldEffectiveDate() != null && returnModel.getOldEffectiveDate() != "") {
			oldDate = DateFormatter.inputDateFormat(returnModel.getOldEffectiveDate(), dateFormat);
		}
		
		returnModel.setCreatedBy(userId);
		returnModel.setEffectiveDate(date);
		returnModel.setOldEffectiveDate(oldDate);
		/*int i = 0;
		 * for (LaundryItemPriceModel a : returnModel) { i = i + 1; a.setEditID(i);
		 * a.setCreatedBy(userId); }
		 */
		try {
			resp = restTemplate.postForObject(env.getHospitalityUrl() + "add-item-price", returnModel, JsonResponse.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addItemPrice ends");
		System.out.println("resp" + resp);
		return resp;
	}

	// view

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-view-item-price-through-ajax")
	public @ResponseBody List<LaundryItemPriceModel> viewItemPrice(HttpSession session) {

		logger.info("Method : viewItemPrice starts");

		JsonResponse<List<LaundryItemPriceModel>> resp = new JsonResponse<List<LaundryItemPriceModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getHospitalityUrl() + "view-item-price", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LaundryItemPriceModel> returnModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LaundryItemPriceModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		int i = 0;
		for (LaundryItemPriceModel a : returnModel) {
			i = i + 1; 
			a.setEditID(i);
			System.out.println("GFFGGG" + a);
			
			if (a.getEffectiveDate() != null && a.getEffectiveDate() != "") {
				a.setEffectiveDate(DateFormatter.dateFormat(a.getEffectiveDate(), dateFormat));
			}
		}
		System.out.println("AAAAAAAA" + returnModel);
		logger.info("Method : viewItemPrice ends");

		return returnModel;
	}

	// delete

	@SuppressWarnings("unchecked")
	@GetMapping("master-laundry-delete-item-price")
	public @ResponseBody JsonResponse<LaundryItemPriceModel> deleteItemPrice(@RequestParam String deleteId1,String deleteId2,String deleteId3,String deleteId4,String deleteId5,HttpSession session) {

		logger.info("Method : deleteItemPrice starts");
		
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}

		if(deleteId5 != null && deleteId5 != "") {
			deleteId5 = DateFormatter.inputDateFormat(deleteId5, dateFormat);
		}
		System.out.println("@@@@@ " + deleteId1+" "+deleteId2+" "+deleteId3+" "+deleteId4+" "+deleteId5);
		JsonResponse<LaundryItemPriceModel> response = new JsonResponse<LaundryItemPriceModel>();

		try {
			response = restTemplate.getForObject(env.getHospitalityUrl() + "delete-item-price?id1=" + deleteId1 +"&id2="+deleteId2+"&id3="+deleteId3+"&id4="+deleteId4+"&id5="+deleteId5,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		logger.info("Method : deleteItemPrice ends");
		return response;
	}
	
	@PostMapping("/master-laundry-uploadFile")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {

			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("quotationPFile", inputFile);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : uploadFile controller function 'post-mapping' ends");
		return response;
	}
	
	@PostMapping("/master-laundry-uploadFile-item")
	public @ResponseBody JsonResponse<Object> uploadFileItem(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");
		
		JsonResponse<Object> response = new JsonResponse<Object>();
		
		try {
			
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("quotationPFileItem", inputFile);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : uploadFile controller function 'post-mapping' ends");
		return response;
	}
	
	@PostMapping("/master-laundry-edit-uploadFile")
	public @ResponseBody JsonResponse<Object> editUploadFile(@RequestBody String inputFile,
			HttpSession session) {
		logger.info("Method : editUploadFile controller function 'post-mapping' starts");
		
		JsonResponse<Object> response = new JsonResponse<Object>();
		session.removeAttribute("editFile");
		try {
			if(inputFile != null && inputFile != "" && !inputFile.equals("null") && !inputFile.equals("noimg")) {
				session.setAttribute("editFile", inputFile);
			} else {
				session.setAttribute("editFile", null);
			}
			System.out.println(session.getAttribute("editFile"));
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : uploadFeditUploadFileile controller function 'post-mapping' ends");
		return response;
	}
	
	@PostMapping("/master-laundry-edit-uploadFile-item")
	public @ResponseBody JsonResponse<Object> editUploadFileItem(@RequestBody String inputFile,
			HttpSession session) {
		logger.info("Method : editUploadFile controller function 'post-mapping' starts");
		
		JsonResponse<Object> response = new JsonResponse<Object>();
		session.removeAttribute("editFile");
		try {
			if(inputFile != null && inputFile != "" && !inputFile.equals("null") && !inputFile.equals("noimg")) {
				session.setAttribute("editFileItem", inputFile);
			} else {
				session.setAttribute("editFileItem", null);
			}
			System.out.println(session.getAttribute("editFileItem"));
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : uploadFeditUploadFileile controller function 'post-mapping' ends");
		return response;
	}
	
	public String saveAllImage(byte[] imageBytes, String ext) {
		logger.info("Method : saveAllImage starts");
		
		String imageName = null;
		
		try {
			
			if(imageBytes!=null) {
				long nowTime = new Date().getTime();
				if(ext.contentEquals("jpeg")) {
					imageName = nowTime+".jpg";
				} else {
					imageName = nowTime+"."+ext;
				}
			}

			Path path = Paths.get(env.getFileUploadMaster() + imageName);
			if(imageBytes !=null) {
				Files.write(path, imageBytes);
				
				ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
				Integer height = 50;
				Integer width = 50;
				
				try{
					BufferedImage img = ImageIO.read(in);
					if(height == 0){
						height = (width * img.getHeight())/img.getWidth();
					}
					if(width == 0){
						width = (height * img.getWidth())/img.getHeight();
					}
					
					BufferedImage outputImage = new BufferedImage(width,
							height, img.getType());
			 
			        Graphics2D g2d = outputImage.createGraphics();
			        g2d.drawImage(img, 0, 0, width, height, null);
			        g2d.dispose();
			        String outputImagePath = env.getFileUploadMaster()+"thumb/"+imageName;
			        ImageIO.write(outputImage, ext, new File(outputImagePath));

				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : saveAllImage ends");
		return imageName;
	}
}
