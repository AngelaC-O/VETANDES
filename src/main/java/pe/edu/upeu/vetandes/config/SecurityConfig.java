package pe.edu.upeu.vetandes.config;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upeu.vetandes.security.*;
import org.springframework.http.HttpMethod;
@Configuration @EnableMethodSecurity public class SecurityConfig  {
    @Bean PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean SecurityFilterChain security(HttpSecurity h,JwtAuthenticationFilter f,RestAuthenticationEntryPoint entry,RestAccessDeniedHandler denied)throws Exception {
        return h.csrf(c->c.disable()).cors(c-> {
        }
        ).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).exceptionHandling(e->e.authenticationEntryPoint(entry).accessDeniedHandler(denied)).authorizeHttpRequests(a->a.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll().requestMatchers("/api/v1/auth/login","/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**").permitAll().requestMatchers(HttpMethod.GET,"/api/v1/prestaciones","/api/v1/prestaciones/**").hasAuthority("PRESTACION_LEER").requestMatchers(HttpMethod.POST,"/api/v1/prestaciones","/api/v1/prestaciones/**").hasAuthority("PRESTACION_ESCRIBIR").requestMatchers(HttpMethod.PUT,"/api/v1/prestaciones","/api/v1/prestaciones/**").hasAuthority("PRESTACION_ESCRIBIR").requestMatchers(HttpMethod.PATCH,"/api/v1/prestaciones","/api/v1/prestaciones/**").hasAuthority("PRESTACION_ESCRIBIR").requestMatchers(HttpMethod.DELETE,"/api/v1/prestaciones","/api/v1/prestaciones/**").hasAuthority("PRESTACION_ESCRIBIR").requestMatchers(HttpMethod.GET,"/api/v1/mascotas","/api/v1/mascotas/**").hasAuthority("MASCOTA_LEER").requestMatchers(HttpMethod.POST,"/api/v1/mascotas","/api/v1/mascotas/**").hasAuthority("MASCOTA_ESCRIBIR").requestMatchers(HttpMethod.PUT,"/api/v1/mascotas","/api/v1/mascotas/**").hasAuthority("MASCOTA_ESCRIBIR").requestMatchers(HttpMethod.PATCH,"/api/v1/mascotas","/api/v1/mascotas/**").hasAuthority("MASCOTA_ESCRIBIR").requestMatchers(HttpMethod.DELETE,"/api/v1/mascotas","/api/v1/mascotas/**").hasAuthority("MASCOTA_ESCRIBIR").requestMatchers(HttpMethod.GET,"/api/v1/propietarios").hasAuthority("MASCOTA_LEER").requestMatchers(HttpMethod.GET,"/api/v1/atenciones/**").hasAuthority("ATENCION_LEER").requestMatchers(HttpMethod.GET,"/api/v1/atenciones").hasAuthority("ATENCION_LEER").requestMatchers(HttpMethod.POST,"/api/v1/atenciones").hasAuthority("ATENCION_REGISTRAR").requestMatchers(HttpMethod.PATCH,"/api/v1/atenciones/*/anular").hasAuthority("ATENCION_ANULAR").requestMatchers(HttpMethod.GET,"/api/v1/reportes/**").hasAuthority("REPORTE_VER").anyRequest().authenticated()).addFilterBefore(f,UsernamePasswordAuthenticationFilter.class).build();
    }
}
