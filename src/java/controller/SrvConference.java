/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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


@WebServlet(name = "SrvConference", urlPatterns = {"/Conferencias"})
public class SrvConference extends HttpServlet {

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
            
            
            String _action = request.getParameter("action");
            System.out.println("************* Conference -> " + _action);
            if (_action != null){
                if (_action.equals("get")) {
                    getConferences(request, response);
                } else if (_action.equals("create")) {
                    createConference(request, response);
                } else if (_action.equals("update")) {
                    updateConference(request, response);
                } else if (_action.equals("delete")) {
                    deleteConference(request, response);
                } else{
                    //Error 404
                }
            } else{
                getConferences(request, response);
            }
            
        } catch (Exception e) {
            e.getStackTrace();
            System.err.println(e.toString());            
                        
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
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
                
        Connection connection = ConnectionPostgreSQL.connect2DB();
        List<Conference> list = new ArrayList<>();

        
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
            
            request.setAttribute("vsDat", list);
            RequestDispatcher rd = request.getRequestDispatcher("/conference.jsp");
            rd.forward(request, response);
        }
    }
    
    private void createConference(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String description = request.getParameter("description");

        if(Conference.isValid(name, date, description)){
            Connection connection = ConnectionPostgreSQL.connect2DB();
            
            if(connection != null){
                Conference conference;
                Statement cs = connection.createStatement();
                String queryString = "insert into conference(name, date, description) values ('" + name + "', '" + date + "', '" + description + "')";                    
                cs.executeUpdate(queryString);
                ConnectionPostgreSQL.disconnectDB(connection);
                
                getConferences(request, response);
            }
            
            ConnectionPostgreSQL.disconnectDB(connection);
        }
        
    }
    
    private void deleteConference(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        String id = request.getParameter("id");        

        if(id != null){
            Connection connection = ConnectionPostgreSQL.connect2DB();
            
            if(connection != null){
                Conference conference;
                Statement cs = connection.createStatement();
                String queryString = "delete from conference where id = '" + id +"'";
                cs.executeUpdate(queryString);
                ConnectionPostgreSQL.disconnectDB(connection);
                
                getConferences(request, response);
            }
        }
    }
    
    private void updateConference(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String description = request.getParameter("description");

        if(Conference.isValid(name, date, description)){
            Connection connection = ConnectionPostgreSQL.connect2DB();
            
            if(connection != null){
                Conference conference;
                Statement cs = connection.createStatement();
                String queryString = "update conference set name='" + name + "', date='" + date + "', description='" + description + "'";
                cs.executeUpdate(queryString);
                ConnectionPostgreSQL.disconnectDB(connection);
                
                getConferences(request, response);
            }
            
            ConnectionPostgreSQL.disconnectDB(connection);

        }
        
    }
}
