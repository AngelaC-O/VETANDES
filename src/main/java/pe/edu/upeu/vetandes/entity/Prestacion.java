package pe.edu.upeu.vetandes.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="prestacion") public class Prestacion  {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String codigo;
    private String nombre;
    private java.math.BigDecimal precio;
    @Column(name="especie_aplicable") private String especieAplicable;
    @Convert(converter=org.hibernate.type.NumericBooleanConverter.class) private Boolean activo=true;
}
