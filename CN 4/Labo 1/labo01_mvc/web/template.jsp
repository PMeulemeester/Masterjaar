<%-- 
    Document   : masterpage
    Created on : 3-okt-2011, 6:47:04
    Author     : Wijnand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="site.css"/>
        <title>Concertreservatie: ${titel}</title>
    </head>
    <body>
        <h1>Concertreservatie: ${titel}</h1>
        
       <jsp:include page="${view}" /> 
        
        <!-- viewing ${view} -->
    </body>
</html>
