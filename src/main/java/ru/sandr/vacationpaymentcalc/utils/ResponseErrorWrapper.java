package ru.sandr.vacationpaymentcalc.utils;

import lombok.Value;
import ru.sandr.vacationpaymentcalc.dto.ErrorResponseDto;

import java.util.List;

@Value
public class ResponseErrorWrapper {
    Integer status;
    List<ErrorResponseDto> errors;
}
