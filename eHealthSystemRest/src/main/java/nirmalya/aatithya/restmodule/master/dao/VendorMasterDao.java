package nirmalya.aatithya.restmodule.master.dao;

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
import nirmalya.aatithya.restmodule.common.utils.ActivitylogModel;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateLocationMasterParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateVendorMasterParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateemployeemasterParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBankDetailsRestModel;
import nirmalya.aatithya.restmodule.master.controller.VendorMasterRestController;
import nirmalya.aatithya.restmodule.master.model.LocationMasterModel;
import nirmalya.aatithya.restmodule.master.model.VendorContactMasterModel;
import nirmalya.aatithya.restmodule.master.model.VendorDocumentMaster;
import nirmalya.aatithya.restmodule.master.model.VendorLocationMasterModel;
import nirmalya.aatithya.restmodule.master.model.VendorMasterModel;

/**
 * @author NirmalyaLabs
 *
 */
@Repository
public class VendorMasterDao {
	Logger logger = LoggerFactory.getLogger(VendorMasterDao.class);
	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<VendorMasterModel>> saveVendorMaster(VendorMasterModel vendorMasterModel) {
		logger.info("Method : saveVendorMaster starts");

		Boolean validity = true;
		JsonResponse<VendorMasterModel> resp = new JsonResponse<VendorMasterModel>();
		resp.setMessage("");
		resp.setCode("");

		List<VendorMasterModel> newVendor = new ArrayList<VendorMasterModel>();

		if (vendorMasterModel.getCode() == null || vendorMasterModel.getCode() == "") {
			resp.setMessage("Code Required");
			validity = false;
		} else if (vendorMasterModel.getVendorName() == null || vendorMasterModel.getVendorName() == "") {
			resp.setMessage("Vendor Name Required");
			validity = false;
		} else if (vendorMasterModel.getCategory() == null || vendorMasterModel.getCategory() == "") {
			resp.setMessage("Category Required");
			validity = false;
		} else if (vendorMasterModel.getCategoryType() == null || vendorMasterModel.getCategoryType() == "") {
			resp.setMessage("Category Type Required");
			validity = false;
		} else if (vendorMasterModel.getComapanyOverview() == null || vendorMasterModel.getComapanyOverview() == "") {
			resp.setMessage("Companny Overview Required");
			validity = false;
		}

		if (validity)
			try {
				String values = GenerateVendorMasterParameter.saveVendorMaster(vendorMasterModel);

				if (vendorMasterModel.getVendorId() != null && vendorMasterModel.getVendorId() != "") {
					// em.createNamedStoredProcedureQuery("locationMasterRoutines")
					// .setParameter("actionType", "modifyLocation").setParameter("actionValue",
					// values).execute();

					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "modifyVendor").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						Object createDate = null;
						Object establishDate = null;

						if (m[7] != null) {
							createDate = m[7].toString();
						}
						if (m[17] != null) {
							createDate = m[17].toString();
						}
						VendorMasterModel vendorMasterModelData = new VendorMasterModel(m[0], m[1], m[2], m[3], m[4],
								m[5], m[6], createDate, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16],
								null, null, null, establishDate, m[18], m[19], m[20], m[21]);
						newVendor.add(vendorMasterModelData);
					}
				} else {

					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "addVendor").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						Object createDate = null;

						if (m[7] != null) {
							createDate = m[7].toString();
						}

						VendorMasterModel vendorMasterModelData = new VendorMasterModel(m[0], m[1], m[2], m[3], m[4],
								m[5], m[6], createDate, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16],
								null, null, null, null, null, null, null, null);
						newVendor.add(vendorMasterModelData);
					}
				}

				resp.setBody(newVendor.get(0));
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

		ResponseEntity<JsonResponse<VendorMasterModel>> response = new ResponseEntity<JsonResponse<VendorMasterModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveVendorMaster ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCategoryList() {
		logger.info("Method : getCategoryList starts");

		List<DropDownModel> categoryList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "getCategoryList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				categoryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCategoryList ends");
		return categoryList;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<VendorLocationMasterModel>> saveVendorLocationMaster(
			VendorLocationMasterModel vendorLocationMasterModel) {
		logger.info("Method : saveVendorMaster starts");

		Boolean validity = true;
		JsonResponse<VendorLocationMasterModel> resp = new JsonResponse<VendorLocationMasterModel>();
		resp.setMessage("");
		resp.setCode("");

		List<VendorLocationMasterModel> newVendor = new ArrayList<VendorLocationMasterModel>();

		if (vendorLocationMasterModel.getVendorLocationName() == null
				|| vendorLocationMasterModel.getVendorLocationName() == "") {
			resp.setMessage("Location Name Required");
			validity = false;
		} else if (vendorLocationMasterModel.getVendorLocationType() == null
				|| vendorLocationMasterModel.getVendorLocationType() == "") {
			resp.setMessage("Vendor Location Type Required");
			validity = false;

		} else if (vendorLocationMasterModel.getVendorLocAddress() == null
				|| vendorLocationMasterModel.getVendorLocAddress() == "") {
			resp.setMessage("Vendor Location Address Required");
			validity = false;

		} /*
			 * else if (vendorLocationMasterModel.getVendorCountry()== null ||
			 * vendorLocationMasterModel.getVendorCountry() == "") {
			 * resp.setMessage("Vendor Location Country Required"); validity = false;
			 * 
			 * } else if (vendorLocationMasterModel.getVendorState()== null ||
			 * vendorLocationMasterModel.getVendorState() == "") {
			 * resp.setMessage("Vendor Location State Required"); validity = false; } else
			 * if (vendorLocationMasterModel.getVendorCity()== null ||
			 * vendorLocationMasterModel.getVendorCity() == "") {
			 * resp.setMessage("Location City Required"); validity = false; }
			 */

		if (validity)
			try {
				String values = GenerateVendorMasterParameter.saveVendorLocationMaster(vendorLocationMasterModel);

				if (vendorLocationMasterModel.getVendorLocationId() != null
						&& vendorLocationMasterModel.getVendorLocationId() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "modifyVendorLocation").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						Object sDate = null;
						if (m[11] != null) {
							sDate = DateFormatter.returnStringDate(m[11]);
						}

						VendorLocationMasterModel vendorLocation = new VendorLocationMasterModel(m[0], m[1], m[2], m[3],
								m[4], m[5], m[6], m[7], m[8], m[9], m[10], sDate);
						newVendor.add(vendorLocation);
					}
				} else {

					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "addVendorLocation").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						Object sDate = null;
						if (m[11] != null) {
							sDate = DateFormatter.returnStringDate(m[11]);
						}

						VendorLocationMasterModel vendorLocation = new VendorLocationMasterModel(m[0], m[1], m[2], m[3],
								m[4], m[5], m[6], m[7], m[8], m[9], m[10], sDate);
						newVendor.add(vendorLocation);
					}
				}

				resp.setBody(newVendor.get(0));
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

		ResponseEntity<JsonResponse<VendorLocationMasterModel>> response = new ResponseEntity<JsonResponse<VendorLocationMasterModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveVendorLocationMaster ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<VendorLocationMasterModel>>> getVendorLocationview(String id) {
		logger.info("Method : getRequistionview starts");

		List<VendorLocationMasterModel> viewVendorLocation = new ArrayList<VendorLocationMasterModel>();

		try {
			String value = "SET @p_vendorId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "viewVendorLocation").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object sDate = null;
				if (m[11] != null) {
					sDate = m[11].toString();
				}

				VendorLocationMasterModel vendorLocation = new VendorLocationMasterModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6], m[7], m[8], m[9], m[10], sDate);
				viewVendorLocation.add(vendorLocation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<VendorLocationMasterModel>> resp = new JsonResponse<List<VendorLocationMasterModel>>();
		resp.setBody(viewVendorLocation);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<VendorLocationMasterModel>>> response = new ResponseEntity<JsonResponse<List<VendorLocationMasterModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getRequistionview ends");
		return response;
	}

	public ResponseEntity<JsonResponse<List<VendorMasterModel>>> getVendorList() {
		logger.info("Method : getVendorList starts");

		List<VendorMasterModel> viewVendor = new ArrayList<VendorMasterModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "viewVendor").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object sDate = null;
				if (m[7] != null) {
					sDate = m[7].toString();
				}
				/*
				 * Object Date = null; if (m[8] != null) { Date =
				 * DateFormatter.returnStringDate(m[8]); }
				 */

				VendorMasterModel vendorMasterModel = new VendorMasterModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						sDate, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16], null, null, null, null,
						null, null, null, null);
				viewVendor.add(vendorMasterModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<VendorMasterModel>> resp = new JsonResponse<List<VendorMasterModel>>();
		resp.setBody(viewVendor);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<VendorMasterModel>>> response = new ResponseEntity<JsonResponse<List<VendorMasterModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getVendorList ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<VendorLocationMasterModel>> editVendorLoactionById(String id) {
		logger.info("Method : editVendorLoactionById starts");

		JsonResponse<VendorLocationMasterModel> resp = new JsonResponse<VendorLocationMasterModel>();
		List<VendorLocationMasterModel> newVendor = new ArrayList<VendorLocationMasterModel>();

		try {

			String value = "SET @P_vendorId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "editVendorLocation").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				Object sDate = null;
				if (m[11] != null) {
					sDate = DateFormatter.returnStringDate(m[11]);
				}

				VendorLocationMasterModel vendorLocation = new VendorLocationMasterModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6], m[7], m[8], m[9], m[10], sDate);
				newVendor.add(vendorLocation);
			}

			resp.setBody(newVendor.get(0));
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

		ResponseEntity<JsonResponse<VendorLocationMasterModel>> response = new ResponseEntity<JsonResponse<VendorLocationMasterModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : editVendorLoactionById ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> deleteVendorLocation(String id, String simpleid, String vendorId,
			String createdBy) {
		logger.info("Method : deleteVendorLocation starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		if (validity)
			try {

				String value = "SET @P_ModifiedBy='" + createdBy + "',@P_Id='" + simpleid + "',@vendorId='" + vendorId
						+ "', @P_VendorLocId='(" + id + ")';";

				em.createNamedStoredProcedureQuery("vendorMasterRoutines").setParameter("actionType", "deleteVendorLoc")
						.setParameter("actionValue", value).execute();

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

		logger.info("Method : deleteVendorLocation ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<VendorMasterModel>> editVendorById(String id) {
		logger.info("Method : editVendorById starts");

		JsonResponse<VendorMasterModel> resp = new JsonResponse<VendorMasterModel>();
		List<VendorMasterModel> newVendor = new ArrayList<VendorMasterModel>();

		try {

			String value = "SET @P_vendorId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "editVendor").setParameter("actionValue", value).getResultList();
			Object sDate = null;
			Object establishDate = null;
			for (Object[] m : x) {

				if (m[7] != null) {
					sDate = m[7].toString();
				}
				if (m[10] != null) {
					establishDate = m[10].toString();
				}
				/*
				 * Object Date = null; if (m[8] != null) { Date =
				 * DateFormatter.returnStringDate(m[8]); }
				 */

				VendorMasterModel vendorMasterModel = new VendorMasterModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						sDate, m[8], m[9], null, null, null, null, null, null, null, null, null, null, establishDate,
						m[11], m[12], m[13], m[14]);
				newVendor.add(vendorMasterModel);
			}
			resp.setBody(newVendor.get(0));
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

		ResponseEntity<JsonResponse<VendorMasterModel>> response = new ResponseEntity<JsonResponse<VendorMasterModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : editVendorById ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<VendorContactMasterModel>> saveVendorContactMaster(
			VendorContactMasterModel vendorContactMasterModel) {
		logger.info("Method : saveVendorMaster starts");

		Boolean validity = true;
		JsonResponse<VendorContactMasterModel> resp = new JsonResponse<VendorContactMasterModel>();
		resp.setMessage("");
		resp.setCode("");

		List<VendorContactMasterModel> newVendor = new ArrayList<VendorContactMasterModel>();

		if (vendorContactMasterModel.getContactName() == null || vendorContactMasterModel.getContactName() == "") {
			resp.setMessage("Contact Name Required");
			validity = false;
		} else if (vendorContactMasterModel.getPhone() == null || vendorContactMasterModel.getPhone() == "") {
			resp.setMessage("Contanct Phone Required");
			validity = false;

		} else if (vendorContactMasterModel.getEmail() == null || vendorContactMasterModel.getEmail() == "") {
			resp.setMessage("Email Required");
			validity = false;

		} /*
			 * else if (vendorLocationMasterModel.getVendorCountry()== null ||
			 * vendorLocationMasterModel.getVendorCountry() == "") {
			 * resp.setMessage("Vendor Location Country Required"); validity = false;
			 * 
			 * } else if (vendorLocationMasterModel.getVendorState()== null ||
			 * vendorLocationMasterModel.getVendorState() == "") {
			 * resp.setMessage("Vendor Location State Required"); validity = false; } else
			 * if (vendorLocationMasterModel.getVendorCity()== null ||
			 * vendorLocationMasterModel.getVendorCity() == "") {
			 * resp.setMessage("Location City Required"); validity = false; }
			 */

		if (validity)
			try {
				String values = GenerateVendorMasterParameter.saveVendorContactMaster(vendorContactMasterModel);

				if (vendorContactMasterModel.getContactId() != null && vendorContactMasterModel.getContactId() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "modifyContact").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						Object sDate = null;
						if (m[10] != null) {
							sDate = DateFormatter.returnStringDate(m[10]);
						}

						VendorContactMasterModel vendorContactMasterModelData = new VendorContactMasterModel(m[0], m[1],
								m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], sDate, m[11]);
						newVendor.add(vendorContactMasterModelData);
					}
				} else {

					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "addContact").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {
						Object sDate = null;
						if (m[10] != null) {
							sDate = DateFormatter.returnStringDate(m[10]);
						}

						VendorContactMasterModel vendorContactMasterModelData = new VendorContactMasterModel(m[0], m[1],
								m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], sDate, m[11]);
						newVendor.add(vendorContactMasterModelData);
					}
				}

				resp.setBody(newVendor.get(0));
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

		ResponseEntity<JsonResponse<VendorContactMasterModel>> response = new ResponseEntity<JsonResponse<VendorContactMasterModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveVendorLocationMaster ends");
		return response;
	}

	public ResponseEntity<JsonResponse<List<VendorContactMasterModel>>> getVendorContact(String id) {
		logger.info("Method : getVendorContact starts");

		List<VendorContactMasterModel> viewVendorContact = new ArrayList<VendorContactMasterModel>();

		try {
			String value = "SET @p_vendorId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "viewVendorContact").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object sDate = null;
				if (m[10] != null) {
					sDate = m[10].toString();
				}

				VendorContactMasterModel vendorContactMasterModelData = new VendorContactMasterModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7], m[8], m[9], sDate, m[11]);
				viewVendorContact.add(vendorContactMasterModelData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<VendorContactMasterModel>> resp = new JsonResponse<List<VendorContactMasterModel>>();
		resp.setBody(viewVendorContact);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<VendorContactMasterModel>>> response = new ResponseEntity<JsonResponse<List<VendorContactMasterModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getVendorContact ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<VendorContactMasterModel>> editVendorContactById(String id) {
		logger.info("Method : editVendorContactById starts");

		JsonResponse<VendorContactMasterModel> resp = new JsonResponse<VendorContactMasterModel>();
		List<VendorContactMasterModel> viewVendorContact = new ArrayList<VendorContactMasterModel>();

		try {

			String value = "SET @P_contactId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "editContact").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object sDate = null;
				if (m[10] != null) {
					sDate = DateFormatter.returnStringDate(m[10]);
				}

				VendorContactMasterModel vendorContactMasterModelData = new VendorContactMasterModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7], m[8], m[9], sDate, null);
				viewVendorContact.add(vendorContactMasterModelData);
			}
			resp.setBody(viewVendorContact.get(0));
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

		ResponseEntity<JsonResponse<VendorContactMasterModel>> response = new ResponseEntity<JsonResponse<VendorContactMasterModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : editVendorContactById ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDocumentTypeList() {
		logger.info("Method : getDocumentTypeList starts");

		List<DropDownModel> documentTypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "getDocumentTypeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				documentTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCategoryList ends");
		return documentTypeList;
	}

	/*
	 * for fowardedReq
	 */
	public ResponseEntity<JsonResponse<Object>> saveVendorDocumentMaster(
			List<VendorDocumentMaster> vendorDocumentMaster) {

		logger.info("Method in Dao: saveVendorDocumentMaster starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {

			String value = GenerateVendorMasterParameter.addVendorDocument(vendorDocumentMaster);

			List<DropDownModel> dropDownModel = new ArrayList<DropDownModel>();
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "addMultiDoc").setParameter("actionValue", value).getResultList();
			if (!x.isEmpty()) {
				for (Object[] m : x) {

					DropDownModel data = new DropDownModel(m[0].toString(), m[1]);
					dropDownModel.add(data);
					resp.setBody(dropDownModel);
				}

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

		if (resp.getMessage() == null) {
			resp.setMessage("success");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp, responseHeaders,
				HttpStatus.CREATED);

		logger.info("Method in Dao: forwardPara ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> saveemployeebankdetailsVendor(
			ManageEmployeeBankDetailsRestModel manageEmployeeBankDetailsRestModel) {
		logger.info("Method : saveemployeebankdetails starts");

		Boolean validity = true;
		JsonResponse<ManageEmployeeBankDetailsRestModel> resp = new JsonResponse<ManageEmployeeBankDetailsRestModel>();
		/*
		 * resp.setMessage(""); resp.setCode("");
		 */

		List<ManageEmployeeBankDetailsRestModel> saveemployeebankdetails = new ArrayList<ManageEmployeeBankDetailsRestModel>();

		if (manageEmployeeBankDetailsRestModel.getEbankName() == null
				|| manageEmployeeBankDetailsRestModel.getEbankName() == "") {
			resp.setMessage("E Bank Name");
			validity = false;
		} else if (manageEmployeeBankDetailsRestModel.getEbankAddress() == null
				|| manageEmployeeBankDetailsRestModel.getEbankAddress() == "") {
			resp.setMessage("E address");
			validity = false;

			/*
			 * } else if (manageEmployeeBankDetailsRestModel.getEbankCity() == null ||
			 * manageEmployeeBankDetailsRestModel.getEbankCity() == "") {
			 * resp.setMessage("E city"); validity = false;
			 * 
			 * } else if (manageEmployeeBankDetailsRestModel.getEbankState() == null ||
			 * manageEmployeeBankDetailsRestModel.getEbankState() == "") {
			 * resp.setMessage("E city"); validity = false;
			 * 
			 * } else if (manageEmployeeBankDetailsRestModel.getEbankCountry() == null ||
			 * manageEmployeeBankDetailsRestModel.getEbankCountry() == "") {
			 * resp.setMessage("E Country"); validity = false;
			 */
		} else if (manageEmployeeBankDetailsRestModel.getEbankAccountNo() == null
				|| manageEmployeeBankDetailsRestModel.getEbankAccountNo() == "") {
			resp.setMessage("E BankaccountNO");
			validity = false;
		} else if (manageEmployeeBankDetailsRestModel.geteIfic() == null
				|| manageEmployeeBankDetailsRestModel.geteIfic() == "") {
			resp.setMessage("E IFIC");
			validity = false;
		}

		if (validity)
			try {
				String values = GenerateemployeemasterParameter
						.saveemployeebankdetails(manageEmployeeBankDetailsRestModel);

				if (manageEmployeeBankDetailsRestModel.getEbankId() != null
						&& manageEmployeeBankDetailsRestModel.getEbankId() != "") {

					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "modifyBank").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						ManageEmployeeBankDetailsRestModel saveemployeebankdetailsssss = new ManageEmployeeBankDetailsRestModel(
								m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], null, m[9], null, null, null,
								null);
						saveemployeebankdetails.add(saveemployeebankdetailsssss);
					}
				} else {

					List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
							.setParameter("actionType", "addBankDetails").setParameter("actionValue", values)
							.getResultList();
					for (Object[] m : x) {

						ManageEmployeeBankDetailsRestModel saveemployeebankdetailsssss = new ManageEmployeeBankDetailsRestModel(
								m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], null, m[9], null, null, null,
								null);
						saveemployeebankdetails.add(saveemployeebankdetailsssss);
					}
				}

				resp.setBody(saveemployeebankdetails.get(0));

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

		ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : saveemployeedependent ends");

		return response;
	}

	public ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>> viewVendorBankDetails(String id) {
		logger.info("Method : viewVendorBankDetails starts");

		List<ManageEmployeeBankDetailsRestModel> manageEmployeeBankDetailsRestModel = new ArrayList<ManageEmployeeBankDetailsRestModel>();

		try {
			String value = "SET @p_vendorId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "viewVendorBank").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object sDate = null;
				if (m[9] != null) {
					sDate = m[9].toString();
				}

				ManageEmployeeBankDetailsRestModel saveemployeebankdetailsssss = new ManageEmployeeBankDetailsRestModel(
						m[0], m[1], m[2], null, m[3], m[4], null, m[5], null, m[6], null, m[7], m[8], sDate, m[10]);
				manageEmployeeBankDetailsRestModel.add(saveemployeebankdetailsssss);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageEmployeeBankDetailsRestModel>> resp = new JsonResponse<List<ManageEmployeeBankDetailsRestModel>>();
		resp.setBody(manageEmployeeBankDetailsRestModel);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>> response = new ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : viewVendorBankDetails ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> editVendorBankById(String id) {
		logger.info("Method : editVendorBankById starts");

		JsonResponse<ManageEmployeeBankDetailsRestModel> resp = new JsonResponse<ManageEmployeeBankDetailsRestModel>();
		List<ManageEmployeeBankDetailsRestModel> viewVendorBank = new ArrayList<ManageEmployeeBankDetailsRestModel>();

		try {

			String value = "SET @P_bankId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "editBank").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				ManageEmployeeBankDetailsRestModel saveemployeebankdetailsssss = new ManageEmployeeBankDetailsRestModel(
						m[0], m[1], m[2], null, m[3], m[4], null, m[5], null, m[6], null, m[7], m[8], null, m[9]);
				viewVendorBank.add(saveemployeebankdetailsssss);
			}
			resp.setBody(viewVendorBank.get(0));
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

		ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> response = new ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : editVendorBankById ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public List<ActivitylogModel> getActivityLogVendor(String id) {
		logger.info("Method : getActivityLog starts");
		List<ActivitylogModel> activitylogModelList = new ArrayList<ActivitylogModel>();
		try {
			String value = "SET @p_vendorId='" + id + "'";
			System.out.println("actvity vendor" + value);
			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "getActivityLogVendor").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object oa = null;
				if (m[6] != null) {
					oa = m[6].toString();
				}
				ActivitylogModel activitylogModel = new ActivitylogModel(m[0], m[1], m[2], m[3], m[4], m[5], oa);
				activitylogModelList.add(activitylogModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getActivityLog ends");
		return activitylogModelList;
	}

	public ResponseEntity<JsonResponse<Object>> deleteVendor(String id, String simpleid, String createdBy) {
		logger.info("Method : deleteVendor starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		if (validity)
			try {

				String value = "SET @P_ModifiedBy='" + createdBy + "',@P_Id='" + simpleid + "', @P_VendorId='(" + id
						+ ")';";

				em.createNamedStoredProcedureQuery("vendorMasterRoutines").setParameter("actionType", "deleteVendor")
						.setParameter("actionValue", value).execute();

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

		logger.info("Method : deleteVendor ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> createVendorWiseUser(VendorContactMasterModel id) {
		logger.info("Method : createVendorWiseUser starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		if (validity)
			try {

				String value = "SET @P_CreatedBy='" + id.getCreatedBy() + "',@P_ContactId='" + id.getContactId()
						+ "', @P_Vendorid='" + id.getVendorId() + "', @P_userName='" + id.getContactName()
						+ "', @P_Phone='" + id.getPhone() + "', @P_Email='" + id.getEmail() + "', @P_Pwd='"
						+ id.getPwd() + "';";

				em.createNamedStoredProcedureQuery("vendorMasterRoutines")
						.setParameter("actionType", "createVendorWiseUser").setParameter("actionValue", value)
						.execute();

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
		logger.info("Method : createVendorWiseUser ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> resetPasswordVendor(VendorContactMasterModel id) {
		logger.info("Method : resetPasswordVendor starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		if (validity)
			try {

				String value = "SET @P_CreatedBy='" + id.getCreatedBy() + "',@P_ContactId='" + id.getContactId()
						+ "', @P_Vendorid='" + id.getVendorId() + "', @P_Pwd='" + id.getPwd() + "';";

				em.createNamedStoredProcedureQuery("vendorMasterRoutines")
						.setParameter("actionType", "resetPasswordVendor").setParameter("actionValue", value).execute();

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
		logger.info("Method : resetPasswordVendor ends");
		return response;
	}

	/**
	 * @return a particular vendor by its id
	 */
	public ResponseEntity<JsonResponse<List<VendorMasterModel>>> getVendorListByVendor(String vendorId) {
		logger.info("Method : getVendorListByVendor starts");

		List<VendorMasterModel> viewVendor = new ArrayList<VendorMasterModel>();

		try {
			String value = "SET @p_vendor='" + vendorId + "';";
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("vendorMasterRoutines")
					.setParameter("actionType", "viewVendorById").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object sDate = null;
				if (m[7] != null) {
					sDate = m[7].toString();
				}
				VendorMasterModel vendorMasterModel = new VendorMasterModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						sDate, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16], null, null, null, null,
						null, null, null, null);
				viewVendor.add(vendorMasterModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<VendorMasterModel>> resp = new JsonResponse<List<VendorMasterModel>>();
		resp.setBody(viewVendor);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<VendorMasterModel>>> response = new ResponseEntity<JsonResponse<List<VendorMasterModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getVendorListByVendor ends");
		return response;
	}
}
