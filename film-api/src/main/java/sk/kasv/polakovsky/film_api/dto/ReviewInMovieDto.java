package sk.kasv.polakovsky.film_api.dto;

public record ReviewInMovieDto(
        String author,
        String content,
        Float rating
) {
}