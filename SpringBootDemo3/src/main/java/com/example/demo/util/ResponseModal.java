package com.example.demo.util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ResponseModal {

	private int status;
	
	private String message;

	private Object result;

}
