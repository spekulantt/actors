package sk.kasv.polakovsky.film_api.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ActorDto(
        @NotBlank(message = "Name is required")
        String name,
        LocalDate birthDate,
        String nationality
) {
}