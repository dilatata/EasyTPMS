package com.hanq.easytpms.service;

import com.hanq.easytpms.repository.TestDefectHistoryRepository;
import com.hanq.easytpms.repository.TestDefectRepository;
import com.hanq.easytpms.repository.TestExecutionRepository;
import com.hanq.easytpms.vo.TestDefectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class TestDefectService {

    private final TestDefectRepository testDefectRepository;
//    private final TestDefectHistoryRepository testDefectHistoryRepository;


    @Autowired
    public TestDefectService(TestDefectRepository testDefectRepository) {// ,  TestDefectHistoryRepository testDefectHistoryRepository){
        this.testDefectRepository = testDefectRepository;
//        this.testDefectHistoryRepository = testDefectHistoryRepository;
    }


    // 시나리오 결함 생성 O
    public void insertTestDefect(TestDefectVO testDefectVO){
        testDefectRepository.insertTestDefect(testDefectVO);
    }

    // 시나리오 결함 개별 삭제 O
    public void deleteTestDefect(Long executionId){
        testDefectRepository.deleteTestDefect(executionId);
    }

    // 연관 결함 리스트 조회 O
    public List<TestDefectVO> findTestDefectListByExecutionId(Long executionId) {
        return testDefectRepository.findTestDefectListByExecutionId(executionId);
    }

    // 결함 리스트 조회 O
    // join projectName 에 해당하는 executionId 를 갖고 있는 data defect 테이블에서 찾아오기
    public List<TestDefectVO> findDefectListByProjectName(String projectName) {
        return testDefectRepository.findDefectListByProjectName(projectName);
    };

    // 결함 상세 조회 O
    public TestDefectVO findTestDefectInfoByDefectId(Long id){
        return testDefectRepository.findTestDefectInfoByDefectId(id);
    }

    // 결함 세부조건 작성(수정) O
    // 조치자 예정시작일, 예정 종료일 -> 최종상태 변경
    public void editTestDefect(TestDefectVO request) {
        if(request.getDefectCharger() != null){
            request.setDefectStatus("조치자지정");
            if (request.getDefectStartDueDate() != null && request.getDefectEndDueDate() != null){
                request.setDefectStatus("조치중");
            }
        }else{
            request.setDefectStatus("New");
        }
        testDefectRepository.editTestDefect(request);
    }

    // 결함조치 결과 작성 -> defectId defect_status defect_date, defect_action_yn, defect_action_contents
    // defect_action_yn - y (defect status 조치완료) / n (변경필요 없음)
    public void updateTestDefect(TestDefectVO request) {
        System.out.println(request.getDefectActionYn());
        if(request.getDefectActionYn().equals("y")){
        Long defectId = request.getDefectId();
        String defectStatus = request.getDefectStatus(); // 조건문 필요
        Date defectDate = request.getDefectDate(); // 조치확인 y로 변한 날 변경해
        String defectActionYn = request.getDefectActionYn();
        String defectActionContents = request.getDefectActionContents();
        BigInteger executionId = BigInteger.valueOf(request.getExecutionId());
        String defectTeam = request.getDefectTeam();;
        String defectCharger = request.getDefectCharger();

        testDefectRepository.updateTestDefectY(defectId, defectStatus, defectDate, defectActionYn, defectActionContents);
//        testDefectHistoryRepository.insertTestDefectHistory(defectId, executionId, defectStatus, defectTeam, defectCharger, defectActionContents);
        }else{
            System.out.println("여긴 뭐가 들어가야 할까");
        }
    }

    // 결함조치 확인 작성 -> 조치 결과 defect_check = y 면 defect_check_date, defect_status 변경, / no -> 결함최종상태 재결함으로 만들기
    public void updateTestDefectCheck(TestDefectVO request){
        Long defectId = request.getDefectId();
        String defectStatus = request.getDefectStatus(); //-> 조치 여부 y 이면 조치 완료
        Date defectDate = request.getDefectDate(); // 조치 확인 n인 경우 비우나?
        String defectActionYn = request.getDefectActionYn();
        String defectActionContents = request.getDefectActionContents();
        BigInteger executionId = BigInteger.valueOf(request.getExecutionId());
        String defectTeam = request.getDefectTeam();;
        String defectCharger = request.getDefectCharger();

        // 조치 확인
        String defectCheck = request.getDefectCheck();
        Date defectCheckDate = request.getDefectCheckDate(); // request.getDefectCheck y인 경우만 생성 n -> status"재결함", defectdate null값으로
        if(defectCheck == "y") {
            testDefectRepository.updateTestDefectCheckY(defectId,defectStatus, defectCheck, defectCheckDate); // check 결과 따라서 조건문으로 만들기
        }else{
            testDefectRepository.updateTestDefectCheckN(defectId);
        }
//        testDefectHistoryRepository.insertTestDefectHistory(defectId, executionId, defectStatus, defectTeam, defectCharger, defectActionContents);
    }

    // 결함 첨부파일 생성


}

