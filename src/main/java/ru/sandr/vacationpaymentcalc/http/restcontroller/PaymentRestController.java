package ru.sandr.vacationpaymentcalc.http.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sandr.vacationpaymentcalc.dto.VacationPaymentRequestDto;
import ru.sandr.vacationpaymentcalc.service.CalculatePaymentService;
import ru.sandr.vacationpaymentcalc.utils.ResponseWrapper;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PaymentRestController {

    @Value("${default.precision}")
    private final Integer DEFAULT_PRECISION;

    private final CalculatePaymentService paymentService;

    @GetMapping("/calculate")
    public ResponseEntity<?> getPayment(@Valid @ModelAttribute VacationPaymentRequestDto requestDto,
                                        @RequestParam(required = false) Optional<Integer> precision) {
        return ResponseEntity.ok(new ResponseWrapper<>(paymentService.calculatePayment(requestDto,
                precision.orElse(DEFAULT_PRECISION))));
    }

}
