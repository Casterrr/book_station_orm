package cacadores.ifal.poo.book_station.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookStationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Book Station API")
                        .description("API for Book Station application")
                        .version("1.0"));
    }
}