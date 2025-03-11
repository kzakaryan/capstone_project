package config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("admin")
                .password("pgadmin")
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
    public DataSource prodDataSource() {

        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("admin")
                .password("pgadmin")
                .build();
    }
}

