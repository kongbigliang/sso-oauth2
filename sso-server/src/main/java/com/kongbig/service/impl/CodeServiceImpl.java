package com.kongbig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.CodeDao;
import com.kongbig.model.Code;
import com.kongbig.service.ICodeService;

@Service
public class CodeServiceImpl implements ICodeService {
	
	@Autowired
	private CodeDao codeDao;

	@Override
	public boolean addCode(Code code) {
		return codeDao.addCode(code);
	}

	@Override
	public Code getCodeByCId(String code) {
		return codeDao.getCodeByCId(code);
	}

	@Override
	public List<Code> getAllCode() {
		return codeDao.getAllCode();
	}

	@Override
	public void deleteCode(String code) {
		codeDao.deleteCode(code);
	}

}
