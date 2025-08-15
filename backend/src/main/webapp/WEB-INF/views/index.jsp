<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Home</title></head>
<body>
  <h1>Home</h1>
  <p><a href="<c:url value='/login'/>">Login</a> | <a href="<c:url value='/logout'/>">Logout</a></p>

  <h3>Principal</h3>
  <pre>${pageContext.request.userPrincipal}</pre>

  <h1>Real-time Chat</h1>
  <form action="/room" method="get">
    <input type="text" name="id" placeholder="room id" required />
    <input type="text" name="name" placeholder="your name"/>
    <button type="submit">Enter</button>
  </form>
</body>
</html>