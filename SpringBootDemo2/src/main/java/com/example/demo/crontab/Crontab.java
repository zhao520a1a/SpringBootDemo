package com.example.demo.crontab;

import com.example.demo.ByHand.service.SendTableService;
import com.example.demo.dao1.PeopleMapper;
import com.example.demo.entity.People;
import com.example.demo.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class Crontab {

    @Autowired
    private PeopleMapper peopleMapper;

    @Scheduled(cron = "0 0/5 * * * ?")  //每5分钟执行一次
    public void sync() {
         sysnPeople();
    }

    @Async("myAsync")
    private void sysnPeople() {
        String now = CommonUtils.getCurrentday("yyyy-MM-dd HH:mm:ss");
        String before5Mins = CommonUtils.getBeforeDateByMin(-5, "yyyy-MM-dd HH:mm:ss");
        try {
            log.info("开始同步people" + " startTime:" + before5Mins + ",endTime: " + now);
            List<People> peoples = peopleMapper.selectAll(before5Mins, now);
            log.info("tableName:people ---查询阶段 数据量：" + String.valueOf(peoples.size()));
            if (!CollectionUtils.isEmpty(peoples)) {
                SendTableService.sendTableByHTTP("people", peoples);
            }
            log.info("同步people成功");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
