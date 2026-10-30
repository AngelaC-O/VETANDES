package pe.edu.upeu.vetandes.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="detalle_atencion") public class DetalleAtencion  {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="atencion_id") private Atencion atencion;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="prestacion_id") private Prestacion prestacion;
    private Integer cantidad;
    @Column(name="precio_unitario") private java.math.BigDecimal precioUnitario;
    private java.math.BigDecimal subtotal;
}
