package ru.sandr.vacationpaymentcalc.service;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.sandr.vacationpaymentcalc.dto.VacationPaymentRequestDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CalculatePaymentService {

    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_LEAP_YEAR = 366;
    private final Set<MonthDay> holidays = new HashSet<>();

    @SneakyThrows
    public CalculatePaymentService() {
        File holidaysFile = new ClassPathResource("VacationDays.txt").getFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(holidaysFile))) {
            Arrays.stream(bufferedReader.readLine().split(",")).map(MonthDay::parse).forEach(holidays::add);
        }
    }

    public BigDecimal calculatePayment(VacationPaymentRequestDto dto, int precision) {
        if (dto.getVacationStartDate() != null) {
            return calcPaymentWithDate(dto, precision);
        } else {
            return calcPaymentWithoutDate(dto, precision);
        }
    }

    private BigDecimal calcPaymentWithDate(VacationPaymentRequestDto dto, int precision) {
        BigDecimal averageDailyIncome = calcAverageDailyIncome(dto.getTotalSalary(), precision,
                dto.getVacationStartDate().isLeapYear());
        int countPaidDays = calcCountPaidDays(dto.getVacationStartDate(), dto.getVacationEndDate());
        return averageDailyIncome.multiply(new BigDecimal(countPaidDays));
    }

    private BigDecimal calcPaymentWithoutDate(VacationPaymentRequestDto dto, int precision) {
        BigDecimal averageDailyIncome = calcAverageDailyIncome(dto.getTotalSalary(), precision, false);
        return averageDailyIncome.multiply(BigDecimal.valueOf(dto.getVacationDays()));
    }

    private BigDecimal calcAverageDailyIncome(BigDecimal salary, int precision, boolean isLeapYear) {
        return isLeapYear ? salary.divide(BigDecimal.valueOf(DAYS_IN_LEAP_YEAR), precision, RoundingMode.HALF_UP) :
                salary.divide(BigDecimal.valueOf(DAYS_IN_YEAR), precision, RoundingMode.HALF_UP);
    }

    private int calcCountPaidDays(LocalDate start, LocalDate end) {
        return (int) start.datesUntil(end)
                .map(date -> MonthDay.of(date.getMonth(), date.getDayOfMonth()))
                .filter(it -> !holidays.contains(it))
                .count();
    }

}
