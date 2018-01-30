package com.kongbig.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kongbig.model.Code;

@Repository
public interface CodeDao {
	
	boolean addCode(Code code);

	Code getCodeByCId(String code);

	List<Code> getAllCode();

	void deleteCode(String code);

}
