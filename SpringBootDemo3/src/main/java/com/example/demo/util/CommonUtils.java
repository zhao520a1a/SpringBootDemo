package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by creditease on 2017/6/2.
 */
@Slf4j
public class CommonUtils {

    public static final String UPPER = "1";
    public static final String LOWER = "2";


    public static final String  SYNCWAY_DATE_RANGE="1";//按时间段同步


    /**
     * 获得当天日期
     * @param pattern
     * @return
     */
    public static String getCurrentday(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }


    public static int getBetweenDays(long obj1, long obj2) {
        int days = 0;
        days = (int) (obj1 - obj2) / 1000 / 3600 / 24;
        return days;
    }


    /**
     * 判断是否为日期
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static boolean isDate(String strDate, String pattern) {
        try {
            new SimpleDateFormat(pattern).parse(strDate);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    /**
     * 获取昨天的时间
     * @param pattern
     * @return
     */
    public static String getStrYesterday(String pattern){
        return  LocalDateTime.now().plusDays(-1).format(DateTimeFormatter.ofPattern(pattern)).toString();
    }



    /**
     * 通过指定的分钟数minNUm，得到距离现在minNum的过去时间
     *
     * @param minNum
     * @param pattern
     * @return
     */
    public static String getBeforeDateByMin(Integer minNum, String pattern) {
        return  LocalDateTime.now().plusMinutes(minNum).format(DateTimeFormatter.ofPattern(pattern)).toString();
    }

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
            if (StringUtils.isEmpty(arrStr[1])) {
                resultMap.put("status", "2");
                resultMap.put("result", new ArrayList());
                resultMap.put("message", arrStr[0] + "不能为空");
                return resultMap;
            }
        }
        return null;
    }




}
