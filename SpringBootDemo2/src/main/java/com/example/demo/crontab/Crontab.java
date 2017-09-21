package com.example.demo.crontab;

import com.example.demo.dao.StrategyUserMapper;
import com.example.demo.entity.StrategyUser;
import com.example.demo.ByHand.service.SendTableService;
import com.example.demo.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class Crontab {

    @Autowired
    private StrategyUserMapper strategyUserMapper;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncStrategyUser() {
        String now = CommonUtils.getCurrentday("yyyy-MM-dd HH:mm:ss");
        String before5Mins = CommonUtils.getBeforeDateByMin(-5, "yyyy-MM-dd HH:mm:ss");
        try {
            log.info("开始同步strategyUser" + " startTime:" + before5Mins + ",endTime: " + now);
            List<StrategyUser> strategyUsers = strategyUserMapper.selectAll(before5Mins, now);
            log.info("tableName:strategyUser ---查询阶段 数据量：" + String.valueOf(strategyUsers.size()));
            if (!CollectionUtils.isEmpty(strategyUsers)) {
                SendTableService.sendTableByHTTP("strategyUser", strategyUsers);
            }
            log.info("同步strategyUser成功");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
