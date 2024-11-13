package entidades;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@WebServlet("/recetas")
@MultipartConfig 
public class RecetaServlet extends HttpServlet {

    private RecetaController controller;

    @Override
    public void init() throws ServletException {
        controller = new RecetaController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
          
            Long idReceta = Long.parseLong(request.getParameter("id"));
            controller.eliminarReceta(idReceta);
            response.sendRedirect(request.getContextPath() + "/recetas");
            return;
        } else if ("edit".equals(action)) {
           
            Long idReceta = Long.parseLong(request.getParameter("id"));
            Receta receta = controller.encontrarReceta(idReceta);
            request.setAttribute("receta", receta);
            request.getRequestDispatcher("/editarReceta.jsp").forward(request, response);
            return;
        }
        List<Receta> recetas = controller.obtenerTodasLasRecetas();
        request.setAttribute("recetas", recetas);
        request.getRequestDispatcher("/recetas.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idStr = request.getParameter("id");
    String nombre = request.getParameter("nombreReceta");
    String tipoCocina = request.getParameter("tipoCocina");
    String ingredientes = request.getParameter("ingredientes");
    String tiempoPrepStr = request.getParameter("tiempoPreparacion");
    String estadoStr = request.getParameter("estado");

    int tiempoPreparacion;
    EstadoReceta estado;
    try {
        if (nombre == null || nombre.trim().isEmpty() || !nombre.matches("^[A-Za-z0-9 ]+$")) {
            throw new IllegalArgumentException("Nombre de receta inválido.");
        }
        if (tipoCocina == null || tipoCocina.trim().isEmpty() || !List.of("Italiana", "Mexicana", "China", "Japonesa", "India").contains(tipoCocina)) {
            throw new IllegalArgumentException("Tipo de cocina inválido.");
        }
        if (ingredientes == null || ingredientes.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredientes inválidos.");
        }

        tiempoPreparacion = Integer.parseInt(tiempoPrepStr);
        if (tiempoPreparacion < 0) {
            throw new IllegalArgumentException("Tiempo de preparación no puede ser negativo.");
        }

        estado = EstadoReceta.valueOf(estadoStr);
    } catch (Exception e) {
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/editarReceta.jsp").forward(request, response);
        return;
    }
    Part imagenPart = request.getPart("imagen");
    byte[] imagenBytes = null;
    if (imagenPart != null && imagenPart.getSize() > 0) {
        imagenBytes = imagenPart.getInputStream().readAllBytes();
    }

    Receta receta;
    if (idStr == null || idStr.isEmpty()) {
        receta = new Receta();
    } else {
        Long id = Long.parseLong(idStr);
        receta = controller.encontrarReceta(id);
        if (receta == null) {
            request.setAttribute("error", "Receta no encontrada.");
            request.getRequestDispatcher("/editarReceta.jsp").forward(request, response);
            return;
        }
    }

    receta.setNombreReceta(nombre);
    receta.setTipoCocina(tipoCocina);
    receta.setIngredientes(ingredientes);
    receta.setTiempoPreparacion(tiempoPreparacion);
    receta.setEstado(estado);
    if (imagenBytes != null) {
        receta.setImagen(imagenBytes);
    }

    if (idStr == null || idStr.isEmpty()) {
        controller.crearReceta(receta);
    } else {
        controller.actualizarReceta(receta);
    }

    response.sendRedirect(request.getContextPath() + "/recetas");
}

    @Override
    public void destroy() {
        controller.cerrar();
    }
}
