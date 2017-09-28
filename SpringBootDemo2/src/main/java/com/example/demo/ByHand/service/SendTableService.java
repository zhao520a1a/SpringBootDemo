package com.example.demo.ByHand.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.IterableByLengthCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class SendTableService {


    @Value("${url}")
    private static String url;

    /**
     * 调用服务接口：一次发送100条数据
     *
     * @param tableName
     * @param tableContent
     */
    public static  void sendTableByHTTP(String tableName, List<?> tableContent) {
        sendTableByHTTPInner(tableName, tableContent, 100, url);
    }

    /**
     * 调用web服务发送指定表中的数据
     *
     * @param tableName
     * @param tableContent
     * @param batchSize
     * @param postUrl
     */
    public  static  void sendTableByHTTPInner(String tableName, List<?> tableContent, int batchSize, String postUrl) {
        try {
            log.info("发送前阶段 tableName: " + tableName + ",总数据量:" + tableContent.size());
            IterableByLengthCollection<?> contents = new IterableByLengthCollection<>(tableContent, batchSize,
                    ArrayList.class);
            for (Collection<?> content : contents) {
                try {
                    StringBuilder resultString = new StringBuilder();
                    resultString.append("{\"table\":\"" + tableName + "\"," + "\"" + tableName + "\":");
                    resultString.append(JSON.toJSONString(content) + "}");

                    String result = "";
                    try {
                        result = HttpClientUtil.doPost(postUrl,
                                resultString.toString());
                    } catch (Exception e) {
                        log.error("发送时异常", e);
                    }

                    log.info("调用服务返回结果信息" + result);
                    JSONObject parseObject = JSON.parseObject(result);
                    if ("success".equals(parseObject.getString("code"))) {
                    } else {
                        log.error("发送失败！" + result);
                    }
                }catch (Exception e) {
                    log.error("单次调用执行异常：tableName={} content.size={} batchSize=" +
                            "{}", tableName, content.size(), batchSize, e);
                }
            }
        } catch (Exception e) {
            log.error("执行sendTableByHTTPInner异常：tableName={} tableContent.size={} batchSize=" +
                    "{}", tableName, tableContent.size(), batchSize, e);
        }
    }




}
