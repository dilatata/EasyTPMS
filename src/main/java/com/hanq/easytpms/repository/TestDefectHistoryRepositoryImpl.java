package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestDefectHistoryVO;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

@Repository
public class TestDefectHistoryRepositoryImpl implements TestDefectHistoryRepository{
    private Jdbi jdbi;

    public TestDefectHistoryRepositoryImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    @Override
    public void insertTestDefectHistory(Long defectId, Long executionId, String defectStatus, String defectTeam, String defectCharger, String defectActionContents) {
        jdbi.useHandle(dao->dao.createUpdate("INSERT INTO defect_history ( defect_id, execution_id, defect_status, defect_team, defect_charger, defect_action_contents, defect_action_date)" +
                        "VALUES (:defectId, :executionId, :defectStatus, :defectTeam, :defectCharger, :defectActionContents, now())")
                .bind("defectId", defectId)
                .bind("executionId",executionId)
                .bind("defectStatus", defectStatus)
                .bind("defectTeam", defectTeam)
                .bind("defectCharger", defectCharger)
                .bind("defectActionContents",defectActionContents)
                .execute()
        );
    }

    @Override
    public void insertTestDefectHistory2(TestDefectHistoryVO request) {
        jdbi.useHandle(dao->dao.createUpdate("INSERT INTO defect_history ( defect_id, execution_id, defect_status, defect_team, defect_charger, defect_action_contents, defect_action_date)" +
                        "VALUES (:defectId, :executionId, :defectStatus, :defectTeam, :defectCharger, :defectActionContents, now())")
                .bindBean(request)
                .defineNamedBindings()
                .execute()
        );
    }
}
