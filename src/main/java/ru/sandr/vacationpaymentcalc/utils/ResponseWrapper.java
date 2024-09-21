package ru.sandr.vacationpaymentcalc.utils;

import lombok.Value;

@Value
public class ResponseWrapper<T> {
    T data;
}
