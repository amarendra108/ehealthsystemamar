package nirmalya.aathithya.webmodule.bloodbank.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;

@Controller
@RequestMapping(value = { "bloodbank/" })
public class BloodBankDashboardController {

	Logger logger = LoggerFactory.getLogger(BloodBankDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	
	PdfGeneratatorUtil pdfGeneratorUtil;
	
	@GetMapping("bloodbank-dashboard")
	public String viewMyLabProfile(Model model, HttpSession session) {

		logger.info("Method : view bloodbank starts");
		
		
		
		logger.info("Method : view bloodbank ends");
		return "blood-bank/bloodbankdashboard";
	}
}
