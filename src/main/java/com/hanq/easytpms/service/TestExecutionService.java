package com.hanq.easytpms.service;

import com.hanq.easytpms.repository.TestDefectHistoryRepository;
import com.hanq.easytpms.repository.TestDefectRepository;
import com.hanq.easytpms.repository.TestExecutionRepository;
import com.hanq.easytpms.vo.TestDefectVO;
import com.hanq.easytpms.vo.TestExecutionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TestExecutionService {


    private final TestExecutionRepository testExecutionRepository;

    private final TestDefectRepository testDefectRepository;
    private final TestDefectHistoryRepository testDefectHistoryRepository;

    private TestExecutionVO testExecutionVO;
    private TestDefectVO testDefectVO;

    @Autowired
    public TestExecutionService(TestExecutionRepository testExecutionRepository, TestDefectRepository testDefectRepository,  TestDefectHistoryRepository testDefectHistoryRepository) {
        this.testExecutionRepository = testExecutionRepository;
        this.testDefectRepository = testDefectRepository;
        this.testDefectHistoryRepository = testDefectHistoryRepository;
    }


    // 단일 execution 생성
    public void insertTestExecution(TestExecutionVO request){
        log.info(String.valueOf(request));
        testExecutionRepository.insertTestExecution(request);
    }

    // 프로젝트 전체 조회 , 조건조회 추가하기
    public List<TestExecutionVO> getTestExecutionList(String projectName){
        return testExecutionRepository.getTestExecutionInfoByProjectName(projectName);
    }

    public List<TestExecutionVO > getTestExecutionList(){
        return testExecutionRepository.getTestExecutionList();
    }

    //  execution 단독 조회
    public TestExecutionVO getTestExecutionInfo(Long executionId) {
        return testExecutionRepository.getTestExecutionInfoByExecutionId(executionId);
    }

    // execution 단독 수정 -> request mapping 해서 저장
    public void editTestExecution(TestExecutionVO request) {
        testExecutionRepository.editTestExecution(request);
    }

    // execution 단독 삭제
    public void deleteTestExecution(Long executionId) {
        testExecutionRepository.deleteTestExecution(executionId);
    }

    // execution 결과입력 -> TestExecutionVO 말고 ExecutionID 수행일 수행상태 수행결과 전달
    public void updateTestExecution(TestExecutionVO request) {
        Long executionId = request.getExecutionId();
        Date executionDate = request.getExecutionDate();
        String execStatus = request.getExecStatus();
        String execResult = request.getExecResult();

        testExecutionRepository.updateTestExecution(executionId, executionDate, execStatus, execResult);
    }

    // if execution_status = 실패 execution update + defect create
    public void saveTestExecution(TestExecutionVO request, String sessionUserId) {
        Long executionId = request.getExecutionId();
        Date executionDate = request.getExecutionDate();
        String execStatus = request.getExecStatus();
        String execResult = request.getExecResult();
        List<TestDefectVO> listTestDefectVO = request.getTestDefectList();
        TestDefectVO testDefectVO = listTestDefectVO.get(0);
        testDefectVO.setCreatedBy(sessionUserId);
        System.out.println(testDefectVO);
        testExecutionRepository.updateTestExecution(executionId, executionDate, execStatus, execResult);
        testDefectRepository.insertTestDefect(testDefectVO);

    }

    // excel upload
    public void excelUpload(MultipartFile file) throws IOException, ParseException {
        int count =0;
        List<TestExecutionVO> dataList = new ArrayList<>();

        // multifile 타입 확인
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        // 1번 sheet 만 갖고옴
        Sheet worksheet = workbook.getSheetAt(0);

        log.info("row count : " +worksheet.getPhysicalNumberOfRows());

        DataFormatter formatter = new DataFormatter();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows() -1; i++) { // 4

            Row row = worksheet.getRow(i);
            TestExecutionVO data = new TestExecutionVO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            data.setProjectName(row.getCell(0).getStringCellValue());
            data.setTestType(row.getCell(1).getStringCellValue());
            data.setScenarioType(row.getCell(2).getStringCellValue());
            data.setBizCategory(row.getCell(3).getStringCellValue());
            data.setBizDetail(row.getCell(4).getStringCellValue());
            data.setVersion(formatter.formatCellValue(row.getCell(5)));
            data.setTeamName(row.getCell(6).getStringCellValue());
            data.setScenarioCategory(row.getCell(7).getStringCellValue());
            data.setTestScenarioId(row.getCell(8).getStringCellValue());
            data.setTestScenarioName(row.getCell(9).getStringCellValue());
            data.setScreenId(row.getCell(10).getStringCellValue());
            data.setScreenName(row.getCell(11).getStringCellValue());
            data.setTestCaseId(row.getCell(12).getStringCellValue());
            data.setTestCaseName(row.getCell(13).getStringCellValue());

            if(row.getCell(14) != null && row.getCell(14).getCellType() != Cell.CELL_TYPE_BLANK){
                java.util.Date utilStartDate = sdf.parse(row.getCell(14).getStringCellValue());
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                System.out.println(row.getCell(14).getStringCellValue());
                System.out.println(sqlStartDate);
                data.setExecDueDate(sqlStartDate);
            }
            if(row.getCell(15) != null && row.getCell(15).getCellType() != Cell.CELL_TYPE_BLANK){
                data.setTester(row.getCell(15).getStringCellValue());
            }
            data.setTestTargetType(row.getCell(16).getStringCellValue());
            if(row.getCell(16).equals("y") || row.getCell(16).equals("Y")){
                data.setTestTargetName(row.getCell(17).getStringCellValue());
            }
            data.setConfirmContents(row.getCell(18).getStringCellValue());
            data.setTestData(row.getCell(19).getStringCellValue());
            data.setBuildName(row.getCell(20).getStringCellValue());
            data.setBuildVersion(formatter.formatCellValue(row.getCell(21)));
            if(row.getCell(22) != null && row.getCell(22).getCellType() != Cell.CELL_TYPE_BLANK) {
                data.setNote(row.getCell(22).getStringCellValue());
            }
            dataList.add(data);
        }

        // repository 저장
        for (int i = 0; i < dataList.size(); i++) {
            testExecutionRepository.insertTestExecution(dataList.get(i));
            count += 1;
        }
        System.out.println(count);
    }


    // excel template download
    private void setHeaderCS(CellStyle cs, Font font, Cell cell) {
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        setHeaderFont(font, cell);
        cs.setFont(font);
        cell.setCellStyle(cs);
    }

    private void setHeaderFont(Font font, Cell cell) {
        font.setBoldweight((short) 700);
        font.setColor(HSSFColor.WHITE.index);
    }

    private void setCmmnCS2(CellStyle cs, Cell cell) {
        cs.setAlignment(CellStyle.ALIGN_LEFT);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cell.setCellStyle(cs);
    }

    public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {


        // .xlsx 확장자
        SXSSFWorkbook wb = new SXSSFWorkbook();

        Sheet sheet = wb.createSheet();



        Row row = sheet.createRow(0);
        Cell cell = null;
        CellStyle cs = wb.createCellStyle();
        Font font = wb.createFont();
        cell = row.createCell(0);
        cell.setCellValue("execution list - 예약신청 리스트");
        setHeaderCS(cs, font, cell);
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, 6));

        row = sheet.createRow(1);
        cell = null;
        cs = wb.createCellStyle();
        font = wb.createFont();

        cell = row.createCell(0);
        cell.setCellValue("프로젝트 이름");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(1);
        cell.setCellValue("테스트 유형");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(2);
        cell.setCellValue("시나리오 유형");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(3);
        cell.setCellValue("비즈니스 유형");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(4);
        cell.setCellValue("비즈니스 상세");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(5);
        cell.setCellValue("차수");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(6);
        cell.setCellValue("팀명");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(7);
        cell.setCellValue("시나리오 구분");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(8);
        cell.setCellValue("테스트 시나리오 Id");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(9);
        cell.setCellValue("테스트 시나리오 명");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(10);
        cell.setCellValue("화면 Id");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(11);
        cell.setCellValue("화면 명");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(12);
        cell.setCellValue("테스트 케이스 Id");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(13);
        cell.setCellValue("테스트 케이스 명");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(14);
        cell.setCellValue("실행 예정일(yyyy-MM-dd)");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(15);
        cell.setCellValue("테스터");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(16);
        cell.setCellValue("장비 I/F");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(17);
        cell.setCellValue("장비 명");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(18);
        cell.setCellValue("확인사항");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(19);
        cell.setCellValue("테스트 데이터");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(20);
        cell.setCellValue("빌드 명");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(21);
        cell.setCellValue("빌드 버전");
        setHeaderCS(cs, font, cell);

        cell = row.createCell(22);
        cell.setCellValue("비고");
        setHeaderCS(cs, font, cell);

        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"excelTemplateFile.xlsx\""));
//        response.setContentType("ms-vnd/excel");
//        response.setContentType("application/vnd.ms-excel");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        OutputStream fileOut = response.getOutputStream();
//        wb.write(fileOut);
//        fileOut.close();
        wb.write(response.getOutputStream());
        wb.close();
        wb.dispose();
//
//        response.getOutputStream().flush();
//        response.getOutputStream().close();
    }


}
