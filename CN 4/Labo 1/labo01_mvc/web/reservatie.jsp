<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Concert: ${concert.titel}</h2>

<form action="naarBevestiging.do" method="post">

    <p class="error">${fout}</p>
    <table>
        <tr>
            <th>Categorie</th>
            <th>Prijs</th>
            <th>Aantal</th>
        </tr>
        <tr>
            <td>Normaal</td>
            <td>&euro;${concert.prijsNormaal}</td>
            <td><input name="aantalNormaal" type="text" maxlength="2" size="2" value="${param.aantalNormaal}"/></td>
        </tr>
        <tr>
            <td>Reductie (-25, +65)</td>
            <td>&euro;${concert.prijsReductie}</td>
            <td><input name="aantalReductie" type="text" maxlength="2" size="2" value="${param.aantalReductie}"/></td>
        </tr>
    </table>
    <p>Naam: <input name="naam" type="text" value="${param.naam}"/></p>
    <p><input type="submit" value="Reserveer" /></p>
        
   

</form>