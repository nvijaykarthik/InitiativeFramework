<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>

<table border="1">
<thead>
<tr>
	<th>Name</th>
        <th>Description</th>
        <th>Date</th>
        <th>Version</th>
        <th>project</th>
		<th>Operation</th>
</tr>
</thead>
<tbody>
<c:forEach items="${modules}" var="p">
    <tr>
    <c:if test="${!p.activated}"><c:set var="action" scope="session" value="activate"/></c:if>
    <c:if test="${p.activated}"><c:set var="action" scope="session" value="deActivate"/></c:if>
    <form:form action="${action}"  method="POST" modelAttribute="module">
        <td><c:out value="${p.name}"/></td>
        <td><c:out value="${p.description}"/></td>
        <td><c:out value="${p.date}"/></td>
        <td><c:out value="${p.version}"/></td>
        <td><c:out value="${p.project}"/></td>
        <td>
        <input id="name" name="id" type="hidden" value="${p.id}"/>
         <input id="name" name="name" type="hidden" value="${p.name}"/>
         <input id="description" name="description" type="hidden" value="${p.description}"/>
         <input id="date" name="date" type="hidden" value="${p.date}"/>
         <input id="version" name="version" type="hidden" value="${p.version}"/>
         <input id="project" name="project" type="hidden" value="${p.project}"/>
         <c:if test="${p.activated}"><input type="submit" value="DeActivate"/> </c:if>
         <c:if test="${!p.activated}"><input type="submit" value="Activate"/> </c:if>
         </td>
        </form:form>    
    </tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
	