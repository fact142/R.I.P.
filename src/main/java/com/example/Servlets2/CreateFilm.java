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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=CreateFilm method=GET>"
                    + "Введите название фильма"
                    + "<input name=" + Names.selectFilmTitle + ">"
                    + "<br>"
                    + "Введите описание фильма"
                    + "<input name=" + Names.selectFilmDescription + ">"
                    + "<input type=submit name=submitNumber value=OK>"
                    + "</form>");

            try {
                String filmTitle = request.getParameter(Names.selectFilmTitle);
                String filmDescription = request.getParameter(Names.selectFilmDescription);
                System.out.println(filmTitle);
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                     PreparedStatement preparedStatement = con.prepareStatement(Names.sqlQuery);
                ) {
                    preparedStatement.setString(1, filmTitle);
                    preparedStatement.setString(2, filmDescription);
                    int rowFilm = preparedStatement.executeUpdate();
                        PreparedStatement preparedStatement1 = con.prepareStatement(Names.extraSQLQuery);
                        ResultSet filmsResultSet = preparedStatement1.executeQuery();
                        filmsResultSet.next();

                        out.println("<table border=" + tableBorderWidth + " bgcolor=yellow>");
                        out.println("<tr>");
                        out.println("<td>");
                        out.println(filmsResultSet.getString("film_id"));
                        out.println("</td>");
                        out.println("<td>");
                        out.println(filmsResultSet.getString("title"));
                        out.println("</td>");
                        out.println("<td>");
                        out.println(filmsResultSet.getString("description"));
                        out.println("</td>");
                        out.println("</tr>");
                        out.println("</table>");
                        filmsResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            out.println("</body>");
            out.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}