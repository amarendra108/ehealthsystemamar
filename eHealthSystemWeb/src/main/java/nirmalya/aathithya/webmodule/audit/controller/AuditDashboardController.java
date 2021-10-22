package nirmalya.aathithya.webmodule.audit.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Controller
@RequestMapping(value = { "audit/" })
public class AuditDashboardController {
	Logger logger = LoggerFactory.getLogger(AuditDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	/*
	 * Get Mapping for dash board
	 */

	@GetMapping("audit-dashboard")
	public String auditDashboard(Model model, HttpSession session) {

		logger.info("Method : auditDashboard starts");

		try {
			DropDownModel[] audit = restTemplate.getForObject(env.getAuditUrl() + "getAuditTypeDashboard",
					DropDownModel[].class);
			List<DropDownModel> auditTypeList = Arrays.asList(audit);
			model.addAttribute("auditTypeList1", auditTypeList);
			
			for (DropDownModel m : auditTypeList) {
				/* m.setDocName(m.getName()); */
				byte[] encodeId1 = Base64.getEncoder().encode(m.getKey().getBytes());
				byte[] encodeId2 = Base64.getEncoder().encode(m.getName().getBytes());
				m.setKey(new String(encodeId1));
				m.setName(new String(encodeId2));

			}
			model.addAttribute("auditTypeList", auditTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : auditDashboard ends");
		return "audit/auditDashboard";
	}
	
}
