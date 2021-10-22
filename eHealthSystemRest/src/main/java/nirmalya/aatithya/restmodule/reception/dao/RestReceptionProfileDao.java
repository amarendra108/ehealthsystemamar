package nirmalya.aatithya.restmodule.reception.dao;

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
import nirmalya.aatithya.restmodule.common.utils.GenerateReceptionDocumentUploadParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.reception.model.RestReceptionProfileDocumentModel;
import nirmalya.aatithya.restmodule.reception.model.RestReceptionProfileModel;

@Repository
public class RestReceptionProfileDao {
	Logger logger = LoggerFactory.getLogger(RestReceptionProfileDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * For Edit Doctor Profile
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestReceptionProfileModel> editdoctorProfileDao(String id) {
		logger.info("Method : editdoctorProfile Dao starts");

		RestReceptionProfileModel req = new RestReceptionProfileModel();
		JsonResponse<RestReceptionProfileModel> resp = new JsonResponse<RestReceptionProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_profile").setParameter("doctOrId", id)
					.getResultList();
			/*
			 * Object doctorId,Object doctorName, Object birthDate, Object gender, Object
			 * education, Object speciality, Object organisation
			 */
			for (Object[] m : x) {
				RestReceptionProfileModel reqEdit = new RestReceptionProfileModel(null, m[0], m[1], m[2], m[3], m[4], m[5],m[6],m[7]);

				req = reqEdit;

			}
			System.out.println("ref"+req);
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : editdoctorProfile Dao ends");
		return resp;
	}

	/*
	 * For Edit Doctor Identification
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestReceptionProfileModel> editreceptionIdentificationsDao(String id) {
		logger.info("Method : editreceptionIdentifications Dao starts");

		RestReceptionProfileModel req = new RestReceptionProfileModel();
		JsonResponse<RestReceptionProfileModel> resp = new JsonResponse<RestReceptionProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_identification")
					.setParameter("doctOrId", id).getResultList();
			/*
			 * ima_no,pancard_no,passport_no,adharcard_no,votercard_no,license_no,
			 * license_authority
			 */

			for (Object[] m : x) {
				RestReceptionProfileModel reqEdit = new RestReceptionProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editreceptionIdentifications Dao ends");

		return resp;
	}

	/*
	 * For Edit Doctor Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestReceptionProfileModel> editreceptionHomeAdressDao(String id) {
		logger.info("Method : editreceptionHomeAdressDao starts");

		RestReceptionProfileModel req = new RestReceptionProfileModel();
		JsonResponse<RestReceptionProfileModel> resp = new JsonResponse<RestReceptionProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestReceptionProfileModel reqEdit = new RestReceptionProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editreceptionHomeAdressDao ends");

		return resp;
	}

	/*
	 * For Edit Doctor Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestReceptionProfileModel> viewreceptionHomeAdressDao(String id) {
		logger.info("Method : viewreceptionHomeAdressDao starts");

		RestReceptionProfileModel req = new RestReceptionProfileModel();
		JsonResponse<RestReceptionProfileModel> resp = new JsonResponse<RestReceptionProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestReceptionProfileModel reqEdit = new RestReceptionProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewreceptionHomeAdressDao ends");

		return resp;
	}

	/*
	 * Add Doctor profile
	 */
	public JsonResponse<Object> addReceptionProfileDao(RestReceptionProfileModel RestReceptionProfileModel) {
		logger.info("Method : addReceptionProfileDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestReceptionProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestReceptionProfileModel.getDoctorName())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_profile_modify")
						.setParameter("p_doctorId", RestReceptionProfileModel.getDoctorId())
						.setParameter("p_education", RestReceptionProfileModel.getEducation())
						.setParameter("p_proimg", RestReceptionProfileModel.getProfileImg())
						.setParameter("p_dobid", RestReceptionProfileModel.getBirthDate())
						.execute();
				
				System.out.println("sadf"+RestReceptionProfileModel.getProfileImg() );
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
		logger.info("Method : addReceptionProfileDao ends");
		return resp;
	}

	/*
	 * Add Doctor Identification
	 */
	public JsonResponse<Object> addReceptionIdentificationDao(RestReceptionProfileModel RestReceptionProfileModel) {
		logger.info("Method : addReceptionIdentificationDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestReceptionProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_identification_add")
						.setParameter("p_imaNo", RestReceptionProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestReceptionProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestReceptionProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestReceptionProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestReceptionProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestReceptionProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestReceptionProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestReceptionProfileModel.getDigitalSign())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_identification_modify")
						.setParameter("p_doctorId", RestReceptionProfileModel.getDoctorId())
						.setParameter("p_imaNo", RestReceptionProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestReceptionProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestReceptionProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestReceptionProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestReceptionProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestReceptionProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestReceptionProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestReceptionProfileModel.getDigitalSign())

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
		logger.info("Method : addReceptionIdentificationDao ends");
		return resp;
	}

	/*
	 * Add Doctor Contact Details
	 */
	public JsonResponse<Object> addReceptionContactDetailsDao(RestReceptionProfileModel RestReceptionProfileModel) {
		logger.info("Method : addReceptionContactDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (RestReceptionProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestReceptionProfileModel.getDoctorName())

						.execute();
			} else {

				em.createNamedStoredProcedureQuery("doctor_contact_details_modify")
						.setParameter("p_doctorId", RestReceptionProfileModel.getDoctorId())
						.setParameter("p_country", RestReceptionProfileModel.getCountry())
						.setParameter("p_state", RestReceptionProfileModel.getState())
						.setParameter("p_district", RestReceptionProfileModel.getDistrict())
						.setParameter("p_city", RestReceptionProfileModel.getCity())
						.setParameter("p_address", RestReceptionProfileModel.getAddress())
						.setParameter("p_zipCode", RestReceptionProfileModel.getZipCode())
						.setParameter("p_mobile", RestReceptionProfileModel.getMobile())
						.setParameter("p_office", RestReceptionProfileModel.getOffice())
						.setParameter("p_email", RestReceptionProfileModel.getEmail())

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
		logger.info("Method : addReceptionContactDetailsDao ends");
		return resp;
	}

	// Country list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCountryListsreceptionDao() {
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
	public JsonResponse<List<DropDownModel>> getStateListreceptionDao(Integer id) {

		logger.info("Method : getStateListreceptionDao starts");
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

		logger.info("Method : getStateListreceptionDao ends");
		return resp;
	}

	// District list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getDistListreceptionDao(String state) {

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
	public JsonResponse<List<DropDownModel>> getCityListreceptionDao(String dist) {

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
	public ResponseEntity<JsonResponse<List<RestReceptionProfileModel>>> adddrDocumentUploadreceptionDao(
			List<RestReceptionProfileModel> RestReceptionProfileModel) {
		System.out.println(" restadddrDocumentUploadDao " + RestReceptionProfileModel);
		logger.info("Method : restadddrDocumentUpload starts");
		boolean validation = true;
		RestReceptionProfileModel req = new RestReceptionProfileModel();
		req = RestReceptionProfileModel.get(0);
		JsonResponse<List<RestReceptionProfileModel>> resp = new JsonResponse<List<RestReceptionProfileModel>>();

		List<RestReceptionProfileModel> listData = new ArrayList<RestReceptionProfileModel>();
		List<RestReceptionProfileModel> docList = new ArrayList<RestReceptionProfileModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";

		if (validation) {

			try {

				System.out.println("value" + RestReceptionProfileModel);
				String value = GenerateReceptionDocumentUploadParameter.getdrDataUpload(req);
				System.out.println("value" + value);
				if (RestReceptionProfileModel.get(0).getDoctorId() != null) {

					System.out.println("patientmodel" + RestReceptionProfileModel.get(0).getDoctorId());

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
		ResponseEntity<JsonResponse<List<RestReceptionProfileModel>>> response = new ResponseEntity<JsonResponse<List<RestReceptionProfileModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restadddrDocumentUpload ends");
		return response;

	}

	/*
	 * View data for Document Attachment
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestReceptionProfileModel>> viewDocumentAttachmentreceptiopnDao(String id, String rolid) {
		logger.info("Method : view DocumentAttachment Dao Starts");

		List<RestReceptionProfileModel> getAllProfDetails = new ArrayList<RestReceptionProfileModel>();
		JsonResponse<List<RestReceptionProfileModel>> resp = new JsonResponse<List<RestReceptionProfileModel>>();

		try {
			Integer drid1 = null;
			if (id != null) {
				drid1 = new Integer(id);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_details").setParameter("drid", drid1)
					.setParameter("roleid", rolid).getResultList();

			for (Object[] m : x) {

				RestReceptionProfileModel reqemp = new RestReceptionProfileModel(m[0], m[1], m[2]);
				getAllProfDetails.add(reqemp);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (RestReceptionProfileModel m : getAllProfDetails) {

			List<RestReceptionProfileDocumentModel> profDoc = new ArrayList<RestReceptionProfileDocumentModel>();
			Integer dr_idDoc = m.getDoctorId();
			String role_idDoc = m.getRoleId();
			try {

				List<Object[]> x2 = em.createNamedStoredProcedureQuery("doctor_view_document_attachment")
						.setParameter("d_id", dr_idDoc).setParameter("r_id", role_idDoc).getResultList();
				for (Object[] n : x2) {

					RestReceptionProfileDocumentModel docAttachment = new RestReceptionProfileDocumentModel(n[0], n[1], null, null);
					profDoc.add(docAttachment);
				}
				System.out.println("@@profDoc@@@@@@@" + profDoc);
				
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
	public RestReceptionProfileDocumentModel attachmentDownloadreceptionDao(String id) {
		logger.info("Method : restattachmentDownloadDao starts");
		RestReceptionProfileDocumentModel patientdetails = new RestReceptionProfileDocumentModel();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_document_upload")
					.setParameter("patid", id).getResultList();

			for (Object[] m : x) {

				
				patientdetails = new RestReceptionProfileDocumentModel(m[0],m[1],null,null);
				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : restattachmentDownloadDao ends");

		return patientdetails;
	}
}
