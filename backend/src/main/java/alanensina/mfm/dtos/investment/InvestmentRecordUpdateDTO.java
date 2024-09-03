package alanensina.mfm.dtos.investment;

import alanensina.mfm.enums.InvestmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record InvestmentRecordUpdateDTO(
        @NotNull UUID id,
        @NotBlank String name,
        @NotBlank String code,
        @NotNull InvestmentType type,
        @NotNull BigDecimal startPrice,
        @NotNull BigDecimal quantity,
        @NotNull Boolean active) {
}
