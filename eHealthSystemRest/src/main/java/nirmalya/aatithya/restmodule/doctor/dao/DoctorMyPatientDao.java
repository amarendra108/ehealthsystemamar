package nirmalya.aatithya.restmodule.doctor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.model.RestViewMyPatientModel;


@Repository
public class DoctorMyPatientDao {
	Logger logger = LoggerFactory.getLogger(DoctorMyPatientDao.class);
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	@SuppressWarnings("unchecked")
	
	public JsonResponse<List<RestViewMyPatientModel>> doctorViewMyPatientDao(String userid) {
		logger.info("Method : getOpdMasterViewDao starts");
		List<RestViewMyPatientModel> viewOpd = new ArrayList<RestViewMyPatientModel>();
		JsonResponse<List<RestViewMyPatientModel>> resp = new JsonResponse<List<RestViewMyPatientModel>>();
         //System.out.println("@@@@@@@@@@@@@ :"+userid);
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_view_my_patient").setParameter("doctorid", userid).getResultList();
			for (Object[] m : x) {
		
				RestViewMyPatientModel ro = new RestViewMyPatientModel(m[0], m[1], m[2], m[3],m[4]);
				viewOpd.add(ro);
				resp.setBody(viewOpd);
			}

		} catch (Exception e) {
			String[] err = serverDao.errorProcedureCall(e);
			resp.setCode(err[0]);
			resp.setMessage(err[1]);
			e.printStackTrace();
		}
		System.out.println(resp);
		logger.info("Method : getOpdMasterViewDao ends");
		return resp;
	}
	
}
