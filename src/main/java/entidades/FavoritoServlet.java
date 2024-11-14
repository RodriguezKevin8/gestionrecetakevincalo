package entidades;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/favoritos")
public class FavoritoServlet extends HttpServlet {

    private FavoritoController controller;

    @Override
    public void init() throws ServletException {
        controller = new FavoritoController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            Long idFavorito = Long.parseLong(request.getParameter("id"));
            
            // Confirmar existencia y eliminar
            boolean eliminado = controller.eliminarFavorito(idFavorito);
            if (eliminado) {
                request.setAttribute("mensaje", "Favorito eliminado exitosamente.");
            } else {
                request.setAttribute("mensaje", "El favorito no existe o ya ha sido eliminado.");
            }
            response.sendRedirect(request.getContextPath() + "/favoritos");
            return;
        }

        // Mostrar todos los favoritos
        List<Favorito> favoritos = controller.obtenerTodosFavoritos();
        request.setAttribute("favoritos", favoritos);
        request.getRequestDispatcher("/favoritos.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        controller.cerrar();
    }
}
