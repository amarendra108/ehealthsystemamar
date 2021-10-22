package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.customer.model.RestCustoomerNewModel;

public class GenerateCustomerNewParameter {
	public static String getAddempParam(RestCustoomerNewModel customer) {
		
		String s = "";

		if (customer.getCustId() != null && customer.getCustId() != "") {
			s = s + "@p_custId='" + customer.getCustId() + "',";
		}
		if (customer.getCustName() != null && customer.getCustName() != "") {
			s = s + "@p_custName='" + customer.getCustName() + "',";
		}
		if (customer.getCustEmail() != null && customer.getCustEmail() != "") {
			s = s + "@p_custEmail='" + customer.getCustEmail() + "',";
		}
		if (customer.getCustPhone()!= null && customer.getCustPhone() != "") {
			s = s + "@p_custPhone='" + customer.getCustPhone() + "',";
		}
		if (customer.getCustAddress()!= null && customer.getCustAddress() != "") {
			s = s + "@p_custAddress='" + customer.getCustAddress() + "',";
		}
		if (customer.getCustCity() != null && customer.getCustCity() != "") {
			s = s + "@p_custCity='" + customer.getCustCity() + "',";
		}
		if (customer.getDistrict() != null && customer.getDistrict() != "") {
			s = s + "@p_district='" + customer.getDistrict() + "',";
		}
		if (customer.getState() != null && customer.getState() != "") {
			s = s + "@p_state='" + customer.getState() + "',";
		}

		if (customer.getCountry() != null && customer.getCountry() != "") {
			s = s + "@p_country='" + customer.getCountry() + "',";
		}

		if (customer.getCustZipCode()!= null && customer.getCustZipCode() != "") {
			s = s + "@p_custZipCode='" + customer.getCustZipCode() + "',";
		}

		if (customer.getCustGSTNo() != null && customer.getCustGSTNo() != "") {
			s = s + "@p_custGSTNo='" + customer.getCustGSTNo() + "',";
		}
		if (customer.getFileCustomer()!= null && customer.getFileCustomer() != "") {
			s = s + "@p_fileCustomer='" + customer.getFileCustomer() + "',";
		}

		if (customer.getContactPerson()!= null && customer.getContactPerson() != "") {
			s = s + "@p_contactPerson='" + customer.getContactPerson() + "',";
		}
		

		if (customer.getCustActive() != null) {
			s = s + "@p_custActive=" + customer.getCustActive() + ",";
		}
		if (customer.getCustCreatedBy()!= null && customer.getCustCreatedBy() != "") {
			s = s + "@p_custCreatedBy='" + customer.getCustCreatedBy() + "',";
		}
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

}
