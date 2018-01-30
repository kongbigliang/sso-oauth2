package com.kongbig.service;

import java.util.List;

import com.kongbig.model.Code;

public interface ICodeService {

	boolean addCode(Code code);

	Code getCodeByCId(String code);

	List<Code> getAllCode();

	void deleteCode(String code);

}
