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
        empDAO = new EmployeeDAO();
    }

    public List<EmployeeDTO> selectAllEmp() {

        Connection con = getConnection();

        List<EmployeeDTO> empList = empDAO.selectAllEmpList(con);

        close(con);

        return empList;

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

        return result;
    }
}
