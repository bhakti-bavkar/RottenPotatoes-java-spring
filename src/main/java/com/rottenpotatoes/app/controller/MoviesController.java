package com.rottenpotatoes.app.controller;

import com.rottenpotatoes.app.errors.EntityNotFoundException;
import com.rottenpotatoes.app.errors.InvalidInputException;
import com.rottenpotatoes.app.model.Movie;
import com.rottenpotatoes.app.services.MovieService;
import com.rottenpotatoes.app.utils.enums.Ratings;
import com.rottenpotatoes.app.utils.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MoviesController{
    @Autowired
    MovieService movieService;

    @GetMapping(produces = "application/json")
    public List<Movie> getMovies(@RequestParam(value="name",required = false) String name)
    throws EntityNotFoundException{
        if(name != null)
            return movieService.findMovieByName(name);
        return movieService.findAllMovies();
    }


    @GetMapping(path = "/{id}", produces = "application/json")
    public Movie movieById(@PathVariable String id) throws EntityNotFoundException {
        return movieService.findMovieById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Movie createMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
        return movie;
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable String id){
        movieService.updateMovie(movie, id);
        return movie;
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable String id){
        movieService.deleteMovieById(id);
        return "Successfully deleted Movie with id: " + id;
    }



}
