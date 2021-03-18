package com.example.Servlets2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/GetById")
public class GetById extends HttpServlet {

    private interface Names {
        String selectFilmsNumber = "selectFilmsNumber";
        String connString = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String sqlQuery = "SELECT title,description FROM film WHERE film_id=?;";
        String login = "root";
        String pswd="root";
    }
    final int tableBorderWidth = 3;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=GetById method=GET>"
                    + "Вывести фильм с id"
                    + "<input name=" + GetById.Names.selectFilmsNumber + ">"
                    + "<input type=submit name=submitNumber value=OK>"
                    + "</form>");
            int id = Integer.parseInt(request.getParameter(Names.selectFilmsNumber));

            Film film = new Film(id, "", "");
            Film newFilm = Crud.getById(film);

            out.println("<table border=" + tableBorderWidth + " bgcolor=yellow>");
            out.println("<tr>");
            out.println("<td>");
            out.println(newFilm.getTitle());
            out.println("</td>");
            out.println("<td>");
            out.println(newFilm.getDescription());
            out.println("</td>");
            out.println("</tr>");
            out.println("<table>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
