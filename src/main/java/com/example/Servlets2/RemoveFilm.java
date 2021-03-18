package com.example.Servlets2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/RemoveFilm")
public class RemoveFilm extends HttpServlet {

    private interface Names {
        String deleteFilmNumber = "deleteFilmNumber";
        String connString = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String sqlQuery = "DELETE FROM film WHERE film_id=?;";
        String login = "root";
        String pswd="root";
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=RemoveFilm method=POST>"
                    + "Удалить фильм с id"
                    + "<input name=" + Names.deleteFilmNumber + ">"
                    + "<input type=submit name=submitNumber value=OK>"
                    + "</form>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            int id = Integer.parseInt(request.getParameter(Names.deleteFilmNumber));
            Film film = new Film(id, "", "");
            Crud.delete(film);

        }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

