package pe.edu.upeu.vetandes.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="roles") public class Rol  {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String nombre;
}
