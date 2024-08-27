package com.ohgiraffers.servletproject.employee.model.service;

import com.ohgiraffers.servletproject.employee.model.dao.EmployeeDAO;
import com.ohgiraffers.servletproject.employee.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.servletproject.common.jdbc.JDBCTemplate.*;

/**/
public class EmployeeService {
    private final EmployeeDAO empDAO;

    public EmployeeService() {
        empDAO = new EmployeeDAO();     //empDAO를 부를때만 객체 생성
    }

    public EmployeeDTO selectOneEmpById(String empId) {

        Connection con = getConnection();

        EmployeeDTO selectedEmp = empDAO.selectEmpById(con, empId);

        close(con);

        return selectedEmp;
    }
}
