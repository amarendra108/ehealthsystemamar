package nirmalya.aatithya.restmodule.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Nirmalya Labs
 *
 */
public class EnvironmentVaribles {

	@Value("${service.url.mobileView}")
	private String mobileView;

	@Value("${service.url.uploadEmployee}")
	private String fileUploadEmployee;
	
	@Value("${service.url.uploadProfile}")
	private String fileUploadProfile;
	
	@Value("${service.url.baseURL}")
	private String baseURL;
	
	@Value("${service.url.userqrCode}")
	private String userQrCode;
	
	public EnvironmentVaribles() {
		super();
		// TODO Auto-generated constructor stub
	}

	 

	public String getMobileView() {
		return mobileView;
	}

	public String getFileUploadEmployee() {
		return fileUploadEmployee;
	}

	public String getFileUploadProfile() {
		return fileUploadProfile;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getUserQrCode() {
		return userQrCode;
	}
	
	 
}
