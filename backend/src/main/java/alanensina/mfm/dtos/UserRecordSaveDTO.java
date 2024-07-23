package alanensina.mfm.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordSaveDTO(
        @NotBlank String name,
        @NotBlank String password,
        @NotBlank @Email String email) {
}
