package ru.sandr.vacationpaymentcalc.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sandr.vacationpaymentcalc.dto.VacationPaymentRequestDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CalculatePaymentServiceTest {

    private static final Integer PRECISION = 2;
    private static final BigDecimal TOTAL_SALARY = BigDecimal.valueOf(1200000);
    private static final Integer VACATION_DAYS = 30;
    private static final LocalDate START_VACATION_DATE = LocalDate.of(2024, 5, 1);
    private static final LocalDate END_VACATION_DATE = LocalDate.of(2024, 6, 1);

    private static final VacationPaymentRequestDto DTO_WITHOUT_DATE = new VacationPaymentRequestDto(TOTAL_SALARY,
            VACATION_DAYS, null, null);
    private static final VacationPaymentRequestDto DTO_WITH_DATE = new VacationPaymentRequestDto(TOTAL_SALARY,
            null, START_VACATION_DATE, END_VACATION_DATE);

    private CalculatePaymentService calculatePaymentService;

    @BeforeEach
    void prepare() {
        calculatePaymentService = new CalculatePaymentService();
    }

    @Test
    void calculatePaymentWithoutDate() {
        BigDecimal expectedResult = new BigDecimal("98630.10");
        assertThat(calculatePaymentService.calculatePayment(DTO_WITHOUT_DATE, PRECISION)).isEqualTo(expectedResult);
    }

    @Test
    void calculatePaymentWithDate() {
        BigDecimal expectedResult = new BigDecimal("95082.01");
        assertThat(calculatePaymentService.calculatePayment(DTO_WITH_DATE, PRECISION)).isEqualTo(expectedResult);
    }

}
