package com.emporio.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse
{
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    // solo se llena cuando el error viene de validaciones (@Valid)
    private Map<String, String> fieldErrors;
}
