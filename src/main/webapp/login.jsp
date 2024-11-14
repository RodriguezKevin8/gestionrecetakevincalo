<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesi�n</title>
</head>
<body>
    <h1>Iniciar Sesi�n</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br>

        <label for="password">Contrase�a:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Iniciar Sesi�n">
    </form>
    <c:if test="${not empty errorMessage}">
        <p style="color:red">${errorMessage}</p>
    </c:if>
</body>
</html>

