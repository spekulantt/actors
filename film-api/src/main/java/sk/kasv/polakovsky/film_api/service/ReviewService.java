package sk.kasv.polakovsky.film_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.kasv.polakovsky.film_api.dto.ReviewCreateDto;
import sk.kasv.polakovsky.film_api.dto.ReviewInMovieDto;
import sk.kasv.polakovsky.film_api.model.Movie;
import sk.kasv.polakovsky.film_api.model.Review;
import sk.kasv.polakovsky.film_api.repository.MovieRepository;
import sk.kasv.polakovsky.film_api.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public Optional<Review> addReview(ReviewCreateDto reviewDto) {
        Optional<Movie> movieOptional = movieRepository.findById(reviewDto.movieId());
        if (movieOptional.isEmpty()) {
            return Optional.empty();
        }
        Review review = new Review();
        review.setAuthor(reviewDto.author());
        review.setContent(reviewDto.content());
        review.setRating(reviewDto.rating());
        review.setMovie(movieOptional.get());
        return Optional.of(reviewRepository.save(review));
    }

    public List<ReviewInMovieDto> getReviewsForMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId).stream()
                .map(review -> new ReviewInMovieDto(review.getAuthor(), review.getContent(), review.getRating()))
                .collect(Collectors.toList());
    }

    public Optional<Review> updateReview(Long id, ReviewCreateDto reviewDto) {
        return reviewRepository.findById(id).map(existingReview -> {
            Optional<Movie> movieOptional = movieRepository.findById(reviewDto.movieId());
            if(movieOptional.isEmpty()) return null;

            existingReview.setAuthor(reviewDto.author());
            existingReview.setContent(reviewDto.content());
            existingReview.setRating(reviewDto.rating());
            existingReview.setMovie(movieOptional.get());
            return reviewRepository.save(existingReview);
        });
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}