<%@page isErrorPage="true" import="java.io.PrintWriter" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
    <head>
        <%@ include file="header.jsp" %>        
    </head>
    <body>
        <%@ include file="menu.jsp" %>

        <div class="content">
            <h3>Error</h3>

            <div style="font-size: 20px; font-family: verdana;">
                <p>A ocurrido un Error en el Sistema</p>
                <img src="${pageContext.request.contextPath}/img/error.jpg" width="666" height="500" />
            </div>
        </div>
    </body>
</html>