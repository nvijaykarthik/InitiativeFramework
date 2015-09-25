<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>

<table>
<thead>
<tr>
	<th>Name</th>
        <th>Description</th>
        <th>Date</th>
        <th>Version</th>
        <th>Activate</th>
        <th>refPath</th>

</tr>
</thead>
<tbody>
<c:forEach items="${modules}" var="p">
    <tr>
        <td><c:out value="${p.name}"/></td>
        <td><c:out value="${p.description}"/></td>
        <td><c:out value="${p.date}"/></td>
        <td><c:out value="${p.version}"/></td>
        <td><c:out value="${p.activated}"/></td>
        <td><c:out value="${p.refPath}"/></td>
    </tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
	