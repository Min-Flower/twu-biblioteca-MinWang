package com.twu.biblioteca.entity;

public class Movie {

    private String movieName;
    private String releaseYear;
    private String director;
    private int rating;
    private String state = "valid";

    public Movie() {
    }

    public Movie(String movieName, String releaseYear, String director, int rating) {
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.director = director;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
