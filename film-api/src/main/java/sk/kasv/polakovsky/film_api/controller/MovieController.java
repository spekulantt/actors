package sk.kasv.polakovsky.film_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.polakovsky.film_api.dto.LinkActorToMovieDto;
import sk.kasv.polakovsky.film_api.dto.MovieCreateDto;
import sk.kasv.polakovsky.film_api.dto.MovieDetailDto;
import sk.kasv.polakovsky.film_api.model.Movie;
import sk.kasv.polakovsky.film_api.model.MovieActor;
import sk.kasv.polakovsky.film_api.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Operations related to movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get all movies", description = "Retrieve a list of all movies in the system")
    @GetMapping
    public List<MovieDetailDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @Operation(summary = "Get movie by ID", description = "Retrieve a movie by its unique identifier")
    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailDto> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new movie", description = "Add a new movie to the system")
    @PostMapping
    public ResponseEntity<MovieDetailDto> createMovie(@Valid @RequestBody MovieCreateDto movieDto) {
        MovieDetailDto savedMovieDto = movieService.createMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieDto);
    }

    @Operation(summary = "Update an existing movie", description = "Modify the details of an existing movie")
    @PutMapping("/{id}")
    public ResponseEntity<MovieDetailDto> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieCreateDto movieDto) {
        return movieService.updateMovie(id, movieDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a movie", description = "Remove a movie from the system by its unique identifier")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Link an actor to a movie", description = "Associate an actor with a movie and specify their role")
    @PostMapping("/{movieId}/actors")
    public ResponseEntity<MovieActor> linkActorToMovie(@PathVariable Long movieId, @Valid @RequestBody LinkActorToMovieDto linkDto) {
        return movieService.linkActorToMovie(movieId, linkDto)
                .map(link -> ResponseEntity.status(HttpStatus.CREATED).body(link))
                .orElse(ResponseEntity.notFound().build());
    }

}