package com.twu.biblioteca.repository;

import com.twu.biblioteca.data.MovieData;
import com.twu.biblioteca.entity.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieManageRepository {

    public boolean getMovieByName(String movieName) {
        boolean ifValid  = getValidMovies().stream()
            .anyMatch(movie -> movie.getMovieName().equals(movieName)
                && movie.getState().equals("valid"));
        if (ifValid) {
            checkoutMovieByName(movieName);
        }
        return ifValid;
    }

    private void checkoutMovieByName(String movieName) {
        getValidMovies().stream()
            .filter(movie -> movie.getMovieName().equals(movieName))
            .forEach(movie -> movie.setState("invalid"));
    }

    public List<Movie> getValidMovies() {
        return MovieData.movieList.stream()
            .filter(movie -> movie.getState().equals("valid"))
            .collect(Collectors.toList());
    }
}
