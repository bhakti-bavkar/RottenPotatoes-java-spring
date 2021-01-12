package com.rottenpotatoes.app.model;

import com.rottenpotatoes.app.utils.enums.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("movies")
public class Movie extends BaseData{
    @Indexed(name="movieName")
    private String name;

    private String director;
    private String releaseDate;
    private String ratings;

    public String getName(){
        return this.name;
    }
    public String getDirector(){
        return this.director;
    }
    public String getReleaseDate(){
        return this.releaseDate;
    }

    @Transient
    public Ratings getRating() throws Exception {
        return Ratings.fromValue(ratings);
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    public void setRating(Ratings rating){
        this.ratings = rating.getValue();
    }

}
