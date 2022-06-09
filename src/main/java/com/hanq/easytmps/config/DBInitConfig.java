package com.hanq.easytmps.config;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@AutoConfigureBefore
@Configuration
public class DBInitConfig {
    // 서비스에 자동 연결될 수 있는 빈 생성성
   @Bean
    public Jdbi jdbi(DataSource datasource){
        return Jdbi.create(datasource)
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new H2DatabasePlugin());
    }


    private Jdbi jdbi;

    @Bean
    public void TestExecutionTable(Jdbi jdbi) {
        this.jdbi = jdbi;
        jdbi.useHandle(dao -> {
            dao.execute("CREATE TABLE IF NOT EXISTS execution(" +
                    "execution_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " project_name VARCHAR(500) NULL," +
                    " test_type VARCHAR(100) NOT NULL," +
                    " scenario_type VARCHAR(100) NOT NULL," +
                    " biz_category VARCHAR(200) NULL," +
                    " biz_detail VARCHAR(500) NULL," +
                    " version VARCHAR(10) NULL," +
                    " team_name VARCHAR(100) NULL," +
                    " scenario_category VARCHAR(200) NULL," +
                    " test_scenario_id VARCHAR(100) NULL," +
                    " test_scenario_name VARCHAR(500) NULL," +
                    " screen_id VARCHAR(100) NULL," +
                    " screen_name VARCHAR(500) NULL," +
                    " test_case_id VARCHAR(100) NOT NULL," +
                    " test_case_name VARCHAR(500) NOT NULL," +
                    " exec_due_date DATE NULL," +
                    " tester VARCHAR(100) NOT NULL," +
                    " test_target_type VARCHAR(100) NULL," +
                    " test_target_name VARCHAR(1000) NULL," +
                    " confirm_contents VARCHAR(2000) NULL," +
                    " test_data VARCHAR(2000) NULL," +
                    " build_name VARCHAR(500) NULL," +
                    " build_version VARCHAR(100) NULL," +
                    " note VARCHAR(2000) NULL," +
                    " execution_date DATE NULL," +
                    " exec_status VARCHAR(100) NULL," +
                    " exec_result VARCHAR(2000) NULL) ");
        });
    }

}
