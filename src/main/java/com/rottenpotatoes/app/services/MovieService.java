package com.rottenpotatoes.app.services;

import com.rottenpotatoes.app.utils.enums.*;
import com.rottenpotatoes.app.model.Movie;

import java.util.List;

public interface MovieService {
    void addNewMovie(Movie movie);

    List<Movie> findAllMovies();

    Movie findMovieById(String id);

    void updateMovie(Movie movie, String id);

    void deleteMovieById(String id);

    List<Movie> findMoviesByUserType(UserType userType);

    List<Movie> findMovieByName(String name);
}