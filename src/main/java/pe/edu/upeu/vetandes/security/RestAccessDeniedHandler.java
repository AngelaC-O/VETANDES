package pe.edu.upeu.vetandes.security;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.*;
import java.io.IOException;
@Component public class RestAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler  {
    public void handle(HttpServletRequest r,HttpServletResponse s,org.springframework.security.access.AccessDeniedException e)throws IOException {
        s.setStatus(403);
        s.setContentType("application/json;charset=UTF-8");
        s.getWriter().write("{\"timestamp\":\""+java.time.LocalDateTime.now()+"\",\"status\":403,\"error\":\"Forbidden\",\"mensaje\":\"No tiene permiso para esta operación\",\"ruta\":\""+r.getRequestURI()+"\"}");
    }
}
