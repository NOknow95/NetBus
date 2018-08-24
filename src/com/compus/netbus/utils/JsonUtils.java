package com.compus.netbus.utils;

import java.util.List;

import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.WaitPot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {

	private static Gson gson = null;

	private JsonUtils() {

	}

	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	public static <T> String listToJson(List<T> list) {
		String json = getGson().toJson(list);
		return json;
	}

	public static <T> String beanToJson(Object o, Class<T> t) {
		String json = getGson().toJson(o, t);
		return json;
	}

	public static String objToJson(Object o) {
		String json = getGson().toJson(o);
		return json;
	}

	public static <T> T jsonToBean(String jsonString, Class<T> typeClass) {
		T bean = getGson().fromJson(jsonString, typeClass);
		return bean;
	}

	/**
	 * json解析成对象的list
	 * 
	 * @param result
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> jsonToList(String result, Class<T> tClass) {
		return gson.fromJson(result, new TypeToken<List<T>>() {
		}.getType());
	}

}
