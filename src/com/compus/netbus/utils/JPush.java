package com.compus.netbus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.PushPayload;

public class JPush {
	protected static final Logger LOG = LoggerFactory.getLogger(JPush.class);
	private static final String appKey = "6989000cb30b9306e72426b4";
	private static final String masterSecret = "ecb30d4700ccb0844777c22e";
	private static JPushClient jpushClient = new JPushClient(masterSecret,
			appKey);

	/*
	 * 推送消息给所有在线用户，非通知栏消息
	 */

	public static long sendPushMessageAll(String content) {
		PushPayload payload = buildPushObject_all_all_message(content);
		payload.resetOptionsApnsProduction(true);
		long msgId = 0;
		try {
			PushResult result = jpushClient.sendPush(payload);
			msgId = result.msg_id;
			LogUtils.f("JPush:sendPushMessageAll:成功推送消息：[" + content + "]");
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			LOG.info("HTTP Status: " + e.getStatus());
			msgId = e.getMsgId();
		}
		return msgId;
	}

	/*
	 * 推送通知给所有在线用户，通知栏显示
	 */
	public static long sendPushAlertAll(String content) {
		PushPayload payload = buildPushObject_all_all_alert(content);
		payload.resetOptionsApnsProduction(true);
		long msgId = 0;
		try {
			PushResult result = jpushClient.sendPush(payload);
			msgId = result.msg_id;
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			LOG.info("HTTP Status: " + e.getStatus());
			msgId = e.getMsgId();
		}
		return msgId;
	}

	/*
	 * 生成一个消息推送类
	 */

	private static PushPayload buildPushObject_all_all_message(String content) {
		return PushPayload.messageAll(content);
	}

	/*
	 * 生成一个通知栏推送类
	 */
	public static PushPayload buildPushObject_all_all_alert(String content) {
		return PushPayload.alertAll(content);
	}

}
