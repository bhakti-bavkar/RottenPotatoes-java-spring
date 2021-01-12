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

public class MovieDBHelper {
    private static final MongoClient mongoDB = MongoDBClient.getSingleInstance().getMongoClient();
    private MongoDatabase database;


    public MovieDBHelper(){
        database = mongoDB.getDatabase("DB_MOVIES");
    }

    public List<Movie> findAllMovies() throws JsonProcessingException {
        MongoCollection<Document> moviesCollection = database.getCollection("movies");
        System.out.println("Movies count: " + moviesCollection.countDocuments());

        List<Movie> movies = new ArrayList<>();
        String movieStr;
        FindIterable<Document> resultSet = moviesCollection.find();
        Iterator movieIterator = resultSet.iterator();
        while(movieIterator.hasNext()){
            movieStr = movieIterator.next().toString();
            System.out.println(movieStr);
            //movies.add(convert(movieStr));
        }

        return movies;
    }

    private Movie convert(String document) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(document, Movie.class);
    }
}
