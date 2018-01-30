package com.kongbig.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

/**
 * 
* 处理json的工具类.
* <br>本类为处理json的工具类
* @author leave
 */
public class JsonTools {

	/**
	 * 
	* json转换list.
	* <br>详细说明
	* @param jsonStr json字符串
	* @return
	* @return List<Map<String,Object>> list
	* @throws
	* @author slj
	* @date 2013年12月24日 下午1:08:03
	 */
	public static List parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List list = new ArrayList();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	/**
	* 
	* json转换map.
	* <br>详细说明
	* @param jsonStr json字符串
	* @return
	* @return Map<String,Object> 集合
	* @throws
	* @author slj
	*/
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		ListOrderedMap map = new ListOrderedMap();
		// System.out.println(jsonStr);
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * 
	* 通过HTTP获取JSON数据.
	* <br>通过HTTP获取JSON数据返回list
	* @param url 链接
	* @return
	* @return List<Map<String,Object>> list
	* @throws
	* @author slj
	 */
	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2List(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	* 
	* 通过HTTP获取JSON数据.
	* <br>通过HTTP获取JSON数据返回map
	* @param url 链接
	* @return
	* @return Map<String,Object> 集合
	* @throws
	* @author slj
	*/
	public static Map<String, Object> getMapByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2Map(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	* map转换json.
	* <br>详细说明
	* @param map 集合
	* @return
	* @return String json字符串
	* @throws
	* @author slj
	 */
	public static String mapToJson(Map map) {
		Gson g = new Gson();
		return g.toJson(map);
//		JSONObject jsonObject = JSONObject.fromObject(map);
//		String jsonStr = jsonObject.toString();
//		return jsonStr;
	}

	/**
	 * 将json转化为实体POJO
	 * @param jsonStr
	 * @param obj
	 * @return
	 */
	public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
		T t = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			t = objectMapper.readValue(jsonStr, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将实体POJO转化为json
	 * @param <T>
	 * @param <T>
	  * @param obj
	 * @return jsonStr
	 */
	public static String ObjToJSON(Object obj) {
		String s = new Gson().toJson(obj);
		return s;
	}

	// test
	public static void main(String[] args) {
		// String url = "http://...";
		// List<Map<String,Object>> list = getListByUrl(url);
		// System.out.println(list);
		Map<String, String> map = new HashMap();
		Map m1 = new HashMap();
		m1.put("m1", 32432432);

		Map m2 = new HashMap();
		m2.put("m2", 55555);

		// map.put("m-1", maptoJson(m1));
		// map.put("m-2", maptoJson(m2));
		// maptoJson(map);
	}
}