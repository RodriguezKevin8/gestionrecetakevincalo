<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(to right, #4e54c8, #8f94fb);
            color: #333;
        }
        .container {
            max-width: 400px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .profile-img {
            border-radius: 50%;
            margin-bottom: 20px;
        }
        .btn-logout {
            background-color: #dc3545;
            color: white;
            border: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mb-4">Bienvenido, Usuario</h1>

        <!-- Mostrar la imagen de perfil si está disponible -->
        <c:choose>
            <c:when test="${not empty sessionScope.usuarioFotoBase64}">
                <img src="data:image/jpeg;base64,${sessionScope.usuarioFotoBase64}" alt="Foto de Usuario" class="profile-img" width="100" height="100"/>
            </c:when>
            <c:otherwise>
                <p>No hay foto de perfil</p>
            </c:otherwise>
        </c:choose>

        <p><strong>ID de Usuario:</strong> ${sessionScope.usuarioId}</p>
        <p><strong>Tipo de Usuario:</strong> ${sessionScope.usuarioTipo}</p>

        <h2 class="my-4">Contenido del Usuario</h2>
        <p>Esta es la página de inicio para los usuarios registrados.</p>

        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit" class="btn btn-logout">Cerrar Sesión</button>
        </form>
    </div>
</body>
</html>
