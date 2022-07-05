package com.hanq.easytpms;

import org.jdbi.v3.core.Jdbi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class EasyTPMSApplication {

	private Jdbi jdbi;

	public EasyTPMSApplication(Jdbi jdbi) {
		this.jdbi = jdbi;

		// execution table
		jdbi.useHandle(dao -> {
			dao.execute("CREATE TABLE IF NOT EXISTS execution(" +
					" execution_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
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

		// defect table
		jdbi.useHandle(dao -> {
			dao.execute("CREATE TABLE IF NOT EXISTS defect(" +
					" defect_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					" execution_id BIGINT NOT NULL, " +
					" defect_category VARCHAR(100) NULL," +
					" defect_contents VARCHAR(2000) NOT NULL," +
					" defect_status VARCHAR(100) NULL," +
					" created_by VARCHAR(100) NULL," +
					" create_at DATE NULL," +
					" defect_team VARCHAR(100) NOT NULL," +
					" defect_charger VARCHAR(100) NULL," +
					" defect_start_due_date DATE NULL," +
					" defect_end_due_date DATE NULL," +
					" defect_date DATE NULL," +
					" defect_action_yn VARCHAR(5) NOT NULL," +
					" defect_action_contents VARCHAR(2000) NULL," +
					" defect_check VARCHAR(5) NOT NULL," +
					" defect_check_date DATE NULL," +
					" foreign key (execution_id) references execution (execution_id)" +
					" on delete cascade) ");
		});

		// defect history table
		jdbi.useHandle(dao -> {
			dao.execute("CREATE TABLE IF NOT EXISTS defect_history(" +
					" defect_history_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					" defect_id BigInt NOT NULL," +
					" execution_id BIGINT NOT NULL, " +
					" defect_status VARCHAR(100) NOT NULL," +
					" defect_action_date DATE NULL," +
					" defect_team VARCHAR(100) NOT NULL," +
					" defect_charger VARCHAR(100) NULL," +
					" defect_action_contents VARCHAR(2000) NULL," +
					" foreign key (defect_id) references defect (defect_id), " +
					" foreign key (execution_id) references execution(execution_id)" +
					" on delete cascade) ");
		});

		// user table
		jdbi.useHandle(dao -> {
			dao.execute("CREATE TABLE IF NOT EXISTS tpms_user(" +
					" id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					" user_id VARCHAR(255) NOT NULL, " +
					" user_password VARCHAR(255) NOT NULL, " +
					" user_name VARCHAR(100) NOT NULL, " +
					" user_email VARCHAR(100) NULL, " +
					" role_type VARCHAR(10) NOT NULL)");
		});

		// common code group
		jdbi.useHandle(dao -> {
			dao.execute("CREATE TABLE IF NOT EXISTS common_code_group(" +
					" code_group_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					" code_group_name VARCHAR(100) NOT NULL, " +
					" code_group_desc VARCHAR(2000) NOT NULL, " +
					" use_yn VARCHAR(5) NOT NULL " +
					")");
		});

		// common code detail
		// order_number 더 생각해봐야함
		jdbi.useHandle(dao -> {
			dao.execute("CREATE TABLE IF NOT EXISTS common_code_detail (" +
					"code_detail_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					"code_group_id BIGINT NOT NULL, " +
					"code_detail_name VARCHAR(100) NOT NULL, " +
					"code_detail_desc VARCHAR(2000) NOT NULL, " +
					"order_num BIGINT UNIQUE NULL, " + //order 로만 하면 org.springframework.beans.BeanInstantiationException : ~~ : expected "identifier" error
					"use_yn VARCHAR(5) NOT NULL," +
					"foreign key (code_group_id) references common_code_group(code_group_id)" +
					"on delete cascade)");
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(EasyTPMSApplication.class, args);
	}


	//jsp
//	@Bean
//	public InternalResourceViewResolver setupViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}

//	@Bean
//	public InternalResourceViewResolver setupViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/resources/static/templates/");
//		resolver.setSuffix(".html");
//		return resolver;
//	}

}
