package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.util.Date;

/**
 * 策略查询的结果集
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StrategyUser {
	private String jobId;
	private Long strategyId;
	private String getTm;// 获取时间
	private String passportId;// passport_id
	private String userName;// 用户姓名
	private String phone;// 手机号码
	private Integer age;// 年龄
	private String gndCd;// 性别代码
	private String gndNm;// 性别名称
	private String phoneBeloProvinceCd;// 手机归属省份代码
	private String phoneBeloProvinceNm;// 手机归属省份名称
	private String phoneBeloCityCd;// 手机归属城市代码
	private String phoneBeloCityNm;// 手机归属城市名称
	private String email;// 邮箱
	private String busTypeCd;// 业务分类代码
	private String busTypeNm;// 业务分类名称
	private String userRoleCd;// 用户角色代码
	private String userRoleNm;// 用户角色名称
	private String keyPointCd;// 关键节点代码
	private String keyPointNm;// 关键节点名称
	private String regChnlCd;// 注册渠道代码
	private String regChnlNm;// 注册渠道名称
	private String submChnlCd;// 进件渠道代码
	private String submChnlNm;// 进件渠道名称
	private Double remainHour;// 停留时长
	private Double userScore;// 用户评分
	private String pushWayCd;// 推送方式代码
	private String pushWayNm;// 推送方式名称
	private String pushFreqCd;// 推送频率diamante
	private String pushFreqNm;// 推送频率名称
	private String createTime;// 创建时间
	private String smsCode;
	private String abTest;
	private String regTm;
	private String cdsNum;
	private String promotionChnlCd;
	private String promotionChnlNm;
	private String estPlatform;
	private String firstAuditPlatform;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date testTime;
}
