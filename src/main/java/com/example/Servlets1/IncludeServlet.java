package com.example.Servlets1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/include-servlet")
public class IncludeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("TestServlet says hi<br/>" +
                "And waiting for '?action=include' or '?action=forward' parameter input ...<br><br>");
        String action = request.getParameter("action");
        try {
            PrintWriter html = response.getWriter();
            html.println("<form method=\"post\" action =\"" + request.getContextPath() + "/include-servlet\" >"
                         + "<input type=\"submit\"  name=\"action\" value=include>"
                         + "<input type=\"submit\"  name=\"action\" value=forward>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (action != null) {
            RequestDispatcher rd = request.getRequestDispatcher("included-servlet");
            if ("include".equalsIgnoreCase(action)) {
                rd.include(request, response);
            } else if ("forward".equalsIgnoreCase(action)) {
                rd.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
