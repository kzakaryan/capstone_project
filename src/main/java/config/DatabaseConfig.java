package config;

import lombok.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/dev_db")
                .username("dev_user")
                .password("dev_password")
                .build();
    }

    @Bean
    @Profile("local")
    public DataSource localDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:localdb")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource(
            @Value("${DB_URL}") String dbUrl,
            @Value("${DB_USER}") String dbUser,
            @Value("${DB_PASSWORD}") String dbPassword) {

        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(dbUser)
                .password(dbPassword)
                .build();
    }
}

