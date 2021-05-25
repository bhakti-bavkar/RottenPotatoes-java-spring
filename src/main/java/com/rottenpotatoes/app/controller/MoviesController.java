package com.rottenpotatoes.app.controller;

import com.rottenpotatoes.app.errors.exceptions.EntityNotFoundException;
import com.rottenpotatoes.app.model.Movie;
import com.rottenpotatoes.app.services.MovieService;
import com.rottenpotatoes.app.utils.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/movies")
@Validated
public class MoviesController{
    @Autowired
    MovieService movieService;

    @GetMapping(produces = "application/json")
    public List<Movie> getMovies(@RequestParam(value="name", required = false) String name,
                                 @RequestParam(value="usertype", required = false) String userType,
                                 @RequestParam(value="ratings", required = false)String[] ratings)
    throws EntityNotFoundException{
        if(name != null)
            return movieService.findMoviesByName(name);
        if(userType != null)
            return movieService.findMoviesByUserType(UserType.fromValue(userType));
        if(ratings != null)
            return movieService.findMoviesByRatings(ratings);
        return movieService.findAllMovies();
    }


    @GetMapping(path = "/{id}", produces = "application/json")
    public Movie movieById(@NotNull @NotEmpty @PathVariable String id) throws EntityNotFoundException {
        return movieService.findMovieById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Movie createMovie(@Valid @RequestBody Movie movie){
        movieService.addNewMovie(movie);
        return movie;
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Movie updateMovie(@Valid @RequestBody Movie movie, @NotNull @NotEmpty @PathVariable String id){
        movieService.updateMovie(movie, id);
        return movie;
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@NotNull @NotEmpty @PathVariable String id){
        movieService.deleteMovieById(id);
        return "Successfully deleted Movie with id: " + id;
    }



}
