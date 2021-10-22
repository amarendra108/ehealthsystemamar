package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.notice.model.RestNoticeTypeMasterModel;

public class GenerateNoticeParameter {

	public static String getNoticeParam(RestNoticeTypeMasterModel noticerestModel) {
		// TODO Auto-generated method stub
		String s = "";

		if (noticerestModel.getNoticeNo() != null && noticerestModel.getNoticeNo() != "") {
			s = s + "@p_custId='" + noticerestModel.getNoticeNo() + "',";
		}

		if (noticerestModel.getNoticeType() != null && noticerestModel.getNoticeType() != "") {
			s = s + "@p_noticeType='" + noticerestModel.getNoticeType() + "',";
		}

		if (noticerestModel.getNoticeActive() != null && noticerestModel.getNoticeActive() != "") {
			s = s + "@p_noticeActive='" + noticerestModel.getNoticeActive() + "',";
		}

		if (noticerestModel.getCreatedBy() != null && noticerestModel.getCreatedBy() != "") {
			s = s + "@p_noticeCreatedBy='" + noticerestModel.getCreatedBy() + "',";
		}

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		return s;

	}
}
