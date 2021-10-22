package nirmalya.aatithya.restmodule.lab.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateLabProfileParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorProfileModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabDocumentModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabProfileModel;

@Repository
public class RestLabProfileDao {
	Logger logger = LoggerFactory.getLogger(RestLabProfileDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * For Edit Doctor Profile
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestLabProfileModel> editlabProfileDao(String id) {
		logger.info("Method : editlabProfile Dao starts");

		RestLabProfileModel req = new RestLabProfileModel();
		JsonResponse<RestLabProfileModel> resp = new JsonResponse<RestLabProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_profile").setParameter("doctOrId", id)
					.getResultList();
			/*
			 * Object doctorId,Object doctorName, Object birthDate, Object gender, Object
			 * education, Object speciality, Object organisation
			 */
			for (Object[] m : x) {
				RestLabProfileModel reqEdit = new RestLabProfileModel(null, m[0], m[1], m[2], m[3], m[4], m[5],m[6],m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : editlabProfile Dao ends");

		return resp;
	}

	/*
	 * For Edit Doctor Identification
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestLabProfileModel> editlabIdentificationDao(String id) {
		logger.info("Method : editlabIdentification Dao starts");

		RestLabProfileModel req = new RestLabProfileModel();
		JsonResponse<RestLabProfileModel> resp = new JsonResponse<RestLabProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_identification")
					.setParameter("doctOrId", id).getResultList();
			/*
			 * ima_no,pancard_no,passport_no,adharcard_no,votercard_no,license_no,
			 * license_authority
			 */

			for (Object[] m : x) {
				RestLabProfileModel reqEdit = new RestLabProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editlabIdentification Dao ends");

		return resp;
	}

	/*
	 * For Edit Lab Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestLabProfileModel> editlabHomeAdressDao(String id) {
		logger.info("Method : editlabHomeAdress Dao starts");

		RestLabProfileModel req = new RestLabProfileModel();
		JsonResponse<RestLabProfileModel> resp = new JsonResponse<RestLabProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestLabProfileModel reqEdit = new RestLabProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editlabHomeAdress Dao ends");

		return resp;
	}

	/*
	 * For Edit Lab Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestLabProfileModel> viewlabHomeAdressDao(String id) {
		logger.info("Method : viewlabHomeAdress Dao starts");

		RestLabProfileModel req = new RestLabProfileModel();
		JsonResponse<RestLabProfileModel> resp = new JsonResponse<RestLabProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestLabProfileModel reqEdit = new RestLabProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewlabHomeAdress Dao ends");

		return resp;
	}

	/*
	 * Add Doctor profile
	 */
	public JsonResponse<Object> addLabProfileDao(RestLabProfileModel RestLabProfileModel) {
		logger.info("Method : addLabProfile Dao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestLabProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestLabProfileModel.getDoctorName())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_profile_modify")
						.setParameter("p_doctorId", RestLabProfileModel.getDoctorId())
						.setParameter("p_education", RestLabProfileModel.getEducation())
						.setParameter("p_proimg", RestLabProfileModel.getProfileImg())
						.setParameter("p_dobid", RestLabProfileModel.getBirthDate())
						.execute();
			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println(err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : addLabProfile Dao ends");
		return resp;
	}

	/*
	 * Add Doctor Identification
	 */
	public JsonResponse<Object> addLabIdentificationDao(RestLabProfileModel RestLabProfileModel) {
		logger.info("Method : addLabIdentification Dao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestLabProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_identification_add")
						.setParameter("p_imaNo", RestLabProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestLabProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestLabProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestLabProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestLabProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestLabProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestLabProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestLabProfileModel.getDigitalSign())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_identification_modify")
						.setParameter("p_doctorId", RestLabProfileModel.getDoctorId())
						.setParameter("p_imaNo", RestLabProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestLabProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestLabProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestLabProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestLabProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestLabProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestLabProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestLabProfileModel.getDigitalSign())

						.execute();
			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println(err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : addLabIdentification Dao ends");
		return resp;
	}

	/*
	 * Add LAB Contact Details
	 */
	public JsonResponse<Object> addLabContactDetailsDao(RestLabProfileModel RestLabProfileModel) {
		logger.info("Method : addLabContactDetails Dao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (RestLabProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestLabProfileModel.getDoctorName())

						.execute();
			} else {

				em.createNamedStoredProcedureQuery("doctor_contact_details_modify")
						.setParameter("p_doctorId", RestLabProfileModel.getDoctorId())
						.setParameter("p_country", RestLabProfileModel.getCountry())
						.setParameter("p_state", RestLabProfileModel.getState())
						.setParameter("p_district", RestLabProfileModel.getDistrict())
						.setParameter("p_city", RestLabProfileModel.getCity())
						.setParameter("p_address", RestLabProfileModel.getAddress())
						.setParameter("p_zipCode", RestLabProfileModel.getZipCode())
						.setParameter("p_mobile", RestLabProfileModel.getMobile())
						.setParameter("p_office", RestLabProfileModel.getOffice())
						.setParameter("p_email", RestLabProfileModel.getEmail())

						.execute();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println(err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : addLabContactDetails Dao ends");
		return resp;
	}

	// Country list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCountryListslabDao() {
		logger.info("Method : getCountryLists Dao starts");

		List<DropDownModel> getCountryList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_country_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2]);
				getCountryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCountryLists Dao ends");
		return getCountryList;
	}

	// State list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getStateListlabDao(Integer id) {

		logger.info("Method : getStateListlab Dao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_state_list").setParameter("country", id)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getStateListlab Dao ends");
		return resp;
	}

	// District list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getDistListlabDao(String state) {

		logger.info("Method : getDistListlab Dao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_district_list").setParameter("stateId", state)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDistListlab Dao ends");
		return resp;
	}
	// City list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCityListlabDao(String dist) {

		logger.info("Method : getCityListlab Dao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_city_list").setParameter("districtId", dist)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCityListlab Dao ends");
		return resp;
	}

	
	/*
	 * DAO Function to Add Document Upload
	 *
	 */

	@SuppressWarnings({ "unused" })
	public ResponseEntity<JsonResponse<List<RestLabProfileModel>>> restadddrDocumentUploadLabDao(
			List<RestLabProfileModel> RestLabProfileModel) {
		logger.info("Method : restadddrDocumentUpload Lab Dao starts");
		boolean validation = true;
		RestLabProfileModel req = new RestLabProfileModel();
		req = RestLabProfileModel.get(0);
		JsonResponse<List<RestLabProfileModel>> resp = new JsonResponse<List<RestLabProfileModel>>();

		List<RestLabProfileModel> listData = new ArrayList<RestLabProfileModel>();
		List<RestLabProfileModel> docList = new ArrayList<RestLabProfileModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";

		if (validation) {

			try {

				String value = GenerateLabProfileParameter.getdrDataUpload(req);
				if (RestLabProfileModel.get(0).getDoctorId() != null) {


					em.createNamedStoredProcedureQuery("doctor_profile_document_upload_add")
							.setParameter("p_documentupload", value).execute();

				}

			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestLabProfileModel>>> response = new ResponseEntity<JsonResponse<List<RestLabProfileModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restadddrDocumentUpload Lab Dao ends");
		return response;

	}

	/*
	 * View data for Document Attachment
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLabProfileModel>> viewDocumentAttachmentlabDao(String id, String rolid) {
		logger.info("Method : viewDocumentAttachment lab Dao Starts");

		List<RestLabProfileModel> getAllProfDetails = new ArrayList<RestLabProfileModel>();
		JsonResponse<List<RestLabProfileModel>> resp = new JsonResponse<List<RestLabProfileModel>>();

		try {
			Integer drid1 = null;
			if (id != null) {
				drid1 = new Integer(id);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_details").setParameter("drid", drid1)
					.setParameter("roleid", rolid).getResultList();

			for (Object[] m : x) {

				RestLabProfileModel reqemp = new RestLabProfileModel(m[0], m[1], m[2]);
				getAllProfDetails.add(reqemp);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (RestLabProfileModel m : getAllProfDetails) {

			List<RestLabDocumentModel> profDoc = new ArrayList<RestLabDocumentModel>();
			Integer dr_idDoc = m.getDoctorId();
			String role_idDoc = m.getRoleId();
			try {

				List<Object[]> x2 = em.createNamedStoredProcedureQuery("doctor_view_document_attachment")
						.setParameter("d_id", dr_idDoc).setParameter("r_id", role_idDoc).getResultList();
				for (Object[] n : x2) {

					RestLabDocumentModel docAttachment = new RestLabDocumentModel(n[0], n[1], null, null);
					profDoc.add(docAttachment);
				}
				
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
			m.setDocumentList(profDoc);
		}
		resp.setBody(getAllProfDetails);
		logger.info("Method : viewDocumentAttachment lab Dao Ends");
		return resp;
	}
	
	/*
	 * Document Attachment Download
	 */
	
	@SuppressWarnings("unchecked")
	public RestLabDocumentModel attachmentDownloadlabDao(String id) {
		logger.info("Method : attachmentDownloadlab Dao starts");
		RestLabDocumentModel patientdetails = new RestLabDocumentModel();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_document_upload")
					.setParameter("patid", id).getResultList();

			for (Object[] m : x) {

				
				patientdetails = new RestLabDocumentModel(m[0],m[1],null,null);
				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : attachmentDownloadlab Dao ends");

		return patientdetails;
	}
}
