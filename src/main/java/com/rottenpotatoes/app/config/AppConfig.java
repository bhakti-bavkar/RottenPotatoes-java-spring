package com.rottenpotatoes.app.config;

import com.rottenpotatoes.app.repository.MovieRepository;
import com.rottenpotatoes.app.services.MovieService;
import com.rottenpotatoes.app.services.MovieServiceImp;
import com.rottenpotatoes.app.utils.JacksonEnumConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MovieService movieService(MovieRepository movieRepository){
        return new MovieServiceImp(movieRepository);
    }


}
