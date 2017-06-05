package sk.elko.trainings.hibernate.bookshop.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class})
public class AppConfig {

    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }

}
