/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import connection.ConnectionPostgreSQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Assistance;
import models.Conference;


@WebServlet(name = "SrvAssistance3", urlPatterns = {"/createAssistance"})
public class SrvAssistance3 extends HttpServlet {

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
        PrintWriter out = response.getWriter();
                
        try {            
            
            Gson gson = new Gson();
            String ci = request.getParameter("ci");
            String name = request.getParameter("name");
            String last_name = request.getParameter("last_name");
            String conference = request.getParameter("conference");
            String email = request.getParameter("email");

            if(Assistance.isValid(ci, name, last_name, conference, email)){
                Connection connection = ConnectionPostgreSQL.connect2DB();

                if(connection != null){
                    Statement cs = connection.createStatement();
                    String queryString = "insert into assistance(ci, name, last_name, conference, email) values ('" + ci + "', '" + name + "', '" + last_name + "', '" + conference + "', '" + email +  "')";  
                    cs.executeUpdate(queryString);
                    ConnectionPostgreSQL.disconnectDB(connection);
                    
                    out.print("{'result': 'ok'}");
                }

                ConnectionPostgreSQL.disconnectDB(connection);
            }
            
        } catch (Exception e) {
            e.getStackTrace();
            System.err.println(e.toString());
            out.print("{'result': 'ok'}");
        } finally{
            out.close();
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
