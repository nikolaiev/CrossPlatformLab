<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Insert title here</title>
    </head>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <body>
    Average : <c:out value="${aver}"/>
    <br/>
    Sum : <c:out value="${sum}"/>
    <br/>


    <br>
    Max elem val : <c:out value="${maxElemInd}"/>
    <br>
    Max elem val index :  <c:out value="${maxElemVal}"/>
    <br/>


    <br>
    Min elem val :<c:out value="${minElemInd}"/>
    <br>
    Min elem val index : <c:out value="${minElemVal}"/>
    <br/>
    <br>
    <br>
    <button onclick="location.href='/'">change params</button>
    <br/>
    <br/>
    Result:
    <table>
    <thead>
    <th>arg value</th>
    <th>function</th>
    </thead>
    <c:forEach items="${sessionScope.result}" var="entry">
        <tr>
            <td>
                <c:out value="${entry.key}"/>
            </td>

            <td>
                <c:out value="${entry.value}"/>
            </td>
        </tr>
    </c:forEach>
    </table>
    </body>
    </html>