package sk.kasv.polakovsky.film_api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.kasv.polakovsky.film_api.dto.ActorRoleDto;
import sk.kasv.polakovsky.film_api.dto.LinkActorToMovieDto;
import sk.kasv.polakovsky.film_api.dto.MovieCreateDto;
import sk.kasv.polakovsky.film_api.model.Actor;
import sk.kasv.polakovsky.film_api.model.Movie;
import sk.kasv.polakovsky.film_api.model.MovieActor;
import sk.kasv.polakovsky.film_api.repository.ActorRepository;
import sk.kasv.polakovsky.film_api.repository.MovieActorRepository;
import sk.kasv.polakovsky.film_api.repository.MovieRepository;
import sk.kasv.polakovsky.film_api.dto.ActorInMovieDto;
import sk.kasv.polakovsky.film_api.dto.MovieDetailDto;
import sk.kasv.polakovsky.film_api.dto.ReviewInMovieDto;
import sk.kasv.polakovsky.film_api.model.MovieActor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final MovieActorRepository movieActorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository, MovieActorRepository movieActorRepository) {        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.movieActorRepository = movieActorRepository;
    }

    public List<MovieDetailDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<MovieDetailDto> getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertToDto);
    }
    @Transactional
    public MovieDetailDto createMovie(MovieCreateDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.title());
        movie.setReleaseYear(movieDto.releaseYear());
        movie.setGenre(movieDto.genre());
        movie.setDurationMinutes(movieDto.durationMinutes());

        Movie savedMovie = movieRepository.save(movie);

        if (movieDto.actors() != null && !movieDto.actors().isEmpty()) {
            for (ActorRoleDto actorRoleDto : movieDto.actors()) {
                Optional<Actor> actorOptional = actorRepository.findById(actorRoleDto.actorId());
                if (actorOptional.isPresent()) {
                    MovieActor movieActor = new MovieActor();
                    movieActor.setMovie(savedMovie);
                    movieActor.setActor(actorOptional.get());
                    movieActor.setRole(actorRoleDto.role());
                    movieActorRepository.save(movieActor);
                }
            }
        }

        return convertToDto(savedMovie);
    }

    public Optional<MovieDetailDto> updateMovie(Long id, MovieCreateDto movieDto) {
        return movieRepository.findById(id).map(existingMovie -> {
            existingMovie.setTitle(movieDto.title());
            existingMovie.setReleaseYear(movieDto.releaseYear());
            existingMovie.setGenre(movieDto.genre());
            existingMovie.setDurationMinutes(movieDto.durationMinutes());

            Movie updatedMovie = movieRepository.save(existingMovie);
            return convertToDto(updatedMovie);
        });
    }

    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<MovieActor> linkActorToMovie(Long movieId, LinkActorToMovieDto linkDto) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<Actor> actor = actorRepository.findById(linkDto.actorId());

        if (movie.isPresent() && actor.isPresent()) {
            MovieActor movieActor = new MovieActor();
            movieActor.setMovie(movie.get());
            movieActor.setActor(actor.get());
            movieActor.setRole(linkDto.role());
            return Optional.of(movieActorRepository.save(movieActor));
        }

        return Optional.empty();
    }

    private MovieDetailDto convertToDto(Movie movie) {
        List<ReviewInMovieDto> reviewDtos = movie.getReviews().stream()
                .map(review -> new ReviewInMovieDto(review.getAuthor(), review.getContent(), review.getRating()))
                .collect(Collectors.toList());

        List<ActorInMovieDto> actorDtos = movie.getMovieActors().stream()
                .map(movieActor -> new ActorInMovieDto(movieActor.getActor().getId(), movieActor.getActor().getName(), movieActor.getRole()))
                .collect(Collectors.toList());

        return new MovieDetailDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getGenre(),
                movie.getDurationMinutes(),
                reviewDtos,
                actorDtos
        );
    }
}