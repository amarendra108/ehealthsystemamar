/**
 *  Defines the dummy entity  for Stored Procedure. 
 */
package nirmalya.aatithya.restmodule.common;

import java.io.IOException;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity

@NamedStoredProcedureQueries({
	
	/* 21-10-2021 */
	@NamedStoredProcedureQuery(name = "check_userid_exist", procedureName = "check_userid_exist", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
	@NamedStoredProcedureQuery(name = "change_password", procedureName = "change_password", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class) }),

		// procedures for mobile api
	@NamedStoredProcedureQuery(name = "test_name_list", procedureName = "test_name_list", parameters = {}),
	/* 101 */
	@NamedStoredProcedureQuery(name = "organ_list", procedureName = "organ_list", parameters = {}),
	/* 102 */
	@NamedStoredProcedureQuery(name = "tissue_list", procedureName = "tissue_list", parameters = {}),
	/** 12-10-2021 **/
	@NamedStoredProcedureQuery(name = "change_test_status", procedureName = "change_test_status", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),
	@NamedStoredProcedureQuery(name = "view_test_orderlist_bylab", procedureName = "view_test_orderlist_bylab", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
	@NamedStoredProcedureQuery(name = "view_requested_test_details", procedureName = "view_requested_test_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class) }),
	@NamedStoredProcedureQuery(name = "post_lab_request_byuser", procedureName = "post_lab_request_byuser", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testsubquery", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
	@NamedStoredProcedureQuery(name = "lab_list_by_location", procedureName = "lab_list_by_location", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "location", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class) }),
	@NamedStoredProcedureQuery(name = "view_teststatus_orderlist", procedureName = "view_teststatus_orderlist", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),
	
	/* 06-10-2021 - GEO MITRAA */
	@NamedStoredProcedureQuery(name = "save_patient_registration_by_geo", procedureName = "save_patient_registration_by_geo", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "firstname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "lastname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "statename", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "cityid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pincode", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "mail", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bloodgroup", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "occp", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dob", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "maritialstatus", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pancard", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "votercard", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhaar", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "licenceno", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "height", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "weight", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "bmi", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pulse", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "respiration", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "systolicbp", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "diastolicbp", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "oxygensaturation", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "tempincel", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "insertvitalquery", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "qualification", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "specialization", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "emername", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "emercontact", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "relation", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "insertemercontactquery", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "familydoctorname", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "familydoctorcontactno", type = String.class),
//			@StoredProcedureParameter(mode = ParameterMode.IN, name = "speciality", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "insertfamilydocquery", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "hospital", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "qrcode", type = String.class) }),
	
	
	@NamedStoredProcedureQuery(name = "geo_post_doctor_medication", procedureName = "geo_post_doctor_medication", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "medname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "noofdose", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "duration", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "remarks", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "datamorn", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "datanoon", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataeven", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "quantity", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorname", type = String.class) }),

	@NamedStoredProcedureQuery(name = "geo_post_doctor_test_details", procedureName = "geo_post_doctor_test_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "remarks", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testgroup", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorname", type = String.class) }),
	
	
	
	
	@NamedStoredProcedureQuery(name = "post_doctor_test_details", procedureName = "post_doctor_test_details", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "remarks", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testgroup", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "testname", type = String.class) }),
	/* 05-10-2021 - GEO MITRAA */
	@NamedStoredProcedureQuery(name = "geo_post_doctor_appointment", procedureName = "geo_post_doctor_appointment", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_datefrom", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_caspaper", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_opdid", type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_timefrom", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctor", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_notes", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_appid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hospitalname", type = String.class), }),
	
	@NamedStoredProcedureQuery(name = "view_user_testdtls_byappno", procedureName = "view_user_testdtls_byappno", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "geo_update_appointment_status", procedureName = "geo_update_appointment_status", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appstatus", type = String.class) }),
	
	@NamedStoredProcedureQuery(name = "geo_post_pharmacy_request_byuser", procedureName = "geo_post_pharmacy_request_byuser", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pharmid", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "medname", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "pnote", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 02-10-2021 - GEO MITRAA */
		/* 82 */
		/*@NamedStoredProcedureQuery(name = "save_patient_registration_by_geo", procedureName = "save_patient_registration_by_geo", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "firstname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "lastname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "statename", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cityid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pincode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bloodgroup", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "occp", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "maritialstatus", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pancard", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "votercard", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhaar", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "licenceno", type = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "height", type =
				// String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "weight", type =
				// String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "bmi", type =
				// String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "pulse", type =
				// String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "respiration", type
				// = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "systolicbp", type
				// = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "diastolicbp", type
				// = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "oxygensaturation",
				// type = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "tempincel", type =
				// String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "insertvitalquery", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "qualification", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "specialization", type = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "emername", type =
				// String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "emercontact", type
				// = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "relation", type =
				// String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "insertemercontactquery", type = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "familydoctorname",
				// type = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name =
				// "familydoctorcontactno", type = String.class),
				// @StoredProcedureParameter(mode = ParameterMode.IN, name = "speciality", type
				// = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "insertfamilydocquery", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "hospital", type = String.class) }),*/

		// * * for login routines

		/* Mobile Sign Up & Log In */
		@NamedStoredProcedureQuery(name = "getCountryList", procedureName = "user_country_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "getUserTitleList", procedureName = "user_title_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "getGenderList", procedureName = "user_gender_list", parameters = {}),
		// @NamedStoredProcedureQuery(name = "getStateList", procedureName =
		// "user_state_list", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type =
		// Integer.class) }),
		@NamedStoredProcedureQuery(name = "searchByPatientId", procedureName = "search_by_patient_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regNo", type = String.class) }),
		/* 6 */
		@NamedStoredProcedureQuery(name = "screeningTestAppointment", procedureName = "appointment_for_screeningtest", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testTime", type = String.class) }),
		/* 7 */
		@NamedStoredProcedureQuery(name = "screeningChkUpAppointment", procedureName = "appointment_for_screeningchkup", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testTime", type = String.class) }),
		/* 8 */
		// @NamedStoredProcedureQuery(name = "registerPatientByPathologist",
		// procedureName = "user_registration_mobile", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "regId", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patName", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patMobile", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "profileImage",
		// type = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patAge", type =
		// Integer.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patGender", type =
		// Integer.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type =
		// Integer.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type =
		// Integer.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "height", type =
		// Double.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "weight", type =
		// Double.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patEmail", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhar", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "enteredBy", type =
		// String.class) }),

		@NamedStoredProcedureQuery(name = "registerPatientByPathologist", procedureName = "user_registration_mobile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patMobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "profileImage", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patAge", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patGender", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "height", type = Double.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "weight", type = Double.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patEmail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhar", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "enteredBy", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dist", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "qrcode", type = String.class) }),
		/* 9 */
		@NamedStoredProcedureQuery(name = "saveMedTelApiData", procedureName = "save_medtel_api_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medTelId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "thpId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "thpName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pMobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pGender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pAge", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screeningDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "reportUrl", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testGroup", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testResult", type = String.class) }),
		/* 10 */
		@NamedStoredProcedureQuery(name = "appointmentScreenTestListData", procedureName = "get_appointmentlist_apiscrntestdata", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointDate", type = String.class) }),
		/* 11 */
		@NamedStoredProcedureQuery(name = "appointmentScreenChkUpListData", procedureName = "get_appointmentlist_apiscrnchkupdata", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointDate", type = String.class) }),
		/* 12 */
		@NamedStoredProcedureQuery(name = "changeScreeningTestStatus", procedureName = "change_screeningtest_status_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screenTestId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screenTestStatus", type = String.class) }),
		/* 13 */
		@NamedStoredProcedureQuery(name = "changeCheckUpStatus", procedureName = "change_checkup_status_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screenTestId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screenTestStatus", type = String.class) }),
		/* 14 */
		// @NamedStoredProcedureQuery(name = "viewMedTeltestList", procedureName =
		// "view_medtellist_apidata", parameters = {}),
		@NamedStoredProcedureQuery(name = "viewMedTeltestList", procedureName = "view_medtellist_apidata", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "start", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "search", type = String.class) }),

		@NamedStoredProcedureQuery(name = "view_medicine_orderlist_bypharmacist", procedureName = "view_medicine_orderlist_bypharmacist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "change_medicine_status", procedureName = "change_medicine_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),

		@NamedStoredProcedureQuery(name = "ambulance_list", procedureName = "ambulance_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "bloodbank_list", procedureName = "bloodbank_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "ngo_list", procedureName = "ngo_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "viewMedTeltestListId", procedureName = "view_medtellist_apidata", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regno", type = String.class) }),

		/* 15 */
		@NamedStoredProcedureQuery(name = "viewMedTeltestDetails", procedureName = "view_medteldetails_apidata", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medteluniqueid", type = String.class) }),
		/* 16 */
		@NamedStoredProcedureQuery(name = "getPatientRegistrationList", procedureName = "get_registered_patient_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "saveOtherSystemAPIData", procedureName = "save_other_system_api_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pMobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screeningDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testResult", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testUnit", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "refRange", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "sampleNo", type = String.class) }),

		/**
		 * for User registration--------By Amar
		 */
		@NamedStoredProcedureQuery(name = "user_title_list", procedureName = "user_title_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_country_list", procedureName = "user_country_list", parameters = {}),

		// @NamedStoredProcedureQuery(name = "user_speciality_list", procedureName =
		// "user_speciality_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_speciality_list", procedureName = "user_speciality_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roleid", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "user_bloodGroup_list", procedureName = "user_bloodGroup_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_roleUser_list", procedureName = "user_roleUser_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_patient_profile_gender_drpdwn", procedureName = "user_patient_profile_gender_drpdwn", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_state_list", procedureName = "user_state_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "user_district_list", procedureName = "user_district_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_city_list", procedureName = "user_city_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_autoSearch_organization_list", procedureName = "user_autoSearch_organization_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_plan_card", procedureName = "user_plan_card", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "descName", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_add_registration", procedureName = "user_add_registration", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profileImg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_countryName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_stateName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qrCode", type = String.class),}),
		
		@NamedStoredProcedureQuery(name = "user_add_prof_registration", procedureName = "user_add_prof_registration", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profileImg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hsId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_exp", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_role", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_speciality", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_jobType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_gender", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_file", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_profile_maritialsts_drpdwn", procedureName = "user_patient_profile_maritialsts_drpdwn", parameters = {}),

		@NamedStoredProcedureQuery(name = "organisation_type_drpdwn", procedureName = "organisation_type_drpdwn", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_add_organisation", procedureName = "user_add_organisation", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgAddress", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgTypeId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgDocNm", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgAttachment", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgRegdNo", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orgGst", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getCountry", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getState", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getDist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getCity", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getOrgPin", type = String.class), }),

		/**
		 * for patient immunization --------By Amar
		 */
		@NamedStoredProcedureQuery(name = "patient_immunization_type", procedureName = "patient_immunization_type", parameters = {}),

		@NamedStoredProcedureQuery(name = "patient_immunization_view", procedureName = "patient_immunization_view", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patId", type = BigInteger.class) }),

		/*
		 * View patient Details(Lab)
		 */

		@NamedStoredProcedureQuery(name = "lab_uhidno_autosearch", procedureName = "lab_uhidno_autosearch", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class) }),

		@NamedStoredProcedureQuery(name = "view_health_screening", procedureName = "view_health_screening", parameters = {}),

		@NamedStoredProcedureQuery(name = "lab_health_screening_add", procedureName = "lab_health_screening_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientIds", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getStartDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getStartTime", type = String.class), }),
		
		
		@NamedStoredProcedureQuery(name = "lab_dashboard_count", procedureName = "lab_dashboard_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)}),


		@NamedStoredProcedureQuery(name = "lab_dashboard_test_report_done", procedureName = "lab_dashboard_test_report_done",parameters = {}),



		@NamedStoredProcedureQuery(name = "lab_dashboard_health_checkup", procedureName = "lab_dashboard_health_checkup",parameters = {}),

//
//		@NamedStoredProcedureQuery(name = "lab_dashboard_view", procedureName = "lab_dashboard_view", parameters = {}),

		/**
		 * for Lab WalkinUser Registration --------By Amar
		 */

		@NamedStoredProcedureQuery(name = "lab_walkinUser_getAddress", procedureName = "lab_walkinUser_getAddress", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class), }),

		@NamedStoredProcedureQuery(name = "lab_add_walkinUser_registration", procedureName = "lab_add_walkinUser_registration", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profileImg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_title", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_countryName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_stateName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_createdBy", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qrCode", type = String.class)}),
		/**
		 * for Lab Test Report --------By Amar
		 */

		// patient lab test
		@NamedStoredProcedureQuery(name = "user_patient_test_view_details", procedureName = "user_patient_test_view_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "labid", type = String.class), }),
		// Test Report Group Name
		@NamedStoredProcedureQuery(name = "user_test_report_groupname", procedureName = "user_test_report_groupname", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_test_report_by_grpName", procedureName = "user_test_report_by_grpName", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "grpId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_test_report_add_report", procedureName = "user_test_report_add_report", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_testSubQuery", type = String.class),

		}),
		/**
		 * for Health Chart --------By Amar
		 */
		@NamedStoredProcedureQuery(name = "patient_healthChart_forHematology", procedureName = "patient_healthChart_forHematology", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_healthChart_forKidneyTest", procedureName = "patient_healthChart_forKidneyTest", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_healthChart_forLipidProfile", procedureName = "patient_healthChart_forLipidProfile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_healthChart_forBloodSugar", procedureName = "patient_healthChart_forBloodSugar", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_healthChart_forElectrolyte", procedureName = "patient_healthChart_forElectrolyte", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_healthChart_forLiverTest", procedureName = "patient_healthChart_forLiverTest", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_health_chart_details", procedureName = "user_patient_health_chart_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_health_chart", procedureName = "user_patient_health_chart", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),
		/* Deepak */

		// // for Doctor dropdown in consultation module
		@NamedStoredProcedureQuery(name = "user_doctor_list", procedureName = "user_doctor_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "specialistId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cityId", type = String.class) }),
		// // for Doctor dropdown in consultation module
		// @NamedStoredProcedureQuery(name = "user_hospital_list", procedureName =
		// "user_hospital_list", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorName", type
		// = String.class) }),

		// // get doctor avaible
		// @NamedStoredProcedureQuery(name = "user_doctor_availble", procedureName =
		// "user_doctor_availble", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "time1", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "workday", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorId", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type =
		// String.class) }),
		// // add consultant
		// @NamedStoredProcedureQuery(name = "user_consultant_add", procedureName =
		// "user_consultant_add", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_datefrom", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_caspaper", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_opdid", type =
		// Integer.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_timefrom", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctor", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_notes", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hospitalname",
		// type = String.class)}),
		// // view consultant
		// @NamedStoredProcedureQuery(name = "user_consultationView", procedureName =
		// "user_consultationView", parameters = {}),
		// @NamedStoredProcedureQuery(name = "user_view_exclude_month", procedureName =
		// "user_view_exclude_month", parameters = {}),

		////////////////////////////////////////////////// base entity
		////////////////////////////////////////////////// CONSULTANT////////////////////////////////////////////////////
		// add consultant
		@NamedStoredProcedureQuery(name = "user_consultant_add", procedureName = "user_consultant_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_datefrom", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_timefrom", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctor", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_notes", type = String.class) }),
		// view consultant
		@NamedStoredProcedureQuery(name = "user_consultationView", procedureName = "user_consultationView", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "user_view_exclude_month", procedureName = "user_view_exclude_month", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		// consultant doctor list
		@NamedStoredProcedureQuery(name = "user_consultant_doctorlist", procedureName = "user_consultant_doctorlist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchvalue", type = String.class) }),
		// get doctor avaible
		@NamedStoredProcedureQuery(name = "user_doctor_date_time", procedureName = "user_doctor_date_time", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),
		// /gettimeslot
		@NamedStoredProcedureQuery(name = "user_consultant_all_time_slot", procedureName = "user_consultant_all_time_slot", parameters = {}),

		// time-interval

		@NamedStoredProcedureQuery(name = "get_time_interval_list", procedureName = "get_time_interval_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bookdate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromtime", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "totime", type = String.class), }),
		// get doctor profile

		@NamedStoredProcedureQuery(name = "view_dcotor_consultant_profile", procedureName = "view_dcotor_consultant_profile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "useraccount", type = String.class), }),
		// set doctor rating

		@NamedStoredProcedureQuery(name = "user_consultant_set_rating", procedureName = "user_consultant_set_rating", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ratingid", type = String.class), }),
		// patient lab test
		@NamedStoredProcedureQuery(name = "user_patient_test_details", procedureName = "user_patient_test_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),
		// patient test download
		@NamedStoredProcedureQuery(name = "user_patient_test_details_download", procedureName = "user_patient_test_details_download", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testdate", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_test_names_pdf", procedureName = "user_patient_test_names_pdf", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testdate", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_test_names_groupby_pdf", procedureName = "user_patient_test_names_groupby_pdf", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "groupname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testdate", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_test_names", procedureName = "user_patient_test_names", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class) }),
		@NamedStoredProcedureQuery(name = "user_patient_test_names_groupby", procedureName = "user_patient_test_names_groupby", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "groupname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class) }),
		// get chemist list
		@NamedStoredProcedureQuery(name = "patient_get_chemist_list", procedureName = "patient_get_chemist_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_place", type = String.class), }),
		// REQUEST ADD chemist
		@NamedStoredProcedureQuery(name = "patient_chemist_request_add", procedureName = "patient_chemist_request_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_values", type = String.class), }),
		// Request mdcn list
		@NamedStoredProcedureQuery(name = "patient_get_request_mdcn_list", procedureName = "patient_get_request_mdcn_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		
	

		/*
		 * Dashboard Profile.....By Rasmita
		 * 
		 */
		@NamedStoredProcedureQuery(name = "patient_dashboard_profile_bloodgroup", procedureName = "patient_dashboard_profile_bloodgroup", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),

		/*
		 * Dashboard Insurance
		 * 
		 */
		@NamedStoredProcedureQuery(name = "dashboard_Insurancet", procedureName = "dashboard_Insurancet", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),
		/*
		 * Dashboard medicals Condition
		 * 
		 */
		@NamedStoredProcedureQuery(name = "dashboard_medical_condition", procedureName = "dashboard_medical_condition", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),
		/*
		 * Dashboard Family Doctors
		 * 
		 */
		@NamedStoredProcedureQuery(name = "dashboard_family_doctors", procedureName = "dashboard_family_doctors", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),
		/*
		 * Dashboard Medication
		 * 
		 */
		@NamedStoredProcedureQuery(name = "dashboard_medication", procedureName = "dashboard_medication", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),
		/*
		 * Dashboard Emergency Contact
		 * 
		 */
		@NamedStoredProcedureQuery(name = "patient_editDashBoardEmergencyContact", procedureName = "patient_editDashBoardEmergencyContact", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),

		/*
		 * Dashboard TestReport
		 * 
		 */
		@NamedStoredProcedureQuery(name = "dashboard_test_report", procedureName = "dashboard_test_report", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),
		/*
		 * Dashboard Allergy
		 * 
		 */
		@NamedStoredProcedureQuery(name = "patient_dash_board_allergy", procedureName = "patient_dash_board_allergy", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),

		/*
		 * Dashboard Appointment
		 * 
		 */
		@NamedStoredProcedureQuery(name = "dashboard_appointment", procedureName = "dashboard_appointment", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),

		/*
		 * Dashboard Medication
		 * 
		 */
		@NamedStoredProcedureQuery(name = "dashboard_appointment_medication", procedureName = "dashboard_appointment_medication", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardID", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_count_appointment", procedureName = "patient_count_appointment", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dashboardid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "ehealth_state_list", procedureName = "ehealth_state_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "ehealth_district_list", procedureName = "ehealth_district_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = Integer.class) }),

		/**
		 * for User Login-------
		 */

		@NamedStoredProcedureQuery(name = "getLogInDetails", procedureName = "get_login_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_username", type = String.class) }),
		@NamedStoredProcedureQuery(name = "getUserMenu", procedureName = "get_user_menu", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roles", type = String.class) }),
		@NamedStoredProcedureQuery(name = "getUserFunction", procedureName = "get_user_function", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roles", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "moduleid", type = String.class) }),
		/*
		 * patient details........By Manmayee
		 */
		@NamedStoredProcedureQuery(name = "user_edit_patient_details", procedureName = "user_edit_patient_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_edit_patient_emercontact_details", procedureName = "user_edit_patient_emercontact_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = BigInteger.class) }),

		@NamedStoredProcedureQuery(name = "user_edit_patient_famdoc_details", procedureName = "user_edit_patient_famdoc_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = BigInteger.class) }),
		@NamedStoredProcedureQuery(name = "user_patient_emercontact_edit", procedureName = "user_patient_emercontact_edit", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "emerContactId", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_dist_list", procedureName = "user_patient_dist_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = Integer.class) }),

		@NamedStoredProcedureQuery(name = "user_edit_patient_vitalsign_details", procedureName = "user_edit_patient_vitalsign_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_patient_allername_drpdwn_details", procedureName = "user_patient_allername_drpdwn_details", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_patient_allertype_drpdwn_details", procedureName = "user_patient_allertype_drpdwn_details", parameters = {}),

		// @NamedStoredProcedureQuery(name = "user_patirnt_detaails_alergy_add",
		// procedureName = "user_patirnt_detaails_alergy_add", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_patient_bname_drpdwn_details", procedureName = "user_patient_bname_drpdwn_details", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_patient_details_alergy_add", procedureName = "user_patient_details_alergy_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alernm", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alertype", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alerseverty", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alerrect", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alerupdatedby", type = String.class) }),
		@NamedStoredProcedureQuery(name = "user_patient_details_biomedical_add", procedureName = "user_patient_details_biomedical_add", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bioname", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_biorection", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_imdate", type = String.class) }),
		/*
		 * modify vitals sign
		 */

		@NamedStoredProcedureQuery(name = "patient_details_vitals_modify", procedureName = "patient_details_vitals_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value1", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value2", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value3", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value4", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value5", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value6", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value7", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value8", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value9", type = Double.class) }),
		/*
		 * add vitals sign
		 */

		@NamedStoredProcedureQuery(name = "patient_details_vitals_add", procedureName = "patient_details_vitals_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value1", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value2", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value3", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value4", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value5", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value6", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value7", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value8", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "actual_value9", type = Double.class),

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ck", type = String.class) }),

		/* save emergency contact */

		@NamedStoredProcedureQuery(name = "patient_details_emercontact_add", procedureName = "patient_details_emercontact_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ename", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_erelatoion", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_emobno", type = String.class) }),

		/* update emergency contact */

		@NamedStoredProcedureQuery(name = "patient_details_emercontact_update", procedureName = "patient_details_emercontact_update", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_puserid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ename", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_erelatoion", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_emobno", type = String.class) }),

		// family doctor*/

		@NamedStoredProcedureQuery(name = "patient_details_famdoc_add", procedureName = "patient_details_famdoc_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ename", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_especiality", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_emobno", type = String.class) }),

		/* save identification */

		@NamedStoredProcedureQuery(name = "patient_details_identification_modify", procedureName = "patient_details_identification_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pan", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pass", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_aadhra", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_votr", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lAuth", type = String.class) }),

		/* save contact */
		@NamedStoredProcedureQuery(name = "patient_details_contact_modify", procedureName = "patient_details_contact_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_add", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phn", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pemail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dist", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_zipcode", type = String.class) }),

		/* save education */
		@NamedStoredProcedureQuery(name = "user_patient_details_education_modify", procedureName = "user_patient_details_education_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_qual", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_spl", type = String.class) }),

		/* save family details */

		@NamedStoredProcedureQuery(name = "patient_details_familydtl_add", procedureName = "patient_details_familydtl_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_famrltn", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_famHealthId", type = String.class), }),
		/* delete family details */

		@NamedStoredProcedureQuery(name = "patient_details_familydtl_delete", procedureName = "patient_details_familydtl_delete", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "slno", type = String.class) }),
		/* delete emergency details */

		@NamedStoredProcedureQuery(name = "patient_details_emercontact_delete", procedureName = "patient_details_emercontact_delete", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "slno", type = String.class) }),
		/* delete family doctor */

		@NamedStoredProcedureQuery(name = "patient_details_familydoctor_delete", procedureName = "patient_details_familydoctor_delete", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "slno", type = String.class) }),
		/* update family details */

		@NamedStoredProcedureQuery(name = "patient_details_familydtl_update", procedureName = "patient_details_familydtl_update", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_slno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_famrltn", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_famHealthId", type = String.class), }),

		/* save personal */

		@NamedStoredProcedureQuery(name = "patient_details_personal_modify", procedureName = "patient_details_personal_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_img", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_bld", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_age", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_gen", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mart", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_oct", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_view_patient_details_education", procedureName = "user_view_patient_details_education", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_patient_emercontact_drpdwn_details", procedureName = "user_patient_emercontact_drpdwn_details", parameters = {}),
		@NamedStoredProcedureQuery(name = "user_patient_famdoc_drpdwn_details", procedureName = "user_patient_famdoc_drpdwn_details", parameters = {}),

		@NamedStoredProcedureQuery(name = "user_view_patient_details_alergy", procedureName = "user_view_patient_details_alergy", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "user_view_patient_details_familydts", procedureName = "user_view_patient_details_familydts", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_view_patient_details_biomedical", procedureName = "user_view_patient_details_biomedical", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "user_autoSearch_profile_familyList_list", procedureName = "user_autoSearch_profile_familyList_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class) }),
		
		/*
		 * edit  patient contact details
		 *
		 */
		@NamedStoredProcedureQuery(name = "patient_edit_contact_deatils", procedureName = "patient_edit_contact_deatils", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patid", type = String.class) }),
		
		/*
		 * dm dashboard
		 */
		/*
		 * @NamedStoredProcedureQuery(name = "dm_dashboard_view_count", procedureName =
		 * "dm_dashboard_view_count", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "supId", type =
		 * String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "dm_dashboard_get_idd", procedureName =
		 * "dm_dashboard_get_idd", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "collectorid", type
		 * = String.class) }),
		 */
		/*********************************
		 * DM Dashboard
		 *************************************/
		/*
		 * Doctor profile by manmayee
		 * 
		 * 
		 * Doctor Profile
		 * 
		 */
		@NamedStoredProcedureQuery(name = "doctor_edit_profile", procedureName = "doctor_edit_profile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctOrId", type = String.class) }),

		/*
		 * Doctor Profile Identification
		 * 
		 */
		@NamedStoredProcedureQuery(name = "doctor_edit_identification", procedureName = "doctor_edit_identification", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctOrId", type = String.class) }),

		/*
		 * Doctor Home Address Edit
		 * 
		 */
		@NamedStoredProcedureQuery(name = "doctor_edit_home_address", procedureName = "doctor_edit_home_address", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctOrId", type = String.class) }),

		/*
		 * Doctor Home Address View
		 * 
		 */
		@NamedStoredProcedureQuery(name = "doctor_view_home_address", procedureName = "doctor_view_home_address", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctOrId", type = String.class) }),
		// MODIFY DOCTOR PROFILE

		@NamedStoredProcedureQuery(name = "doctor_profile_modify", procedureName = "doctor_profile_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctorId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_education", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_proimg", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dobid", type = String.class), }),

		// MODIFY DOCTOR IDENTIFICATION

		@NamedStoredProcedureQuery(name = "doctor_identification_modify", procedureName = "doctor_identification_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctorId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_imaNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_panCardNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_passportNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_adharNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_voterCardNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_licenseNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_licenseAuthority", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_digitalSign", type = String.class), }),

		// MODIFY DOCTOR CONTACT DETAILS

		@NamedStoredProcedureQuery(name = "doctor_contact_details_modify", procedureName = "doctor_contact_details_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctorId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_country", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_district", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_zipCode", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_office", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class), }),

		/*
		 * Doctor Attachment
		 * 
		 */
		@NamedStoredProcedureQuery(name = "doctor_view_document_attachment", procedureName = "doctor_view_document_attachment", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "r_id", type = String.class) }),
		/*
		 * Doctor details for attachment
		 * 
		 */
		@NamedStoredProcedureQuery(name = "doctor_view_details", procedureName = "doctor_view_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "drid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "roleid", type = String.class) }),

		/*
		 * Doctor Document Upload
		 */
		@NamedStoredProcedureQuery(name = "doctor_profile_document_upload_add", procedureName = "doctor_profile_document_upload_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_documentupload", type = String.class), }),

		/*
		 * Doctor Document Download
		 */
		@NamedStoredProcedureQuery(name = "doctor_document_upload", procedureName = "doctor_document_upload", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patid", type = String.class) }),
		
		
		/*
		 *  Patient Ambulance Request	Manmayee
		 */
					
			@NamedStoredProcedureQuery(name = "patient_autoserach_ambulance_owner", procedureName = "patient_autoserach_ambulance_owner", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class) }),
			/*
			 * view Patient Ambulance Request		
			 */
			@NamedStoredProcedureQuery(name = "patient_ambulance_request_view", procedureName = "patient_ambulance_request_view", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) ,
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "place", type = String.class)}),
			
			/*'
			 * Save Ambulance Patient Request by Manmayee		
			 */
			
			@NamedStoredProcedureQuery(name = "patient_ambulance_request_save", procedureName = "patient_ambulance_request_save", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ambid", type = String.class),
					//@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ambname", type = String.class), 
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ftmdest", type = String.class), 
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_todest", type = String.class), 
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patnote", type = String.class), 
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dateid", type = String.class), 
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),}),


		/*
		 * dm dashboard by satabdee
		 */
		@NamedStoredProcedureQuery(name = "dm_dashboard_view_count", procedureName = "dm_dashboard_view_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "dm_dashboard_get_idd", procedureName = "dm_dashboard_get_idd", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "collectorid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "dm_dashboard_view_patientDetails", procedureName = "dm_dashboard_view_patientDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "typeId", type = String.class) }),

		/*
		 * dm dashboard ------ By Amar
		 */
		/* Age wise total test */
		@NamedStoredProcedureQuery(name = "dm_dashboard_view_agewisetotaltest", procedureName = "dm_dashboard_view_agewisetotaltest", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		/* Area wise total test */
		@NamedStoredProcedureQuery(name = "dm_dashboard_view_areawisetotaltest", procedureName = "dm_dashboard_view_areawisetotaltest", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),
		/* Disease wise total abnormal */
		@NamedStoredProcedureQuery(name = "dm_dashboard_view_diseasewisetotalabnormal", procedureName = "dm_dashboard_view_diseasewisetotalabnormal", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		/* Disease wise total High Risk */
		@NamedStoredProcedureQuery(name = "dm_dashboard_view_diseasewisetotalhighrisk", procedureName = "dm_dashboard_view_diseasewisetotalhighrisk", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),
		/*
		 * dm dashboard------Deepak
		 */
		@NamedStoredProcedureQuery(name = "dm_dashboard_view_total_checkupNormalAbnormal", procedureName = "dm_dashboard_view_total_checkupNormalAbnormal", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "dm_dashboard_view_total_checkupMaleFemale", procedureName = "dm_dashboard_view_total_checkupMaleFemale", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "dm_dashboard_view_total_checkupAreaWise", procedureName = "dm_dashboard_view_total_checkupAreaWise", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),
		@NamedStoredProcedureQuery(name = "dm_dashboard_genderwise_disease", procedureName = "dm_dashboard_genderwise_disease", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtid", type = String.class) }),

		/*
		 * dm dashboard------Priyanka
		 */
		@NamedStoredProcedureQuery(name = "dm_dashboard_view_total_areawisenormal", procedureName = "dm_dashboard_view_total_areawisenormal", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "dm_dashboard_view_total_areawisehighrisk", procedureName = "dm_dashboard_view_total_areawisehighrisk", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtId", type = String.class) }),

		/*
		 * cm dashboard by priyanka
		 */
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_count", procedureName = "cm_dashboard_view_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class), }),

		@NamedStoredProcedureQuery(name = "cm_dashboard_get_idd", procedureName = "cm_dashboard_get_idd", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cmid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_patientDetails", procedureName = "cm_dashboard_view_patientDetails", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "typeId", type = String.class) }),
		/* Age wise total test */
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_agewisetotaltest", procedureName = "cm_dashboard_view_agewisetotaltest", parameters = {

				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),
		/* Area wise total test */
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_areawisetotaltest", procedureName = "cm_dashboard_view_areawisetotaltest", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),

		/* Disease wise total High Risk */
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_diseasewisetotalhighrisk", procedureName = "cm_dashboard_view_diseasewisetotalhighrisk", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),
		/* Disease wise total abnormal */
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_diseasewisetotalabnormal", procedureName = "cm_dashboard_view_diseasewisetotalabnormal", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),
		/* checkupMaleFemale */
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_total_checkupMaleFemale", procedureName = "cm_dashboard_view_total_checkupMaleFemale", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "cm_dashboard_view_total_checkupNormalAbnormal", procedureName = "cm_dashboard_view_total_checkupNormalAbnormal", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_total_checkupAreaWise", procedureName = "cm_dashboard_view_total_checkupAreaWise", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_total_areawisenormal", procedureName = "cm_dashboard_view_total_areawisenormal", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_view_total_areawisehighrisk", procedureName = "cm_dashboard_view_total_areawisehighrisk", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class) }),

		/**
		 * for Malaysia Dashboard --------By Rasmita
		 */

		@NamedStoredProcedureQuery(name = "pm_dashboard_view_count", procedureName = "pm_dashboard_view_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryId", type = String.class), }),

		@NamedStoredProcedureQuery(name = "pm_dashboard_get_idd", procedureName = "pm_dashboard_get_idd", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pmid", type = String.class) }),

		/*
		 * View Current Medicine Details(Medication)----- by satabdee
		 */

		@NamedStoredProcedureQuery(name = "patient_medicine_details", procedureName = "patient_medicine_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = BigInteger.class) }),

		/*
		 * View doctor Details(Medication)----- by satabdee
		 */

		@NamedStoredProcedureQuery(name = "current_doctor_details", procedureName = "current_doctor_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = BigInteger.class) }),

		/*
		 * View prescription details based on dr_id and date (Medication)----- by
		 * satabdee
		 */

		@NamedStoredProcedureQuery(name = "current_doctor_prescription_details", procedureName = "current_doctor_prescription_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dr_id", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "prescr_date", type = String.class) }),

		/*
		 * View doctor patient Details(Medication)----- by satabdee
		 */

		@NamedStoredProcedureQuery(name = "doctor_patient_details", procedureName = "doctor_patient_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = BigInteger.class) }),

		/*
		 * Auto search medicine Details(Medication)----- by satabdee
		 */

		@NamedStoredProcedureQuery(name = "doctor_autosearch_medicine_list", procedureName = "doctor_autosearch_medicine_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class) }),

		/*
		 * for patient ehealthcard --------By satabdee
		 */

		@NamedStoredProcedureQuery(name = "user_ehealthcard_details", procedureName = "user_ehealthcard_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patid", type = String.class) }),


		/*
		 * Add medicine Details (Medication)----- by satabdee
		 * 
		 */

		@NamedStoredProcedureQuery(name = "doctor_patient_prescription_details", procedureName = "doctor_patient_prescription_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_mapid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_medname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_medtype", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fromdate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_todate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_morning", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_afternoon", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_evening", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_remarks", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_drId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_appno", type = String.class), }),
		/*
		 * Medicine type drop down (Medication)----- by satabdee
		 * 
		 */
		@NamedStoredProcedureQuery(name = "doctor_medicineType_list", procedureName = "doctor_medicineType_list", parameters = {}),

		/*
		 * Auto search test name with group Details(Medication)----- by satabdee
		 */

		@NamedStoredProcedureQuery(name = "doctor_autosearch_test_list", procedureName = "doctor_autosearch_test_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "searchValue", type = String.class) }),

		/*
		 * Add test Details (Medication)----- by satabdee
		 */
		@NamedStoredProcedureQuery(name = "doctor_patient_test_details", procedureName = "doctor_patient_test_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pid", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_testname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_testgroup", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_remarks", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_drid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_appno", type = String.class), }),
		/*
		 * Doctor view test details (Medication)----- by satabdee
		 * 
		 */
		@NamedStoredProcedureQuery(name = "patient_medication_test_view", procedureName = "patient_medication_test_view", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = BigInteger.class), }),

		/*
		 * Login Details
		 * 
		 */


		@NamedStoredProcedureQuery(name = "patient_login_details", procedureName = "patient_login_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)
		}),

		/*
		 * Patient History
		 * 
		 */
		// Major Illness
		@NamedStoredProcedureQuery(name = "patient_history_view", procedureName = "patient_history_view", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pid", type = String.class)
		}),

		/*
		 * Major Surgery
		 * 
		 */

		@NamedStoredProcedureQuery(name = "patient_major_surgery", procedureName = "patient_major_surgery", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pid", type = String.class)
		}),

		/*
		 * Medical Condition
		 * 
		 */

		@NamedStoredProcedureQuery(name = "patient_medical_condition", procedureName = "patient_medical_condition", parameters = {
				
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pId", type = String.class)}),
		// Smoking List
		

		@NamedStoredProcedureQuery(name = "get_patient_smoking_list", procedureName = "get_patient_smoking_list", parameters = {}),

		/*
		 * Alcohol List
		 * 
		 */

		@NamedStoredProcedureQuery(name = "get_patient_alcohol_list", procedureName = "get_patient_alcohol_list", parameters = {}),

		@NamedStoredProcedureQuery(name = "patient_life_style_history_add", procedureName = "patient_life_style_history_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientId", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_smokingId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_alcoholId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_diet", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_exercise", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_occupation", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pets", type = String.class) }),

		@NamedStoredProcedureQuery(name = "user_edit_patient_life_style", procedureName = "user_edit_patient_life_style", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class) }),

		// Health Check Up

		@NamedStoredProcedureQuery(name = "lab_health_checkup_add", procedureName = "lab_health_checkup_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientIds", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getAppointmentDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_getAppointmentTime", type = String.class), }),

		/*
		 * Health Check Up View
		 * 
		 */

		@NamedStoredProcedureQuery(name = "view_health_checkup", procedureName = "view_health_checkup", parameters = {}),

		// patient lab test
		// @NamedStoredProcedureQuery(name = "user_patient_test_details", procedureName
		// = "user_patient_test_details", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type =
		// String.class) }),

		/*
		 * Treatment Tracker
		 * 
		 */


		@NamedStoredProcedureQuery(name = "patient_treatment_tracker", procedureName = "patient_treatment_tracker", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pId", type = String.class) 
		}),
		/*/ * Insurance View
		 * 
		 */

		@NamedStoredProcedureQuery(name = "patient_insurance_view", procedureName = "patient_insurance_view", parameters = {}),

		/*
		 * Insurance Edit
		 * 
		 */
		@NamedStoredProcedureQuery(name = "edit_patient_insurance", procedureName = "edit_patient_insurance", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "insuranceID", type = String.class) }),

		// ADD INSURANCE

		@NamedStoredProcedureQuery(name = "insurance_add", procedureName = "insurance_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientIds", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_insuranceCompany", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_insuranceType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_healthInsuranceType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_thirdPartyAdmin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_policyNo", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_premiumAmount", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_policyStartDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_policyEndDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalInsuranceAmount", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sumAssuredAmount", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_premiumDueDate", type = String.class), }),

		// MODIFY INSURANCE

		@NamedStoredProcedureQuery(name = "insurance_modify", procedureName = "insurance_modify", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_isurnceno", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patientIds", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_insuranceCompany", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_insuranceType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_healthInsuranceType", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_thirdPartyAdmin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_policyNo", type = BigInteger.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_premiumAmount", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_policyStartDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_policyEndDate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_totalInsuranceAmount", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_sumAssuredAmount", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_premiumDueDate", type = String.class), }),

		@NamedStoredProcedureQuery(name = "patient_family_details", procedureName = "patient_family_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class)
		}),
		
		@NamedStoredProcedureQuery(name = "doctor_getAll_patient", procedureName = "doctor_getAll_patient", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_patient_details_by_id", procedureName = "doctor_patient_details_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_update_patient_details_by_id", procedureName = "doctor_update_patient_details_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "note", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "opdid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_booking_count", procedureName = "doctor_booking_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),
		// doctor dashboard
		@NamedStoredProcedureQuery(name = "doctor_dashboardbooking_count", procedureName = "doctor_dashboardbooking_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_dashboard_table", procedureName = "doctor_dashboard_table", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),
		@NamedStoredProcedureQuery(name = "doctor_action_list", procedureName = "doctor_action_list", parameters = {}),
		@NamedStoredProcedureQuery(name = "doctor_dashboardpiechart_count", procedureName = "doctor_dashboardpiechart_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "doctor_user_password_by_id", procedureName = "doctor_user_password_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class) }),

		// chemist section by deepak start
		// for chemist dashboard procedureQuery

		@NamedStoredProcedureQuery(name = "chemist_dashboard", procedureName = "total_precription", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = String.class) }),

		@NamedStoredProcedureQuery(name = "patient_prescription_view", procedureName = "patient_prescription_view", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),

		// for ajax calling to view all prescription

		@NamedStoredProcedureQuery(name = "patient_prescription_model", procedureName = "patient_prescription_model", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientName", type = String.class) }),
		// for ajax calling to save status of chemist

		@NamedStoredProcedureQuery(name = "chemist_save_medicine_status", procedureName = "chemist_save_medicine_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orderid", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "cm_dashboard_genderwise_patientno", procedureName = "cm_dashboard_genderwise_patientno", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_areawise_patientno", procedureName = "cm_dashboard_areawise_patientno", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_agewise_healthcheckup", procedureName = "cm_dashboard_agewise_healthcheckup", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_genderwise_disease", procedureName = "cm_dashboard_genderwise_disease", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_riskwise_patientno", procedureName = "cm_dashboard_riskwise_patientno", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		@NamedStoredProcedureQuery(name = "cm_dashboard_phcwise_patientno", procedureName = "cm_dashboard_phcwise_patientno", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		
		
		
		
		
		
		@NamedStoredProcedureQuery(name = "cm_dashboard_checkup_status", procedureName = "cm_dashboard_checkup_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "cm_dashboard_areawise_highrisk", procedureName = "cm_dashboard_areawise_highrisk", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

@NamedStoredProcedureQuery(name = "cm_dashboard_diseasewise_total", procedureName = "cm_dashboard_diseasewise_total", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),


@NamedStoredProcedureQuery(name = "cm_dashboard_disease_trand", procedureName = "cm_dashboard_disease_trand", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		

		// for cm_dashboard
		
		@NamedStoredProcedureQuery(name = "cm_dashboard_count_test_details", procedureName = "cm_dashboard_count_test_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateId", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		//Ambulance by priyanka
		@NamedStoredProcedureQuery(name = "ambulance_dashboard_table", procedureName = "ambulance_dashboard_table", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),
		@NamedStoredProcedureQuery(name = "save_ambulance_status", procedureName = "save_ambulance_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_order", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class) }),
		@NamedStoredProcedureQuery(name = "ambulance_patient_details_by_id", procedureName = "ambulance_patient_details_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "ambulance_dashboard_count", procedureName = "ambulance_dashboard_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),



		// chemist section by deepak end
		/* rest api call */
		// @NamedStoredProcedureQuery(name = "userSelfRegistrationAPI", procedureName =
		// "user_selfregistration_mobile_api", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "regid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patname", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patlname", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patmobile", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "profileimage",
		// type = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patage", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "ageyears", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patdob", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "patgender", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "enteredby", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "ttl", type =
		// String.class) }),
		
		
		// by nimrmal opdmaster
		
		  /*
				 *doctor opd view
				   */
				@NamedStoredProcedureQuery(name = "doctor_opd_details_view", procedureName = "doctor_opd_details_view", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor_id", type = String.class) }),
				
				
				/*
				 * Opd Details Edit
				 * 
				 */
				@NamedStoredProcedureQuery(name = "doctor_opd_details_edit", procedureName = "doctor_opd_details_edit", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "opdid", type = String.class) }),
				/*
				 * Opd Details Delete
				 * 
				 */
				@NamedStoredProcedureQuery(name = "doctor_opd_details_delete", procedureName = "doctor_opd_details_delete", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "opdid", type = String.class) }),
				/** Opd master details add **/
				@NamedStoredProcedureQuery(name = "doctor_opd_details_add", procedureName = "doctor_opd_details_add", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_id", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opddate", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opdfromtime", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opdtotime", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opdremarks", type = String.class)
						}),
				/* ***Opd master details update **/
				@NamedStoredProcedureQuery(name = "doctor_opd_details_update", procedureName = "doctor_opd_details_update", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opdid", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_id", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opddate", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opdfromtime", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opdtotime", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "d_opdremarks", type = String.class)
						}),
				/* doctor my patient view */
				@NamedStoredProcedureQuery(name = "doctor_view_my_patient", procedureName = "doctor_view_my_patient", parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "userSelfRegistrationAPI", procedureName = "user_selfregistration_mobile_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patlname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patmobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "profileimage", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patage", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ageyears", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patdob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patgender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "state", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "enteredby", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ttl", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dist", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "qrcode", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "getPatientDetailsAPI", procedureName = "get_patient_details_mobile_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		
		@NamedStoredProcedureQuery(name = "update_profile_image_api", procedureName = "update_profile_image_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "profileimage", type = String.class) }),
		/* 26 */
		@NamedStoredProcedureQuery(name = "patient_mobile_number_exist", procedureName = "patient_mobile_number_exist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class) }),
		/* 27 */
		@NamedStoredProcedureQuery(name = "patient_aadhar_number_exist", procedureName = "patient_aadhar_number_exist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhar", type = String.class) }),
		/* 28 */
		@NamedStoredProcedureQuery(name = "get_hospital_for_doc_app", procedureName = "get_hospital_for_doc_app", parameters = {}),
		/* 29 */
		@NamedStoredProcedureQuery(name = "patient_emergency_contact_exist", procedureName = "patient_emergency_contact_exist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 30 */
		@NamedStoredProcedureQuery(name = "patient_family_doctor_exist", procedureName = "patient_family_doctor_exist", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 31 */
		@NamedStoredProcedureQuery(name = "hospital_for_appointment", procedureName = "hospital_for_appointment", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type = String.class) }),

		/* 12-08-2021 */
		/* 32 */
		@NamedStoredProcedureQuery(name = "doctorAvailableByDate", procedureName = "doctor_available_by_date", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),
		/* 33 */
		@NamedStoredProcedureQuery(name = "doctorAvailableByTime", procedureName = "doctor_available_by_time", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "time", type = String.class) }),
		/* 34 */
		@NamedStoredProcedureQuery(name = "get_casepaper_no", procedureName = "get_casepaper_no", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 35 */
		@NamedStoredProcedureQuery(name = "updateUserProfile", procedureName = "update_user_profile_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "regid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patdob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bloodgroup", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "ename", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "relation", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "emobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "fdocname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "speciality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docnumber", type = String.class) }),
		/* 36 */
		@NamedStoredProcedureQuery(name = "post_doctor_appointment", procedureName = "post_doctor_appointment", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_datefrom", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_caspaper", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_opdid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_timefrom", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_doctor", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_notes", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_hospitalname", type = String.class) }),
		/* 37 */
		/*
		 * @NamedStoredProcedureQuery(name = "get_user_appointment_list", procedureName
		 * = "get_user_appointment_list", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type =
		 * String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type =
		 * String.class) }),
		 * 
		 * 
		 */

		/* 06-09-2021 */
		/* 53 */
		@NamedStoredProcedureQuery(name = "get_user_emergency_contactdetails_api", procedureName = "get_user_emergency_contactdetails_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		/* 07-09-2021 */
		/* 54 */
		@NamedStoredProcedureQuery(name = "get_user_emergency_contactnumber_api", procedureName = "get_user_emergency_contactnumber_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		/* 08-09-2021 */
		/* 37,48 No Changed */
		/* 55 */
		@NamedStoredProcedureQuery(name = "post_emergency_contact_api_data", procedureName = "post_emergency_contact_api_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "relation", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class) }),

		/* 56 */
		@NamedStoredProcedureQuery(name = "view_user_meddtls_byappno", procedureName = "view_user_meddtls_byappno", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class) }),

		/* 57 */
		@NamedStoredProcedureQuery(name = "get_time_slot_api", procedureName = "get_time_slot_api", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),

		/* 13-09-2021 */
		/* 58 */
		@NamedStoredProcedureQuery(name = "health_provider_list", procedureName = "health_provider_list", parameters = {}),

		/* 14-09-2021 */
		/* 59 */
		@NamedStoredProcedureQuery(name = "find_health_provider_details", procedureName = "find_health_provider_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "longi", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "lati", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "addr", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "healthpro", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),

		/* 60 */
		@NamedStoredProcedureQuery(name = "update_emergency_contact_api_data", procedureName = "update_emergency_contact_api_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "relation", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = String.class) }),

		/* 16-09-2021 */
		/* 61 */
		@NamedStoredProcedureQuery(name = "post_user_emergency_message", procedureName = "post_user_emergency_message", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "message", type = String.class) }),

		/* 22-09-2021 */
		/* 62 */
		@NamedStoredProcedureQuery(name = "delete_medicine_byappno", procedureName = "delete_medicine_byappno", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "srlone", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "srltwo", type = String.class) }),

		/* 23-09-2021 */
		/* 63 */
		@NamedStoredProcedureQuery(name = "pharmacy_list", procedureName = "pharmacy_list", parameters = {}),

		/* 64 */
		@NamedStoredProcedureQuery(name = "pharmacy_registration_mobile", procedureName = "pharmacy_registration_mobile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "organizationid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "titleid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "experience", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "speciality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cityid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "profileImage", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "role", type = String.class) }),

		/* 65 */
		@NamedStoredProcedureQuery(name = "pharmacy_list_by_location", procedureName = "pharmacy_list_by_location", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "location", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type = String.class) }),

		/* 66 */
		@NamedStoredProcedureQuery(name = "save_screen_test_report_data", procedureName = "save_screen_test_report_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqueid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobile", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "aadhaar", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "phc", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "screentestdate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "createddate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testgroup", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testresult", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "unit", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "fromrange", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "torange", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "refrange", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),

		/** 75 **/
		@NamedStoredProcedureQuery(name = "save_allergies_api_data", procedureName = "save_allergies_api_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "allnameid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "alltypeid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "severity", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "reaction", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "updatedby", type = String.class) }),

		/** 76 **/
		@NamedStoredProcedureQuery(name = "view_requested_medicine_details", procedureName = "view_requested_medicine_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class) }),

		/** 76 **/
		@NamedStoredProcedureQuery(name = "save_biomed_implants_api_data", procedureName = "save_biomed_implants_api_data", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bioname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "biodate", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bioreason", type = String.class) }),

//		@NamedStoredProcedureQuery(name = "get_user_appointment_list", procedureName = "get_user_appointment_list", parameters = {
//				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "get_user_appointment_list", procedureName = "get_user_appointment_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class) }),
		
		/* 13-08-2021 */
		/* 38 */
		@NamedStoredProcedureQuery(name = "view_doctor_appointment_list", procedureName = "view_doctor_appointment_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class) }),
		/* 39 */
		@NamedStoredProcedureQuery(name = "changeUserAppointmentStatus", procedureName = "change_user_appointment_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appstatus", type = String.class) }),
		/* 40 */
		@NamedStoredProcedureQuery(name = "user_all_organization_list", procedureName = "user_all_organization_list", parameters = {}),
		/* 41 */
		// @NamedStoredProcedureQuery(name = "doctor_registration_mobile", procedureName
		// = "doctor_registration_mobile", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "organizationid",
		// type = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "titleid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "docname", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "educationid", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "speciality", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "dob", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "countryid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "bloodgroup", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "stateid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "districtid", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "cityid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "pin", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "homephone", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "officephone", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "mobno", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "alteremail", type
		// = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "profileImage",
		// type = String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "role", type =
		// String.class) }),

		// reception

		@NamedStoredProcedureQuery(name = "reception_appointment_count", procedureName = "reception_appointment_count", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "reception_dashboard_patient_disease", procedureName = "reception_dashboard_patient_disease", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "receptionid", type = String.class) }),
		@NamedStoredProcedureQuery(name = "reception_dashboard_appointment_status", procedureName = "reception_dashboard_appointment_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctorid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "doctor_registration_mobile", procedureName = "doctor_registration_mobile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "organizationid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "titleid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "docname", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "educationid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "speciality", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dob", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "gender", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "countryid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "bloodgroup", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "stateid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "districtid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cityid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "pin", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "homephone", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "officephone", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "email", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "alteremail", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "profileImage", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "role", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "experience", type = String.class) }),

		/* 14-08-2021 */
		/* 42 */
		@NamedStoredProcedureQuery(name = "user_personal_information_list", procedureName = "user_personal_information_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 43 */
		@NamedStoredProcedureQuery(name = "user_lab_test_report", procedureName = "user_lab_test_report", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 44 */
		@NamedStoredProcedureQuery(name = "user_medication_details", procedureName = "user_medication_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 45 */
		@NamedStoredProcedureQuery(name = "user_allergies_details", procedureName = "user_allergies_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 46 */
		@NamedStoredProcedureQuery(name = "user_biomedical_details", procedureName = "user_biomedical_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		/* 47 */
		@NamedStoredProcedureQuery(name = "get_medicinelist_withtype", procedureName = "get_medicinelist_withtype", parameters = {}),
		/* 48 */
		// @NamedStoredProcedureQuery(name = "post_doctor_medication", procedureName =
		// "post_doctor_medication", parameters = {
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "casepaper", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "medid", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "noofdose", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "fromadate", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "todate", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "remarks", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "datamorn", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "datanoon", type =
		// String.class),
		// @StoredProcedureParameter(mode = ParameterMode.IN, name = "dataeven", type =
		// String.class) }),
		//

		@NamedStoredProcedureQuery(name = "post_doctor_medication", procedureName = "post_doctor_medication", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appno", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "medid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "noofdose", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "duration", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "remarks", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "doctor", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "datamorn", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "datanoon", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dataeven", type = String.class) }),

		/* 49 */
		@NamedStoredProcedureQuery(name = "user_appointment_screentest_list", procedureName = "user_appointment_screentest_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointdate", type = String.class) }),
		/* 50 */
		@NamedStoredProcedureQuery(name = "user_appointment_checkup_list", procedureName = "user_appointment_checkup_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "appointdate", type = String.class) }),

		/* 18-08-2021 */
		/* 51 */
		@NamedStoredProcedureQuery(name = "user_vitalsign_details", procedureName = "user_vitalsign_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "other_users_profile", procedureName = "other_users_profile", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),

		@NamedStoredProcedureQuery(name = "update_vital_sign_details", procedureName = "update_vital_sign_details", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "subquery", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "casepaper", type = String.class) }),

		@NamedStoredProcedureQuery(name = "post_pharmacy_request_byuser", procedureName = "post_pharmacy_request_byuser", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "testsubquery", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class) }),
		// REQUEST ADD lab
		@NamedStoredProcedureQuery(name = "patient_lab_request_add", procedureName = "patient_lab_request_add", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_values", type = String.class), }),
		/*
		 * 
		   lab flow fro patient ---------- by Deepak
		*/
		@NamedStoredProcedureQuery(name = "patient_get_Lab_list", procedureName = "patient_get_Lab_list", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_place", type = String.class), }),
		@NamedStoredProcedureQuery(name = "lab_dashboard_view", procedureName = "lab_dashboard_view", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "reqdate", type = String.class),
		}),
		
		@NamedStoredProcedureQuery(name = "lab_dashboard_patient_modal", procedureName = "lab_dashboard_patient_modal", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "patientid", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "orderid", type = String.class)
				}),
		@NamedStoredProcedureQuery(name = "lab_save_lab_status", procedureName = "lab_save_lab_status", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_orderid", type = String.class),
				}),
		

		// for Hie dashboard
			
			@NamedStoredProcedureQuery(name = "mys_dashboard_view_count", procedureName = "mys_dashboard_view_count", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
			
			//Diagnostic Lab
					@NamedStoredProcedureQuery(name = "mys_dashboard_view_diagnostic_lab", procedureName = "mys_dashboard_view_diagnostic_lab", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
					
					//Pathology Lab
					@NamedStoredProcedureQuery(name = "mys_dashboard_view_pathology_lab", procedureName = "mys_dashboard_view_pathology_lab", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
					
					//Total Pharmacy
					@NamedStoredProcedureQuery(name = "mys_dashboard_view_total_pharmacy", procedureName = "mys_dashboard_view_total_pharmacy", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
					
					//Ambulance
					@NamedStoredProcedureQuery(name = "mys_dashboard_view_ambulance", procedureName = "mys_dashboard_view_ambulance", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
					
					//Total Blood Bank
					@NamedStoredProcedureQuery(name = "mys_dashboard_view_blood_bank", procedureName = "mys_dashboard_view_blood_bank", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
					
					//Total Insurance Service Provider
					@NamedStoredProcedureQuery(name = "mys_dashboard_view_insurance", procedureName = "mys_dashboard_view_insurance", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
					
				
					
					//Gender Wise
					@NamedStoredProcedureQuery(name = "mys_dashboard_total_gender_wise", procedureName = "mys_dashboard_total_gender_wise", parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
					
				
				
					
					//Doctor Wise
			
			@NamedStoredProcedureQuery(name = "mys_dashboard_view_doctor_month_wise", procedureName = "mys_dashboard_view_doctor_month_wise", parameters = {

					@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),

					@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),

					@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
			//pm by deepak eis hospital area wise dashboard
			@NamedStoredProcedureQuery(name = "mys_dashboard_view_hospital_area_wise", procedureName = "mys_dashboard_view_hospital_area_wise", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
	//pm by deepak eis clinic area wise dashboard
			@NamedStoredProcedureQuery(name = "mys_dashboard_view_clinic_area_wise", procedureName = "mys_dashboard_view_clinic_area_wise", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
			
			@NamedStoredProcedureQuery(name = "mys_dashboard_total_organ_donor", procedureName = "mys_dashboard_total_organ_donor", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
			
			@NamedStoredProcedureQuery(name = "mys_dashboard_total_age_wise", procedureName = "mys_dashboard_total_age_wise", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "country", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class) }),
		/*
		 * @NamedStoredProcedureQuery(name = "auditDashboardRoutines", procedureName =
		 * "audit_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "auditLinkRoutines", procedureName =
		 * "audit_linkRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "auditManageRoutines", procedureName =
		 * "audit_ManageRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "auditSupplementaryRoutines", procedureName
		 * = "audit_supplementaryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "auditDocumentRoutines", procedureName =
		 * "audit_documentSectionRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "auditmeetingRoutines", procedureName =
		 * "audit_meetingRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * 
		 * FOR HIRE ACTION ROUTINES
		 * 
		 * @NamedStoredProcedureQuery(name = "hireActionRoutines", procedureName =
		 * "hrm_hireActionRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * FOR PROJECT MASTER ROUTINES
		 * 
		 * @NamedStoredProcedureQuery(name = "ProjectsRoutines", procedureName =
		 * "hrm_projectsRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * * for kitchen manager routines
		 * 
		 * @NamedStoredProcedureQuery(name = "kitchenApiRoutines", procedureName =
		 * "user_kitchen_api_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * * for kitchen staff routines
		 * 
		 * @NamedStoredProcedureQuery(name = "kitchenApiStaffRoutines", procedureName =
		 * "user_kitchen_staff_api_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * FOR CANDIDATE MASTER ROUTINES
		 * 
		 * @NamedStoredProcedureQuery(name = "candidateRoutines", procedureName =
		 * "hrm_candidateMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * * for menu item routines
		 * 
		 * @NamedStoredProcedureQuery(name = "menuItem", procedureName =
		 * "user_menu_api_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * * for restaurant manager api routines
		 * 
		 * @NamedStoredProcedureQuery(name = "restaurantManager", procedureName =
		 * "restaurant_manager_api_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * * for restaurant staff api routines
		 * 
		 * @NamedStoredProcedureQuery(name = "restaurantStaff", procedureName =
		 * "restaurant_staff_api_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "userRoutines", procedureName =
		 * "user_userRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user Type Procedure definition for user type
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "UserType", procedureName =
		 * "user_typeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user_prefixManagementRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "userPrefixManagementRoutines",
		 * procedureName = "user_prefixManagementRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * sacCodeRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "sacCodeRoutines", procedureName =
		 * "user_sacCodeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user_ManageRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "userManageRoutines", procedureName =
		 * "user_ManageRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * user_processRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "processRoutines", procedureName =
		 * "user_processRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "customermaster", procedureName =
		 * "customer_masterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "quotationmasterRotines", procedureName =
		 * "quotation_masterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "salesOrderNew", procedureName =
		 * "sales_ordernew_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "salesInvoiceNew", procedureName =
		 * "sales_InvoiceNew_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "salesInvoiceReturnNew", procedureName =
		 * "sales_InvoiceReturnNew_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user_guestDetailRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "guestRoutines", procedureName =
		 * "master_guestDetailRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * PropertyDashboardRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "PropertyDashboardRoutines", procedureName
		 * = "property_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * property_assignmentOfpropertyToStaff Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "assignPropertyToStaffRoutines",
		 * procedureName = "property_assignmentOfpropertyToStaff", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * property reservation Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propertyReservation", procedureName =
		 * "property_reservationRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * comparePropertyRoutines Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "comparePropertyRoutines", procedureName =
		 * "user_comparePropertyRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * user_stateRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "stateRoutines", procedureName =
		 * "user_stateRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * userroleRoutines Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "userroleRoutines", procedureName =
		 * "user_userroleRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user rating Stored Procedure definition for user set authority
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "UserSetAuthority", procedureName =
		 * "user_setauthorityRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user Authority dropdownRoutines Stored Procedure definition for user set
			 * authority drop down
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "userAuthoritydropdown", procedureName =
		 * "user_authoritydropdownRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * user ApprovalAction Stored Procedure definition for user set authority drop
			 * down
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "ApprovalAction", procedureName =
		 * "user_ApprovalAction", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user_districtRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "districtRoutines", procedureName =
		 * "user_districtRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * property category Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propertyCategory", procedureName =
		 * "property_categoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * // property_assignAssetToStaffRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "assignAssetToStaffRoutines", procedureName
		 * = "property_assignAssetToStaffRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "AssetMaintenanceRoutines", procedureName =
		 * "asset_maintenance_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "AssetPoAndInventory", procedureName =
		 * "asset_Po_And_Inventory_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * assignedConsumeItemsRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "assignedConsumeItemsRoutines",
		 * procedureName = "property_assignedConsumeItemsRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Property transaction routine Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "propertyTransactionRoutines",
		 * procedureName = "property_transactionRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * property master Stored Procedure definition for dropdown
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propertyMasterDropDown", procedureName =
		 * "property_masterDropdownRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * user rating Stored Procedure definition for payment mode
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "paymentModeRoutines", procedureName =
		 * "master_paymentModeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * user rating Stored Procedure definition for Account Group Master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "accountGroupRoutines", procedureName =
		 * "master_accGroupRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * propBookTypeRoutines Stored Procedure definition for dropdown
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propBookTypeRoutines", procedureName =
		 * "property_propBookTypeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * property master Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propertyMaster", procedureName =
		 * "property_masterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * property asset code Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "assetCode", procedureName =
		 * "property_assetcodeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * asset code drop down property Stored Procedure definition for drop down
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "getItemName", procedureName =
		 * "property_assetcodedropdownRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // mano
		 * 
		 * @NamedStoredProcedureQuery(name = "addPropertyType", procedureName =
		 * "property_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "getPropertyName", procedureName =
		 * "property_amenityRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery( name = "add_Amenity", procedureName =
		 * "propert_Amenity_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * @NamedStoredProcedureQuery(name = "add_Amenity", procedureName =
		 * "property_amenityRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * For Amenity Item
		 * 
		 * @NamedStoredProcedureQuery(name = "AmenityItem", procedureName =
		 * "property_amenityItemRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }), // mano
		 * 
		 *//**
			 * property Theme Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "propertyTheme", procedureName =
		 * "property_themeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * property_assignAssetRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "AssignAsset", procedureName =
		 * "property_assignAssetRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * sitting plan Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "SittingPlan", procedureName =
		 * "property_seatingPlanRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * For Assignment of Seating Plan
		 * 
		 * @NamedStoredProcedureQuery(name = "AssignmentOfSeatingPlan", procedureName =
		 * "property_assignmentOfSeatingPlan", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * property floor Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propertyFloorRoutines", procedureName =
		 * "property_floorRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * property booking Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propertyBooking", procedureName =
		 * "property_bookingRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * property booking Stored Procedure definition for dropdown
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "propertyBookingDropDown", procedureName =
		 * "property_bookingDropdownRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * property hotel Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "hotelRoutines", procedureName =
		 * "property_hotelRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * hotel drop down property Stored Procedure definition for drop down
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "hoteldropdownRoutines", procedureName =
		 * "property_hoteldropdownRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Maintenance Work order Dashboard routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "MaintenanceDashboardRoutines",
		 * procedureName = "maintenance_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * Maintenance Work order routine Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "maintenanceWorkOrderRoutines",
		 * procedureName = "maintenance_workOrderRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * maintenance_taskRoutines Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "maintenancetaskRoutines", procedureName =
		 * "maintenance_taskRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Maintenance_propertyTaskAssignRoutines Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "propertyTaskAssignRoutines", procedureName
		 * = "Maintenance_propertyTaskAssignRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * maintenance_AssignTaskToStaffRoutines Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "maintenanceAssignTaskToStaffRoutines",
		 * procedureName = "maintenance_AssignTaskToStaffRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * maintenance_MaidPropertyTaskAssignRoutines Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "maintenanceMaidPropertyTaskAssign",
		 * procedureName = "maintenance_MaidPropertyTaskAssign", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Request Quotation Routines for Select Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "reqSelectRFQRoutines", procedureName =
		 * "inventory_reqSelectRFQRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Request Quotation Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "requestQuotRoutines", procedureName =
		 * "inventory_requestQuotRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * RFQ Dlts Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "invRFQDtlsRoutines", procedureName =
		 * "inventory_rfqDtlstRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * RFQ PO Dlts Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "invPORFQDtlsRoutines", procedureName =
		 * "inventory_rfqPODtlstRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * inventoryItemCategoryRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "InventoryDashboardRoutines", procedureName
		 * = "inventory_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * inventory_inventoryStoreRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "inventoryStoreRoutines", procedureName =
		 * "inventory_inventoryStoreRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * inventoryItemCategoryRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "inventoryItemCategoryRoutines",
		 * procedureName = "inventory_inventoryItemCategoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * inventoryItemSubCategoryRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "itemSubCatRoutines", procedureName =
		 * "inventory_itemSubCatRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * inventoryItemRoutines Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "inventoryItemRoutines", procedureName =
		 * "inventory_inventoryItemRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * inventoryItemServeTypeRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "itemServeTypeRoutines", procedureName =
		 * "inventory_itemServeTypeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * inventoryCostCenterRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "manageCostcenter", procedureName =
		 * "manage_costcenter", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * inventoryItemRequisitionRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "inventoryItemRequisitionRoutines",
		 * procedureName = "inventory_inventoryItemRequisitionRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * inventoryVendorRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "inventoryVendorRoutines", procedureName =
		 * "inventory_inventoryVendorRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * inventoryPurchaseOrderRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "inventoryPurchaseOrderRoutines",
		 * procedureName = "inventory_inventoryPurchaseOrderRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * inventorygoodsReturnInvoiceRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "goodsReceiveInvoiceRoutines",
		 * procedureName = "inventory_goodsReceiveInvoiceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * Inventory goods return routine Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "inventoryGoodsReturnRoutines",
		 * procedureName = "inventory_goodsReturnRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * inventoryPurchaseOrderRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "inventoryIssueNoteRoutines", procedureName
		 * = "inventory_inventoryIssueNoteRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * Laundry Dashboard routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryDashboardRoutines", procedureName =
		 * "laundry_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * Laundry ItemCategory routine Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "laundryItemCategoryRoutines",
		 * procedureName = "laundry_itemCategoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * laundryHotelServiceRoutines routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryHotelServiceRoutines",
		 * procedureName = "laundry_hotelServiceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * laundryItemRoutines routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryItemRoutines", procedureName =
		 * "laundry_itemRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * laundryServiceRoutines routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryServiceRoutines", procedureName =
		 * "laundry_laundryServiceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * laundry item service routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryItemService", procedureName =
		 * "laundry_ItemServiceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * laundryServiceUserTypeRoutines routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryServiceUserTypeRoutines",
		 * procedureName = "laundry_userTypeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * laundryItemPriceRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryItemPriceRoutines", procedureName =
		 * "laundry_itemPriceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * laundryDropdownRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "laundryDropdownRoutines", procedureName =
		 * "laundry_dropdownRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * Laundry ReturnIN routine Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "laundryReturnInRoutines", procedureName =
		 * "laundry_returnInRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Restaurant RestaurantDashboardRoutines routine Stored Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "RestaurantDashboardRoutines",
		 * procedureName = "restaurant_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * Restaurant mealTypeRoutines routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "mealTypeRoutines", procedureName =
		 * "restaurant_mealTypeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * Restaurant restaurant_tableRoutines routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "tableRoutines", procedureName =
		 * "restaurant_tableRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * restaurant_menu itemRoutines Routines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "itemRoutines", procedureName =
		 * "restaurant_itemRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * restaurant_tablebookRoutinesRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "bookRoutines", procedureName =
		 * "restaurant_bookRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * restaurantDropdownRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "restaurantDropdownRoutines", procedureName
		 * = "restaurant_counter_dropdownRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * restaurantFoodOrderRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "restaurantFoodOrderRoutines",
		 * procedureName = "restaurant_counter_foodOrderRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * restaurantMenuItemPriceMasterRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "restaurantMenuItemPriceMasterRoutines",
		 * procedureName = "restaurant_menuItemPriceMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * restaurant_assignTableStaffRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "assignTableStaffRoutines", procedureName =
		 * "restaurant_assignTableStaffRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * restaurant_shiftRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "shiftRoutines", procedureName =
		 * "restaurant_shiftRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * restaurant_menuItemCategoryRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "menuItemCategoryRoutines", procedureName =
		 * "restaurant_menuItemCategoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * // master_acvtivityMasterRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "activityRoutines", procedureName =
		 * "master_acvtivityMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // master_functionMasterRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "functionRoutines", procedureName =
		 * "master_functionMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * // master_moduleMasterRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "moduleRoutines", procedureName =
		 * "master_moduleMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * housekeeping dashboard
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "HousekeepingDashboardRoutines",
		 * procedureName = "housekeeping_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * housekeeping_taskRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "taskRoutinesMaster", procedureName =
		 * "housekeeping_taskRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * housekeeping_taskRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "taskRoutines", procedureName =
		 * "housekeeping_propertyTaskRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * housekeeping_AssignStaffRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "AssignStaffRoutines", procedureName =
		 * "housekeeping_AssignStaffRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * housekeeping_MaidPropertyTask
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "MaidPropertyTask", procedureName =
		 * "housekeeping_MaidPropertyTask", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), //DashBoard
		 * 
		 * @NamedStoredProcedureQuery(name = "ProcurementDashboardRoutines",
		 * procedureName = "procurement_execute_Dashboard_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * housekeeping_maidTaskAssignRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "maidTaskAssignRoutines", procedureName =
		 * "housekeeping_maidTaskAssignRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * housekeeping_guestConsumeRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "guestConsumeRoutines", procedureName =
		 * "roomservice_guestConsumeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * assignKitchenToRestaurantRoutines
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "assignKitchenToRestaurantRoutines",
		 * procedureName = "kitchen_assignKitchenToRestaurantRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * kitchen_foodOrderListRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "foodOrderListRoutines", procedureName =
		 * "kitchen_foodOrderListRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * kitchen_kitchenManagerRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "kitchenManagerRoutines", procedureName =
		 * "kitchen_kitchenManagerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * gym_lockermasterRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "lockerRoutines", procedureName =
		 * "gym_lockermasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * gym_assign_lockerRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "assignLocker", procedureName =
		 * "gym_assign_lockerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * gym_transaction_Routines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "gymTransaction", procedureName =
		 * "gym_transaction_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * roomservice_roomServiceTask
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "roomServiceRoutines", procedureName =
		 * "roomservice_roomServiceTask", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * roomservice_RoomServiceTaskAssigned
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "roomTaskAssignRoutines", procedureName =
		 * "roomservice_RoomServiceTaskAssigned", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * other_service_masterRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "otherServiceRoutines", procedureName =
		 * "other_service_masterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * other_service_PriceRoutines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "otherServicePriceRoutines", procedureName
		 * = "other_service_PriceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * routine Stored Procedure memberMstrRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "memberMstrRoutines", procedureName =
		 * "user_memberMstrRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * routine Stored Procedure member registration
		 * 
		 * @NamedStoredProcedureQuery(name = "userMemRegRoutines", procedureName =
		 * "user_userMemRegRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * routine Stored Procedure parking_resrveParkingRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "resrveParkingRoutines", procedureName =
		 * "parking_resrveParkingRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * routine Stored Procedure parking_vehicleTypeRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "vehicleTypeRoutines", procedureName =
		 * "parking_vehicleTypeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * routine Stored Procedure parking_parkingCapacityRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "parkingCapacityRoutines", procedureName =
		 * "parking_parkingCapacityRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * routine Stored Procedure parking_entryVehicleRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "entryVehicleRoutines", procedureName =
		 * "parking_entryVehicleRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * routine Stored Procedure parking_dashboardRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "dashboardRoutines", procedureName =
		 * "parking_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * routine Stored Procedure sales_salesCustomerRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "salesCustomerRoutines", procedureName =
		 * "sales_salesCustomerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * routine Stored Procedure sales_quotationRotines
		 * 
		 * @NamedStoredProcedureQuery(name = "quotationRotines", procedureName =
		 * "sales_quotationRotines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * routine Stored Procedure sales_salesInvoiceRoutiness
		 * 
		 * @NamedStoredProcedureQuery(name = "salesInvoiceRoutiness", procedureName =
		 * "sales_salesInvoiceRoutiness", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * master_masterCountryRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "masterCountryRoutines", procedureName =
		 * "master_masterCountryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * routine Stored Procedure sales_saleDashboardone
		 * 
		 * @NamedStoredProcedureQuery(name = "salesDashboardoneRoutiness", procedureName
		 * = "sale_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * routine Stored Procedure account_bankRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "AccountBankRoutines", procedureName =
		 * "account_bankRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * routine Stored Procedure account_bankBranchRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "AccountBankBranchRoutines", procedureName
		 * = "account_bankBranchRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * routine Stored Procedure account_bankAccountRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "AccountBankAccountRoutines", procedureName
		 * = "account_bankAccountRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * routine Stored Procedure account_Account LedgerRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "accountLedgerRoutines", procedureName =
		 * "account_accountLedgerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * routine Stored Procedure logo
		 * 
		 * @NamedStoredProcedureQuery(name = "logoImageRoutines", procedureName =
		 * "logo_logoImageRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for inventory purchase order
		 * 
		 * @NamedStoredProcedureQuery(name = "purchasVoucher", procedureName =
		 * "inventory_purchase_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for inventory purchase order
		 * 
		 * @NamedStoredProcedureQuery(name = "journalVoucher", procedureName =
		 * "account_journal_voucher_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for inventory account head type
		 * 
		 * @NamedStoredProcedureQuery(name = "accountHead", procedureName =
		 * "account_head_type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }), // for inventory purchase order
		 * 
		 * @NamedStoredProcedureQuery(name = "paymentVoucher", procedureName =
		 * "inventory_payment_voucher_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "salesInvReturnRoutines", procedureName =
		 * "sales_salesInvReturnRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "creditNoteRoutines", procedureName =
		 * "sales_creditNoteRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * inventory_debitNoteRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "debitNoteRoutines", procedureName =
		 * "inventory_debitNoteRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "contraVoucherRoutines", procedureName =
		 * "account_contraVoucherRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * routine Stored Procedure account_Credit LedgerRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "creditLedgerRoutines", procedureName =
		 * "account_creditLedgerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * routine Stored Procedure account_Debit LedgerRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "debitLedgerRoutines", procedureName =
		 * "account_debitLedgerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * for trial balance
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "trialBalanceRoutines", procedureName =
		 * "account_trial_balance_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for cash book
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "cashBookRoutines", procedureName =
		 * "account_cash_book_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * routine Stored Procedure Purchase Ledger Routines
		 * 
		 * @NamedStoredProcedureQuery(name = "purchaseRoutines", procedureName =
		 * "account_purchaseLedgerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * routine Stored Procedure Purchase Ledger Routines
		 * 
		 * @NamedStoredProcedureQuery(name = "incomeTaxRoutines", procedureName =
		 * "account_incomeTaxPayableRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * routine Stored Procedure Sales Ledger Routines
		 * 
		 * @NamedStoredProcedureQuery(name = "salesRoutines", procedureName =
		 * "account_salesLedgerRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "tableStatus", procedureName =
		 * "restaurant_tableStatusRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * for employment master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EmploymentMaster", procedureName =
		 * "hrm_employment_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for job title master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "JobTypeMaster", procedureName =
		 * "hrm_jobType_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for department master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "departmentMaster", procedureName =
		 * "hrm_department_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for skill master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "skillMaster", procedureName =
		 * "hrm_skill_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for qualification master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "qualificationMaster", procedureName =
		 * "hrm_qualification_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for certification master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "certificationMaster", procedureName =
		 * "hrm_certification_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for language master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "languageMaster", procedureName =
		 * "hrm_language_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Employee
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "Employee", procedureName =
		 * "hrm_employee_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Employee Skill assign
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EmployeeSkillAssign", procedureName =
		 * "hrm_employee_skill_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Employee Education assign
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EmployeeEducationAssign", procedureName =
		 * "hrm_employee_education_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Employee Education assign getAssignCertDetails
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EmployeecertificationAssign",
		 * procedureName = "hrm_employee_certification_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Employee Education assign getAssignCertDetails
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EmployeeLanguageAssign", procedureName =
		 * "hrm_employee_language_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Supervisor master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "supervisorMaster", procedureName =
		 * "hrm_supervisor_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for employee dependent
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EmployeedependentAssign", procedureName =
		 * "hrm_employee_dependent_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for emergency
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "emergencyMaster", procedureName =
		 * "hrm_emergency_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "attendenceRoutines", procedureName =
		 * "hrms_AttendenceDetailsRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "holidayMasterRoutines", procedureName =
		 * "hrms_holidayMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "workWeekMasterRoutines", procedureName =
		 * "hrms_workWeekMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * All Leave Applied by employee Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveApplyAdminRoutines", procedureName =
		 * "hrms_leaveApplyAdminRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Apply Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveApplyRoutines", procedureName =
		 * "hrms_leaveApplyRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Approved Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveApprovedRoutines", procedureName =
		 * "hrms_leaveApprovedRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Cancelled Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveCancelledRoutines", procedureName =
		 * "hrms_leaveCancelledRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Entitle Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveEntitleRoutines", procedureName =
		 * "hrms_leaveEntitleRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Pending Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leavePendingRoutines", procedureName =
		 * "hrms_leavePendingRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Period Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leavePeriodRoutines", procedureName =
		 * "hrms_leavePeriodRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Rejected Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveRejectedRoutines", procedureName =
		 * "hrms_leaveRejectedRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Rule Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveRuleRoutines", procedureName =
		 * "hrms_leaveRuleRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * Leave Rule Routines Stored Procedure definition hrm_Reimbursement_Routines
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "reimbursementRoutines", procedureName =
		 * "hrm_Reimbursement_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Type Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "leaveTypeRoutines", procedureName =
		 * "hrms_leaveTypeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * for REFERENCE__HR__ JobType
			 * 
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "HrReference", procedureName =
		 * "hr_Job_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrWorkHours", procedureName =
		 * "hr_Work_Hours_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrEducation", procedureName =
		 * "hr_education_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrJobBand", procedureName =
		 * "hr_JobBand_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrBenefit", procedureName =
		 * "hr_Benefit_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrAddress", procedureName =
		 * "hr_Address_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrBloodGroup", procedureName =
		 * "hr_BloodGroup_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrMarital", procedureName =
		 * "hr_Marital_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrDocument", procedureName =
		 * "hr_Document_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrmTimeSheet", procedureName =
		 * "hrm_TimeSheet_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrmEmpStatus", procedureName =
		 * "hrm_EmpStatus_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrmProjectType", procedureName =
		 * "hrm_Project_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "priorityMaster", procedureName =
		 * "hrm_Priority_Master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "GenderMaster", procedureName =
		 * "hrm_Gender_Master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "DepRelationshipMaster", procedureName =
		 * "hrm_Emp_Dependent_Relationship_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "dependentMaster", procedureName =
		 * "hrm_Emp_Dependent_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "insuranceMaster", procedureName =
		 * "hrm_Insurance_Company_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * Leave Type Routines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "reimbursementTypeMaster", procedureName =
		 * "hrm_reimbursement_type_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Department Job Title Assign
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "JobTitleAssign", procedureName =
		 * "hrm_department_jobtitle_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * * for traveling requisition
		 * 
		 * @NamedStoredProcedureQuery(name = "TravelingRequisitionMaster", procedureName
		 * = "hrm_traveling_requisition_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * * for reimbursement payment
		 * 
		 * @NamedStoredProcedureQuery(name = "reimbursementPaymentRoutines",
		 * procedureName = "hrm_reimbursement_payment_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * * for reimbursement payment
		 * 
		 * @NamedStoredProcedureQuery(name = "policyTypeMaster", procedureName =
		 * "hrm_policy_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * * for BeautyDashboardRoutines sonam 0901
		 * 
		 * @NamedStoredProcedureQuery(name = "BeautyDashboardRoutines", procedureName =
		 * "beautyparlour_BeautyParlourDashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * * for FrontdeskDashboardRoutines sonam 0901
		 * 
		 * @NamedStoredProcedureQuery(name = "FrontdeskDashboardRoutines", procedureName
		 * = "frontdesk_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for GymDashboardRoutines sonam 0901
		 * 
		 * @NamedStoredProcedureQuery(name = "GymDashboardRoutines", procedureName =
		 * "gym_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for KitchenDashboardRoutines sonam 0901
		 * 
		 * @NamedStoredProcedureQuery(name = "KitchenDashboardRoutines", procedureName =
		 * "kitchen_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for laundryDashboardRoutines sonam 0901
		 * 
		 * 
		 * 
		 * * for NightClubDashboardRoutines sonam 0901
		 * 
		 * @NamedStoredProcedureQuery(name = "NightClubDashboardRoutines", procedureName
		 * = "nightclub_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for NightClubDashboardRoutines sonam 0901
		 * 
		 * @NamedStoredProcedureQuery(name = "RestaurantStaffDashboardRoutines",
		 * procedureName = "restaurant_staffDashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * for spa Dashboard Routines sonam 0901
		 * 
		 * 
		 * @NamedStoredProcedureQuery(name = "SpaDashboardRoutines", procedureName =
		 * "spa_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * for superadmin Dashboard Routines sonam 0901
		 * 
		 * 
		 * @NamedStoredProcedureQuery(name = "userDashboardRoutines", procedureName =
		 * "user_dashboardRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * for master_serviceRoutines Routines sagar 13012020
		 * 
		 * 
		 * 
		 * @NamedStoredProcedureQuery(name = "serviceRoutines", procedureName =
		 * "master_serviceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * for restaurant_menuCourseRoutines Routines sagar 13012020
		 * 
		 * 
		 * @NamedStoredProcedureQuery(name = "menuCourseRoutines", procedureName =
		 * "restaurant_menuCourseRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * sub group name Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "SubGroupName", procedureName =
		 * "SubGroupName_procedure", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * Store master routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "storeMasterRoutines", procedureName =
		 * "master_storeMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * Godown master routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "godownMasterRoutines", procedureName =
		 * "master_godownMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * employee routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "KRAMeasureRoutine", procedureName =
		 * "hrm_employee_KRAMeasureRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * for Employee Goal Master
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "GoalMaster", procedureName =
		 * "hrm_goal_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * for Employee Appraisal Details
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EmployeeAppraisalDetails", procedureName =
		 * "hrm_employee_appraisal_review_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "gradeSalarynewRoutines", procedureName =
		 * "grade_salary_new_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * Appraisal Form routine Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "AppraisalFormApproval", procedureName =
		 * "hrm_appraisalFormApproval_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "documentRoutines", procedureName =
		 * "document_DocumentRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "masterCategoryRoutines", procedureName =
		 * "master_masterCategoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "organizationCategoryRoutines",
		 * procedureName = "master_organizationCategoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * view ticket Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "invjournalvoucherPaymentRoutines",
		 * procedureName = "account_journal_voucher_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "inventoryStockTransferRoutines",
		 * procedureName = "inventory_inventoryStockTransferRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "saleDelChallanRoutines", procedureName =
		 * "sales_saleDel_Maharaja_ChallanRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * inventoryDamagedItemRoutines Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "damagedItemRoutines", procedureName =
		 * "inventory_inventoryDamagedItemRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "entryRoutines", procedureName =
		 * "gatepass_entryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "assignAssetVehicleRoutines", procedureName
		 * = "asset_assignAssetVehicleRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * for delivery challan
		 * 
		 * @NamedStoredProcedureQuery(name = "DeliverChallanRoutines", procedureName =
		 * "sales_delivery_challan_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "sandQualityControl", procedureName =
		 * "quality_control_sand_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * quality Production Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "QualityProduction", procedureName =
		 * "quality_control_production_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "assignVehicleDriverRoutines",
		 * procedureName = "asset_assignVehicleDriverRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "fuelConsumption", procedureName =
		 * "asset_fuel_consumption_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "assetPolicy", procedureName =
		 * "asset_policy_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "outRoutines", procedureName =
		 * "gatepass_outRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "productionPlanningRoutines", procedureName
		 * = "Production_Planning_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "productionPlanningTempRoutines",
		 * procedureName = "production_planningTempRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "productionGrade", procedureName =
		 * "production_GradeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * Mother Coil Slit Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "motherCoilRoutines", procedureName =
		 * "production_motherCoil_Routine", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * Mother Coil Slit Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "pipeProductionRoutines", procedureName =
		 * "production_pipeProduction_Routine", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * Mother Coil Slit Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "pipePolishingRoutines", procedureName =
		 * "production_pipePolishing_Routine", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * pipe packaging Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "pipePackagingRoutines", procedureName =
		 * "production_pipePackaging_Routine", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * pipe Scrap Procedure definition
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "pipeScrapRoutines", procedureName =
		 * "production_pipeScrap_Routine", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "gatePassEntryRoutines", procedureName =
		 * "gatepass_gatePassEntryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "gatePassOutRoutines", procedureName =
		 * "gatepass_gatePassOutRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "batchCodeRoutines", procedureName =
		 * "inventory_batchCodeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "assignSlitWidth", procedureName =
		 * "production_assignSlitWidth", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "menuItemStockRoutines", procedureName =
		 * "restaurant_menuItemStockRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * // sale order add
		 * 
		 * @NamedStoredProcedureQuery(name = "SaleOrderRotines", procedureName =
		 * "sales_sale_order_Rotines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }), // sale order add
		 * 
		 * @NamedStoredProcedureQuery(name = "salesCounterInvoiceRoutines",
		 * procedureName = "sales_salesCounterInvoiceRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }), // for customer item assign
		 * 
		 * @NamedStoredProcedureQuery(name = "Customer_Item_Rutines", procedureName =
		 * "inventory_Customer_Item_Rutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * // for assign stock price
		 * 
		 * @NamedStoredProcedureQuery(name = "AssignStockPriceRutines", procedureName =
		 * "inventory_Assign_StockPrice_Rutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * // for assign stock price
		 * 
		 * @NamedStoredProcedureQuery(name = "vendor_Item_asgn_Rutines", procedureName =
		 * "inventory_vendor_Item_asgn_Rutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for multiple grn
		 * 
		 * @NamedStoredProcedureQuery(name = "multipleGRNRoutines", procedureName =
		 * "inventory_multipleGRNRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for gocool production plan
		 * 
		 * @NamedStoredProcedureQuery(name = "gocool-prod-planning", procedureName =
		 * "Production_Prod_Gocool_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * // for gocool production add mix
		 * 
		 * @NamedStoredProcedureQuery(name = "gocool-prod-add-mix", procedureName =
		 * "Production_Gocool_Mix_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for gocool production add packaging
		 * 
		 * @NamedStoredProcedureQuery(name = "gocool-prod-packaging", procedureName =
		 * "Production_Prod_Pack_Gocool_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for gocool production
		 * 
		 * @NamedStoredProcedureQuery(name = "gocool_production", procedureName =
		 * "Production_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ), // for batch details
		 * 
		 * @NamedStoredProcedureQuery(name = "ProductItemRutines", procedureName =
		 * "inventory_Product_Item_Rutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//** SUB-INVENTORY **/
		/*
		 * @NamedStoredProcedureQuery(name = "subInventoryRoutines", procedureName =
		 * "inventory_subInventoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//** WAREHOUSE **/
		/*
		 * @NamedStoredProcedureQuery(name = "warehouseRoutines", procedureName =
		 * "inventory_warehouseRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//** WAREHOUSE **/
		/*
		 * @NamedStoredProcedureQuery(name = "rackShelvesRoutines", procedureName =
		 * "inventory_rackShelvesRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }), // for batch details
		 * 
		 * @NamedStoredProcedureQuery(name = "production_return", procedureName =
		 * "Production_Return_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * view advance payment Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "advancePaymentMasterRoutines",
		 * procedureName = "hrm_employee_advance_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "foodTracking", procedureName =
		 * "hrms_foodTrackingRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * for employee income tax
		 * 
		 * @NamedStoredProcedureQuery(name = "emplyeeIncomeTaxRoutines", procedureName =
		 * "hrms_income_tax_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 *//**
			 * trip bonous Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "tripBonousRoutines", procedureName =
		 * "hrm_employee_trip_bonous_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * trip bonous Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "dailyExcelUpload", procedureName =
		 * "hrm_daily_excel_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "EmployeePayRoll", procedureName =
		 * "hrm_employeePayRoll_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "gradeSalaryRoutines", procedureName =
		 * "hrm_grade_salary_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "salaryComponentsMstrRoutines",
		 * procedureName = "hrm_salary_component_master_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "offerLetterRoutines", procedureName =
		 * "hrm_offerLetterDetails_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * for salary details
		 * 
		 * @NamedStoredProcedureQuery(name = "salary_routines", procedureName =
		 * "hrms_salary_details", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * for leave details
		 * 
		 * // hrms_leave_details
		 * 
		 * @NamedStoredProcedureQuery(name = "leave_routines", procedureName =
		 * "hrm_employee_advance_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "jobCardRoutines", procedureName =
		 * "asset_add_job_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "employeeLeaveRoutine", procedureName =
		 * "employee_leave_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * for organization structure
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "orgStructure", procedureName =
		 * "hrms_org_structure_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//** GRADE MASTER **/
		/*
		 * @NamedStoredProcedureQuery(name = "masterGradeRoutines", procedureName =
		 * "master_masterGradeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//**
			 * for performance Goal
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "OrganizationRoutine", procedureName =
		 * "hrm_organization_Routine", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for recruitment routines
		 * 
		 * @NamedStoredProcedureQuery(name = "recruitment", procedureName =
		 * "hrms_jobtitle_routienes", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for requistionRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "requistionRoutines", procedureName =
		 * "hrm_RequistionRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for specficTypeRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "specficTypeRoutines", procedureName =
		 * "hrms_specficTypeRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for questionTypeRoutines
		 * 
		 * @NamedStoredProcedureQuery(name = "exitmanagement", procedureName =
		 * "exitManagement_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "exitInitiate", procedureName =
		 * "exitInitiate_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "exitFinance", procedureName =
		 * "exitFinance_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * 
		 * Resource Routines
		 * 
		 * @NamedStoredProcedureQuery(name = "resourceRoutines", procedureName =
		 * "recruitment_resource_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 *//** for shift management master **/
		/*
		 * @NamedStoredProcedureQuery(name = "rshiftRoutines", procedureName =
		 * "hrm_ShiftRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for trainingCreation routines
		 * 
		 * @NamedStoredProcedureQuery(name = "trainingCreation", procedureName =
		 * "hrms_Training_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * * for trainingCreation routines
		 * 
		 * @NamedStoredProcedureQuery(name = "gradeRevisionRoutines", procedureName =
		 * "grade_Revision_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * * for trainingCreation routines
		 * 
		 * @NamedStoredProcedureQuery(name = "workSheetPlanningRoutines", procedureName
		 * = "work_sheet_planning_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "paymentTerms", procedureName =
		 * "account_paymentTermsRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * finance Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "financeRoutines", procedureName =
		 * "finance_mstr_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 *//**
			 * tds Stored Procedure definition
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "tdsRoutines", procedureName =
		 * "tds_mstr_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "payableRoutines", procedureName =
		 * "account_payableRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "reOrderItemRoutines", procedureName =
		 * "inventory_reOrderItemRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * for Question master routines
		 * 
		 * @NamedStoredProcedureQuery(name = "questionRoutines", procedureName =
		 * "hrms_questionRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * for shift scheduling routines
		 * 
		 * @NamedStoredProcedureQuery(name = "addTrainingRoutines", procedureName =
		 * "hrm_TrainingPlanningRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "physicalVerification", procedureName =
		 * "physical_verification_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * for shift scheduling routines
		 * 
		 * @NamedStoredProcedureQuery(name = "shiftScheduleRoutines", procedureName =
		 * "hrms_shiftScheduleRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "assetMaintenanceHistory", procedureName =
		 * "asset_assetMaintenanceHistory", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "deliveryNoteRoutines", procedureName =
		 * "inventory_deliveryNoteRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "locationMasterRoutines", procedureName =
		 * "master_locationMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "productCategoryRoutines", procedureName =
		 * "master_productCategoryRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "productMasterRoutines", procedureName =
		 * "master_productMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "mailConfigRoutines", procedureName =
		 * "master_mailConfigRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "budgetPlanRoutines", procedureName =
		 * "master_budgetPlanRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "templatesRoutines", procedureName =
		 * "master_templatesRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "userAuthorityRoutines", procedureName =
		 * "user_userAuthorityRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }),
		 * 
		 * @NamedStoredProcedureQuery(name = "warehouseRoutine", procedureName =
		 * "warehouse_new_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "inventoryStockRoutines", procedureName =
		 * "inventory_stock_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * for inventory requisition
		 * 
		 * @NamedStoredProcedureQuery(name = "inventoryRequisitionRoutines",
		 * procedureName = "inventory_Requisition_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * for vendor master
		 * 
		 * @NamedStoredProcedureQuery(name = "vendorMasterRoutines", procedureName =
		 * "master_vendorMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * for manage employee
		 * 
		 * @NamedStoredProcedureQuery(name = "employeeMasterRoutines", procedureName =
		 * "employee_mst_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "GlobalMasterRoutines", procedureName =
		 * "master_globalMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * for rfq
		 * 
		 * @NamedStoredProcedureQuery(name = "RfqRoutines", procedureName =
		 * "inventory_rfq_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * po
		 * 
		 * @NamedStoredProcedureQuery(name = "poRoutines", procedureName =
		 * "inventory_po_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * action rfq model
		 * 
		 * @NamedStoredProcedureQuery(name = "ActionRfqRoutines", procedureName =
		 * "inventory_action_rfq_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * for REFERENCE__Procurement
			 * 
			 */
		/*
		 * 
		 * @NamedStoredProcedureQuery(name = "MeasurementTypeReference", procedureName =
		 * "procure_Measure_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "RequisitionTypeReference", procedureName =
		 * "procure_Requisition_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "RequisitionPrioTypeReference",
		 * procedureName = "procure_Requisition_Prio_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "PaymentTermReference", procedureName =
		 * "procure_Payment_Term_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "LegalTermReference", procedureName =
		 * "procure_Legal_Term_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "PaymentStatusReference", procedureName =
		 * "procure_Payment_Status_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "DeliveryMethodReference", procedureName =
		 * "procure_Delivery_Method_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * for REFERENCE__Product
			 * 
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "BrandTypeReference", procedureName =
		 * "product_Brand_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "ProductTypeReference", procedureName =
		 * "product_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "VariationTypeReference", procedureName =
		 * "variation_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * for REFERENCE__Entity
			 * 
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "EntityCostNature", procedureName =
		 * "entity_Cost_Nature_Type_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "EntityCostLabour", procedureName =
		 * "entity_cost_labour_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "EntityVendorCategory", procedureName =
		 * "entity_vendor_category_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "EntityLocationType", procedureName =
		 * "entity_location_type_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "EntityRoomType", procedureName =
		 * "entity_room_type_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "EntityVendorType", procedureName =
		 * "entity_vendor_type_routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * for cost center master
		 * 
		 * @NamedStoredProcedureQuery(name = "costCenterRoutines", procedureName =
		 * "master_costcenterMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * for chart of account master
		 * 
		 * @NamedStoredProcedureQuery(name = "chartOfAcRoutines", procedureName =
		 * "master_chartofaccMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * Vendor rfq model
		 * 
		 * @NamedStoredProcedureQuery(name = "VendorRfqRoutines", procedureName =
		 * "inventory_vendor_rfq_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * for account mapping
		 * 
		 * @NamedStoredProcedureQuery(name = "accountMappingRoutines", procedureName =
		 * "master_accmappingMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * action invoice
		 * 
		 * @NamedStoredProcedureQuery(name = "action_invoice_Routines", procedureName =
		 * "inventory_action_invoice_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * action grn
		 * 
		 * @NamedStoredProcedureQuery(name = "execute_grn_Routines", procedureName =
		 * "inventory_execute_grn_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * action grn
		 * 
		 * @NamedStoredProcedureQuery(name = "execute_grn_return_Routines",
		 * procedureName = "inventory_execute_grn_return_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * for account mapping
		 * 
		 * @NamedStoredProcedureQuery(name = "masterTimeSheetRoutines", procedureName =
		 * "master_timeSheetMasterRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "vendor_invoice_Routines", procedureName =
		 * "inventory_vendor_invoice_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * @NamedStoredProcedureQuery(name = "HrShift", procedureName =
		 * "hr_Shift_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 * 
		 * for ticket mapping
		 * 
		 * 
		 * @NamedStoredProcedureQuery(name = "ticketRoutines", procedureName =
		 * "callAgent_ticketRoutines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) } ),
		 * 
		 * @NamedStoredProcedureQuery(name = "manageSupervisorRoutines", procedureName =
		 * "viewSupervisorTicket_procedure", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) } ),
		 * 
		 * 
		 * reimbursement
		 * 
		 * 
		 * @NamedStoredProcedureQuery(name = "reimbursementRoutine", procedureName =
		 * "hrms_Reimbursement_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * for vendor grn return
			 */
		/*
		 * @NamedStoredProcedureQuery(name = "vendor_grn_return_Routines", procedureName
		 * = "inventory_vendor_grn_return_Routines", parameters = {
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
		 * = String.class),
		 * 
		 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
		 * = String.class) }
		 * 
		 * ),
		 * 
		 *//**
			 * for Notice Period
			 *//*
				 * @NamedStoredProcedureQuery(name = "noticetpmstr", procedureName =
				 * "notice_type_master_Routines", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }
				 * 
				 * ),
				 * 
				 * @NamedStoredProcedureQuery(name = "initiateNotice", procedureName =
				 * "notice_initiate_Routine", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }
				 * 
				 * ),
				 * 
				 * @NamedStoredProcedureQuery(name = "AssetCode", procedureName =
				 * "asset_Code_Routies", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }),
				 * 
				 * @NamedStoredProcedureQuery(name = "assetRoutines", procedureName =
				 * "master_asset_classification", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }),
				 * 
				 * @NamedStoredProcedureQuery(name = "assetPolicyRoutines", procedureName =
				 * "asset_Maintainance_Policy_Routines", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }),
				 * 
				 * @NamedStoredProcedureQuery(name = "questionTypeRoutines", procedureName =
				 * "hrms_questionTypeRoutines", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }),
				 * 
				 * @NamedStoredProcedureQuery(name = "customerDashBoard", procedureName =
				 * "customer_dashboardRoutines", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }),
				 * 
				 * @NamedStoredProcedureQuery(name = "wereHouseDashBoard", procedureName =
				 * "wereHouse_dashboardRoutines", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }),
				 * 
				 * @NamedStoredProcedureQuery(name = "tickettDashBoard", procedureName =
				 * "ticket_dashboardRotunies", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) }),
				 * 
				 * @NamedStoredProcedureQuery(name = "projectDashBoard", procedureName =
				 * "hrm_project_dashboardRotunies", parameters = {
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionType", type
				 * = String.class),
				 * 
				 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "actionValue", type
				 * = String.class) })
				 */

})

/**
 * @author NirmalyaLabs
 *
 */
public class BaseEntityClass {

	@Id
	private Integer pKey;

	public BaseEntityClass() {
		super();
	}

	public Integer getpKey() {
		return pKey;
	}

	public void setpKey(Integer pKey) {
		this.pKey = pKey;
	}

	/**
	 * Overrides toString method for converting class to string and back
	 **/
	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}
}
