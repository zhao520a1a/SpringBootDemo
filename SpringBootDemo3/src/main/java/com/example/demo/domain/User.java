package com.example.demo.domain;

import lombok.*;

/**
 * @author 赵金鑫
 * @date 2017年08月19日
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class User {
    private  String username;
    private String password;
}
