/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author admin
 */
public class ChangePass extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Document</title>\n"
                    + "    <link rel=\"stylesheet\" href=\"change.css\">\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    <form>\n"
                    + "    <div id=\"container\">\n"
                    + "        <div id=\"first\">\n"
                    + "            <div id=\"divone\">Change Password</div>\n"
                    + "            <div class=\"divtwo\">Enter Your old password:</div>\n"
                    + "            <div class=\"divtwo\"><input type=\"password\" name=\"old\" class=\"input\" required></div>\n"
                    + "            <div class=\"divtwo\">Enter Your new password:</div>\n"
                    + "            <div class=\"divtwo\"><input type=\"password\" name=\"newpass\" class=\"input\" required></div>\n"
                    + "            <div class=\"divtwo\">Confirm Password</div>\n"
                    + "            <div class=\"divtwo\"><input type=\"password\" name=\"confirm\" class=\"input\" required></div>\n");
                 
           
                
           
            String op = request.getParameter("old");
            String p = request.getParameter("newpass");
            String cp = request.getParameter("confirm");
//            if (p.equals(cp)) {
            Connection con = null;
            try {
                HttpSession session = request.getSession();
                String oldpass = (String) session.getAttribute("pass");
                if (!(p.equals(cp))) {
                    out.print("<div style='background-color:white; color:red; font-size:20px' class='cp'>New Password and confirm password does not match.</div>");
                } else if (!(op.equals(oldpass))) {
                    out.print("<div style='background-color:white; color:red; font-size:20px' class='cp'>old password incorrect</div>");
                } else {

                    String email = (String) session.getAttribute("email");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    System.out.println("Driver Loaded");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServletShop", "root", "Info@1234");
                    System.out.println("Connection Done");
//                String sql = "update shop set Fullname = ? where email = ?";

                    PreparedStatement ps = con.prepareStatement("update shop set pass = ? where email = ?");
                    ps.setString(1, p);
                    ps.setString(2, email);
                    int n = ps.executeUpdate();
                    if (n > 0) {
                        out.print("<div  style='background-color:lightgreen;'>Changes Saved</div>");
                    } else {
                        out.print("<div style='background-color:red;'>Changes Not Saved</div>");
                    }
                }
//                       else
//                       {
//                            out.print("<div style='background-color:#B36B40; color:red; font-size:20px' class='cp'>Incorrect old password</div>");
//                       }
            } catch (Exception e) {
                System.out.println(e);
            }
            out.print("            <div id=\"button\"><input type=\"submit\" value=\"Save\"></div>\n");
             out.print("            <div class=\"divtwo\"><a href='Profile'>Back</a></div>\n");
             out.print("    </div>\n"
                         + "        </div>\n"
                    + "</form>\n"
                    + "</body>\n"
                    + "</html>");
        }
//        else {
//                out.print("<div style='background-color:#B36B40; color:red; font-size:20px' class='cp'>New Password and confirm password does not match.</div>");
//
//            }
//            

    

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
