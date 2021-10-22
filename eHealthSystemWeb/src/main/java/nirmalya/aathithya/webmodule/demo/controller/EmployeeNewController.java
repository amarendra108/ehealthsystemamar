/*
 * package nirmalya.aathithya.webmodule.demo.controller;
 * 
 * import java.util.Arrays; import java.util.List;
 * 
 * import javax.servlet.http.HttpSession;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.client.RestClientException; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import nirmalya.aathithya.webmodule.common.utils.DropDownModel; import
 * nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles; import
 * nirmalya.aathithya.webmodule.common.utils.JsonResponse; import
 * nirmalya.aathithya.webmodule.demo.model.EmployeeNewModel;
 * 
 * 
 * 
 * @Controller
 * 
 * @RequestMapping(value = "demo/") public class EmployeeNewController {
 * 
 * Logger logger = LoggerFactory.getLogger(EmployeeNewController.class);
 * 
 * @Autowired RestTemplate restTemplate;
 * 
 * @Autowired EnvironmentVaribles env;
 * 
 * // Summary
 * 
 * @GetMapping("add-emp") public String requisition(Model model, HttpSession
 * session) {
 * 
 * logger.info("Method : demo starts");
 * 
 * try {
 * 
 * DropDownModel[] gender = restTemplate.getForObject(env.getDemoUrl() +
 * "getgenderlist2", DropDownModel[].class); List<DropDownModel> genderlist =
 * Arrays.asList(gender); System.out.println("genderlist " + genderlist);
 * 
 * model.addAttribute("genderlist", genderlist); } catch (RestClientException e)
 * { e.printStackTrace();
 * 
 * }
 * 
 * try { DropDownModel[] MaritalStatus =
 * restTemplate.getForObject(env.getDemoUrl() + "getmaritalstatusList2",
 * DropDownModel[].class); List<DropDownModel> maritalstatusList =
 * Arrays.asList(MaritalStatus); System.out.println("maritalstatusList " +
 * maritalstatusList); model.addAttribute("maritalstatusList",
 * maritalstatusList); } catch (RestClientException e) { e.printStackTrace(); }
 * 
 * logger.info("Method : demo ends");
 * 
 * return "demo/add-emp"; }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @PostMapping("add-emp-add-ajax") public @ResponseBody JsonResponse<Object>
 * addEmp(Model model, HttpSession session,
 * 
 * @RequestBody EmployeeNewModel employeeNewModel) {
 * 
 * logger.info("Method : addEmp starts");
 * 
 * JsonResponse<Object> resp = new JsonResponse<Object>();
 * 
 * System.out.println(employeeNewModel);
 * 
 * try { resp = restTemplate.postForObject(env.getDemoUrl() + "addEmp",
 * employeeNewModel, JsonResponse.class); } catch (RestClientException e) {
 * e.printStackTrace(); }
 * 
 * if (resp.getMessage() != "" && resp.getMessage() != null) {
 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
 * resp.setMessage("Success"); }
 * 
 * logger.info("Method : addEmp ends"); System.out.println("resp" + resp);
 * return resp; }
 * 
 *//**
	 * Web Controller - view-employee-new-through-ajax
	 *
	 *//*
		 * 
		 * @SuppressWarnings("unchecked")
		 * 
		 * @GetMapping("add-emp-through-ajax") public @ResponseBody
		 * List<EmployeeNewModel> viewemployee() {
		 * 
		 * logger.info("Method :viewemployee starts");
		 * 
		 * JsonResponse<List<EmployeeNewModel>> resp = new
		 * JsonResponse<List<EmployeeNewModel>>(); try {
		 * 
		 * resp = restTemplate.getForObject(env.getDemoUrl() + "getAllemployee",
		 * JsonResponse.class); } catch (RestClientException e) { e.printStackTrace(); }
		 * 
		 * if (resp.getMessage() != "" && resp.getMessage() != null) {
		 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
		 * resp.setMessage("Success"); }
		 * 
		 * System.out.println("Response" + resp);
		 * 
		 * logger.info("Method :viewemployee ends"); System.out.println("RESPONSE" +
		 * resp); return resp.getBody(); }
		 * 
		 * @SuppressWarnings("unchecked")
		 * 
		 * @GetMapping("add-emp-edit") public @ResponseBody
		 * JsonResponse<EmployeeNewModel> editemployee(Model model, @RequestParam String
		 * id, HttpSession session) {
		 * 
		 * logger.info("Method : editemployee starts");
		 * 
		 * JsonResponse<EmployeeNewModel> jsonResponse = new
		 * JsonResponse<EmployeeNewModel>(); System.out.println(id); try { jsonResponse
		 * = restTemplate.getForObject(env.getDemoUrl() + "editemployee?id=" + id,
		 * JsonResponse.class);
		 * 
		 * } catch(RestClientException e) { e.printStackTrace(); }
		 * 
		 * 
		 * if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
		 * jsonResponse.setCode(jsonResponse.getMessage());
		 * jsonResponse.setMessage("Unsuccess");
		 * 
		 * } else { jsonResponse.setMessage("Success"); }
		 * 
		 * System.out.println("REsp"+jsonResponse);
		 * logger.info("Method : editemployee ends"); return jsonResponse; }
		 * 
		 * @SuppressWarnings("unchecked")
		 * 
		 * @GetMapping("add-emp-delete") public @ResponseBody
		 * JsonResponse<EmployeeNewModel> deleteemp(Model model, @RequestParam String
		 * deleteId, HttpSession session) { logger.info("Method : deleteemp starts");
		 * 
		 * JsonResponse<EmployeeNewModel> resp = new JsonResponse<EmployeeNewModel>();
		 * System.out.println(deleteId); try { resp = restTemplate.getForObject(
		 * env.getDemoUrl() + "deleteemp?id="+ deleteId, JsonResponse.class); } catch
		 * (RestClientException e) { e.printStackTrace(); }
		 * 
		 * if (resp.getMessage() != null && resp.getMessage() != "") {
		 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
		 * resp.setMessage("success"); }
		 * 
		 * logger.info("Method :  deleteemp ends");
		 * 
		 * System.out.println(resp); return resp; } }
		 */

/* img */

/*
 * package nirmalya.aathithya.webmodule.master.controller;
 * 
 * import java.util.Arrays; import java.util.Base64; import java.util.List;
 * 
 * import javax.servlet.http.HttpSession;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.client.RestClientException; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import com.fasterxml.jackson.core.type.TypeReference; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles; import
 * nirmalya.aathithya.webmodule.common.utils.FileUpload; import
 * nirmalya.aathithya.webmodule.common.utils.JsonResponse; import
 * nirmalya.aathithya.webmodule.master.model.DemoDocumentModel;
 * 
 * @Controller
 * 
 * @RequestMapping(value = { "master/" }) public class DemoDocumentController {
 * 
 * Logger logger = LoggerFactory.getLogger(TemplatesMasterController.class);
 * 
 * @Autowired RestTemplate restClient;
 * 
 * @Autowired EnvironmentVaribles env;
 * 
 * @Autowired FileUpload fileUpload;
 * 
 * @GetMapping(value = { "demo-document" }) public String demoDoc(Model model,
 * HttpSession session) { logger.info("Method : demoDoc starts");
 * 
 * try { DemoDocumentModel[] temp = restClient.getForObject(env.getMasterUrl() +
 * "getdemoList", DemoDocumentModel[].class); List<DemoDocumentModel> tempList =
 * Arrays.asList(temp);
 * 
 * if (tempList != null) { for (DemoDocumentModel m : tempList) { if
 * (m.getFileName() != null && m.getFileName() != "") { String[] extension =
 * m.getFileName().split("\\."); if (extension.length == 2) { if
 * (extension[1].equals("html")) { String docPath =
 * "<i class=\"fa fa-file\" title=\"" + m.getFileName() + "\"></i>";
 * m.setAction(docPath); } } else { m.setAction("N/A"); } } else {
 * m.setAction("N/A"); } m.setAction("<a href=\"/document/module/" +
 * m.getFileName() + "\" target=\"_blank\">" + m.getAction() + "</a>"); } }
 * 
 * model.addAttribute("tempList", tempList); } catch (RestClientException e) {
 * e.printStackTrace(); }
 * 
 * logger.info("Method : demoDoc ends"); return "master/treeDemo"; }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @PostMapping(value = "demo-document-save") public @ResponseBody
 * JsonResponse<Object> saveDemoDocument(@RequestBody DemoDocumentModel
 * tempDtls, HttpSession session) {
 * logger.info("Method : saveDemoDocument function starts");
 * 
 * //System.out.println("DATAAAAAAAA======" + tempDtls); JsonResponse<Object>
 * res = new JsonResponse<Object>(); DemoDocumentModel docList = new
 * DemoDocumentModel();
 * 
 * String userId = ""; try { userId = (String) session.getAttribute("USER_ID");
 * } catch (Exception e) {
 * 
 * } tempDtls.setCreatedBy(userId); if (tempDtls.getFileName() != null &&
 * tempDtls.getFileName() != "") { String delimiters = "\\."; String[] x =
 * tempDtls.getFileName().split(delimiters);
 * 
 * if (x[1].contentEquals("png") || x[1].contentEquals("PNG") ||
 * x[1].contentEquals("jpg") || x[1].contentEquals("jpeg")) { for (String s1 :
 * tempDtls.getFile()) { try { byte[] bytes = Base64.getDecoder().decode(s1);
 * String htmlName = fileUpload.saveAllImage(bytes);
 * tempDtls.setFileName(htmlName); } catch (Exception e) { e.printStackTrace();
 * } } }
 * 
 * } //System.out.println("tempDtls " + tempDtls); try { res =
 * restClient.postForObject(env.getMasterUrl() + "savedemoDocument", tempDtls,
 * JsonResponse.class);
 * 
 * } catch (RestClientException e) { e.printStackTrace(); } ObjectMapper mapper
 * = new ObjectMapper();
 * 
 * docList = mapper.convertValue(res.getBody(), new
 * TypeReference<DemoDocumentModel>() { }); if (docList != null) { if
 * (docList.getFileName() != null && docList.getFileName() != "") { String[]
 * extension = docList.getFileName().split("\\."); if (extension.length == 2) {
 * if (extension[1].equals("html")) { String docPath =
 * "<i class=\"fa fa-file\" title=\"" + docList.getFileName() + "\"></i>";
 * docList.setAction(docPath); } } else { docList.setAction("N/A"); } } else {
 * docList.setAction("N/A"); } docList.setAction("<a href=\"/document/module/" +
 * docList.getFileName() + "\" target=\"_blank\">" + docList.getAction() +
 * "</a>"); } String message = res.getMessage(); if (message != null && message
 * != "") {
 * 
 * } else { res.setMessage("Success"); } res.setBody(docList);
 * logger.info("Method : saveDemoDocument function Ends"); return res; }
 * 
 * }
 */

/*
 * 
 * 
 * 
 * 
 * package nirmalya.aatithya.restmodule.master.dao;
 * 
 * import java.util.ArrayList; import java.util.List; import
 * javax.persistence.EntityManager; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Repository; import
 * nirmalya.aatithya.restmodule.common.ServerDao; import
 * nirmalya.aatithya.restmodule.common.utils.JsonResponse; import
 * nirmalya.aatithya.restmodule.master.model.RestDemoDocumentModel;
 * 
 * @Repository public class RestDemoDocumentDao {
 * 
 * Logger logger = LoggerFactory.getLogger(RestDemoDocumentDao.class);
 * 
 * @Autowired EntityManager em;
 * 
 * @Autowired ServerDao serverDao;
 * 
 * @SuppressWarnings("unchecked") public List<RestDemoDocumentModel>
 * getdemoList() { logger.info("Method : getdemoList starts");
 * 
 * List<RestDemoDocumentModel> tempList = new
 * ArrayList<RestDemoDocumentModel>();
 * 
 * try { List<Object[]> x = em.createNamedStoredProcedureQuery("DemoDoc")
 * .setParameter("actionType", "getDocList").setParameter("actionValue",
 * "").getResultList();
 * 
 * for (Object[] m : x) { RestDemoDocumentModel dropDownModel = new
 * RestDemoDocumentModel(m[0], m[1], m[2], m[3]); tempList.add(dropDownModel); }
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * logger.info("Method : getdemoList ends"); return tempList; }
 * 
 * @SuppressWarnings("unchecked") public
 * ResponseEntity<JsonResponse<RestDemoDocumentModel>>
 * savedemoDocument(RestDemoDocumentModel tempDtls) {
 * logger.info("Method : savedemoDocument starts");
 * 
 * 
 * //System.out.println("DAOOOO===="+tempDtls); Boolean validity = true;
 * JsonResponse<RestDemoDocumentModel> resp = new
 * JsonResponse<RestDemoDocumentModel>(); resp.setMessage(""); resp.setCode("");
 * 
 * List<RestDemoDocumentModel> newLoc = new ArrayList<RestDemoDocumentModel>();
 * 
 * if (validity) try {
 * 
 * String value = "SET @P_DocId='" + tempDtls.getDocId() + "', @P_CreatedBy='" +
 * tempDtls.getCreatedBy() + "', @P_Attachments='" + tempDtls.getFileName() +
 * "', @p_docName='" + tempDtls.getDocName() +"';";
 * 
 * em.createNamedStoredProcedureQuery("DemoDoc") .setParameter("actionType",
 * "saveDemoDocument").setParameter("actionValue", value).execute();
 * 
 * for (Object[] m : x) {
 *
 * RestDemoDocumentModel item = new RestDemoDocumentModel(m[0], m[1], m[2],
 * m[3]); newLoc.add(item); }
 * 
 * //resp.setBody(newLoc.get(0));
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * ResponseEntity<JsonResponse<RestDemoDocumentModel>> response = new
 * ResponseEntity<JsonResponse<RestDemoDocumentModel>>( resp,
 * HttpStatus.CREATED); //System.out.println(response);
 * logger.info("Method : savedemoDocument ends"); return response; } }
 */
