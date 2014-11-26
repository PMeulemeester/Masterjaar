<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<h1>Concerten</h1>
        
<table>
<c:forEach items="${concertDao.alleConcerten}" var="entry" >
        <tr>
        <td>${entry.value.titel}</td>
        <td><a href="<c:url value='reservatie.do'>
                        <c:param name='concertid' value='${entry.key}'/>
                     </c:url>">Reserveer</a></td>
    </tr>
</c:forEach>
</table>
