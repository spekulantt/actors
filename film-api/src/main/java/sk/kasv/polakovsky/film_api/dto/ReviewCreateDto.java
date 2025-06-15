package sk.kasv.polakovsky.film_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewCreateDto(
        @NotBlank(message = "Name is required")
        String author,
        @NotBlank(message = "Content cannot be empty")
        String content,
        @NotNull(message = "Review is required")
        @Min(value = 1, message = "Minimum rating is 1")
        @Max(value = 5, message = "Maximum rating is 5")
        Float rating,

        @NotNull(message = "ID of the movie is required")
        Long movieId
) {
}