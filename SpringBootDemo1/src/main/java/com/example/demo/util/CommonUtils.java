package com.example.demo.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by creditease on 2017/6/2.
 */
@Slf4j
public class CommonUtils {

    public static final String UPPER = "1";
    public static final String LOWER = "2";



    /**
     * 获取详细的异常信息
     *
     * @param t
     * @return
     */
    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }


    /**
     * 由于把时间直接按String读取（相当于先取java.sql.Timestamp该类型数据然后再对该数据进行toString()的结果）
     * 使得每条数据都是以.0结尾，因此我们可以对其进行截取字符串的方式处理。
     *   “2017-06-02 15:39:22.0” --》  “2017-06-02 15:39:22”
     * @param dateStr
     * @return
     */
    public static String getDatetimeStr(String dateStr){
        return dateStr.substring(0,dateStr.length()-2);
    }

    /**
     * 把Map中的key转成大写或小写
     *
     * @param srcMap
     * @param type
     * @return
     */
    public static Map mapKey2UpperOrLower(Map<String, Object> srcMap, String type) {
        Map descMap = new HashMap();
        Iterator<String> itrKeys = srcMap.keySet().iterator();
        while (itrKeys.hasNext()) {
            String key = itrKeys.next();
            Object srcVal = srcMap.get(key);
            if (srcVal == null) {
                srcVal = "";
            }
            if (UPPER.equals(type)) {
                descMap.put(key.toUpperCase(), srcVal);
            }
            if (LOWER.equals(type)) {
                descMap.put(key.toLowerCase(), srcVal);
            }
        }
        return descMap;
    }


    /**
     * 检查参数中是否为空
     * @param arrNullParam
     * @return
     */
    public Map validNullParam(String[][] arrNullParam) {
        Map resultMap = new HashMap();
        for (String[] arrStr : arrNullParam) {
            if (StringUtils.isEmpty (arrStr[1])) {
                resultMap.put("status", "2");
                resultMap.put("result", new ArrayList());
                resultMap.put("message", arrStr[0] + "不能为空");
                return resultMap;
            }
        }
        return null;
    }


    /**
     * 从request中获得参数Map，并返回可读的Map.
     *
     * @param request the request
     * @return the parameter map
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        //返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Set<String> keySet = properties.keySet();
        for(String key: keySet){
            String[] values = properties.get(key);
            String value = "";
            if(values != null && (values.length==1&& values[0] != " "?true:false)){
                for(int i=0;i<values.length;i++){
                    if(values[i] != null && !"".equals(values[i])){
//							value = new String(values[i].getBytes("ISO-8859-1"),"UTF-8") + ",";
                        value += values[i] + ",";
                    }
                }
                if(value != null && !"".equals(value)){
                    value = value.substring(0, value.length()-1);
                }
                if(key.equals("keywords")){//关键字特殊查询字符转义
                    value =  value.replace("_", "\\_").replace("%", "\\%");
                }
                returnMap.put(key, value);
            }
        }
        return returnMap;
    }



    /* 获得Post请求中内容 */
    public static String getPostData(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try {
            //req.setCharacterEncoding("utf-8");
            InputStream body = req.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(body));
            String line;
            do {
                line = reader.readLine();
                log.info(line);
                sb.append(line).append("\n");
            } while (line != null);
            reader.close();
        } catch (IOException e) {
            log.error("getPostData couldn't.. get the post data", e);  // This has happened if the request's reader is closed
        }
        return sb.toString();
    }

}
