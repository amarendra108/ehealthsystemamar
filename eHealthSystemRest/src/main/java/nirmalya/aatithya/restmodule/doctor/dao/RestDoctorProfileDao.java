package nirmalya.aatithya.restmodule.doctor.dao;

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
import nirmalya.aatithya.restmodule.common.utils.GenerateDoctorDocumentUploadParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDocumentUpload;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorProfileModel;

@Repository
public class RestDoctorProfileDao {
	Logger logger = LoggerFactory.getLogger(RestDoctorProfileDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * For Edit Doctor Profile
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestDoctorProfileModel> editdoctorProfile(String id) {
		logger.info("Method : getCustomerDetails starts");

		RestDoctorProfileModel req = new RestDoctorProfileModel();
		JsonResponse<RestDoctorProfileModel> resp = new JsonResponse<RestDoctorProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_profile").setParameter("doctOrId", id)
					.getResultList();
		
			for (Object[] m : x) {
				String data = null;
				if (m[1] != null) {
					data = m[1].toString();
				}
				RestDoctorProfileModel reqEdit = new RestDoctorProfileModel(null, m[0], data, m[2], m[3], m[4], m[5],m[6],m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCustomerDetails ends");
		return resp;
	}

	/*
	 * For Edit Doctor Identification
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestDoctorProfileModel> editdoctorIdentification(String id) {
		logger.info("Method : editdoctorIdentification starts");

		RestDoctorProfileModel req = new RestDoctorProfileModel();
		JsonResponse<RestDoctorProfileModel> resp = new JsonResponse<RestDoctorProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_identification")
					.setParameter("doctOrId", id).getResultList();
			/*
			 * ima_no,pancard_no,passport_no,adharcard_no,votercard_no,license_no,
			 * license_authority
			 */

			for (Object[] m : x) {
				RestDoctorProfileModel reqEdit = new RestDoctorProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCustomerDetails ends");

		return resp;
	}

	/*
	 * For Edit Doctor Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestDoctorProfileModel> editdoctorHomeAdress(String id) {
		logger.info("Method : editdoctorHomeAdress starts");

		RestDoctorProfileModel req = new RestDoctorProfileModel();
		JsonResponse<RestDoctorProfileModel> resp = new JsonResponse<RestDoctorProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestDoctorProfileModel reqEdit = new RestDoctorProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editdoctorHomeAdress ends");

		return resp;
	}

	/*
	 * For Edit Doctor Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestDoctorProfileModel> viewdoctorHomeAdress(String id) {
		logger.info("Method : viewdoctorHomeAdress starts");

		RestDoctorProfileModel req = new RestDoctorProfileModel();
		JsonResponse<RestDoctorProfileModel> resp = new JsonResponse<RestDoctorProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestDoctorProfileModel reqEdit = new RestDoctorProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewdoctorHomeAdress ends");

		return resp;
	}

	/*
	 * Add Doctor profile
	 */
	public JsonResponse<Object> addDoctorProfile(RestDoctorProfileModel restDoctorProfileModel) {
		logger.info("Method : addDoctorProfile starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (restDoctorProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", restDoctorProfileModel.getDoctorName())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_profile_modify")
						.setParameter("p_doctorId", restDoctorProfileModel.getDoctorId())
						.setParameter("p_education", restDoctorProfileModel.getEducation())
						.setParameter("p_proimg", restDoctorProfileModel.getProfileImg())
						.setParameter("p_dobid", restDoctorProfileModel.getBirthDate())
						
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
		logger.info("Method : addDoctorProfile ends");
		return resp;
	}

	/*
	 * Add Doctor Identification
	 */
	public JsonResponse<Object> addDoctorIdentification(RestDoctorProfileModel restDoctorProfileModel) {
		logger.info("Method : addDoctorIdentification starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (restDoctorProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_identification_add")
						.setParameter("p_imaNo", restDoctorProfileModel.getImaNo())
						.setParameter("p_panCardNo", restDoctorProfileModel.getPanCardNo())
						.setParameter("p_passportNo", restDoctorProfileModel.getPassportNo())
						.setParameter("p_adharNo", restDoctorProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", restDoctorProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", restDoctorProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", restDoctorProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", restDoctorProfileModel.getDigitalSign())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_identification_modify")
						.setParameter("p_doctorId", restDoctorProfileModel.getDoctorId())
						.setParameter("p_imaNo", restDoctorProfileModel.getImaNo())
						.setParameter("p_panCardNo", restDoctorProfileModel.getPanCardNo())
						.setParameter("p_passportNo", restDoctorProfileModel.getPassportNo())
						.setParameter("p_adharNo", restDoctorProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", restDoctorProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", restDoctorProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", restDoctorProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", restDoctorProfileModel.getDigitalSign())

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
		logger.info("Method : addDoctorIdentification ends");
		return resp;
	}

	/*
	 * Add Doctor Contact Details
	 */
	public JsonResponse<Object> addDoctorContactDetails(RestDoctorProfileModel restDoctorProfileModel) {
		logger.info("Method : addDoctorContactDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (restDoctorProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", restDoctorProfileModel.getDoctorName())

						.execute();
			} else {

				em.createNamedStoredProcedureQuery("doctor_contact_details_modify")
						.setParameter("p_doctorId", restDoctorProfileModel.getDoctorId())
						.setParameter("p_country", restDoctorProfileModel.getCountry())
						.setParameter("p_state", restDoctorProfileModel.getState())
						.setParameter("p_district", restDoctorProfileModel.getDistrict())
						.setParameter("p_city", restDoctorProfileModel.getCity())
						.setParameter("p_address", restDoctorProfileModel.getAddress())
						.setParameter("p_zipCode", restDoctorProfileModel.getZipCode())
						.setParameter("p_mobile", restDoctorProfileModel.getMobile())
						.setParameter("p_office", restDoctorProfileModel.getOffice())
						.setParameter("p_email", restDoctorProfileModel.getEmail())

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
		logger.info("Method : addDoctorContactDetails ends");
		return resp;
	}

	// Country list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCountryLists() {
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
	public JsonResponse<List<DropDownModel>> getStateListDao(Integer id) {

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
	public JsonResponse<List<DropDownModel>> getDistLists(String state) {

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
	public JsonResponse<List<DropDownModel>> getCityListDao(String dist) {

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
	public ResponseEntity<JsonResponse<List<RestDoctorProfileModel>>> restadddrDocumentUploadDao(
			List<RestDoctorProfileModel> restDoctorProfileModel) {
		logger.info("Method : restadddrDocumentUpload starts");
		boolean validation = true;
		RestDoctorProfileModel req = new RestDoctorProfileModel();
		req = restDoctorProfileModel.get(0);
		JsonResponse<List<RestDoctorProfileModel>> resp = new JsonResponse<List<RestDoctorProfileModel>>();

		List<RestDoctorProfileModel> listData = new ArrayList<RestDoctorProfileModel>();
		List<RestDoctorProfileModel> docList = new ArrayList<RestDoctorProfileModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";

		if (validation) {

			try {

				String value = GenerateDoctorDocumentUploadParameter.getdrDataUpload(req);
				if (restDoctorProfileModel.get(0).getDoctorId() != null) {


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
		ResponseEntity<JsonResponse<List<RestDoctorProfileModel>>> response = new ResponseEntity<JsonResponse<List<RestDoctorProfileModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restadddrDocumentUpload ends");
		return response;

	}

	/*
	 * View data for Document Attachment
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestDoctorProfileModel>> viewDocumentAttachmentDao(String id, String rolid) {
		logger.info("Method : view DocumentAttachment Dao Starts");

		List<RestDoctorProfileModel> getAllProfDetails = new ArrayList<RestDoctorProfileModel>();
		JsonResponse<List<RestDoctorProfileModel>> resp = new JsonResponse<List<RestDoctorProfileModel>>();

		try {
			Integer drid1 = null;
			if (id != null) {
				drid1 = new Integer(id);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_details").setParameter("drid", drid1)
					.setParameter("roleid", rolid).getResultList();

			for (Object[] m : x) {

				RestDoctorProfileModel reqemp = new RestDoctorProfileModel(m[0], m[1], m[2]);
				getAllProfDetails.add(reqemp);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (RestDoctorProfileModel m : getAllProfDetails) {

			List<RestDoctorDocumentUpload> profDoc = new ArrayList<RestDoctorDocumentUpload>();
			Integer dr_idDoc = m.getDoctorId();
			String role_idDoc = m.getRoleId();
			try {

				List<Object[]> x2 = em.createNamedStoredProcedureQuery("doctor_view_document_attachment")
						.setParameter("d_id", dr_idDoc).setParameter("r_id", role_idDoc).getResultList();
				for (Object[] n : x2) {

					RestDoctorDocumentUpload docAttachment = new RestDoctorDocumentUpload(n[0], n[1], null, null);
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
	public RestDoctorDocumentUpload restattachmentDownloadDao(String id) {
		logger.info("Method : restattachmentDownloadDao starts");
		RestDoctorDocumentUpload patientdetails = new RestDoctorDocumentUpload();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_document_upload")
					.setParameter("patid", id).getResultList();

			for (Object[] m : x) {

				
				patientdetails = new RestDoctorDocumentUpload(m[0],m[1],null,null);
				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : restattachmentDownloadDao ends");

		return patientdetails;
	}
}
