package pe.edu.upeu.vetandes.exception;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import pe.edu.upeu.vetandes.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
@Slf4j @RestControllerAdvice public class GlobalExceptionHandler  {
    private ResponseEntity<ErrorResponseDTO> error(int status,String mensaje,Map<String,String> campos,HttpServletRequest r) {
        return ResponseEntity.status(status).body(new ErrorResponseDTO(LocalDateTime.now(),status,HttpStatus.valueOf(status).getReasonPhrase(),mensaje,r.getRequestURI(),campos));
    }
    @ExceptionHandler(DominioException.class) ResponseEntity<ErrorResponseDTO> dominio(DominioException e,HttpServletRequest r) {
        log.warn("Regla rechazada: {}",e.getMessage());
        return error(e.getStatus(),e.getMessage(),null,r);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponseDTO> validacion(MethodArgumentNotValidException e,HttpServletRequest r) {
        Map<String,String> campos=new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach(f->campos.put(f.getField(),f.getDefaultMessage()));
        return error(400,"Datos invÃ¡lidos",campos,r);
    }
    @ExceptionHandler( {
        org.springframework.http.converter.HttpMessageNotReadableException.class,org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class
    }
    ) ResponseEntity<ErrorResponseDTO> formato(Exception e,HttpServletRequest r) {
        return error(400,"Formato de datos invÃ¡lido",null,r);
    }
    @ExceptionHandler(DataIntegrityViolationException.class) ResponseEntity<ErrorResponseDTO> integridad(Exception e,HttpServletRequest r) {
        log.warn("Conflicto de integridad",e);
        return error(409,"El registro estÃ¡ duplicado o tiene referencias",null,r);
    }
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class) ResponseEntity<ErrorResponseDTO> permiso(Exception e,HttpServletRequest r) {
        return error(403,"No tiene permiso para esta operaciÃ³n",null,r);
    }
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class) ResponseEntity<ErrorResponseDTO> noEncontrado(Exception e,HttpServletRequest r) {
        return error(404,"Recurso no encontrado",null,r);
    }
    @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponseDTO> inesperado(Exception e,HttpServletRequest r) {
        log.error("Error inesperado",e);
        return error(500,"Error interno del servidor",null,r);
    }
}
