package sk.kasv.polakovsky.film_api.dto;

import java.util.List;

public record MovieDetailDto(
        Long id,
        String title,
        Integer releaseYear,
        String genre,
        Integer durationMinutes,
        List<ReviewInMovieDto> reviews,
        List<ActorInMovieDto> actors
) {
}