package com.kongbig.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kongbig.service.impl.DataTableService;

@Controller
@RequestMapping("/DataTableController")
public class DataTableController {

	@Resource
	private DataTableService dataTableService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getListForPage.do")
	@ResponseBody
	public Map getTableListForPage(String statement, String aoData, String param) throws Exception {
		Map value = dataTableService.getData(statement, aoData, param);
		return value;
	}

}
