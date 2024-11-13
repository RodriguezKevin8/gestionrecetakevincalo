package entidades;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class UsuarioController {

    private EntityManagerFactory emf;

    public UsuarioController() {
        this.emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo usuario
    public void crearUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(usuario);
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

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Encontrar usuario por ID
    public Usuario encontrarUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    // Actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(usuario);
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

    // Eliminar un usuario
    public void eliminarUsuario(Long id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
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

    // Cerrar el EntityManagerFactory
    public void cerrar() {
        if (emf != null) {
            emf.close();
        }
    }
}

