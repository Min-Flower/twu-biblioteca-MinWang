package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.exceptions.InvalidProductException;
import com.twu.biblioteca.repository.MovieManageRepository;

import java.util.List;

public class MovieManageService {

    private MovieManageRepository movieManageRepository = new MovieManageRepository();

    public String displayMovies() {
        return String.format("%30s %15s %20s %10s\n", "Movie Name", "Release Year", "Director", "Rating") +
            movieManageRepository.getValidMovies().stream().map(movie -> String.format("%30s %15s %20s %10d",
                    movie.getMovieName(), movie.getReleaseYear(), movie.getDirector(), movie.getRating()))
                .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }

    public String checkoutMovie(String movieName) {
        if (movieManageRepository.getMovieByName(movieName)) {
            return "Thank you! Enjoy the movie.";
        } else {
            throw new InvalidProductException("Sorry, that movie is not available.");
        }
    }

    public List<Movie> getValidMovies() {
        return movieManageRepository.getValidMovies();
    }
}
