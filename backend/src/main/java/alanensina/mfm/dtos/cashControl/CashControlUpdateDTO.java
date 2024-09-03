package alanensina.mfm.dtos.cashControl;

import alanensina.mfm.enums.CashControlType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CashControlUpdateDTO(
        @NotNull UUID id,
        @NotNull CashControlType type,
        @NotEmpty String description,
        @NotNull BigDecimal value
        ) {
}
