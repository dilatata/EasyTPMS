package com.hanq.easytpms;

import org.jdbi.v3.core.Jdbi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyTPMSApplication {

	// h2 연결은 되지만 table생성되지 않음 -> 부르고 tjqltm, repository 찾아가게 만들면 생성될까?
	private Jdbi jdbi;

	public void createExecutionTable(Jdbi jdbi) {
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

	public static void main(String[] args) {
		SpringApplication.run(EasyTPMSApplication.class, args);
	}
}
