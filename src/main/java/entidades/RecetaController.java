package entidades;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class RecetaController {

    private EntityManagerFactory emf;

    public RecetaController() {
        
        this.emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crearReceta(Receta receta) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(receta);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Receta encontrarReceta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receta.class, id);
        } finally {
            em.close();
        }
    }

    public List<Receta> obtenerTodasLasRecetas() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Receta r", Receta.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarReceta(Receta receta) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(receta);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminarReceta(Long id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Receta receta = em.find(Receta.class, id);
            if (receta != null) {
                em.remove(receta);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
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