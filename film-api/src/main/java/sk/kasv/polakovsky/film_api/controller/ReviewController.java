package sk.kasv.polakovsky.film_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.polakovsky.film_api.dto.ReviewCreateDto;
import sk.kasv.polakovsky.film_api.dto.ReviewInMovieDto;
import sk.kasv.polakovsky.film_api.model.Review;
import sk.kasv.polakovsky.film_api.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Reviews", description = "Operations related to movie reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @Operation(summary = "Get reviews by movie ID", description = "Retrieve all reviews for a specific movie")
    public List<ReviewInMovieDto> getReviewsByMovie(@RequestParam Long movieId) {
        return reviewService.getReviewsForMovie(movieId);
    }

    @PostMapping
    @Operation(summary = "Create a new review", description = "Add a new review for a movie")
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewCreateDto reviewDto) {
        return reviewService.addReview(reviewDto)
                .map(review -> ResponseEntity.status(HttpStatus.CREATED).body(review))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing review", description = "Modify the details of an existing review")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewCreateDto reviewDto) {
        return reviewService.updateReview(id, reviewDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a review", description = "Remove a review by its unique identifier")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (reviewService.deleteReview(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}