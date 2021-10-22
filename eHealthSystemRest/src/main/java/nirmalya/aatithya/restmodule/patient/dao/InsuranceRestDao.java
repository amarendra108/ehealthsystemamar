package nirmalya.aatithya.restmodule.patient.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.ReimbursementModel;
import nirmalya.aatithya.restmodule.patient.model.InsuranceRestModel;
import nirmalya.aatithya.restmodule.patient.model.LifeStyleHistoryModel;
import nirmalya.aatithya.restmodule.patient.model.PatientHistoryModel;
@Repository
public class InsuranceRestDao {
	
	Logger logger = LoggerFactory.getLogger(InsuranceRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	/*
	 * Add Life Style
	 */
	public JsonResponse<Object> patientInsuranceAdd(InsuranceRestModel insuranceRestModel) {
		logger.info("Method : saveInsurance starts");
		System.out.println("######" + insuranceRestModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			/*if (insuranceRestModel.getInsuranceId()!= null ) {
				em.createNamedStoredProcedureQuery("patient_insurance_add")
				.setParameter("p_patientid", insuranceRestModel.getPatientId())
				.setParameter("p_insurancecompany", insuranceRestModel.getInsuranceCompany())
				//.setParameter("p_companyContactNo", insuranceRestModel.getCompanyContactNo())
				.setParameter("p_insurancetype", insuranceRestModel.getInsuranceType())
				.setParameter("p_healthinsurancetype", insuranceRestModel.getHealthInsuranceType())
				.setParameter("p_thirdpartyadmin", insuranceRestModel.getThirdPartyAdmin())
				.setParameter("p_policyno", insuranceRestModel.getPolicyNo())
				.setParameter("p_premiumamount", insuranceRestModel.getPremiumAmount())
				.setParameter("p_policystartdate", insuranceRestModel.getPolicyStartDate())
				.setParameter("p_policyenddate", insuranceRestModel.getPolicyEndDate())
				.setParameter("p_totalinsuranceamount", insuranceRestModel.getTotalInsuranceAmount())
				.setParameter("p_sumassuredamount", insuranceRestModel.getSumAssuredAmount())
				.setParameter("p_premiumduedate", insuranceRestModel.getPremiumDueDate())
				.execute();*/
				
			//}
			

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
		logger.info("Method : saveInsurance ends");
		return resp;
	}
	
	/*
	 * For Patient Insurance View
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<InsuranceRestModel>> getAllPatientInsuranceView() {
		logger.info("Method : getAllPatientInsuranceView Dao starts");

		List<InsuranceRestModel> getAllemployee = new ArrayList<InsuranceRestModel>();
		JsonResponse<List<InsuranceRestModel>> resp = new JsonResponse<List<InsuranceRestModel>>();
		try {						
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_insurance_view").getResultList();
			for (Object[] m : x) {
				Object PRMIUMDATE = null;
				if (m[4] != null) {
					PRMIUMDATE = m[4].toString();
				}			
				
				Integer totalAmount = null;
				if(m[3]!=null) {
					BigDecimal bd = (BigDecimal) m[3];
					totalAmount = bd.intValue();
				}
			System.out.println("getAllemployee" + getAllemployee);
			
			InsuranceRestModel viewdemo = new InsuranceRestModel(m[0], null, m[1], null, m[2], null, null, null,
					null, null, totalAmount, null, PRMIUMDATE);
			 								
		getAllemployee.add(viewdemo);
				/*
				 * Object insuranceId, Object patientId, Object insuranceCompany, Object
				 * companyContactNo, Object insuranceType, Object healthInsuranceType, Object
				 * thirdPartyAdmin, Object policyNo, Object premiumAmount, Object
				 * policyStartDate, Object policyEndDate, Object totalInsuranceAmount, Object
				 * sumAssuredAmount, Object premiumDueDate
				 */
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : getAllPatientInsuranceView Dao ends");
		return resp;
	}
	
	/*
	 * Edit Insurance
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<InsuranceRestModel>> editPatientInsurance(String id) {

		logger.info("Method : editManageGRN Dao starts");
		List<InsuranceRestModel> purchaseOrder = new ArrayList<InsuranceRestModel>();
		JsonResponse<List<InsuranceRestModel>> resp = new JsonResponse<List<InsuranceRestModel>>();
		
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("edit_patient_insurance").setParameter("insuranceID", id)
					.getResultList();
			for (Object[] m : x) {
				Object date = null;
				if (m[7] != null) {
					date = m[7].toString();
				}
					
				Object edste = null;
				if (m[8] != null) {
					edste = m[8].toString();
				}	
				Object PRMIUMDATE = null;
				if (m[11] != null) {
					PRMIUMDATE = m[11].toString();
				}			
				
				Integer premamt = null;
				if(m[6]!=null) {
					BigDecimal bd = (BigDecimal) m[6];
					premamt = bd.intValue();
				}
				Integer insamt = null;
				if(m[9]!=null) {
					BigDecimal bd = (BigDecimal) m[9];
					insamt = bd.intValue();
				}
				
				Integer assueredamt = null;
				if(m[10]!=null) {
					BigDecimal bd = (BigDecimal) m[10];
					assueredamt = bd.intValue();
				}
				
				InsuranceRestModel leave = new InsuranceRestModel(m[0], null, m[1], m[2], m[3], m[4], m[5],
						premamt, date, edste, insamt, assueredamt, PRMIUMDATE);
			purchaseOrder.add(leave);
				System.out.println("leave"+leave);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(purchaseOrder);
		System.out.println("RESPP"+resp);
		logger.info("Method : editManageGRN Dao ends");
		return resp;

	}
	
	/*
	 * Add Life Style
	 */
	public JsonResponse<Object> addInsurance(InsuranceRestModel lifeStyleHistoryModel) {
		logger.info("Method : addInsurance starts");
		System.out.println("######" + lifeStyleHistoryModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println("#######"+lifeStyleHistoryModel.getInsuranceId());
		try {
			if (lifeStyleHistoryModel.getInsuranceId() == null ) {
				//System.out.println("######add######");
				em.createNamedStoredProcedureQuery("insurance_add")
						.setParameter("p_patientIds", lifeStyleHistoryModel.getPatientId())
						.setParameter("p_insuranceCompany", lifeStyleHistoryModel.getInsuranceCompany())
						.setParameter("p_insuranceType", lifeStyleHistoryModel.getInsuranceType())
						.setParameter("p_healthInsuranceType", lifeStyleHistoryModel.getHealthInsuranceType())
						.setParameter("p_thirdPartyAdmin", lifeStyleHistoryModel.getThirdPartyAdmin())
						.setParameter("p_policyNo", lifeStyleHistoryModel.getPolicyNo())
						.setParameter("p_premiumAmount", lifeStyleHistoryModel.getPremiumAmount())
						.setParameter("p_policyStartDate", lifeStyleHistoryModel.getPolicyStartDate())
						.setParameter("p_policyEndDate", lifeStyleHistoryModel.getPolicyEndDate())
						.setParameter("p_totalInsuranceAmount", lifeStyleHistoryModel.getTotalInsuranceAmount())
						.setParameter("p_sumAssuredAmount", lifeStyleHistoryModel.getSumAssuredAmount())
						.setParameter("p_premiumDueDate", lifeStyleHistoryModel.getPremiumDueDate())
						
						.execute();
			}else {
				//System.out.println("######modify#######");
				em.createNamedStoredProcedureQuery("insurance_modify")
				.setParameter("p_isurnceno", lifeStyleHistoryModel.getInsuranceId())
				.setParameter("p_patientIds", lifeStyleHistoryModel.getPatientId())
				.setParameter("p_insuranceCompany", lifeStyleHistoryModel.getInsuranceCompany())
				.setParameter("p_insuranceType", lifeStyleHistoryModel.getInsuranceType())
				.setParameter("p_healthInsuranceType", lifeStyleHistoryModel.getHealthInsuranceType())
				.setParameter("p_thirdPartyAdmin", lifeStyleHistoryModel.getThirdPartyAdmin())
				.setParameter("p_policyNo", lifeStyleHistoryModel.getPolicyNo())
				.setParameter("p_premiumAmount", lifeStyleHistoryModel.getPremiumAmount())
				.setParameter("p_policyStartDate", lifeStyleHistoryModel.getPolicyStartDate())
				.setParameter("p_policyEndDate", lifeStyleHistoryModel.getPolicyEndDate())
				.setParameter("p_totalInsuranceAmount", lifeStyleHistoryModel.getTotalInsuranceAmount())
				.setParameter("p_sumAssuredAmount", lifeStyleHistoryModel.getSumAssuredAmount())
				.setParameter("p_premiumDueDate", lifeStyleHistoryModel.getPremiumDueDate())
				
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
		logger.info("Method : addInsurance ends");
		return resp;
	}
	
}
