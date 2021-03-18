package com.example.Servlets2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/CreateFilm")
public class CreateFilm extends HttpServlet {

    private interface Names {
        String selectFilmTitle = "selectFilmsTitle";
        String selectFilmDescription = "selectFilmDescription";
        String connString = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String sqlQuery = "INSERT INTO film(title,description, language_id) VALUES (?, ?, 1);";
        String extraSQLQuery = "SELECT film_id, title, description FROM film WHERE film_id=(SELECT MAX(film_id) FROM film);";
        String login = "root";
        String pswd="root";
    }
    final int tableBorderWidth = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=CreateFilm method=POST>"
                    + "Введите название фильма"
                    + "<input name=" + Names.selectFilmTitle + ">"
                    + "<br>"
                    + "Введите описание фильма"
                    + "<input name=" + Names.selectFilmDescription + ">"
                    + "<input type=submit name=submitNumber value=OK>"
                    + "</form>");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filmTitle = request.getParameter(Names.selectFilmTitle);
        String filmDescription = request.getParameter(Names.selectFilmDescription);
        System.out.println(filmTitle);
        Film film = new Film(0, filmTitle, filmDescription);
        Crud.create(film);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}