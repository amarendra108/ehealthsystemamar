package nirmalya.aatithya.restmodule.notice.dao;
import java.util.ArrayList;
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
import nirmalya.aatithya.restmodule.common.utils.GenerateNoticeParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.notice.dao.RestNoticeTypeMastreDao;
import nirmalya.aatithya.restmodule.notice.model.RestNoticeTypeMasterModel;

@Repository
public class RestNoticeTypeMastreDao {
	Logger logger = LoggerFactory.getLogger(RestNoticeTypeMastreDao.class);
	@Autowired
	EntityManager em;
	@Autowired
	ServerDao serverDao;
	
	// View Notice Details

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestNoticeTypeMasterModel>> viewNoticeDtlsDao() {

	logger.info("Method in Dao: viewNoticeDtlsDao starts");

	List<RestNoticeTypeMasterModel>  noticeList = new ArrayList<RestNoticeTypeMasterModel>();

	try {

	List<Object[]> x = em.createNamedStoredProcedureQuery("noticetpmstr")
	.setParameter("actionType", "viewNoticeDetails").setParameter("actionValue", "").getResultList();

	for (Object[] m : x) {

	RestNoticeTypeMasterModel noticeModel = new RestNoticeTypeMasterModel(m[0], m[1], m[2],null);
	noticeList.add(noticeModel);
	}

	} catch (Exception e) {
	e.printStackTrace();
	}
	JsonResponse<List<RestNoticeTypeMasterModel>> resp = new JsonResponse<List<RestNoticeTypeMasterModel>>();
	resp.setBody(noticeList);

	logger.info("Method in Dao: viewNoticeDtlsDao ends");
	//System.out.println("view dao======="+resp);
	return resp;
	}
	
	// Add Notice Details
	
	public ResponseEntity<JsonResponse<Object>> addNotice(RestNoticeTypeMasterModel noticerestModel) {

		logger.info("Method : addNotice starts");
		//System.out.println("noticerestModel" + noticerestModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateNoticeParameter.getNoticeParam(noticerestModel);
			//System.out.println("45677654"+values);

			if (noticerestModel.getNoticeNo() == null || noticerestModel.getNoticeNo() == "") {
				//System.out.println("Hii New");
				System.out.println("print in addEmp block");
				em.createNamedStoredProcedureQuery("noticetpmstr").setParameter("actionType", "addNotice")
						.setParameter("actionValue", values).execute();
				//System.out.println("print in addEmp block");

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("noticetpmstr").setParameter("actionType", "modifyNotice")
						.setParameter("actionValue", values).execute();

			}

		} catch(Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : addNotice ends");
		return response;
	}
	
	// Edit Notice Details
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestNoticeTypeMasterModel>> editNotice(String id) {
		logger.info("Method : editNotice starts");
		 System.out.println("############"+id); 

		RestNoticeTypeMasterModel req = new RestNoticeTypeMasterModel();
		JsonResponse<RestNoticeTypeMasterModel> resp = new JsonResponse<RestNoticeTypeMasterModel>();

		try {

			String value = "SET @p_custId='" + id + "';";
			//System.out.println("dassss"+value);

			List<Object[]> x = em.createNamedStoredProcedureQuery("noticetpmstr")
					.setParameter("actionType", "editNotice").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				RestNoticeTypeMasterModel reqnotice = new RestNoticeTypeMasterModel(m[0], m[1], m[2],null);
				System.out.println("@@@@@@@@@@@"+reqnotice);
				req=reqnotice;

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestNoticeTypeMasterModel>> response = new ResponseEntity<JsonResponse<RestNoticeTypeMasterModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editNotice ends");
		System.out.println(response);
		return response;
	}
	
	// Delete Notice Details
	
	public JsonResponse<RestNoticeTypeMasterModel> deleteNotice(String deleteId) {
		logger.info("Method : deleteCustomer starts");

		RestNoticeTypeMasterModel req = new RestNoticeTypeMasterModel();
		JsonResponse<RestNoticeTypeMasterModel> resp = new JsonResponse<RestNoticeTypeMasterModel>();

		try {
			String value = "SET  @p_custId='(" + deleteId + ")';";
			System.out.println("DELETE " + value);

			em.createNamedStoredProcedureQuery("noticetpmstr").setParameter("actionType", "deleteNotice")
					.setParameter("actionValue", value).
					execute();
			
			
			resp.setBody(req);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		
		logger.info("Method : deleteCustomer ends");
		return resp;
		
	}
}
