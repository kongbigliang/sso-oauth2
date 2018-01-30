package com.kongbig.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataTableDao {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<Map> getListForPage(String statement, Map queryParam) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		Integer start = Integer.parseInt(queryParam.get("start").toString());
		Integer length = Integer.parseInt(queryParam.get("length").toString());

		List<Map> result = sqlSession.selectList(statement, queryParam,
				new RowBounds(start, length));

		sqlSession.close();

		return result;
	}

	public int getListForPageCount(String statement, Map queryParam) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		Integer count = sqlSession.selectOne(statement, queryParam);

		sqlSession.close();

		return count;
	}

}
