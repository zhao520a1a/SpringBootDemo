package com.example.demo.dao;

import com.example.demo.entity.Travelrecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 赵金鑫
 * @date 2017年08月25日
 */
@Repository
public interface TravelrecordDao extends JpaRepository<Travelrecord,Long> {


    Travelrecord findByUserId(String userId);

    /*
    如果参数名为多个字母组成，请首字母大写。勿使用驼峰命名，jpa不识别驼峰
     */
    Travelrecord findByIdAndDays(int id, Integer day);

    /**
     * Travelrecord为@Entity 的名字，即：JavaBean类名
     */
    @Query("from Travelrecord t where t.userId=:userId")
    Travelrecord findUser(@Param("userId") String userId);

}
