package com.twu.biblioteca.service;

import com.twu.biblioteca.repository.MovieManageRepository;

public class MovieManageService {

    private MovieManageRepository movieManageRepository = new MovieManageRepository();

    public String displayMovies() {
        return String.format("%20s %15s %20s %10s\n", "Movie Name", "Release Year", "Director", "Rating") +
            movieManageRepository.getMovies().stream().map(movie -> String.format("%20s %15s %20s %10d",
                    movie.getMovieName(), movie.getReleaseYear(), movie.getDirector(), movie.getRating()))
                .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }
}
