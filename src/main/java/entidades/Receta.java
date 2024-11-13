package entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Long idReceta;

    @Column(name = "nombre_receta", nullable = false)
    private String nombreReceta;

    @Column(name = "tipo_cocina", nullable = false)
    private String tipoCocina;

    @Lob
    private String ingredientes;

    @Column(name = "tiempo_preparacion")
    private int tiempoPreparacion;

    @Lob
    private byte[] imagen;

    @Enumerated(EnumType.STRING)
    private EstadoReceta estado;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorito> favoritos;

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public EstadoReceta getEstado() {
        return estado;
    }

    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public Receta() {
    }

    public Receta(Long idReceta, String nombreReceta, String tipoCocina, String ingredientes, int tiempoPreparacion, byte[] imagen, EstadoReceta estado, List<Favorito> favoritos) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.tipoCocina = tipoCocina;
        this.ingredientes = ingredientes;
        this.tiempoPreparacion = tiempoPreparacion;
        this.imagen = imagen;
        this.estado = estado;
        this.favoritos = favoritos;
    }
}

enum EstadoReceta {
    INACTIVO,
    ACTIVA
}
