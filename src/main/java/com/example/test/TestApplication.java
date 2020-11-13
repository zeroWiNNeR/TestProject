package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {

        String sql = "CREATE DATABASE test_db";
        try (Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "postgres")) {
            Statement stmt = db.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            e.getLocalizedMessage();
            System.out.println(e.toString());
        }

        SpringApplication.run(TestApplication.class, args);
    }

}
