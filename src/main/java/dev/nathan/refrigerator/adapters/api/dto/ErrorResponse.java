package dev.nathan.refrigerator.adapters.api.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int status;
}