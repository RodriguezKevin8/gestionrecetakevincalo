<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Usuario</title>
    </head>
    <body>
        <h1>Editar Usuario</h1>
        <form action="${pageContext.request.contextPath}/usuarios" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${usuario.idUsuario}">

            <label for="nombre">Nombre:</label><br>
            <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required><br>

            <label for="apellido">Apellido:</label><br>
            <input type="text" id="apellido" name="apellido" value="${usuario.apellido}" required><br>

            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" value="${usuario.email}" required><br>

            <label for="clave">Contraseña:</label><br>
            <input type="password" id="clave" name="clave" value="${usuario.clave}" required><br>

            <label for="estado">Estado:</label><br>
            <select id="estado" name="estado">
                <option value="ACTIVO" ${usuario.estado == 'ACTIVO' ? 'selected' : ''}>ACTIVO</option>
                <option value="INCAPACITADO" ${usuario.estado == 'INCAPACITADO' ? 'selected' : ''}>INCAPACITADO</option>
                <option value="DESPEDIDO" ${usuario.estado == 'DESPEDIDO' ? 'selected' : ''}>DESPEDIDO</option>
                <option value="RENUNCIO" ${usuario.estado == 'RENUNCIO' ? 'selected' : ''}>RENUNCIO</option>
            </select><br><br>

            <label for="tipoUsuario">Tipo de Usuario:</label><br>
            <select id="tipoUsuario" name="tipoUsuario">
                <option value="ADMIN" ${usuario.tipoUsuario == 'ADMIN' ? 'selected' : ''}>Admin</option>
                <option value="USUARIO" ${usuario.tipoUsuario == 'USUARIO' ? 'selected' : ''}>Usuario</option>
            </select><br><br>

            <label for="accesoSistema">Acceso al Sistema:</label>
            <input type="checkbox" id="accesoSistema" name="accesoSistema" value="true" ${usuario.accesoSistema ? 'checked' : ''}><br><br>

            <!-- Mostrar la foto actual si existe -->
            <label for="fotoActual">Foto Actual:</label><br>
            <c:choose>
                <c:when test="${not empty usuario.fotoBase64}">
                    <img src="data:image/jpeg;base64,${usuario.fotoBase64}" alt="Foto de Usuario" width="100" height="100"><br>
                </c:when>
                <c:otherwise>
                    No hay foto
                </c:otherwise>
            </c:choose>

            <!-- Campo para cargar una nueva foto -->
            <label for="foto">Cambiar Foto:</label><br>
            <input type="file" id="foto" name="foto"><br><br>

            <input type="submit" value="Guardar Cambios">
            <a href="${pageContext.request.contextPath}/usuarios">Cancelar</a>
        </form>
    </body>
</html>

