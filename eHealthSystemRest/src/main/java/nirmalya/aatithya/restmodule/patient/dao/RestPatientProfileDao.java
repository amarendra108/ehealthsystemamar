package nirmalya.aatithya.restmodule.patient.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.patient.dao.RestPatientProfileDao;
@Repository
public class RestPatientProfileDao {
	Logger logger = LoggerFactory.getLogger(RestPatientProfileDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
/*	@SuppressWarnings("unchecked")
	public List<DropDownModel> getgenderListnew() {
		logger.info("Method : getgenderListnew starts");

		List<DropDownModel> genderTypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_profile_gender_drpdwn").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				genderTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getgenderListnew ends");
		return genderTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getbloodgroupListnew() {
		logger.info("Method : getbloodgroupListnew starts");

		List<DropDownModel> bloodgroupList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_profile_bloodgrp_drpdwn").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				bloodgroupList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getbloodgroupListnew ends");
		return bloodgroupList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getmaritalstatusListnew() {
		logger.info("Method : getmaritalstatusListnew starts");

		List<DropDownModel> maritalstatusList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_profile_maritialsts_drpdwn").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				maritalstatusList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getmaritalstatusListnew ends");
		return maritalstatusList;
	}
*/
}
