<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Login</title></head>
<body>
  <h1>Login</h1>
  <ul>
    <li><a href="<c:url value='/oauth2/authorization/google'/>">Login with Google</a></li>
    <li><a href="<c:url value='/oauth2/authorization/kakao'/>">Login with Kakao</a></li>
    <li><a href="<c:url value='/oauth2/authorization/naver'/>">Login with Naver</a></li>
  </ul>
</body>
</html>