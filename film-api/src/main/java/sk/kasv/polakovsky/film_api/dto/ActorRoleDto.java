package sk.kasv.polakovsky.film_api.dto;

import jakarta.validation.constraints.NotNull;

public record ActorRoleDto(
        @NotNull Long actorId,
        String role
) {
}