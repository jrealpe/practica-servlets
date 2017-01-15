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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Assistance;
import models.Conference;


@WebServlet(name = "SrvAssistance2", urlPatterns = {"/getAsistencias"})
public class SrvAssistance2 extends HttpServlet {

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
            List<Assistance> list = new ArrayList<>();
            Gson gson = new Gson();

            if(connection != null){
                Statement cs = connection.createStatement();
                String queryString = "select a.id as id, a.ci as ci, a.name as name, a.last_name as last_name, a.email as email, c.id as conference_id, c.name as conference_name from assistance a, conference c where c.id=a.conference";

                ResultSet resultSet = cs.executeQuery(queryString);
                ConnectionPostgreSQL.disconnectDB(connection);
                Assistance assistance;

                if (resultSet != null){
                    while (resultSet.next()) {
                        assistance = new Assistance();
                        assistance.setId(resultSet.getString("id"));
                        assistance.setCi(resultSet.getString("ci"));
                        assistance.setName(resultSet.getString("name"));
                        assistance.setLast_name(resultSet.getString("last_name"));
                        assistance.setConference_name(resultSet.getString("conference_name"));
                        assistance.setConference_id(resultSet.getString("conference_id"));
                        assistance.setEmail(resultSet.getString("email"));
                        list.add(assistance);
                    }

                }

                out.print(gson.toJson(list));
            }
            
        } catch (Exception e) {
            e.getStackTrace();
            System.err.println(e.toString());                        
            out.print("{'result': 'error'}");
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
