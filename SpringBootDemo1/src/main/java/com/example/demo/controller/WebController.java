package com.example.demo.controller;

import com.example.demo.entity.Travelrecord;
import com.example.demo.service.TravelrecordService;
import com.example.demo.util.CommonUtils;
import com.example.demo.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 赵金鑫
 * @date 2017年08月16日
 */

@Slf4j
@RestController /* @Controller + @ResponseBody */
public class WebController {




    /*SpringBoot默认使用json解析框架是jackson：返回json格式的数据*/
    @GetMapping("testReturnDefaultJson")
    public Travelrecord getStrategyUserBean(){
        Travelrecord travelrecord = Travelrecord.builder().userId("111").days(22).testTime(DateUtil.getCurrentday("yyyy-MM-dd HH:mm:ss")).build();
        return travelrecord;
    }


    /*
    * 手动配置使用FastJson解析Json数据，要下载1.2.10+的jar包
    * 第一种方法：
    *   启动类继承 WebMvcConfigureerAdapter类
    *   覆盖configureMessageConverters（）
    *
    * 第二种方法：
    *   在启动类中注入一个Bean: HttpMessageConverters;
    * */
    @GetMapping("testReturnFastJson")
    public Travelrecord getStrategyUserBean1(){
        Travelrecord travelrecord = Travelrecord.builder().userId("111").days(22).testTime(DateUtil.getCurrentday("yyyy-MM-dd HH:mm:ss")).build();

        //Travelrecord travelrecord = Travelrecord.builder().jobId("123").age(22).testTime(new Date()).build();
        return travelrecord;
    }

    /*pom文件中实现热部署*/


    private TravelrecordService travelrecordService;

    /**
     * 通过spring data jpa 调用方法 api :localhost:8099/users/byname?username=xxx
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/byname", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getUser(HttpServletRequest request) {
        Map<String, Object> map = CommonUtils.getParameterMap(request);
        String username = (String) map.get("username");
        return new ResponseEntity<>(travelrecordService.getTravelrecordByUserId(username), HttpStatus.OK);
    }



}
