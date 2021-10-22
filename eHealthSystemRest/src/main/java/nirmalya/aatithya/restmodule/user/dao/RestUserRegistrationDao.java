package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.RestUserRegistrationModel;

@Repository
public class RestUserRegistrationDao {
	Logger logger = LoggerFactory.getLogger(RestUserRegistrationDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// Title list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> geTitleLists() {
		logger.info("Method : geTitleLists Dao starts");

		List<DropDownModel> geTitleList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_title_list").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				geTitleList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : geTitleLists Dao ends");

		return geTitleList;
	}

	// Country list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCountryLists() {
		logger.info("Method : getCountryLists Dao starts");

		List<DropDownModel> getCountryList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_country_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2]);
				getCountryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCountryLists Dao ends");

		return getCountryList;
	}

	
	// Blood Group list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBloodGrpLists() {
		logger.info("Method : getBloodGrpLists Dao starts");

		List<DropDownModel> bloodGrpList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_bloodGroup_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				bloodGrpList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getBloodGrpLists Dao ends");

		return bloodGrpList;
	}

	// Role User list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getroleUserLists() {
		logger.info("Method : getroleUserList Dao starts");

		List<DropDownModel> uRoleList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_roleUser_list").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				uRoleList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getroleUserList Dao ends");
		return uRoleList;
	}

	// Gender list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getGenderLists() {
		logger.info("Method : getGenderList Dao starts");

		List<DropDownModel> genderList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_profile_gender_drpdwn").getResultList();

			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				genderList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getGenderList Dao ends");
		return genderList;
	}
	// Speciality list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getSpecialityListDao(Integer id) {

		logger.info("Method : getSpecialityListDao starts");
		List<DropDownModel> splList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_speciality_list").setParameter("roleid", id)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				splList.add(dropDownModel);
			}
			resp.setBody(splList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getSpecialityListDao ends");
		return resp;
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
	public JsonResponse<List<DropDownModel>> getDistListDao(String state) {

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

	// AutoSearch
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestUserRegistrationModel>> getOrganizationAutoSearchListDao(String id) {
		logger.info("Method : getOrganizationAutoSearchListDao starts");
		List<RestUserRegistrationModel> orgNameList = new ArrayList<RestUserRegistrationModel>();
		JsonResponse<List<RestUserRegistrationModel>> resp = new JsonResponse<List<RestUserRegistrationModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_autoSearch_organization_list")
					.setParameter("searchValue", id).getResultList();
			for (Object[] m : x) {
				RestUserRegistrationModel dropDownModel = new RestUserRegistrationModel(m[0], m[1]);
				orgNameList.add(dropDownModel);
			}
			resp.setBody(orgNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getOrganizationAutoSearchListDao ends");
		return resp;
	}

	// get card plan
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestUserRegistrationModel>> getPlanCardDao(String id) {

		logger.info("Method : getPlanCard Dao starts");
		List<RestUserRegistrationModel> plan = new ArrayList<RestUserRegistrationModel>();
		JsonResponse<List<RestUserRegistrationModel>> resp = new JsonResponse<List<RestUserRegistrationModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_plan_card").setParameter("descName", id)
					.getResultList();

			for (Object[] m : x) {

				RestUserRegistrationModel planCard = new RestUserRegistrationModel(m[0], m[1].toString(),
						m[2].toString(), m[3]);

				plan.add(planCard);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(plan);
		logger.info("Method : getPlanCard Dao ends");
		return resp;

	}

	/**
	 * DAO Function to Add User Registration
	 *
	 */
	// save
	public JsonResponse<Object> addUserRegistrationDao(RestUserRegistrationModel regdModel) {

		logger.info("Method : addUserRegistrationDao starts");
		System.out.println(regdModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (regdModel.getCreatedBy() == null) {
				em.createNamedStoredProcedureQuery("user_add_registration")
						.setParameter("p_profileImg", regdModel.getFileProfileimg())
						.setParameter("p_patientId", regdModel.getPatientId())
						.setParameter("p_title", regdModel.getTittle())
						.setParameter("p_fName", regdModel.getfName())
						.setParameter("p_lName", regdModel.getlName())
						.setParameter("p_countryName", regdModel.getCountryName())
						.setParameter("p_country", regdModel.getCountry())
						.setParameter("p_stateName", regdModel.getStateName())
						.setParameter("p_state", regdModel.getState())
						.setParameter("p_dist", regdModel.getDist())
						.setParameter("p_city", regdModel.getCity())
						.setParameter("p_mob", regdModel.getMob())
						.setParameter("p_mail", regdModel.getMail())
						.setParameter("p_userId", regdModel.getUserId())
						.setParameter("p_qrCode", regdModel.getqRFileName())
						.setParameter("p_password", regdModel.getPassword()).execute();
			} else {
				em.createNamedStoredProcedureQuery("lab_add_walkinUser_registration")
				.setParameter("p_profileImg", regdModel.getFileProfileimg())
				.setParameter("p_patientId", regdModel.getPatientId())
				.setParameter("p_title", regdModel.getTittle())
				.setParameter("p_fName", regdModel.getfName())
				.setParameter("p_lName", regdModel.getlName())
				.setParameter("p_countryName", regdModel.getCountryName())
				.setParameter("p_country", regdModel.getCountry())
				.setParameter("p_stateName", regdModel.getStateName())
				.setParameter("p_state", regdModel.getState())
				.setParameter("p_dist", regdModel.getDist())
				.setParameter("p_city", regdModel.getCity())
				.setParameter("p_mob", regdModel.getMob())
				.setParameter("p_mail", regdModel.getMail())
				.setParameter("p_userId", regdModel.getUserId())
				.setParameter("p_password", regdModel.getPassword())
				.setParameter("p_createdBy", regdModel.getCreatedBy())
				.setParameter("p_qrCode", regdModel.getqRFileName()).execute();
			}
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : addUserRegistrationDao ends");
		return resp;
	}

	/**
	 * DAO Function to Add User Registration
	 *
	 */

	// save
	public JsonResponse<Object> addProfRegistrationDao(RestUserRegistrationModel regdModel) {

		logger.info("Method : addProfRegistrationDao starts");

		System.out.println("regdModel===" + regdModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			em.createNamedStoredProcedureQuery("user_add_prof_registration")
					.setParameter("p_profileImg", regdModel.getFileProfileimg())
					.setParameter("p_hsId", regdModel.getOrganizationId())
					.setParameter("p_title", regdModel.getTittle()).setParameter("p_name", regdModel.getName())
					.setParameter("p_userId", regdModel.getUserId()).setParameter("p_password", regdModel.getPassword())
					.setParameter("p_exp", regdModel.getExperience()).setParameter("p_role", regdModel.getRoleUser())
					.setParameter("p_speciality", regdModel.getSpeciality())
					.setParameter("p_jobType", regdModel.getJobType())
					.setParameter("p_gender", regdModel.getGender()).setParameter("p_address", regdModel.getAddress())
					.setParameter("p_country", regdModel.getCountry()).setParameter("p_state", regdModel.getState())
					.setParameter("p_dist", regdModel.getDist()).setParameter("p_city", regdModel.getCity())
					.setParameter("p_pin", regdModel.getPin()).setParameter("p_mob", regdModel.getMob())
					.setParameter("p_mail", regdModel.getMail()).setParameter("p_file", regdModel.getFileUpload())
					.execute();
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : addProfRegistrationDao ends");
		return resp;
	}
	
	// State list Organisation

			@SuppressWarnings("unchecked")
			public JsonResponse<List<DropDownModel>> getStateListOrgDao(Integer id) {

				logger.info("Method : getStateListDao starts");
				List<DropDownModel> stateList = new ArrayList<DropDownModel>();
				JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("user_state_list").setParameter("country", id)
							.getResultList();
					for (Object[] m : x) {
						DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString(), m[2].toString());
						stateList.add(dropDownModel);
						System.out.println("stateList"+stateList);
					}

					resp.setBody(stateList);

				} catch (Exception e) {
					e.printStackTrace();
				}
	System.out.println("getStateListOrgDao"+resp);
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
			public JsonResponse<List<DropDownModel>> getCityListsDao(String dist) {

				logger.info("Method : getCityLists starts");
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
				logger.info("Method : getCityLists ends");
				return resp;
			}
			// Organisation Type list
			@SuppressWarnings("unchecked")
			public List<DropDownModel> organisationTypeListLists() {
				logger.info("Method : organisationTypeListLists Dao starts");

				List<DropDownModel> organisationTypeListLists = new ArrayList<DropDownModel>();

				try {

					List<Object[]> x = em.createNamedStoredProcedureQuery("organisation_type_drpdwn").getResultList();

					for (Object[] m : x) {

						DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
						organisationTypeListLists.add(dropDownModel);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				logger.info("Method : organisationTypeListLists Dao ends");
				return organisationTypeListLists;
			}
			
			/*
			 * Add Organisation
			 */
			public JsonResponse<Object> addOrganisation(RestUserRegistrationModel restUserRegistrationModel) {
				logger.info("Method : addOrganisation starts");
				System.out.println("######" + restUserRegistrationModel);
				JsonResponse<Object> resp = new JsonResponse<Object>();
				try {
					if (restUserRegistrationModel.getOrganizationModalId() == null) {
						em.createNamedStoredProcedureQuery("user_add_organisation")
								.setParameter("p_orgName", restUserRegistrationModel.getOrgName())
								.setParameter("p_orgAddress", restUserRegistrationModel.getOrgAddress())
								.setParameter("p_orgTypeId", restUserRegistrationModel.getOrgTypeId())
								.setParameter("p_orgDocNm", restUserRegistrationModel.getOrgdocName())
								.setParameter("p_orgAttachment", restUserRegistrationModel.getOrgAttachment())
								.setParameter("p_orgRegdNo", restUserRegistrationModel.getOrgRegdNo())
								.setParameter("p_orgGst", restUserRegistrationModel.getOrgGst())
								.setParameter("p_getCountry", restUserRegistrationModel.getCountry())
								.setParameter("p_getState", restUserRegistrationModel.getState())
								.setParameter("p_getDist", restUserRegistrationModel.getDist())
								.setParameter("p_getCity", restUserRegistrationModel.getCity())
								.setParameter("p_getOrgPin", restUserRegistrationModel.getOrgPin())
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
				System.out.println("ADD DAO" + resp);
				logger.info("Method : addOrganisation ends");
				return resp;
			}
}
