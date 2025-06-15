package sk.kasv.polakovsky.film_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public record MovieCreateDto(
        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
        String title,

        Integer releaseYear,
        String genre,
        Integer durationMinutes,

        List<ActorRoleDto> actors
) {
}