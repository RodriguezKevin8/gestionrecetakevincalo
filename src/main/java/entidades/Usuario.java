package entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false)
    private String clave;

    @Lob
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;
    
    @Transient // Esto indica que no se debe persistir en la base de datos
    private String fotoBase64;

    @Column(name = "acceso_sistema", nullable = false)
    private boolean accesoSistema = true;

    @Column(name = "tipo_usuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorito> favoritos;

    public Usuario(Long idUsuario, String nombre, String apellido, String email, LocalDate fechaRegistro, String clave, byte[] foto, EstadoUsuario estado, boolean accesoSistema, TipoUsuario tipoUsuario, List<Favorito> favoritos) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.clave = clave;
        this.foto = foto;
        this.estado = estado;
        this.accesoSistema = accesoSistema;
        this.tipoUsuario = tipoUsuario;
        this.favoritos = favoritos;
    }

    public Usuario() {
    }

    // Getters y setters...

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public boolean isAccesoSistema() {
        return accesoSistema;
    }

    public void setAccesoSistema(boolean accesoSistema) {
        this.accesoSistema = accesoSistema;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }
}

enum EstadoUsuario {
    ACTIVO,
    INCAPACITADO,
    DESPEDIDO,
    RENUNCIO
}

enum TipoUsuario {
    ADMIN,
    USUARIO
}
