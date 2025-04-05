package com.solventa.device.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDTO {

	private String code;

	private HttpStatus status;

	private String message;

	private List<String> detailMessages;

	private String timeStamp;

}
