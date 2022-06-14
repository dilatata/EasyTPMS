package com.hanq.easytpms.controller;

import com.hanq.easytpms.service.TestDefectService;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;

public class EasyTPMSUserController {

    private final Jdbi jdbi;

    @Autowired
    public EasyTPMSUserController(Jdbi jdbi) {
        this.jdbi = jdbi;
        // user table
        jdbi.useHandle(dao -> {
            dao.execute("CREATE TABLE IF NOT EXISTS user(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id VARCHAR(255) NOT NULL, " +
                    "user_password VARCHAR(255) NOT NULL, " +
                    "user_name VARCHAR(100) NOT NULL, " +
                    "user_email VARCHAR(100) NULL, " +
                    "role_type VARCHAR(10) NOT NULL");
        });
    }


}
