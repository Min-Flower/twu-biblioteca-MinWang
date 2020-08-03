package com.twu.biblioteca;

import com.twu.biblioteca.data.MovieData;
import com.twu.biblioteca.exceptions.InvalidProductException;
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
        MovieData.initDB();
    }

    @Test
    public void chooseDisplayMoviesShouldReturnMovieList() {
        List<String> expectedMovies = new ArrayList<>();
        expectedMovies.add(String.format("%30s %15s %20s %10s", "Movie Name", "Release Year", "Director", "Rating"));
        MovieData.movieList
            .forEach(movie -> expectedMovies.add(String.format("%30s %15s %20s %10d",
                movie.getMovieName(), movie.getReleaseYear(), movie.getDirector(), movie.getRating()))
            );

        String expectedResult = String.join("\n", expectedMovies);
        String actualResult = movieManageService.displayMovies();

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void afterCheckedOutSuccessfulMessageShouldBeSent() {
        String expectedResult = "Thank you! Enjoy the movie.";
        String actualResult = movieManageService.checkoutMovie("Titanic");

        assertThat(actualResult, is(expectedResult));
        assertThat(movieManageService.getValidMovies().size(), is(2));
    }

    @Test
    public void failToCheckOutInvalidExceptionShouldBeThrown() {
        try {
            movieManageService.checkoutMovie("The Titanic");

            Assert.fail("Should throw InvalidProductException");

        } catch (InvalidProductException e) {
            String expectedResult = "Sorry, that movie is not available.";
            String actualResult = e.getMessage();

            assertThat(actualResult, is(expectedResult));
            assertThat(movieManageService.getValidMovies().size(), is(3));
        }
    }

}
