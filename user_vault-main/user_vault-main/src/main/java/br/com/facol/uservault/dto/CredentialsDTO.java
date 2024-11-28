package br.com.facol.uservault.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record CredentialsDTO(
        @Email
        String email,
        @Min(value = 4)
        String password
) {
}
