package happy.paws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
@Override
    public void addCorsMappings(CorsRegistry registry) {
     //   registry.addMapping("/**")
     //           .allowedOrigins("*")
     //           .allowedMethods("*")
     //           .allowedHeaders("*");
     registry.addMapping("/ws/**") // WebSocket endpoint
                .allowedOriginPatterns("http://localhost:*") // Permite cualquier puerto en localhost
                .allowedMethods("GET", "POST")
                .allowCredentials(true);
    }
}
