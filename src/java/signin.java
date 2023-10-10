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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class signin extends HttpServlet {

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
            
            out.print("<form >"
                    + "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Document</title>\n"
                    + "    <link rel=\"stylesheet\" href=\"signin.css\">\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    \n"
                    + "    <div id=\"container\">\n"
                    + "        <div id=\"page\">\n"
                    + "            <div id=\"pageTwo\">\n"
                    + "               \n"
                    + "            </div>\n"
                    + "            <div id=\"pageOne\">\n"
                    + "                <div id=\"sign\"><b>Sign In</b></div>\n"
                    + "                <div>Username</div>\n"
                    + "                <div><input type=\"username\" name=\"username\" placeholder=\"Username\" required></div>\n"
                    + "                <div>Password</div> \n"
                    + "                <div><input type=\"password\" name=\"pass\" placeholder=\".........\" required></div>\n"
                    + "                <div id=\"buttons\"><button>Sign in</button></div>\n");
                    out.print("<div><a href='log'>New user?</a></div>");
                
            String us = request.getParameter("username");
            String p = request.getParameter("pass");
            Connection con = null;
            if (!us.equals(null) && !p.equals(null)) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    System.out.println("Driver Loaded");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServletShop", "root", "Info@1234");
                    System.out.println("Connection Done");
                    String sql = "select * from shop where username=? and pass=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, us);
                    ps.setString(2, p);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                         HttpSession session = request.getSession();
                    session.setAttribute("username",rs.getString("username"));
                     session.setAttribute("pass",rs.getString("pass"));
                     session.setAttribute("email",rs.getString("email"));
                      session.setAttribute("fullname",rs.getString("Fullname"));
                          session.setAttribute("rs",rs);
                        response.sendRedirect("HomePage");
                    } else {
                        out.println("<div style='color:red'>Invalid Username/Password</div>");
                        out.println("</div></div></div>");
                         out.print("</body>\n"
                    + "</html></form>");
                    }

//                    rs.close();
                    ps.close();
                    con.close();

                } catch (Exception e) {
                    response.sendRedirect("signin");
//                out.print("hiiii....");

                } finally {

                    try {
                        con.close();
                    } catch (SQLException ex) {

                    }
                }
            }
       
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
