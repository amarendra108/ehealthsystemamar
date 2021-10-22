package nirmalya.aatithya.restmodule.patient.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestImmunizationModel;
import nirmalya.aatithya.restmodule.user.model.RestUserRegistrationModel;

@Repository
public class RestImmunizationDao {
	Logger logger = LoggerFactory.getLogger(RestImmunizationDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// get Immunization Type
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getImmunizationTypeList() {
		logger.info("Method : getImmunizationType Dao starts");

		List<DropDownModel> geimunizationList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_immunization_type").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				geimunizationList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getImmunizationType Dao ends");

		return geimunizationList;
	}
	
	// view

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestImmunizationModel>> viewImmunizationDao(BigInteger id) {

		logger.info("Method : viewImmunization Dao starts");
		List<RestImmunizationModel> immunization = new ArrayList<RestImmunizationModel>();
		JsonResponse<List<RestImmunizationModel>> resp = new JsonResponse<List<RestImmunizationModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_immunization_view").setParameter("p_patId", id).getResultList();
			for (Object[] m : x) {
		
				RestImmunizationModel so = new RestImmunizationModel(m[0], m[1], m[2], m[3], m[4].toString(),m[5],m[6]);
				immunization.add(so);
				resp.setBody(immunization);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(resp);
		logger.info("Method : viewImmunization Dao ends");
		return resp;
	}

}
