package com.hanq.easytpms.config;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@AutoConfigureBefore
@Configuration
public class DBInitConfig {
    // 서비스에 자동 연결될 수 있는 빈 생성성
   @Bean
    public Jdbi jdbi(DataSource datasource){
        return Jdbi.create(datasource)
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new H2DatabasePlugin());
    }


}
