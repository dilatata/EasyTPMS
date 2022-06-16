package com.hanq.easytpms.repository;

import com.hanq.easytpms.mapper.CommonCodeGroupMapper;
import com.hanq.easytpms.vo.CommonCodeGroupVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonCodeGroupRepositoryImpl implements CommonCodeGroupRepository{

    private Jdbi jdbi;

    public CommonCodeGroupRepositoryImpl(Jdbi jdbi) {this.jdbi = jdbi;}

    @Override
    public void insertCodeGroup(CommonCodeGroupVO commonCodeGroupVO) {
        jdbi.useHandle(dao -> dao.createUpdate("INSERT INTO common_code_group(code_group_name, code_group_desc, use_yn) " +
                        "VALUES(:codeGroupName, :codeGroupDesc, :useYn) ")
                .bindBean(commonCodeGroupVO)
                .execute()
        );
    }

    @Override
    public void editCodeGroup(CommonCodeGroupVO commonCodeGroupVO) {
        jdbi.useHandle(dao->dao.createUpdate("UPDATE common_code_group SET " +
                        "code_group_name = :codeGroupName, code_group_desc = :codeGroupDesc, use_yn = :useYn " +
                        "WHERE code_group_id = :codeGroupId ")
                .bindBean(commonCodeGroupVO)
                .defineNamedBindings()
                .execute()
        );
    }

    // 삭제시에 해당 codeGroupId fk 로 사용하는 codedetail 있으면 삭제 불가
    @Override
    public void deleteCodeGroup(Long codeGroupId) {
        jdbi.useHandle(dao->dao.createUpdate("DELETE FROM common_code_group WHERE code_group_id = :codeGroupId")
                .bind("codeGroupId",codeGroupId)
                .execute()
        );
    }

    @Override
    public List<CommonCodeGroupVO> getCodeGroupInfoList() {
        Handle handle = jdbi.open();
        List<CommonCodeGroupVO> commonCodeGroupVOS = handle.createQuery("SELECT * FROM common_code_group")
                .map(new CommonCodeGroupMapper())
                .list();
        handle.close();
        return commonCodeGroupVOS;
    }

    @Override
    public CommonCodeGroupVO getCodeGroupInfo(Long codeGroupId) {
        Handle handle = jdbi.open();
        CommonCodeGroupVO commonCodeGroupVO = handle.createQuery("SELECT * FROM common_code_group WHERE code_group_id = :codeGroupId")
                .bind("codeGroupId",codeGroupId)
                .map(new CommonCodeGroupMapper())
                .one();
        handle.close();
        return commonCodeGroupVO;
    }
}
