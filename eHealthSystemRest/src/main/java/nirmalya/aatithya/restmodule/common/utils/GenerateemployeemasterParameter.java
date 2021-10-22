package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.employee.model.EmployeeDocumentModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeAddressRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBankDetailsRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBenifitrestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeDependentRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeInsuranceDetailsrestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeWorkdetailsRestModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorDocumentModel;

public class GenerateemployeemasterParameter {

	public static String getAddempParam(ManageEmployeeRestModel employee) {

		String s = "";

		if (employee.getEmployeeId() != null && employee.getEmployeeId() != "") {
			s = s + "@p_empId='" + employee.getEmployeeId() + "',";
		}
		if (employee.getFileEmployeeimg() != null && employee.getFileEmployeeimg() != "") {
			s = s + "@p_empimg='" + employee.getFileEmployeeimg() + "',";
		}
		if (employee.getFirstName() != null && employee.getFirstName() != "") {
			s = s + "@p_firstName='" + employee.getFirstName() + "',";
		}
		if (employee.getLastName() != null && employee.getLastName() != "") {
			s = s + "@p_LastName='" + employee.getLastName() + "',";
		}
		if (employee.getGender() != null && employee.getGender() != "") {
			s = s + "@p_gender='" + employee.getGender() + "',";
		}

			if (employee.getDob() != null && employee.getDob() != "") {
				s = s + "@p_dob='" + employee.getDob() + "',";
			}
			if (employee.getBloodGroup() != null && employee.getBloodGroup() != "") {
				s = s + "@p_bloodgroup='" + employee.getBloodGroup() + "',";
			}
			if (employee.getMaritalStatus() != null && employee.getMaritalStatus() != "") {
				s = s + "@p_maratial='" + employee.getMaritalStatus() + "',";
			}
			if (employee.getNationality() != null && employee.getNationality() != "") {
				s = s + "@p_nationality='" + employee.getNationality() + "',";
			}
			if (employee.getFatherName() != null && employee.getFatherName() != "") {
				s = s + "@p_fathername='" + employee.getFatherName() + "',";
			}
			if (employee.getMotherName() != null && employee.getMotherName() != "") {
				s = s + "@p_mothername='" + employee.getMotherName() + "',";
			}
			if (employee.getMobileNo() != null && employee.getMobileNo() != "") {
				s = s + "@p_mobileNo='" + employee.getMobileNo() + "',";
			}
			if (employee.getPersonalMail() != null && employee.getPersonalMail() != "") {
				s = s + "@p_personalmail='" + employee.getPersonalMail() + "',";

			}

			if (employee.getWorkMail() != null && employee.getWorkMail() != "") {
				s = s + "@p_workmail='" + employee.getWorkMail() + "',";
			}
			if (employee.getCreatedBy() != null && employee.getCreatedBy() != "") {
				s = s + "@p_createdby='" + employee.getCreatedBy() + "',";
			}

			// address

			if (s != "") {
				s = s.substring(0, s.length() - 1);

				s = "SET " + s + ";";
			}

		return s;
	}

	public static String saveempadd(ManageEmployeeAddressRestModel manageEmployeeAddressRestModel) {

		String s = "";

		if (manageEmployeeAddressRestModel.getAddressId() != null
				&& manageEmployeeAddressRestModel.getAddressId() != "") {
			s = s + "@p_getAddressId='" + manageEmployeeAddressRestModel.getAddressId() + "',";
		}
		if (manageEmployeeAddressRestModel.getEmployeeId() != null
				&& manageEmployeeAddressRestModel.getEmployeeId() != "") {
			s = s + "@p_empId='" + manageEmployeeAddressRestModel.getEmployeeId() + "',";
		}
		if (manageEmployeeAddressRestModel.getType() != null && manageEmployeeAddressRestModel.getType() != "") {
			s = s + "@p_getType='" + manageEmployeeAddressRestModel.getType() + "',";
		}
		if (manageEmployeeAddressRestModel.getAddress() != null && manageEmployeeAddressRestModel.getAddress() != "") {
			s = s + "@p_Address='" + manageEmployeeAddressRestModel.getAddress() + "',";
		}

		if (manageEmployeeAddressRestModel.getCity() != null && manageEmployeeAddressRestModel.getCity() != "") {
			s = s + "@p_City='" + manageEmployeeAddressRestModel.getCity() + "',";
		}
		if (manageEmployeeAddressRestModel.getState() != null && manageEmployeeAddressRestModel.getState() != "") {
			s = s + "@p_State='" + manageEmployeeAddressRestModel.getState() + "',";
		}
		if (manageEmployeeAddressRestModel.getCountry() != null && manageEmployeeAddressRestModel.getCountry() != "") {
			s = s + "@p_Country='" + manageEmployeeAddressRestModel.getCountry() + "',";
		}
		if (manageEmployeeAddressRestModel.getZipCode() != null && manageEmployeeAddressRestModel.getZipCode() != "") {
			s = s + "@p_ZipCode='" + manageEmployeeAddressRestModel.getZipCode() + "',";
		}

		if (manageEmployeeAddressRestModel.getStatus() != null && manageEmployeeAddressRestModel.getStatus() != "") {
			s = s + "@p_pStatus='" + manageEmployeeAddressRestModel.getStatus() + "',";
		}
		if (manageEmployeeAddressRestModel.getCreatedBy() != null
				&& manageEmployeeAddressRestModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + manageEmployeeAddressRestModel.getCreatedBy() + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

	public static String saveempworkdetails(ManageEmployeeWorkdetailsRestModel manageEmployeeWorkdetailsRestModel) {

		String s = "";

		if (manageEmployeeWorkdetailsRestModel.getEmployeeworkId() != null
				&& manageEmployeeWorkdetailsRestModel.getEmployeeworkId() != "") {
			s = s + "@p_getEmployeeworkId='" + manageEmployeeWorkdetailsRestModel.getEmployeeworkId() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getEmployeeId() != null
				&& manageEmployeeWorkdetailsRestModel.getEmployeeId() != "") {
			s = s + "@p_empId='" + manageEmployeeWorkdetailsRestModel.getEmployeeId() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getStartDate() != null
				&& manageEmployeeWorkdetailsRestModel.getStartDate() != "") {
			s = s + "@p_gtartDate='" + manageEmployeeWorkdetailsRestModel.getStartDate() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getEndDate() != null
				&& manageEmployeeWorkdetailsRestModel.getEndDate() != "") {
			s = s + "@p_AEndDates='" + manageEmployeeWorkdetailsRestModel.getEndDate() + "',";
		}

		if (manageEmployeeWorkdetailsRestModel.getJobType() != null
				&& manageEmployeeWorkdetailsRestModel.getJobType() != "") {
			s = s + "@p_JobTypee='" + manageEmployeeWorkdetailsRestModel.getJobType() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getJobTitle() != null
				&& manageEmployeeWorkdetailsRestModel.getJobTitle() != "") {
			s = s + "@p_JobTitle='" + manageEmployeeWorkdetailsRestModel.getJobTitle() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getDepartment() != null
				&& manageEmployeeWorkdetailsRestModel.getDepartment() != "") {
			s = s + "@p_Department='" + manageEmployeeWorkdetailsRestModel.getDepartment() + "',";
		}
		
		
		if (manageEmployeeWorkdetailsRestModel.getTimesheet() != null
				&& manageEmployeeWorkdetailsRestModel.getTimesheet() != "") {
			s = s + "@p_getTimesheet='" + manageEmployeeWorkdetailsRestModel.getTimesheet() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getEmploymentStatus() != null
				&& manageEmployeeWorkdetailsRestModel.getEmploymentStatus() != "") {
			s = s + "@p_EmploymentStatu='" + manageEmployeeWorkdetailsRestModel.getEmploymentStatus() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getDegination() != null
				&& manageEmployeeWorkdetailsRestModel.getDegination() != "") {
			s = s + "@p_Deginatio='" + manageEmployeeWorkdetailsRestModel.getDegination() + "',";
		}

		if (manageEmployeeWorkdetailsRestModel.getBand() != null
				&& manageEmployeeWorkdetailsRestModel.getBand() != "") {
			s = s + "@p_Band='" + manageEmployeeWorkdetailsRestModel.getBand() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getManager() != null
				&& manageEmployeeWorkdetailsRestModel.getManager() != "") {
			s = s + "@p_Manager='" + manageEmployeeWorkdetailsRestModel.getManager() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getAnnualCTC() != null
				&& manageEmployeeWorkdetailsRestModel.getAnnualCTC() != "") {
			s = s + "@p_AnnualCTC='" + manageEmployeeWorkdetailsRestModel.getAnnualCTC() + "',";
		}
		if (manageEmployeeWorkdetailsRestModel.getCreatedBy() != null
				&& manageEmployeeWorkdetailsRestModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + manageEmployeeWorkdetailsRestModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

	public static String saveemployeedependent(ManageEmployeeDependentRestModel manageEmployeeDependentRestModel) {

		String s = "";

		if (manageEmployeeDependentRestModel.getDependentId() != null
				&& manageEmployeeDependentRestModel.getDependentId() != "") {
			s = s + "@p_depId='" + manageEmployeeDependentRestModel.getDependentId() + "',";
		}
		if (manageEmployeeDependentRestModel.getEmployeeId() != null
				&& manageEmployeeDependentRestModel.getEmployeeId() != "") {
			s = s + "@p_empId='" + manageEmployeeDependentRestModel.getEmployeeId() + "',";
		}
		if (manageEmployeeDependentRestModel.getDependentType() != null
				&& manageEmployeeDependentRestModel.getDependentType() != "") {
			s = s + "@p_dtype='" + manageEmployeeDependentRestModel.getDependentType() + "',";
		}
		if (manageEmployeeDependentRestModel.getDfirstName() != null
				&& manageEmployeeDependentRestModel.getDfirstName() != "") {
			s = s + "@p_dfrtname='" + manageEmployeeDependentRestModel.getDfirstName() + "',";
		}

		if (manageEmployeeDependentRestModel.getDlastName() != null
				&& manageEmployeeDependentRestModel.getDlastName() != "") {
			s = s + "@p_dlastname='" + manageEmployeeDependentRestModel.getDlastName() + "',";
		}
		if (manageEmployeeDependentRestModel.getDepdob() != null
				&& manageEmployeeDependentRestModel.getDepdob() != "") {
			s = s + "@p_dDob='" + manageEmployeeDependentRestModel.getDepdob() + "',";
		}
		if (manageEmployeeDependentRestModel.getGender() != null
				&& manageEmployeeDependentRestModel.getGender() != "") {
			s = s + "@p_dgender='" + manageEmployeeDependentRestModel.getGender() + "',";
		}
		if (manageEmployeeDependentRestModel.getDrelationship() != null
				&& manageEmployeeDependentRestModel.getDrelationship() != "") {
			s = s + "@p_drel='" + manageEmployeeDependentRestModel.getDrelationship() + "',";
		}

		if (manageEmployeeDependentRestModel.getCreatedBy() != null
				&& manageEmployeeDependentRestModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + manageEmployeeDependentRestModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

	public static String saveemployeebankdetails(
			ManageEmployeeBankDetailsRestModel manageEmployeeBankDetailsRestModel) {

		String s = "";

		if (manageEmployeeBankDetailsRestModel.getEbankId() != null
				&& manageEmployeeBankDetailsRestModel.getEbankId() != "") {
			s = s + "@p_ebankId='" + manageEmployeeBankDetailsRestModel.getEbankId() + "',";
		}
		if (manageEmployeeBankDetailsRestModel.getEmployeeId() != null
				&& manageEmployeeBankDetailsRestModel.getEmployeeId() != "") {
			s = s + "@p_empId='" + manageEmployeeBankDetailsRestModel.getEmployeeId() + "',";
		}
		if (manageEmployeeBankDetailsRestModel.getEbankName() != null
				&& manageEmployeeBankDetailsRestModel.getEbankName() != "") {
			s = s + "@p_EbankName='" + manageEmployeeBankDetailsRestModel.getEbankName() + "',";
		}
		if (manageEmployeeBankDetailsRestModel.getEbankAddress() != null
				&& manageEmployeeBankDetailsRestModel.getEbankAddress() != "") {
			s = s + "@p_EbankAddress='" + manageEmployeeBankDetailsRestModel.getEbankAddress() + "',";
		}

		if (manageEmployeeBankDetailsRestModel.getEbankCity() != null
				&& manageEmployeeBankDetailsRestModel.getEbankCity() != "") {
			s = s + "@p_getEbankCity='" + manageEmployeeBankDetailsRestModel.getEbankCity() + "',";
		}
		if (manageEmployeeBankDetailsRestModel.getEbankState() != null
				&& manageEmployeeBankDetailsRestModel.getEbankState() != "") {
			s = s + "@p_getEbankState='" + manageEmployeeBankDetailsRestModel.getEbankState() + "',";
		}
		if (manageEmployeeBankDetailsRestModel.getEbankCountry() != null
				&& manageEmployeeBankDetailsRestModel.getEbankCountry() != "") {
			s = s + "@p_getEbankCountry='" + manageEmployeeBankDetailsRestModel.getEbankCountry() + "',";
		}
		if (manageEmployeeBankDetailsRestModel.getEbankAccountNo() != null
				&& manageEmployeeBankDetailsRestModel.getEbankAccountNo() != "") {
			s = s + "@p_getEbankAccountNo='" + manageEmployeeBankDetailsRestModel.getEbankAccountNo() + "',";
		}
		if (manageEmployeeBankDetailsRestModel.geteIfic() != null
				&& manageEmployeeBankDetailsRestModel.geteIfic() != "") {
			s = s + "@p_geteIfic='" + manageEmployeeBankDetailsRestModel.geteIfic() + "',";
		}
		
		if (manageEmployeeBankDetailsRestModel.getBankPrimaryStatus() != null
				&& manageEmployeeBankDetailsRestModel.getBankPrimaryStatus() != "") {
				s = s + "@p_primaryStatusBank='" + manageEmployeeBankDetailsRestModel.getBankPrimaryStatus() + "',";
				} else {
				s = s + "@p_primaryStatusBank='" + 0 + "',";
				}
   
		
		
		if (manageEmployeeBankDetailsRestModel.getModule() != null && manageEmployeeBankDetailsRestModel.getModule() != "") {
			s = s + "@p_module='" + manageEmployeeBankDetailsRestModel.getModule() + "',";
			}
			if (manageEmployeeBankDetailsRestModel.getComponent() != null && manageEmployeeBankDetailsRestModel.getComponent() != "") {
			s = s + "@p_component='" + manageEmployeeBankDetailsRestModel.getComponent() + "',";
			}
			if (manageEmployeeBankDetailsRestModel.getSubcomponent()!= null && manageEmployeeBankDetailsRestModel.getSubcomponent() != "") {
			s = s + "@p_subComponent='" + manageEmployeeBankDetailsRestModel.getSubcomponent() + "',";
			}
		if (manageEmployeeBankDetailsRestModel.getCreatedBy() != null
				&& manageEmployeeBankDetailsRestModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + manageEmployeeBankDetailsRestModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}

	public static String insurancedetails(
			ManageEmployeeInsuranceDetailsrestModel manageEmployeeInsuranceDetailsrestModel) {

		String s = "";

		if (manageEmployeeInsuranceDetailsrestModel.getEinsuraneId() != null
				&& manageEmployeeInsuranceDetailsrestModel.getEinsuraneId() != "") {
			s = s + "@p_einsuranceId='" + manageEmployeeInsuranceDetailsrestModel.getEinsuraneId() + "',";
		}
		if (manageEmployeeInsuranceDetailsrestModel.getEmployeeId() != null
				&& manageEmployeeInsuranceDetailsrestModel.getEmployeeId() != "") {
			s = s + "@p_empId='" + manageEmployeeInsuranceDetailsrestModel.getEmployeeId() + "',";
		}
		if (manageEmployeeInsuranceDetailsrestModel.getEinsurancetype() != null
				&& manageEmployeeInsuranceDetailsrestModel.getEinsurancetype() != "") {
			s = s + "@p_Einsurancetype='" + manageEmployeeInsuranceDetailsrestModel.getEinsurancetype() + "',";
		}
		if (manageEmployeeInsuranceDetailsrestModel.getEinsurancecompany() != null
				&& manageEmployeeInsuranceDetailsrestModel.getEinsurancecompany() != "") {
			s = s + "@p_Einsurancecompany='" + manageEmployeeInsuranceDetailsrestModel.getEinsurancecompany() + "',";
		}

		if (manageEmployeeInsuranceDetailsrestModel.getPolicyno() != null
				&& manageEmployeeInsuranceDetailsrestModel.getPolicyno() != "") {
			s = s + "@p_einsurancepolicy='" + manageEmployeeInsuranceDetailsrestModel.getPolicyno() + "',";
		}
		if (manageEmployeeInsuranceDetailsrestModel.getEifromdate() != null
				&& manageEmployeeInsuranceDetailsrestModel.getEifromdate() != "") {
			s = s + "@p_eifromdate='" + manageEmployeeInsuranceDetailsrestModel.getEifromdate() + "',";
		}
		if (manageEmployeeInsuranceDetailsrestModel.getEitodate() != null
				&& manageEmployeeInsuranceDetailsrestModel.getEitodate() != "") {
			s = s + "@p_eitodate='" + manageEmployeeInsuranceDetailsrestModel.getEitodate() + "',";
		}
		

		if (manageEmployeeInsuranceDetailsrestModel.getCreatedBy() != null
				&& manageEmployeeInsuranceDetailsrestModel.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + manageEmployeeInsuranceDetailsrestModel.getCreatedBy() + "',";
		}
		
		
		

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
	}
	
	  public static String getAddbenParam( ManageEmployeeBenifitrestModel
			  manageEmployeeBenifitrestModel) {

		  String s = "";
		  String qItem = "";
		  
		  if(manageEmployeeBenifitrestModel.getEmployeeId() != null || manageEmployeeBenifitrestModel.getEmployeeId() != "") {
				for (int i =0; i < manageEmployeeBenifitrestModel.getBenefits().size() ; i++) {
					qItem = qItem + "(\"" + manageEmployeeBenifitrestModel.getEmployeeId() + "\",\"" + manageEmployeeBenifitrestModel.getBenefits().get(i) + "\",\"" + manageEmployeeBenifitrestModel.getCreatedby() + "\"),";
				}
				qItem = qItem.substring(0, qItem.length() - 1);


			
		}
	  s = s + "@p_benefitsData='" + qItem + "',";
	  s = s + "@p_empId='" + manageEmployeeBenifitrestModel.getEmployeeId() + "',";
	  
	  if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
	  
	  System.out.println(s);
	  return s;
	  }
	
	  public static String getEmployeeDoc( EmployeeDocumentModel
			  employeeDocumentModel) {

		  String s = "";
		  String qItem = "";
		  s = s + "@p_emId='" + employeeDocumentModel.getEmployeeId() + "',";
		  s = s + "@p_createdBy='" + employeeDocumentModel.getCreatedBy() + "',";
		  
		  for (InventoryVendorDocumentModel a : employeeDocumentModel.getDocumentList()) {
			  qItem = qItem + "(@p_emId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
			}
			if(!qItem.isEmpty()) {
				qItem = qItem.substring(0, qItem.length() - 1);
				s = s + "@p_vendorDocuments='" + qItem + "',";
			}
	  
		  if (s != "") {
				s = s.substring(0, s.length() - 1);
	
				s = "SET " + s + ";";
			}
	  
	  System.out.println(s);
	  return s;
	  }
	  
}
