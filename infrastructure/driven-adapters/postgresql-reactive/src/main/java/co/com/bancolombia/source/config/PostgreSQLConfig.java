package co.com.bancolombia.source.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

@Configuration
@RequiredArgsConstructor
@EnableR2dbcRepositories
public class PostgreSQLConfig {

    @Bean
    public ConnectionFactoryInitializer initializer(
            @Qualifier("getConnectionConfig") ConnectionFactory connectionFactory) {

        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        return initializer;
    }

    @Bean
    public ConnectionPool getConnectionPool(@Qualifier("getConnectionConfig") ConnectionFactory connectionFactory){
        return new ConnectionPool (ConnectionPoolConfiguration.builder()
                .connectionFactory(connectionFactory)
                .build());
    }

    @Bean
    public ConnectionFactory getConnectionConfig() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .enableSsl()
                        .host("artifacts-database.cbcmndmlhgg0.us-east-2.rds.amazonaws.com")
                        .port(5432)
                        .database("postgres")
                        .username("postgres")
                        .password("admin123")
                        .sslMode(SSLMode.ALLOW)
                        .schema("artifacts")
                        .build());


    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(
            @Qualifier("getConnectionConfig") ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }

}
