package nirmalya.aatithya.restmodule.ambulance.dao;

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
import nirmalya.aatithya.restmodule.common.utils.GenerateAmbulanceDocumentUploadParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.ambulance.model.RestAmbulanceDocumentUpload;
import nirmalya.aatithya.restmodule.ambulance.model.RestAmbulaneProfileModel;

@Repository
public class RestAmbulaneProfileDao {
	Logger logger = LoggerFactory.getLogger(RestAmbulaneProfileDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * For Edit Doctor Profile
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestAmbulaneProfileModel> editambulanceProfilesDao(String id) {
		logger.info("Method : editambulanceProfilesDao starts");

		RestAmbulaneProfileModel req = new RestAmbulaneProfileModel();
		JsonResponse<RestAmbulaneProfileModel> resp = new JsonResponse<RestAmbulaneProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_profile").setParameter("doctOrId", id)
					.getResultList();
		
			for (Object[] m : x) {
				String data = null;
				if (m[1] != null) {
					data = m[1].toString();
				}
				RestAmbulaneProfileModel reqEdit = new RestAmbulaneProfileModel(null, m[0], data, m[2], m[3], m[4], m[5],m[6],m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : editambulanceProfilesDao ends");
		return resp;
	}

	/*
	 * For Edit Doctor Identification
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestAmbulaneProfileModel> editambulanceIdentifications(String id) {
		logger.info("Method : editambulanceIdentifications starts");

		RestAmbulaneProfileModel req = new RestAmbulaneProfileModel();
		JsonResponse<RestAmbulaneProfileModel> resp = new JsonResponse<RestAmbulaneProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_identification")
					.setParameter("doctOrId", id).getResultList();
			/*
			 * ima_no,pancard_no,passport_no,adharcard_no,votercard_no,license_no,
			 * license_authority
			 */

			for (Object[] m : x) {
				RestAmbulaneProfileModel reqEdit = new RestAmbulaneProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editambulanceIdentifications ends");

		return resp;
	}

	/*
	 * For Edit Doctor Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestAmbulaneProfileModel> editambulanceHomeAdressDao(String id) {
		logger.info("Method : editambulanceHomeAdressDao starts");

		RestAmbulaneProfileModel req = new RestAmbulaneProfileModel();
		JsonResponse<RestAmbulaneProfileModel> resp = new JsonResponse<RestAmbulaneProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestAmbulaneProfileModel reqEdit = new RestAmbulaneProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editambulanceHomeAdressDao ends");

		return resp;
	}

	/*
	 * For Edit Doctor Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestAmbulaneProfileModel> viewambulanceHomeAdressDao(String id) {
		logger.info("Method : viewambulanceHomeAdressDao starts");

		RestAmbulaneProfileModel req = new RestAmbulaneProfileModel();
		JsonResponse<RestAmbulaneProfileModel> resp = new JsonResponse<RestAmbulaneProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestAmbulaneProfileModel reqEdit = new RestAmbulaneProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewambulanceHomeAdressDao ends");

		return resp;
	}

	/*
	 * Add Doctor profile
	 */
	public JsonResponse<Object> addambulanceProfileDao(RestAmbulaneProfileModel RestAmbulaneProfileModel) {
		logger.info("Method : addambulanceProfileDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestAmbulaneProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestAmbulaneProfileModel.getDoctorName())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_profile_modify")
						.setParameter("p_doctorId", RestAmbulaneProfileModel.getDoctorId())
						.setParameter("p_education", RestAmbulaneProfileModel.getEducation())
						.setParameter("p_proimg", RestAmbulaneProfileModel.getProfileImg())
						.setParameter("p_dobid", RestAmbulaneProfileModel.getBirthDate())
						
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
		logger.info("Method : addambulanceProfileDao ends");
		return resp;
	}

	/*
	 * Add Doctor Identification
	 */
	public JsonResponse<Object> addambulanceIdentificationDao(RestAmbulaneProfileModel RestAmbulaneProfileModel) {
		logger.info("Method : addambulanceIdentificationDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestAmbulaneProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_identification_add")
						.setParameter("p_imaNo", RestAmbulaneProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestAmbulaneProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestAmbulaneProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestAmbulaneProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestAmbulaneProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestAmbulaneProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestAmbulaneProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestAmbulaneProfileModel.getDigitalSign())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_identification_modify")
						.setParameter("p_doctorId", RestAmbulaneProfileModel.getDoctorId())
						.setParameter("p_imaNo", RestAmbulaneProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestAmbulaneProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestAmbulaneProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestAmbulaneProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestAmbulaneProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestAmbulaneProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestAmbulaneProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestAmbulaneProfileModel.getDigitalSign())

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
		logger.info("Method : addambulanceIdentificationDao ends");
		return resp;
	}

	/*
	 * Add Doctor Contact Details
	 */
	public JsonResponse<Object> addambulanceContactDetailsDao(RestAmbulaneProfileModel RestAmbulaneProfileModel) {
		logger.info("Method : addambulanceContactDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (RestAmbulaneProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestAmbulaneProfileModel.getDoctorName())

						.execute();
			} else {

				em.createNamedStoredProcedureQuery("doctor_contact_details_modify")
						.setParameter("p_doctorId", RestAmbulaneProfileModel.getDoctorId())
						.setParameter("p_country", RestAmbulaneProfileModel.getCountry())
						.setParameter("p_state", RestAmbulaneProfileModel.getState())
						.setParameter("p_district", RestAmbulaneProfileModel.getDistrict())
						.setParameter("p_city", RestAmbulaneProfileModel.getCity())
						.setParameter("p_address", RestAmbulaneProfileModel.getAddress())
						.setParameter("p_zipCode", RestAmbulaneProfileModel.getZipCode())
						.setParameter("p_mobile", RestAmbulaneProfileModel.getMobile())
						.setParameter("p_office", RestAmbulaneProfileModel.getOffice())
						.setParameter("p_email", RestAmbulaneProfileModel.getEmail())

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
		logger.info("Method : addambulanceContactDetailsDao ends");
		return resp;
	}

	// Country list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCountryListsambulanceDao() {
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
	public JsonResponse<List<DropDownModel>> getStateListambulannceDao(Integer id) {

		logger.info("Method : getStateListDao starts");
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

		logger.info("Method : getStateListDao ends");
		return resp;
	}

	// District list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getDistListsambulanceDao(String state) {

		logger.info("Method : getDistListDao starts");
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
		logger.info("Method : getDistListDao ends");
		return resp;
	}
	// City list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCityListambulanceDao(String dist) {

		logger.info("Method : getCityListDao starts");
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
		logger.info("Method : getCityListDao ends");
		return resp;
	}

	
	/*
	 * DAO Function to Add Document Upload
	 *
	 */

	@SuppressWarnings({ "unused" })
	public ResponseEntity<JsonResponse<List<RestAmbulaneProfileModel>>> restadddrDocumentUploadAmbulanceDao(
			List<RestAmbulaneProfileModel> RestAmbulaneProfileModel) {
		logger.info("Method : restadddrDocumentUpload starts");
		boolean validation = true;
		RestAmbulaneProfileModel req = new RestAmbulaneProfileModel();
		req = RestAmbulaneProfileModel.get(0);
		JsonResponse<List<RestAmbulaneProfileModel>> resp = new JsonResponse<List<RestAmbulaneProfileModel>>();

		List<RestAmbulaneProfileModel> listData = new ArrayList<RestAmbulaneProfileModel>();
		List<RestAmbulaneProfileModel> docList = new ArrayList<RestAmbulaneProfileModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";

		if (validation) {

			try {

				String value = GenerateAmbulanceDocumentUploadParameter.getdrDataUpload(req);
				if (RestAmbulaneProfileModel.get(0).getDoctorId() != null) {


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
		ResponseEntity<JsonResponse<List<RestAmbulaneProfileModel>>> response = new ResponseEntity<JsonResponse<List<RestAmbulaneProfileModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restadddrDocumentUpload ends");
		return response;

	}

	/*
	 * View data for Document Attachment
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAmbulaneProfileModel>> viewDocumentAttachmentAmbulanceDao(String id, String rolid) {
		logger.info("Method : view DocumentAttachment Dao Starts");

		List<RestAmbulaneProfileModel> getAllProfDetails = new ArrayList<RestAmbulaneProfileModel>();
		JsonResponse<List<RestAmbulaneProfileModel>> resp = new JsonResponse<List<RestAmbulaneProfileModel>>();

		try {
			Integer drid1 = null;
			if (id != null) {
				drid1 = new Integer(id);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_details").setParameter("drid", drid1)
					.setParameter("roleid", rolid).getResultList();

			for (Object[] m : x) {

				RestAmbulaneProfileModel reqemp = new RestAmbulaneProfileModel(m[0], m[1], m[2]);
				getAllProfDetails.add(reqemp);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (RestAmbulaneProfileModel m : getAllProfDetails) {

			List<RestAmbulanceDocumentUpload> profDoc = new ArrayList<RestAmbulanceDocumentUpload>();
			Integer dr_idDoc = m.getDoctorId();
			String role_idDoc = m.getRoleId();
			try {

				List<Object[]> x2 = em.createNamedStoredProcedureQuery("doctor_view_document_attachment")
						.setParameter("d_id", dr_idDoc).setParameter("r_id", role_idDoc).getResultList();
				for (Object[] n : x2) {

					RestAmbulanceDocumentUpload docAttachment = new RestAmbulanceDocumentUpload(n[0], n[1], null, null);
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
		logger.info("Method : view DocumentAttachment Dao Ends");
		return resp;
	}
	
	/*
	 * Document Attachmet Download
	 */
	
	@SuppressWarnings("unchecked")
	public RestAmbulanceDocumentUpload restattachmentDownloadAmbulanceDao(String id) {
		logger.info("Method : restattachmentDownloadDao starts");
		RestAmbulanceDocumentUpload patientdetails = new RestAmbulanceDocumentUpload();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_document_upload")
					.setParameter("patid", id).getResultList();

			for (Object[] m : x) {

				
				patientdetails = new RestAmbulanceDocumentUpload(m[0],m[1],null,null);
				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : restattachmentDownloadDao ends");

		return patientdetails;
	}
}
