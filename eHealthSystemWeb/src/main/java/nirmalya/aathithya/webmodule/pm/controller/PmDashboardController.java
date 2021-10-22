package nirmalya.aathithya.webmodule.pm.controller;

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

@Controller
@RequestMapping(value = "pm/")
public class PmDashboardController {
	Logger logger = LoggerFactory.getLogger(PmDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("/pm-dashboard")
	public String pmDashboardView(Model model, HttpSession session) {

		logger.info("Method : pmDashboardView starts");
		
		String userId1 = (String) session.getAttribute("USER_ID");
		try {
			DropDownModel[] region = restTemplate.getForObject(env.getUserUrl() + "rest-pmDashboardgetId?id=" + userId1,
					DropDownModel[].class);
			List<DropDownModel> pMDashboardModel = Arrays.asList(region);
			model.addAttribute("pMDashboardModel", pMDashboardModel);
			System.out.println("pMDashboardModel@@@@@@@" + pMDashboardModel);
		} catch (RestClientException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		logger.info("Method : pmDashboardView ends");
		return "pm/countryMaster";

	}

}
