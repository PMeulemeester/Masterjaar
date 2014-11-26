<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<h2>Uw reservatie:</h2>
<p>Concert: <b><c:out value="${concert.titel}"/></b>
<p>Naam: <b><c:out value="${reservatie.gebruikersnaam}"/></b></p>
<p>Aantal normale plaatsen aan &euro;<c:out value="${concert.prijsNormaal}"/>: <b><c:out value="${reservatie.aantalNormaal}"/></b></p>
<p>Aantal reductieplaatsen aan &euro;<c:out value="${concert.prijsReductie}"/>: <b><c:out value="${reservatie.aantalReductie}"/></b></p>

<p>Totaal bedrag: <b>&euro;<c:out value="${reservatie.aantalNormaal*concert.prijsNormaal + reservatie.aantalReductie*concert.prijsReductie}"/></b>.

<p>
<form action="bev.do" method="get">
    <input type="submit" value="bevestig">
</form>
<form action="reservatie.do" method="post">
    <input type="hidden" name="aantalNormaal" value="${reservatie.aantalNormaal}">
    <input type="hidden" name="aantalReductie" value="${reservatie.aantalReductie}">
    <input type="hidden" name="naam" value="${reservatie.gebruikersnaam}">
    <input type="submit" value="wijzig">
</form>

