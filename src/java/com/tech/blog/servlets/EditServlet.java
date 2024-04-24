package com.tech.blog.servlets;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;

@WebServlet("/EditServlet")
@MultipartConfig
public class EditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

//            fetch all data
            String name = request.getParameter("user_name");
            String email = request.getParameter("user_email");
            String password = request.getParameter("user_password");
            String about = request.getParameter("user_about");
            Part part = request.getPart("image");
            String imageName = part.getSubmittedFileName();

            //get the user from the session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setAbout(about);
            String oldFile = user.getProfile();

            String oldFilePath = request.getServletContext().getRealPath("/") + "pics" + File.separator + oldFile;

            user.setProfile(imageName);

            //update the user in the database
            UserDao dao = new UserDao(ConnectionProvider.getConnection());
            boolean ans = dao.updateUser(user);
            if (ans) {

                //FIRST OPTION TO GET PATH UP TO Webpages
                //ServletContext context=request.getServletContext();
                //String path=context.getRealPath("/");
                String path = request.getServletContext().getRealPath("/") + "pics" + File.separator + user.getProfile();
                out.println(path);

                //delete code
                if (!oldFile.equals("dafault.png")) {
                    Helper.deleteFile(oldFilePath);
                }

                if (Helper.saveFile(part.getInputStream(), path)) {

                    out.println("Profile updated");
                    Message msg = new Message("Profile detail's updated", "success", "alert-success");
                    session.setAttribute("msg", msg);

                } else {
                    out.println("file not saved successfully !..");
                    Message msg = new Message("Something went wrong..", "error", "alert-danger");
                    session.setAttribute("msg", msg);
                }

            } else {
                out.println("not updated to db");
                Message msg = new Message("Something went wrong..", "error", "alert-danger");
                session.setAttribute("msg", msg);
            }
            response.sendRedirect("profile.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
