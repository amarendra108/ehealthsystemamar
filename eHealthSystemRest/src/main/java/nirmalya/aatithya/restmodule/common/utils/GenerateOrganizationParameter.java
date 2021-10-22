package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.master.model.RestOrganizationMasterModel;

public class GenerateOrganizationParameter {

public static String saveOrganizationCategory(RestOrganizationMasterModel organization) {
		
		String s = "";
		
		if(organization.getOrganizationalId()!=null && organization.getOrganizationalId()!="") {
			s = s + "@p_organizationId='" + organization.getOrganizationalId() + "',";
		}
		if(organization.getOrganizationalName()!=null && organization.getOrganizationalName()!="") {
			s = s + "@p_organizationName='" + organization.getOrganizationalName() + "',";
		}
		if(organization.getOrganizationalDesc()!=null && organization.getOrganizationalDesc()!="") {
			s = s + "@p_organizationDesc='" + organization.getOrganizationalDesc() + "',";
		}
		if(organization.getParentId()!=null && organization.getParentId()!="") {
			s = s + "@p_parentId='" + organization.getParentId() + "',";
		}
		if(organization.getCreatedBy()!=null && organization.getCreatedBy()!="") {
			s = s + "@p_createdBy='" + organization.getCreatedBy() + "',";
		}
		if(organization.getOrganizationalStatus()!=null && organization.getOrganizationalStatus()!="") {
			s = s + "@p_isActive='" + organization.getOrganizationalStatus() + "',";
		} else {
			s = s + "@p_isActive='" + 0 + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println(s);
		
		return s;
	}

}
