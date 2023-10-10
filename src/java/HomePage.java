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

/**
 *
 * @author admin
 */
public class HomePage extends HttpServlet {

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
            response.setHeader("Cache-Control", "No-Cache");
            response.setHeader("Cache-Control", "No-Store");
            HttpSession session = request.getSession();
            if (session.getAttribute("rs") != null) {                
                out.print("<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "    <meta charset=\"UTF-8\">\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    <title>Document</title>\n"
                        + "    <link rel=\"stylesheet\" href=\"HomePage.css\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "    <div id=\"first\">\n"
                        + "        <div id=\"firstOne\"><img src=\"hap.png\" id=\"logo\"></div>\n"
                        + "        <div id=\"firstTwo\"><input type=\"search\" name=\"search\" placeholder=\"Search\" id=\"search\"></div>\n"
                        + "        <div id=\"firstThree\">\n"
                        + "            <a href=\"#\">Home</a>\n"
                        + "            <a href=\"#\">About Us</a>\n"
                        + "            <a href=\"#\">Contact Us</a>\n"
                        + "            <a href=\"Profile\">My Profile</a>\n"
                        + "            <a href=\"Logout\">Logout</a>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div id=\"second\"></div>\n"
                        + "    <div id=\"third\"></div>\n"
                        + "</body>\n"
                        + "</html>");
            } else {
                response.sendRedirect("signin");
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
