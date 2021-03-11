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
    final int tableBorderWidth = 3;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=UpdateFilm method=GET>"
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

            try {
                int filmId = Integer.parseInt(request.getParameter(Names.selectFilmId));
                String filmTitle = request.getParameter(Names.selectFilmTitle);
                String filmDescription = request.getParameter(Names.selectFilmDescription);
                System.out.println(filmTitle);
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                     PreparedStatement preparedStatement = con.prepareStatement(Names.sqlQuery);
                ) {
                    preparedStatement.setString(1, filmTitle);
                    preparedStatement.setString(2, filmDescription);
                    preparedStatement.setInt(3, filmId);
                    int rowFilm = preparedStatement.executeUpdate();
                    PreparedStatement preparedStatement1 = con.prepareStatement(Names.extraSQLQuery);
                    preparedStatement1.setInt(1, filmId);
                    ResultSet filmsResultSet = preparedStatement1.executeQuery();
                    filmsResultSet.next();

                    out.println("<table border=" + tableBorderWidth + " bgcolor=yellow>");
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(filmsResultSet.getString("title"));
                    out.println("</td>");
                    out.println("<td>");
                    out.println(filmsResultSet.getString("description"));
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</table>");
                    filmsResultSet.close();
                    }
                catch (SQLException e) {
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