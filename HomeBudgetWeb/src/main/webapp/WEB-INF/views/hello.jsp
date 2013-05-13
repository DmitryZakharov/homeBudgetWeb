<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h3>Hello ${username} ${usersurname}</h3>	
	
	<a href="<c:url value="/account.html" />" > accounts details</a>
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
</body>
</html>