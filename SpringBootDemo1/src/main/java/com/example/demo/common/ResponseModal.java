package com.example.demo.common;

import lombok.*;


/*
*  相应工具类

### 接口返回参数
| 参数吗        | 类型    |  说明  |
| --------   | -----  | :----: |
| code        | string  | 'OK', 'FAILURE', 'SYS_ERROR' |
| msg        | string      |   说明文字    |
| data        | json      |   json数据    |

#### code说明：
1. 'OK' => 成功
2. 'FAILURE' => 业务失败
3. 'SYS_ERROR' => 系统失败

### 代码示例
	{
		"code": "OK",
		"msg": "查询成功",
		"data": {
			"userCode": "123456789",
			"name": "教主",
			"idCard": "123456789012345678"
		}
	}

	{
		"code": "FAILURE",
		"msg": "登录失败，用户名密码不匹配",
		"data": null,
		"errCode":"301"
	}

	{
		"code": "SYS_ERROR",
		"msg": "服务器连接失败",
		"data": null
	}
*
*
*/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ResponseModal {

	public enum RespCode {
		//OK -- 成功;  FAILURE -- 业务失败;  SYS_ERROR -- 系统失败
		OK, FAILURE, SYS_ERROR
	}

	private RespCode code;

	private String msg;

	private Object data;

}



