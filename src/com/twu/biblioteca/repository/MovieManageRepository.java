package com.twu.biblioteca.repository;

import com.twu.biblioteca.data.MovieData;
import com.twu.biblioteca.entity.Movie;

import java.util.List;

public class MovieManageRepository {
    public List<Movie> getMovies() {
        return MovieData.movieList;
    }
}
