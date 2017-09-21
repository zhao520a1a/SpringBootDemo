package com.example.demo.ByAuto.controller;


import com.example.demo.ByAuto.service.RedisService;
import com.example.demo.domain.User;
import com.example.demo.util.CommonUtils;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.ResponseModal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ExampleController {

    @Autowired
    private RedisService redisService;


    @RequestMapping(value = "/redis/setObject", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseModal setObject(@RequestBody User user) {
        try {
            log.info("{}",user);
            //User user = User.builder().username("文曰小强").password("123").build();
            redisService.put("user", user, -1);
            return new ResponseModal(1, "调用成功", "");
        } catch (Exception e) {
            return new ResponseModal(0, "调用异常", CommonUtils.getTrace(e));
        }
    }



    @RequestMapping("/redis/set")
    public ResponseModal redisSet(@RequestParam("value") String value) {
        try {
            redisService.put("name", value, -1);
            log.info("{}","调用redis/set成功");
            return new ResponseModal(1, "调用成功", "");
        } catch (  Exception e) {
            return new ResponseModal(0, "调用异常", CommonUtils.getTrace(e));
        }
    }
    @RequestMapping("/redis/get")
    public ResponseModal redisSet() {
        try {
            Map map = (Map) new HashMap<>().put("value", "ewq");
             HttpClientUtil.doGet("http://10.106.204.5:8080/redis/set", map);
            //HttpClientUtil.doPostSSL("http://localhost:8080/redis/set",)
            return new ResponseModal(1, "调用成功", "");
        } catch (  Exception e) {
            return new ResponseModal(0, "调用异常", CommonUtils.getTrace(e));
        }
    }


}
