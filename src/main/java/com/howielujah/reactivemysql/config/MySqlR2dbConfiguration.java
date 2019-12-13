package com.howielujah.reactivemysql.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.ConnectionPoolConfiguration;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories
public class MySqlR2dbConfiguration extends AbstractR2dbcConfiguration {

  @Value("${billing.datasource.username}")
  private String username;
  @Value("${billing.datasource.password}")
  private String password;
  @Value("${billing.datasource.host}")
  private String host;
  @Value("${billing.datasource.port}")
  private Integer port;
  @Value("${billing.datasource.database}")
  private String database;
  @Value("${billing.datasource.maximum-pool-size}")
  private Integer poolSize;

  @Override
  @Bean
  public ConnectionFactory connectionFactory() {
    return new JasyncConnectionFactory(
        new MySQLConnectionFactory(new ConnectionPoolConfiguration(host, port, database, username, password, poolSize).getConnectionConfiguration()));
  }

  @Bean
  ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
    return new R2dbcTransactionManager(connectionFactory);
  }

}
