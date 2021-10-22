package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.SignUpLogInDao;
import nirmalya.aatithya.restmodule.api.model.ApiUserModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.OtherUsersProfileModel;
import nirmalya.aatithya.restmodule.api.model.SignUpModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class SignUpLogInController {
	
	@Autowired
	SignUpLogInDao sigUpLogInDao;
	
	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(SignUpLogInController.class);
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ApiUserModel>> getOtp(@RequestParam String mobileNo,@RequestParam String password) {
		logger.info("Method : getOtp starts");
		logger.info("Method : getOtp ends");
		return sigUpLogInDao.getLoginDetails(mobileNo,password);
	}
	
	@RequestMapping(value = "/login-with-otp", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ApiUserModel>> getLogInWithOTP(@RequestParam String mobileNo) {
		logger.info("Method : getLogInWithOTP starts");
		logger.info("Method : getLogInWithOTP ends");
		return sigUpLogInDao.getLoginDetailsByMob(mobileNo);
	}
	
	/** Fetch Country List **/
	@GetMapping(value = "/get-country-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> countryList() {
		logger.info("Method : countryList starts");

		logger.info("Method : countryList ends");
		return sigUpLogInDao.getCountryList();
	}
	
	/** Fetch User Title List **/
	@GetMapping(value = "/get-user-title-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> userTitleList() {
		logger.info("Method : userTitleList starts");
		
		logger.info("Method : userTitleList ends");
		return sigUpLogInDao.getUserTitleList();
	}
	
	/** Fetch Organization List **/
	@GetMapping(value = "/get-hospital-list-for-doc-app")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getHospitalList() {
		logger.info("Method : getHospitalList starts");
		
		logger.info("Method : getHospitalList ends");
		return sigUpLogInDao.getHospitalList();
	}
	
	/** Fetch Gender Type List **/
	@GetMapping(value = "/get-gender-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> genderList() {
		logger.info("Method : genderList starts");
		
		logger.info("Method : genderList ends");
		return sigUpLogInDao.getGenderList();
	}
	
	@GetMapping(value = "/get-bloodgroup-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> bloodGroupList() {
		logger.info("Method : bloodGroupList starts");
		
//		String msg = "Welcome to eHealthApp. Your userid is 8917225033 password is User@123. Please click on OK to activate your account.";
//		
//		try {
//			CommonUsed.sendSMS("918917225033", msg);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		logger.info("Method : bloodGroupList ends");
		return sigUpLogInDao.getBloodGroupList();
	}
	
	/** Fetch User Title List **/
	@GetMapping(value = "/get-state-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> stateList(@RequestParam Integer country) {
		logger.info("Method : stateList starts");
		
		logger.info("Method : stateList ends");
		return sigUpLogInDao.getStateList(country);
	}
	
	/** Fetch User Title List **/
	@GetMapping(value = "/get-district-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> districtList(@RequestParam Integer state) {
		logger.info("Method : districtList starts");
		
		logger.info("Method : districtList ends");
		return sigUpLogInDao.getDistrictList(state);
	}
	
	/** Fetch User Title List **/
	@GetMapping(value = "/get-city-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> cityList(@RequestParam Integer district) {
		logger.info("Method : cityList starts");
		
		logger.info("Method : cityList ends");
		return sigUpLogInDao.getCityList(district);
	}
	
	@GetMapping(value = "/get-hospital-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> hospitalList(@RequestParam Integer doctor) {
		logger.info("Method : hospitalList starts");
		
		logger.info("Method : hospitalList ends");
		return sigUpLogInDao.getHospitalList(doctor);
	}
	
	/** Fetch all organization List **/
	@GetMapping(value = "/get-all-organization-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> organizationList() {
		logger.info("Method : organizationList starts");

		logger.info("Method : organizationList ends");
		return sigUpLogInDao.organizationList();
	}
	
	/** Fetch Gender Type List **/
	@GetMapping(value = "/get-doctor-speciality-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> doctorSpecialityList() {
		logger.info("Method : specialityList starts");
		
		logger.info("Method : specialityList ends");
		return sigUpLogInDao.getDoctorSpecialityList();
	}
	
	@GetMapping(value = "/get-health-provider-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> healthProviderList() {
		logger.info("Method : healthProviderList starts");
		
		
		logger.info("Method : healthProviderList ends");
		return sigUpLogInDao.getHealthProviderList();
	}
	
	@GetMapping(value = "/get-relation-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> relationList() {
		logger.info("Method : relationList starts");
		
		logger.info("Method : relationList ends");
		return sigUpLogInDao.getRelationList();
	}
	
	@GetMapping(value = "/get-pharmacy-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> pharmacyList() {
		logger.info("Method : pharmacyList starts");
		
		logger.info("Method : pharmacyList ends");
		return sigUpLogInDao.getPharmacyList();
	}
	
	@GetMapping(value = "/get-adm-equipment-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> admEquipmentList() {
		logger.info("Method : admEquipmentList starts");
		
		logger.info("Method : admEquipmentList ends");
		return sigUpLogInDao.getAdmEquipmentList();
	}
	
	@GetMapping(value = "/get-allergy-name-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> allergyNameList() {
		logger.info("Method : allergyNameList starts");
		
		logger.info("Method : allergyNameList ends");
		return sigUpLogInDao.getallergyNameListDao();
	}
	
	@GetMapping(value = "/get-allergy-type-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> allergyTypeList() {
		logger.info("Method : allergyTypeList starts");
		
		logger.info("Method : allergyTypeList ends");
		return sigUpLogInDao.getallergyTypeListDao();
	}
	
	@GetMapping(value = "/get-doctor-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> doctorList(@RequestParam Integer type,@RequestParam Integer city) {
		logger.info("Method : doctorList starts");
		
		logger.info("Method : doctorList ends");
		return sigUpLogInDao.getDoctorList(type,city);
	}
	
	/** Patient Register By Pathologist **/
	@PostMapping(value = "/signup-by-pathologist")
	public ResponseEntity<JsonResponse<Object>> signUpByPathologist(@RequestBody SignUpModel data) {
		logger.info("Method : signUpByPathologist starts");
		
		logger.info("Method : signUpByPathologist ends");
		return sigUpLogInDao.signUpByPathologistDao(data);
	}
	/** Fetch Patient Registration List **/
	@GetMapping(value = "/get-patient-registration-list")
	public ResponseEntity<JsonResponse<List<SignUpModel>>> getPatientRegistrationList(@RequestParam String userid) {
		logger.info("Method : getPatientRegistrationList starts");
		
		logger.info("Method : getPatientRegistrationList ends");
		return sigUpLogInDao.getPatientRegistrationList(userid);
	}
	/** Fetch Patient Registration List **/
	@GetMapping(value = "/get-patient-details")
	public ResponseEntity<JsonResponse<UserProfileAPIModel>> getPatientDetailsRestApi(@RequestParam String userid) {
		logger.info("Method : getPatientDetailsRestApi starts");
		
		logger.info("Method : getPatientDetailsRestApi ends");
		return sigUpLogInDao.getPatientDetailsRestApi(userid);
	}
	
	@GetMapping(value = "/other-user-profile")
	public ResponseEntity<JsonResponse<OtherUsersProfileModel>> othersUserProfile(@RequestParam String userid) {
		logger.info("Method : othersUserProfile starts");
		
		logger.info("Method : othersUserProfile ends");
		return sigUpLogInDao.othersUserProfile(userid);
	}
	
	@GetMapping(value = "/get-ambulance-org-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ambulanceList() {
		logger.info("Method : ambulanceList starts");
		
		logger.info("Method : ambulanceList ends");
		return sigUpLogInDao.ambulanceList();
	} 
	
	@GetMapping(value = "/get-bloodbank-org-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> bloodbankList() {
		logger.info("Method : bloodbankList starts");
		
		logger.info("Method : bloodbankList ends");
		return sigUpLogInDao.bloodbankList();
	}
	
	@GetMapping(value = "/get-ngo-org-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ngoList() {
		logger.info("Method : ngoList  starts");
		
		logger.info("Method : ngoList ends");
		return sigUpLogInDao.ngoList();
	}
	
	@GetMapping(value = "/get-pathology-lab-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> pathologylabList() {
		logger.info("Method : pathologylabList  starts");
		
		logger.info("Method : pathologylabList ends");
		return sigUpLogInDao.getpathologylabList();
	}
	
	@GetMapping(value = "/get-testname-list")
	public ResponseEntity<JsonResponse<List<CountryModel>>> testnameList() {
		logger.info("Method : testnameList  starts");
		
		logger.info("Method : testnameList ends");
		return sigUpLogInDao.gettestNameList();
	}
	
	@GetMapping(value = "/get-organ-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> organList() {
		logger.info("Method : organList  starts");
		
		logger.info("Method : organList ends");
		return sigUpLogInDao.getOrganList();
	}
	@GetMapping(value = "/get-tissue-list")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> tissueList() {
		logger.info("Method : tissueList  starts");
		
		logger.info("Method : tissueList ends");
		return sigUpLogInDao.getTissueList();
	}
	/* Forgot Password - Check */
	@PostMapping(value = "/forgot-password-get-otp")
	public ResponseEntity<JsonResponse<Object>> getOTPForFOrgotPassword(@RequestBody DropDownModel data) {
		logger.info("Method : getOTPForFOrgotPassword starts");
		
		logger.info("Method : getOTPForFOrgotPassword ends");
		return sigUpLogInDao.getOTPForFOrgotPassword(data);
	}
	
	/* Forgot Password - Change Password */
	@PostMapping(value = "/change-password")
	public ResponseEntity<JsonResponse<Object>> changePassword(@RequestBody DropDownModel data) {
		logger.info("Method : changePassword starts");
		
		logger.info("Method : changePassword ends");
		return sigUpLogInDao.changePassword(data);
	}
//	@GetMapping(value = "/get-organ-list")
//	public ResponseEntity<Object> getOrganList() {
//		logger.info("Method : getOrganList  starts");
//		
//		logger.info("Method : getOrganList ends");
//		return sigUpLogInDao.getOrganList();
//	}
	
}
