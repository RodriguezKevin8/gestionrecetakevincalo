package entidades;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Base64;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioController usuarioController;

    @Override
    public void init() throws ServletException {
        usuarioController = new UsuarioController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige al formulario de login
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usuarioController.findByEmail(email);

        // Verifica si el usuario existe y la contraseña es correcta
        if (usuario != null && usuario.getClave().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioId", usuario.getIdUsuario());  // Almacena el ID del usuario en la sesión
            session.setAttribute("usuarioTipo", usuario.getTipoUsuario());  // Almacena el tipo de usuario en la sesión

            // Convierte la foto a Base64 si existe y la almacena en la sesión
            if (usuario.getFoto() != null) {
                String fotoBase64 = Base64.getEncoder().encodeToString(usuario.getFoto());
                session.setAttribute("usuarioFotoBase64", fotoBase64);  // Almacena la foto en Base64 en la sesión
            } else {
                session.setAttribute("usuarioFotoBase64", null);  // Establece como null si no hay foto
            }

            // Redirige según el rol del usuario
            if (usuario.getTipoUsuario() == TipoUsuario.ADMIN) {
                response.sendRedirect(request.getContextPath() + "/usuarios");
            } else {
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            }
        } else {
            // Credenciales inválidas, redirige a la página de login con un mensaje de error
            request.setAttribute("errorMessage", "Email o contraseña incorrectos.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        usuarioController.cerrar();
    }
}
