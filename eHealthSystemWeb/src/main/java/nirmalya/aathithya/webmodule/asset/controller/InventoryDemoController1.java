package nirmalya.aathithya.webmodule.asset.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author NirmalyaLabs
 *
 */
@Controller 
@RequestMapping(value = "inventory/")
public class InventoryDemoController1 {
	Logger logger = LoggerFactory.getLogger(InventoryDemoController1.class);

 
	@GetMapping("generate-order")
	public String generateOrder(Model model, HttpSession session) {
		
		logger.info("Method : generateOrder starts");
		model.addAttribute("pageName", "Generate Order Page Coming Soon");
		logger.info("Method : generateOrder ends");
		return "recruitment/view-action";
		
	}
	@GetMapping("view-payment")
	public String viewPayment(Model model, HttpSession session) {
		
		logger.info("Method : viewPayment starts");
		model.addAttribute("pageName", "Payment Page Coming Soon");
		logger.info("Method : viewPayment ends");
		return "recruitment/view-action";
		
	} 
 
	@GetMapping("physical-verification")
	public String viewPhysical(Model model, HttpSession session) {
		
		logger.info("Method : viewPhysical starts");
		model.addAttribute("pageName", "Physical Verification Page Coming Soon");
		logger.info("Method : viewPhysical ends");
		return "recruitment/view-action";
		
	}
	 
	@GetMapping("vendor-payment")
	public String viewVendorPayment(Model model, HttpSession session) {
		
		logger.info("Method : viewPhysical starts");
		model.addAttribute("pageName", "Vendor Payment Page Coming Soon");
		logger.info("Method : viewPhysical ends");
		return "recruitment/view-action";
		
	}
 
	@GetMapping("entity-master")
	public String viewMAsterCompany(Model model, HttpSession session) {
		
		logger.info("Method : viewMAsterCompany starts");
		model.addAttribute("pageName", "Entity Master Page Coming Soon");
		logger.info("Method : viewMAsterCompany ends");
		return "recruitment/view-action";
		
	}
}
