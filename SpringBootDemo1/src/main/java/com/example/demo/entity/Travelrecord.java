package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author 赵金鑫
 * @date 2017年08月25日
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Entity
@Table(name="travelrecord")
public class Travelrecord implements java.io.Serializable{

    @Id
    @GeneratedValue
    private BigInteger id;
    @Column(name = "user_id")
    private String userId;
    @Column(name="traveldate")
    private String traveldate ;
    @Column(name = "fee")
    private BigDecimal fee;
    @Column(name="days")
    private Integer days;


    //为了测试Json格式是否有效，才设置的字段；并不对应数据库中的字段
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String testTime;
}
