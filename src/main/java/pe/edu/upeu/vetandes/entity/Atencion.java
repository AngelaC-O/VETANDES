package pe.edu.upeu.vetandes.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="atencion") public class Atencion  {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String numero;
    private java.time.LocalDateTime fecha;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="mascota_id") private Mascota mascota;
    private java.math.BigDecimal peso;
    private String diagnostico;
    private String estado;
    private java.math.BigDecimal subtotal;
    private java.math.BigDecimal descuento;
    private java.math.BigDecimal total;
    @Column(name="usuario_registro") private String usuarioRegistro;
    @Column(name="motivo_anulacion") private String motivoAnulacion;
    @OneToMany(mappedBy="atencion",cascade=CascadeType.ALL) private java.util.List<DetalleAtencion> detalles=new java.util.ArrayList<>();
}
