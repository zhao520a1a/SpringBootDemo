package com.example.demo.ByHand.controller;

import com.example.demo.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赵金鑫
 * @date 2017年08月16日
 */

@Slf4j
@RestController
public class WebController {

    /**
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解
     *
     * @return
     */
    @RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
    public void test(@RequestBody People people){
        System.out.println(people);
    }


}
