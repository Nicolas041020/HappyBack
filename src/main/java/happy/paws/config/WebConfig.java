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
        registry.addMapping("/**") 
        .allowedOriginPatterns("http://localhost:*", "http://10.0.2.2:*", "http://192.168.1.100:*") // Permite cualquier puerto
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true);
    // registry.addMapping("/ws/**") // WebSocket endpoint
      //          .allowedOriginPatterns("http://localhost:*") // Permite cualquier puerto en localhost
        //        .allowedMethods("GET", "POST")
          //      .allowCredentials(true);
    }
}
