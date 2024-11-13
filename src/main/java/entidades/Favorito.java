package entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito")
    private Long idFavorito;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @Column(name = "fecha_favorito", nullable = false)
    private LocalDate fechaFavorito;


    @Enumerated(EnumType.STRING)
    private EstadoFavorito estado;

    public Favorito() {
    }
    
     public Favorito(Long idFavorito, Usuario usuario, Receta receta, LocalDate fechaFavorito, EstadoFavorito estado) {
        this.idFavorito = idFavorito;
        this.usuario = usuario;
        this.receta = receta;
        this.fechaFavorito = fechaFavorito;
        this.estado = estado;
    }

    // Getters y setters
    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public LocalDate getFechaFavorito() {
        return fechaFavorito;
    }

    public void setFechaFavorito(LocalDate fechaFavorito) {
        this.fechaFavorito = fechaFavorito;
    }

    public EstadoFavorito getEstado() {
        return estado;
    }

    public void setEstado(EstadoFavorito estado) {
        this.estado = estado;
    }
}

enum EstadoFavorito {
    INACTIVO,
    ACTIVO
}
