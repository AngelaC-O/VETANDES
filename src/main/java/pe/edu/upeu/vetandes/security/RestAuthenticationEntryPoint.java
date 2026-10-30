package pe.edu.upeu.vetandes.security;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.*;
import java.io.IOException;
@Component public class RestAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint  {
    public void commence(HttpServletRequest r,HttpServletResponse s,org.springframework.security.core.AuthenticationException e)throws IOException {
        s.setStatus(401);
        s.setContentType("application/json;charset=UTF-8");
        s.getWriter().write("{\"timestamp\":\""+java.time.LocalDateTime.now()+"\",\"status\":401,\"error\":\"Unauthorized\",\"mensaje\":\"Debe iniciar sesión\",\"ruta\":\""+r.getRequestURI()+"\"}");
    }
}
