package com.example.demo.common;

import com.alibaba.fastjson.JSON;
import com.yirendai.jfsk.rsc.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class RemoteService {
	private static final transient Logger LOG = LoggerFactory.getLogger(RemoteService.class);

	private final static RemoteService rs = new RemoteService();

	public static RemoteService getInstance() {
		return rs;
	}

	/**
	 * 
	 * 通用调用服务的方法
	 * 
	 * @param serviceUrl
	 * @param param
	 * @return
 	 */
	public Map callService(String serviceUrl, Map param) throws ServiceException {
		ServerInfo serverInfo;
		HttpClient client = new HttpClient();
		try {
			long startDateTime = System.currentTimeMillis();
			LOG.debug("[" + startDateTime + "]" + serviceUrl + ",参数：" + param.toString());
			serverInfo = client.sendPost(serviceUrl, param);
			long usetime = System.currentTimeMillis() - startDateTime;
			LOG.debug("[" + startDateTime + "]" + serviceUrl + ",耗时：" + usetime + "，结果："
					+ serverInfo.getContent());
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error(serviceUrl + "服务通信异常");
			throw new ServiceException(0, serviceUrl + "服务通信异常");
		}


		Map serviceMap = (Map)JSON.parse(serverInfo.getContent());

		if (String.valueOf(serviceMap.get("status")).equals("0")) {
			LOG.error(serviceUrl + "服务调用返回错误代码,status：" + serviceMap.get("status") + ";message:"
					+ serviceMap.get("message"));
			throw new ServiceException(Integer.parseInt(String.valueOf(serviceMap.get("status"))), serviceUrl
					+ "服务返回错误信息：" + (String) serviceMap.get("message"), serverInfo.getContent());
		}

		return serviceMap;
	}

}
