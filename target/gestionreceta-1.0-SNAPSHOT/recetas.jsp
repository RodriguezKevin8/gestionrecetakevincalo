<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Recetas</title>
    
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border: none;
        }
        .card-header, .table thead {
            background-color: #343a40;
            color: white;
        }
        .table tbody tr:hover {
            background-color: #e9ecef;
        }
        .btn-primary, .btn-warning, .btn-danger {
            margin: 5px 0;
        }
        h1, h2 {
            color: #343a40;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Lista de Recetas</h1>
    
    <!-- Tabla de Recetas -->
    <div class="card mb-4">
        <div class="card-header text-center">
            <h5>Recetas Guardadas</h5>
        </div>
        <div class="card-body">
            <table id="recetasTable" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Tipo de Cocina</th>
                        <th>Ingredientes</th>
                        <th>Tiempo de Preparación</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="receta" items="${recetas}">
                        <tr>
                            <td>${receta.idReceta}</td>
                            <td>${receta.nombreReceta}</td>
                            <td>${receta.tipoCocina}</td>
                            <td>${receta.ingredientes}</td>
                            <td>${receta.tiempoPreparacion} minutos</td>
                            <td>${receta.estado == 'ACTIVA' ? 'Activo' : 'Inactivo'}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/recetas?action=edit&id=${receta.idReceta}" class="btn btn-warning btn-sm">Editar</a>
                                <a href="${pageContext.request.contextPath}/recetas?action=delete&id=${receta.idReceta}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar esta receta?');">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <h2 class="text-center">Agregar Nueva Receta</h2>
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/recetas" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="nombreReceta" class="text-dark">Nombre de la Receta:</label>
                    <input type="text" id="nombreReceta" name="nombreReceta" class="form-control" required pattern="^[A-Za-z0-9 ]+$" title="Sólo letras, números y espacios.">
                </div>
                <div class="form-group">
                    <label for="tipoCocina" class="text-dark">Tipo de Cocina:</label>
                    <select id="tipoCocina" name="tipoCocina" class="form-control" required>
                        <option value="">Seleccione...</option>
                        <option value="Italiana">Italiana</option>
                        <option value="Mexicana">Mexicana</option>
                        <option value="China">China</option>
                        <option value="Japonesa">Japonesa</option>
                        <option value="India">India</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="ingredientes" class="text-dark">Ingredientes:</label>
                    <textarea id="ingredientes" name="ingredientes" class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label for="tiempoPreparacion" class="text-dark">Tiempo de Preparación (minutos):</label>
                    <input type="number" id="tiempoPreparacion" name="tiempoPreparacion" class="form-control" required min="0" title="Debe ser un número no negativo.">
                </div>
                 <div class="form-group">
            <label for="imagen" class="text-dark">Imagen (opcional):</label>
            <input type="file" id="imagen" name="imagen" class="form-control-file" accept="image/*">
        </div>
                <div class="form-group">
                    <label for="estado" class="text-dark">Estado:</label>
                    <select id="estado" name="estado" class="form-control" required>
                        <option value="ACTIVA">Activo</option>
                        <option value="INACTIVO">Inactivo</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Agregar Receta</button>
            </form>
        </div>
    </div>
</div>

<!-- jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<!-- DataTables JS -->
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function() {
        $('#recetasTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
            }
        });
    });
</script>
</body>
</html>
