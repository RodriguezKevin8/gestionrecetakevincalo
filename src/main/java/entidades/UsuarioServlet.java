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
            Long userId = Long.parseLong(request.getParameter("id"));
            controller.eliminarUsuario(userId);
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } else if ("edit".equals(action)) {
            // Editar usuario: cargar datos del usuario en el formulario
            Long userId = Long.parseLong(request.getParameter("id"));
            Usuario usuario = controller.encontrarUsuario(userId);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/editarUsuario.jsp").forward(request, response);
        } else {
            // Listar usuarios
            List<Usuario> usuarios = controller.obtenerTodosLosUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("id");

        if (userIdParam != null && !userIdParam.isEmpty()) {
            // Actualizar usuario existente
            Long userId = Long.parseLong(userIdParam);
            Usuario usuario = controller.encontrarUsuario(userId);

            if (usuario != null) {
                // Obtener y actualizar datos del usuario
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setApellido(request.getParameter("apellido"));
                usuario.setEmail(request.getParameter("email"));
                usuario.setClave(request.getParameter("clave"));
                usuario.setEstado(EstadoUsuario.valueOf(request.getParameter("estado")));
                usuario.setAccesoSistema(request.getParameter("accesoSistema") != null);

                controller.actualizarUsuario(usuario);
            }
        } else {
            // Crear un nuevo usuario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String clave = request.getParameter("clave");
            EstadoUsuario estado = EstadoUsuario.valueOf(request.getParameter("estado"));
            boolean accesoSistema = request.getParameter("accesoSistema") != null;

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setClave(clave);
            usuario.setFechaRegistro(LocalDate.now());
            usuario.setEstado(estado);
            usuario.setAccesoSistema(accesoSistema);

            controller.crearUsuario(usuario);
        }
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    @Override
    public void destroy() {
        controller.cerrar();
    }
}
