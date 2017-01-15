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
            <h4>Asistencias a conferencias</h4>
            <button id="btn-create">Registrar Nueva</button>
            
            <table id="table-assistences" class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Cédula</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Conferencia</th>
                        <th>Correo</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody></tbody>
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
                        <form id="form-add" action="#" method="post"
                              style="display: inline-block;" role="form">
                                                        
                            <input type="hidden" id="id" name="id" value=""/>
                            
                            <div class="form-group">
                                <input type="text" class="form-control"
                                       id="ci" name="ci" placeholder="Cédula" required/>
                            </div>
                            
                            <div class="form-group">
                                <input type="text" class="form-control"
                                       id="name" name="name" placeholder="Nombre de asistente" required/>
                            </div>
                            
                            <div class="form-group">
                                <input type="text" class="form-control"
                                       id="last_name" name="last_name" placeholder="Apellido de asistente" required/>
                            </div>
                            
                            <div class="form-group">
                                <select id="conference" name="conference" required></select>
                            </div>
                            
                            <div class="form-group">
                                <input type="email" class="form-control"
                                       id="email" name="email" placeholder="Correo de asistente" required/>
                            </div>
                            
                          <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
                              
        <!-- Modal DELETE -->
        <div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
        <script src="${pageContext.request.contextPath}/js/action2.js"></script>
    </body>
</html>
