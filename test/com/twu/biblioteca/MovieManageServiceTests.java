package com.twu.biblioteca;

import com.twu.biblioteca.data.MovieData;
import com.twu.biblioteca.service.MovieManageService;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MovieManageServiceTests {

    private MovieManageService movieManageService;

    @Before
    public void setUp() {
        this.movieManageService = new MovieManageService();
    }

    @After
    public void tearDown() {
        this.movieManageService = null;
    }

    @Test
    public void chooseDisplayMoviesShouldReturnMovieList() {
        List<String> expectedMovies = new ArrayList<>();
        expectedMovies.add(String.format("%20s %15s %20s %10s", "Movie Name", "Release Year", "Director", "Rating"));
        MovieData.movieList
            .forEach(movie -> expectedMovies.add(String.format("%20s %15s %20s %10d",
                movie.getMovieName(), movie.getReleaseYear(), movie.getDirector(), movie.getRating()))
            );

        String expectedResult = String.join("\n", expectedMovies);
        String actualResult = movieManageService.displayMovies();

        assertThat(actualResult, is(expectedResult));
    }
}
