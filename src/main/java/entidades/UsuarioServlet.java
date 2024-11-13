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
import java.util.List;

@WebServlet("/usuarios")
@MultipartConfig // Habilita la carga de archivos en el servlet
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
            Long userId = Long.parseLong(request.getParameter("id"));
            controller.eliminarUsuario(userId);
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } else if ("edit".equals(action)) {
            Long userId = Long.parseLong(request.getParameter("id"));
            Usuario usuario = controller.encontrarUsuario(userId);

            // Convierte la foto a Base64 si existe
            if (usuario != null && usuario.getFoto() != null) {
                usuario.setFotoBase64(controller.convertirFotoABase64(usuario.getFoto()));
            }

            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/editarUsuario.jsp").forward(request, response);
        } else {
            List<Usuario> usuarios = controller.obtenerTodosLosUsuarios();

            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración para manejar caracteres especiales
        request.setCharacterEncoding("UTF-8");

        String userIdParam = request.getParameter("id");
        Usuario usuario;

        if (userIdParam != null && !userIdParam.isEmpty()) {
            // Actualizar usuario existente
            Long userId = Long.parseLong(userIdParam);
            usuario = controller.encontrarUsuario(userId);
        } else {
            // Crear un nuevo usuario
            usuario = new Usuario();
            usuario.setFechaRegistro(LocalDate.now());
        }

        // Obtener y actualizar datos del usuario
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setClave(request.getParameter("clave"));
        usuario.setEstado(EstadoUsuario.valueOf(request.getParameter("estado")));
        usuario.setAccesoSistema(request.getParameter("accesoSistema") != null);

        // Obtener y establecer el tipo de usuario (admin o usuario)
        usuario.setTipoUsuario(TipoUsuario.valueOf(request.getParameter("tipoUsuario").toUpperCase()));

        // Manejar la carga de la foto
        Part fotoPart = request.getPart("foto");
        if (fotoPart != null && fotoPart.getSize() > 0) {
            // Leer el contenido del archivo como byte array
            InputStream inputStream = fotoPart.getInputStream();
            byte[] fotoBytes = inputStream.readAllBytes();
            usuario.setFoto(fotoBytes);
        }

        // Guardar o actualizar el usuario
        if (userIdParam != null && !userIdParam.isEmpty()) {
            controller.actualizarUsuario(usuario);
        } else {
            controller.crearUsuario(usuario);
        }

        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    @Override
    public void destroy() {
        controller.cerrar();
    }
}

