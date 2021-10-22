package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

@Repository
public class CheckDuplicateDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	Logger logger = LoggerFactory.getLogger(CheckDuplicateDao.class);
	
	@SuppressWarnings("unchecked")
	public Boolean isPatientMobileNumberExist (String mobile) {
		logger.info("Method : isPatientMobileNumberExist Dao starts");
		
		Boolean isExist =  false;

		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_mobile_number_exist").setParameter("mobile", mobile).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isPatientMobileNumberExist Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isPatientAadharNumberExist (String aadhar) {
		logger.info("Method : isPatientAadharNumberExist Dao starts");
		
		Boolean isExist =  false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_aadhar_number_exist").setParameter("aadhar", aadhar).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isPatientMobileNumberExist Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isEContactAdded (String userid) {
		logger.info("Method : isEContactAdded Dao starts");
		
		Boolean isExist =  false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_emergency_contact_exist").setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isEContactAdded Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isFDoctorAdded (String userid) {
		logger.info("Method : isFDoctorAdded Dao starts");
		
		Boolean isExist =  false;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_family_doctor_exist").setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList != null) {
				isExist =  true;
			}
			
			if(dataList.size() > 0) {
				isExist =  true;
			} else {
				isExist =  false;
			}
			
		} catch (Exception e) {
			isExist =  false;
		}
		
		logger.info("Method : isFDoctorAdded Dao ends");
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public String getCasePaperNumber (String userid) {
		logger.info("Method : getCasePaperNumber Dao starts");
		
		String casepaper =  null;
		
		List<DropDownModel> dataList = new ArrayList<DropDownModel>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_casepaper_no").setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dataList.add(dropDownModel);
			}
			
			if(dataList.size() > 0) {
				casepaper = dataList.get(0).getName();
			} else {
				casepaper =  null;
			}
			
		} catch (Exception e) {
			casepaper =  null;
		}
		
		logger.info("Method : getCasePaperNumber Dao ends");
		return casepaper;
	}
	
}
