package alanensina.mfm.dtos.investment;

import alanensina.mfm.enums.InvestmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InvestmentRecordSaveDTO(
        @NotBlank String name,
        @NotBlank String code,
        @NotNull InvestmentType type,
        @NotNull BigDecimal startPrice,
        @NotNull BigDecimal quantity) {
}
