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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;

@Controller
@RequestMapping(value = { "audit/" })
public class AuditSupplementaryController {
	Logger logger = LoggerFactory.getLogger(AuditSupplementaryController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	/*
	 * Get Mapping for audit Link
	 */

	@GetMapping("/audit-dashboard-link-supplementary")
	public String auditLinkSupplementary(HttpSession session, Model model) {

		try {
			DropDownModel[] audit = restTemplate.getForObject(env.getAuditUrl() + "getSupplementaryTypeDashboard",
					DropDownModel[].class);
			List<DropDownModel> auditTypeList = Arrays.asList(audit);
			model.addAttribute("auditTypeList1", auditTypeList);
			for (DropDownModel m : auditTypeList) {
				
				byte[] encodeId1 = Base64.getEncoder().encode(m.getKey().getBytes());
				byte[] encodeId2 = Base64.getEncoder().encode(m.getName().getBytes());
				m.setKey(new String(encodeId1));
				m.setName(new String(encodeId2));

			}
			model.addAttribute("auditTypeList", auditTypeList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		logger.info("Method : auditLinkSupplementary ends");
		return "audit/auditLinkSupplementary";
	}
}
