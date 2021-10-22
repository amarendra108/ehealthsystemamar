package nirmalya.aathithya.webmodule.patient.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import nirmalya.aathithya.webmodule.common.pagination.DataTableRequest;
import nirmalya.aathithya.webmodule.common.utils.DeleteQrImage;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;
import nirmalya.aathithya.webmodule.gatepass.controller.PdfGatePassController;
import nirmalya.aathithya.webmodule.gatepass.model.GatePassEntryModel;
import nirmalya.aathithya.webmodule.patient.model.LabTestReportModel;

/**
 * @author Nirmalya Labs
 *
 */
@Controller
@RequestMapping(value = "user/")
public class PdfTestReportController {
	Logger logger = LoggerFactory.getLogger(PdfGatePassController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PdfGeneratatorUtil pdfGeneratorUtil;

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/view-patient-test-report-pdf-download" })
	public void generateAssignedAssetPdf(HttpServletResponse response, Model model,HttpSession session,
			@RequestParam("id") String encodedParam1,@RequestParam("id2") String id2) {
		
		logger.info("Method : testReport starts");

		byte[] encodeByte1 = Base64.getDecoder().decode(encodedParam1.getBytes());

		String param1 = (new String(encodeByte1));

		JsonResponse<List<LabTestReportModel>> jsonResponse = new JsonResponse<List<LabTestReportModel>>();
		DataTableRequest tableRequest = new DataTableRequest();

		tableRequest.setParam1(param1);
		System.out.println("param1=="+param1);
		System.out.println("param2=="+id2);
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "rest-getTestTetailss?id=" + param1+"&id2="+id2,
					JsonResponse.class);

		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabTestReportModel> gatPatientdetails = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<List<LabTestReportModel>>() {
				});
		Map<String, Object> data = new HashMap<String, Object>();
		//Omraj start
		
		
		//System.out.println("Sradha"+gatPatientdetails.get(0).getPatientId());
		String qrdata = "https://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+encodedParam1+"&id2="+gatPatientdetails.get(0).getTestDate();
		//System.out.println("@@@@@$$$$$$"+qrdata);
		String file1 ="QR"+gatPatientdetails.get(0).getPatientId()+new Date().getTime()+".png";
		try {
			String qrCodeData = qrdata;
			//String filePath = env.getBaseURL() + file1 ;
			String filePath = env.getUserqrcodeUrl() + file1;
			System.out.println("@@@@@@@@@"+filePath);
			String charset = "UTF-8";// "ISO-8859-1";

			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, 200, 200, hintMap);
			MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1),
					new File(filePath));
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		//String path1 = env.getBaseURL() + "assets/images/e.png";
		String path1 = env.getUserqrcodeUrl()+file1;
				
		String path2 = env.getBaseURL() + "document/qrcode/" + file1;
		
		System.out.println(path1);
		data.put("image", path2);
		data.put("gatPatientdetails", gatPatientdetails);
		System.out.println("patient Details"+gatPatientdetails);
		System.out.println("patient data"+data);
		/**
		 * get Hotel Logo
		 *
		 */
		/**
		 * List<DropDownModel> logoList = new ArrayList<DropDownModel>(); try {
		 * DropDownModel[] logo = restClient.getForObject(env.getAssetUrl() +
		 * "restLogoImage-Maintenance?logoType="+"header-Logo", DropDownModel[].class);
		 * logoList = Arrays.asList(logo); model.addAttribute("logoList", logoList);
		 * data.put("logoList", logoList);
		 * 
		 * } catch (RestClientException e) { e.printStackTrace(); }
		 * 
		 * String variable = env.getBaseUrlPath();
		 * 
		 * String logo = logoList.get(0).getName();
		 * data.put("logoImage",variable+"document/hotel/"+logo+"");
		 */
//	    data.put("fromDate", param2);
//		data.put("toDate", param3);
//		data.put("gatePass", gatePass);

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=TestReport.pdf");
		File file;
		byte[] fileData = null;
		try {
			file = pdfGeneratorUtil.createPdf("patient/test-report-pdf-download", data);
			InputStream in = new FileInputStream(file);
			fileData = IOUtils.toByteArray(in);
			response.setContentLength(fileData.length);
			response.getOutputStream().write(fileData);
			response.getOutputStream().flush();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DeleteQrImage.getDeleteImage(path1);
		logger.info("Method : testReport ends");
	}
	/****************************** Lab Report Download  *******************************/
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/view-patient-test-report-pdf-download-pdf" })
	public void generateAssignedAssetPdfForLab(HttpServletResponse response, Model model,HttpSession session,
			@RequestParam("id") String encodedParam1) {
		logger.info("Method : generateAssignedAssetPdfForLab starts");

		byte[] encodeByte1 = Base64.getDecoder().decode(encodedParam1.getBytes());

		String param1 = (new String(encodeByte1));

		JsonResponse<List<LabTestReportModel>> jsonResponse = new JsonResponse<List<LabTestReportModel>>();
		DataTableRequest tableRequest = new DataTableRequest();

		tableRequest.setParam1(param1);
		System.out.println("param1=="+param1);
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "getpatienttestdetails?id=" + param1,
					JsonResponse.class);

		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabTestReportModel> gatPatientdetails = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<List<LabTestReportModel>>() {
				});
		Map<String, Object> data = new HashMap<String, Object>();
		
		String path1 = env.getBaseURL() + "assets/images/e.png";
		System.out.println(path1);
		data.put("image", path1);
		data.put("gatPatientdetails", gatPatientdetails);
		System.out.println("patient Details"+gatPatientdetails);
		System.out.println("patient data"+data);
		/**
		 * get Hotel Logo
		 *
		 */
		/**
		 * List<DropDownModel> logoList = new ArrayList<DropDownModel>(); try {
		 * DropDownModel[] logo = restClient.getForObject(env.getAssetUrl() +
		 * "restLogoImage-Maintenance?logoType="+"header-Logo", DropDownModel[].class);
		 * logoList = Arrays.asList(logo); model.addAttribute("logoList", logoList);
		 * data.put("logoList", logoList);
		 * 
		 * } catch (RestClientException e) { e.printStackTrace(); }
		 * 
		 * String variable = env.getBaseUrlPath();
		 * 
		 * String logo = logoList.get(0).getName();
		 * data.put("logoImage",variable+"document/hotel/"+logo+"");
		 */
//	    data.put("fromDate", param2);
//		data.put("toDate", param3);
//		data.put("gatePass", gatePass);

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=TestReport.pdf");
		File file;
		byte[] fileData = null;
		try {
			file = pdfGeneratorUtil.createPdf("patient/test-report-pdf-download", data);
			InputStream in = new FileInputStream(file);
			fileData = IOUtils.toByteArray(in);
			response.setContentLength(fileData.length);
			response.getOutputStream().write(fileData);
			response.getOutputStream().flush();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : generateAssignedAssetPdfForLab ends");
	}
}
