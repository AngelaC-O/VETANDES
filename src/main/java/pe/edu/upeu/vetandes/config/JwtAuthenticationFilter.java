package pe.edu.upeu.vetandes.config;
import pe.edu.upeu.vetandes.security.*;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@Component @RequiredArgsConstructor public class JwtAuthenticationFilter extends OncePerRequestFilter  {
    private final JwtService jwt;
    private final UsuarioDetailsService usuarios;
    private final RestAuthenticationEntryPoint entry;
    protected void doFilterInternal(HttpServletRequest r,HttpServletResponse s,FilterChain c)throws ServletException,IOException {
        String h=r.getHeader("Authorization");
        if(h!=null&&h.startsWith("Bearer ")) {
            try {
                var u=usuarios.loadUserByUsername(jwt.extraerUsername(h.substring(7)));
                if(!u.isEnabled())throw new IllegalArgumentException();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities()));
            }
            catch(Exception e) {
                SecurityContextHolder.clearContext();
                entry.commence(r,s,new org.springframework.security.authentication.BadCredentialsException("Token inválido"));
                return;
            }
        }
        c.doFilter(r,s);
    }
}
