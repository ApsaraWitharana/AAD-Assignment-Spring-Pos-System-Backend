package lk.ijse.gdse68.springpossystembackend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lk.ijse.gdse68.springpossystembackend")
@EnableJpaRepositories (basePackages = "lk.ijse.gdse68.springpossystembackend")
@EnableTransactionManagement
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // Adjust this to your endpoint pattern
                .allowedOrigins("http://127.0.0.1:5501", "http://localhost:5501")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
