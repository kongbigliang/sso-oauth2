package com.kongbig.dao;

import org.springframework.stereotype.Repository;

import com.kongbig.model.TempTokenModel;
@Repository
public interface TempTokenDao {

	boolean addTempToken(TempTokenModel tempTokenModel);

	TempTokenModel getTempTokenByPK(String tempToken);

}
