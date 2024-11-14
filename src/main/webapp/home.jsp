<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home - Usuario</title>
    </head>
    <body>
        <h1>Bienvenido</h1>

        <p>ID de Usuario: ${sessionScope.usuarioId}</p>
        <p>Tipo de Usuario: ${sessionScope.usuarioTipo}</p>

        <h2>Contenido del Usuario</h2>
        <p>Esta es la página de inicio para los usuarios registrados.</p>

        <!-- Opcional: enlace para cerrar sesión -->
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Cerrar Sesión">
        </form>

    </body>
</html>

