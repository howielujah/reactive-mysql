package com.howielujah.reactivemysql.listener;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.Configuration;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import com.github.jasync.sql.db.mysql.util.URLParser;
import com.howielujah.reactivemysql.entity.Employee;
import io.r2dbc.spi.ConnectionFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private ConnectionFactory connectionFactory;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("Start inserting data into the company database" );
    DatabaseClient client = DatabaseClient.create(connectionFactory);

    try {
      Mono<Void> schemaStatement = executeStatement(client, readFileToString(new ClassPathResource("/schema.sql")));
      schemaStatement.block();
      executeStatement(client, readFileToString(new ClassPathResource("/data.sql"))).subscribe();
    } catch (IOException e) {
      log.error(e,e);
    }
  }


  private String readFileToString(Resource resource) throws IOException {
    try (Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8")) {
      return FileCopyUtils.copyToString(reader);
    }
  }

  private Mono<Void> executeStatement(DatabaseClient client, String sql) {
    String[] statements = statementsFrom(sql);
    return Flux
        .fromArray(statements)
        .flatMap(s -> client.execute(s).then())
        .then();
  }

  private String[] statementsFrom(String sql) {
    if (sql.contains(";")) {
      return sql.split(";");
    }
    else {
      return new String[]{sql};
    }
  }
}
