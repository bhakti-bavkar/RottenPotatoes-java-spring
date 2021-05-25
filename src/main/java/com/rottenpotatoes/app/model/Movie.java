package com.rottenpotatoes.app.model;

import com.rottenpotatoes.app.utils.enums.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Document("movies")
public class Movie extends BaseData{
    @Indexed(name="movieName")
    @NotNull
    @NotEmpty
    private String name;

    private String director;

    private String releaseDate;

    @NotNull
    @NotEmpty
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
    public Ratings getRatings() throws Exception {
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
    public void setRatings(Ratings ratings){
        this.ratings = ratings.getValue();
    }

}
