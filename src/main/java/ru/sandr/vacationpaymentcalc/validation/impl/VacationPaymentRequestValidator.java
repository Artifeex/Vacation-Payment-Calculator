package ru.sandr.vacationpaymentcalc.validation.impl;

import ru.sandr.vacationpaymentcalc.dto.VacationPaymentRequestDto;
import ru.sandr.vacationpaymentcalc.validation.VacationPaymentRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.temporal.ChronoUnit;

public class VacationPaymentRequestValidator implements ConstraintValidator<VacationPaymentRequest, VacationPaymentRequestDto> {

    private static final String END_DATE_EARLIER_START_DATE = "vacationStartDate must be earlier than vacationEndDate";
    private static final String VACATION_DAYS_NOT_EQUAL_DATE_PERIOD = "value vacationDays is not equal to count days " +
            "between startVacationDate and endVacationDate";
    private static final String VACATION_DAYS_AND_DATES_NOT_PROVIDED = "Either vacationDays or both vacationStartDate and " +
            "vacationEndDate must be provided";


    public boolean isValid(VacationPaymentRequestDto dto, ConstraintValidatorContext context) {
        boolean isValid = true;
        context.disableDefaultConstraintViolation();
        if ((dto.getVacationStartDate() != null && dto.getVacationEndDate() != null)) {
            if (dto.getVacationEndDate().isBefore(dto.getVacationStartDate())) {
                context.buildConstraintViolationWithTemplate(END_DATE_EARLIER_START_DATE).addConstraintViolation();
                isValid = false;
            } else if (dto.getVacationDays() != null && ChronoUnit.DAYS.between(dto.getVacationStartDate(),
                    dto.getVacationEndDate()) != dto.getVacationDays()) {
                context.buildConstraintViolationWithTemplate(VACATION_DAYS_NOT_EQUAL_DATE_PERIOD).addConstraintViolation();
                isValid = false;
            }
        } else if (dto.getVacationDays() != null) {
            return isValid;

        } else {
            context.buildConstraintViolationWithTemplate(VACATION_DAYS_AND_DATES_NOT_PROVIDED).addConstraintViolation();
            isValid = false;
        }
        return isValid;
    }

}
