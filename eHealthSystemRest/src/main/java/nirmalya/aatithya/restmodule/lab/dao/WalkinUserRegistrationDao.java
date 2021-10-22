package nirmalya.aatithya.restmodule.lab.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.RestUserRegistrationModel;

@Repository
public class WalkinUserRegistrationDao {

	Logger logger = LoggerFactory.getLogger(WalkinUserRegistrationDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestUserRegistrationModel>> getAddressDao(String userId) {
		logger.info("Method : getAddressDao starts");

		RestUserRegistrationModel address = new RestUserRegistrationModel();
		JsonResponse<RestUserRegistrationModel> resp = new JsonResponse<RestUserRegistrationModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_walkinUser_getAddress")
					.setParameter("userId", userId).getResultList();
			for (Object[] m : x) {
				RestUserRegistrationModel regd = new RestUserRegistrationModel(null,m[0],m[1],m[2], m[3]);
				address = regd;
			}
			resp.setBody(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<RestUserRegistrationModel>> response = new ResponseEntity<JsonResponse<RestUserRegistrationModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method : getAddressDao ends")	;
		return response;
	}
}
