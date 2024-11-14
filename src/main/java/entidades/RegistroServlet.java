package entidades;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@WebServlet("/registro")
@MultipartConfig
public class RegistroServlet extends HttpServlet {

    private UsuarioController usuarioController;

    @Override
    public void init() throws ServletException {
        usuarioController = new UsuarioController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige al formulario de registro
        request.getRequestDispatcher("/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoger los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        // Manejar la carga de la foto si existe
        Part fotoPart = request.getPart("foto");
        byte[] fotoBytes = null;
        if (fotoPart != null && fotoPart.getSize() > 0) {
            try (InputStream inputStream = fotoPart.getInputStream()) {
                fotoBytes = inputStream.readAllBytes();
            }
        }

        // Crear el objeto Usuario con tipo USUARIO
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setClave(clave);
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setFoto(fotoBytes);
        usuario.setTipoUsuario(TipoUsuario.USUARIO); // Siempre será "USUARIO"
        usuario.setEstado(EstadoUsuario.ACTIVO); // Estado predeterminado

        // Guardar el usuario en la base de datos
        usuarioController.crearUsuario(usuario);

        // Redirigir al login con un mensaje de éxito
        request.setAttribute("successMessage", "Cuenta creada exitosamente. Ahora puedes iniciar sesión.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        usuarioController.cerrar();
    }
}
