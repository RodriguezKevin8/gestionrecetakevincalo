package entidades;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.Base64;
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

    public Usuario findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No se encontró el usuario
        } finally {
            em.close();
        }
    }

    // Método para convertir byte[] a una cadena Base64
    public String convertirFotoABase64(byte[] foto) {
        return (foto != null) ? Base64.getEncoder().encodeToString(foto) : null;
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        EntityManager em = getEntityManager();
        try {
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();

            // Convertir fotos a Base64 para cada usuario
            for (Usuario usuario : usuarios) {
                if (usuario.getFoto() != null) {
                    usuario.setFotoBase64(convertirFotoABase64(usuario.getFoto()));
                }
            }
            return usuarios;
        } finally {
            em.close();
        }
    }

    // Encontrar usuario por ID
    public Usuario encontrarUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            Usuario usuario = em.find(Usuario.class, id);

            // Convertir la foto a Base64 si existe
            if (usuario != null && usuario.getFoto() != null) {
                usuario.setFotoBase64(convertirFotoABase64(usuario.getFoto()));
            }
            return usuario;
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
