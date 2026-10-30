package pe.edu.upeu.vetandes.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="propietario") public class Propietario  {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String dni;
    private String nombres;
    private String telefono;
}
