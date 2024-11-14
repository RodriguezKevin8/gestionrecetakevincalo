<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #4e54c8, #8f94fb);
            color: #333;
        }
        .card {
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .btn-custom {
            background-color: #4e54c8;
            color: #fff;
        }
        .btn-custom:hover {
            background-color: #3e44b1;
        }
        .form-label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card bg-white p-4">
                    <h2 class="text-center mb-4">Editar Usuario</h2>
                    <form action="${pageContext.request.contextPath}/usuarios" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${usuario.idUsuario}">

                        <!-- Nombre -->
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" class="form-control" value="${usuario.nombre}" required>
                        </div>

                        <!-- Apellido -->
                        <div class="mb-3">
                            <label for="apellido" class="form-label">Apellido:</label>
                            <input type="text" id="apellido" name="apellido" class="form-control" value="${usuario.apellido}" required>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" id="email" name="email" class="form-control" value="${usuario.email}" required>
                        </div>

                        <!-- Contraseña -->
                        <div class="mb-3">
                            <label for="clave" class="form-label">Contraseña:</label>
                            <input type="password" id="clave" name="clave" class="form-control" value="${usuario.clave}" required>
                        </div>

                        <!-- Estado -->
                        <div class="mb-3">
                            <label for="estado" class="form-label">Estado:</label>
                            <select id="estado" name="estado" class="form-select">
                                <option value="ACTIVO" ${usuario.estado == 'ACTIVO' ? 'selected' : ''}>ACTIVO</option>
                                <option value="INCAPACITADO" ${usuario.estado == 'INCAPACITADO' ? 'selected' : ''}>INCAPACITADO</option>
                                <option value="DESPEDIDO" ${usuario.estado == 'DESPEDIDO' ? 'selected' : ''}>DESPEDIDO</option>
                                <option value="RENUNCIO" ${usuario.estado == 'RENUNCIO' ? 'selected' : ''}>RENUNCIO</option>
                            </select>
                        </div>

                        <!-- Tipo de Usuario -->
                        <div class="mb-3">
                            <label for="tipoUsuario" class="form-label">Tipo de Usuario:</label>
                            <select id="tipoUsuario" name="tipoUsuario" class="form-select">
                                <option value="ADMIN" ${usuario.tipoUsuario == 'ADMIN' ? 'selected' : ''}>Admin</option>
                                <option value="USUARIO" ${usuario.tipoUsuario == 'USUARIO' ? 'selected' : ''}>Usuario</option>
                            </select>
                        </div>

                        <!-- Acceso al Sistema -->
                        <div class="mb-3 form-check">
                            <input type="checkbox" id="accesoSistema" name="accesoSistema" value="true" class="form-check-input" ${usuario.accesoSistema ? 'checked' : ''}>
                            <label for="accesoSistema" class="form-check-label">Acceso al Sistema</label>
                        </div>

                        <!-- Foto Actual -->
                        <div class="mb-3">
                            <label for="fotoActual" class="form-label">Foto Actual:</label><br>
                            <c:choose>
                                <c:when test="${not empty usuario.fotoBase64}">
                                    <img src="data:image/jpeg;base64,${usuario.fotoBase64}" alt="Foto de Usuario" class="rounded-circle mb-3" width="100" height="100">
                                </c:when>
                                <c:otherwise>
                                    <p>No hay foto</p>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <!-- Cambiar Foto -->
                        <div class="mb-3">
                            <label for="foto" class="form-label">Cambiar Foto:</label>
                            <input type="file" id="foto" name="foto" class="form-control">
                        </div>

                        <!-- Botones de Acción -->
                        <div class="text-center">
                            <button type="submit" class="btn btn-custom">Guardar Cambios</button>
                            <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-danger ms-3">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
