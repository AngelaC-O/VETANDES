package pe.edu.upeu.vetandes.config;
import org.springframework.context.annotation.*;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
@Configuration public class OpenApiConfig  {
    @Bean OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("VetAndes API").version("1.0")).components(new Components().addSecuritySchemes("bearerAuth",new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
