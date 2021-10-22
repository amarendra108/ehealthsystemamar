package nirmalya.aatithya.restmodule.patient.dao;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.patient.model.RestEHealthCardModel;

@Repository
public class RestEHealthCardDao {
	Logger logger = LoggerFactory.getLogger(RestEHealthCardDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	public RestEHealthCardModel getEhealthCardDetailsDao(String id) {
		logger.info("Method : getEhealthCardDetailsDao starts");
		RestEHealthCardModel patientdetails = new RestEHealthCardModel();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_ehealthcard_details")
					.setParameter("patid", id).getResultList();
			System.out.println("patidpatidpatid"+id);

			for (Object[] m : x) {
				System.out.println("patientdetails"+Arrays.toString(m));
				
				patientdetails = new RestEHealthCardModel(m[0], m[1],m[2], m[3],m[4],m[5],m[6]);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getEhealthCardDetailsDao ends");

		return patientdetails;
	}
	/******************************  EHealthCard Details   ***************************/

}

