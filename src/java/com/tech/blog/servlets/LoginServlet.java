package com.tech.blog.servlets;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //        login
            //        Fetch username and password from request
            String email = request.getParameter("user_email");
            String password = request.getParameter("user_password");

            UserDao dao = new UserDao(ConnectionProvider.getConnection());
            User user = dao.getUserByEmailAndPassword(email, password);

            if (user == null) {

                //login...........
                //error///
                //response.sendRedirect("error_page.jsp");
                //out.println("Invalid details...try again");
                Message msg = new Message("Invalid Details  ! Try with another", "error", "alert-danger");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);

                response.sendRedirect("login_page.jsp");

            } else {
                //..
                //login success
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);
                response.sendRedirect("profile.jsp");

            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
