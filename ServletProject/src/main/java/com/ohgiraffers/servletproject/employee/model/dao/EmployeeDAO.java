package com.ohgiraffers.servletproject.employee.model.dao;

import com.ohgiraffers.servletproject.common.config.ConfigLocation;
import com.ohgiraffers.servletproject.employee.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.servletproject.common.jdbc.JDBCTemplate.close;

public class EmployeeDAO {

    private final Properties prop;

    public EmployeeDAO() {

        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "employee-mapper.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmployeeDTO selectEmpById(Connection con, String empId) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO selectedEmp = null;

        String query = prop.getProperty("selectEmpById");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                selectedEmp = new EmployeeDTO();

                selectedEmp.setEmpId(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
                selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectedEmp.setJobCode(rset.getString("JOB_CODE"));
                selectedEmp.setSalary(rset.getInt("SALARY"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return selectedEmp;
    }

    public String selectNewEmpId(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String newEmpId = null;

        String query = prop.getProperty("selectNewEmpId");

        try {
            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                newEmpId = rset.getString("EMP_ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return newEmpId;
    }

    // insert 사원등록
    public int insertEmp(Connection con, EmployeeDTO emp) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("insertEmp");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, emp.getEmpId());
            pstmt.setString(2, emp.getEmpName());
            pstmt.setString(3, emp.getEmpNo());
            pstmt.setString(4, emp.getEmail());
            pstmt.setString(5, emp.getPhone());
            pstmt.setString(6, emp.getDeptCode());
            pstmt.setString(7, emp.getJobCode());
            pstmt.setString(8, emp.getSalLevel());
            pstmt.setInt(9, emp.getSalary());
            pstmt.setDouble(10, emp.getBonus());
            pstmt.setString(11, emp.getManagerId());
            pstmt.setDate(12, emp.getHireDate());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int updateEmp(Connection con, EmployeeDTO emp) {
        PreparedStatement pstmt = null;  // PreparedStatement 객체를 선언합니다.
        int result = 0;  // 쿼리 실행 결과를 저장할 변수입니다.

        // SQL 쿼리를 properties 파일에서 읽어옵니다.
        String query = prop.getProperty("updateEmp");
        // properties는 데이터베이스 연결 정보(왼쪽에 connection-info.properties) 라던지 SQL쿼리를 가져올수있슴


        try {
            //Connection 객체(con)를 사용하여 PreparedStatement 객체(pstmt)를 생성하고, query를 pstmt에 담는다.
            pstmt = con.prepareStatement(query);

            pstmt.setDate(1, emp.getEntDate());  // 첫 번째 ?에 emp의 입사일 값을 설정합니다.
            pstmt.setString(2, emp.getEmpId());  // 두 번째 ?에 emp의 ID 값을 설정합니다.

            // 쿼리를 실행하고, 영향을 받은 행의 수를 반환받습니다.
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // SQLException 발생 시 예외를 출력합니다.
        } finally {
            close(pstmt);  // PreparedStatement 자원을 해제합니다.
        }

        return result;  // 영향을 받은 행의 수를 반환합니다.
    }

}
