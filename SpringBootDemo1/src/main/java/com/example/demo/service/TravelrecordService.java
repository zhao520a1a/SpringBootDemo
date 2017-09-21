package com.example.demo.service;

import com.example.demo.entity.Travelrecord;

/**
 * @author 赵金鑫
 * @date 2017年08月25日
 */
public interface TravelrecordService {

    Travelrecord getTravelrecordByUserId(String userId);
}
