package com.bbt.main.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonHelper {
	// 将json字符串反序列化成字符串数�?
	public static String[] jsonStringToArray(String jsonString, String key) {
		JSONArray jsonArray = null;
		String[] arrString = null;
		try {
			if (key == null) {
				jsonArray = new JSONArray(jsonString);
			} else {

				JSONObject jsonObject = new JSONObject(jsonString);
				jsonArray = jsonObject.getJSONArray(key);
			}

			arrString = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				arrString[i] = jsonArray.getString(i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return arrString;
	}

	// 将json字符串反序列化成Map对象
	public static Map<String, Object> jsonStringToMap(String jsonString,
			String[] keyNames, String key) {
		JSONObject jsonObject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (key == null) {
				jsonObject = new JSONObject(jsonString);
				for (int i = 0; i < keyNames.length; i++) {
					map.put(keyNames[i], jsonObject.get(keyNames[i]));
				}
			} else {
				jsonObject = new JSONObject(jsonString);
				JSONObject jsonObject2 = jsonObject.getJSONObject(key);
				for (int i = 0; i < keyNames.length; i++) {
					map.put(keyNames[i], jsonObject2.get(keyNames[i]));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	// json字符串反序列化成List对象
	// 参数1：待解析的json字符串；参数2：待解析出的keys;
	public static List<Map<String, Object>> jsonStringToList(String jsonString,
			String[] keyNames, String key) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		try {
			if (key == null) {
				jsonArray = new JSONArray(jsonString);

			} else {
				jsonObject = new JSONObject(jsonString);
				jsonArray = jsonObject.getJSONArray(key);
			}

			// 遍历Json数组
			for (int i = 0; i < jsonArray.length(); i++) {
				// 获取当前Json对象
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				
				// 获取Json对象中对应的键�?�对
				for (int j = 0; j < keyNames.length; j++) {
					System.out.println("abcdefgh"+keyNames[j]);
					if (keyNames[j] == "images") {
						String url = (String) jsonObject2
								.getJSONArray("images").get(0);
						System.out.println(url);
						map.put(keyNames[j], url);
					} else {
						map.put(keyNames[j], jsonObject2.get(keyNames[j]));
					}
				}
				list.add(map);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("list"+list.size());
		return list;
	}
	// json字符串转成JSONObject对象
	public static JSONObject jsonStringToJSONObject(String jsonString,
			String key) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
			jsonObject = jsonObject.getJSONObject(key);
			return jsonObject;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// JSONObject对象转成List对象
	public static List<Map<String, Object>> jsonObjectToList(
			JSONObject jsonObject, String[] keyNames, String key) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = null;
		try {
			if (key != null) {
				jsonArray = jsonObject.getJSONArray(key);
			} else {
				return null;
			}

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();

				for (int j = 0; j < keyNames.length; j++) {
					map.put(keyNames[j], jsonObject2.get(keyNames[j]));
				}
				list.add(map);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
