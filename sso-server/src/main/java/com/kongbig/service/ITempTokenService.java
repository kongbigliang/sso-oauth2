package com.kongbig.service;

import com.kongbig.model.ResultModel;
import com.kongbig.model.TempTokenModel;

public interface ITempTokenService {

	ResultModel addTempToken(TempTokenModel tempTokenModel);

	ResultModel checkReURI(String tempToken, String redirectURI, String returnURI);

}
