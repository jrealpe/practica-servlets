<%@page import="models.Conference"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@ include file="header.jsp" %>
    </head>
    <body>
        <%@ include file="menu.jsp" %>
        <div class="clear"></div>
        <div class="content">
            <h4>Conferencia a realizarse</h4>
            <button id="btn-create">Registrar Nueva</button>
            
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                      <th>Nombre</th>
                      <th>Fecha</th>
                      <th>Description</th>
                      <th></th>
                    </tr>
                </thead>
                <tbody>
                  
                    <%
                        List<Conference> conferences = (List<Conference>) request.getAttribute("vsDat");
                        if (!conferences.isEmpty()){
                            for (Conference conference : conferences) {
                    %>
                    <tr>
                        <input type="hidden" id="name-<%= conference.getId() %>" value="<%= conference.getName() %>" />
                        <input type="hidden" id="date-<%= conference.getId() %>" value="<%= conference.getDate() %>" />
                        <input type="hidden" id="description-<%= conference.getId() %>" value="<%= conference.getDescription() %>" />
                        <td><%= conference.getName() %></td>
                        <td><%= conference.getDate() %></td>
                        <td><%= conference.getDescription() %></td>
                        <td>                          
                            <a href="#" onclick="return false;"><span data-id="<%= conference.getId() %>" class="edit glyphicon glyphicon-edit" aria-hidden="true" title="Editar"></span></a>
                            <span style="padding-right: 5px;"></span>
                            <a href="#" onclick="return false;"><span data-id="<%= conference.getId() %>" class="delete glyphicon glyphicon-remove" aria-hidden="true" title="Eliminar"></span></a>
                        </td>                      
                    </tr>
                    <%
                            }
                        }
                    %>             
              </tbody>
            </table>
        </div>
              
        <!-- Modal EDIT and CREATE -->
        <div class="modal fade" id="modalConference" tabindex="-1" role="dialog" 
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <button type="button" class="close" 
                           data-dismiss="modal">
                               <span aria-hidden="true">&times;</span>
                               <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="title"></h4>
                    </div>

                    <!-- Modal Body -->
                    <div class="modal-body">
                        <form id="form" action="${pageContext.request.contextPath}/Conferencias" method="post"
                              style="display: inline-block;" role="form">
                            
                            <input type="hidden" id="action" name="action" value=""/>
                            <input type="hidden" id="id" name="id" value=""/>
                            
                            <div class="form-group">
                                <input type="text" class="form-control"
                                       id="name" name="name" placeholder="Nombre de la conferencia" required/>
                            </div>
                            
                            <div class="form-group">
                                <div class='input-group date' id='datepicker'>                                    
                                    <input name="date" type='date' class="form-control" required/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <textarea rows="4" cols="50"
                                       id="description" name="description" placeholder="   Algo que agregar...?"></textarea>
                            </div>
                            
                          <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
                              
        <!-- Modal DELETE -->
        <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <center>
                            <p>Eliminar</p>
                            <p>Seguro que desea eliminar este registro?</p>
                        </center>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                        <button id="btn-confirm" type="button" class="btn btn-danger btn-ok" data-dismiss="modal">Si</button>                        
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/action.js"></script>
    </body>
</html>
