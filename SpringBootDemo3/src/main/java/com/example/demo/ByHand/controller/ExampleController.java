//package com.example.demo.ByHand.controller;
//
//
//import com.example.demo.ByHand.service.IRedisService;
//import com.example.demo.ByHand.util.CommonUtils;
//import com.example.demo.domain.User;
//import com.example.demo.ByHand.util.ResponseModal;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//public class ExampleController {
//
//    @Autowired
//    private IRedisService redisService;
//
//
//    @RequestMapping("/redis/setObject")
//    public ResponseModal setObject(@RequestBody User user) {
//        try {
//            //User user = User.builder().username("文曰小强").password("123").build();
//            redisService.lpush("user", user);
//            return new ResponseModal(1, "调用成功", "");
//        }catch (Exception e){
//            return new ResponseModal(0, "调用异常", CommonUtils.getTrace(e));
//        }
//    }
//
//
//
//    @RequestMapping("/redis/set")
//    public ResponseModal redisSet(@RequestParam("value") String value) {
//        try {
//            boolean isOk = redisService.set("name", value);
//            if (isOk) {
//                return new ResponseModal(1, "调用成功", "");
//            } else {
//                return new ResponseModal(1, "调用失败", "");
//            }
//        }catch (Exception e){
//            return new ResponseModal(0, "调用异常", CommonUtils.getTrace(e));
//        }
//    }
//
//    @RequestMapping("/redis/get")
//    public ResponseModal redisGet() {
//        try {
//            String name = redisService.get("name");
//            return new ResponseModal(0, "调用成功", name);
//        }catch (Exception e){
//            return new ResponseModal(1, "调用异常", CommonUtils.getTrace(e));
//        }
//    }
//
//
//
//
//}
