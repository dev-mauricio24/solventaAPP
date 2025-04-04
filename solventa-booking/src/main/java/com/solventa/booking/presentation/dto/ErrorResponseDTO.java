package com.solventa.booking.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {
    private String code;

    private HttpStatus status;

    private String message;

    private List<String> detailMessages;

    private String timeStamp;
}
