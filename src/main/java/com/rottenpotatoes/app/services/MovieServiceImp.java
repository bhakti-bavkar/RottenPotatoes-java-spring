package com.rottenpotatoes.app.services;


import com.rottenpotatoes.app.errors.EntityNotFoundException;
import com.rottenpotatoes.app.utils.enums.*;
import com.rottenpotatoes.app.model.Movie;
import com.rottenpotatoes.app.repository.MovieRepository;
import com.rottenpotatoes.app.utils.enums.Ratings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rottenpotatoes.app.utils.Constants.*;

import java.util.Arrays;
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
        if(movies == null){
            throw new EntityNotFoundException(Movie.class, "No Data");
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
        List<Ratings> allowedRatings = getEligibleRatings(userType);

        List<Movie> movies = movieRepository.findMoviesByRatings(allowedRatings);
        if(movies == null){
            throw new EntityNotFoundException(Movie.class, "userType", userType.getValue());
        }
        return movies;
    }

    @Override
    public List<Movie> findMovieByName(String name){
        List<Movie> movies = movieRepository.findMovieByName(name);
        if(movies == null){
            throw new EntityNotFoundException(Movie.class, "name", name);
        }
        return movies;
    }

    private List<Ratings> getEligibleRatings(UserType userType){
        List<Ratings> eligibleRatings;
        switch (userType){
            case CHILD:
                eligibleRatings = Arrays.asList(Ratings.PG, Ratings.G);
                break;
            case TEEN:
                eligibleRatings = Arrays.asList(Ratings.PG, Ratings.PG_13, Ratings.G);
                break;
            case ADULT:
            default:
                eligibleRatings = Arrays.asList(Ratings.values());
                break;
        }
        return eligibleRatings;
    }

}
