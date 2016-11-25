/*
 * 短信发送接口 
 * 
 * 对接阿里云短信业务功能。需要先在阿里云后台开通短线功能。以及生成accessKey和accessSecret。发送短信前要新建短信签名和短信模板。
 * 为了保护accessKey和accessSecret，本程序将accessKey和accessSecret内置在程序中。也可以其他安全方式存储accessKey和accessSecret。
 * 
 * @author TTFC
 * @version 0.0.1
 * @see https://help.aliyun.com/document_detail/44366.html?spm=5176.doc44364.6.570.nVCRvV
 */
package com.ttfc.soa.dubbo.proxy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ttfc.soa.dubbo.helper.Validater;
import com.ttfc.soa.dubbo.proxy.domain.WResponse;
import com.ttfc.soa.dubbo.proxy.domain.WResult;
import com.ttfc.soa.dubbo.proxy.service.SMSService;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.google.gson.Gson;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Service("smsServiceProxy")
@Path("sms")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class SMSServiceImplProxy implements SMSService {

	/**
	 * 发送短信 发送短信须接受如下json格式：
	 * {"signCode":"W_JIEKUAN","receiveMobile":"13660439550","params":{"code":
	 * "123678624"}} 参数说明： - signCode： 本次短信发送所要执行的识别码 - receiveMobile： 接收者手机号码 -
	 * params： 短信模板中要替换的变量
	 * 
	 * @author TTFC
	 * @param data 发送短信必须要的参数。
	 * @return WResult 发送成功或者失败。发送成功只是代表程序上没有问题，但不代表该次发送已到达接收者。实际发送情况以阿里云后台展示为准。
	 */
	@Override
	@POST
	@Path("sendSMS")
	public WResult sendSMS(HashMap<String, Object> data) {
		Gson gson = new Gson();
		String signCode = String.valueOf(data.get("signCode"));
		String recnum = String.valueOf(data.get("receiveMobile")); 
		if (!Validater.isMobileNO(recnum)) {
			return WResponse.fail(WResponse.Action.NOT_ACCEPTABLE).msg("手机号不合格").build();
		}
		String paramStrinig = gson.toJson(data.get("params"));

		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI08TWq7DvaGbw",
				"1E3y76GkOezw0sJNsf0UvyN8yMhJ6a");
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
		} catch (ClientException e1) {
			e1.printStackTrace();
			return WResponse.fail(WResponse.Action.EXPECTATION_FAILED).msg("内部错误").build();
		}

		boolean isSend = false;
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendSmsRequest request = new SingleSendSmsRequest();
		try {
			request.setSignName(SMS.smsTemp(signCode).getSMSSingName()); // 设置签名名称
			request.setTemplateCode(SMS.smsTemp(signCode).getSMSTempCode()); // 设置短信模板code
			request.setParamString(paramStrinig); // 设置短信内容替换变量
			request.setRecNum(recnum); // 短信接收着
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request); // 短信发送及返回
			System.out.println(httpResponse.getRequestId());
			// System.out.println(httpResponse.getModel());
			isSend = true;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}

		if (isSend) {
			return WResponse.success(WResponse.Action.ACCEPTED).msg("发送成功").build();
		} else {
			return WResponse.fail(WResponse.Action.EXPECTATION_FAILED).msg("内部错误").build();
		}
	}

	/**
	 * 提取短信发送的签名和模板代号接口
	 * 
	 * @author TTFC
	 */
	public interface SMSType {
		public String getSMSSingName(); // 获取短信签名

		public String getSMSTempCode(); // 获取短信模板代号
	}

	/**
	 * 标识短信发送类别及获取该类别的短信签名和短信模板
	 * 
	 * @author TTFC
	 */
	public enum SMS {
		// 短信发送识别码
		WJIEKUAN("W_JIEKUAN"); // W端借款流程发送

		private final String signName;

		private SMS(String s) {
			signName = s;
		}

		public String getSignName() {
			return signName;
		}

		@Override
		public String toString() {
			return signName;
		}

		public static Template smsTemp(String signCode) {
			// 识别是否是W端借款流程的短信发送
			if (signCode.equals(SMS.WJIEKUAN.toString())) {
				return Template.WJIEKUAN;
			}
	
			return Template.OTHER;
		}

		public enum Template implements SMSType {
			WJIEKUAN("万众金融", "SMS_29190092"), // W端借款流程所使用的短信签名和模板代号
			OTHER("NO", "NO");
			Template(final String signName, final String tempCode) {
				this.signName = signName;
				this.tempCode = tempCode;
			}

			private final String signName;
			private final String tempCode;

			public String getSMSSingName() {
				return signName;
			}

			public String getSMSTempCode() {
				return tempCode;
			}

			@Override
			public String toString() {
				return signName + ":" + tempCode;
			}
		}

	}
}