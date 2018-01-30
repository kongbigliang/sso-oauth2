package com.kongbig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.TempTokenDao;
import com.kongbig.model.ResultModel;
import com.kongbig.model.TempTokenModel;
import com.kongbig.service.ITempTokenService;
import com.kongbig.util.StringUtilsss;

@Service
public class TempTokenServiceImpl implements ITempTokenService {

	@Autowired
	private TempTokenDao tempTokenDao;

	@Override
	public ResultModel addTempToken(TempTokenModel tempTokenModel) {
		ResultModel result = null;
		if (tempTokenDao.addTempToken(tempTokenModel)) {
			result = new ResultModel("001", "success");
			return result;
		}
		result = new ResultModel("000", "fail");
		return result;
	}

	@Override
	public ResultModel checkReURI(String tempToken, String redirectURI, String returnURI) {
		ResultModel result = null;
		TempTokenModel tt = tempTokenDao.getTempTokenByPK(tempToken);
		// 保证重定向地址的ip、目标地址和原来的一致.
		if (tt != null && tt.getRedirectURIIP().equals(StringUtilsss.getIP(redirectURI))
				&& tt.getReturnURI().equals(returnURI)) {
			result = new ResultModel("001", "success");
			return result;
		}
		result = new ResultModel("000", "fail");
		return result;
	}

}
