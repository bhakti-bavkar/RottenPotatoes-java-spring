package com.rottenpotatoes.app.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.IOException;
import java.util.Properties;

public class MongoDBClient {
    private MongoClient mongoClient = null;

    private MongoDBClient(){
        Properties mongoDBProps = new Properties();
        try{
            mongoDBProps.load(ClassLoader.getSystemResourceAsStream("application.properties"));
            mongoClient = connect(mongoDBProps.getProperty("mongoDBConnectionString"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private MongoClient connect(String connectionString){
        return MongoClients.create(connectionString);
    }

    private void disconnct(MongoClient mongoClient){
        mongoClient.close();
    }

    private static class Singleton{
        private static final MongoDBClient INSTANCE = new MongoDBClient();
    }

    public static MongoDBClient getSingleInstance(){
        return Singleton.INSTANCE;
    }
    public MongoClient getMongoClient(){
        return this.mongoClient;
    }
}
