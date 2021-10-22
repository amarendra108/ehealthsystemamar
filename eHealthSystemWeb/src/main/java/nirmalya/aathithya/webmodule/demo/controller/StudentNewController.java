/*
 * package nirmalya.aathithya.webmodule.demo.controller;
 * 
 * import java.util.Arrays; import java.util.List; import
 * javax.servlet.http.HttpSession; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory;import*org.springframework.beans.factory.annotation.
 * Autowired;import*org.springframework.stereotype.Controller;import*org.
 * springframework.ui.Model;import*org.springframework.web.bind.annotation.
 * GetMapping;import*org.springframework.web.bind.annotation.PostMapping;import*
 * org.springframework.web.bind.annotation.RequestBody;import*org.
 * springframework.web.bind.annotation.RequestMapping;import*org.springframework
 * .web.bind.annotation.RequestParam;import*org.springframework.web.bind.
 * annotation.ResponseBody;import*org.springframework.web.client.
 * RestClientException;import*org.springframework.web.client.RestTemplate;**
 * import
 * com.fasterxml.jackson.core.type.TypeReference;import*com.fasterxml.jackson.
 * databind.ObjectMapper;** import
 * nirmalya.aathithya.webmodule.common.utils.DateFormatter;import*nirmalya.
 * aathithya.webmodule.common.utils.DropDownModel;import*nirmalya.aathithya.
 * webmodule.common.utils.EnvironmentVaribles;import*nirmalya.aathithya.
 * webmodule.common.utils.JsonResponse;import*nirmalya.aathithya.webmodule.demo.
 * model.StudentNewModel;****
 * 
 * @Controller
 * 
 * @RequestMapping(value = "demo/") public class StudentNewController {
 * 
 * Logger logger = LoggerFactory.getLogger(StudentNewController.class);**
 * 
 * @Autowired RestTemplate restTemplate;**
 * 
 * @Autowired EnvironmentVaribles env;** // Summary
 * 
 * 
 * @GetMapping("add-emp-stud") public String student(Model model, HttpSession
 * session) {
 * 
 * logger.info("Method : demo starts");
 * 
 * try {
 * 
 * DropDownModel[] country = restTemplate.getForObject(env.getDemoUrl() +
 * "get-country-list", DropDownModel[].class); List<DropDownModel> countrylist =
 * Arrays.asList(country);
 * 
 * model.addAttribute("countrylist", countrylist); } catch (RestClientException
 * e) { e.printStackTrace();
 * 
 * }
 * 
 * try { DropDownModel[] bloodGroup = restTemplate.getForObject(env.getDemoUrl()
 * + "getbloodGroup", DropDownModel[].class); List<DropDownModel> bloodGroupList
 * = Arrays.asList(bloodGroup); model.addAttribute("bloodGroupList",
 * bloodGroupList); } catch (RestClientException e) { e.printStackTrace(); }
 * logger.info("Method : demo ends");
 * 
 * return "demo/add-student"; }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @GetMapping(value = { "view-customer-stateName-ajax" }) public @ResponseBody
 * JsonResponse<Object> getstateList(@RequestParam String id) {
 * logger.info("Method : getstateListAJAX starts");**JsonResponse<Object>res=new
 * JsonResponse<Object>();try{res=*restTemplate.getForObject(env.getDemoUrl()+
 * "rest-get-stateList-New?id="+*id,JsonResponse.class);}catch(Exception
 * e){e.printStackTrace();}if*(res.getMessage()!=null){res.setCode(res.
 * getMessage());*res.setMessage("Unsuccess");}else{res.setMessage("success");}*
 * System.out.println("state"+res);*logger.info("Method : getstateListAJAX ends"
 * );return res;** }**
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @GetMapping(value = { "add-emp-stud-dist-ajax" }) public @ResponseBody
 * JsonResponse<Object> getdistList(Model model, @RequestParam String id) {
 * logger.info("Method : getdistListAJAX starts"); JsonResponse<Object> res =
 * new JsonResponse<Object>(); try { res =
 * restTemplate.getForObject(env.getDemoUrl() + "rest-get-distList-New?id=" +
 * id, JsonResponse.class); } catch (Exception e) { e.printStackTrace(); } if
 * (res.getMessage() != null) { res.setCode(res.getMessage());
 * res.setMessage("Unsuccess"); } else { res.setMessage("success"); }
 * System.out.println(res); logger.info("Method : getdistListAJAX ends"); return
 * res;
 * 
 * }**
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @PostMapping("add-emp-stud-add") public @ResponseBody JsonResponse<Object>
 * addstud(Model model, HttpSession session,
 * 
 * @RequestBody StudentNewModel studentNewModel) {
 * 
 * logger.info("Method : addstud starts"); String dateFormat = ""; try {
 * dateFormat = (String) session.getAttribute("DATEFORMAT"); } catch (Exception
 * e) {
 * 
 * } if (studentNewModel.getDob() != null && studentNewModel.getDob() != "") {
 * studentNewModel.setDob(DateFormatter.inputDateFormat(studentNewModel.getDob()
 * , dateFormat)); } if (studentNewModel.getDateofadmisn() != null &&
 * studentNewModel.getDateofadmisn() != "") { studentNewModel
 * .setDateofadmisn(DateFormatter.inputDateFormat(studentNewModel.
 * getDateofadmisn(), dateFormat)); } if (studentNewModel.getDateofpasst() !=
 * null && studentNewModel.getDateofpasst() != "") {
 * studentNewModel.setDateofpasst(DateFormatter.inputDateFormat(studentNewModel.
 * getDateofpasst(), dateFormat)); } JsonResponse<Object> resp = new
 * JsonResponse<Object>();
 * 
 * System.out.println(studentNewModel);
 * 
 * try { resp = restTemplate.postForObject(env.getDemoUrl() + "addstud",
 * studentNewModel, JsonResponse.class); } catch (RestClientException e) {
 * e.printStackTrace(); }
 * 
 * if (resp.getMessage() != "" && resp.getMessage() != null) {
 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
 * resp.setMessage("Success"); }
 * 
 * logger.info("Method : addstud ends"); System.out.println("resp" + resp);
 * return resp; }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @GetMapping("add-emp-stud-through-ajax") public @ResponseBody
 * 
 * 
 * List<StudentNewModel> viewstudentt(HttpSession session) {
 * 
 * logger.info("Method :viewstudentt starts");
 * 
 * JsonResponse<List<StudentNewModel>> resp = new
 * JsonResponse<List<StudentNewModel>>(); try {
 * 
 * resp = restTemplate.getForObject(env.getDemoUrl() + "getAllstudentt",
 * JsonResponse.class); } catch (RestClientException e) { e.printStackTrace(); }
 * 
 * ObjectMapper mapper = new ObjectMapper();
 * 
 * List<StudentNewModel> studentNewModel = mapper.convertValue(resp.getBody(),
 * new TypeReference<List<StudentNewModel>>() { }); String dateFormat = ""; try
 * { dateFormat = (String) session.getAttribute("DATEFORMAT"); } catch
 * (Exception e) {
 * 
 * } for(StudentNewModel a : studentNewModel) { if (a.getDob() != null &&
 * a.getDob() != "") { a.setDob(DateFormatter.dateFormat(a.getDob(),
 * dateFormat)); } if (a.getDateofadmisn() != null && a.getDateofadmisn() != "")
 * { a.setDateofadmisn(DateFormatter.dateFormat(a.getDateofadmisn(),
 * dateFormat)); } if (a.getDateofpasst() != null && a.getDateofpasst() != "") {
 * a.setDateofpasst(DateFormatter.dateFormat(a.getDateofpasst(), dateFormat)); }
 * }
 * 
 * resp.setBody(studentNewModel); if (resp.getMessage() != null &&
 * resp.getMessage() != "") { resp.setCode(resp.getMessage());
 * resp.setMessage("Unsuccess");
 * 
 * } else { resp.setMessage("Success"); }
 * 
 * System.out.println("Response" + resp);
 * 
 * logger.info("Method :viewstudentt ends"); System.out.println("RESPONSE" +
 * resp); return resp.getBody(); }**
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @GetMapping("add-emp-stud-edit") public @ResponseBody
 * JsonResponse<StudentNewModel> editstudentt(Model model, @RequestParam String
 * id, HttpSession session) {
 * 
 * logger.info("Method : editstudentt starts");
 * 
 * JsonResponse<StudentNewModel> jsonResponse = new
 * JsonResponse<StudentNewModel>(); System.out.println(id); try { jsonResponse =
 * restTemplate.getForObject(env.getDemoUrl() + "editstudentt?id=" + id,
 * JsonResponse.class);
 * 
 * } catch(RestClientException e) { e.printStackTrace(); }
 * 
 * ObjectMapper mapper = new ObjectMapper();
 * 
 * StudentNewModel studentModel = mapper.convertValue(jsonResponse.getBody(),
 * new TypeReference<StudentNewModel>() { }); String dateFormat = ""; try {
 * dateFormat = (String) session.getAttribute("DATEFORMAT"); } catch (Exception
 * e) {
 * 
 * } if (studentModel.getDob() != null && studentModel.getDob() != "") {
 * studentModel.setDob(DateFormatter.dateFormat(studentModel.getDob(),
 * dateFormat)); } if (studentModel.getDateofadmisn() != null &&
 * studentModel.getDateofadmisn() != "") {
 * studentModel.setDateofadmisn(DateFormatter.dateFormat(studentModel.
 * getDateofadmisn(), dateFormat)); } if (studentModel.getDateofpasst() != null
 * && studentModel.getDateofpasst() != "") {
 * studentModel.setDateofpasst(DateFormatter.dateFormat(studentModel.
 * getDateofpasst(), dateFormat)); } jsonResponse.setBody(studentModel); if
 * (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
 * jsonResponse.setCode(jsonResponse.getMessage());
 * jsonResponse.setMessage("Unsuccess");
 * 
 * } else { jsonResponse.setMessage("Success"); }
 * System.out.println("REsp"+jsonResponse);
 * logger.info("Method : editstudentt ends"); return jsonResponse; }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @GetMapping("add-emp-stud-delete") public @ResponseBody
 * 
 * 
 * JsonResponse<StudentNewModel> deletestudd(Model model,@RequestParam
 * String*deleteId,HttpSession session) {
 * logger.info("Method : deletestudd starts");**JsonResponse<StudentNewModel>
 * resp=new
 * JsonResponse<StudentNewModel>();*System.out.println(deleteId);try{resp=
 * restTemplate.getForObject(*env.getDemoUrl()+"deletestudd?id="+deleteId,
 * JsonResponse.class);}catch*(RestClientException
 * e){e.printStackTrace();}**if(resp.getMessage()!=null&&resp.getMessage()!=""){
 * resp.setCode(resp.getMessage());resp.setMessage("Unsuccess");}else{*resp.
 * setMessage("success");}**logger.info("Method :  deletestudd ends");**System.
 * out.println(resp);return resp;} }
 */









/*package nirmalya.aatithya.restmodule.master.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.dao.RestDemoDocumentDao;
import nirmalya.aatithya.restmodule.master.model.RestDemoDocumentModel;

@RestController
@RequestMapping(value = "master/")
public class RestDemoDocumentController {

	Logger logger = LoggerFactory.getLogger(RestDemoDocumentController.class);

	@Autowired
	RestDemoDocumentDao docDao;

	@RequestMapping(value = "savedemoDocument", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestDemoDocumentModel>> savedemoDocument(
			@RequestBody RestDemoDocumentModel docDtls) {
		logger.info("Method : savedemoDocument starts");

		logger.info("Method : savedemoDocument ends");
		return docDao.savedemoDocument(docDtls);
	}

	@RequestMapping(value = "getdemoList", method = { RequestMethod.GET })
	public List<RestDemoDocumentModel> getdemoList() {
		logger.info("Method : getdemoList starts");

		logger.info("Method : getdemoList end");
		return docDao.getdemoList();
	}*/

