<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuarios</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #4e54c8, #8f94fb);
            color: #333;
        }
        .container {
            margin-top: 50px;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .table img {
            border-radius: 50%;
        }
        .btn-custom {
            background-color: #4e54c8;
            color: #fff;
        }
        .btn-custom:hover {
            background-color: #3e44b1;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card p-4 mb-4">
            <h2 class="text-center">Lista de Usuarios</h2>
            <div class="table-responsive">
                <table class="table table-striped table-hover mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Email</th>
                            <th>Fecha de Registro</th>
                            <th>Estado</th>
                            <th>Acceso al Sistema</th>
                            <th>Tipo de Usuario</th>
                            <th>Foto</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usuario" items="${usuarios}">
                            <tr>
                                <td>${usuario.idUsuario}</td>
                                <td>${usuario.nombre}</td>
                                <td>${usuario.apellido}</td>
                                <td>${usuario.email}</td>
                                <td>${usuario.fechaRegistro}</td>
                                <td>${usuario.estado}</td>
                                <td>${usuario.accesoSistema ? "Sí" : "No"}</td>
                                <td>${usuario.tipoUsuario}</td>
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
                                    <a href="${pageContext.request.contextPath}/usuarios?action=edit&id=${usuario.idUsuario}" class="btn btn-warning btn-sm">Editar</a>
                                    <a href="${pageContext.request.contextPath}/usuarios?action=delete&id=${usuario.idUsuario}" class="btn btn-danger btn-sm">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="card p-4">
            <h2 class="text-center">Agregar Nuevo Usuario</h2>
            <form action="${pageContext.request.contextPath}/usuarios" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" required>
                </div>
                
                <div class="mb-3">
                    <label for="apellido" class="form-label">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" class="form-control" required>
                </div>
                
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                
                <div class="mb-3">
                    <label for="clave" class="form-label">Contraseña:</label>
                    <input type="password" id="clave" name="clave" class="form-control" required>
                </div>
                
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado:</label>
                    <select id="estado" name="estado" class="form-select">
                        <option value="ACTIVO">ACTIVO</option>
                        <option value="INCAPACITADO">INCAPACITADO</option>
                        <option value="DESPEDIDO">DESPEDIDO</option>
                        <option value="RENUNCIO">RENUNCIO</option>
                    </select>
                </div>
                
                <div class="mb-3 form-check">
                    <input type="checkbox" id="accesoSistema" name="accesoSistema" class="form-check-input" value="true" checked>
                    <label for="accesoSistema" class="form-check-label">Acceso al Sistema</label>
                </div>
                
                <div class="mb-3">
                    <label for="tipoUsuario" class="form-label">Tipo de Usuario:</label>
                    <select id="tipoUsuario" name="tipoUsuario" class="form-select">
                        <option value="ADMIN">Admin</option>
                        <option value="USUARIO">Usuario</option>
                    </select>
                </div>
                
                <div class="mb-3">
                    <label for="foto" class="form-label">Foto:</label>
                    <input type="file" id="foto" name="foto" class="form-control">
                </div>
                
                <div class="text-center">
                    <button type="submit" class="btn btn-custom">Agregar Usuario</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
