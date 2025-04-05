package com.solventa.user.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {
	private String code;
    private HttpStatus status;
    private String message;
    private List<String> detailMessages;
    private LocalDateTime timeStamp;

}
