package com.hanq.easytpms.repository;

import com.hanq.easytpms.mapper.TestDefectRowMapper;
import com.hanq.easytpms.vo.TestDefectVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TestDefectRepositoryImpl implements TestDefectRepository{
    private Jdbi jdbi;

    public TestDefectRepositoryImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    @Override
    public void insertTestDefect(TestDefectVO testDefectVO) {
        jdbi.useHandle(dao->dao.createUpdate("INSERT INTO defect ( execution_id, defect_category, defect_contents, defect_status, created_by, create_at, defect_team, defect_charger, " +
//                                "defect_start_due_date, defect_end_due_date, defect_date, " +
                                "defect_action_yn, defect_action_contents, defect_check, defect_check_date)" +
                "VALUES ( :executionId, :defectCategory, :defectContents, 'NEW', :createdBy, :createAt, :defectTeam, :defectCharger," +
//                        " :defectStartDueDate, :defectEndDueDate, :defectDate," +
                        " 'n', :defectActionContents, 'n', :defectCheckDate)")
                .bindBean(testDefectVO)
                .execute()
        );
    }

    @Override
    public void deleteTestDefect(Long defectId) {
        jdbi.useHandle(dao -> dao.createUpdate("DELETE defect WHERE defect_id = :defectId")
                .bind("defectId", defectId)
                .execute()
        );
    }

    @Override
    public List<TestDefectVO> findTestDefectListByExecutionId(Long id) {
        Handle handle = jdbi.open();
        List<TestDefectVO> testDefectVOList = handle.createQuery("SELECT * FROM defect WHERE execution_id = :executionId")
                .bind("executionId", id)
                .map(new TestDefectRowMapper())
                .list();
        handle.close();
        return testDefectVOList;
    }

    @Override
    public TestDefectVO findTestDefectInfoByDefectId(Long defectId) {
        Handle handle = jdbi.open();
        TestDefectVO testDefectVO = handle.createQuery("SELECT * FROM defect WHERE defect_id = :defectId")
                .bind("defectId", defectId)
                .map(new TestDefectRowMapper())
                .one();
        handle.close();
        return testDefectVO;
    }


    @Override
    public List<TestDefectVO> findDefectListByProjectName(String p_name) {
        Handle handle = jdbi.open();
        List<TestDefectVO> testDefectVOList = handle.createQuery("SELECT d.* FROM defect WHERE execution_id = :(SELECT execution_id FROM execution where e.project_name = :projectName)")
                .bind("projectName", p_name)
                .map(new TestDefectRowMapper())
                .list();
        handle.close();
        return testDefectVOList;
    }

    @Override
    public void editTestDefect(TestDefectVO request) {
        jdbi.useHandle(dao -> dao.createUpdate("UPDATE defect SET defect_category = :defectCategory, defect_contents = :defectContents, defect_status = :defectStatus," +
                "created_by = :createdBy, create_at = :createAt, defect_team = :defectTeam, defect_charger = :defectCharger," +
                "defect_start_due_date = :defectStartDueDate, defect_end_due_date = :defectEndDueDate, defect_date = :defectDate " +
//                ", defect_action_yn = :defectActionYn, defect_action_contents = :defectActionContents " + // edit 하는 상태에서 변경에 들어가야할까?
                        "WHERE defect_id = :defectId")
                .bindBean(request)
                .defineNamedBindings()
                .execute()
        );
    }

    @Override
    public void updateTestDefectY(Long id, String defectStatus, Date defectDate, String defectActionYn, String defectActionContents) {
        jdbi.useHandle(dao -> dao.createUpdate("UPDATE defect SET defect_action_yn = :defectActionYn, defect_action_contents = :defectActionContents, " +
                        "defect_status = :defectStatus, defect_date = :defectDate WHERE defect_id = :defectId")
                .bind("defectId", id)
                .bind("defectStatus", "조치완료") // 여기서 할지 화면에서 조치완료가 오도록할지
                .bind("defectDate",defectDate)
                .bind("defectActionYn", defectActionYn)
                .bind("defectActionContents",defectActionContents)
                .execute()
        );
    }

    @Override
    public void updateTestDefectCheckY(Long id, String defectStatus, String defectCheck, Date defectCheckDate) {
        jdbi.useHandle(dao->dao.createUpdate("UPDATE defect SET defect_status=:defectStatus, defect_check = defectCheck, defect_check_date = :defectCheckDate WHERE defect_id =defectId")
                .bind("defectStatus", "확인 완료")
                .bind("defectCheck", defectCheck)
                .bind("defectCheckDate", defectCheckDate)
                .bind("defectId", id)
                .execute()
        );
    }

    @Override
    public void updateTestDefectCheckN(Long id, String defectStatus, String defectActionYn) {
        jdbi.useHandle(dao->dao.createUpdate("UPDATE defect SET defect_status=:defectStatus, defect_check = defectCheck, defect_check_date = :defectCheckDate WHERE defect_id =defectId")
                .bind("defectStatus", "재결함")
                .bind("defectId", id)
                .bind("defectActionYn", "n")
                .execute()
        );

    }
}
