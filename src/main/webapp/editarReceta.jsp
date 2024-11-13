<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Receta</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border: none;
        }
        h1 {
            color: #343a40;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Editar Receta</h1>

    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/recetas?action=update" method="post" class="p-4" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${receta.idReceta}" />

                <div class="form-group">
                    <label for="nombreReceta" class="text-dark">Nombre de la Receta:</label>
                    <input type="text" id="nombreReceta" name="nombreReceta" class="form-control" value="${receta.nombreReceta}" required pattern="^[A-Za-z0-9 ]+$" title="Sólo letras, números y espacios.">
                </div>

                <div class="form-group">
                    <label for="tipoCocina" class="text-dark">Tipo de Cocina:</label>
                    <select id="tipoCocina" name="tipoCocina" class="form-control" required>
                        <option value="">Seleccione...</option>
                        <option value="Italiana" ${receta.tipoCocina == 'Italiana' ? 'selected' : ''}>Italiana</option>
                        <option value="Mexicana" ${receta.tipoCocina == 'Mexicana' ? 'selected' : ''}>Mexicana</option>
                        <option value="China" ${receta.tipoCocina == 'China' ? 'selected' : ''}>China</option>
                        <option value="Japonesa" ${receta.tipoCocina == 'Japonesa' ? 'selected' : ''}>Japonesa</option>
                        <option value="India" ${receta.tipoCocina == 'India' ? 'selected' : ''}>India</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="ingredientes" class="text-dark">Ingredientes:</label>
                    <textarea id="ingredientes" name="ingredientes" class="form-control" required>${receta.ingredientes}</textarea>
                </div>

                <div class="form-group">
                    <label for="tiempoPreparacion" class="text-dark">Tiempo de Preparación (min):</label>
                    <input type="number" id="tiempoPreparacion" name="tiempoPreparacion" class="form-control" value="${receta.tiempoPreparacion}" required min="0" title="Debe ser un número no negativo.">
                </div>

                <div class="form-group">
                    <label for="estado" class="text-dark">Estado:</label>
                    <select id="estado" name="estado" class="form-control" required>
                        <option value="ACTIVA" ${receta.estado == 'ACTIVA' ? 'selected' : ''}>Activo</option>
                        <option value="INACTIVO" ${receta.estado == 'INACTIVO' ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Actualizar Receta</button>
                <a href="${pageContext.request.contextPath}/recetas" class="btn btn-secondary btn-block">Cancelar</a>
            </form>
        </div>
    </div>
</div>

<!-- jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
