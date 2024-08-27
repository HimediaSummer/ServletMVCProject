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

}
