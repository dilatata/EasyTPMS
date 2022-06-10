package com.hanq.easytpms;

import com.hanq.easytpms.vo.TestExecutionVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class EasyTPMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyTPMSApplication.class, args);
	}
}
