package sk.kasv.polakovsky.film_api.dto;

import jakarta.validation.constraints.NotNull;

public record LinkActorToMovieDto(
        @NotNull
        Long actorId,
        String role
) {
}