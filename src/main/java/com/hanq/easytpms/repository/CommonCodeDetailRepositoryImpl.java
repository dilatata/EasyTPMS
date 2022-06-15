package com.hanq.easytpms.repository;

import com.hanq.easytpms.mapper.CommonCodeDetailMapper;
import com.hanq.easytpms.vo.CommonCodeDetailVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonCodeDetailRepositoryImpl implements CommonCodeDetailRepository{

    private Jdbi jdbi;

    public CommonCodeDetailRepositoryImpl(Jdbi jdbi) {this.jdbi = jdbi;}

    @Override
    public void insertCodeDetail(CommonCodeDetailVO commonCodeDetailVO) {
        jdbi.useHandle(dao -> dao.createUpdate("INSERT INTO common_code_detail (code_group_id, code_detail_name, code_detail_desc, order_num, use_yn) " +
                        "VALUES(:codeGroupId, :codeDetailName, :codeDetailDesc, :orderNum, :useYn) ")
                .bindBean(commonCodeDetailVO)
                .defineNamedBindings()
                .execute()
        );
    }

    //detail edit은 개별개별의 저장보다 list를 한번에 넘겨서 저장하는 방법을 사용할꺼라 변경 필요
    @Override
    public void editCodeDetail(CommonCodeDetailVO commonCodeDetailVO) {
        jdbi.useHandle(dao->dao.createUpdate("UPDATE common_code_detail SET " +
                        "code_detail_name = :codeDetailName, code_detail_desc = :codeDetailDesc, order_num = :orderNum, use_yn = :useYn " +
                        "WHERE code_detail_id = :codeDetailId ")
                .bindBean(commonCodeDetailVO)
                .defineNamedBindings()
                .execute()
        );
    }

    @Override
    public void deleteCodeDetail(Long codeDetailId) {
        jdbi.useHandle(dao->dao.createUpdate("DELETE FROM common_code_detail WHERE code_Detail_id = :codeDetailId")
                .bind("codeDetailId",codeDetailId)
                .execute()
        );
    }

    @Override
    public List<CommonCodeDetailVO> getCodeDetailList(Long codeGroupId){
        Handle handle = jdbi.open();
        List<CommonCodeDetailVO> commonCodeDetailVOList = handle.createQuery("SELECT * FROM common_code_detail WHERE code_group_id = :codeGroupId")
                .bind("codeGroupId", codeGroupId)
                .map(new CommonCodeDetailMapper())
                .list();
        handle.close();
        return commonCodeDetailVOList;
    }
}
