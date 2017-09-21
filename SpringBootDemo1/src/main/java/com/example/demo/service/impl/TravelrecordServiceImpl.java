package com.example.demo.service.impl;

import com.example.demo.entity.Travelrecord;
import com.example.demo.dao.TravelrecordDao;
import com.example.demo.service.TravelrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 赵金鑫
 * @date 2017年08月25日
 */
@Service
public class TravelrecordServiceImpl implements TravelrecordService {

    @Autowired
    private TravelrecordDao travelrecordDao;

    @Override
    public Travelrecord getTravelrecordByUserId(String userId) {
        return travelrecordDao.findByUserId(userId);
    }
}
