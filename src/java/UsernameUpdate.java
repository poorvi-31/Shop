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
public class UsernameUpdate extends HttpServlet {

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
 /* TODO output your page here. You may use following sample code. */
//            out.print("<!DOCTYPE html>\n"
//                    + "<html lang=\"en\">\n"
//                    + "<head>\n"
//                    + "    <meta charset=\"UTF-8\">\n"
//                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
//                    + "    <title>Document</title>\n"
//                    + "    <link rel=\"stylesheet\" href=\"profile.css\">\n"
//                    + "</head>\n"
//                    + "<body>\n"
//                    + "    <div>\n"
//                    + "        <div><form action='UsernameUpdate'><input type=\"text\" placeholder=\"Enter your new username:\" name=\"edit\">\n"
//                    + "        <input type=\"submit\" value=\"Save\"></div></div>\n"
//                    + "</form></body>\n"
//                    + "</html>");
            out.print("<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Document</title>\n"
                    + "    <link rel=\"stylesheet\" href=\"update.css\">\n"
                    + "</head>\n"
                    + "<body>\n"
                    +"<form\n"
                    + "    <div class=\"container\">\n"
                    + "        <div class=\"first\">\n"
                    + "            <div class=\"input\" id=\"head\"><b><u>Enter Your New Username</u></b></div>\n"
                    + "            <div class=\"input\"><b>Username</b></div>\n"
                    + "            <div class=\"input\"><input type=\"text\" name=\"us\"></div>\n"
                    + "            <div class=\"input\"><button type=\"submit\" value=\"Save\">Save</button></div>\n"
                    + "            <div class=\"input\"><a href=\"#\">Back</a></div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
            );
               out.print("</form>\n"
                    + "</body>\n"
                    + "</html>");
            String p = request.getParameter("us");
//             for (int i = 0; i < p.length(); i++) {
//
//                        if ((p.charAt(i) < 65 || p.charAt(i) > 90) && (p.charAt(i) < 97 || p.charAt(i) > 122)) {
//
//                            out.print("<div style='color:red; margin-top:10px;'>Name cannot have numbers.</div> ");
//                            out.print("     "
//                                    + "    </div>");
//                            return;
//                        }
                   
            Connection con = null;
            try {
                
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("email");
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver Loaded");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServletShop", "root", "Info@1234");
                System.out.println("Connection Done");
//                String sql = "update shop set Fullname = ? where email = ?";

                PreparedStatement ps = con.prepareStatement("update shop set username = ? where email = ?");
                ps.setString(1, p);
                ps.setString(2, email);
                int n = ps.executeUpdate();
                if (n > 0) {
                    out.print("<div>Changes Saved</div>");
                } else {
                    out.print("<div>Changes Not Saved</div>");
                }
            } catch (Exception e) {
                System.out.println(e);
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
