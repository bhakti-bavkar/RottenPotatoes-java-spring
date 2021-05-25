package com.rottenpotatoes.app.services;


import com.rottenpotatoes.app.errors.exceptions.EntityNotFoundException;
import com.rottenpotatoes.app.utils.enums.*;
import com.rottenpotatoes.app.model.Movie;
import com.rottenpotatoes.app.repository.MovieRepository;
import com.rottenpotatoes.app.utils.enums.Ratings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImp implements MovieService{
    private final MovieRepository movieRepository;

    public MovieServiceImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void addNewMovie(Movie movie){
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        if(movies == null || movies.isEmpty()){
            throw new EntityNotFoundException(Movie.class, "movies", "DB Collection is empty");
        }
        return movies;
    }

    @Override
    public Movie findMovieById(String id){
        Movie movie = movieRepository.findMovieById(id);
        if(movie == null){
             throw new EntityNotFoundException(Movie.class, "id", id);
        }
        return movie;
    }

    @Override
    public void updateMovie(Movie movie, String id){
        movie.setId(id);
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(String id){
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findMoviesByUserType(UserType userType){
        String[] allowedRatings = getEligibleRatings(userType);

        List<Movie> movies = movieRepository.findMoviesByRatings(allowedRatings);
        if(movies == null){
            throw new EntityNotFoundException(Movie.class, "userType", userType.getValue());
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByName(String name){
        List<Movie> movies = movieRepository.findMovieByName(name);
        if(movies == null){
            throw new EntityNotFoundException(Movie.class, "name", name);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByRatings(String[] ratings){
        List<Movie> movies = movieRepository.findMoviesByRatings(ratings);
        if(movies == null){
            throw new EntityNotFoundException(Movie.class, "ratings", ratings.toString());
        }
        return movies;
    }

    private String[] getEligibleRatings(UserType userType){
        String[] eligibleRatings;
        switch (userType){
            case CHILD:
                eligibleRatings = new String[]{
                        Ratings.PG.toString(),
                        Ratings.G.toString()};
                break;
            case TEEN:
                eligibleRatings = new String[]{
                        Ratings.PG.toString(),
                        Ratings.PG_13.toString(),
                        Ratings.G.toString()};
                break;
            case ADULT:
            default:
                eligibleRatings = Ratings.toStrArray();
                break;
        }
        return eligibleRatings;
    }

}
