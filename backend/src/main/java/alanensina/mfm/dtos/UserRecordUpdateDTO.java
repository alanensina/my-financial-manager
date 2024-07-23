package alanensina.mfm.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserRecordUpdateDTO(
        @NotNull UUID id,
        @NotBlank String name,
        @NotBlank String password,
        @NotBlank @Email String email) {
}
