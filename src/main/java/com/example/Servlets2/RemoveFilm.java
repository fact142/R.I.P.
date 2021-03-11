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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=RemoveFilm method=GET>"
                    + "Удалить фильм с id"
                    + "<input name=" + Names.deleteFilmNumber + ">"
                    + "<input type=submit name=submitNumber value=OK>"
                    + "</form>");

            try {
                int numberOfFilmsToExtract = Integer.parseInt(request.getParameter(Names.deleteFilmNumber));
                System.out.println(numberOfFilmsToExtract);
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                     PreparedStatement preparedStatement = con.prepareStatement(Names.sqlQuery);
                ) {
                    preparedStatement.setInt(1, numberOfFilmsToExtract);
                    int filmsResultSet = preparedStatement.executeUpdate();

                    out.println(filmsResultSet);

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

