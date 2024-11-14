<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Favoritos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Gestión de Favoritos</h1>

    <!-- Mostrar mensaje de eliminación -->
    <c:if test="${not empty mensaje}">
        <div class="alert alert-info">${mensaje}</div>
    </c:if>

    <table id="favoritosTable" class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>Usuario</th>
                <th>Receta</th>
                <th>Fecha de Favorito</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="favorito" items="${favoritos}">
                <tr>
                    <td>${favorito.usuario.nombre} ${favorito.usuario.apellido}</td>
                    <td>${favorito.receta.nombreReceta}</td>
                    <td>${favorito.fechaFavorito}</td>
                    <td>${favorito.estado == 'ACTIVO' ? 'Activa' : 'Inactiva'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/favoritos?action=delete&id=${favorito.idFavorito}" 
                           class="btn btn-danger btn-sm" 
                           onclick="return confirm('¿Estás seguro de que deseas eliminar este favorito?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty favoritos}">
        <p class="text-center text-muted">No se encontraron favoritos.</p>
    </c:if>
</div>

<!-- jQuery y DataTables JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

<script>
    $(document).ready(function() {
        $('#favoritosTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
            }
        });
    });
</script>
</body>
</html>
