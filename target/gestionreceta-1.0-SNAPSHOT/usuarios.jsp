<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- Importación de la biblioteca de funciones JSTL -->
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuarios</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Fecha de Registro</th>
            <th>Estado</th>
            <th>Acceso al Sistema</th>
            <th>Foto</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.idUsuario}</td>
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido}</td>
                <td>${usuario.email}</td>
                <td>${usuario.fechaRegistro}</td>
                <td>${usuario.estado}</td>
                <td>${usuario.accesoSistema ? "Sí" : "No"}</td>
                <td>
                    <c:choose>
                        <c:when test="${usuario.fotoBase64 != null}">
                            <img src="data:image/jpeg;base64,${usuario.fotoBase64}" alt="Foto de Usuario" width="50" height="50"/>
                        </c:when>
                        <c:otherwise>
                            No hay foto
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/usuarios?action=edit&id=${usuario.idUsuario}">Editar</a> |
                    <a href="${pageContext.request.contextPath}/usuarios?action=delete&id=${usuario.idUsuario}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!-- Formulario para agregar un nuevo usuario -->
    <h2>Agregar Nuevo Usuario</h2>
    <form action="${pageContext.request.contextPath}/usuarios" method="post" enctype="multipart/form-data">
        <!-- Campos de entrada para el usuario -->
        <label for="nombre">Nombre:</label><br>
        <input type="text" id="nombre" name="nombre" required><br>
        <label for="apellido">Apellido:</label><br>
        <input type="text" id="apellido" name="apellido" required><br>
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br>
        <label for="clave">Contraseña:</label><br>
        <input type="password" id="clave" name="clave" required><br>
        <label for="estado">Estado:</label><br>
        <select id="estado" name="estado">
            <option value="ACTIVO">ACTIVO</option>
            <option value="INCAPACITADO">INCAPACITADO</option>
            <option value="DESPEDIDO">DESPEDIDO</option>
            <option value="RENUNCIO">RENUNCIO</option>
        </select><br><br>
        <label for="accesoSistema">Acceso al Sistema:</label>
        <input type="checkbox" id="accesoSistema" name="accesoSistema" value="true" checked><br><br>
        <label for="foto">Foto:</label><br>
        <input type="file" id="foto" name="foto"><br><br>
        <input type="submit" value="Agregar Usuario">
    </form>
</body>
</html>
