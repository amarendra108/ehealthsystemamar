package nirmalya.aatithya.restmodule.bloodbank.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateBloodBankDocumentUploadParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.bloodbank.model.RestBloodBankDocumentUpload;
import nirmalya.aatithya.restmodule.bloodbank.model.RestBloodBankProfileModel;

@Repository
public class RestBloodBankProfileDao {
	Logger logger = LoggerFactory.getLogger(RestBloodBankProfileDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * For Edit Doctor Profile
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestBloodBankProfileModel> editBloodbankProfileDao(String id) {
		logger.info("Method : editBloodbankProfile Dao starts");

		RestBloodBankProfileModel req = new RestBloodBankProfileModel();
		JsonResponse<RestBloodBankProfileModel> resp = new JsonResponse<RestBloodBankProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_profile").setParameter("doctOrId", id)
					.getResultList();
		
			for (Object[] m : x) {
				String data = null;
				if (m[1] != null) {
					data = m[1].toString();
				}
				RestBloodBankProfileModel reqEdit = new RestBloodBankProfileModel(null, m[0], data, m[2], m[3], m[4], m[5],m[6],m[7]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : editBloodbankProfile Dao ends");
		return resp;
	}

	/*
	 * For Edit Doctor Identification
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestBloodBankProfileModel> editBloodbankIdentificationDao(String id) {
		logger.info("Method : editdoctorIdentification starts");

		RestBloodBankProfileModel req = new RestBloodBankProfileModel();
		JsonResponse<RestBloodBankProfileModel> resp = new JsonResponse<RestBloodBankProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_identification")
					.setParameter("doctOrId", id).getResultList();
			/*
			 * ima_no,pancard_no,passport_no,adharcard_no,votercard_no,license_no,
			 * license_authority
			 */

			for (Object[] m : x) {
				RestBloodBankProfileModel reqEdit = new RestBloodBankProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
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
	public JsonResponse<RestBloodBankProfileModel> editBloodbankHomeAdressDao(String id) {
		logger.info("Method : editdoctorHomeAdress starts");

		RestBloodBankProfileModel req = new RestBloodBankProfileModel();
		JsonResponse<RestBloodBankProfileModel> resp = new JsonResponse<RestBloodBankProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_edit_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestBloodBankProfileModel reqEdit = new RestBloodBankProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
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
	public JsonResponse<RestBloodBankProfileModel> viewBloodbankHomeAdressDao(String id) {
		logger.info("Method : viewdoctorHomeAdress starts");

		RestBloodBankProfileModel req = new RestBloodBankProfileModel();
		JsonResponse<RestBloodBankProfileModel> resp = new JsonResponse<RestBloodBankProfileModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_home_address")
					.setParameter("doctOrId", id).getResultList();

			for (Object[] m : x) {
				RestBloodBankProfileModel reqEdit = new RestBloodBankProfileModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
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
	public JsonResponse<Object> addBloodbankProfileDao(RestBloodBankProfileModel RestBloodBankProfileModel) {
		logger.info("Method : addDoctorProfile starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestBloodBankProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestBloodBankProfileModel.getDoctorName())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_profile_modify")
						.setParameter("p_doctorId", RestBloodBankProfileModel.getDoctorId())
						.setParameter("p_education", RestBloodBankProfileModel.getEducation())
						.setParameter("p_proimg", RestBloodBankProfileModel.getProfileImg())
						.setParameter("p_dobid", RestBloodBankProfileModel.getBirthDate())
						
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
	public JsonResponse<Object> addBloodbankIdentificationDao(RestBloodBankProfileModel RestBloodBankProfileModel) {
		logger.info("Method : addDoctorIdentification starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (RestBloodBankProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_identification_add")
						.setParameter("p_imaNo", RestBloodBankProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestBloodBankProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestBloodBankProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestBloodBankProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestBloodBankProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestBloodBankProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestBloodBankProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestBloodBankProfileModel.getDigitalSign())

						.execute();
			} else {
				em.createNamedStoredProcedureQuery("doctor_identification_modify")
						.setParameter("p_doctorId", RestBloodBankProfileModel.getDoctorId())
						.setParameter("p_imaNo", RestBloodBankProfileModel.getImaNo())
						.setParameter("p_panCardNo", RestBloodBankProfileModel.getPanCardNo())
						.setParameter("p_passportNo", RestBloodBankProfileModel.getPassportNo())
						.setParameter("p_adharNo", RestBloodBankProfileModel.getAdharNo())
						.setParameter("p_voterCardNo", RestBloodBankProfileModel.getVotorCardNo())
						.setParameter("p_licenseNo", RestBloodBankProfileModel.getLicenseNo())
						.setParameter("p_licenseAuthority", RestBloodBankProfileModel.getLicenseAuthority())
						.setParameter("p_digitalSign", RestBloodBankProfileModel.getDigitalSign())

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
	public JsonResponse<Object> addBloodbankContactDetailsDao(RestBloodBankProfileModel RestBloodBankProfileModel) {
		logger.info("Method : addDoctorContactDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (RestBloodBankProfileModel.getDoctorId() == null) {
				em.createNamedStoredProcedureQuery("doctor_profile_add")

						.setParameter("p_insuranceCompany", RestBloodBankProfileModel.getDoctorName())

						.execute();
			} else {

				em.createNamedStoredProcedureQuery("doctor_contact_details_modify")
						.setParameter("p_doctorId", RestBloodBankProfileModel.getDoctorId())
						.setParameter("p_country", RestBloodBankProfileModel.getCountry())
						.setParameter("p_state", RestBloodBankProfileModel.getState())
						.setParameter("p_district", RestBloodBankProfileModel.getDistrict())
						.setParameter("p_city", RestBloodBankProfileModel.getCity())
						.setParameter("p_address", RestBloodBankProfileModel.getAddress())
						.setParameter("p_zipCode", RestBloodBankProfileModel.getZipCode())
						.setParameter("p_mobile", RestBloodBankProfileModel.getMobile())
						.setParameter("p_office", RestBloodBankProfileModel.getOffice())
						.setParameter("p_email", RestBloodBankProfileModel.getEmail())

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
	public List<DropDownModel> getCountryListsBloodbankDao() {
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
	public JsonResponse<List<DropDownModel>> getStateListBloodbankDao(Integer id) {

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
	public JsonResponse<List<DropDownModel>> getDistListsBloodbankDao(String state) {

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
	public JsonResponse<List<DropDownModel>> getCityListBloodbankDao(String dist) {

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
	public ResponseEntity<JsonResponse<List<RestBloodBankProfileModel>>> adddrDocumentUploadBloodbankDao(
			List<RestBloodBankProfileModel> RestBloodBankProfileModel) {
		logger.info("Method : restadddrDocumentUpload starts");
		boolean validation = true;
		RestBloodBankProfileModel req = new RestBloodBankProfileModel();
		req = RestBloodBankProfileModel.get(0);
		JsonResponse<List<RestBloodBankProfileModel>> resp = new JsonResponse<List<RestBloodBankProfileModel>>();

		List<RestBloodBankProfileModel> listData = new ArrayList<RestBloodBankProfileModel>();
		List<RestBloodBankProfileModel> docList = new ArrayList<RestBloodBankProfileModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";

		if (validation) {

			try {

				String value = GenerateBloodBankDocumentUploadParameter.getdrDataUpload(req);
				if (RestBloodBankProfileModel.get(0).getDoctorId() != null) {


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
		ResponseEntity<JsonResponse<List<RestBloodBankProfileModel>>> response = new ResponseEntity<JsonResponse<List<RestBloodBankProfileModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restadddrDocumentUpload ends");
		return response;

	}

	/*
	 * View data for Document Attachment
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestBloodBankProfileModel>> viewDocumentAttachmentBloodbankDao(String id, String rolid) {
		logger.info("Method : view DocumentAttachment Dao Starts");

		List<RestBloodBankProfileModel> getAllProfDetails = new ArrayList<RestBloodBankProfileModel>();
		JsonResponse<List<RestBloodBankProfileModel>> resp = new JsonResponse<List<RestBloodBankProfileModel>>();

		try {
			Integer drid1 = null;
			if (id != null) {
				drid1 = new Integer(id);
			}

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_details").setParameter("drid", drid1)
					.setParameter("roleid", rolid).getResultList();

			for (Object[] m : x) {

				RestBloodBankProfileModel reqemp = new RestBloodBankProfileModel(m[0], m[1], m[2]);
				getAllProfDetails.add(reqemp);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (RestBloodBankProfileModel m : getAllProfDetails) {

			List<RestBloodBankDocumentUpload> profDoc = new ArrayList<RestBloodBankDocumentUpload>();
			Integer dr_idDoc = m.getDoctorId();
			String role_idDoc = m.getRoleId();
			try {

				List<Object[]> x2 = em.createNamedStoredProcedureQuery("doctor_view_document_attachment")
						.setParameter("d_id", dr_idDoc).setParameter("r_id", role_idDoc).getResultList();
				for (Object[] n : x2) {

					RestBloodBankDocumentUpload docAttachment = new RestBloodBankDocumentUpload(n[0], n[1], null, null);
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
	public RestBloodBankDocumentUpload restattachmentDownloadBloodbankDao(String id) {
		logger.info("Method : restattachmentDownloadDao starts");
		RestBloodBankDocumentUpload patientdetails = new RestBloodBankDocumentUpload();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_document_upload")
					.setParameter("patid", id).getResultList();

			for (Object[] m : x) {

				
				patientdetails = new RestBloodBankDocumentUpload(m[0],m[1],null,null);
				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : restattachmentDownloadDao ends");

		return patientdetails;
	}
}
