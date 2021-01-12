package com.rottenpotatoes.app.db;

import com.rottenpotatoes.app.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.rottenpotatoes.app.utils.Constants.*;

public class Movies {
    private static final MongoClient mongoDB = MongoDBClient.getSingleInstance().getMongoClient();
    private MongoDatabase database;


    public Movies(){
         database = mongoDB.getDatabase(DB_MOVIES);
    }

    public List<Movie> findAllMovies() throws JsonProcessingException {
        MongoCollection<Document> moviesCollection = database.getCollection("movies");
        System.out.println(moviesCollection.countDocuments());

        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();

        FindIterable<Document> resultSet = moviesCollection.find();
        Iterator movieIterator = resultSet.iterator();
        while(movieIterator.hasNext()){
            movie = convert((Document)movieIterator.next());
            movies.add(movie);
        }

        return movies;
    }

    private Movie convert(Document document) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(document.toString(), Movie.class);
    }
}
