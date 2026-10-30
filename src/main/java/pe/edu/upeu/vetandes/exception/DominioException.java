package pe.edu.upeu.vetandes.exception;
import lombok.Getter;
@Getter public class DominioException extends RuntimeException  {
    private final int status;
    public DominioException(int status,String mensaje) {
        super(mensaje);
        this.status=status;
    }
}
