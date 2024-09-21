package ru.sandr.vacationpaymentcalc.dto;

import lombok.Value;

@Value
public class ErrorResponseDto {

    String fieldName;
    String detail;
}
