package entidades;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class FavoritoController {

    private EntityManagerFactory emf;

    public FavoritoController() {
        this.emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Obtener todos los favoritos con las relaciones de Usuario y Receta
    public List<Favorito> obtenerTodosFavoritos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Favorito f JOIN FETCH f.usuario JOIN FETCH f.receta", Favorito.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public boolean eliminarFavorito(Long idFavorito) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            Favorito favorito = em.find(Favorito.class, idFavorito);

            if (favorito != null) {
                em.remove(favorito); // Eliminar el favorito si existe
                tx.commit();
                return true;
            } else {
                tx.rollback();
                return false; // Favorito no encontrado
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public void cerrar() {
        if (emf != null) {
            emf.close();
        }
    }
}
