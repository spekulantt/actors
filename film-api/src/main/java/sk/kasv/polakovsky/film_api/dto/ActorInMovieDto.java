package sk.kasv.polakovsky.film_api.dto;

public record ActorInMovieDto(
        Long actorId,
        String name,
        String role
) {
}