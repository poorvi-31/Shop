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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author admin
 */
public class log extends HttpServlet {

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
            out.print(
                    "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Document</title>\n"
                    + "    <link rel=\"stylesheet\" href=\"login.css\">\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    \n"
                    + "<form><div id=\"container\">\n"
                    + "        <div id=\"page\">\n"
                    + "            <div id=\"pageOne\">\n"
                    + "                <div id=\"sign\"><b>Sign Up</b></div>\n"
                    + "                <div>Full Name</div>\n"
                    + "                <div><input type=\"text\" name=\"fullname\" placeholder=\"Name...\" required></div>\n"
                    + "                <div>Email</div>\n"
                    + "                <div><input type=\"email\" name=\"email\" placeholder=\"Email...\" required></div>\n"
                    + "                <div>Username</div>\n"
                    + "                <div><input type=\"username\" name=\"username\" placeholder=\"Username\" required></div>\n"
                    + "                <div>Password</div> \n"
                    + "                <div><input type=\"password\" name=\"pass\" placeholder=\".........\" maxlength='8' required></div>\n"
                    + "                <div>Confirm Password</div>\n"
                    + "                <div><input type=\"password\" name=\"cpass\" placeholder=\".........\" maxlength='8' required></div>\n"
                    + "                <div id=\"buttons\"><button name='sign' value='up'>Sign up</button></form><form action='signin'><button  name='sign' value='in'>Sign in</button></form></div>\n"
                    + "            </div>\n"
                    + "            <div id=\"pageTwo\"></div>\n</div>\n");

            String button = request.getParameter("sign");
            switch (button) {

                case "up":
                    String fn = request.getParameter("fullname");
                    String em = request.getParameter("email");
                    String us = request.getParameter("username");
                    String p = request.getParameter("pass");
                    String cp = request.getParameter("cpass");
//                    StringBuilder validationMessage = new StringBuilder();

                    for (int i = 0; i < fn.length(); i++) {

                        if ((fn.charAt(i) < 65 || fn.charAt(i) > 90) && (fn.charAt(i) < 97 || fn.charAt(i) > 122)) {

                            out.print("<div style='color:red; margin-top:10px;'>Name cannot have numbers.</div> ");
                            out.print("     "
                                    + "    </div>");
                            return;
                        }
//                        } else if (fn.charAt(i) < 97 || fn.charAt(i) > 122) {
//
//                            out.print("<div style='color:red; margin-top:10px;'> name and Confirm Password do not match</div> ");
//                            out.print("     "
//                                    + "    </div>");
//                            return;
//                        }
                    }
//                    if () {
////                        validationMessage.append("Password and Confirm Password do not match.<br>");
//                          out.print("<div style='color:red; margin-top:10px;'>Password and Confirm Password do not match</div> ");
//                    out.print("     "
//                   +"    </div>");         
//                    } 
//                    if(fn.equals(null)||em.equals(null)||us.equals(null)||p.equals(null)||cp.equals(null))
//                    {
//                          out.print("<div style='color:red; margin-top:10px;'></div> ");
//                    out.print("     "
//                   +"    </div>");  
//                    }
                    if (!p.equals(cp)) {
//                        validationMessage.append("Password and Confirm Password do not match.<br>");
                        out.print("<div style='color:red; margin-top:10px;'>Password and Confirm Password do not match</div> ");
                        out.print("     "
                                + "    </div>");
                    } else {
                        try {

                            Class.forName("com.mysql.cj.jdbc.Driver");
                            System.out.println("Driver Loaded");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServletShop", "root", "Info@1234");
                            System.out.println("Connection Done");
                            String sql = "insert into shop values(?,?,?,?)";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, fn);
                            ps.setString(2, em);
                            ps.setString(3, us);
                            ps.setString(4, p);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                response.sendRedirect("HomePage");
                            }
                        } catch (Exception e) {
                            response.sendRedirect("log");

                        }
                    }
                    break;

            }

            out.print("</body>\n"
                    + "</html>");

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
