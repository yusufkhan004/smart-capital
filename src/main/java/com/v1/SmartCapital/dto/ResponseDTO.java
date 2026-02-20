package com.v1.SmartCapital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.v1.SmartCapital.constants.GeneralMsgConstants.MSG_SUCCESS;


@NoArgsConstructor
@Data
public class ResponseDTO<T> {

	private int status = HttpStatus.OK.value();
	private T data;
	private String message = MSG_SUCCESS;

	public ResponseDTO(T data, String message) {
		this.data = data;
		this.message = message;
	}
}
