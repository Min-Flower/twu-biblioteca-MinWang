package com.twu.biblioteca.data;

import com.twu.biblioteca.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieData {

    public static List<Movie> movieList = new ArrayList<>();

    static {
        initDB();
    }

    public static void initDB() {
        movieList = new ArrayList<>();
        movieList.add(new Movie("The Shawshank Redemption", "1994", "Frank Drapant", 9));
        movieList.add(new Movie("Mulholland Dr.", "2001", "David Lynch", 8));
        movieList.add(new Movie("Titanic", "1997", "James Cameron", 9));
    }
}
