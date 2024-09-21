package ru.sandr.vacationpaymentcalc.validation;

import ru.sandr.vacationpaymentcalc.validation.impl.VacationPaymentRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = {VacationPaymentRequestValidator.class})
public @interface VacationPaymentRequest {

    String message() default "VacationPaymentRequestDto validation fail";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
