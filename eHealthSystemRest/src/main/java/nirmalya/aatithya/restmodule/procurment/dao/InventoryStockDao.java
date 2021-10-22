package nirmalya.aatithya.restmodule.procurment.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DataTableRequest;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.inventory.model.InventoryGetDailyDataModel;
import nirmalya.aatithya.restmodule.inventory.model.InventoryStockDailyReportFinalModel;
import nirmalya.aatithya.restmodule.inventory.model.InventoryStockDailyReportModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryStockModel;

/*
 * @author NirmalyaLabs
 *
 */
@Repository
public class InventoryStockDao {

	Logger logger = LoggerFactory.getLogger(InventoryStockDao.class);

	@Autowired
	private EntityManager entityManager;

	@Autowired
	ServerDao serverDao;

	/**
	 * DAO Function to get all product
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<InventoryStockModel>>> getAllStockDetails(DataTableRequest request) {

		logger.info("Method : getAllStockDetails starts");

		List<InventoryStockModel> invGoodsReceiveModel = new ArrayList<InventoryStockModel>();

		Integer total = 0;
		try {

			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("inventoryStockRoutines")
					.setParameter("actionType", "getAllStockDetails").setParameter("actionValue", "").getResultList();
			if (!x.isEmpty()) {
				for (Object[] m : x) {
					InventoryStockModel itmCat = new InventoryStockModel(m[0], m[1], m[2], m[3], m[4], null, m[5], m[6],
							m[7], m[8], null, null, null, null, null, null, null, m[9]);
					invGoodsReceiveModel.add(itmCat);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<InventoryStockModel>> resp = new JsonResponse<List<InventoryStockModel>>();
		resp.setBody(invGoodsReceiveModel);
		resp.setTotal(total);
		ResponseEntity<JsonResponse<List<InventoryStockModel>>> response = new ResponseEntity<JsonResponse<List<InventoryStockModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getAllStockDetails ends");
		return response;
	}

	/*
	 * for getting daily stock report
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<InventoryStockDailyReportFinalModel>> restDailyStockReport(String data,
			String empId) {
		logger.info("Method : restDailyStockReport starts");

		InventoryStockDailyReportFinalModel dataList = new InventoryStockDailyReportFinalModel();

		List<InventoryStockDailyReportModel> dailyList = new ArrayList<InventoryStockDailyReportModel>();

		List<String> dates = null;

		JsonResponse<InventoryStockDailyReportFinalModel> resp = new JsonResponse<InventoryStockDailyReportFinalModel>();

		for (String a : data.split(",")) {
			List<InventoryGetDailyDataModel> rawData = new ArrayList<InventoryGetDailyDataModel>();
			try {
				String value = "SET @p_skuId='" + a + "',@p_empId='" + empId + "';";
				List<Object[]> x = entityManager.createNamedStoredProcedureQuery("inventoryStockRoutines")
						.setParameter("actionType", "restDailyStockReport").setParameter("actionValue", value)
						.getResultList();
				for (Object[] m : x) {
					InventoryGetDailyDataModel m2 = new InventoryGetDailyDataModel(m[0], m[1].toString(), m[2]);
					rawData.add(m2);
				}

				if (dates == null) {
					dates = rawData.stream().map(s -> s.getDates()).collect(Collectors.toList());
					dates.add(0, "");
				}

				List<Double> values = rawData.stream().map(s -> s.getValue()).collect(Collectors.toList());

				dailyList.add(new InventoryStockDailyReportModel(rawData.get(0).getItemName(), values));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dataList.setDates(dates);
		dataList.setDataList(dailyList);
		resp.setBody(dataList);
		ResponseEntity<JsonResponse<InventoryStockDailyReportFinalModel>> response = new ResponseEntity<JsonResponse<InventoryStockDailyReportFinalModel>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : restDailyStockReport ends");
		return response;
	}

}
