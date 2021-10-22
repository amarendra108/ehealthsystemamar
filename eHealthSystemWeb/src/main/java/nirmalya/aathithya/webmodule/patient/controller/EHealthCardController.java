package nirmalya.aathithya.webmodule.patient.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;
import nirmalya.aathithya.webmodule.patient.model.EHealthCardModel;

@Controller
@RequestMapping(value = "user/")
public class EHealthCardController {
	Logger logger = LoggerFactory.getLogger(EHealthCardController.class);
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PdfGeneratatorUtil pdfGeneratorUtil;
	
	@GetMapping("ehealthcard-download")
	public String ehealthcardDetails(Model model, HttpSession session) {
		logger.info("Method : ehealthcardDetails starts");

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			EHealthCardModel title = restTemplate.getForObject(env.getUserUrl() + "getEhealthCardInformation?id=" + userId, EHealthCardModel.class);
			EHealthCardModel cardDetails = title;
			System.out.println("cardDetailscardDetails"+cardDetails);
			model.addAttribute("cardDetails", cardDetails);

			ObjectMapper mapper = new ObjectMapper();
			EHealthCardModel eHealthCardDetails = mapper.convertValue(cardDetails,new TypeReference<EHealthCardModel>() {
				
					});
			String profilePath = null;
			String qRPath = null;
			//profilePath = env.getBaseURL() + "document/profile/" + eHealthCardDetails.getProfileImg();
			
			
			if(eHealthCardDetails.getProfileImg() == null || eHealthCardDetails.getProfileImg() == "" )
			{
				profilePath= "../assets/eHealth/images/user_img.jpg";
			}else {
				profilePath = env.getBaseURL() + "document/profile/" + eHealthCardDetails.getProfileImg();
			}
			
			if(eHealthCardDetails.getQrCode() == null || eHealthCardDetails.getQrCode() == "" ) {
				qRPath= "../assets/eHealth/images/not_qrcode1.jpg";
			}else {
				qRPath = env.getBaseURL() + "document/qrcode/" + eHealthCardDetails.getQrCode();
			}
				
			model.addAttribute("profileimg", profilePath);
			model.addAttribute("qRCode", qRPath);
			
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : ehealthcardDetails ends");
		return "patient/ehealthCard";
	}
	
	/*********************** pdf download****************/
	@GetMapping("ehealthcard-download-pdf")
	public void ehealthCardPdfDownload(HttpSession session, HttpServletResponse response) {
		logger.info("Method : ehealthCardPdfDownload starts");

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		EHealthCardModel title = new EHealthCardModel();
		try {
			 title = restTemplate.getForObject(env.getUserUrl() + "getEhealthCardInformation?id=" + userId, EHealthCardModel.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("cardDetails", title);
		
		ObjectMapper mapper = new ObjectMapper();
		EHealthCardModel eHealthCardDetails = mapper.convertValue(title,
				new TypeReference<EHealthCardModel>() {
				});
		
		
		String profilePath = null;
		String qRPath = null;
		if(eHealthCardDetails.getProfileImg() == null || eHealthCardDetails.getProfileImg() == "" )
		{
			profilePath= "../assets/eHealth/images/user_img.jpg";
		}else {
			profilePath = env.getBaseURL() + "document/profile/" + eHealthCardDetails.getProfileImg();
		}
		
		
		if(eHealthCardDetails.getQrCode() == null || eHealthCardDetails.getQrCode() == "" ) {
			qRPath= "../assets/eHealth/images/not_qrcode1.jpg";
		}else {
			qRPath = env.getBaseURL() + "document/qrcode/" + eHealthCardDetails.getQrCode();
		}
		
		
		
		String bg1 = env.getBaseURL()+"assets/eHealth/images/frontend_card.jpg";
		String bg2 = env.getBaseURL()+"assets/eHealth/images/backend_card.jpg";
		
		data.put("bg1", bg1);
		data.put("bg2", bg2);
		
		data.put("profileimg", profilePath);
		data.put("qRCode", qRPath);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=healthCard.pdf");
		File file;
		byte[] fileData = null;
		try {
			file = pdfGeneratorUtil.createPdf("patient/eHealthCard-Pdf-Download", data);
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
		logger.info("Method : ehealthCardPdfDownload ends");
		
	}
}
