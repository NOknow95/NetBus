package com.compus.netbus.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.compus.netbus.bean.ResponseEntity;

public class RespontHelper {

	public static final int MODE_DEFAULT = 0;
	public static final int MODE_TEST = 1;

	/**
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param object
	 *            需要返回给客户端的对象
	 * @param mode
	 *            开发模式，
	 * @return o的json格式的字符串
	 */
	public String response(HttpServletResponse response, Object object, int mode) {
		try {

			String resp = "";

			ResponseEntity responseEntity = null;

			switch (mode) {
			case MODE_DEFAULT:
				// 正常模式
				PrintWriter writer = response.getWriter();

				if (object instanceof ResponseEntity) {
					responseEntity = (ResponseEntity) object;
				} else {
					responseEntity = new ResponseEntity(object);
				}

				resp += JsonUtils.objToJson(responseEntity);
				writer.print("" + resp);
				break;

			case MODE_TEST:
				if (object instanceof ResponseEntity) {
					responseEntity = (ResponseEntity) object;
				} else {
					responseEntity = new ResponseEntity(object);
				}
				resp += JsonUtils.objToJson(responseEntity);
				break;
			}

			return resp;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void toResponse(HttpServletResponse response, Object object) {
		this.response(response, object, MODE_DEFAULT);
	}

}
