package com.ohgiraffers.servletproject.employee.model.service;

import com.ohgiraffers.servletproject.employee.model.dao.EmployeeDAO;
import com.ohgiraffers.servletproject.employee.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.ohgiraffers.servletproject.common.jdbc.JDBCTemplate.*;

/**/
public class EmployeeService {
    private final EmployeeDAO empDAO;

    public EmployeeService() {
        empDAO = new EmployeeDAO();
    }

    public EmployeeDTO selectOneEmpById(String empId) {

        /* Connection 생성 */
        Connection con = getConnection();

        /* Connection과 함께 정보를 전달하여 조회한다. */
        EmployeeDTO selectedEmp = empDAO.selectEmpById(con, empId);

        /* connection 닫기 */
        close(con);

        /* 조회 결과를 반환한다. */
        return selectedEmp;
    }

    /* 직원 정보 전체 조회용 메소드 */
    public List<EmployeeDTO> selectAllEmp() {

        /* Connection 생성 */
        Connection con = getConnection();

        /* 비지니스 로직 */
        /* 1. dao 호출하여 조회 */
        List<EmployeeDTO> empList = empDAO.selectAllEmpList(con);

        /* Connection 닫기 */
        close(con);

        /* 수행 결과 반환 */
        return empList;
    }

    public String selectNewEmpId() {

        /* Connection 생성 */
        Connection con = getConnection();

        /* 비지니스 로직 */
        /* 1. dao 호출하여 조회 */
        String newEmpId = empDAO.selectNewEmpId(con);

        /* Connection 닫기 */
        close(con);

        /* 수행 결과 반환 */
        return newEmpId;
    }

    /* 신규 사원 등록용 메소드 */
    public int insertEmp(EmployeeDTO emp) {

        Connection con = getConnection();

        int result = empDAO.insertEmp(con, emp);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);

        return result;
    }

    public int updateEmp(EmployeeDTO emp) {

        Connection con = getConnection();  // 1. 데이터베이스 연결 생성

        int result = empDAO.updateEmp(con, emp);  // 2. 직원 정보를 업데이트하는 DAO 메서드 호출

        if (result > 0) {  // 3. 업데이트 성공 여부 확인
            commit(con);  // 4. 성공하면 트랜잭션을 커밋
        } else {
            rollback(con);  // 5. 실패하면 트랜잭션을 롤백
        }

        close(con);  // 6. 데이터베이스 연결 종료

        return result;  // 7. 업데이트 결과 반환
    }

    public int deleteEmp(String empId) {
        Connection con = getConnection();

        int result = empDAO.deleteEmp(con, empId);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);

        System.out.println("");

        return result;
    }
}
