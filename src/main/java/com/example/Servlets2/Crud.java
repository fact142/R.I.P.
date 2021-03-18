package com.example.Servlets2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public  class Crud {
    private interface Names {
        String selectFilmsNumber = "selectFilmsNumber";
        String connString = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String getByIdQuery = "SELECT title,description FROM film WHERE film_id=?;";
        String deleteQuery = "DELETE FROM film WHERE film_id=?;";
        String updateQuery = "UPDATE film SET title=?, description=? WHERE film_id=?;";
        String createQuery = "INSERT INTO film(title,description, language_id) VALUES (?, ?, 1);";
        String login = "root";
        String pswd="root";
    }

    public static Film getById(Film film) {
        Film film1 = null;
        try {
            int numberOfFilmsToExtract = film.getId();
            System.out.println(numberOfFilmsToExtract);
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                 PreparedStatement preparedStatement = con.prepareStatement(Names.getByIdQuery);
            ) {
                preparedStatement.setInt(1, numberOfFilmsToExtract);
                ResultSet filmsResultSet = preparedStatement.executeQuery();
                filmsResultSet.next();
                film1 = new Film(0, filmsResultSet.getString("title"), filmsResultSet.getString("description"));
                filmsResultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return film1;
    }
    public static void delete(Film film){
        try {
            int numberOfFilmsToExtract = film.getId();
            System.out.println(numberOfFilmsToExtract);
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                 PreparedStatement preparedStatement = con.prepareStatement(Names.deleteQuery);
            ) {
                preparedStatement.setInt(1, numberOfFilmsToExtract);
                int filmsResultSet = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void update(Film film){
        try {
            int filmId = film.getId();
            String filmTitle = film.getTitle();
            String filmDescription = film.getDescription();
            System.out.println(filmTitle);
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                 PreparedStatement preparedStatement = con.prepareStatement(Names.updateQuery);
            ) {
                preparedStatement.setString(1, filmTitle);
                preparedStatement.setString(2, filmDescription);
                preparedStatement.setInt(3, filmId);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void create(Film film){
        try {
            String filmTitle = film.getTitle();
            String filmDescription = film.getDescription();
            System.out.println(filmTitle);
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                 PreparedStatement preparedStatement = con.prepareStatement(Names.createQuery);
            ) {
                preparedStatement.setString(1, filmTitle);
                preparedStatement.setString(2, filmDescription);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
