package com.example.Servlets2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/UpdateFilm")
public class UpdateFilm extends HttpServlet {

    private interface Names {
        String selectFilmId = "selectFilmId";
        String selectFilmTitle = "selectFilmsTitle";
        String selectFilmDescription = "selectFilmDescription";
        String connString = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String sqlQuery = "UPDATE film SET title=?, description=? WHERE film_id=?;";
        String extraSQLQuery = "SELECT title,description FROM film WHERE film_id=?;";
        String login = "root";
        String pswd="root";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=UpdateFilm method=POST  >"
                    + "Введите id"
                    + "<input name=" + Names.selectFilmId + ">"
                    + "<br>"
                    + "Введите название фильма"
                    + "<input name=" + Names.selectFilmTitle + ">"
                    + "<br>"
                    + "Введите описание фильма"
                    + "<input name=" + Names.selectFilmDescription + ">"
                    + "<input type=submit name=submitNumber value=OK>"
                    + "</form>");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int filmId = Integer.parseInt(request.getParameter(Names.selectFilmId));
        String filmTitle = request.getParameter(Names.selectFilmTitle);
        String filmDescription = request.getParameter(Names.selectFilmDescription);
        System.out.println(filmTitle);
        Film film = new Film(filmId, filmTitle, filmDescription);
        Crud.update(film);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}