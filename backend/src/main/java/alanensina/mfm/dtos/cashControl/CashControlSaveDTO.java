package alanensina.mfm.dtos.cashControl;

import alanensina.mfm.enums.CashControlType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CashControlSaveDTO(
        @NotNull CashControlType type,
        @NotNull UUID userId,
        @NotEmpty String description,
        @NotNull BigDecimal value
        ) {
}
