package com.kongbig.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongbig.dao.DataTableDao;
import com.kongbig.model.Page;
import com.kongbig.util.JsonTools;

@Service
public class DataTableService {

	@Autowired
	private DataTableDao dataTableDao;

	public Map getData(String statement, String aoData, String param) throws Exception {
		// 解析分页参数
		Page page = new Page(aoData);
		// 检索参数
		String sSearch = null;
		List<Map> ja = JsonTools.parseJSON2List(aoData);
		for (int i = 0; i < ja.size(); i++) {
			Map obj = ja.get(i);
			if (obj.get("name").equals("sSearch"))
				sSearch = obj.get("value").toString();
		}

		// 分页查询参数
		Map queryParam = new HashMap();
		queryParam.put("start", page.getiDisplayStart());
		queryParam.put("length", page.getiDisplayLength());
		queryParam.put("sSearch", sSearch);
		// 解析参数
		Map<String, Object> jsonParam = JsonTools.parseJSON2Map(param);
		for (String key : jsonParam.keySet()) {
			queryParam.put(key, jsonParam.get(key));
		}

		// 查询列表数据
		List<Map> resultList = dataTableDao.getListForPage(statement, queryParam);

		// 条件查询记录总数
		int count = dataTableDao.getListForPageCount(statement + "Count", queryParam);

		page.setCount(count);

		// 返回界面数据
		Map map = new HashMap();
		page.pageObjToMap(map);
		map.put("aaData", resultList);

		return map;
	}

}
