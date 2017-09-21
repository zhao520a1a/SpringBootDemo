//package com.example.demo.ByHand.service;
//
//import java.util.List;
//
///**
// * @author 赵金鑫
// * @date 2017年08月18日
// */
//public interface IRedisService {
//
//    boolean set(String key, String value);
//
//    String get(String key);
//
//    boolean expire(String key, long expire);
//
//    <T> boolean setList(String key, List<T> list);
//
//    <T> List<T> getList(String key, Class<T> clz);
//
//    long lpush(String key, Object obj);
//
//    long rpush(String key, Object obj);
//
//    String lpop(String key);
//
//    boolean setObject(String key, Object obj);
//
//    <T> T getObject(String key, Class<T> clz);
//
//}
