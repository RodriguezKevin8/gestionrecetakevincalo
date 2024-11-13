package entidades;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioController controller;

    @Override
    public void init() throws ServletException {
        controller = new UsuarioController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            // Eliminar usuario
            try {
                Long userId = Long.parseLong(request.getParameter("id"));
                controller.eliminarUsuario(userId);
                System.out.println("Usuario eliminado con ID: " + userId);
            } catch (NumberFormatException e) {
                System.out.println("ID de usuario no válido para eliminación");
            }
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } else {
            // Listar usuarios
            List<Usuario> usuarios = controller.obtenerTodosLosUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear un nuevo usuario usando los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        EstadoUsuario estado = EstadoUsuario.valueOf(request.getParameter("estado"));
        boolean accesoSistema = request.getParameter("accesoSistema") != null;  // Checkbox manejado como booleano

        // Crear el objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setClave(clave);
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setEstado(estado);
        usuario.setAccesoSistema(accesoSistema);

        // Guardar usuario
        controller.crearUsuario(usuario);

        // Redirigir a la lista de usuarios
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    @Override
    public void destroy() {
        controller.cerrar();
    }
}
