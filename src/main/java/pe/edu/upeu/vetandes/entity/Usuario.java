package pe.edu.upeu.vetandes.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="usuarios") public class Usuario  {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String username;
    private String password;
    @Column(name="nombre_completo") private String nombreCompleto;
    @Convert(converter=org.hibernate.type.NumericBooleanConverter.class) private Boolean activo;
    @ManyToMany(fetch=FetchType.EAGER) @JoinTable(name="usuario_roles",joinColumns=@JoinColumn(name="usuario_id"),inverseJoinColumns=@JoinColumn(name="rol_id")) private java.util.Set<Rol> roles=new java.util.HashSet<>();
}
