<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Enlace a Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .login-card {
            border-radius: 20px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
        }
        .login-title {
            font-weight: 600;
            color: #4a4a4a;
        }
        .login-card .card-body {
            padding: 2rem;
        }
        .btn-primary {
            background-color: #4a90e2;
            border: none;
            font-weight: 600;
        }
        .btn-primary:hover {
            background-color: #357ab7;
        }
        .form-label {
            font-weight: 500;
            color: #4a4a4a;
        }
        .hidden {
            display: none;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card login-card">
                    <div class="card-body">
                        <h3 class="card-title text-center login-title mb-4">
                            <i class="bi bi-person-circle me-2"></i>Iniciar Sesión
                        </h3>
                        <form action="${pageContext.request.contextPath}/login" method="post">
                            <div class="mb-3">
                                <label for="email" class="form-label">Correo Electrónico</label>
                                <input type="email" id="email" name="email" class="form-control" placeholder="Ingrese su correo" required>
                            </div>
                            <div class="mb-4">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="password" id="password" name="password" class="form-control" placeholder="Ingrese su contraseña" required>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary btn-lg">Iniciar Sesión</button>
                            </div>
                        </form>

                        <!-- Mensaje de error con clase "hidden" si no hay errorMessage -->
                        <div class="alert alert-danger text-center mt-4 ${not empty errorMessage ? '' : 'hidden'}" role="alert">
                            ${errorMessage}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Enlace a Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Enlace a Bootstrap JS y Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>


