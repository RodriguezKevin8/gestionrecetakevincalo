<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Nuevo Usuario</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #4e54c8, #8f94fb);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }
        .container {
            max-width: 500px;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Crear Nueva Cuenta</h2>
        <form action="${pageContext.request.contextPath}/registro" method="post" enctype="multipart/form-data">
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
                <label for="foto" class="form-label">Foto de Perfil:</label>
                <input type="file" id="foto" name="foto" class="form-control">
            </div>

            <!-- Campo oculto para que el tipo de usuario sea siempre USUARIO -->
            <input type="hidden" name="tipoUsuario" value="USUARIO">

            <div class="text-center">
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </div>
        </form>
    </div>
</body>
</html>

