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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Conference;


@WebServlet(name = "SrvConference2", urlPatterns = {"/Conferencias2"})
public class SrvConference2 extends HttpServlet {

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
            Connection connection = ConnectionPostgreSQL.connect2DB();
            List<Conference> list = new ArrayList<>();
            Gson gson = new Gson();

            if(connection != null){
                Statement cs = connection.createStatement();
                String queryString = "select * from conference";            

                ResultSet resultSet = cs.executeQuery(queryString);
                ConnectionPostgreSQL.disconnectDB(connection);
                Conference conference;

                if (resultSet != null){
                    while (resultSet.next()) {
                        conference = new Conference();
                        conference.setId(resultSet.getInt("id"));
                        conference.setName(resultSet.getString("name"));
                        conference.setDate(resultSet.getDate("date"));
                        conference.setDescription(resultSet.getString("description"));
                        list.add(conference);
                    }

                }

                out.print(gson.toJson(list));                
            }
            
        } catch (Exception e) {
            e.getStackTrace();
            System.err.println(e.toString());                        
            out.print("error");
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

    private void getConferences(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
                
        
    }
}
