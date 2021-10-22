package nirmalya.aatithya.restmodule.doctor.dao;

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
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.model.RestOpdMasterModel;
@Repository
public class OpdMasterDao {

	Logger logger = LoggerFactory.getLogger(OpdMasterDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// Rest OpdMasterview

		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestOpdMasterModel>> getOpdMasterViewDao(String id) {

			logger.info("Method : getOpdMasterViewDao starts");
			List<RestOpdMasterModel> viewOpd = new ArrayList<RestOpdMasterModel>();
			JsonResponse<List<RestOpdMasterModel>> resp = new JsonResponse<List<RestOpdMasterModel>>();
            // System.out.println("@@@@@@@@@@@@@"+id);
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_opd_details_view").setParameter("doctor_id", id).getResultList();
				for (Object[] m : x) {
			
					RestOpdMasterModel ro = new RestOpdMasterModel(m[0], m[1], m[2], m[3],m[4]);
					viewOpd.add(ro);
					resp.setBody(viewOpd);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(resp);
			logger.info("Method : getOpdMasterViewDao ends");
			return resp;
		}
		
		// add opdmaster details
		public JsonResponse<Object> saveOpdDetailsDao(RestOpdMasterModel restOpdMasterModel) {
			logger.info("Method : saveOpdDetailsDao starts");
          //System.out.println("restOpdMasterModel"+restOpdMasterModel);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			try {
				if (restOpdMasterModel.getOpdId() == null || restOpdMasterModel.getOpdId()=="") {
					
					
					
					em.createNamedStoredProcedureQuery("doctor_opd_details_add")
					        .setParameter("d_id", restOpdMasterModel.getDrId())
							.setParameter("d_opddate", restOpdMasterModel.getOpdDate())
							.setParameter("d_opdfromtime", restOpdMasterModel.getFromTime())
							.setParameter("d_opdtotime", restOpdMasterModel.getToTime())
							.setParameter("d_opdremarks", restOpdMasterModel.getRemarks())
							.execute();

      			}
					else{
					
					//	System.out.println("########update######");

						em.createNamedStoredProcedureQuery("doctor_opd_details_update")
						        .setParameter("d_opdid", restOpdMasterModel.getOpdId())
						        .setParameter("d_id", restOpdMasterModel.getDrId())
								.setParameter("d_opddate", restOpdMasterModel.getOpdDate())
								.setParameter("d_opdfromtime", restOpdMasterModel.getFromTime())
								.setParameter("d_opdtotime", restOpdMasterModel.getToTime())
								.setParameter("d_opdremarks", restOpdMasterModel.getRemarks())
								.execute();

				}

			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					System.out.println("err" + err);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			logger.info("Method : saveOpdDetailsDao ends");
			return resp;
		}
		
		
		/*
		 * Edit Opd Details
		 * 
		 */
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestOpdMasterModel>> editOpdDetails(String id) {

			logger.info("Method : editOpdDetails Dao starts");
			List<RestOpdMasterModel> ropddetails = new ArrayList<RestOpdMasterModel>();
			JsonResponse<List<RestOpdMasterModel>> resp = new JsonResponse<List<RestOpdMasterModel>>();
			
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_opd_details_edit").setParameter("opdid", id)
						.getResultList();
				for (Object[] m : x) {
					
					RestOpdMasterModel rom = new RestOpdMasterModel(m[0],m[1],m[2],m[3],m[4],null);
				ropddetails.add(rom);
					System.out.println("Rom : "+rom);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(ropddetails);
			//System.out.println("RESPP"+resp);
			logger.info("Method : editOpdDetails Dao ends");
			return resp;

		}
		
		/*
		 * Delete Opd Details
		 * 
		 */
		public ResponseEntity<JsonResponse<Object>> deleteOpdDetails(String id) {
			
			logger.info("Method : deleteOpdDetails starts");
			
			Boolean validity = true;
			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");
			
			//System.out.println("@@@@@id@@@@@"+id);
			//String sqlquery = "("+id+")";
			//System.out.println(sqlquery);
			if (validity)
				try {

					em.createNamedStoredProcedureQuery("doctor_opd_details_delete")
							.setParameter("opdid", id).execute();

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

			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
					HttpStatus.CREATED);

			logger.info("Method : deleteOpdDetails ends");
			return response;
			

//			logger.info("Method : deleteOpdDetails Dao starts");
//			List<RestOpdMasterModel> ropddetails = new ArrayList<RestOpdMasterModel>();
//			JsonResponse<List<RestOpdMasterModel>> resp = new JsonResponse<List<RestOpdMasterModel>>();
//			
//			try {
//
//				
//				em.createNamedStoredProcedureQuery("doctor_opd_details_delete").setParameter("opdid", id)
//				.execute();
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			resp.setBody(ropddetails);
//			System.out.println("RESPP"+resp);
//			logger.info("Method : editOpdDetails Dao ends");
//			return resp;

		}
		

		
}
