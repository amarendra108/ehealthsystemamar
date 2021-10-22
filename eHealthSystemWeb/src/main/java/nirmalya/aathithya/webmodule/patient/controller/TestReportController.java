package nirmalya.aathithya.webmodule.patient.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;


@Controller
@RequestMapping(value ="user/")
public class TestReportController {
	Logger logger = LoggerFactory.getLogger(TestReportController.class);
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	EnvironmentVaribles environmentVaribles;
	
	@GetMapping("test-report")
	public String testReport(Model model, HttpSession session) {
		logger.info("Method : testReport starts");

		logger.info("Method : testReport ends");
		return "patient/test-report-pdf-download";
	}
}
