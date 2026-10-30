package pe.edu.upeu.vetandes.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="mascota") public class Mascota  {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String nombre;
    private String especie;
    private String raza;
    @Column(name="fecha_nacimiento") private java.time.LocalDate fechaNacimiento;
    @Column(columnDefinition="CHAR(1)") @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.CHAR) private String sexo;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="propietario_id") private Propietario propietario;
}
