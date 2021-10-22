package nirmalya.aathithya.webmodule.notice.controller;
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

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.notice.model.NoticeTypeMasterModel;

@Controller
@RequestMapping(value = { "notice/" })
public class NoticeTypeMasterController {
	
		Logger logger = LoggerFactory.getLogger(NoticeTypeMasterController.class);

		@Autowired
		RestTemplate restTemplate;

		@Autowired
		EnvironmentVaribles env;

		@Autowired
		NoticeTypeMasterController notice;

		

		/*
		 * Get Mapping for notice page
		 */
		@GetMapping("view-notice-master")
		public String noticeDetails(Model model, HttpSession session) {

			logger.info("Method : noticeDetails starts");

			logger.info("Method : noticeDetails ends");
			return "notice/vNotice";
		}
		/*
		* View Notice Details
		*
		*/

		@SuppressWarnings("unchecked")

		@GetMapping("view-notice-master_throughAjax")
		public @ResponseBody List<NoticeTypeMasterModel> viewNoticeDetails() {

		logger.info("Method : viewNoticeDetails starts");

		JsonResponse<List<NoticeTypeMasterModel>> resp = new JsonResponse<List<NoticeTypeMasterModel>>();

		try {
		resp = restTemplate.getForObject(env.getNotice() + "rest-viewNoticeDetails", JsonResponse.class);
		} catch (RestClientException e) {
		e.printStackTrace();
		}

		logger.info("Method : viewNoticeDetails ends");

		//System.out.println("View web controller======="+resp);
		return resp.getBody();
		}
		
		/*
		* Add Notice Details
		*
		*/
		@SuppressWarnings("unchecked")
		@PostMapping("view-notice-master-add")
		public @ResponseBody JsonResponse<Object> addNotice(Model model, HttpSession session,@RequestBody NoticeTypeMasterModel noticeModel) {
			logger.info("Method : addNotice starts");
			JsonResponse<Object> resp = new JsonResponse<Object>();

			String userId = "";

			try {
				userId = (String) session.getAttribute("USER_ID");
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			noticeModel.setCreatedBy(userId);
			try {
				resp = restTemplate.postForObject(env.getNotice() + "addNotice", noticeModel,
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

			logger.info("Method : addcustomer ends");
			//System.out.println("resp" + resp);
			return resp;
		}
		/*
		* Edit Notice Details
		*
		*/			
	
		@SuppressWarnings("unchecked")
		@GetMapping("view-notice-master-edit")
		public @ResponseBody JsonResponse<NoticeTypeMasterModel> editNotice(Model model, @RequestParam String id,
				HttpSession session) {

			logger.info("Method : editNotice starts");

			JsonResponse<NoticeTypeMasterModel> jsonResponse = new JsonResponse<NoticeTypeMasterModel>();
			System.out.println("momoomooo"+id);
			try {
				jsonResponse = restTemplate.getForObject(env.getNotice() + "editNotice?id=" + id, JsonResponse.class);

				ObjectMapper mapper = new ObjectMapper();
				NoticeTypeMasterModel noticewebModel = mapper.convertValue(jsonResponse.getBody(),
						new TypeReference<NoticeTypeMasterModel>() {
						});
			
			jsonResponse.setBody(noticewebModel);
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
			logger.info("Method : editNotice ends");
			return jsonResponse;
		}
		
		/*
		* Delete Notice Details
		*
		*/
		
		@SuppressWarnings("unchecked")

		@GetMapping("view-notice-master-delete")
		public @ResponseBody JsonResponse<NoticeTypeMasterModel> deleteNotice(Model model, @RequestParam String deleteId,
				
				HttpSession session) {
			logger.info("Method : deleteNotice starts");

			JsonResponse<NoticeTypeMasterModel> resp = new JsonResponse<NoticeTypeMasterModel>();
			System.out.println("Delete"+deleteId);
			

			try {
			resp = restTemplate.getForObject(env.getNotice() + "deleteNotice?deleteId="+deleteId,
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

			logger.info("Method :  deleteNotice ends");

			System.out.println("resp" +resp);
			return resp;
		}
}
