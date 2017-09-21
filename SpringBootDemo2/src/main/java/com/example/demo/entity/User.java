package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author 赵金鑫
 * @date 2017年08月22日
 */
@Builder
@Data
@ToString
public class User {
    private String username;
    private String pwd;
}
