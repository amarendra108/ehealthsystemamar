package nirmalya.aathithya.webmodule.notice.model;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class NoticeTypeMasterModel {

		private String noticeNo;
		private String noticeType;
		private String noticeActive;
		private String createdBy;
		

		

		public String getNoticeNo() {
			return noticeNo;
		}
		public void setNoticeNo(String noticeNo) {
			this.noticeNo = noticeNo;
		}

		public String getNoticeType() {
			return noticeType;
		}
		public void setNoticeType(String noticeType) {
			this.noticeType = noticeType;
		}




		public String getNoticeActive() {
			return noticeActive;
		}




		public void setNoticeActive(String noticeActive) {
			this.noticeActive = noticeActive;
		}




		public String getCreatedBy() {
			return createdBy;
		}




		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}




		@Override
		public String toString() {
			ObjectMapper mapperObj = new ObjectMapper();
			String jsonStr;
			try {
				jsonStr = mapperObj.writeValueAsString(this);
			} catch (IOException ex) {

				jsonStr = ex.toString();
			}
			return jsonStr;
		}

	}


