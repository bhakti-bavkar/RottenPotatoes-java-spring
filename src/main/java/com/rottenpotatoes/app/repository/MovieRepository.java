package com.rottenpotatoes.app.repository;

import com.rottenpotatoes.app.utils.enums.*;
import com.rottenpotatoes.app.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    //Basic CRUD operations out of box

    @Query("{id : ?0}")
    Movie findMovieById(String id);

    @Query("{'name' : { $regex: ?0,  $options: 'i', $ne : null}}")
    List<Movie> findMovieByName(String name);

    @Query("{ratings: {$in: ?0}}")
    List<Movie> findMoviesByRatings(String[] ratings);

}
