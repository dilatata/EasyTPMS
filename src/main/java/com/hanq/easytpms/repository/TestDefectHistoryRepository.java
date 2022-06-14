package com.hanq.easytpms.repository;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TestDefectHistoryRepository {
    // 결함 history 생성 -> table defect_action_date 자동 생성되도록 설정하기 DB 저장되는 시점
    @Transaction
    @SqlUpdate("INSERT INTO execution(defect_id, execution_id, defect_status, defect_team, defect_charge, defect_action_contents) " +
            "VALUES(:defectId, :executionId, :defectStatus, :defectTeam, :defectCharge, :defectActionContents) ")
    @GetGeneratedKeys
    void insertTestDefectHistory(@Bind("defect_id") BigInteger defectId,
                                 @Bind("execution_id") BigInteger executionId,
                                 @Bind("defect_status") String defectStatus,
                                 @Bind("defect_team") String defectTeam,
                                 @Bind("defect_charger") String defectCharger,
                                 @Bind("defect_action_contents") String defectActionContents);
}
