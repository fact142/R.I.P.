package com.example.Servlets1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loop-servlet")
public class LoopSerlvet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int number;
        try{
            number = Integer.parseInt(request.getParameter("number"));
        } catch(NumberFormatException e){
            number = 2;
    }
        String color;
        try{
            color = request.getParameter("color");
        } catch(NumberFormatException e) {
            color = "black";
        }
        PrintWriter out = response.getWriter();
        for (int i = 0; i < number; i++) {
            out.println("<p style=\"color:"+ color + "\">Text <p>");
        }
    }
}
