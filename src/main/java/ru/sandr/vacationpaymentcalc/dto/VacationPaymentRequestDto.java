package ru.sandr.vacationpaymentcalc.dto;

import lombok.Value;
import ru.sandr.vacationpaymentcalc.validation.VacationPaymentRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@VacationPaymentRequest
public class VacationPaymentRequestDto {

    @NotNull(message = "totalSalary must be provided")
    @Positive(message = "totalSalary must be positive")
    BigDecimal totalSalary;
    @Positive(message = "vacationDays must be positive")
    Integer vacationDays;
    LocalDate vacationStartDate;
    LocalDate vacationEndDate;

}
