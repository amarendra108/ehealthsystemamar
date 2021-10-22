package nirmalya.aatithya.restmodule.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.dao.RestReferenceProductDao; 
import nirmalya.aatithya.restmodule.master.model.RestReferenceProductModel;

@RestController
@RequestMapping(value = { "master" })
public class RestReferenceProductController {
	Logger logger = LoggerFactory.getLogger(RestReferenceProductController.class);

	@Autowired
	RestReferenceProductDao restReferenceProductDao;

	@RequestMapping(value = "addBrandType", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> restaddBrandType(
			@RequestBody RestReferenceProductModel restProcurementMasterModel) {
		logger.info("Method : restaddBrandType starts");

		logger.info("Method : restaddBrandType ends");
		return restReferenceProductDao.addBrandType(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewBrandType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestReferenceProductModel>>> viewBrandType() {
		logger.info("Method : viewBrandType starts");

		logger.info("Method : viewBrandType ends");
		return restReferenceProductDao.viewBrandType();
	}

	@RequestMapping(value = "deletebrandType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deletebrandType(@RequestParam String id) {
		logger.info("Method : deletebrandType starts");

		logger.info("Method : deletebrandType ends");
		return restReferenceProductDao.deletebrandType(id);
	}

	// product Type

	@RequestMapping(value = "addProductType", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> restaddProductType(
			@RequestBody RestReferenceProductModel restProcurementMasterModel) {
		logger.info("Method : rest addProductType starts");

		logger.info("Method : rest addProductType ends");
		return restReferenceProductDao.addProductType(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewProductType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestReferenceProductModel>>> viewProductType() {
		logger.info("Method :rest viewProductType starts");

		logger.info("Method :rest viewProductType ends");
		return restReferenceProductDao.viewProductType();
	}

	@RequestMapping(value = "deleteProductType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteProductType(@RequestParam String id) {
		logger.info("Method : deleteProductType starts");

		logger.info("Method : deleteProductType ends");
		return restReferenceProductDao.deleteProductType(id);
	}

	// Variation Type

	@RequestMapping(value = "addVariationType", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> restaddVariationType(
			@RequestBody RestReferenceProductModel restProcurementMasterModel) {
		logger.info("Method : rest addVariationType starts");

		logger.info("Method : rest addVariationType ends");
		return restReferenceProductDao.addVariationType(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewVariationType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestReferenceProductModel>>> viewVariationType() {
		logger.info("Method :rest viewVariationType starts");

		logger.info("Method :rest viewVariationType ends");
		return restReferenceProductDao.viewVariationType();
	}

	@RequestMapping(value = "deleteVariationType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteVariationType(@RequestParam String id) {
		logger.info("Method : deleteVariationType starts");

		logger.info("Method : deleteVariationType ends");
		return restReferenceProductDao.deleteVariationType(id);
	}

	/**
	 * add brand type from csv 
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addBrandTypeCsv")
	public ResponseEntity<JsonResponse<Object>> addBrandTypeCsv(
			@RequestBody List<RestReferenceProductModel> reqtypeList) {
		logger.info("Method : addBrandTypeCsv starts");
		logger.info("Method : addBrandTypeCsv ends");
		return restReferenceProductDao.addBrandTypeCsv(reqtypeList);
	}

	/**
	 * add product type from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addProductTypeCsv")
	public ResponseEntity<JsonResponse<Object>> addProductTypeCsv(
			@RequestBody List<RestReferenceProductModel> reqtypeList) {
		logger.info("Method : addProductTypeCsv starts");
		logger.info("Method : addProductTypeCsv ends");
		return restReferenceProductDao.addProductTypeCsv(reqtypeList);
	}

	/**
	 * add variation type from csv
	 * 
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addVariationTypeCsv")
	public ResponseEntity<JsonResponse<Object>> addVariationTypeCsv(
			@RequestBody List<RestReferenceProductModel> reqtypeList) {
		logger.info("Method : addBrandTypeCsv starts");
		logger.info("Method : addBrandTypeCsv ends");
		return restReferenceProductDao.addVariationTypeCsv(reqtypeList);
	}
}
