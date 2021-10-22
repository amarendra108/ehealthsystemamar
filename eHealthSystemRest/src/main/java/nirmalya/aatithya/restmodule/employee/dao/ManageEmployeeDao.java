package nirmalya.aatithya.restmodule.employee.dao;

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
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateReqParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateVendorRfqPAram;
import nirmalya.aatithya.restmodule.common.utils.GenerateemployeemasterParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.EmployeeDocumentModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeAddressRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBankDetailsRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBenifitrestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeDependentRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeInsuranceDetailsrestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeWorkdetailsRestModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorDocumentModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorRfqModel;
import nirmalya.aatithya.restmodule.recruitment.model.AddRecruitentModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateDetailsModel;

/**
 * @author NirmalyaLabs
 *
 */
@Repository
public class ManageEmployeeDao {

	Logger logger = LoggerFactory.getLogger(ManageEmployeeDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCountryList() {
		logger.info("Method : getCountryListemployee starts");

		List<DropDownModel> CountryList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getCountryList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				CountryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return CountryList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getstateList1() {
		logger.info("Method : getstateListemployee starts");

		List<DropDownModel> stateList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getstateList1").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getstateListForemployee ends");
		return stateList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getcityList1() {
		logger.info("Method : getcityListemployee starts");

		List<DropDownModel> cityList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getcityList1").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				cityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return cityList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getgenderList1() {
		logger.info("Method : genderTypeList starts");

		List<DropDownModel> genderTypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getgenderList1").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				genderTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return genderTypeList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getnationalityList1() {
		logger.info("Method : getnationalityList1 starts");

		List<DropDownModel> nationalityList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "nationalityList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				nationalityList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return nationalityList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getaddressList1() {
		logger.info("Method : getaddressList1 starts");

		List<DropDownModel> getaddressList1 = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getaddressList1").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getaddressList1.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return getaddressList1;
	}

	
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getemploymentType1() {
		logger.info("Method : getemploymentType1 starts");

		List<DropDownModel> employmentList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "employmentList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				employmentList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return employmentList;
	}
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getbloodgroupList1() {
		logger.info("Method : getnationalityList1 starts");

		List<DropDownModel> bloodgroupList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getbloodgroupList1").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				bloodgroupList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return bloodgroupList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBandList() {
		logger.info("Method : getnationalityList1 starts");

		List<DropDownModel> getBandList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getBandList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getBandList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return getBandList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getmaritalstatusList1() {
		logger.info("Method : getmaritalstatusList1 starts");

		List<DropDownModel> maritalstatusList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "maritalstatusList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				maritalstatusList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");
		return maritalstatusList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getdocumenttypeList1() {
		logger.info("Method : documenttypeList starts");

		List<DropDownModel> documenttypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "documentTypeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				documenttypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : documenttypeList ends");
		return documenttypeList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getJobType1() {
		logger.info("Method : getJobType1 starts");

		List<DropDownModel> jobtypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "jobtypeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jobtypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getJobType1 ends");
		return jobtypeList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDepartmentList1() {
		logger.info("Method : getDepartmentList1 starts");

		List<DropDownModel> DepartmentList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "DepartmentList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				DepartmentList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getDepartmentList1 ends");
		return DepartmentList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getTimesheetType1() {
		logger.info("Method : getTimesheetType1 starts");

		List<DropDownModel> TimesheetList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "TimesheetList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				TimesheetList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : TimesheetList ends");
		return TimesheetList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getemploymentstatusList1() {
		logger.info("Method : getemploymentstatusList1 starts");

		List<DropDownModel> employmentstatusList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getemploymentstatusList1").setParameter("actionValue", "")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				employmentstatusList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getemploymentstatusList1 ends");
		return employmentstatusList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getJobType2() {
		logger.info("Method : getJobType2 starts");

		List<DropDownModel> jobtypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "jobtypeList2").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jobtypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : jobtypeList2 ends");
		return jobtypeList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBenefits() {
		logger.info("Method : getBenefits starts");

		List<DropDownModel> benefitsList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "benefitsList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				benefitsList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : benefitsList ends");
		return benefitsList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBankNameList() {
		logger.info("Method : getBankNameList starts");

		List<DropDownModel> getBankNameList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getBankNameList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getBankNameList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : benefitsList ends");
		return getBankNameList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> dependentTypeList() {
		logger.info("Method : dependentTypeList starts");

		List<DropDownModel> getDependentTypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "dependentTypeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getDependentTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : dependentTypeList ends");
		return getDependentTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> relationshipList() {
		logger.info("Method : relationshipList starts");

		List<DropDownModel> getRelationshipList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "relationshipList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getRelationshipList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : relationshipList ends");
		return getRelationshipList;
	}
	
	@SuppressWarnings("unchecked") public List<DropDownModel> EmployeeList() {
	 logger.info("Method : EmployeeList starts");
	List<DropDownModel> getBankNameList = new ArrayList<DropDownModel>();
	 try { List<Object[]> x =
	 em.createNamedStoredProcedureQuery("employeeMasterRoutines")
	 .setParameter("actionType", "EmployeeList").setParameter("actionValue",
	 "").getResultList();
	 for (Object[] m : x) { DropDownModel dropDownModel = new DropDownModel(m[0],
	 m[1]); getBankNameList.add(dropDownModel); }
	 } catch (Exception e) { e.printStackTrace(); }
	 System.out.println(getBankNameList);
	 logger.info("Method : EmployeeList ends"); return getBankNameList; }
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> insuranceCompanyList() {
		logger.info("Method : insuranceCompanyList starts");

		List<DropDownModel> insuranceCompanyList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "insuranceCompanyList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				insuranceCompanyList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : insuranceCompanyList ends");
		return insuranceCompanyList;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getManager(String id){
		
		logger.info("Method : getManager starts");
		
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		List<DropDownModel> model = new ArrayList<DropDownModel>();
		
		String values = "SET @p_empId='" + id +"';";
		System.out.println(values);
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "getManager").setParameter("actionValue", values)
					.getResultList();
			for (Object[] m : x) {

				DropDownModel item = new DropDownModel(m[0],m[1]);
				model.add(item);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		resp.setBody(model);
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("MANAGER"+response);
		logger.info("Method : getManager ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ManageEmployeeRestModel>> saveemployeeMaster(ManageEmployeeRestModel employee) {
		logger.info("Method : saveemployeeMaster starts");

		JsonResponse<ManageEmployeeRestModel> resp = new JsonResponse<ManageEmployeeRestModel>();
		
		List<ManageEmployeeRestModel> newemp = new ArrayList<ManageEmployeeRestModel>();

		
			try {
				String values = GenerateemployeemasterParameter.getAddempParam(employee);
				
				if (employee.getEmployeeId() != null && employee.getEmployeeId() != "") {
					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "modifyemp").setParameter("actionValue", values)
							.execute();
					
				} else {

					List<Object> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "addemp").setParameter("actionValue", values).getResultList();

					ManageEmployeeRestModel candidate = new ManageEmployeeRestModel(x.get(0),null,null,null,null,null,null,null,null,null,
							null,null,null,null,null,null);
					resp.setBody(candidate);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<ManageEmployeeRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeRestModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveEmpMaster ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ManageEmployeeRestModel>>> viewemppersonal() {
		logger.info("Method : viewemppersonal starts");

		List<ManageEmployeeRestModel> viewemppersonal = new ArrayList<ManageEmployeeRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "viewemppersonal").setParameter("actionValue", "").getResultList();
			for (Object[] m : x) {

				ManageEmployeeRestModel vendorLocation = new ManageEmployeeRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14],null);
				viewemppersonal.add(vendorLocation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeRestModel>> resp = new JsonResponse<List<ManageEmployeeRestModel>>();
		resp.setBody(viewemppersonal);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeRestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : viewemppersonal ends");
		return response;
	}

	public ResponseEntity<JsonResponse<ManageEmployeeAddressRestModel>> saveemployeeaddress(
			ManageEmployeeAddressRestModel manageEmployeeAddressRestModel) {
		logger.info("Method : saveemployeeaddress starts");

		JsonResponse<ManageEmployeeAddressRestModel> resp = new JsonResponse<ManageEmployeeAddressRestModel>();

			try {
				String values = GenerateemployeemasterParameter.saveempadd(manageEmployeeAddressRestModel);
				System.out.println("values " + values);
				if (manageEmployeeAddressRestModel.getAddressId() != null && manageEmployeeAddressRestModel.getAddressId() != "") {
					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "modifyempadd").setParameter("actionValue", values)
							.execute();
				} else {

					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "addempaddress").setParameter("actionValue", values)
							.execute();
	
				}

			} catch (Exception e) {
				
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<ManageEmployeeAddressRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeAddressRestModel>>(
				resp, HttpStatus.CREATED);
		
		logger.info("Method : saveVendorLocationMaster ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ManageEmployeeAddressRestModel>>> viewEmployeeadd(String id) {
		logger.info("Method : getRequistionview starts");

		List<ManageEmployeeAddressRestModel> viewEmployeeadd = new ArrayList<ManageEmployeeAddressRestModel>();

		try {

			String value = "SET @p_empId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "viewEmployeeadd").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
			
				ManageEmployeeAddressRestModel vendorLocation = new ManageEmployeeAddressRestModel( m[0], m[1],
						m[2], m[3], m[4], m[5], m[6], m[7],m[8] ,m[9],m[10],m[11],m[12], null);
				viewEmployeeadd.add(vendorLocation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeAddressRestModel>> resp = new JsonResponse<List<ManageEmployeeAddressRestModel>>();
		resp.setBody(viewEmployeeadd);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeAddressRestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeAddressRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getRequistionview ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> deleteAddress(String id) {
		logger.info("Method : deleteAddress starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
	
			try {
				String values = "SET @p_id='(" + id + ")'";
					em.createNamedStoredProcedureQuery("employeeMasterRoutines").setParameter("actionType", "deleteAddress")
					.setParameter("actionValue", values).execute();
					
					
			} catch

			(Exception e) {
				e.printStackTrace();

			}
			

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		logger.info("Method : deleteAddress ends");
		return response;
	}

	public ResponseEntity<JsonResponse<ManageEmployeeWorkdetailsRestModel>> saveemployeeworkdetails(
			ManageEmployeeWorkdetailsRestModel manageEmployeeWorkdetailsRestModel) {
		logger.info("Method : saveemployeeworkdetails starts");

		JsonResponse<ManageEmployeeWorkdetailsRestModel> resp = new JsonResponse<ManageEmployeeWorkdetailsRestModel>();
	
			try {
				String values = GenerateemployeemasterParameter.saveempworkdetails(manageEmployeeWorkdetailsRestModel);
				System.out.println("values " + values);
				if (manageEmployeeWorkdetailsRestModel.getEmployeeworkId() != null
						&& manageEmployeeWorkdetailsRestModel.getEmployeeworkId() != "") {
					
					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "modifyempworkdetails").setParameter("actionValue", values)
							.execute();
					
				} else {

					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "addempworkdetails").setParameter("actionValue", values)
							.execute();
	
				}

			} catch (Exception e) {
			
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<ManageEmployeeWorkdetailsRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeWorkdetailsRestModel>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : saveemployeeworkdetails ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ManageEmployeeWorkdetailsRestModel>>> viewEmployeework(String id) {
		logger.info("Method : getworkkkk starts");

		List<ManageEmployeeWorkdetailsRestModel> viewEmployeework = new ArrayList<ManageEmployeeWorkdetailsRestModel>();

		try {

			String value = "SET @p_empId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "viewEmployework").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {

				ManageEmployeeWorkdetailsRestModel viewEmployee = new ManageEmployeeWorkdetailsRestModel( m[0],
						m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9],m[10],m[11],m[12],  m[13],m[14],m[15],m[16],m[17],m[18],null);
				viewEmployeework.add(viewEmployee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeWorkdetailsRestModel>> resp = new JsonResponse<List<ManageEmployeeWorkdetailsRestModel>>();
		resp.setBody(viewEmployeework);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeWorkdetailsRestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeWorkdetailsRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getworkkkk ends");
		return response;
	}

	
	public ResponseEntity<JsonResponse<ManageEmployeeDependentRestModel>> saveemployeedependent(
			ManageEmployeeDependentRestModel manageEmployeeDependentRestModel) {
		logger.info("Method : saveemployeedependent starts");

		JsonResponse<ManageEmployeeDependentRestModel> resp = new JsonResponse<ManageEmployeeDependentRestModel>();
		

		List<ManageEmployeeDependentRestModel> saveemployeedependent = new ArrayList<ManageEmployeeDependentRestModel>();

			try {
				String values = GenerateemployeemasterParameter.saveemployeedependent(manageEmployeeDependentRestModel);
				if (manageEmployeeDependentRestModel.getDependentId() != null
						&& manageEmployeeDependentRestModel.getDependentId() != "") {

					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "modifydeptnt").setParameter("actionValue", values)
							.execute();
					
				} else {

					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "saveemployeedependent").setParameter("actionValue", values)
							.execute();
					
				}

				resp.setBody(saveemployeedependent.get(0));
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<ManageEmployeeDependentRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeDependentRestModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveemployeedependent ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ManageEmployeeDependentRestModel>>> viewempdepent(String id) {
		logger.info("Method : viewempdepent starts");

		List<ManageEmployeeDependentRestModel> viewempdepent = new ArrayList<ManageEmployeeDependentRestModel>();

		try {

			String value = "SET @p_empId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "viewempdepent").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				ManageEmployeeDependentRestModel viewEmployeesss = new ManageEmployeeDependentRestModel( m[0],
						m[1], m[2], m[3], m[4], m[5],m[6],m[7],m[8],m[9],m[10], null);
				viewempdepent.add(viewEmployeesss);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeDependentRestModel>> resp = new JsonResponse<List<ManageEmployeeDependentRestModel>>();
		resp.setBody(viewempdepent);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeDependentRestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeDependentRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getworkkkk ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> deletedependent(String id) {
		logger.info("Method : deletedependent starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
	
			try {
				String values = "SET @p_id='(" + id + ")'";
					em.createNamedStoredProcedureQuery("employeeMasterRoutines").setParameter("actionType", "deletedependent")
					.setParameter("actionValue", values).execute();
					
					
			} catch

			(Exception e) {
				e.printStackTrace();

			}
			

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : deletedependent ends");
		return response;
	}
	
	
	public ResponseEntity<JsonResponse<Object>> deletebank(String id) {
		logger.info("Method : deletebank starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
	
			try {
				String values = "SET @p_id='(" + id + ")'";
					em.createNamedStoredProcedureQuery("employeeMasterRoutines").setParameter("actionType", "deletebank")
					.setParameter("actionValue", values).execute();
					
					
			} catch

			(Exception e) {
				e.printStackTrace();

			}
			

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		logger.info("Method : deletebank ends");
		return response;
	}
	
		@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> saveemployeebank(
			ManageEmployeeBankDetailsRestModel manageEmployeeBankDetailsRestModel) {
		logger.info("Method : saveemployeedependent starts");

		JsonResponse<ManageEmployeeBankDetailsRestModel> resp = new JsonResponse<ManageEmployeeBankDetailsRestModel>();
		

		List<ManageEmployeeBankDetailsRestModel> saveemployeedependent = new ArrayList<ManageEmployeeBankDetailsRestModel>();

			try {
				String values = GenerateemployeemasterParameter
						.saveemployeebankdetails(manageEmployeeBankDetailsRestModel);
				if (manageEmployeeBankDetailsRestModel.getEbankId() != null
						&& manageEmployeeBankDetailsRestModel.getEbankId() != "") {

					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "modifybankdetails").setParameter("actionValue", values)
							.execute();
				} else {
					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "saveemployeebankdetails").setParameter("actionValue", values)
							.execute();
				}

				resp.setBody(saveemployeedependent.get(0));
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveemployeedependent ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>> viewebank(String id) {
		logger.info("Method : viewempdepent starts");

		List<ManageEmployeeBankDetailsRestModel> viewempdepent = new ArrayList<ManageEmployeeBankDetailsRestModel>();

		try {

			String value = "SET @p_empId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "viewebank").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				/*
				 * Object sDate = null; if (m[7] != null) { sDate =
				 * DateFormatter.returnStringDate(m[7]); } Object Date = null; if (m[8] != null)
				 * { Date = DateFormatter.returnStringDate(m[8]); }
				 */

				ManageEmployeeBankDetailsRestModel viewEmployeesss = new ManageEmployeeBankDetailsRestModel( m[0],
						m[1], m[2], m[3], m[4], m[5], m[6], m[7],m[8] ,m[9],m[10],m[11],m[12], null, null);
				viewempdepent.add(viewEmployeesss);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeBankDetailsRestModel>> resp = new JsonResponse<List<ManageEmployeeBankDetailsRestModel>>();
		resp.setBody(viewempdepent);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getworkkkk ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ManageEmployeeInsuranceDetailsrestModel>> saveempinsurance(
			ManageEmployeeInsuranceDetailsrestModel manageEmployeeInsuranceDetailsrestModel) {
		logger.info("Method : saveempinsurance starts");

		JsonResponse<ManageEmployeeInsuranceDetailsrestModel> resp = new JsonResponse<ManageEmployeeInsuranceDetailsrestModel>();

		List<ManageEmployeeInsuranceDetailsrestModel> saveemployeedependent = new ArrayList<ManageEmployeeInsuranceDetailsrestModel>();
		
			try {
				String values = GenerateemployeemasterParameter
						.insurancedetails(manageEmployeeInsuranceDetailsrestModel);
				if (manageEmployeeInsuranceDetailsrestModel.getEinsuraneId() != null
						&& manageEmployeeInsuranceDetailsrestModel.getEinsuraneId() != "") {

					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "modifyinsurence").setParameter("actionValue", values)
							.execute();

				} else {
					em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "insurancedetailsssss").setParameter("actionValue", values)
							.execute();
				}

				resp.setBody(saveemployeedependent.get(0));
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<ManageEmployeeInsuranceDetailsrestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeInsuranceDetailsrestModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveempinsurance ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ManageEmployeeInsuranceDetailsrestModel>>> vieweinsurance(String id) {
		logger.info("Method : vieweinsurance starts");

		List<ManageEmployeeInsuranceDetailsrestModel> viewempdepent = new ArrayList<ManageEmployeeInsuranceDetailsrestModel>();

		try {

			String value = "SET @p_empId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "vieweinsurance").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				
				ManageEmployeeInsuranceDetailsrestModel viewEmployeesss = new ManageEmployeeInsuranceDetailsrestModel(
				 m[0],m[1], m[2], m[3], m[4],m[5],m[6],m[7],m[8], null);
				viewempdepent.add(viewEmployeesss);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeInsuranceDetailsrestModel>> resp = new JsonResponse<List<ManageEmployeeInsuranceDetailsrestModel>>();
		resp.setBody(viewempdepent);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeInsuranceDetailsrestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeInsuranceDetailsrestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : vieweinsurance ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ManageEmployeeRestModel>>> viewmanageemployeemaste() {
		logger.info("Method : getVendorList starts");

		List<ManageEmployeeRestModel> viewVendor = new ArrayList<ManageEmployeeRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "viewemppersonal").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				/* Object sDate = null; */
				/*
				 * if (m[7] != null) { sDate = DateFormatter.returnStringDate(m[7]); }
				 */
				/*
				 * Object Date = null; if (m[8] != null) { Date =
				 * DateFormatter.returnStringDate(m[8]); }
				 */

				ManageEmployeeRestModel vendorMasterModel = new ManageEmployeeRestModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], null,null);
				viewVendor.add(vendorMasterModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeRestModel>> resp = new JsonResponse<List<ManageEmployeeRestModel>>();
		resp.setBody(viewVendor);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeRestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getVendorList ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ManageEmployeeRestModel>> editmanageEmployeemasterById(String id) {
		logger.info("Method : editmanageEmployeemasterById starts");

		JsonResponse<ManageEmployeeRestModel> resp = new JsonResponse<ManageEmployeeRestModel>();
		List<ManageEmployeeRestModel> editemp = new ArrayList<ManageEmployeeRestModel>();

		try {

			String value = "SET @P_employeeId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "editemp").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				/*
				 * if (m[7] != null) { sDate = DateFormatter.returnStringDate(m[7]); }
				 */
				/*
				 * Object Date = null; if (m[8] != null) { Date =
				 * DateFormatter.returnStringDate(m[8]); }
				 */

				ManageEmployeeRestModel manageEmployeeRestModel = new ManageEmployeeRestModel(m[0], m[1], m[2], m[3],
						m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], null,null);
				editemp.add(manageEmployeeRestModel);
			}
			resp.setBody(editemp.get(0));
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

		ResponseEntity<JsonResponse<ManageEmployeeRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeRestModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : editVendorById ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> deleteinsurance(String id) {
		logger.info("Method : deleteinsurance starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
	
			try {
				String values = "SET @p_id='(" + id + ")'";
					em.createNamedStoredProcedureQuery("employeeMasterRoutines").setParameter("actionType", "deleteinsurance")
					.setParameter("actionValue", values).execute();
					
					
			} catch

			(Exception e) {
				e.printStackTrace();

			}
			

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		logger.info("Method : deleteinsurance ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> deletework(String id) {
		logger.info("Method : deletework starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
	
			try {
				String values = "SET @p_id='(" + id + ")'";
				System.out.println(values);
					em.createNamedStoredProcedureQuery("employeeMasterRoutines").setParameter("actionType", "deletework")
					.setParameter("actionValue", values).execute();
					
					
			} catch

			(Exception e) {
				e.printStackTrace();

			}
			

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		logger.info("Method : deletework ends");
		return response;
	}


	
	 
public ResponseEntity<JsonResponse<Object>> savebenifit(ManageEmployeeBenifitrestModel req) {
	logger.info("Method : savebenifit starts");
	
	JsonResponse<Object> resp = new JsonResponse<Object>();
	
		try {
			String values = GenerateemployeemasterParameter.getAddbenParam(req);

			if (req.getEbenifitId() == null || req.getEbenifitId() == "") {
				em.createNamedStoredProcedureQuery("employeeMasterRoutines").setParameter("actionType", "addbenifit")
				.setParameter("actionValue", values).execute();
				
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}

	ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
			HttpStatus.CREATED);

	logger.info("Method : savebenifit ends");
	
	
	return response;
}

@SuppressWarnings("unchecked")
public ResponseEntity<JsonResponse<List<ManageEmployeeBenifitrestModel>>> viewBenefits(String id){
	
logger.info("Method : viewBenefits starts");
	
	JsonResponse<List<ManageEmployeeBenifitrestModel>> resp = new JsonResponse<List<ManageEmployeeBenifitrestModel>>();
	List<ManageEmployeeBenifitrestModel> editemp = new ArrayList<ManageEmployeeBenifitrestModel>();	
		try {
			String values = "SET @p_empId='" + id + "';";
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
					.setParameter("actionType", "viewBenefits").setParameter("actionValue", values).getResultList();

			for (Object[] m : x) {
				
				ManageEmployeeBenifitrestModel benefits = new ManageEmployeeBenifitrestModel(m[0], null,m[1],null);
				editemp.add(benefits);
			}
			resp.setBody(editemp);
		} catch(Exception e) {
			e.printStackTrace();
		}

	ResponseEntity<JsonResponse<List<ManageEmployeeBenifitrestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeBenifitrestModel>>>(resp,
			HttpStatus.CREATED);

	logger.info("Method : viewBenefits ends");
	
	return response;
	
}

@SuppressWarnings("unchecked")
public ResponseEntity<JsonResponse<List<DropDownModel>>> getTeam(String id){
	
	logger.info("Method : getManager starts");
	
	JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
	List<DropDownModel> model = new ArrayList<DropDownModel>();
	
	String values = "SET @p_empId='" + id +"';";
	System.out.println(values);
	try {
		List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
				.setParameter("actionType", "getTeam").setParameter("actionValue", values)
				.getResultList();
		for (Object[] m : x) {

			DropDownModel item = new DropDownModel(m[0],m[1]);
			model.add(item);
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
	resp.setBody(model);
	ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
			resp, HttpStatus.CREATED);
	System.out.println("TM==="+response);
	logger.info("Method : getManager ends");
	return response;
}

public ResponseEntity<JsonResponse<Object>> addDoc(
		EmployeeDocumentModel employeeDocumentModel) {
	logger.info("Method : addDoc starts");
	JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String value = GenerateemployeemasterParameter.getEmployeeDoc(employeeDocumentModel);
			if (employeeDocumentModel.getEmployeeId() != null
					&& employeeDocumentModel.getEmployeeId() != "") {

				em.createNamedStoredProcedureQuery("employeeMasterRoutines")
						.setParameter("actionType", "modifyEmpDoc").setParameter("actionValue", value)
						.execute();
			} 
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

	ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
			resp, HttpStatus.CREATED);
	logger.info("Method : add item addDoc ends");
	return response;
}


@SuppressWarnings("unchecked")
public List<InventoryVendorDocumentModel> viewEmpDocEdit(String id) {
	logger.info("Method : viewEmpDocEdit starts");
	
	List<InventoryVendorDocumentModel> docList = new ArrayList<InventoryVendorDocumentModel>();
	List<InventoryVendorDocumentModel> docList1 = new ArrayList<InventoryVendorDocumentModel>();
	String values = "SET @p_empId='" + id + "';";
	
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
						.setParameter("actionType", "getEmpDocs").setParameter("actionValue", values)
						.getResultList();
				for (Object[] m : x1) {

					InventoryVendorDocumentModel dropDownModel = new InventoryVendorDocumentModel(m[0], m[1],null);
					docList.add(dropDownModel);
				}
				
				List<DropDownModel> documenttypeList = new ArrayList<DropDownModel>();

				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("employeeMasterRoutines")
							.setParameter("actionType", "documentTypeList").setParameter("actionValue", "").getResultList();

					for (Object[] m : x) {
						DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
						documenttypeList.add(dropDownModel);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				if(docList.size() > 0) {
					docList.get(0).setDrop(documenttypeList);
				} else {
					InventoryVendorDocumentModel abc = new InventoryVendorDocumentModel();
					abc.setDrop(documenttypeList);
					docList1.add(abc);
					return docList1;
				}
				
			} catch (Exception e) {

			}
			
			System.out.println(docList);
	logger.info("Method : viewEmpDocEdit ends");
	return docList;
}


} 

